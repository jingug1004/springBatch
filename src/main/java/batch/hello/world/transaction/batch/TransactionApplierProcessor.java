package batch.hello.world.transaction.batch;

import batch.hello.world.transaction.dao.TransactionDao;
import batch.hello.world.transaction.domain.AccountSummaryVo;
import batch.hello.world.transaction.domain.TransactionVo;
import org.springframework.batch.item.ItemProcessor;

import java.util.List;

public class TransactionApplierProcessor implements ItemProcessor<AccountSummaryVo, AccountSummaryVo> {

    private TransactionDao transactionDao;

    public TransactionApplierProcessor(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    @Override
    public AccountSummaryVo process(AccountSummaryVo summary) throws Exception {
        List<TransactionVo> transactionVos = transactionDao.getTransactionsByAccountNumber(summary.getAccountNumber());

        for (TransactionVo transactionVo : transactionVos) {
            summary.setCurrentBalance(summary.getCurrentBalance() + transactionVo.getAmount());
        }
        return summary;
    }

}