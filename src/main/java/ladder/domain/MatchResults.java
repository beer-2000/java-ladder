package ladder.domain;

import java.util.ArrayList;
import java.util.List;

public class MatchResults {
    private static final List<MatchResult> matchResultList = new ArrayList<>();

    public static void add(MatchResult matchResult) {
        matchResultList.add(matchResult);
    }

    public static MatchResult getMatchResultOf(Player player) {
        return matchResultList.stream()
                .filter(matchResult -> matchResult.belongTo(player))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("결과가 존재하지 않는 플레이어입니다."));
    }
}
