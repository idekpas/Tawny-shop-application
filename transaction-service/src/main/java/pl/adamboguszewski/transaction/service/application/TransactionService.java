package pl.adamboguszewski.transaction.service.application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.adamboguszewski.transaction.service.application.dto.CreateTransactionDto;
import pl.adamboguszewski.transaction.service.application.dto.GetTransactionDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class TransactionService {

    private final TransactionRepository<Transaction> repository;

    public TransactionService(TransactionRepository<Transaction> repository) {
        this.repository = repository;
    }

    public List<Transaction> getAllTransactions() {
        log.debug("Getting all transactions.");
        List<Transaction> transactions = repository.getAll();
        log.info("Received " + transactions.size() + " transactions.");
        return transactions;
    }

    public GetTransactionDto getByTransactionId(UUID id) {
        log.debug("Getting transaction with id: " + id);
        GetTransactionDto transactionDto = GetTransactionDto.fromTransaction(repository
                .getByTransactionId(id)
                .orElseThrow(() -> new TransactionNotFoundException(id)));
        log.info("Found transaction with id " + id);
        return transactionDto;
    }

    public Optional<Transaction> createTransaction(CreateTransactionDto dto) {
        log.debug("Creating transaction from given dto: " + new GsonBuilder().create().toJson(dto));
        Optional<Transaction> transaction = Optional.ofNullable(repository.save(new Transaction(dto)));
        if(transaction.isPresent()) {
            log.info("Transaction with id " + dto.getTransactionId() + " created successfully.");
        }
        else {
            log.debug("Could not create transaction with id " + dto.getTransactionId());
        }
        return transaction;
    }

    public Optional<Transaction> updateTransaction(UUID id, CreateTransactionDto dto) {
        Optional<Transaction> transaction = repository.getByTransactionId(id);
        if (transaction.isPresent()) {
            log.debug("Method with id " + id + " updated.");
            // [TODO]: Method for updating already existing transaction.
            return Optional.empty();
        } else {
            log.debug("Method with id " + id + " not found, creating a new transaction.");
            return createTransaction(dto);
        }
    }

    public void deleteTransaction(Long id) {
        log.debug("Deleting transaction with id " + id);
        repository.deleteById(id);
        log.info("Transaction with id " + id + " deleted.");
    }

    public void deleteOldTransactions() {
        long months = 24L; // [TODO]: 010-manage-application-properties
        log.debug("Deleting transactions older than " + months + " months");
        repository.deleteByTransactionDateTimeBefore(LocalDateTime.now().minusMonths(months));
    }
}
