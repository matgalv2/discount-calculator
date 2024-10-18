package io.github.g4lowy;


import io.github.g4lowy.controller.DiscountCalculationController;
import io.github.g4lowy.domain.DomainValidationException;
import io.github.g4lowy.service.DiscountCalculationDefaultService;
import io.github.g4lowy.service.DiscountCalculationService;
import io.github.g4lowy.view.DiscountCalculationView;
import io.github.g4lowy.view.DiscountCalculationViewConsole;

public class DiscountCalculatorApp {
    public static void main(String[] args) {
        DiscountCalculationService service = new DiscountCalculationDefaultService();
        DiscountCalculationView view = new DiscountCalculationViewConsole();
        DiscountCalculationController controller = new DiscountCalculationController(service, view);

        try {
            controller.run();
        }
        catch(DomainValidationException ex){
            System.out.println(ex.getMessage());
        }
    }
}
