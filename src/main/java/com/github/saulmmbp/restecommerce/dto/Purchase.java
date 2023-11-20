package com.github.saulmmbp.restecommerce.dto;

import com.github.saulmmbp.restecommerce.entity.Address;
import com.github.saulmmbp.restecommerce.entity.Customer;
import com.github.saulmmbp.restecommerce.entity.Order;
import com.github.saulmmbp.restecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;

}
