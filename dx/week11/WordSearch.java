package dx.week11;

public class WordSearch {
    Trie trie;

    void init() {
        trie = new Trie();
    }

    int add(char str[]) {
        String word = new String(str).split("\0")[0];
        return trie.insert(word);
    }

    int remove(char str[]) {
        String word = new String(str).split("\0")[0];
        return trie.delete(word);
    }

    int search(char str[]) {
        String word = new String(str).split("\0")[0];
        return trie.find(word);
    }
}
