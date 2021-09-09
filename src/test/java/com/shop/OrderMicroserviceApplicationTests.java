package com.shop;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.shop.order.OrderApplication;
import com.project.shop.order.dto.OrderDTO;
import com.project.shop.order.entity.Order;
import com.project.shop.order.repository.OrderRepository;
import com.project.shop.order.repository.ProductsOrderRepo;
import com.project.shop.order.service.OrderService;
import com.project.shop.order.service.ProductsOrderService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= OrderApplication.class)

public class OrderMicroserviceApplicationTests {

//	@Test
//	public void contextLoads() {
//	}
	@Mock
	OrderRepository orderRepo;
	
	@InjectMocks
	OrderService orderService =  new OrderService();

	@Mock
	ProductsOrderRepo productsOrderRepo;
	
	@InjectMocks
	ProductsOrderService productsOrderService =  new ProductsOrderService();
	
/*	@Test
	public void OrderTestValid() throws InfyShopException {
		OrderDTO order = new OrderDTO();
		order.setOrderid("monica");
		order.setBuyerid("monica123");
		Mockito.when(orderRepo.findByOrderid("monica")).thenReturn(order);
		String actual = orderService.order(order);
		Assertions.assertEquals("SUCCESS", actual);
	} 

	@Test
	public void authenticateCustomerTestInValidCredentials() throws InfyBankException {
		CustomerLoginDTO customer = new CustomerLoginDTO();
		customer.setLoginName("monica");
		customer.setPassword("monica12");
		
		CustomerLoginDTO fromRepository = new CustomerLoginDTO();
		fromRepository.setLoginName("monica");
		fromRepository.setPassword("monica123");
		
		Mockito.when(customerLoginRepository.getCustomerLoginByLoginName("monica")).thenReturn(fromRepository);
		InfyBankException exception = Assertions.assertThrows(InfyBankException.class, () -> customerLoginService.authenticateCustomer(customer));
		Assertions.assertEquals("Service.WRONG_CREDENTIALS", exception.getMessage());
	}
	
*/
	@Test
	public void orderValidTest() throws ShopException {
	List<Order> orderList=new ArrayList<Order>();
    
    Order orderEntity = new Order();
    //Order orderEntity1 = new Date();
    orderEntity.setOrderid("1");
    orderEntity.setBuyerid("B101");
    orderEntity.setAmount(100.0);
    orderEntity.setOrderdate(new Date (2020-9-12));
    orderEntity.setAddress("KUDT");
    orderEntity.setStatus("ORDER PLACED");
    
    orderList.add(orderEntity);
    
    Mockito.when(orderRepo.findAll()).thenReturn(orderList);
    
    List<OrderDTO> reorders= orderService.getAllOrders();
    System.out.println(reorders.get(0));
    Assertions.assertEquals(reorders.isEmpty(), orderList.isEmpty());


   }
	
	@Test
	public void orderInvalidTest() throws ShopException {
	List<Order> orderList=new ArrayList<Order>();
    
    Order orderEntity = new Order();
    
    orderEntity.setOrderid("1");
    orderEntity.setBuyerid("B101");
    orderEntity.setAmount(100.0);
    orderEntity.setOrderdate(new Date (2020-9-12));
    orderEntity.setAddress("KUDT");
    orderEntity.setStatus("ORDER PLACED");
    
    Optional case1 = Optional.of(orderEntity);  // Valid
    
    Optional case2 = Optional.empty();  // Invalid
    
    Mockito.when(orderRepo.findById(Mockito.anyString())).thenReturn(case1);
    
    List<OrderDTO> reorders= orderService.getAllOrders();
    //System.out.println(reorders.get(0));
    Assertions.assertEquals(reorders.isEmpty(), orderList.isEmpty());


   }
	
	
}
