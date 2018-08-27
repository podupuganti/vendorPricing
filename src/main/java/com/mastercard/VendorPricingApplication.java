package com.mastercard;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mastercard.service.dto.VendorDTO;
import com.mastercard.service.dto.VendorPriceDTO;

@SpringBootApplication
public class VendorPricingApplication {

	public static void main(String[] args) {
		SpringApplication.run(VendorPricingApplication.class, args);
	}

	public static List<VendorDTO> vendorDtos;
	static {

		vendorDtos = new ArrayList<VendorDTO>();		
		vendorDtos.add(new VendorDTO().vendorName("NewApp").vendorPriceDto(new VendorPriceDTO().startDate(1).endDate(5).price(20000)));
		vendorDtos.add(new VendorDTO().vendorName("BlueSoft").vendorPriceDto(new VendorPriceDTO().startDate(3).endDate(6).price(15000)));
		vendorDtos.add(new VendorDTO().vendorName("AvidTech").vendorPriceDto(new VendorPriceDTO().startDate(2).endDate(8).price(25000)));
		vendorDtos.add(new VendorDTO().vendorName("DataPro").vendorPriceDto(new VendorPriceDTO().startDate(7).endDate(12).price(11000)));
		vendorDtos.add(new VendorDTO().vendorName("SureFind").vendorPriceDto(new VendorPriceDTO().startDate(1).endDate(31).price(22000)));
	}
}
