package dx.week12;

import java.io.*;

public class Problem1 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseCount = Integer.parseInt(reader.readLine());

        for (int i = 0; i < testCaseCount; i++) {
            String[] info = reader.readLine().split(" ");
            String[] numbers = reader.readLine().split(" ");
            long[] arr = new long[numbers.length + 1];
            MaxSegmentTree maxTree = new MaxSegmentTree(Integer.parseInt(info[0]));
            MinSegmentTree minTree = new MinSegmentTree(Integer.parseInt(info[0]));

            for (int j = 0; j < numbers.length; j++) {
                arr[j + 1] = Long.parseLong(numbers[j]);
            }
            maxTree.init(arr, 1, 1, arr.length - 1);
            minTree.init(arr, 1, 1, arr.length - 1);
            writer.write("#" + (i + 1) + " ");
            for (int j = 0; j < Integer.parseInt(info[1]); j++) {
                String[] order = reader.readLine().split(" ");
                if (order[0].equals("0")) {
                    maxTree.update(1, 1, arr.length - 1, Integer.parseInt(order[1]) + 1, Long.parseLong(order[2]));
                    minTree.update(1, 1, arr.length - 1, Integer.parseInt(order[1]) + 1, Long.parseLong(order[2]));
                } else {
                    writer.write(Math.toIntExact(maxTree.findMax(1, 1, arr.length - 1, Integer.parseInt(order[1]) + 1,Integer.parseInt(order[2]))
                    - minTree.findMin(1, 1, arr.length - 1, Integer.parseInt(order[1]) + 1,Integer.parseInt(order[2]))) + " ");
                }
            }
            writer.write("\n");
        }

        reader.close();
        writer.flush();
        writer.close();
    }
}
