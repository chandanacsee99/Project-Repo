package com.project.shop.order.service;



import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.shop.order.dto.OrderDTO;
import com.project.shop.order.entity.Order;
import com.project.shop.order.repository.OrderRepository;
import com.project.shop.order.repository.ReorderRepository;


@Service
@Transactional
public class OrderService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	OrderRepository orderrepo;
	@Autowired
	ReorderRepository reorderRepo;
	
	public List<OrderDTO> getSpecificOrder(String orderid) {
		logger.info("Productname request for product {}", orderid);
		List<Order> order = orderrepo.findByOrderid(orderid);
		List<OrderDTO> orderDTO = new ArrayList<OrderDTO>();
		for (Order ord : order) {
			orderDTO.add(OrderDTO.valueOf(ord));
		}
		logger.info("Productname for product : {}", order);
		return orderDTO;
	}
	public List<OrderDTO> getAllOrders() {

		List<Order> orders = orderrepo.findAll();
		List<OrderDTO> orderDTOs = new ArrayList<>();

		for (Order order : orders) {
			OrderDTO orderDTO = OrderDTO.valueOf(order);
			orderDTOs.add(orderDTO);
		}

		logger.info("Product Details : {}", orderDTOs);
		return orderDTOs;
	}
	public String saveBuyer(OrderDTO orderDTO)  {
		Order order = orderrepo.getOrderByBuyeridAndAddress(orderDTO.getBuyerid(),orderDTO.getAddress());
		if(order!=null) {
			return "order placed successfully";
	}
		return "order didn't placed";
	}
	public boolean Order(OrderDTO orderDTO) {
		logger.info("Login request for customer {} with password {}", orderDTO.getOrderid());
		Order ord = reorderRepo.findByOrderid(orderDTO.getOrderid());
		if (ord != null && ord.getOrderid().equals(orderDTO.getOrderid())) {
			return true;
		}
		return false;
	}

}