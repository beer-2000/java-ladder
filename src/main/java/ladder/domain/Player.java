package ladder.domain;

public class Player {
    private final Name name;
    private final int index;
    private MatchCandidate matchCandidate;

    public Player(String nameValue, int index) {
        this.name = new Name(nameValue);
        this.index = index;
    }

    public Name getName() {
        return name;
    }

    public void saveMatchCandidate(MatchCandidate matchCandidate) {
        this.matchCandidate = matchCandidate;
    }


    public boolean haveMatchCandidate() {
        return this.matchCandidate != null;
    }

    public String getNameValue() {
        return this.name.value();
    }

    public MatchCandidate getMatchCandidate() {
        return this.matchCandidate;
    }

    public int getIndex() {
        return this.index;
    }

    public boolean haveNameOf(String nameValue) {
        return this.name.haveSameValueWith(nameValue);
    }

    public String getContentOfMatchCandidate() {
        return getMatchCandidate().content();
    }
}
