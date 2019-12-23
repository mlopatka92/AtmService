package pl.mlopatka.model;

import com.google.common.base.Joiner;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Withdraw {

    private final static String DEFAULT_CURRENCY = "PLN";

    private Map<Integer, Integer> banknoteAmount;
    private String currencyCode = DEFAULT_CURRENCY;

    public Withdraw() {
        this.banknoteAmount = new HashMap<>();
    }

    public Withdraw(Map<Integer, Integer> banknoteAmount, String currencyCode) {
        this.banknoteAmount = banknoteAmount;
        this.currencyCode = currencyCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Withdraw withdraw = (Withdraw) o;
        return Objects.equals(banknoteAmount, withdraw.banknoteAmount) &&
                Objects.equals(currencyCode, withdraw.currencyCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(banknoteAmount, currencyCode);
    }

    @Override
    public String toString() {
        return "Withdraw{" +
                "CurrencyCode: " + currencyCode
                + "banknoteAmounts: " + Joiner.on(',').withKeyValueSeparator("=").join(banknoteAmount)
                + '}';
    }
}
