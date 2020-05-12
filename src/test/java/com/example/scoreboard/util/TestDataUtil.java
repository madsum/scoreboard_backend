package com.example.scoreboard.util;

import com.example.scoreboard.model.ScoreBoard;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestDataUtil {

    public static ScoreBoard createScoreBoard() {
        ScoreBoard scoreBoard = new ScoreBoard();
        scoreBoard.setId(1);
        scoreBoard.setTeam("test team");
        scoreBoard.setPoint(10);
        return scoreBoard;
    }

    public static List<ScoreBoard> createScoreBoardList(int numOfElements) {
        String team = "team name ";

        return IntStream.range(0, numOfElements)
                .mapToObj(i -> {
                    ScoreBoard scoreBoard = new ScoreBoard();
                    scoreBoard.setTeam(team + i);
                    scoreBoard.setPoint(i);
                    return scoreBoard;
                })
                .collect(Collectors.toList());
    }
}
