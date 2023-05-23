package com.example.ecommercebackend.Entities.Payment;

import com.example.ecommercebackend.Entities.Cart.Cart;
import com.example.ecommercebackend.Entities.Cart.CartRepository;
import com.example.ecommercebackend.Entities.Cart.CartService;
import com.example.ecommercebackend.Entities.CartLine.CartLineService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.net.ApiResource;
import com.stripe.net.Webhook;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.PaymentIntentCreateParams;
import net.minidev.json.JSONObject;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

@RestController
public class CheckoutController {

    @Autowired
    CartService cartService;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartLineService cartLineService;

    //    @Value("${STRIPE_PUBLIC_KEY}")
//    private String stripePublicKey;
    static class CreatePaymentResponse {
        private String clientSecret;

        public CreatePaymentResponse(String clientSecret) {
            this.clientSecret = clientSecret;
        }
    }

    @PostMapping("/checkout")
    public String CheckoutTest(@RequestBody Cart cart, @RequestParam Integer cart_id) throws StripeException {
        Stripe.apiKey = "sk_test_51NAPhSDNDkGZR7LmgrgVxe72tZOA9NLrnoDSIIuXO1yeWF0rAPdgvppwRJC8IMka19ZcL1k56Tnjqmq9cYghbyhw00EShaFrD1";
        CustomerCreateParams customerParams = new CustomerCreateParams.Builder().build();
        Customer customer = Customer.create(customerParams);
//        customer.setId("2");
//        System.out.println(customer.getId());
//        CreatePayment postBody = gson.fromJson(request.body(), CreatePayment.class);
        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setCustomer(customer.getId())
                        .setSetupFutureUsage(PaymentIntentCreateParams.SetupFutureUsage.OFF_SESSION)
                        .setAmount((long) cartLineService.getCartTotalPriceByCardID(cart_id)*100)
                        .setCurrency("usd")
                        .putMetadata("cart_id", cart_id.toString())
                        .putMetadata("address", cart.getAddress())
                        .putMetadata("phoneNumber", String.valueOf(cart.getPhoneNumber()))
                        .setAutomaticPaymentMethods(
                                PaymentIntentCreateParams.AutomaticPaymentMethods
                                        .builder()
                                        .setEnabled(true)
                                        .build()
                        )
                        .build();

        // Create a PaymentIntent with the order amount and currency
        PaymentIntent paymentIntent = PaymentIntent.create(params);
        CreatePaymentResponse paymentResponse = new CreatePaymentResponse(paymentIntent.getClientSecret());
        return paymentResponse.clientSecret;
    }

    @RequestMapping("/checkout_success")
    public ResponseEntity<String> webhook(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader) throws JsonProcessingException {
        Event event = null;
        try {
            event = Webhook.constructEvent(payload, sigHeader, "whsec_c1bd3bd882a2c2ec215a5f98531547359c9620650e116c1c6ca55c6210e6d2e8");
        } catch (SignatureVerificationException e) {
            System.out.println("Failed signature verification");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
        StripeObject stripeObject = null;
        if (dataObjectDeserializer.getObject().isPresent()) {
            stripeObject = dataObjectDeserializer.getObject().get();
        } else {
            // Deserialization failed, probably due to an API version mismatch.
            // Refer to the Javadoc documentation on `EventDataObjectDeserializer` for
            // instructions on how to handle this case, or return an error here.
        }
        Date date = new Date();
//        JSONParser parser=new JSONParser(stripeObject.toJson());
        switch (event.getType()) {
            case "payment_intent.succeeded":
//                System.out.println(props.get("cart_id"));
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonNode = mapper.readTree(stripeObject.toJson());
                JsonNode metaDataNode = mapper.readTree(jsonNode.get("metadata").toString());
//                System.out.println(jsonNode.get("cart_id"));
                ObjectNode objectNode = (ObjectNode) jsonNode.get("metadata");
                int cart_id = objectNode.get("cart_id").asInt();
                Cart cart = cartService.getCartByCartID(cart_id);
                cart.setOrdered_at(new Timestamp(date.getTime()));
                cart.setCart_status(1);
                cart.setAddress(objectNode.get("address").asText());
                cart.setPhoneNumber(objectNode.get("phoneNumber").asDouble());
                cart.setHasPaid(true);
                cartRepository.save(cart);
                break;
            case "payment_method.attached":
                // ...
                break;
            // ... handle other event types
            default:
                // Unexpected event type
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}