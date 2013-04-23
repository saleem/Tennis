import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ScoreTest {
    @Test
    public void scoreForNewGameIsLoveAll() {
        Score score = new Score();
        assertThat(score.call(), is("Love All"));
    }

    @Test
    public void whenTheServerWinsFirstRallyScoreForGameIsFifteenLove() {
        Score score = new Score();
        score.rallyForServer();
        assertThat(score.call(), is("Fifteen Love"));
    }

    @Test
    public void whenTheReceiverWinsFirstRallyScoreForGameIsLoveFifteen() {
        Score score = new Score();
        score.rallyForReceiver();
        assertThat(score.call(), is("Love Fifteen"));
    }
}
