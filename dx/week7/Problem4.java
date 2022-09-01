package dx.week7;

import java.util.HashMap;
import java.util.HashSet;

class Result {
    int count;
    String str;
}

class Contact {
    String[] data = new String[5];

    public Contact(String name, String number, String birthday, String email, String memo) {
        data[0] = name;
        data[1] = number;
        data[2] = birthday;
        data[3] = email;
        data[4] = memo;
    }
}

public class Problem4 {
    private int primaryKey;
    private HashMap<String, HashSet<Integer>>[] dataDB;
    private HashMap<Integer, Contact> contactDB;

    void InitDB() {
        primaryKey = 0;
        dataDB = new HashMap[5];
        for (int i = 0; i < 5; i++) {
            dataDB[i] = new HashMap<>();
        }
        contactDB = new HashMap<>();
    }

    void Add(String name, String number, String birthday, String email, String memo) {
        Contact contact = new Contact(name, number, birthday, email, memo);
        for (int i = 0; i < 5; i++) {
            if (!dataDB[i].containsKey(contact.data[i])) {
                dataDB[i].put(contact.data[i], new HashSet<>());
            }
            dataDB[i].get(contact.data[i]).add(primaryKey);
        }
        contactDB.put(primaryKey++, contact);
    }

    int Delete(int field, String str) {
        if (!dataDB[field].containsKey(str)) {
            return 0;
        }
        int count = dataDB[field].get(str).size();

        for (int key : dataDB[field].get(str)) {
            Contact contact = contactDB.get(key);
            for (int i = 0; i < 5; i++) {
                if (i == field) {
                    continue;
                }
                dataDB[i].get(contact.data[i]).remove(key);
            }
            contactDB.remove(key);
        }
        dataDB[field].put(str, new HashSet<>());
        return count;
    }

    int Change(int field, String str, int changefield, String changestr) {
        if (!dataDB[field].containsKey(str)) {
            return 0;
        }
        int count = dataDB[field].get(str).size();

        if (field == changefield) {
            if (!dataDB[changefield].containsKey(changestr)) {
                dataDB[changefield].put(changestr, new HashSet<>());
            }
            for (int key : dataDB[field].get(str)) {
                contactDB.get(key).data[changefield] = changestr;
                dataDB[changefield].get(changestr).add(key);
            }
            dataDB[field].put(str, new HashSet<>());
        } else {
            if (!dataDB[changefield].containsKey(changestr)) {
                dataDB[changefield].put(changestr, new HashSet<>());
            }
            for (int key : dataDB[field].get(str)) {
                dataDB[changefield].get(contactDB.get(key).data[changefield]).remove(key);
                dataDB[changefield].get(changestr).add(key);
                contactDB.get(key).data[changefield] = changestr;
            }
        }
        return count;
    }

    Result Search(int field, String str, int returnfield) {
        Result result = new Result();
        result.count = dataDB[field].containsKey(str) ? dataDB[field].get(str).size() : 0;
        if (result.count == 1) {
            for (int key : dataDB[field].get(str)) {
                result.str = contactDB.get(key).data[returnfield];
            }
        }
        return result;
    }
}
