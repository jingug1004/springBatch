package batch.hello.world.transaction.domain;

import lombok.*;

import java.util.Date;


@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    private String accountNumber;

    private Date timestamp;

    private double amount;
}