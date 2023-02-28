package ladder.domain;

public class Player {
    private final Name name;
    private final int index;

    public Player(String nameValue, int index) {
        this.name = new Name(nameValue);
        this.index = index;
    }

    public Name getName() {
        return name;
    }

    public String getNameValue() {
        return this.name.value();
    }

    public int getIndex() {
        return this.index;
    }

    public boolean haveNameOf(String nameValue) {
        return this.name.haveSameValueWith(nameValue);
    }
}
