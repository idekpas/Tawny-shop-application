package pl.adamboguszewski.transactionservice.service.api.transaction;

import lombok.Value;

@Value
public class CreateUnsuccessfulTransactionResponse implements CreateTransactionResponse {

    ErrorType errorType;

    public enum ErrorType {
        UNSUCCESSFUL_PAYMENT,
        DATABASE_CONNECTION_FAILED
    }
}
