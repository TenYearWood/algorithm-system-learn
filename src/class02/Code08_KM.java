package class02;

public class Code08_KM {

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

    public static void main(String[] args) {
        int[] arr = {4, 3, 1, 3, 3, 1, 1, 4};
        int k = 2;
        int m = 3;
        System.out.println(onlyKTimes(arr, k, m));
    }
}
