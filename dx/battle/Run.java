package dx.battle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Run {
    private final static int CMD_INIT = 1;
    private final static int CMD_BUY = 2;
    private final static int CMD_CANCEL = 3;
    private final static int CMD_SELL = 4;
    private final static int CMD_REFUND = 5;

    private final static MarketManagement usersolution = new MarketManagement();

    private static boolean run(BufferedReader br) throws Exception {
        int q = Integer.parseInt(br.readLine());

        int id, product, price, quantity;
        int cmd, ans, ret = 0;
        boolean okay = false;

        for (int i = 0; i < q; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            cmd = Integer.parseInt(st.nextToken());
            switch (cmd) {
                case CMD_INIT:
                    usersolution.init();
                    okay = true;
                    break;
                case CMD_BUY:
                    id = Integer.parseInt(st.nextToken());
                    product = Integer.parseInt(st.nextToken());
                    price = Integer.parseInt(st.nextToken());
                    quantity = Integer.parseInt(st.nextToken());
                    ans = Integer.parseInt(st.nextToken());
                    ret = usersolution.buy(id, product, price, quantity);
                    System.out.println(id + " " + ans + " " + ret);
                    if (ret != ans){
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        okay = false;
                    }
                    break;
                case CMD_CANCEL:
                    id = Integer.parseInt(st.nextToken());
                    ans = Integer.parseInt(st.nextToken());
                    ret = usersolution.cancel(id);
                    System.out.println(id + " " + ans + " " + ret);
                    if (ret != ans){
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        okay = false;
                    }
                    break;
                case CMD_SELL:
                    id = Integer.parseInt(st.nextToken());
                    product = Integer.parseInt(st.nextToken());
                    price = Integer.parseInt(st.nextToken());
                    quantity = Integer.parseInt(st.nextToken());
                    ans = Integer.parseInt(st.nextToken());
                    ret = usersolution.sell(id, product, price, quantity);
                    System.out.println(id + " " + ans + " " + ret);
                    if (ret != ans){
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        okay = false;
                    }
                    break;
                case CMD_REFUND:
                    id = Integer.parseInt(st.nextToken());
                    ans = Integer.parseInt(st.nextToken());
                    ret = usersolution.refund(id);
                    System.out.println(id + " " + ans + " " + ret);
                    if (ret != ans){
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        okay = false;
                    }
                    break;
                default:
                    okay = false;
                    break;
            }
        }
        return okay;
    }

    public static void main(String[] args) throws Exception {
        int TC, MARK;

        //System.setIn(new java.io.FileInputStream("res/sample_input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        TC = Integer.parseInt(st.nextToken());
        MARK = Integer.parseInt(st.nextToken());

        for (int testcase = 1; testcase <= TC; ++testcase) {
            int score = run(br) ? MARK : 0;
            System.out.println("#" + testcase + " " + score);
        }

        br.close();
    }
}