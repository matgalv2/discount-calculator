# Discount Calculator
Simple app allowing to split discount between provided products.

## Architecture
MVC pattern has been utilized to ensure that all components can be developed individually.
* Model - represented as DiscountCalculationService with default implementation, which provides calculating discount with default strategy and chosen one.
* View - represented as DiscountCalculationView with 2 methods - getData and showResult. Default implementation uses mocked input data and console for showing results.
* Controller - represented as DiscountCalculationController.

## Design aspects
* The exercise specified that there would be at most 5 products, but utilized algorithm is more generic and allows calculating discount for unlimited number of products.
* Application requires that passed data from view is ordered to ensure that the rest of unused discount is intended for the last element.
* The discount and products' prices are integers so every calculated value is rounded down. If there was a need to represents values as floating point number it would be easy to implement due to a fact that in case of currencies only first and second decimal places are important. Everything further must be rounded down. So it all comes to manipulating comma place.
* Domain constraints:
  * Discount represents a positive integer.
  * Item need product name and price, where price is also positive integer.
* Design patterns:
  * Strategy - allows defining and using other discount calculation algorithms
  * Iterator

## External libraries
* Lombok - reducing boilerplate code
* JUnit - testing