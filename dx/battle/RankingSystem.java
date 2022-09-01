package dx.battle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

class Result {
    int currentRank;
    int bestRank;
    int worstRank;

    public Result(int currentRank, int bestRank, int worstRank) {
        this.currentRank = currentRank;
        this.bestRank = bestRank;
        this.worstRank = worstRank;
    }
}

class Player {
    int score;
    int minusScore;

    public Player(int score, int minusScore) {
        this.score = score;
        this.minusScore = minusScore;
    }
}

public class RankingSystem {
    private int totalScore;
    private HashMap<String, Integer> problems;
    private HashMap<String, Player> players;
    private HashMap<String, ArrayList<String>> corrects;
    private HashMap<String, ArrayList<String>> wrongs;
    private TreeMap<Integer, Integer> scoreRanking;
    private TreeMap<Integer, Integer> minusScoreRanking;

    void init () {
        totalScore = 0;
        problems = new HashMap<>();
        players = new HashMap<>();
        corrects = new HashMap<>();
        wrongs = new HashMap<>();
        scoreRanking = new TreeMap<>();
        minusScoreRanking = new TreeMap<>();
    }

    void destroy () {

    }

    void newPlayer(char name[]) {
        String strName = new String(name).split("\0")[0];
        players.put(strName, new Player(0, 0));

        Player player = players.get(strName);

        int value = scoreRanking.get(player.score) == null ? 1 : scoreRanking.get(player.score) + 1;
        scoreRanking.put(player.score, value);

        value = minusScoreRanking.get(player.minusScore) == null ? 1 : minusScoreRanking.get(player.minusScore) + 1;
        minusScoreRanking.put(player.minusScore, value);
    }

    void newProblem(char name[], int score) {
        String strName = new String(name).split("\0")[0];
        problems.put(strName, score);
        totalScore += score;

        corrects.put(strName, new ArrayList<>());
        wrongs.put(strName, new ArrayList<>());
    }

    void changeProblemScore(char name[], int new_score) {
        String strName = new String(name).split("\0")[0];
        int diff = new_score - problems.get(strName);
        totalScore += diff;
        problems.put(strName, new_score);

        for (String playerName : corrects.get(strName)) {
            Player player = players.get(playerName);
            scoreRanking.put(player.score, scoreRanking.get(player.score) - 1);

            player.score += diff;
            int value = scoreRanking.get(player.score) == null ? 1 : scoreRanking.get(player.score) + 1;
            scoreRanking.put(player.score, value);
        }
        for (String playerName : wrongs.get(strName)) {
            Player player = players.get(playerName);
            minusScoreRanking.put(player.minusScore, minusScoreRanking.get(player.minusScore) - 1);

            player.minusScore -= diff;
            int value = minusScoreRanking.get(player.minusScore) == null ? 1 : minusScoreRanking.get(player.minusScore) + 1;
            minusScoreRanking.put(player.minusScore, value);
        }
    }

    void attemptProblem(char player_name[], char problem_name[], int attempt_result) {
        String strPlayerName = new String(player_name).split("\0")[0];
        String strProblemName = new String(problem_name).split("\0")[0];
        if (attempt_result > 0) {
            corrects.get(strProblemName).add(strPlayerName);

            Player player = players.get(strPlayerName);
            scoreRanking.put(player.score, scoreRanking.get(player.score) - 1);

            player.score += problems.get(strProblemName);
            int value = scoreRanking.get(player.score) == null ? 1 : scoreRanking.get(player.score) + 1;
            scoreRanking.put(player.score, value);
        } else {
            wrongs.get(strProblemName).add(strPlayerName);

            Player player = players.get(strPlayerName);
            minusScoreRanking.put(player.minusScore, minusScoreRanking.get(player.minusScore) - 1);

            player.minusScore -= problems.get(strProblemName);
            int value = minusScoreRanking.get(player.minusScore) == null ? 1 : minusScoreRanking.get(player.minusScore) + 1;
            minusScoreRanking.put(player.minusScore, value);
        }
    }

    Result getRank(char player_name[]) {
        String strName = new String(player_name).split("\0")[0];
        Player player = players.get(strName);

        int currentRank = 0, bestRank = 0, worstRank = 0;
        for (int playerCount : scoreRanking.subMap(player.score + 1, Integer.MAX_VALUE).values()) {
            currentRank += playerCount;
        }
        for (int playerCount : scoreRanking.subMap(totalScore + player.minusScore + 1, Integer.MAX_VALUE).values()) {
            bestRank += playerCount;
        }
        for (int playerCount : minusScoreRanking.subMap(player.score - totalScore + 1, Integer.MAX_VALUE).values()) {
            worstRank += playerCount;
        }
        worstRank = totalScore == player.minusScore ? worstRank + 1 : worstRank;
        return new Result(currentRank + 1, bestRank + 1, worstRank);
    }
}
