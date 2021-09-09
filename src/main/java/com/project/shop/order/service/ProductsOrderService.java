package com.project.shop.order.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.shop.order.dto.ProductsOrderedDTO;
import com.project.shop.order.entity.ProductsOrder;
import com.project.shop.order.repository.ProductsOrderRepo;
@Service
@Transactional
public class ProductsOrderService {
	
			Logger logger = LoggerFactory.getLogger(this.getClass());

		@Autowired
		ProductsOrderRepo orderproRepo;	
	
	public List<ProductsOrderedDTO> getProductById(String prodid) {
		logger.info("Productname request for product {}", prodid);
		List<ProductsOrder> proord = orderproRepo.findByProdid(prodid);
		List<ProductsOrderedDTO> proorderDTO = new ArrayList<ProductsOrderedDTO>();
		for (ProductsOrder pord : proord) {
			proorderDTO.add(ProductsOrderedDTO.valueOf(pord));
		}
		logger.info("Productname for product : {}", proord);
		return proorderDTO;
	}
}
