package datastructure;

import java.util.Scanner;

/**
 * ���ҷ�����
 *
 * @author liangxiamoyi
 */
public class Search {
    /**
     * ˳�����
     *
     * @param r �������ļ�
     * @param n ����
     * @param k ���ҵ�Ԫ��
     * @return �ɹ�����k���ڵ�λ�ã����ɹ�����0
     */
    public static int seqSearch(Element[] r, int n, int k) {
        int i = 1;
        while (i <= n && r[i].getKey() != k) {
            i++;
        }
        if (i > n) return 0;
        return i;
    }

    /**
     * <p>���ٲ��ң�����һ�������⡱��¼r[n+1],����㷨����Ч��</p>
     * <p>����ԭ�����㷨��һ����ѭ��Ҫ����������������ʱ��Ӧ��ͼ������ٳ�һ������</p>
     * <p>�㷨quickSearch��seqSearch��ʡ20%������ʱ��</p>
     *
     * @param r �������ļ�
     * @param n ����
     * @param k ����Ԫ��
     * @return �ɹ�����k���ڵ�λ�ã����ɹ�����0
     */
    public static int quickSearch(Element[] r, int n, int k) {
        int i = 1;
        r[n + 1].setKey(k);
        while (r[i].getKey() != k) {
            i++;
        }
        if (i <= n) return i;
        return 0;
    }

    /**
     * <p>�����ٵĲ���</p>
     * <p>�㷨quickerSearch��quickSearch�ܱȽϴ���������10%������ʱ�������30%</p>
     *
     * @param r �������ļ�
     * @param n ����
     * @param k ����Ԫ��
     * @return �ɹ�����k��λ�ã����ɹ�����0
     */
    public static int quickerSearch(Element[] r, int n, int k) {
        int i = -1;
        r[n + 1].setKey(k);
        i += 2;
        while (r[i].getKey() != k) {
            if (r[i + 1].getKey() != k) i += 2;
            else {
                i++;
                break;
            }
        }
        if (i <= n) return i;
        else return 0;
    }

    /**
     * <p>������˳����ң��趨һ�������¼r[n+1]=MAX_VALUE</p>
     * <p>���㷨�ܸ����ȷ��һ����¼������</p>
     *
     * @param r �����ҵ��ļ�
     * @param n ����
     * @param k ����Ԫ��
     * @return �ɹ�����k��λ�ã����ɹ�����0
     */
    public static int sortedSearch(Element[] r, int n, int k) {
        int i = 1;
        r[n + 1].setKey(Integer.MAX_VALUE);
        while (r[i].getKey() < k) {
            i++;
        }
        if (r[i].getKey() == k) return i;
        else return 0;
    }

    /**
     * �԰����
     *
     * @param r �������ļ�
     * @param n ����
     * @param k ����Ԫ��
     * @return �ɹ�����k��λ�ã����ɹ�����0
     */
    public static int binSearch(Element[] r, int n, int k) {
        int i, s = 1, e = n;
        while (s <= e) {
            i = (s + e) / 2;
            if (k < r[i].getKey()) e = i - 1;
            if (k > r[i].getKey()) s = i + 1;
            if (k == r[i].getKey()) return i;
        }
        return 0;
    }

    /**
     * һ�¶԰���ң���nΪż��,���㷨��ʱ���漰һ��r[0]=MIN_VALUE
     * <p>����ڶ԰���ң��������α��Ϊ�����α�</p>
     *
     * @param r �������ļ�
     * @param n ����
     * @param k ����Ԫ��
     * @return �ɹ�����k��λ�ã����ɹ�����0
     */
    public static int unanBinSearch(Element[] r, int n, int k) {
        int i = (n + 1) / 2;
        int m = n / 2;
        r[0].setKey(Integer.MIN_VALUE);
        while (k != r[i].getKey()) {
            if (k < r[i].getKey()) {
                if (m == 0) return 0;
                else {
                    i -= (m + 1) / 2;
                    m = m / 2;
                }
            }
            if (k > r[i].getKey()) {
                if (m == 0) return 0;
                else {
                    i += (m + 1) / 2;
                    m = m / 2;
                }
            }
        }
        return i;
    }

    /**
     * ����delta����
     *
     * @param delta ������
     * @param n     ����
     */
    public static void calDelta(int[] delta, int n) {
        int k = (int) (Math.log(n) / Math.log(2)) + 2;
        int s = 1;//�ۻ��洢2��j-1�η�����ʼΪ2��0�η�����Ϊ1
        for (int j = 1; j <= k; j++) {
            delta[j] = (n + s) / (s * 2);
            s *= 2;
        }
    }

    /**
     * ʹ�ø������һ�¶԰����
     * <p>���㷨���㷨unanBinSearch���ƣ�����ʹ��һ���������������漰m�ļ���</p>
     * <p>�����������DELTA[j]=(N+2^(j-1))/2^j����ȡ��</p>
     *
     * @param r     �������ļ�
     * @param n     ����
     * @param k     ����Ԫ��
     * @param delta ������
     * @return �ɹ�����k��λ�ã����ɹ�����0
     */
    public static int conBinSearch(Element[] r, int n, int k, int[] delta) {
        int i = delta[1], j = 2;
        while (k != r[i].getKey()) {
            if (k < r[i].getKey()) {
                if (delta[j] == 0) return 0;
                else {
                    i -= delta[j];
                    j++;
                }
            }
            if (k > r[i].getKey()) {
                if (delta[j] == 0) return 0;
                else {
                    i += delta[j];
                    j++;
                }
            }
        }
        return i;
    }

    /**
     * ����쳲���������
     *
     * @param f �洢���е�����
     * @param n �ļ�����
     */
    public static void fibonacci(int[] f, int n) {
        f[0] = 0;
        f[1] = 1;
        f[2] = 1;
        int i = 2;
        while (f[i] < n) {
            f[i + 1] = f[i] + f[i - 1];
            i = i + 1;
        }
    }

    /**
     * 쳲��������ң��㷨�ٶ�n+1��һ��쳲�����������n+1=f[m+1]
     * <p>T(k)��ʾk��쳲�����������k=0��1���������0����k>=2;����T(k)��Ϊf[k].</p>
     * <p>������ΪT[k-1],������Ϊ����Ϊk-2�����н��֮��Ŷ�����f[k]��쳲�������</p>
     *
     * @param r   �������ļ�
     * @param m   ����
     * @param k   ����Ԫ��
     * @param fib ���Fibonacci���е�����
     * @return �ɹ�����k��λ�ã����ɹ�����0
     */
    public static int fibSearch(Element[] r, int m, int k, int[] fib) {
        int i = fib[m], p = fib[m - 1], q = fib[m - 2];
        int t;
        while (k != r[i].getKey()) {
            if (k < r[i].getKey()) {
                if (q == 0) return 0;
                else {
                    i -= q;
                    t = p;
                    p = q;
                    q = t - p;
                }
            }
            if (k > r[i].getKey()) {
                if (p == 1) return 0;
                else {
                    i += q;
                    p -= q;
                    q -= p;
                }
            }
        }
        return i;
    }

    /**
     * ��ֵ���ң����йؼ�����Ϊ��������(k[0],k[n+1])֮����ȷֲ�
     *
     * @param r �������ļ�
     * @param n ����
     * @param k ����Ԫ��
     * @return �ɹ�����k��λ�ã����ɹ�����0
     */
    public static int interSearch(Element[] r, int n, int k) {
        int s = 0, e = n + 1;//s��e��ʾ��ǰ�����ӱ�����޺�����
        int i;
        while (e - s > 1) {
            //k������ֵ
            i = (int) (Math.ceil(s + 1.0 * (k - r[s].getKey()) / (r[e].getKey() - r[s].getKey()) * (e - s - 1)));
            if (k < r[i].getKey()) e = i;
            if (k > r[i].getKey()) s = i;
            if (k == r[i].getKey()) return i;
        }
        return 0;
    }

    public static void main(String[] args) {
        int num;
        Scanner s = new Scanner(System.in);
        System.out.println("�������ļ�������");
        num = s.nextInt();
        Element[] e = new Element[num + 2];
        System.out.println("�����������ļ��ؼ��ʣ�");
        e[0] = new Element();
        e[0].setKey(0);
        e[num + 1] = new Element();
        e[num + 1].setKey(13);
        for (int i = 1; i < num + 1; i++) {
            e[i] = new Element();
            e[i].setKey(s.nextInt());
        }
        System.out.println("��������Ҫ���ҵ��ļ��ؼ��ʣ�");
        System.out.println(Search.interSearch(e, num, s.nextInt()));
        System.out.println(Search.seqSearch(e, num, s.nextInt()));
        System.out.println(Search.quickSearch(e, num, s.nextInt()));
        System.out.println(Search.quickerSearch(e, num, s.nextInt()));
        System.out.println(Search.sortedSearch(e, num, s.nextInt()));
        System.out.println(Search.binSearch(e, num, s.nextInt()));
        System.out.println(Search.unanBinSearch(e, num, s.nextInt()));
        int[] delta = new int[num];
        Search.calDelta(delta, num);
        System.out.println(Search.conBinSearch(e, num, s.nextInt(), delta));
        int[] fib = new int[num];
        Search.fibonacci(fib, num);
        int m = 0;
        while (fib[m + 1] - 1 != num) {
            m++;
        }
        System.out.println(Search.fibSearch(e, m, s.nextInt(), fib));
    }
}
