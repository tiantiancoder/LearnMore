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

    /**
     * 快速排序
     *
     * @param array
     */
    public static void quickSort(int[] array, int low, int high) {
        int l = low;
        int h = high;
        int p = array[low];
        if (low >= high) return;
        while (l < h) {
            while (l < h && array[h] >= p)
                h--;
            while (l < h && array[l] <= p)
                l++;
            if (l < h) {
                int temp = array[h];
                array[h] = array[l];
                array[l] = temp;
            }
        }
        array[low] = array[h];
        array[h] = p;
        if (h > 0) {
            quickSort(array, low, h - 1);
        }
        if (h < array.length - 1) {
            quickSort(array, h + 1, high);
        }
    }


    public static int selectMidOfThree(int[] array, int low, int high) {
        int mid = (low + high) / 2;
        if (array[mid] > array[high]) {
            int temp = array[high];
            array[high] = array[mid];
            array[mid] = temp;
        }
        if (array[low] > array[high]) {
            int temp = array[high];
            array[high] = array[low];
            array[low] = temp;
        }
        if (array[mid] > array[low]) {
            int temp = array[low];
            array[low] = array[mid];
            array[mid] = temp;
        }
        return array[low];
    }

    public static void optimizedQuickSort(int[] array, int low, int high) {
        if (high - low + 1 < 10) {
            insertSort(array);
            return;
        }
        int l = low;
        int h = high;
        int p = selectMidOfThree(array, low, high);
        while (l < h) {
            while (l < h && array[h] >= p)
                h--;
            while (l < h && array[l] <= p)
                l++;
            if (l < h) {
                int temp = array[h];
                array[h] = array[l];
                array[l] = temp;
            }
        }
        array[low] = array[h];
        array[h] = p;
        if (h > 0) {
            quickSort(array, low, h - 1);
        }
        if (h < array.length - 1) {
            quickSort(array, h + 1, high);
        }
    }

    /**
     * 插入排序
     *
     * @param array
     */
    public static void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int item = array[i];
            int index = i - 1;
            while (index >= 0 && item < array[index]) {
                array[index + 1] = array[index];
                index--;
            }
            if (index < 0) {
                array[0] = item;
            } else {
                array[index] = item;
            }
        }
    }

    /**
     * 冒泡排序
     *
     * @param array
     */
    public static void bubble(int[] array) {
        int bound = array.length;
        while (bound > 0) {
            int t = 0;
            for (int i = 1; i < bound; i++) {
                if (array[i] < array[i - 1]) {
                    int item = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = item;
                    t = i;
                }
            }
            bound = t;
        }
    }

    /**
     * 选择排序
     *
     * @param array
     */
    public static void selectSort(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int t = 0;
            for (int j = 1; j <= i; j++) {
                if (array[t] < array[j]) {
                    t = j;
                }
            }
            int item = array[t];
            array[t] = array[i];
            array[i] = item;
        }
    }

    /**
     * 希尔排序，gap为增量，分组
     *
     * @param array
     */
    public static void shellSort(int[] array) {
        int gap = array.length;
        while (gap > 1) {
            gap = (gap + 1) / 2;
            for (int i = 0; i <= gap; i++) {
                for (int j = i + gap; j < array.length; j = j + gap) {
                    int item = array[j];
                    while (j > i && array[j] < array[j - gap]) {
                        array[j] = array[j - gap];
                        j = j - gap;
                    }
                    if (j <= i) {
                        array[i] = item;
                    } else {
                        array[j] = item;
                    }
                }
            }
        }
    }

    public static void merge(int[] sourceArray, int[] sortArray, int start, int bound, int end) {
        int index1 = start, index2 = bound + 1, index = start;
        while (index1 <= bound && index2 < end) {
            while (index1 <= bound && sourceArray[index1] <= sourceArray[index2]) {
                sortArray[index] = sourceArray[index1];
                index1++;
                index++;
            }
            while (index2 < end && sourceArray[index1] > sourceArray[index2]) {
                sortArray[index] = sourceArray[index2];
                index2++;
                index++;
            }
        }
        if (index1 <= bound) {
            for (int i = index1; i <= bound; i++) {
                sortArray[index] = sourceArray[i];
                index++;
            }
        } else {
            for (int i = index2; i < end; i++) {
                sortArray[index] = sourceArray[i];
                index++;
            }
        }
    }

    public static void mergePass(int[] sourceArray, int[] sortArray, int subLength) {
        int i = 0;
        for (i = 0; i <= sourceArray.length - 2 * subLength; i = i + 2 * subLength) {
            merge(sourceArray, sortArray, i, i + subLength - 1, i + 2 * subLength);
        }
        if (i + subLength < sourceArray.length) {
            merge(sourceArray, sortArray, i, i + subLength - 1, sourceArray.length);
        } else {
            for (int j = i; j < sourceArray.length; j++) {
                sortArray[j] = sourceArray[j];
            }
        }
    }

    /**
     * 归并排序
     *
     * @param array
     */
    public static void mergeSort(int[] array) {
        int[] sortArray = new int[array.length];
        for (int i = 1; i < array.length; i = i * 2) {
            mergePass(array, sortArray, i);
            i = i * 2;
            mergePass(sortArray, array, i);
        }
    }

    public static void restore(int[] array, int root, int end) {
        int j = root;
        int m;
        while (j <= end / 2 - 1) {
            if (j * 2 + 2 < end && array[j * 2 + 1] < array[j * 2 + 2]) {
                m = j * 2 + 2;
            } else {
                m = j * 2 + 1;
            }
            if (array[m] > array[j]) {
                int temp = array[m];
                array[m] = array[j];
                array[j] = temp;
                j = m;
            } else {
                j = end;
            }
        }
    }

    /**
     * 堆排序
     *
     * @param array
     */
    public static void heapSort(int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            restore(array, i, array.length);
        }
        for (int j = array.length - 1; j > 0; j--) {
            int temp = array[0];
            array[0] = array[j];
            array[j] = temp;
            restore(array, 0, j - 1);
        }
    }


    public static void main(String[] args) {
        Algorithm algorithm = new Algorithm();
        int[] a = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        algorithm.heapSort(a);
        for (int i = 0; i < a.length; i++)
            System.out.print(a[i]);
    }
}
