package io.github.g4lowy;


import io.github.g4lowy.controller.DiscountCalculationController;
import io.github.g4lowy.domain.DomainValidationException;
import io.github.g4lowy.dto.Data;
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
            Data data = view.getData();
            Map<String, Integer> result = controller.calculateDiscounts(data);

            view.showResult(result);
        }
        catch(DomainValidationException ex){
            System.out.println(ex.getMessage());
        }
    }
}
