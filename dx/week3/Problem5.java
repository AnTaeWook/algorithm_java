package dx.week3;

public class Problem5 {
    public static char[] sc(String str){
        char[] charArr = str.toCharArray();
        return charArr;
    }

    public static void main(String[] args) {
        DST dst = new DST();
        dst.init(11);
        dst.cmd_mkdir(sc("/"), sc("aa"));
        dst.cmd_mkdir(sc("/"), sc("bb"));
        dst.cmd_mkdir(sc("/aa/"), sc("cc"));
        dst.cmd_mkdir(sc("/bb/"), sc("dd"));
        dst.cmd_cp(sc("/bb/"), sc("/aa/"));
        dst.cmd_mv(sc("/aa/cc/"), sc("/"));
        System.out.println(dst.cmd_find(sc("/")));
        dst.cmd_mv(sc("/bb/"), sc("/cc/"));
        System.out.println(dst.cmd_find(sc("/cc/")));
        dst.cmd_rm(sc("/cc/"));
        System.out.println(dst.cmd_find(sc("/")));
    }
}
