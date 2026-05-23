package class01;

/**
 * @description 在一个有序数组中，找>=某个数最左侧的位置。
 * @author: chengyu
 * @date: 2026-05-01 11:16
 */
public class Code05_BSNearLeft {

    /**
     * 在arr上，找满足>=value的最左位置
     */
    public static int nearestIndex(int[] sortedArr, int num) {
        int L = 0;
        int R = sortedArr.length - 1;
        int index = -1;     // 记录最左的对号

        while (L <= R) {    // 至少一个数的时候
            int mid = L + ((R - L) >> 1);
            if (sortedArr[mid] >= num) {
                index = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        mytest();
    }

    public static void mytest() {
        int[] arr = {0, 1, 2, 3, 3, 4, 5, 8, 9, 12, 15};
        int index = nearestIndex(arr, 20);
        System.out.println("找到最左边>=数为下标:" + index);
    }
}
