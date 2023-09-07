package br.com.rsoft.api.cdc.cart;

import br.com.rsoft.api.cdc.validator.ValidationErrorHandler;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    @PostMapping("/payment")
    ResponseEntity addPayment(@RequestBody @Valid NewPaymentRequest newPaymentRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorHandler.getErrorMessages(bindingResult));
        }

        System.out.println(newPaymentRequest);

        return null;

    }
}
