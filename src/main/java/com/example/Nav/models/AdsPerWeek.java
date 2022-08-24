package com.example.Nav.models;

import java.util.Objects;

public class AdsPerWeek {

	int year;
	Integer weekNumber;
	Integer adsQuantityJava;
	Integer adsQuantityKotlin;

	public AdsPerWeek() {
		this.adsQuantityJava = 0;
		this.adsQuantityKotlin = 0;
		this.weekNumber = 0;
		this.year = 0;
	}


	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Integer getWeekNumber() {
		return weekNumber;
	}

	public void setWeekNumber(Integer weekNumber) {
		this.weekNumber = weekNumber;
	}

	public Integer getAdsQuantityJava() {
		return adsQuantityJava;
	}

	public void setAdsQuantityJava(Integer adsQuantityJava) {
		this.adsQuantityJava = adsQuantityJava;
	}

	public Integer getAdsQuantityKotlin() {
		return adsQuantityKotlin;
	}

	public void setAdsQuantityKotlin(Integer adsQuantityKotlin) {
		this.adsQuantityKotlin = adsQuantityKotlin;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof AdsPerWeek that)) return false;
		return year == that.year && weekNumber.equals(that.weekNumber) && Objects.equals(adsQuantityJava, that.adsQuantityJava) && Objects.equals(adsQuantityKotlin, that.adsQuantityKotlin);
	}

	@Override
	public int hashCode() {
		return Objects.hash(year, weekNumber);
	}
}
