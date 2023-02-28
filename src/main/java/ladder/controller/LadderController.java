package ladder.controller;

import static ladder.domain.GameCommand.CONTINUE;
import static ladder.domain.GameCommand.END;

import ladder.domain.GameCommand;
import ladder.domain.Ladder;
import ladder.domain.Location;
import ladder.domain.Player;
import ladder.domain.Players;
import ladder.domain.MatchCandidate;
import ladder.domain.MatchCandidates;
import ladder.view.InputView;
import ladder.view.OutputView;

public class LadderController {
    private static final int DIFFERENCE_PLAYERS_AND_BARS = 1;

    private Players players;
    private final Ladder ladder;
    private final MatchCandidates matchCandidates;

    public LadderController() {
        initPlayers();
        this.matchCandidates = new MatchCandidates(InputView.readMatchCandidates(), this.players.getSize());
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
        OutputView.printMatchCandidates(this.matchCandidates.getContents());
        printMatchCandidateAfterPlay();
    }

    private void printMatchCandidateAfterPlay() {
        GameCommand gameCommand;
        do {
            gameCommand = printMatchCandidateByInput(InputView.readPlayerName());
        } while (gameCommand == CONTINUE);
    }

    private GameCommand printMatchCandidateByInput(String playerNameInput) {
        if (playerNameInput.equals("all")) {
            calculateMatchCandidateOfAllPlayer();
            printMatchCandidatesOfAllPlayers();
            return END;
        }
        printMatchCandidateOnePlayer(playerNameInput);
        return CONTINUE;
    }

    private void calculateMatchCandidateOfAllPlayer() {
        this.players.getPlayers().forEach(this::calculateMatchCandidateOfPlayer);
    }

    private void printMatchCandidatesOfAllPlayers() {
        OutputView.printMatchCandidatesOfAllPlayers(this.players.getPlayers());
    }

    private void printMatchCandidateOnePlayer(String playerNameInput) {
        Player player = this.players.getPlayerByName(playerNameInput);
        if (!player.haveMatchCandidate()) {
            calculateMatchCandidateOfPlayer(player);
        }
        OutputView.printMatchCandidateOfPlayer(player.getContentOfMatchCandidate());
    }

    private void calculateMatchCandidateOfPlayer(Player player) {
        Location location = new Location(player.getIndex());
        this.ladder.move(location);
        MatchCandidate matchCandidateOfPlayer = this.matchCandidates.getMatchCandidateByIndex(location.getColumnIndex());
        player.saveMatchCandidate(matchCandidateOfPlayer);
    }
}
