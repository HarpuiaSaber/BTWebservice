package webservice.BHXH.service;

import com.paypal.api.payments.*;
import com.paypal.base.rest.PayPalRESTException;
import webservice.BHXH.enums.PaypalPaymentIntent;
import webservice.BHXH.enums.PaypalPaymentMethod;


public interface PaypalService {

    Payment createPayment(
            float total,
            String currency,
            PaypalPaymentMethod method,
            PaypalPaymentIntent intent,
            String description,
            String cancelUrl,
            String successUrl) throws PayPalRESTException;

    Payment executePayment(String paymentId, String payerId) throws PayPalRESTException;

}
