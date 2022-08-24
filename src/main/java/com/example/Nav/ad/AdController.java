package com.example.Nav.ad;

import com.example.Nav.models.AdsPerWeek;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class AdController {

	private final AdService adService;


	@Autowired
	public AdController(AdService adService) {
		this.adService = adService;
	}

	@GetMapping("api/v1/getAdsByWeek")
	public List<AdsPerWeek> getAdsSortedByWeek() {
		return adService.getAdsJson();
	}

}
