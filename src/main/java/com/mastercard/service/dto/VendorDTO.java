package com.mastercard.service.dto;

public class VendorDTO {

	String vendorName;
	VendorPriceDTO vendorPriceDto;
	
	public String getVendorName() {
		return vendorName;
	}
	public VendorDTO vendorName(String vendorName) {
		this.vendorName = vendorName;
		return this;
	}
	public VendorPriceDTO getVendorPriceDto() {
		return vendorPriceDto;
	}
	public VendorDTO vendorPriceDto(VendorPriceDTO vendorPriceDto) {
		this.vendorPriceDto = vendorPriceDto;
		return this;
	}


}
