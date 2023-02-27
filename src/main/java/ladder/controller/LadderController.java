package ladder.controller;

import static ladder.domain.GameCommand.CONTINUE;
import static ladder.domain.GameCommand.END;

import ladder.domain.GameCommand;
import ladder.domain.Ladder;
import ladder.domain.Location;
import ladder.domain.Player;
import ladder.domain.Players;
import ladder.domain.Result;
import ladder.domain.Results;
import ladder.view.InputView;
import ladder.view.OutputView;

public class LadderController {
    private static final int DIFFERENCE_PLAYERS_AND_BARS = 1;

    private Players players;
    private final Ladder ladder;
    private final Results results;

    public LadderController() {
        initPlayers();
        this.results = new Results(InputView.readResults(), this.players.getSize());
        this.ladder = new Ladder(InputView.readCountOfLines(), getCountOfBars());
    }

    private void initPlayers() {
        boolean isGenerated;
        do {
            isGenerated = generatePlayers();
        } while (!isGenerated);
    }

    private boolean generatePlayers() {
        try {
            this.players = new Players(InputView.readNames());;
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private int getCountOfBars() {
        return this.players.getSize() - DIFFERENCE_PLAYERS_AND_BARS;
    }

    public void play() {
        OutputView.announceCreateLadder();
        OutputView.printPlayers(this.players.getNameValues());
        OutputView.printLadder(this.ladder.getLines());
        OutputView.printResults(this.results.getContents());
        printResultAfterPlay();
    }

    private void printResultAfterPlay() {
        GameCommand gameCommand;
        do {
            gameCommand = printResultByInput(InputView.readPlayerName());
        } while (gameCommand == CONTINUE);
    }

    private GameCommand printResultByInput(String playerNameInput) {
        if (playerNameInput.equals("all")) {
            calculateResultOfAllPlayer();
            printResultsOfAllPlayers();
            return END;
        }
        printResultOnePlayer(playerNameInput);
        return CONTINUE;
    }

    private void calculateResultOfAllPlayer() {
        this.players.getPlayers().forEach(this::calculateResultOfPlayer);
    }

    private void printResultsOfAllPlayers() {
        OutputView.printResultsOfAllPlayers(this.players.getPlayers());
    }

    private void printResultOnePlayer(String playerNameInput) {
        Player player = this.players.getPlayerByName(playerNameInput);
        if (!player.haveResult()) {
            calculateResultOfPlayer(player);
        }
        OutputView.printResultOfPlayer(player.getContentOfResult());
    }

    private void calculateResultOfPlayer(Player player) {
        Location location = new Location(player.getIndex());
        this.ladder.move(location);
        Result resultOfPlayer = this.results.getResultByIndex(location.getColumnIndex());
        player.saveResult(resultOfPlayer);
    }
}
