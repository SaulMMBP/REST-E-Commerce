package com.github.saulmmbp.restecommerce.service;

import com.github.saulmmbp.restecommerce.dto.Purchase;
import com.github.saulmmbp.restecommerce.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);

}
