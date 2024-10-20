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
* Domain constraints:
  * Discount represents a positive integer.
  * Item need product name and price, where price is also positive integer.
* Design patterns:
  * Strategy - allows defining others discount calculation algorithms
  * Iterator

## External libraries
* Lombok - reducing boilerplate code
* JUnit - testing