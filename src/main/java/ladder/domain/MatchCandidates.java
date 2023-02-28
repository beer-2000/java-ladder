package ladder.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MatchCandidates {
    private final List<MatchCandidate> matchCandidates;

    public MatchCandidates(String matchCandidateValuesRaw, int size) {
        List<String> matchCandidateValues = splitMatchCandidatesRaw(matchCandidateValuesRaw);
        validateSize(matchCandidateValues, size);
        this.matchCandidates = generateMatchCandidatesByRaw(matchCandidateValues);
    }

    public List<String> getContents() {
        return this.matchCandidates.stream()
                .map(MatchCandidate::content)
                .collect(Collectors.toList());
    }

    private List<String> splitMatchCandidatesRaw(String matchCandidateValuesRaw) {
        return Arrays.stream(matchCandidateValuesRaw.split(","))
                .collect(Collectors.toList());
    }

    private void validateSize(List<String> matchCandidateValues, int size) {
        if (matchCandidateValues.size() != size) {
            throw new IllegalArgumentException("플레이어의 수와 결과의 수가 다릅니다.");
        }
    }

    private List<MatchCandidate> generateMatchCandidatesByRaw(List<String> matchCandidateValues) {
        return matchCandidateValues.stream()
                .map(MatchCandidate::new)
                .collect(Collectors.toList());
    }

    public MatchCandidate getMatchCandidateByIndex(int columnIndex) {
        return this.matchCandidates.get(columnIndex);
    }
}
