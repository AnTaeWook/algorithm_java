package skill;

import java.math.BigInteger;

public class GcdLcm {

    private static int getGcd(int n1, int n2) {
        BigInteger b1 = BigInteger.valueOf(n1), b2 = BigInteger.valueOf(n2);
        return b1.gcd(b2).intValue();
    }

    private static int getLcm(int n1, int n2) {
        return (n1 * n2) / getGcd(n1, n2);
    }

    public static void main(String[] args) {
        // 최대공약수, 최대공배수 구하기
        int n1 = 10, n2 = 15;
        System.out.println(getGcd(n1, n2) + " " + getLcm(n1, n2));
    }
}
