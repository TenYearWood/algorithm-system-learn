package class01;

/**
 * @description class01
 * @author: chengyu
 * @date: 2026-05-01 10:49
 */
public class Code04_BSExist {

    /**
     * 二分法查找
     * mid = L + ((R - L) >> 1);为什么这么写，加入L和R是一个很大的值，L=19亿，R=20亿，(L + R) / 2 如果这么算的话，
     * L + R 是会造成溢出。
     *
     * mid还可以这么表示：
     * mid = L + (R - L) / 2
     * mid = L + ((R - L) >> 1)
     * >> 1 表示右移1位，带符号右移，相当于/2
     * 所以一个数，不管是正还是负，它除2，只用带符号右移1位就可以了。
     *
     * 这么写肯定不会溢出了，R不会溢出，那么L + (R - L) / 2，L + 一半的位置，肯定也不会溢出。
     */
    public static boolean exist(int[] sortedArr, int num) {
        if (sortedArr == null || sortedArr.length == 0) {
            return false;
        }
        int L = 0;
        int R = sortedArr.length - 1;
        int mid = 0;
        // L..R
        while (L < R) { // L..R 至少两个数的时候
            mid = L + ((R - L) >> 1);
            if (sortedArr[mid] == num) {
                return true;
            } else if (sortedArr[mid] > num) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return sortedArr[L] == num;
    }
}
