package tennis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class GameTest {

    private Game game;

    @BeforeEach
    public void setUp() throws Exception {
        game = new Game();
    }

    @DisplayName("Score for new game = Love All")
    @Test
    public void newGame() {
        assertThat(game.call(), is("Love All"));
    }

    @DisplayName("Server = 1 rally, Receiver = no rallies, Score = Fifteen Love")
    @Test
    public void serverOneReceiverZero() {
        ralliesForServer(1);
        assertThat(game.call(), is("Fifteen Love"));
    }

    @DisplayName("Server = no rallies, Receiver = 1 rally, Score = Love Fifteen")
    @Test
    public void serverZeroReceiverOne() {
        ralliesForReceiver(1);
        assertThat(game.call(), is("Love Fifteen"));
    }

    @DisplayName("Server = 2 rallies, Receiver = 0 rallies, Score = Thirty Love")
    @Test
    public void serverTwoReceiverZero() {
        ralliesForServer(2);
        assertThat(game.call(), is("Thirty Love"));
    }

    @DisplayName("Server = 3 rallies, Receiver = 0 rallies, Score = Forty Love")
    @Test
    public void serverThreeReceiverZero() {
        ralliesForServer(3);
        assertThat(game.call(), is("Forty Love"));
    }

    @DisplayName("Server = 3 rallies, Receiver = 3 rallies, Score = Deuce")
    @Test
    public void firstDeuce() {
        basicDeuce();
        assertThat(game.call(), is("Deuce"));
    }

    @DisplayName("Server = 3 rallies, Receiver = 3 rallies, then Server = 1 rally. Receiver = 1 rally, Score = Deuce")
    @Test
    public void secondDeuce() {
        basicDeuce();
        ralliesForServer(1);
        ralliesForReceiver(1);
        assertThat(game.call(), is("Deuce"));
    }

    @DisplayName("Receiver = 2 rallies, Server = 4 rallies, Score = Game Server")
    @Test
    public void receiverTwoServerFour() {
        ralliesForReceiver(2);
        ralliesForServer(4);
        assertThat(game.call(), is("Game Server"));
    }

    @DisplayName("Server = 2 rallies, Receiver = 4 rallies, Score = Game Receiver")
    @Test
    public void serverTwoReceiverFour() {
        ralliesForServer(2);
        ralliesForReceiver(4);
        assertThat(game.call(), is("Game Receiver"));
    }

    @DisplayName("First deuce, then Server = 1 rally, Score = Advantage Server")
    @Test
    public void advServer() {
        basicDeuce();
        ralliesForServer(1);
        assertThat(game.call(), is("Advantage Server"));
    }

    @DisplayName("First deuce, then Server = 1 rally, Receiver = 2 rallies, Score = Advantage Receiver")
    @Test
    public void advReceiver() {
        basicDeuce();
        ralliesForServer(1);
        ralliesForReceiver(2);
        assertThat(game.call(), is("Advantage Receiver"));
    }

    @DisplayName("Error check: Server cannot score when game is over")
    @Test
    public void whenGameIsOverServerCannotWinAnyMoreRallies() {
        ralliesForServer(4);
        InvalidGameOperationException thrown = Assertions.assertThrows(InvalidGameOperationException.class, () -> {
           ralliesForServer(1);
        });
        assertThat(thrown.getMessage(), is("Game over: server cannot win any more rallies!"));
    }

    @DisplayName("Error check: Receiver cannot score when game is over")
    @Test
    public void whenGameIsOverReceiverCannotWinAnyMoreRallies() {
        ralliesForServer(4);
        InvalidGameOperationException thrown = Assertions.assertThrows(InvalidGameOperationException.class, () -> {
            ralliesForReceiver(1);
        });
        assertThat(thrown.getMessage(), is("Game over: receiver cannot win any more rallies!"));
    }

    private void basicDeuce() {
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
