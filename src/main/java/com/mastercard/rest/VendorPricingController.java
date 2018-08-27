package com.mastercard.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mastercard.service.dto.VendorPriceDTO;
import com.mastercard.service.dto.VendorPricingService;

import io.swagger.annotations.*;

@RestController
@RequestMapping("")
@Api("Registration")
public class VendorPricingController {

	@Autowired VendorPricingService vendorPricingService;

	@ApiResponses(value={

			@ApiResponse(code = 200, message = "Non-conflicting schedule of best prices. " +

					"Could include data such as 'start date', 'end date', 'price', etc"),

			@ApiResponse(code = 400, message = "The request received contains improper information"),

			@ApiResponse(code = 404, message = "No data found")

	})
	@GetMapping("/VendorBestPrices")
	public ResponseEntity<List<VendorPriceDTO>> getVendorBestPrices() {

		return new ResponseEntity<List<VendorPriceDTO>>(vendorPricingService.getbestVendorPrice(), HttpStatus.OK);

	}


}
