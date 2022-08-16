package dx.week12;

public class SumSegmentTree {
    private long[] tree;

    public SumSegmentTree(int n) {
        double treeHeight = Math.ceil(Math.log(n)/Math.log(2)) + 1;
        long treeNodeCount = Math.round(Math.pow(2, treeHeight));
        tree = new long[Math.toIntExact(treeNodeCount)];
    }

    public long init(long[] arr, int node, int start, int end) {
        if (start == end) {
            return tree[node] = arr[start];
        } else {
            return tree[node] = init(arr, node * 2, start, (start + end) / 2)
                    + init(arr, node * 2 + 1, (start + end) / 2 + 1, end);
        }
    }

    long update(int node, int start, int end, int index, long changeValue) {
        if (index < start || end < index) {
            return tree[node];
        } else if (start == index && end == index) {
            return tree[node] = changeValue;
        } else {
            return tree[node] = update(node * 2, start, (start + end) / 2, index, changeValue)
                    + update(node * 2 + 1, (start + end) / 2 + 1, end, index, changeValue);
        }
    }

    long findSum(int node, int start, int end, int left, int right) {
        if (end < left || right < start) {
            return 0;
        } else if (left <= start && end <= right) {
            return tree[node];
        } else {
            return findSum(node * 2, start, (start + end) / 2, left, right)
                    + findSum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
        }
    }

    void show() {
        for (int i = 1; i < tree.length; i++) {
            System.out.print(tree[i] + " ");
        }
        System.out.println();
    }
}
