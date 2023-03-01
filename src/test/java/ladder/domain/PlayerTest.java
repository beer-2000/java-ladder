package ladder.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("이름을 입력받아 Player를 생성한다")
    public void shouldCreatePlayerWhenInputName() {
        // given
        // when
        // then
        assertDoesNotThrow(() -> new Player("name", 0));
    }

    @Test
    @DisplayName("이름을 반환한다.")
    void shouldReturnNameValueWhenRequest() {
        String name = "name";
        Player player = new Player(name, 0);
        assertThat(player.getNameValue()).isEqualTo(name);
    }

    @Test
    @DisplayName("Player의 위치를 반환한다.")
    void shouldReturnIndexWhenRequest() {
        Player player = new Player("name", 3);
        assertThat(player.getIndex()).isEqualTo(3);
    }

    @Test
    @DisplayName("입력받은 값과 같은 이름을 가지고 있으면 true를 반환한다.")
    void shouldTrueWhenHaveNameWithSameValue() {
        String nameValue = "name";
        Player player = new Player(nameValue, 0);
        assertThat(player.haveNameOf(nameValue)).isTrue();
    }

    @Test
    @DisplayName("입력받은 값과 같은 이름을 가지고 있지 않으면 false를 반환한다.")
    void shouldFalseWhenHaveNameWithDifferentValue() {
        String differentNameValue = "abcd";
        Player player = new Player("name", 0);
        assertThat(player.haveNameOf(differentNameValue)).isFalse();
    }
}

