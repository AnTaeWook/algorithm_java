package dx.week8;

import java.util.Arrays;
import java.util.HashMap;

class Post {
    int uID;
    int pID;
    int timestamp;
    int like;

    public Post(int uID, int pID, int timestamp, int like) {
        this.uID = uID;
        this.pID = pID;
        this.timestamp = timestamp;
        this.like = like;
    }
}

class UserSolution {
    Post[] posts;
    int postCount;
    HashMap<Integer, HashMap<Integer, Boolean>> follows;

    public void init(int N)
    {
        postCount = 0;
        posts = new Post[100001];
        follows = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            follows.put(i, new HashMap<>());
            follows.get(i).put(i, true);
        }
    }

    public void follow(int uID1, int uID2, int timestamp)
    {
        follows.get(uID1).put(uID2, false);
    }

    public void makePost(int uID, int pID, int timestamp)
    {
        posts[++postCount] = new Post(uID, pID, timestamp, 0);
    }

    public void like(int pID, int timestamp)
    {
        posts[pID].like += 1;
    }

    public void getFeed(int uID, int timestamp, int[] pIDList)
    {
        int postIndex = postCount;
        int pushCount = 0;
        Post[] returnValue = new Post[10];

        while (postIndex > 0 && timestamp - posts[postIndex].timestamp <= 1000) {
            if(follows.get(uID).containsKey(posts[postIndex].uID)){
                push(returnValue, posts[postIndex]);
                pushCount++;
            }
            postIndex--;
        }
        while (postIndex > 0 && pushCount < 10) {
            if(follows.get(uID).containsKey(posts[postIndex].uID)){
                returnValue[pushCount] = posts[postIndex];
                pushCount++;
            }
            postIndex--;
        }
        int limit = Math.min(pushCount, 10);
        for (int i = 0; i < limit; i++) {
            pIDList[i] = returnValue[i].pID;
        }
    }

    private void push(Post[] arr, Post post) {
        for (int i = 0; i < 10; i++) {
            if (arr[i] == null) {
                arr[i] = post;
                return;
            }
            if (arr[i].like < post.like || (arr[i].like == post.like && arr[i].timestamp < post.timestamp)) {
                for (int j = 9; j > i; j--) {
                    arr[j] = arr[j - 1];
                }
                arr[i] = post;
                return;
            }
        }
    }
}

public class Problem6 {
    public static void main(String[] args) {
        int[] result = new int[10];
        UserSolution sn = new UserSolution();
        sn.init(10);
        sn.follow(1, 2, 1);
        sn.follow(2, 1, 1);
        sn.getFeed(2, 534, result);
        System.out.println(Arrays.toString(result));
        sn.getFeed(2, 766, result);
        System.out.println(Arrays.toString(result));
        sn.getFeed(1, 1088, result);
        System.out.println(Arrays.toString(result));
        sn.makePost(1, 1, 1752);
        sn.like(1, 1861);
        sn.makePost(1, 2, 2027);
        sn.makePost(2, 3, 2117);
        sn.makePost(1, 4, 2163);
        sn.getFeed(2, 2467, result);
        System.out.println(Arrays.toString(result));
        sn.like(2, 2494);
        sn.like(1, 2542);
        sn.makePost(1, 5, 2666);
        sn.getFeed(2, 2853, result);
        System.out.println(Arrays.toString(result));
        sn.like(3, 2944);
        sn.getFeed(2, 3033, result);
        System.out.println(Arrays.toString(result));
    }
}
