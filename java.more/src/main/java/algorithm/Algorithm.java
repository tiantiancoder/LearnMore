package algorithm;

/**
 * Created by wangtian9 on 2017/7/17.
 */
public class Algorithm {
    /**
     * 求数组的子数组之和的最大值
     *
     * @param array
     * @return
     */
    public int maxSubArraySum(int[] array) {
        int n = array.length;
        if (n == 1) {
            return array[0];
        }
        int[] start = new int[n];
        int[] all = new int[n];
        start[n - 1] = array[n - 1];
        all[n - 1] = array[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            start[i] = Math.max(array[i], start[i + 1] + array[i]);
            all[i] = Math.max(start[i], all[i + 1]);
        }
        return all[0];
    }

    /**
     * 数塔问题
     *
     * @param array
     * @return
     */
    public int maxTowerPath(int[][] array) {
        int[][] data = new int[array.length][array.length];
        for (int k = 0; k < array.length; k++) {
            data[array.length - 1][k] = array[array.length - 1][k];
        }
        for (int i = array.length - 2; i >= 0; i--) {
            for (int j = 0; j < array.length - 1; j++) {
                data[i][j] = Math.max(data[i + 1][j], data[i + 1][j + 1]) + array[i][j];
            }
        }
        return data[0][0];
    }

    /**
     * 01背包问题
     *
     * @param weight
     * @param value
     * @return
     */
    public int back(int[] weight, int[] value, int sumWeight) {
        int n = weight.length;
        int[][] back = new int[n + 1][sumWeight + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sumWeight; j++) {
                if (j >= weight[i - 1]) {
                    back[i][j] = Math.max(back[i - 1][j], back[i - 1][j - weight[i - 1]] + value[i - 1]);
                } else {
                    back[i][j] = back[i - 1][j];
                }
            }
        }
        return back[n][sumWeight];
    }

    /**
     * 最长递增子序列LIS
     *
     * @param array
     * @return
     */
    public int lis(int[] array) {
        if (array.length == 1) {
            return 1;
        }
        int[] data = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            data[i] = 1;
            for (int j = 0; j < i; j++) {
                if (array[j] < array[i] && data[j] + 1 > data[i]) {
                    data[i] = data[j] + 1;
                }
            }
        }
        return data[array.length - 1];
    }

    /**
     * 最长公共子序列(LCS)
     *
     * @param a
     * @param b
     * @return
     */
    public int lcs(int[] a, int[] b) {
        int[][] data = new int[a.length + 1][b.length + 1];
        for (int i = 1; i <= a.length; i++) {
            for (int j = 1; j <= b.length; j++) {
                if (a[i - 1] == b[j - 1]) {
                    data[i][j] = data[i - 1][j - 1] + 1;
                } else {
                    data[i][j] = Math.max(data[i - 1][j], data[i][j - 1]);
                }
            }
        }
        return data[a.length][b.length];
    }

    public static void main(String[] args) {
        Algorithm algorithm = new Algorithm();
        System.out.print(algorithm.back(new int[]{1, 2, 3}, new int[]{1, 2, 3}, 6));
    }
}
