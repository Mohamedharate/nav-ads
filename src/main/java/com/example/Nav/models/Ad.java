package com.example.Nav.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Ad implements Serializable {
	private String uuid;
	private LocalDateTime published;
	private LocalDateTime updated;
	private LocalDateTime expires;
	private Location[] workLocations;
	private String title;
	private String jobTitle;
	private String description;
	private String sourceurl;
	private String source;
	private String applicationDue;
	private String applicationUrl;
	private Occupation[] occupationCategories;
	private String link;
	private String engagementtype;
	private String extent;
	private String starttime;
	private String positioncount;
	private String sector;
	private Employer employer;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public LocalDateTime getPublished() {
		return published;
	}

	public void setPublished(LocalDateTime published) {
		this.published = published;
	}

	public LocalDateTime getUpdated() {
		return updated;
	}

	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}

	public LocalDateTime getExpires() {
		return expires;
	}

	public void setExpires(LocalDateTime expires) {
		this.expires = expires;
	}

	public Location[] getWorkLocations() {
		return workLocations;
	}

	public void setWorkLocations(Location[] workLocations) {
		this.workLocations = workLocations;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSourceurl() {
		return sourceurl;
	}

	public void setSourceurl(String sourceurl) {
		this.sourceurl = sourceurl;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getApplicationDue() {
		return applicationDue;
	}

	public void setApplicationDue(String applicationDue) {
		this.applicationDue = applicationDue;
	}

	public String getApplicationUrl() {
		return applicationUrl;
	}

	public void setApplicationUrl(String applicationUrl) {
		this.applicationUrl = applicationUrl;
	}

	public Occupation[] getOccupationCategories() {
		return occupationCategories;
	}

	public void setOccupationCategories(Occupation[] occupationCategories) {
		this.occupationCategories = occupationCategories;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getEngagementtype() {
		return engagementtype;
	}

	public void setEngagementtype(String engagementtype) {
		this.engagementtype = engagementtype;
	}

	public String getExtent() {
		return extent;
	}

	public void setExtent(String extent) {
		this.extent = extent;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getPositioncount() {
		return positioncount;
	}

	public void setPositioncount(String positioncount) {
		this.positioncount = positioncount;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public Employer getEmployer() {
		return employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

	public boolean adContainsString(String s) {

		return getDescription().toLowerCase().contains(s.toLowerCase())
				|| getTitle().toLowerCase().contains(s.toLowerCase());
	}


	@Override
	public String toString() {
		return "Ad{" +
				"uuid='" + uuid + '\'' +
				", published=" + published +
				", updated=" + updated +
				", expires=" + expires +
				", workLocations=" + Arrays.toString(workLocations) +
				", title='" + title + '\'' +
				", jobTitle='" + jobTitle + '\'' +
				", description='" + description + '\'' +
				", sourceurl='" + sourceurl + '\'' +
				", source='" + source + '\'' +
				", applicationDue='" + applicationDue + '\'' +
				", applicationUrl='" + applicationUrl + '\'' +
				", occupationCategories=" + Arrays.toString(occupationCategories) +
				", link='" + link + '\'' +
				", engagementtype='" + engagementtype + '\'' +
				", extent='" + extent + '\'' +
				", starttime='" + starttime + '\'' +
				", positioncount='" + positioncount + '\'' +
				", sector='" + sector + '\'' +
				", employer=" + employer +
				'}';
	}
}

