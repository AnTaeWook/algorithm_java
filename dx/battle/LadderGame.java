package dx.battle;

import java.util.TreeSet;

public class LadderGame {
    TreeSet<Integer>[] ladderMap;

    public void init() {
        ladderMap = new TreeSet[101];
        for (int i = 0; i < 101; i++) {
            ladderMap[i] = new TreeSet<>();
            ladderMap[i].add(Integer.MAX_VALUE);
            ladderMap[i].add(-1);
        }
    }

    public void add(int mX, int mY) {
        ladderMap[mX].add(mY);
    }

    public void remove(int mX, int mY) {
        ladderMap[mX].remove(mY);
    }

    public int numberOfCross(int mID) {
        int currentY = 0, crossCount = 0, currentX = mID;
        while (true) {
            int left = ladderMap[currentX - 1].higher(currentY);
            int right = ladderMap[currentX].higher(currentY);
            if (left > 1000000000 && right > 1000000000) {
                break;
            }
            if (left < right) {
                currentX--;
                currentY = left;
            } else {
                currentX++;
                currentY = right;
            }
            crossCount++;
        }
        return crossCount;
    }

    public int participant(int mX, int mY) {
        int currentY = mY, currentX = mX;
        while (true) {
            int left = ladderMap[currentX - 1].lower(currentY);
            int right = ladderMap[currentX].lower(currentY);
            if (left < 0 && right < 0) {
                break;
            }
            if (left > right) {
                currentX--;
                currentY = left;
            } else {
                currentX++;
                currentY = right;
            }
        }
        return currentX;
    }
}
