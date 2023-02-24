package ladder.domain;

public class Location {
    private int lineIndex;
    private int barIndex;

    public Location(int barIndex) {
        this.lineIndex = 0;
        this.barIndex = barIndex;
    }
}
