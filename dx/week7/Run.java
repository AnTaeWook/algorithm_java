package dx.week7;

public class Run {
    static char[] sc(String str){
        char[] charArr = str.toCharArray();
        return charArr;
    }

    public static void main(String[] args) {
        Problem6 secure = new Problem6();
        secure.init(15, sc("baaabbbeebbbeee"));
        System.out.println(secure.change(sc("baa"), sc("aba")));
        System.out.println(secure.change(sc("aaa"), sc("bba")));
        System.out.println(secure.change(sc("bbb"), sc("abb")));
        System.out.println(secure.change(sc("abb"), sc("zzz")));
        System.out.println(secure.change(sc("zze"), sc("zze")));
        char[] s = new char[15];
        secure.result(s);
        System.out.println(s);

//        Problem5 mail = new Problem5();
//        mail.init(10, 3);
//        mail.show();
//
//        char[] s = new char[200];
//        s[0] = 'h';
//        s[1] = ' ';
//        s[2] = 'g';
//        s[3] = '\0';
//        mail.sendMail(s, 0, 3, new int[]{0, 1, 2});
//        mail.show();
//
//        mail.sendMail(sc("test email abcd\0"), 0, 2, new int[]{2, 3});
//        mail.show();
//
//        mail.sendMail(sc("test key test aaa\0"), 1, 2, new int[]{0, 2});
//        mail.show();
//
//        System.out.println(mail.getCount(2));
//        mail.show();
//
//        System.out.println(mail.searchMail(2, sc("test\0")));
//        mail.show();
//
//        System.out.println(mail.deleteMail(2, sc("test email abcd\0")));
//        mail.show();
//
//        mail.sendMail(sc("key subject\0"), 1, 2, new int[]{0, 1});
//        mail.show();
//
//        System.out.println(mail.searchMail(0, sc("abcd\0")));
//        mail.show();
//
//        mail.sendMail(sc("subject email\0"), 2, 2, new int[]{0, 3});
//        mail.show();
//
//        System.out.println(mail.searchMail(0, sc("sub\0")));
//        mail.show();
//
//        System.out.println(mail.deleteMail(2, sc("dummy age\0")));
//        mail.show();
//
//        System.out.println(mail.searchMail(0, sc("goto\0")));
//        mail.show();
    }
}
