package ladder.domain;

public class MatchResult {
    private final Player player;
    private final MatchCandidate matchCandidate;

    public MatchResult(Player player, MatchCandidate matchCandidate) {
        this.player = player;
        this.matchCandidate = matchCandidate;
    }

    public boolean belongTo(Player player) {
        return this.player.equals(player);
    }

    public String getMatchCandidateValue() {
        return this.matchCandidate.content();
    }
}
