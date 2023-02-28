package ladder.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MatchCandidatesTest {

    @Test
    @DisplayName("여러 개의 값을 입력받아 MatchCandidate를 복수로 생성한다.")
    void shouldCreatePlayersWhenInputStrings() {
        String matchCandidateValuesRaw = "꽝,5000,당첨,재시도";
        assertDoesNotThrow(() -> new MatchCandidates(matchCandidateValuesRaw, 4));
    }

    @Test
    @DisplayName("입력받은 수와 다른 수의 MatchCandidates를 생성하려하면 예외가 발생한다.")
    void shouldThrowExceptionWhenCreateMatchCandidatesMoreThanInputSize() {
        String matchCandidateValuesRaw = "꽝,5000,당첨,재시도";
        assertThatThrownBy(() -> new MatchCandidates(matchCandidateValuesRaw, 3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 수와 결과의 수가 다릅니다.");

    }

    @Test
    @DisplayName("결과들의 내용을 반환한다.")
    void shouldReturnContentsOfMatchCandidatesWhenRequest() {
        String matchCandidateValuesRaw = "꽝,5000,당첨";
        MatchCandidates matchCandidates = new MatchCandidates(matchCandidateValuesRaw, 3);
        assertThat(matchCandidates.getContents()).containsExactly("꽝", "5000", "당첨");
    }

    @Test
    @DisplayName("인덱스를 받아 해당하는 결과를 반환한다.")
    void shouldReturnMatchCandidateWhenInputIndex() {
        String matchCandidateValuesRaw = "꽝,5000,당첨";
        MatchCandidates matchCandidates = new MatchCandidates(matchCandidateValuesRaw, 3);
        MatchCandidate matchCandidate = matchCandidates.getMatchCandidateByIndex(2);
        assertThat(matchCandidate.content()).isEqualTo("당첨");
    }
}