package skill;

public class StringControl {
    public static void main(String[] args) {

        // 문자가 알파벳인지(숫자인지) 알아내기
        System.out.println(Character.isAlphabetic('A'));
        System.out.println(Character.isDigit('5'));

        // StringBuilder 사용하기
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('a');
        stringBuilder.append('b');
        stringBuilder.deleteCharAt(0);
        System.out.println(stringBuilder.toString());

        // 정규식 표현과 replaceAll 사용하기
        String s = "1j231jh2g3*(12";
        System.out.println(s.replaceAll("1j", "xx"));
        System.out.println(s.replaceAll("[^0-9]", "x"));
        System.out.println(s.replaceAll("[*(]", "x"));
        System.out.println(s.replaceAll("[a-z]", "x"));
    }
}
