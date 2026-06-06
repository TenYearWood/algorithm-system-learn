package class02;

import java.util.Arrays;


public class Code07_EvenTimesOddTimes {

    /**
     * arr中，有两种数，出现奇数次
     * 找到这两个数：
     */
    public static int[] printOddTimesNum2(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^=  arr[i];
        }
        //eor = a ^ b
        //eor != 0
        //eor必然有一个位置上是1
        //0110010000
        //0000010000
        int rightOne = eor & (~eor + 1);    //提取出最右的1
        int onlyOne = 0; //eor'
        for (int i = 0; i < arr.length; i++) {
            //arr[i]  = 111100011110000
            //rightOne= 000000000010000
            if ((arr[i] & rightOne) != 0) {
                onlyOne ^= arr[i];
            }
        }

        return new int[]{onlyOne, eor ^ onlyOne};
    }

    public static void main(String[] args) {
        int[] arr = {6, 6, 6, 10, 4, 4, 12, 12, 12, 12, 3, 3};
        int[] result = printOddTimesNum2(arr);
        System.out.println(Arrays.toString(result));
    }
}
