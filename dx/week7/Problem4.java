package dx.week7;

class Result {
    int count;
    String data;
}

public class Problem4 {
    String[][] db;
    boolean[] isValid;
    int dbIndex;

    void InitDB()
    {
        db = new String[50000][5];
        isValid = new boolean[50000];
        dbIndex = 0;
    }

    void Add(String name, String number, String birthday, String email, String memo)
    {
        db[dbIndex][0] = name;
        db[dbIndex][1] = number;
        db[dbIndex][2] = birthday;
        db[dbIndex][3] = email;
        db[dbIndex++][4] = memo;
    }

    int Delete(int field, String str)
    {
        int count = 0;
        for (int i = 0; i < dbIndex; i++) {
            if (db[i][field].equals(str) && isValid[i]) {
                isValid[i] = false;
                count++;
            }
        }
        return count;
    }

    int Change(int field, String str, int changefield, String changestr)
    {
        int count = 0;
        for (int i = 0; i < dbIndex; i++) {
            if (db[i][field].equals(str) && isValid[i]) {
                db[i][changefield] = changestr;
                count++;
            }
        }
        return count;
    }

    Result Search(int field, String str, int returnfield)
    {
        Result result = new Result();
        result.count = 0;
        for (int i = 0; i < dbIndex; i++) {
            if (db[i][field].equals(str) && isValid[i]) {
                result.data = db[i][returnfield];
                result.count++;
            }
        }
        if (result.count != 1) {
            result.data = "";
        }
        return result;
    }
}
