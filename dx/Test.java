package dx;

import java.util.*;
import java.util.stream.*;

public class Test {

    public static void main(String[] args) {
        String[] arr = {"asd", "AS"};
        Set<String> collect = Arrays.stream(arr).collect(Collectors.toSet());
        System.out.println(collect.getClass());
    }

}
