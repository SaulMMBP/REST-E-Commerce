package com.github.saulmmbp.restecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = true)
@Data
public class PurchaseResponse extends RepresentationModel<PurchaseResponse> {

    private final String orderTrackingNumber;

}
