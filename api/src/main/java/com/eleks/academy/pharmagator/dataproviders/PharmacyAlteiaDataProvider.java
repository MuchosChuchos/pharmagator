package com.eleks.academy.pharmagator.dataproviders;

import com.eleks.academy.pharmagator.dataproviders.dto.MedicineDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
@Slf4j
@RequiredArgsConstructor
@Qualifier("pharmacyAlteiaDataProvider")
public class PharmacyAlteiaDataProvider implements DataProvider {

    @Qualifier("pharmacyAlteiaWebClient")
    private final WebClient alteiaClient;

    @Value("${pharmagator.data-providers.pharmacy-alteia.products-fetch-url}")
    private String productsFetchUrl;

    @Value("${pharmagator.data-providers.pharmacy-alteia.page-limit}")
    private Long pageLimit;

    @Value("${pharmagator.data-providers.pharmacy-alteia.pharmacy-name}")
    private String pharmacyName;

    @Value("${pharmagator.data-providers.pharmacy-alteia.start-page-to-parse}")
    private Long startPage;

    @Override
    public Stream<MedicineDto> loadData() {
        String html = this.fetchHtmlByPage(1L);
        return this.getMedicineDtoStreamFromHtml(html);
    }

    private Stream<MedicineDto> getMedicineDtoStreamFromHtml(String html) {
        Document page = Jsoup.parse(html);

        Stream<MedicineDto> medicineDtoStream = this.getMedicineStreamFromPage(page);

        long lastPage;
        long currentPage = startPage;

        do {
            Document newPage = Jsoup.parse(this.fetchHtmlByPage(currentPage));
            Elements pageNumbers = newPage.select("ul[class=page-numbers]").select("a[class=page-numbers]");
            int totalPages = Integer.parseInt(pageNumbers.last().text());
            lastPage = totalPages > pageLimit ? pageLimit : totalPages;

            medicineDtoStream = Stream.concat(medicineDtoStream, this.getMedicineStreamFromPage(newPage));
            currentPage++;
        } while (currentPage <= lastPage);

        return medicineDtoStream;
    }

    private Stream<MedicineDto> getMedicineStreamFromPage(Document page) {
        List<MedicineDto> medicineDtos = new ArrayList<>();
        Elements medsName = page.select("h2[class=woocommerce-loop-product__title]");
        Element products = page.select("ul[class=products columns-4]").first();
        Elements medsPrice = products.select("span[class=woocommerce-Price-amount amount]");
        Elements medsId = page.select("a[class=woocommerce-LoopProduct-link woocommerce-loop-product__link]");
        IntStream.range(0, medsName.size())
                .forEach((i) -> buildMedicineDto(page, medicineDtos, medsName, medsPrice, medsId, i));
        return medicineDtos.stream();
    }

    private void buildMedicineDto(Document page, List<MedicineDto> medicineDtos, Elements medsName, Elements medsPrice, Elements medsId, int i) {
        if (medsName.get(i).text().trim().isEmpty()) {
            medsName.remove(i);
            medsId.remove(i);
        }

        try {
            medicineDtos.add(MedicineDto.builder()
                    .externalId(this.getTextBetween(medsId.get(i).attr("href"), "product/", "/"))
                    .price(new BigDecimal(this.getTextBetween(medsPrice.get(i).text(), "", "\u20B4").replace(",", "")))
                    .title(medsName.get(i).text())
                    .pharmacyName(pharmacyName)
                    .build());
        } catch (ArrayIndexOutOfBoundsException ex) {
            /*
             Means that html page have not valid structure of medicine.
             price or title not exists
             */
            log.info("Exception ({}) was on page: {}", ex.getMessage(), page.text());
        }
    }

    private String getTextBetween(String html, String before, String after) {
        int start = html.indexOf(before);
        if (start == -1) {
            return "";
        }
        start += before.length();
        int end = html.indexOf(after, start);
        if (end == -1) {
            return "";
        }
        return html.substring(start, end);
    }

    private String fetchHtmlByPage(long page) {
        return this.alteiaClient.post()
                .uri(productsFetchUrl + "/" + page)
                .body(Mono.just("ppp=96"), String.class)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
