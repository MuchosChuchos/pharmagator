package com.eleks.academy.pharmagator.dataproviders;

import com.eleks.academy.pharmagator.dataproviders.dto.MedicineDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.spy;

@ActiveProfiles("test")
public class PharmacyAlteiaDataProviderTest {

    private static WebClient alteiaClient;

    private static PharmacyAlteiaDataProvider mockProvider;

    private static String html;

    private static List<MedicineDto> dtos;

    @BeforeAll
    public static void setUp() {
        mockProvider = spy(new PharmacyAlteiaDataProvider(alteiaClient));
        dtos = List.of(
                MedicineDto.builder().title("Нафтизин капли 0.1% 10мл").price(new BigDecimal("19.55")).externalId("naftyzyn-kaply-0-1-10ml").pharmacyName("alteia").build(),
                MedicineDto.builder().title("Маска медицинская защитная").price(new BigDecimal("1.90")).externalId("maska-medytsynskaia-zashchytnaia").pharmacyName("alteia").build(),
                MedicineDto.builder().title("Флуоксетин табл. п/о 0.02г №10").price(new BigDecimal("25.75")).externalId("fluoksetyn-tabl-p-o-0-02h-10").pharmacyName("alteia").build(),
                MedicineDto.builder().title("Шприц Артериум 2мл. 2комп. 1игла").price(new BigDecimal("2.50")).externalId("shpric-arterium-2ml-2komp-1igla").pharmacyName("alteia").build(),
                MedicineDto.builder().title("Уголь активированный табл. 0.25г №10").price(new BigDecimal("13.65")).externalId("uhol-aktyvyrovannyi-tabl-0-25h-10").pharmacyName("alteia").build(),
                MedicineDto.builder().title("Витамин С апельсин табл. 500мг №10").price(new BigDecimal("894.80")).externalId("vytamyn-s-apelsyn-tabl-500mh-10").pharmacyName("alteia").build(),
                MedicineDto.builder().title("Виагра табл. п/о 100мг №4").price(new BigDecimal("894.80")).externalId("vyahra-tabl-p-o-100mh-4").pharmacyName("alteia").build(),
                MedicineDto.builder().title("Шприц 3-х компон. 2 мл с иглой 23 G").price(new BigDecimal("2.95")).externalId("shpryts-3-kh-kompon-2-ml-s-yhloi-23-g").pharmacyName("alteia").build(),
                MedicineDto.builder().title("Вормил табл. жев.400мг №3").price(new BigDecimal("92.20")).externalId("vormyl-tabl-zhev-400mh-3").pharmacyName("alteia").build(),
                MedicineDto.builder().title("Гидазепам IC табл. 0.02г №20 (10×2)").price(new BigDecimal("104.70")).externalId("hydazepam-ic-tabl-0-02h-20-10x2").pharmacyName("alteia").build()
        );
        html = """
                            
                <html lang="uk"><head></head>
                <!DOCTYPE html>
                <body class="archive post-type-archive post-type-archive-product wp-custom-logo wp-embed-responsive _masterslider _ms_version_3.6.4 woocommerce woocommerce-page woocommerce-js storefront-full-width-content storefront-align-wide right-sidebar woocommerce-active" data-new-gr-c-s-check-loaded="14.1039.0" data-gr-ext-installed="">
                <div id="page" class="hfeed site">
                <header id="masthead" class="site-header" role="banner" style="">
                <div class="col-full">	<a class="skip-link screen-reader-text" href="#site-navigation">Перейти до навігації</a>
                <a class="skip-link screen-reader-text" href="#content">Перейти до контенту</a>
                <div class="site-branding">
                <a href="/" class="custom-logo-link" rel="home" itemprop="url"><script data-pagespeed-no-defer="">(function(){for(var g="function"==typeof Object.defineProperties?Object.defineProperty:function(b,c,a){if(a.get||a.set)throw new TypeError("ES3 does not support getters and setters.");b!=Array.prototype&&b!=Object.prototype&&(b[c]=a.value)},h="undefined"!=typeof window&&window===this?this:"undefined"!=typeof global&&null!=global?global:this,k=["String","prototype","repeat"],l=0;l<k.length-1;l++){var m=k[l];m in h||(h[m]={});h=h[m]}var n=k[k.length-1],p=h[n],q=p?p:function(b){var c;if(null==this)throw new TypeError("The 'this' value for String.prototype.repeat must not be null or undefined");c=this+"";if(0>b||1342177279<b)throw new RangeError("Invalid count value");b|=0;for(var a="";b;)if(b&1&&(a+=c),b>>>=1)c+=c;return a};q!=p&&null!=q&&g(h,n,{configurable:!0,writable:!0,value:q});var t=this;function u(b,c){var a=b.split("."),d=t;a[0]in d||!d.execScript||d.execScript("var "+a[0]);for(var e;a.length&&(e=a.shift());)a.length||void 0===c?d[e]?d=d[e]:d=d[e]={}:d[e]=c};function v(b){var c=b.length;if(0<c){for(var a=Array(c),d=0;d<c;d++)a[d]=b[d];return a}return[]};function w(b){var c=window;if(c.addEventListener)c.addEventListener("load",b,!1);else if(c.attachEvent)c.attachEvent("onload",b);else{var a=c.onload;c.onload=function(){b.call(this);a&&a.call(this)}}};var x;function y(b,c,a,d,e){this.h=b;this.j=c;this.l=a;this.f=e;this.g={height:window.innerHeight||document.documentElement.clientHeight||document.body.clientHeight,width:window.innerWidth||document.documentElement.clientWidth||document.body.clientWidth};this.i=d;this.b={};this.a=[];this.c={}}function z(b,c){var a,d,e=c.getAttribute("data-pagespeed-url-hash");if(a=e&&!(e in b.c))if(0>=c.offsetWidth&&0>=c.offsetHeight)a=!1;else{d=c.getBoundingClientRect();var f=document.body;a=d.top+("pageYOffset"in window?window.pageYOffset:(document.documentElement||f.parentNode||f).scrollTop);d=d.left+("pageXOffset"in window?window.pageXOffset:(document.documentElement||f.parentNode||f).scrollLeft);f=a.toString()+","+d;b.b.hasOwnProperty(f)?a=!1:(b.b[f]=!0,a=a<=b.g.height&&d<=b.g.width)}a&&(b.a.push(e),b.c[e]=!0)}y.prototype.checkImageForCriticality=function(b){b.getBoundingClientRect&&z(this,b)};u("pagespeed.CriticalImages.checkImageForCriticality",function(b){x.checkImageForCriticality(b)});u("pagespeed.CriticalImages.checkCriticalImages",function(){A(x)});function A(b){b.b={};for(var c=["IMG","INPUT"],a=[],d=0;d<c.length;++d)a=a.concat(v(document.getElementsByTagName(c[d])));if(a.length&&a[0].getBoundingClientRect){for(d=0;c=a[d];++d)z(b,c);a="oh="+b.l;b.f&&(a+="&n="+b.f);if(c=!!b.a.length)for(a+="&ci="+encodeURIComponent(b.a[0]),d=1;d<b.a.length;++d){var e=","+encodeURIComponent(b.a[d]);131072>=a.length+e.length&&(a+=e)}b.i&&(e="&rd="+encodeURIComponent(JSON.stringify(B())),131072>=a.length+e.length&&(a+=e),c=!0);C=a;if(c){d=b.h;b=b.j;var f;if(window.XMLHttpRequest)f=new XMLHttpRequest;else if(window.ActiveXObject)try{f=new ActiveXObject("Msxml2.XMLHTTP")}catch(r){try{f=new ActiveXObject("Microsoft.XMLHTTP")}catch(D){}}f&&(f.open("POST",d+(-1==d.indexOf("?")?"?":"&")+"url="+encodeURIComponent(b)),f.setRequestHeader("Content-Type","application/x-www-form-urlencoded"),f.send(a))}}}function B(){var b={},c;c=document.getElementsByTagName("IMG");if(!c.length)return{};var a=c[0];if(!("naturalWidth"in a&&"naturalHeight"in a))return{};for(var d=0;a=c[d];++d){var e=a.getAttribute("data-pagespeed-url-hash");e&&(!(e in b)&&0<a.width&&0<a.height&&0<a.naturalWidth&&0<a.naturalHeight||e in b&&a.width>=b[e].o&&a.height>=b[e].m)&&(b[e]={rw:a.width,rh:a.height,ow:a.naturalWidth,oh:a.naturalHeight})}return b}var C="";u("pagespeed.CriticalImages.getBeaconData",function(){return C});u("pagespeed.CriticalImages.Run",function(b,c,a,d,e,f){var r=new y(b,c,a,e,f);x=r;d&&w(function(){window.setTimeout(function(){A(r)},0)})});})();pagespeed.CriticalImages.Run('/ngx_pagespeed_beacon','https://apteka.alteia.ua/shop/','BWCRDMz9rM',true,false,'b6t7wYPj-Kw');</script><img width="500" height="118" src="/wp-content/uploads/2020/11/cropped-1alte.png.pagespeed.ce.SP9FKGs8dJ.png" class="custom-logo" alt="Інтернет-аптека Алтея" itemprop="logo" srcset="https://apteka.alteia.ua/wp-content/uploads/2020/11/cropped-1alte.png.pagespeed.ce.SP9FKGs8dJ.png 500w, https://apteka.alteia.ua/wp-content/uploads/2020/11/cropped-1alte-416x98.png.pagespeed.ce.aDHrqAFwRm.png 416w, https://apteka.alteia.ua/wp-content/uploads/2020/11/cropped-1alte-300x71.png.pagespeed.ce.5G3WJ4ieW8.png 300w" sizes="(max-width: 500px) 100vw, 500px" data-pagespeed-url-hash="4045242853" onload="pagespeed.CriticalImages.checkImageForCriticality(this);"></a>	</div>
                <div class="site-search">
                <div class="widget woocommerce widget_product_search"><div class="aws-container" data-url="/?wc-ajax=aws_action" data-siteurl="https://apteka.alteia.ua" data-lang="" data-show-loader="true" data-show-more="true" data-show-page="true" data-show-clear="true" data-mobile-screen="false" data-use-analytics="false" data-min-chars="1" data-buttons-order="2" data-is-mobile="false" data-page-id="0" data-tax=""><form class="aws-search-form aws-show-clear" action="/" method="get" role="search"><div class="aws-wrapper"><input type="search" name="s" value="" class="aws-search-field" placeholder="Пошук" autocomplete="off"><input type="hidden" name="post_type" value="product"><input type="hidden" name="type_aws" value="true"><div class="aws-search-clear"><span>×</span></div><div class="aws-loader"></div></div><div class="aws-search-btn aws-form-btn"><span class="aws-search-btn_icon"><svg focusable="false" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24px"><path d="M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z"></path></svg></span></div></form></div></div>	</div>
                </div><div class="storefront-primary-navigation"><div class="col-full">	<nav id="site-navigation" class="main-navigation" role="navigation" aria-label="Головне меню">
                <button class="menu-toggle" aria-controls="site-navigation" aria-expanded="false"><span>Меню</span></button>
                <div class="primary-navigation"><ul id="menu-osnovnoe" class="menu nav-menu" aria-expanded="false"><li id="menu-item-4187" class="menu-item menu-item-type-post_type menu-item-object-page current-menu-item current_page_item menu-item-4187"><a href="/shop/">Товари</a></li>
                <li id="menu-item-176" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-176"><a href="/instrukcii/">Інструкцiї</a></li>
                <li id="menu-item-22" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-22"><a href="/apteki/">Наші аптеки</a></li>
                <li id="menu-item-26" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-26"><a href="/faq/">Питання-Відповідь</a></li>
                <li id="menu-item-25" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-25"><a href="/kontakty/">Контакти</a></li>
                <li id="menu-item-49" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-49"><a href="/my-account/">Мій кабінет</a></li>
                </ul></div><div class="handheld-navigation"><ul id="menu-osnovnoe-1" class="menu"><li class="menu-item menu-item-type-post_type menu-item-object-page current-menu-item current_page_item menu-item-4187"><a href="/shop/">Товари</a></li>
                <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-176"><a href="/instrukcii/">Інструкцiї</a></li>
                <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-22"><a href="/apteki/">Наші аптеки</a></li>
                <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-26"><a href="/faq/">Питання-Відповідь</a></li>
                <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-25"><a href="/kontakty/">Контакти</a></li>
                <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-49"><a href="/my-account/">Мій кабінет</a></li>
                </ul></div>	</nav><!-- #site-navigation -->
                <ul id="site-header-cart" class="site-header-cart menu">
                <li class="">
                			<a class="cart-contents" href="https://apteka.alteia.ua/cart/" title="Переглянути кошик">
                								<span class="woocommerce-Price-amount amount">0.00<span class="woocommerce-Price-currencySymbol">₴</span></span> <span class="count">0 товарів</span>
                			</a>
                		
                </li>
                <li>
                <div class="widget woocommerce widget_shopping_cart"><div class="widget_shopping_cart_content">
                            
                	<p class="woocommerce-mini-cart__empty-message">Немає товарів у кошику.</p>
                            
                            
                </div></div>	</li>
                </ul>
                </div></div>
                </header><!-- #masthead -->
                <div class="storefront-breadcrumb"><div class="col-full"><nav class="woocommerce-breadcrumb"><a href="/">Головна</a><span class="breadcrumb-separator"> / </span>Товари</nav></div></div>
                <div id="content" class="site-content" tabindex="-1">
                <div class="col-full">
                <div class="woocommerce"></div>	<div id="primary" class="content-area">
                <main id="main" class="site-main" role="main">
                <header class="woocommerce-products-header">
                <h1 class="woocommerce-products-header__title page-title">Товари</h1>
                <div class="page-description"><div class="aws-container" data-url="/?wc-ajax=aws_action" data-siteurl="https://apteka.alteia.ua" data-lang="" data-show-loader="true" data-show-more="true" data-show-page="true" data-show-clear="true" data-mobile-screen="false" data-use-analytics="false" data-min-chars="1" data-buttons-order="2" data-is-mobile="false" data-page-id="0" data-tax=""><form class="aws-search-form aws-show-clear" action="/" method="get" role="search"><div class="aws-wrapper"><input type="search" name="s" value="" class="aws-search-field" placeholder="Пошук" autocomplete="off"><input type="hidden" name="post_type" value="product"><input type="hidden" name="type_aws" value="true"><div class="aws-search-clear"><span>×</span></div><div class="aws-loader"></div></div><div class="aws-search-btn aws-form-btn"><span class="aws-search-btn_icon"><svg focusable="false" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24px"><path d="M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z"></path></svg></span></div></form></div>
                </div></header>
                <div class="storefront-sorting"><div class="woocommerce-notices-wrapper"></div><form class="woocommerce-ordering" method="get">
                <select name="orderby" class="orderby">
                <option value="popularity" selected="selected">Сортувати за популярністю</option>
                <option value="rating">Сортувати за оцінкою</option>
                <option value="date">Сортувати за останніми</option>
                <option value="price">Сортувати за ціною: від нижчої до вищої</option>
                <option value="price-desc">Сортувати за ціною: від вищої до нижчої</option>
                </select>
                <input type="hidden" name="paged" value="1">
                </form>
                <p class="woocommerce-result-count">
                Відображається один результат</p>
                <form method="post" action="" style="float: right; margin-left: 5px;" class="form-wppp-select products-per-page"><select name="ppp" onchange="this.form.submit()" class="select wppp-select"><option value="24" selected="selected">24 products per page</option><option value="48">48 products per page</option><option value="96">96 products per page</option></select></form><nav class="woocommerce-pagination">
                <ul class="page-numbers">
                <li><span aria-current="page" class="page-numbers current">1</span></li>
                <li><a class="page-numbers" href="page/2/">2</a></li>
                <li><a class="page-numbers" href="page/3/">3</a></li>
                <li><a class="page-numbers" href="page/4/">4</a></li>
                <li><span class="page-numbers dots">…</span></li>
                <li><a class="page-numbers" href="page/226/">226</a></li>
                <li><a class="page-numbers" href="page/227/">227</a></li>
                <li><a class="page-numbers" href="page/228/">228</a></li>
                <li><a class="next page-numbers" href="page/2/">→</a></li>
                </ul>
                </nav>
                </div><ul class="products columns-4">
                <li class="post-254207 product type-product status-publish product_cat-protynabriakovi-ta-inshi-preparaty-dlia-mistsevoho-zastosuvannia-pry-zakhvoriuvanniakh-porozhnyny-nosa first instock purchasable product-type-simple">
                <a href="/product/naftyzyn-kaply-0-1-10ml/" class="woocommerce-LoopProduct-link woocommerce-loop-product__link"><img src="/wp-content/plugins/woocommerce/assets/images/placeholder.png.pagespeed.ce.4Sqe18Ydo1.png" alt="Заповнювач" width="600" class="woocommerce-placeholder wp-post-image" height="600" data-pagespeed-url-hash="1549400627" onload="pagespeed.CriticalImages.checkImageForCriticality(this);"><h2 class="woocommerce-loop-product__title">Нафтизин капли 0.1% 10мл</h2>
                <span class="price"><span class="woocommerce-Price-amount amount">19.55<span class="woocommerce-Price-currencySymbol">₴</span></span></span>
                </a><a href="/shop/?add-to-cart=254207" data-quantity="1" class="button product_type_simple add_to_cart_button ajax_add_to_cart" data-product_id="254207" data-product_sku="319" aria-label="Додайте “Нафтизин капли 0.1% 10мл” до кошика" rel="nofollow">Додати у кошик</a></li>
                <li class="post-253828 product type-product status-publish has-post-thumbnail instock purchasable product-type-simple">
                <a href="/product/maska-medytsynskaia-zashchytnaia/" class="woocommerce-LoopProduct-link woocommerce-loop-product__link"><img width="225" height="225" src="/wp-content/uploads/2020/11/kk759.jpg.pagespeed.ce.f7Wh4NcZV1.jpg" class="attachment-product_thumb size-product_thumb" alt="" srcset="https://apteka.alteia.ua/wp-content/uploads/2020/11/kk759.jpg.pagespeed.ce.f7Wh4NcZV1.jpg 225w, https://apteka.alteia.ua/wp-content/uploads/2020/11/kk759-150x150.jpg.pagespeed.ce.B4yydlUUJO.jpg 150w, https://apteka.alteia.ua/wp-content/uploads/2020/11/kk759-100x100.jpg.pagespeed.ce.ApCGYslos7.jpg 100w" sizes="(max-width: 225px) 100vw, 225px" data-pagespeed-url-hash="3719022885" onload="pagespeed.CriticalImages.checkImageForCriticality(this);"><h2 class="woocommerce-loop-product__title">Маска медицинская защитная</h2>
                <span class="price"><span class="woocommerce-Price-amount amount">1.90<span class="woocommerce-Price-currencySymbol">₴</span></span></span>
                </a><a href="/shop/?add-to-cart=253828" data-quantity="1" class="button product_type_simple add_to_cart_button ajax_add_to_cart" data-product_id="253828" data-product_sku="48420" aria-label="Додайте “Маска медицинская защитная” до кошика" rel="nofollow">Додати у кошик</a></li>
                <li class="post-256268 product type-product status-publish has-post-thumbnail product_cat-antydepresanty instock purchasable product-type-simple">
                <a href="/product/fluoksetyn-tabl-p-o-0-02h-10/" class="woocommerce-LoopProduct-link woocommerce-loop-product__link"><img width="270" height="270" src="/wp-content/uploads/2019/07/f139.jpg.pagespeed.ce.--j5Qdr-_R.jpg" class="attachment-product_thumb size-product_thumb" alt="" srcset="https://apteka.alteia.ua/wp-content/uploads/2019/07/f139.jpg.pagespeed.ce.--j5Qdr-_R.jpg 270w, https://apteka.alteia.ua/wp-content/uploads/2019/07/f139-150x150.jpg.pagespeed.ce.6eMp5Mw-CI.jpg 150w, https://apteka.alteia.ua/wp-content/uploads/2019/07/f139-100x100.jpg.pagespeed.ce.4NAhqiNxnq.jpg 100w" sizes="(max-width: 270px) 100vw, 270px" data-pagespeed-url-hash="160155980" onload="pagespeed.CriticalImages.checkImageForCriticality(this);"><h2 class="woocommerce-loop-product__title">Флуоксетин табл. п/о 0.02г №10</h2>
                <span class="price"><span class="woocommerce-Price-amount amount">25.75<span class="woocommerce-Price-currencySymbol">₴</span></span></span>
                </a><a href="/shop/?add-to-cart=256268" data-quantity="1" class="button product_type_simple add_to_cart_button ajax_add_to_cart" data-product_id="256268" data-product_sku="14381" aria-label="Додайте “Флуоксетин табл. п/о 0.02г №10” до кошика" rel="nofollow">Додати у кошик</a></li>
                <li class="post-221976 product type-product status-publish has-post-thumbnail last instock purchasable product-type-simple">
                <a href="/product/shpric-arterium-2ml-2komp-1igla/" class="woocommerce-LoopProduct-link woocommerce-loop-product__link"><img width="200" height="200" src="/wp-content/uploads/2020/05/qw1055.jpg.pagespeed.ce.sO7ZePTeuY.jpg" class="attachment-product_thumb size-product_thumb" alt="" srcset="https://apteka.alteia.ua/wp-content/uploads/2020/05/qw1055.jpg.pagespeed.ce.sO7ZePTeuY.jpg 200w, https://apteka.alteia.ua/wp-content/uploads/2020/05/qw1055-150x150.jpg.pagespeed.ce.8MurZPmt2l.jpg 150w, https://apteka.alteia.ua/wp-content/uploads/2020/05/qw1055-100x100.jpg.pagespeed.ce.fb9EmuCo_-.jpg 100w" sizes="(max-width: 200px) 100vw, 200px" data-pagespeed-url-hash="4263532332" onload="pagespeed.CriticalImages.checkImageForCriticality(this);"><h2 class="woocommerce-loop-product__title">Шприц Артериум 2мл. 2комп. 1игла</h2>
                <span class="price"><span class="woocommerce-Price-amount amount">2.50<span class="woocommerce-Price-currencySymbol">₴</span></span></span>
                </a><a href="/shop/?add-to-cart=221976" data-quantity="1" class="button product_type_simple add_to_cart_button ajax_add_to_cart" data-product_id="221976" data-product_sku="48030" aria-label="Додайте “Шприц Артериум 2мл. 2комп. 1игла” до кошика" rel="nofollow">Додати у кошик</a></li>
                <li class="post-256031 product type-product status-publish has-post-thumbnail product_cat-enterosorbenty first instock purchasable product-type-simple">
                <a href="/product/uhol-aktyvyrovannyi-tabl-0-25h-10/" class="woocommerce-LoopProduct-link woocommerce-loop-product__link"><img width="240" height="199" src="/wp-content/uploads/2019/07/u3.jpg" class="attachment-product_thumb size-product_thumb" alt="" data-pagespeed-url-hash="1368234497" onload="pagespeed.CriticalImages.checkImageForCriticality(this);"><h2 class="woocommerce-loop-product__title">Уголь активированный табл. 0.25г №10</h2>
                <span class="price"><span class="woocommerce-Price-amount amount">3.55<span class="woocommerce-Price-currencySymbol">₴</span></span></span>
                </a><a href="/shop/?add-to-cart=256031" data-quantity="1" class="button product_type_simple add_to_cart_button ajax_add_to_cart" data-product_id="256031" data-product_sku="17989" aria-label="Додайте “Уголь активированный табл. 0.25г №10” до кошика" rel="nofollow">Додати у кошик</a></li>
                <li class="post-256990 product type-product status-publish has-post-thumbnail product_cat-vitaminy-prosti-preparaty-askorbinovoi-kysloty-vitaminu-s-vitamin-s-askorbinova-kyslota-nalezhy instock purchasable product-type-simple">
                <a href="/product/vytamyn-s-apelsyn-tabl-500mh-10/" class="woocommerce-LoopProduct-link woocommerce-loop-product__link"><img width="300" height="300" src="/wp-content/uploads/2019/07/v135-300x300.jpg" class="attachment-product_thumb size-product_thumb" alt="" srcset="https://apteka.alteia.ua/wp-content/uploads/2019/07/v135.jpg 300w, https://apteka.alteia.ua/wp-content/uploads/2019/07/v135-150x150.jpg 150w, https://apteka.alteia.ua/wp-content/uploads/2019/07/v135-100x100.jpg 100w" sizes="(max-width: 300px) 100vw, 300px" data-pagespeed-url-hash="1333478405" onload="pagespeed.CriticalImages.checkImageForCriticality(this);"><h2 class="woocommerce-loop-product__title">Витамин С апельсин табл. 500мг №10</h2>
                <span class="price"><span class="woocommerce-Price-amount amount">13.65<span class="woocommerce-Price-currencySymbol">₴</span></span></span>
                </a><a href="/shop/?add-to-cart=256990" data-quantity="1" class="button product_type_simple add_to_cart_button ajax_add_to_cart" data-product_id="256990" data-product_sku="34713" aria-label="Додайте “Витамин С апельсин табл. 500мг №10” до кошика" rel="nofollow">Додати у кошик</a></li>
                <li class="post-257292 product type-product status-publish has-post-thumbnail product_cat-zasoby-shcho-zastosovuiutsia-pry-erektylnii-dysfunktsii-syldenafil instock purchasable product-type-simple">
                <a href="/product/vyahra-tabl-p-o-100mh-4/" class="woocommerce-LoopProduct-link woocommerce-loop-product__link"><img width="300" height="259" src="/wp-content/uploads/2019/07/v98.jpg" class="attachment-product_thumb size-product_thumb" alt="" srcset="https://apteka.alteia.ua/wp-content/uploads/2019/07/v98.jpg 463w, https://apteka.alteia.ua/wp-content/uploads/2019/07/v98-416x359.jpg 416w, https://apteka.alteia.ua/wp-content/uploads/2019/07/v98-300x259.jpg 300w" sizes="(max-width: 300px) 100vw, 300px" data-pagespeed-url-hash="4240412848" onload="pagespeed.CriticalImages.checkImageForCriticality(this);"><h2 class="woocommerce-loop-product__title">Виагра табл. п/о 100мг №4</h2>
                <span class="price"><span class="woocommerce-Price-amount amount">894.80<span class="woocommerce-Price-currencySymbol">₴</span></span></span>
                </a><a href="/shop/?add-to-cart=257292" data-quantity="1" class="button product_type_simple add_to_cart_button ajax_add_to_cart" data-product_id="257292" data-product_sku="3388" aria-label="Додайте “Виагра табл. п/о 100мг №4” до кошика" rel="nofollow">Додати у кошик</a></li>
                <li class="post-256645 product type-product status-publish has-post-thumbnail last instock purchasable product-type-simple">
                <a href="/product/shpryts-3-kh-kompon-2-ml-s-yhloi-23-g/" class="woocommerce-LoopProduct-link woocommerce-loop-product__link"><img width="200" height="200" src="/wp-content/uploads/2020/11/kk691.jpg" class="attachment-product_thumb size-product_thumb" alt="" srcset="https://apteka.alteia.ua/wp-content/uploads/2020/11/kk691.jpg 200w, https://apteka.alteia.ua/wp-content/uploads/2020/11/kk691-150x150.jpg 150w, https://apteka.alteia.ua/wp-content/uploads/2020/11/kk691-100x100.jpg 100w" sizes="(max-width: 200px) 100vw, 200px" data-pagespeed-url-hash="2325522576" onload="pagespeed.CriticalImages.checkImageForCriticality(this);"><h2 class="woocommerce-loop-product__title">Шприц 3-х компон. 2 мл с иглой 23 G</h2>
                <span class="price"><span class="woocommerce-Price-amount amount">2.95<span class="woocommerce-Price-currencySymbol">₴</span></span></span>
                </a><a href="/shop/?add-to-cart=256645" data-quantity="1" class="button product_type_simple add_to_cart_button ajax_add_to_cart" data-product_id="256645" data-product_sku="47997" aria-label="Додайте “Шприц 3-х компон. 2 мл с иглой 23 G” до кошика" rel="nofollow">Додати у кошик</a></li>
                <li class="post-252290 product type-product status-publish has-post-thumbnail product_cat-antyhelmintni-zasoby-zasoby-shcho-zastosovuiutsia-pry-nematodozakh-pokhidni-benzymidazolu first instock purchasable product-type-simple">
                <a href="/product/vormyl-tabl-zhev-400mh-3/" class="woocommerce-LoopProduct-link woocommerce-loop-product__link"><img width="300" height="300" src="/wp-content/uploads/2019/07/v182.jpg" class="attachment-product_thumb size-product_thumb" alt="" srcset="https://apteka.alteia.ua/wp-content/uploads/2019/07/v182.jpg 400w, https://apteka.alteia.ua/wp-content/uploads/2019/07/v182-300x300.jpg 300w, https://apteka.alteia.ua/wp-content/uploads/2019/07/v182-150x150.jpg 150w, https://apteka.alteia.ua/wp-content/uploads/2019/07/v182-100x100.jpg 100w" sizes="(max-width: 300px) 100vw, 300px" data-pagespeed-url-hash="4003284740" onload="pagespeed.CriticalImages.checkImageForCriticality(this);"><h2 class="woocommerce-loop-product__title">Вормил табл. жев.400мг №3</h2>
                <span class="price"><span class="woocommerce-Price-amount amount">92.20<span class="woocommerce-Price-currencySymbol">₴</span></span></span>
                </a><a href="/shop/?add-to-cart=252290" data-quantity="1" class="button product_type_simple add_to_cart_button ajax_add_to_cart" data-product_id="252290" data-product_sku="7893" aria-label="Додайте “Вормил табл. жев.400мг №3” до кошика" rel="nofollow">Додати у кошик</a></li>
                <li class="post-252418 product type-product status-publish has-post-thumbnail product_cat-psykholeptychni-zasoby-anksiolityky-pokhidni-benzodiazepinu instock purchasable product-type-simple">
                <a href="/product/hydazepam-ic-tabl-0-02h-20-10x2/" class="woocommerce-LoopProduct-link woocommerce-loop-product__link"><img width="220" height="220" src="/wp-content/uploads/2019/07/g120.jpg" class="attachment-product_thumb size-product_thumb" alt="" srcset="https://apteka.alteia.ua/wp-content/uploads/2019/07/g120.jpg 220w, https://apteka.alteia.ua/wp-content/uploads/2019/07/g120-150x150.jpg 150w, https://apteka.alteia.ua/wp-content/uploads/2019/07/g120-100x100.jpg 100w" sizes="(max-width: 220px) 100vw, 220px" data-pagespeed-url-hash="3882997003" onload="pagespeed.CriticalImages.checkImageForCriticality(this);"><h2 class="woocommerce-loop-product__title">Гидазепам IC табл. 0.02г №20 (10×2)</h2>
                <span class="price"><span class="woocommerce-Price-amount amount">104.70<span class="woocommerce-Price-currencySymbol">₴</span></span></span>
                </a><a href="/shop/?add-to-cart=252418" data-quantity="1" class="button product_type_simple add_to_cart_button ajax_add_to_cart" data-product_id="252418" data-product_sku="9817" aria-label="Додайте “Гидазепам IC табл. 0.02г №20 (10x2)” до кошика" rel="nofollow">Додати у кошик</a></li>
                </body>
                </html>
                """;
    }

    @Test
    public void loadData_ok() throws Exception {
        doReturn(html).when(mockProvider, "fetchHtmlByPage", anyInt());

        List<MedicineDto> result = mockProvider.loadData().collect(Collectors.toList());

        Assertions.assertEquals(dtos, result);
    }

}
