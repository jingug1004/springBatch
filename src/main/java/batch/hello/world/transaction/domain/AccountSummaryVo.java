package batch.hello.world.transaction.domain;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountSummaryVo {

    private int id;

    private String accountNumber;

    private Double currentBalance;
}