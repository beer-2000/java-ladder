package ladder.controller;

import ladder.domain.Ladder;
import ladder.domain.Players;
import ladder.domain.Result;
import ladder.domain.Results;
import ladder.view.InputView;
import ladder.view.OutputView;

public class LadderController {
    private static final int DIFFERENCE_PLAYERS_AND_BARS = 1;

    private final Players players;
    private final Ladder ladder;
    private final Results results;

    public LadderController() {
        this.players = new Players(InputView.readNames());
        this.results = new Results(InputView.readResults());
        this.ladder = new Ladder(InputView.readCountOfLines(), getCountOfBars());
    }

    private int getCountOfBars() {
        return this.players.getSize() - DIFFERENCE_PLAYERS_AND_BARS;
    }

    public void play() {
        OutputView.announceExecution();
        OutputView.printPlayers(players.getNameValues());
        OutputView.printLadder(ladder.getLines());
        OutputView.printResults(results.getResultValues());
        printResult();
    }

    private void printResult() {
        String nameRaw = InputView.readNameForResult();
        int playerIndex = players.getIndexOf(nameRaw);
        int resultIndex = ladder.getResultStartAt(playerIndex);
        Result result = results.getResultByIndex(resultIndex);
        OutputView.printResultAfterPlay(result);
    }
}
