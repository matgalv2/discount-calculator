package io.github.g4lowy;


import io.github.g4lowy.controller.DiscountCalculationController;
import io.github.g4lowy.error.DomainValidationException;
import io.github.g4lowy.dto.DiscountRequestData;
import io.github.g4lowy.service.DiscountCalculationDefaultService;
import io.github.g4lowy.service.DiscountCalculationService;
import io.github.g4lowy.view.DiscountCalculationView;
import io.github.g4lowy.view.DiscountCalculationViewConsole;

import java.util.Map;

public class DiscountCalculatorApp {
    public static void main(String[] args) {
        DiscountCalculationService service = new DiscountCalculationDefaultService();
        DiscountCalculationView view = new DiscountCalculationViewConsole();
        DiscountCalculationController controller = new DiscountCalculationController(service);

        try {
            DiscountRequestData discountRequestData = view.getData();
            Map<String, Integer> result = controller.calculateDiscounts(discountRequestData);

            view.showResult(result);
        }
        catch(DomainValidationException ex){
            System.out.println(ex.getMessage());
        }
    }
}
