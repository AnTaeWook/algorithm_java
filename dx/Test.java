package dx;

import java.util.*;
import java.util.stream.*;

public class Test {

    public static void main(String[] args) {
        LRU<String, String> cache = new LRU<>(5);
        cache.put("newYork", "yes");
    }


}

class LRU<K, V> extends LinkedHashMap<K, V> {
    private final int cacheSize;

    public LRU(int cacheSize) {
        super(cacheSize, 0.75f, true);
        this.cacheSize = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> entry) {
        return this.size() < cacheSize;
    }
}
