package dx.battle;

import java.util.HashMap;

class Soldier {
    int id;
    int team;
    int score;

    public Soldier(int id, int team, int score) {
        this.id = id;
        this.team = team;
        this.score = score;
    }

    public void updateScore(int score) {
        this.score = score;
    }

    public void addScore(int change) {
        score += change;
        if (score > 5) {
            score = 5;
        } else if (score < 1) {
            score = 1;
        }
    }
}

public class SoldierDBMS {
    HashMap<Integer, HashMap<Integer, Soldier>> db;
    HashMap<Integer, Integer> teamMileStone;
    HashMap<Integer, Integer> changeScorePerTeam;

    void init() {
        db = new HashMap<>();
        teamMileStone = new HashMap<>();
        changeScorePerTeam = new HashMap<>();
        for (int i = 1; i < 6; i++) {
            db.put(i, new HashMap<>());
            changeScorePerTeam.put(i, 0);
        }
    }

    void hire(int mID, int mTeam, int mScore) {
        int deviation = changeScorePerTeam.get(mTeam);
        db.get(mTeam).put(mID, new Soldier(mID, mTeam, mScore - deviation));
        teamMileStone.put(mID, mTeam);
    }

    void fire(int mID) {
        db.get(teamMileStone.get(mID)).remove(mID);
    }

    void updateSoldier(int mID, int mScore) {
        int deviation = changeScorePerTeam.get(teamMileStone.get(mID));
        db.get(teamMileStone.get(mID)).get(mID).updateScore(mScore - deviation);
    }

    void updateTeam(int mTeam, int mChangeScore) {
        int score = changeScorePerTeam.get(mTeam);
        changeScorePerTeam.put(mTeam, score + mChangeScore);
    }

    void update(int mTeam) {
        int score = changeScorePerTeam.get(mTeam);
        for (Soldier s : db.get(mTeam).values()) {
            s.addScore(score);
        }
        changeScorePerTeam.put(mTeam, 0);
    }

    int bestSoldier(int mTeam) {
        update(mTeam);
        int bestSoldierId = -1, bestScore = -1;
        for (Soldier s : db.get(mTeam).values()) {
            if (s.score > bestScore) {
                bestScore = s.score;
                bestSoldierId = s.id;
            } else if (s.score == bestScore) {
                bestSoldierId = Math.max(bestSoldierId, s.id);
            }
        }
        return bestSoldierId;
    }

    void show() {
        int count = 1;
        for (HashMap<Integer, Soldier> team : db.values()) {
            System.out.println("----team " + count + " ----");
            for (Soldier s : team.values()) {
                System.out.print(s.id + " " + s.score + " | ");
            }
            System.out.println();
        }
    }
}
