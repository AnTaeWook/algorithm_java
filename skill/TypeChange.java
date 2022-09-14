package skill;

public class TypeChange {
    public static void main(String[] args) {
        // String 에서 int
        String string = "12345";
        int decimal = Integer.parseInt(string);
        System.out.println(decimal);

        // String 에서 int(2진수 혹은 3진수 등)
        String binary = "1011";
        System.out.println(Integer.parseInt(binary, 2));

        // char 에서 int
        char number = '2';
        decimal = Character.getNumericValue(number);
        System.out.println(decimal);

        // int 에서 String
        decimal = 15;
        System.out.println(Integer.toString(decimal));
        System.out.println("" + decimal);

        // decimal 에서 binary
        System.out.println(Integer.toBinaryString(25));
        
        // decimal 에서 n 진수 표현
        System.out.println(Integer.toString(10, 3));
    }
}
