package lotto.practice.random.domain.winning.rank;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RankCommand {

    private Rank fifth;
    private Rank fourth;
    private Rank third;
    private Rank second;
    private Rank first;

    @Builder
    public RankCommand(Rank fifth, Rank fourth, Rank third, Rank second, Rank first) {
        this.fifth = fifth;
        this.fourth = fourth;
        this.third = third;
        this.second = second;
        this.first = first;
    }
}
