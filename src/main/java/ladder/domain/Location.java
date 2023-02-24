package ladder.domain;

public class Location {
    private int lineIndex;
    private int barIndex;

    public Location(int barIndex) {
        this.lineIndex = 0;
        this.barIndex = barIndex;
    }

    public int getBarIndex() {
        return this.barIndex;
    }

    public void moveTo(Direction direction) {
        this.barIndex += direction.getIndexDifference();
        this.lineIndex += 1;
    }
}
