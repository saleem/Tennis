package tennis;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class GameTest {

    private Game game;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

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
        firstDeuce();
        assertThat(game.call(), is("Deuce"));
    }

    @Test
    public void whenBothServerAndReceiverHaveWonFourRalliesScoreCallIsDeuce() {
        firstDeuce();
        ralliesForServer(1);
        ralliesForReceiver(1);
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
        ralliesForServer(2);
        ralliesForReceiver(4);
        assertThat(game.call(), is("Game Receiver"));
    }

    @Test
    public void whenServerHasWonFourRalliesAndReceiverHasWonThreeRalliesScoreCallIsAdvantageServer() {
        firstDeuce();
        ralliesForServer(1);
        assertThat(game.call(), is("Advantage Server"));
    }

    @Test
    public void whenServerHasWonFourRalliesAndReceiverHasWonFiveRalliesScoreCallIsAdvantageReceiver() {
        firstDeuce();
        ralliesForServer(1);
        ralliesForReceiver(2);
        assertThat(game.call(), is("Advantage Receiver"));
    }

    @Test
    public void whenGameIsOverServerCannotWinAnyMoreRallies() {
        expectedException.expect(InvalidGameOperationException.class);
        expectedException.expectMessage("Game over: server cannot win any more rallies!");
        ralliesForServer(4);
        ralliesForServer(1);
    }

    @Test
    public void whenGameIsOverReceiverCannotWinAnyMoreRallies() {
        expectedException.expect(InvalidGameOperationException.class);
        expectedException.expectMessage("Game over: receiver cannot win any more rallies!");
        ralliesForServer(4);
        ralliesForReceiver(1);
    }

    private void firstDeuce() {
        ralliesForServer(3);
        ralliesForReceiver(3);
    }

    private void ralliesForServer(int howMany) {
        for (int i = 0; i < howMany; i++) {
            game.rallyForServer();
        }
    }

    private void ralliesForReceiver(int howMany) {
        for (int i = 0; i < howMany; i++) {
            game.rallyForReceiver();
        }
    }
}
