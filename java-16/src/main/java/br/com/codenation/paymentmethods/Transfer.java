package br.com.codenation.paymentmethods;

public class Transfer implements PriceStrategy {

    @Override
    public Double calculate(Double price) {
        final double discount = 0.92;
        return price * discount;
    }
}
