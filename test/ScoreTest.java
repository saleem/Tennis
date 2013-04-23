import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ScoreTest {
    @Test
    public void scoreForNewGameIsLoveAll() {

        Score score = new Score();
        assertThat(score.call(), is("Love All"));
    }
}
