package dx.week7;

import java.util.ArrayList;
import java.util.HashMap;

class Num {
    int data;
    Num next;

    public Num(int data) {
        this.data = data;
        next = null;
    }
}

public class Problem6 {
    HashMap<String, Num> dictionary;
    HashMap<Integer, Boolean> isRemoved;
    HashMap<Integer, Boolean> isAdded;
    char[] stringData;

    void init(int N, char[] init_string) {
        dictionary = new HashMap<>();
        stringData = new char[N];

        for (char a = 'a'; a <= 'z'; a++) {
            for (char b = 'a'; b <= 'z'; b++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    dictionary.put(Character.toString(a) + b + c, new Num(-1));
                }
            }
        }
        System.arraycopy(init_string, 0, stringData, 0, N);
        for (int i = 0; i < N - 2; i++) {
            initAdd(Character.toString(stringData[i]) + stringData[i + 1] + stringData[i + 2], i);
        }
    }

    int change(char[] string_A, char[] string_B) {
        ArrayList<Integer> indexForModify = new ArrayList<>();
        Num temp = dictionary.get(new String(string_A).split("\0")[0]).next;
        isRemoved = new HashMap<>();
        isAdded = new HashMap<>();

        int leastIndex = -3;
        while (temp != null) {
            if (temp.data - leastIndex > 2) {
                indexForModify.add(temp.data);
                leastIndex = temp.data;
            }
            temp = temp.next;
        }

        for (Integer modify : indexForModify) {
            for (int j = -2; j <= 2; j++) {
                if (modify + j < 0 || modify + j > stringData.length - 3) {
                    continue;
                }
                if (!isRemoved.containsKey(modify + j)) {
                    remove(Character.toString(stringData[modify + j]) +
                            stringData[modify + j + 1] + stringData[modify + j + 2], modify + j);
                    isRemoved.put(modify + j, true);
                }
            }
        }

        for (Integer modify : indexForModify) {
            for (int i = 0; i < 3; i++) {
                stringData[modify + i] = string_B[i];
            }
        }

        for (Integer modify : indexForModify) {
            for (int j = -2; j <= 2; j++) {
                if (modify + j < 0 || modify + j > stringData.length - 3) {
                    continue;
                }
                if (!isAdded.containsKey(modify + j)) {
                    add(Character.toString(stringData[modify + j]) +
                            stringData[modify + j + 1] + stringData[modify + j + 2], modify + j);
                    isAdded.put(modify + j, true);
                }
            }
        }
        return indexForModify.size();
    }

    void result(char[] ret) {
        System.arraycopy(stringData, 0, ret, 0, stringData.length);
    }

    private void initAdd(String code, int index) {
        Num temp = dictionary.get(code);
        while (true) {
            if (temp.next == null) {
                temp.next = new Num(index);
                return;
            }
            temp = temp.next;
        }
    }

    private void remove(String code, int index) {
        Num temp = dictionary.get(code);
        while (temp.next != null) {
            if (temp.next.data == index) {
                temp.next = temp.next.next;
                return;
            }
            temp = temp.next;
        }
    }

    private void add(String code, int index) {
        Num temp = dictionary.get(code);
        Num tempNext;
        while (true) {
            if (temp.next == null || temp.next.data > index) {
                tempNext = temp.next;
                temp.next = new Num(index);
                temp.next.next = tempNext;
                return;
            }
            temp = temp.next;
        }
    }
}
