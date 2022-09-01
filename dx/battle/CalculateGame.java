package dx.battle;

import java.util.ArrayDeque;
import java.util.Deque;

class Deck {
    public int[] deck;

    public Deck(int[] deck) {
        this.deck = new int[4];
        System.arraycopy(deck, 0, this.deck, 0, 4);
    }
}

public class CalculateGame {
    private Deque<Deck>[][] cards;
    private int joker;
    int[] left;
    int[] right;
    int[] tempArray;

    void init(int mJoker, int mNumbers[]) {
        joker = mJoker;
        cards = new Deque[31][20];
        left = new int[5];
        right = new int[5];
        tempArray = new int[10];

        for (int i = 1; i < 31; i++) {
            for (int j = 0; j < 20; j++) {
                cards[i][j] = new ArrayDeque<>();
            }
        }
        for (int i = 1; i < 31; i++) {
            for (int j = 0; j < 2; j++) {
                int sum = 0;
                int[] deck = new int[4];
                for (int k = j; k < j + 4; k++) {
                    deck[k - j] = mNumbers[k];
                    if (mNumbers[k] < 0) {
                        sum += i;
                    } else {
                        sum += mNumbers[k];
                    }
                }
                cards[i][sum % 20].add(new Deck(deck));
            }
        }
        System.arraycopy(mNumbers, 0, left, 0, 5);
        System.arraycopy(mNumbers, 0, right, 0, 5);
    }

    void putCards(int mDir, int mNumbers[]) {
        if (mDir == 0) {
            for (int i = 0; i < 5; i++) {
                tempArray[i] = mNumbers[i];
                tempArray[i + 5] = left[i];
            }
            for (int i = 1; i < 31; i++) {
                for (int j = 4; j >= 0; j--) {
                    int sum = 0;
                    int[] deck = new int[4];
                    for (int k = j; k < j + 4; k++) {
                        deck[k - j] = tempArray[k];
                        if (tempArray[k] < 0) {
                            sum += i;
                        } else {
                            sum += tempArray[k];
                        }
                    }
                    cards[i][sum % 20].addFirst(new Deck(deck));
                }
            }
            System.arraycopy(mNumbers, 0, left, 0, 5);
        } else {
            for (int i = 0; i < 5; i++) {
                tempArray[i] = right[i];
                tempArray[i + 5] = mNumbers[i];
            }
            for (int i = 1; i < 31; i++) {
                for (int j = 2; j < 7; j++) {
                    int sum = 0;
                    int[] deck = new int[4];
                    for (int k = j; k < j + 4; k++) {
                        deck[k - j] = tempArray[k];
                        if (tempArray[k] < 0) {
                            sum += i;
                        } else {
                            sum += tempArray[k];
                        }
                    }
                    cards[i][sum % 20].add(new Deck(deck));
                }
            }
            System.arraycopy(mNumbers, 0, right, 0, 5);
        }
    }

    int findNumber(int mNum, int mNth, int ret[]) {
        if (cards[joker][mNum].size() < mNth) {
            return 0;
        }
        int count = 0;
        for (Deck d : cards[joker][mNum]) {
            count++;
            if (count == mNth) {
                System.arraycopy(d.deck, 0, ret, 0, 4);
                return 1;
            }
        }
        return 0;
    }

    void changeJoker(int mValue) {
        joker = mValue;
    }
}
