package techcourse.jcf.mission;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SimpleListTest {
    SimpleList list;

    @BeforeEach
    void initList() {
        // 구현한 구현체로 바꾸기
        list = new SimpleArrayList(new String[]{"0", "1"});
    }

    @Test
    void get() {
        assertThat(list.get(0)).isEqualTo("0");
    }

    @Test
    void addWithOnlyValue() {
        assertThat(list.add("2")).isTrue();
        assertThat(list.get(2)).isEqualTo("2");
    }

    @Test
    void addManyValuesThrowNoException() {
        assertDoesNotThrow(() -> {
            for (int i = 2; i < 50; i++) {
                list.add(String.format("%d", i));
            }
        });
    }

    @Test
    void addWithVIndexValue() {
        list.add(1, "2");
        assertThat(list.get(1)).isEqualTo("2");
    }

    @Test
    void addManyValuesWithIndexThrowNoException() {
        assertDoesNotThrow(() -> {
            for (int i = 2; i < 50; i++) {
                list.add(i, String.format("%d", i));
            }
        });
    }

    @Test
    void addWithVIndexValueThrowException() {
        assertThatThrownBy(() -> list.add(3, "3"))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void set() {
        assertThat(list.set(1, "2")).isEqualTo("1");
        assertThat(list.get(1)).isEqualTo("2");
    }

    @Test
    void setThrowException() {
        assertThatThrownBy(() -> list.set(2, "2"))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void getThrowException() {
        assertThatThrownBy(() -> list.get(2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void contains() {
        assertThat(list.contains("1")).isTrue();
        assertThat(list.contains("2")).isFalse();
    }

    @Test
    void indexOf() {
        assertThat(list.indexOf("1")).isEqualTo(1);
        assertThat(list.indexOf("3")).isEqualTo(-1);
    }

    @Test
    void size() {
        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    void isEmpty() {
        assertThat(list.isEmpty()).isFalse();
        list.clear();
        assertThat(list.isEmpty()).isTrue();
    }

    @Test
    void removeWithValue() {
        assertThat(list.remove("1")).isTrue();
        assertThat(list.get(0)).isEqualTo("2");
    }

    @Test
    void removeFailWithValue() {
        assertThat(list.remove("3")).isFalse();
    }

    @Test
    void removeWithIndex() {
        assertThat(list.remove(0)).isEqualTo("1");
        assertThat(list.get(0)).isEqualTo("2");
    }

    @Test
    void clear() {
        assertThat(list.size()).isEqualTo(2);
        list.clear();
        assertThat(list.size()).isEqualTo(0);
    }
}