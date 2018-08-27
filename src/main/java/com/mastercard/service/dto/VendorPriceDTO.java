package com.mastercard.service.dto;

public class VendorPriceDTO implements Comparable<VendorPriceDTO> {
	
	//Ideally dates should be ZonedDateTime. For now we consider them as int
	Integer startDate;
	Integer endDate;
	Integer price;

	
	public Integer getEndDate() {
		return endDate;
	}
	public VendorPriceDTO endDate(Integer endDate) {
		this.endDate = endDate;
		return this;
	}

	public Integer getPrice() {
		return price;
	}
	public VendorPriceDTO price(Integer price) {
		this.price = price;
		return this;
	}
	public Integer getStartDate() {
		return startDate;
	}
	public VendorPriceDTO startDate(Integer startDate) {
		this.startDate = startDate;
		return this;
	}
	@Override
	public int compareTo(VendorPriceDTO vp) {
		return Integer.compare(this.endDate,vp.endDate);
		
	} 


}
