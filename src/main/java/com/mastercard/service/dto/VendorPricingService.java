package com.mastercard.service.dto;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.mastercard.VendorPricingApplication;

@Service
public class VendorPricingService {
	final static Logger log = Logger.getLogger(VendorPricingService.class);

	public List<VendorPriceDTO> getbestVendorPrice() {
		List<VendorPriceDTO> vendorPriceDtos = VendorPricingApplication.vendorDtos.stream().map(v->v.vendorPriceDto).collect(Collectors.toList());
		return getBestCombination(vendorPriceDtos);
	}


	/*
	 * Verifies if there two objects having overlapping schedule
	 */
	boolean isOverlap(VendorPriceDTO vp1, VendorPriceDTO vp2) {
		if (vp1.startDate < vp2.startDate) {
			return vp1.endDate >= vp2.startDate;
		}
		if (vp1.startDate> vp2.startDate) {
			return vp1.endDate <= vp2.endDate;
		}
		if (vp1.startDate==vp2.startDate||vp1.endDate==vp2.endDate) {
			return true;
		}		
		return false;		

	}





	private List<VendorPriceDTO> getBestCombination(List<VendorPriceDTO> vendorPriceDtos) {

		Collections.sort(vendorPriceDtos, new Comparator<VendorPriceDTO>() {
			@Override
			public int compare(VendorPriceDTO o1, VendorPriceDTO o2) {
				return o1.getStartDate().compareTo(o2.getStartDate());
			}
		});


		for(int i=0;i<vendorPriceDtos.size()-1;++i) {
			VendorPriceDTO previous=vendorPriceDtos.get(i);
			VendorPriceDTO current = vendorPriceDtos.get(i+1);
			if(isOverlap(previous, current)) {

				log.debug("---------------------------------------");
				vendorPriceDtos.stream().forEach(vp->log.debug("current - startDate:"+vp.getStartDate()+" EndDate:"+vp.getEndDate()+" price:"+vp.getPrice()));

				if(current.getStartDate()<previous.getEndDate() && current.startDate!=previous.startDate){
					vendorPriceDtos.add(new VendorPriceDTO().startDate(previous.startDate).endDate(current.startDate).price(getbestPrice(previous,current)));
					vendorPriceDtos.add(new VendorPriceDTO().startDate(current.startDate+1).endDate(Math.max(previous.endDate, current.endDate)).price(getbestPriceonMaxDates(previous,current)));

				}
				else if(current.getStartDate()<previous.getEndDate() && current.startDate==previous.startDate)
				{
					vendorPriceDtos.add(new VendorPriceDTO().startDate(previous.startDate).endDate(Math.min(previous.endDate, current.endDate)).price(getbestPrice(previous,current)));
					vendorPriceDtos.add(new VendorPriceDTO().startDate(Math.min(previous.endDate, current.endDate)+1).endDate(Math.max(previous.endDate, current.endDate)).price(getbestPriceonMaxDates(previous,current)));
				}
				else if(current.getStartDate()==previous.getEndDate()||current.getEndDate()==previous.getStartDate())
				{
					if(current.price==previous.price) {
						vendorPriceDtos.add(new VendorPriceDTO().startDate(previous.startDate).endDate(Math.max(previous.endDate, current.endDate)).price(getbestPriceonMaxDates(previous,current)));
					} else {
						vendorPriceDtos.add(new VendorPriceDTO().startDate(previous.startDate).endDate(Math.min(previous.endDate, current.endDate)).price(getbestPriceoMinnDates(previous,current)));
						vendorPriceDtos.add(new VendorPriceDTO().startDate(Math.min(previous.endDate, current.endDate)+1).endDate(Math.max(previous.endDate, current.endDate)).price(getbestPriceonMaxDates(previous,current)));

					}

				}

				vendorPriceDtos.remove(current);
				vendorPriceDtos.remove(previous);

				getBestCombination(vendorPriceDtos);

			}


		}
		
		log.debug("---------------------------------------");
		vendorPriceDtos.stream().forEach(vp->log.debug("current - startDate:"+vp.getStartDate()+" EndDate:"+vp.getEndDate()+" price:"+vp.getPrice()));

		return vendorPriceDtos;
	}
	
	
	private Integer getbestPrice(VendorPriceDTO vp1, VendorPriceDTO vp2) {

		return vp2.getPrice()<vp1.getPrice()?vp2.getPrice():vp1.getPrice();
	}

	private Integer getbestPriceonMaxDates(VendorPriceDTO vp1, VendorPriceDTO vp2) {

		return vp1.getEndDate()>vp2.getEndDate()?vp1.getPrice():vp2.getPrice();
	}

	private Integer getbestPriceoMinnDates(VendorPriceDTO vp1, VendorPriceDTO vp2) {

		return vp1.getEndDate()<vp2.getEndDate()?vp1.getPrice():vp2.getPrice();
	}

}
