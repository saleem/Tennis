import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GameTest {

    private Game game;

    @Before
    public void setUp() throws Exception {
        game = new Game();
    }

    @Test
    public void scoreForNewGameIsLoveAll() {
        assertThat(game.call(), is("Love All"));
    }

    @Test
    public void whenServerHasWonOneRallyAndReceiverHasNotWonAnyRalliesScoreCallIsFifteenLove() {
        ralliesForServer(1);
        assertThat(game.call(), is("Fifteen Love"));
    }

    @Test
    public void whenServerHasNotWonAnyRalliesAndReceiverHasWonOneRallyScoreCallIsLoveFifteen() {
        ralliesForReceiver(1);
        assertThat(game.call(), is("Love Fifteen"));
    }

    @Test
    public void whenServerHasWonTwoRalliesAndReceiverHasNotWonAnyRalliesScoreCallIsThirtyLove() {
        ralliesForServer(2);
        assertThat(game.call(), is("Thirty Love"));
    }

    @Test
    public void whenServerHawWonThreeRalliesAndReceiverHasNotWonAnyRalliesScoreCallIsFortyLove() {
        ralliesForServer(3);
        assertThat(game.call(), is("Forty Love"));
    }

    @Test
    public void whenBothServerAndReceiverHaveWonThreeRalliesScoreCallIsDeuce() {
        ralliesForServer(3);
        ralliesForReceiver(3);
        assertThat(game.call(), is("Deuce"));
    }

    @Test
    public void whenBothServerAndReceiverHaveWonFourRalliesScoreCallIsDeuce() {
        ralliesForServer(4);
        ralliesForReceiver(4);
        assertThat(game.call(), is("Deuce"));
    }

    @Test
    public void whenServerHasWonFourRalliesAndReceiverHasWonTwoRalliesScoreCallIsGameServer() {
        ralliesForReceiver(2);
        ralliesForServer(4);
        assertThat(game.call(), is("Game Server"));
    }

    @Test
    public void whenServerHasWonTwoRalliesAndReceiverHasWonFourRalliesScoreCallIsGameReceiver() {
        ralliesForReceiver(4);
        ralliesForServer(2);
        assertThat(game.call(), is("Game Receiver"));
    }

    private void ralliesForServer(int howMany) {
        for (int i=0; i < howMany; i++) {
           game.rallyForServer();
        }
    }

    private void ralliesForReceiver(int howMany) {
        for (int i=0; i < howMany; i++) {
           game.rallyForReceiver();
        }
    }
}
