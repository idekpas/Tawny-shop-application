package pl.adamboguszewski.transaction.service.api.exception;

import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
public class IllegalCurrencyArgumentException extends IllegalTransactionArgumentException {

    String currency;

    public IllegalCurrencyArgumentException(String currency) {
        super("Currency named " + currency + " does not exist.");
        this.currency = currency;
    }
}
