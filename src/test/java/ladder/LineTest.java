package ladder;

import static ladder.domain.Bar.IMMOVABLE;
import static ladder.domain.Bar.MOVABLE;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import ladder.domain.Line;
import ladder.utils.BooleanGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LineTest {

    @Test
    @DisplayName("bar를 생성한다")
    void should3barWhenCreateLine() {
        // given
        // when
        Line line = new Line(3);
        // then
        assertThat(line.getBars()).hasSize(3);
    }

    @Test
    @DisplayName("0 이하 크기의 bar를 생성하면 예외를 발생시킨다")
    void shouldThrowExceptionWhenInputBelow0() {
        // given
        // when
        // then
        assertThatThrownBy(() -> new Line(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("한 Line의 Bar는 1개 이상이어야 합니다.");
    }

    @Test
    @DisplayName("bar가 연속되지 않게 Line을 생성한다")
    void shouldDeterminedValuesWhenCreateLine() {
        // given
        List<Boolean> determinedBars = new ArrayList<>(List.of(true, true));
        // when
        Line line = new Line(3, new DeterminedBooleanGenerator(determinedBars));
        // then
        assertThat(line.getBars()).containsExactly(MOVABLE, IMMOVABLE, MOVABLE);
    }

    static class DeterminedBooleanGenerator implements BooleanGenerator {
        private final List<Boolean> bars;

        public DeterminedBooleanGenerator(List<Boolean> bars) {
            this.bars = bars;
        }

        @Override
        public boolean generate() {
            return bars.remove(0);
        }
    }
}
