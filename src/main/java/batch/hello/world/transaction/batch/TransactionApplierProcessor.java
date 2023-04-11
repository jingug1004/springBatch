package batch.hello.world.transaction.batch;

import batch.hello.world.transaction.dao.TransactionDao;
import batch.hello.world.transaction.domain.AccountSummary;
import batch.hello.world.transaction.domain.Transaction;
import org.springframework.batch.item.ItemProcessor;

import java.util.List;

public class TransactionApplierProcessor implements ItemProcessor<AccountSummary, AccountSummary> {

    private TransactionDao transactionDao;

    public TransactionApplierProcessor(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    @Override
    public AccountSummary process(AccountSummary summary) throws Exception {
        List<Transaction> transactions = transactionDao.getTransactionsByAccountNumber(summary.getAccountNumber());

        for (Transaction transaction : transactions) {
            summary.setCurrentBalance(summary.getCurrentBalance() + transaction.getAmount());
        }
        return summary;
    }

}