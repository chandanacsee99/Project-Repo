package com.project.shop.order.controller;

import java.util.List;
import java.lang.String;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.shop.order.dto.OrderDTO;
import com.project.shop.order.dto.ProductsOrderedDTO;
import com.project.shop.order.service.OrderService;
import com.project.shop.order.service.ProductsOrderService;

@RestController
@CrossOrigin
public class OrderController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
@Autowired
Environment environment;
@Autowired
private OrderService orderservice;
@Autowired DiscoveryClient client;
@Autowired
ProductsOrderService productService;



@RequestMapping(value = "/api/orders/{orderid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
public List<OrderDTO> getSpecificOrder(@PathVariable String orderid) {
	logger.info("productname request for product {}", orderid);
	return orderservice.getSpecificOrder(orderid);
}
	@GetMapping(value = "/api/orders", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<OrderDTO> getAllOrder() {
		logger.info("Fetching all products");
		return orderservice.getAllOrders();
	}
	@RequestMapping(value = "/orders/placeorders", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String searchBuyer(@RequestBody OrderDTO buyerDTO) {
		logger.info("ORDER PLACING", buyerDTO);
		return orderservice.saveBuyer(buyerDTO);
	}
	@PostMapping(value = "/orders/reorder/{orderid}/{orderid}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean login(@RequestBody OrderDTO orderDTO) {
		logger.info("Login request for customer {} with password {}", orderDTO.getOrderid());
		return orderservice.Order(orderDTO);
	}
	@RequestMapping(value = "/api/productsorders/{prodid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductsOrderedDTO> getProductById(@PathVariable String prodid) {
		logger.info("productname request for product {}", prodid);
		return productService.getProductById(prodid);
	}
	

}
