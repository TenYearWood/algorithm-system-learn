package class02;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Code08_KM {

    /**
     * 用来做对数器的对照方法
     * 保证这个一定是正确的，这里用hashMap词频统计来实现
     */
    public static int test(int[] arr, int k, int m) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
            } else {
                map.put(arr[i], 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == k) {
                return entry.getKey();
            }
        }
        return -1;
    }

    /**
     * 请保证arr中，只有一种数出现了K次，其他数都出现了M次。
     * <p>
     * 别看有两个for循环，但是下面的for循环次数是固定的，32次。所以时间复杂度是O(n)
     * 虽然申请了一个数组，但是长度也是固定32个。额外空间复杂度是O(1)
     */
    public static int onlyKTimes(int[] arr, int k, int m) {
        int[] t = new int[32];
        // t[0] 0位置的1出现了几个
        // t[i] i位置的1出现了几个
        for (int num : arr) {
            for (int i = 0; i <= 31; i++) {
                t[i] += (num >> i) & 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if (t[i] % m != 0) {    //在第i位上，有1
                ans |= (1 << i);
            }
        }
        return ans;
    }

    /**
     * 用来产生一个随机数组
     * @param maxKinds  出现多少种数
     * @param range 每个数的大小范围
     * @param k 出现k次
     * @param m 出现m次  k < m
     * @return
     */
    public static int[] randomArray(int maxKinds, int range, int k, int m) {
        int ktimeNum = randomNumber(range);
        //产生多少种数，最少2种
        int numKinds = (int) (Math.random() * maxKinds) + 2;
        //数组的长度, k * 1 + (numKinds - 1) * m
        int[] arr = new int[k + (numKinds - 1) * m];
        //先在数组中填充出现K次的数
        int index = 0;
        for (; index < k; index++) {
            arr[index] = ktimeNum;
        }
        //继续填充出现M次的数
        numKinds--;
        HashSet<Integer> set = new HashSet<>();
        set.add(ktimeNum);
        while (numKinds != 0) {
            int curNum = 0;
            do {
                curNum = randomNumber(range);
            } while (set.contains(curNum));
            set.add(curNum);
            numKinds--;
            for (int i = 0; i < m; i++) {
                arr[index++] = curNum;
            }
        }

        //arr 填好了，但是顺序太有规律了，进行调整一下
        // i位置的数，我想随机和j位置的数做交换
        for (int i = 0; i < arr.length; i++) {
            int j = (int) (Math.random() * arr.length);  // 0 ~ N -1
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        return arr;
    }

    /**
     * 产生一个 [-Range, +Range]之间的随机数
     */
    public static int randomNumber(int range) {
        return (int) ((range + 1) * Math.random()) - (int) ((range + 1) * Math.random());
    }

    public static void main(String[] args) {
        int[] array = {4, 3, 1, 3, 3, 1, 1, 4};
        int k1 = 2;
        int m1 = 3;
        //System.out.println(onlyKTimes(array, k1, m1));
        //System.out.println(test(array, k1, m1));

        //使用对数器进行测试
        int kinds = 10;     //测试数组中最多有10种数
        int range = 200;    //数组中数据大小范围 -200 ~ 200
        int testTimes = 100000;
        int max = 9;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int a = (int) (Math.random() * max) + 1;        //a  1 ~ 9
            int b = (int) (Math.random() * max) + 1;        //b  1 ~ 9
            int k = Math.min(a, b);
            int m = Math.max(a, b);
            if (k == m) {   // 确保K < M
                m++;
            }

            int[] arr = randomArray(kinds, range, k, m);
            int ans1 = test(arr, k, m);
            int ans2 = onlyKTimes(arr, k, m);
            if (ans1 != ans2) {
                System.out.println("出错了!");
            }
        }
        System.out.println("测试结束");
    }
}
