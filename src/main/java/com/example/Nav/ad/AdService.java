package com.example.Nav.ad;

import com.example.Nav.models.Ad;
import com.example.Nav.models.Ads;
import com.example.Nav.models.AdsPerWeek;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdService {

	private final RestTemplate restTemplate;

	public AdService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	public List<AdsPerWeek> getAdsJson() {

		LocalDateTime now = LocalDateTime.now();
		LocalDateTime then = now.minusMonths(6);

		now = now.getDayOfWeek().getValue() != 1 ? now.minusDays(now.getDayOfWeek().getValue() - 1) : now;
		then = then.getDayOfWeek().getValue() != 1 ? then.minusDays(then.getDayOfWeek().getValue() - 1) : then;

		String uri = "https://arbeidsplassen.nav.no/public-feed/api/v1/ads";

		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwdWJsaWMudG9rZW4u" +
				"djFAbmF2Lm5vIiwiYXVkIjoiZmVlZC1hcGktdjEiLCJpc3MiOiJuYXYubm8iLCJpYXQiOjE1NTc0NzM0MjJ9.jNGlL" +
				"UF9HxoHo5JrQNMkweLj_91bgk97ZebLdfx3_UQ");

		HttpEntity<String> request = new HttpEntity<>(headers);

		ResponseEntity<Ads> firstPage = this.restTemplate.exchange(generateUriWithParams(uri, then, 0,
				"IT", 100), HttpMethod.GET, request, Ads.class);

		ArrayList<Ad> ads = new ArrayList<>(Objects.requireNonNull(firstPage.getBody()).getContent());

		Integer totalPages = firstPage.getBody().getTotalPages();

		for (int i = 1; i <= totalPages; i++) {
			ResponseEntity<Ads> res = this.restTemplate.exchange(generateUriWithParams(uri, then, i, "IT",
					100), HttpMethod.GET, request, Ads.class);
			ads.addAll(Objects.requireNonNull(res.getBody()).getContent());
		}

		List<Ad> adListFiltered = ads.stream().filter(ad -> ad.adContainsString("java")
				|| ad.adContainsString("kotlin")).toList();

		if (firstPage.getStatusCode() == HttpStatus.OK) {
			return countAdsPerWeek(adListFiltered, then, now);
		} else {
			return null;
		}
	}

	/*
	  Generates the uri used to fetch the ads.
   */
	public String generateUriWithParams(String uri, LocalDateTime fromDate, int pageNumber, String category, int pageSize) {

		return UriComponentsBuilder.fromHttpUrl(uri)
				.queryParam("published", "[" + fromDate + ",*)")
				.queryParam("size", pageSize)
				.queryParam("category", category)
				.queryParam("page", pageNumber)
				.build().toUriString();
	}

	/*
		Generates a HashMap with adsPerWeek objects. The objects have key = year-weekNumber.
	 */
	public HashMap<String, AdsPerWeek> generateAdsPerWeekMap(LocalDateTime then, LocalDateTime now) {

		HashMap<String, AdsPerWeek> adsPerWeekHashMap = new HashMap<>();

		LocalDateTime helperDate = then;

		while (true) {

			if ((helperDate.isBefore(now) || helperDate.isEqual(now))) {
				AdsPerWeek adsPerWeek = new AdsPerWeek();
				adsPerWeek.setYear(helperDate.getYear());
				adsPerWeek.setWeekNumber(helperDate.get(ChronoField.ALIGNED_WEEK_OF_YEAR));
				adsPerWeekHashMap.put(adsPerWeek.getYear() + "-" + adsPerWeek.getWeekNumber(), adsPerWeek);
			} else {
				break;
			}
			helperDate = helperDate.plusWeeks(1);
		}
		return adsPerWeekHashMap;
	}

	/*
	   Counts the ads per week and returns an arrayList sorted by year and weekNumber.
	*/
	public List<AdsPerWeek> countAdsPerWeek(List<Ad> ads, LocalDateTime then, LocalDateTime now) {

		HashMap<String, AdsPerWeek> adsPerWeekHashMap = generateAdsPerWeekMap(then, now);

		for (Ad ad : ads) {

			AdsPerWeek adsPerWeek = adsPerWeekHashMap.get(ad.getPublished().getYear() + "-" +
					ad.getPublished().get(ChronoField.ALIGNED_WEEK_OF_YEAR));

			if (ad.adContainsString("java") && ad.adContainsString("kotlin")) {
				adsPerWeek.setAdsQuantityJava(adsPerWeek.getAdsQuantityJava() + 1);
				adsPerWeek.setAdsQuantityKotlin(adsPerWeek.getAdsQuantityKotlin() + 1);
			} else if (ad.adContainsString("java")) {
				adsPerWeek.setAdsQuantityJava(adsPerWeek.getAdsQuantityJava() + 1);
			} else {
				adsPerWeek.setAdsQuantityKotlin(adsPerWeek.getAdsQuantityKotlin() + 1);
			}
		}
		return new ArrayList<>(adsPerWeekHashMap.values()).stream()
				.sorted(Comparator
						.comparingInt(AdsPerWeek::getYear)
						.thenComparingInt(AdsPerWeek::getWeekNumber))
				.collect(Collectors.toList());
	}

}
