package ladder.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Results {
    private final List<Result> results;

    public Results(String resultsInput) {
        List<String> resultsRaw = splitResultsRaw(resultsInput);
        this.results = generateResults(resultsRaw);
    }

    private List<Result> generateResults(List<String> resultsRaw) {
        return resultsRaw.stream()
                .map(Result::new)
                .collect(Collectors.toList());
    }

    private List<String> splitResultsRaw(String resultsInput) {
        return Arrays.stream(resultsInput.split(",")).collect(Collectors.toList());
    }

}
