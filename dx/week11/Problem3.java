package dx.week11;

public class Problem3 {
    public static void main(String[] args) {
        WordSearch ws = new WordSearch();
        ws.init();
        System.out.println(ws.add(ss("about")));
        System.out.println(ws.add(ss("about")));
        System.out.println(ws.add(ss("above")));
        System.out.println(ws.search(ss("abo??")));
        System.out.println(ws.add(ss("admit")));
        System.out.println(ws.add(ss("adult")));
        System.out.println(ws.search(ss("ad??t")));
        System.out.println(ws.add(ss("amount")));
        System.out.println(ws.search(ss("a???t")));
        System.out.println(ws.remove(ss("a?o?n?")));
        System.out.println(ws.add(ss("avoid")));
        System.out.println(ws.search(ss("a?o??")));
        System.out.println(ws.remove(ss("about")));
        System.out.println(ws.search(ss("a?o??")));
        System.out.println(ws.add(ss("visit")));
        System.out.println(ws.search(ss("???it")));
        System.out.println(ws.search(ss("?c?d?")));
    }

    static char[] ss(String str) {
        return str.toCharArray();
    }
}
