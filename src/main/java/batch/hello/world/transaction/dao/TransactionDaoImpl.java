package batch.hello.world.transaction.dao;

import batch.hello.world.transaction.domain.TransactionVo;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class TransactionDaoImpl extends JdbcTemplate implements TransactionDao {

    public TransactionDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<TransactionVo> getTransactionsByAccountNumber(String accountNumber) {
        return query(
                "select t.id, t.timestamp, t.amount " +
                        "from transaction t inner join account_summary a on " +
                        "a.id = t.account_summary_id " +
                        "where a.account_number = ?",
                new Object[]{accountNumber},
                (rs, rowNum) -> {
                    TransactionVo trans = new TransactionVo();
                    trans.setAmount(rs.getDouble("amount"));
                    trans.setTimestamp(rs.getDate("timestamp"));
                    return trans;
                }
        );
    }
}