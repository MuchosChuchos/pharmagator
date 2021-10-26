package com.eleks.academy.pharmagator.dataproviders;

import com.eleks.academy.pharmagator.dataproviders.dto.MedicineDto;
import com.eleks.academy.pharmagator.dataproviders.dto.rozetka.RozetkaIdsResponse;
import com.eleks.academy.pharmagator.dataproviders.dto.rozetka.RozetkaIdsResponseData;
import com.eleks.academy.pharmagator.dataproviders.dto.rozetka.RozetkaMedicineDto;
import com.eleks.academy.pharmagator.dataproviders.dto.rozetka.RozetkaMedicineResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Stream;

@Service
@Slf4j
@RequiredArgsConstructor
@Qualifier("pharmacyRozetkaDataProvider")
public class PharmacyRozetkaDataProvider implements DataProvider {

    @Qualifier("pharmacyRozetkaWebClient")
    private final WebClient rozetkaClient;

    @Value("${pharmagator.data-providers.apteka-rozetka.ids-fetch-url}")
    private String idsFetchUrl;

    @Value("${pharmagator.data-providers.apteka-rozetka.category-id}")
    private String categoryId;

    @Value("${pharmagator.data-providers.apteka-rozetka.sell-status}")
    private String sellStatus;

    @Value("${pharmagator.data-providers.apteka-rozetka.goods-fetch-url}")
    private String goodsPath;

    @Override
    public Stream<MedicineDto> loadData() {
        RozetkaIdsResponseData rozetkaIdsResponse;

        Stream<MedicineDto> medicineDtoStream = Stream.of();
        int currentPage = 1;

        do {
            rozetkaIdsResponse = this.fetchIds(currentPage);
            medicineDtoStream = Stream.concat(medicineDtoStream, this.fetchMedicines(rozetkaIdsResponse.getIds()));
            currentPage++;

        } while (rozetkaIdsResponse.getShowNext() != 0);

        return medicineDtoStream;
    }

    private Stream<MedicineDto> fetchMedicines(List<Long> ids) {
        String productIds = StringUtils.collectionToDelimitedString(ids, ",");
        RozetkaMedicineResponse response = this.rozetkaClient.get().uri(uriBuilder -> uriBuilder
                        .path(goodsPath)
                        .queryParam("product_ids", productIds)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<RozetkaMedicineResponse>() {
                })
                .block();

        if (response != null) {
            return response.getData().stream()
                    .map(this::mapToMedicineDto);
        }
        return Stream.empty();
    }


    private RozetkaIdsResponseData fetchIds(int page) {
        RozetkaIdsResponse idsResponse = this.rozetkaClient.get().uri(uriBuilder -> uriBuilder
                        .path(idsFetchUrl)
                        .queryParam("category_id", categoryId)
                        .queryParam("sell_status", sellStatus)
                        .queryParam("page", page)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<RozetkaIdsResponse>() {
                })
                .block();
        return idsResponse.getData();
    }

    private MedicineDto mapToMedicineDto(RozetkaMedicineDto rozetkaMedicineDto) {
        return MedicineDto.builder()
                .externalId(rozetkaMedicineDto.getId().toString())
                .price(rozetkaMedicineDto.getPrice())
                .title(rozetkaMedicineDto.getTitle())
                .pharmacy("Rozetka")
                .build();
    }
}
