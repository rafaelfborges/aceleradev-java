package br.com.codenation.paymentmethods;

public class CreditCard implements PriceStrategy{

    @Override
    public Double calculate(Double price) {
        final double discount = 0.98;
        return price * discount;
    }
}
