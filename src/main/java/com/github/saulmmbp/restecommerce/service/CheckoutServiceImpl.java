package com.github.saulmmbp.restecommerce.service;

import com.github.saulmmbp.restecommerce.dto.Purchase;
import com.github.saulmmbp.restecommerce.dto.PurchaseResponse;
import com.github.saulmmbp.restecommerce.entity.Customer;
import com.github.saulmmbp.restecommerce.entity.Order;
import com.github.saulmmbp.restecommerce.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private CustomerRepository customerRepository;

    public CheckoutServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        Order order = purchase.getOrder();

        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        purchase.getOrderItems().forEach(order::add);

        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        Customer customer = purchase.getCustomer();
        String email = customer.getEmail();
        Optional<Customer> customerDB = customerRepository.findByEmail(email);
        if(customerDB.isPresent()) customer = customerDB.get();
        customer.add(order);

        customerRepository.save(customer);

        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }

}
