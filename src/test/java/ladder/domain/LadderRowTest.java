package ladder.domain;

import ladder.util.RowInputGenerator;
import org.junit.After;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class LadderRowTest {

    @Test
    public void 사다리_한줄_긋기_1칸남음() {
        LadderRow ladderRow = new LadderRow(1);

        ByteArrayInputStream input = new ByteArrayInputStream("1 1".getBytes());
        System.setIn(input);

        ladderRow.makeRow();
        assertEquals(Arrays.asList(0), ladderRow.status());
    }

    @Test
    public void 사다리_만들기_테스트() {
        RowInputGenerator rowInputGenerator = new RowInputGenerator(Arrays.asList(1, 1, 1));
        LadderRow ladderRow = new LadderRow(5, rowInputGenerator);
        ladderRow.makeRow();
        assertEquals(Arrays.asList(1, -1, 1, -1, 0), ladderRow.status());
    }

    @Test
    public void 사다리_한줄_만들기_테스트() {
        RowInputGenerator rowInputGenerator = new RowInputGenerator(Arrays.asList(0, 0, 0, 0, 0));
        LadderRow ladderRow = new LadderRow(5, rowInputGenerator);
        ladderRow.makeRow();
        assertEquals(Arrays.asList(0, 0, 0, 0, 0), ladderRow.status());
    }

    @Test
    public void 사다리_한줄_만들기_테스트2() {
        RowInputGenerator rowInputGenerator = new RowInputGenerator(Arrays.asList(1, 1, 0));
        LadderRow ladderRow = new LadderRow(5, rowInputGenerator);
        ladderRow.makeRow();
        assertEquals(Arrays.asList(1, -1, 1, -1, 0), ladderRow.status());
    }

    @After
    public void flushSTDIN() {
        System.setIn(System.in);
    }
}