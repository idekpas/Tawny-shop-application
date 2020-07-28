package pl.adamboguszewski.transactionservice.application;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Transaction {

    private final List<Product> products;

    private final Long totalPrice;

    private final String id;

    private final UUID transactionId;

    private final String checkoutId;

    private final LocalTime transactionTime;

    private final LocalDate transactionDate;

    public Transaction(List<Product> products, String id, UUID transactionId,
                       Long totalPrice, String checkoutId,
                       LocalTime transactionTime, LocalDate transactionDate) {
        this.id = id;
        this.products = new ArrayList<>();
        this.products.addAll(products);
        this.transactionId = transactionId;
        this.totalPrice = totalPrice;
        this.checkoutId = checkoutId;
        this.transactionTime = transactionTime;
        this.transactionDate = transactionDate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public String getId() {
        return id;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public String getCheckoutId() {
        return checkoutId;
    }

    public LocalTime getTransactionTime() {
        return transactionTime;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

}
