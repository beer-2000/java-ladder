package ladder.domain;

import java.util.Optional;

public class Player {
    private final Name name;
    private Optional<Result> result;

    public Player(String nameValue) {
        this.name = new Name(nameValue);
        this.result = Optional.empty();
    }

    public Name getName() {
        return name;
    }

    public void saveResult(Result result) {
        this.result = Optional.ofNullable(result);
    }


    public boolean haveResult() {
        return this.result.isPresent();
    }

    public String getNameValue() {
        return this.name.value();
    }
}
