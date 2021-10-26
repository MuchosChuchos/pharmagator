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
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
@Slf4j
@RequiredArgsConstructor
@Qualifier("pharmacyAlteiaDataProvider")
public class PharmacyAlteiaDataProvider implements DataProvider {

    @Qualifier("pharmacyAlteiaWebClient")
    private final WebClient alteiaClient;

    @Value("${pharmagator.data-providers.apteka-alteia.products-fetch-url}")
    private String productsFetchUrl;

    @Override
    public Stream<MedicineDto> loadData() {
        String html = this.fetchHtmlByPage(1);
        return this.getMedicineDtoStreamFromHtml(html);
    }

    private Stream<MedicineDto> getMedicineDtoStreamFromHtml(String html) {
        Document page = Jsoup.parse(html);

        Stream<MedicineDto> medicineDtoStream = this.getMedicineStreamFromPage(page);

        int lastPage;
        int currentPage = 2;

        do {
            Document newPage = Jsoup.parse(this.fetchHtmlByPage(currentPage));
            Elements pageNumbers = newPage.select("ul[class=page-numbers]").select("a[class=page-numbers]");
            lastPage = Integer.parseInt(pageNumbers.last().text());

            medicineDtoStream = Stream.concat(medicineDtoStream, this.getMedicineStreamFromPage(newPage));
            currentPage++;
        } while (currentPage <= lastPage);

        return medicineDtoStream;
    }

    private Stream<MedicineDto> getMedicineStreamFromPage(Document page) {
        return this.getMedicineFromHtml(page).stream();
    }

    private List<MedicineDto> getMedicineFromHtml(Document page) {
        List<MedicineDto> medicineDtos = new ArrayList<>();
        Elements medsName = page.select("h2[class=woocommerce-loop-product__title]");
        Element products = page.select("ul[class=products columns-4]").first();
        Elements medsPrice = products.select("span[class=woocommerce-Price-amount amount]");
        Elements medsId = page.select("a[class=woocommerce-LoopProduct-link woocommerce-loop-product__link]");
        for (int i = 0; i < medsName.size(); i++) {
            if ("".equals(medsName.get(i).text())) {
                medsName.remove(i);
                medsId.remove(i);
            }
            try {
                medicineDtos.add(MedicineDto.builder()
                        .externalId(this.getTextBetween(medsId.get(i).attr("href"), "product/", "/"))
                        .price(new BigDecimal(this.getTextBetween(medsPrice.get(i).text(), "", "\u20B4").replaceAll(",", "")))
                        .title(medsName.get(i).text())
                        .pharmacy("Alteia")
                        .build());
            } catch (ArrayIndexOutOfBoundsException ex) {
                log.info("Exception ({}) was on page: {}", ex.getMessage(), page.text());
            }
        }
        return medicineDtos;
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

    private String fetchHtmlByPage(int page) {
        return this.alteiaClient.post()
                .uri(productsFetchUrl + "/" + page)
                .body(Mono.just("ppp=96"), String.class)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
