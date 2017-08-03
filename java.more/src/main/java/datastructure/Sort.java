package datastructure;
import java.util.Scanner;

/**
 * ���򷽷���
 * @author liangxiamoyi
 *
 */
public class Sort {
	/**
	 * ��Сֵ
	 */
	public static int MINKEY=-1000;
	/**
	 * ���ֵ
	 */
	public static int MAXKEY=1000;
	/**
	 * ֱ�Ӳ������򣬽�list����
	 * @param list �����������,1-n,list[0]С�ڵ�������list[1]-[n]
	 * @param n �����¼�ĸ���
	 */
	public static void insertSortA(Element[] list,int n){
		Element e=new Element();
		list[0].setKey(MINKEY);
		for(int j=2;j<=n;j++){
			e.setKey(list[j].getKey());
			int i=j-1;
			while(e.getKey()<list[i].getKey()){//list[0]��֤��ѭ��
				list[i+1].setKey(list[i].getKey());
				i--;
			}
			list[i+1].setKey(e.getKey());;
		}
	}
	/**
	 * ð�������㷨����n����¼��������r1,r2,....,rn
	 * @param r �����¼
	 * @param n ��¼����
	 */
	public static void bubble(Element[] r,int n){
		int bound,j,t;
		Element e=new Element();
		bound=n;
		while(bound>0){
			t=0;//t������¼һ��ð������¼������λ��
			for(j=1;j<bound;j++){
				if(r[j].getKey()>r[j+1].getKey()){
					interChange(r, j, j+1);
					t=j;
				}
			}
			bound=t;//bound֮��ļ�¼�Ѿ��ź����´�ð��ֻ�赽bound�����ٱȽϴ���
		}
	}
	/**
	 * ֱ��ѡ�������㷨�����㷨�����ļ�r1,r2,...,rn
	 * @param e �������ļ�����
	 * @param n �ļ�����
	 */
	public static void selectSort(Element[] e,int n){
		int t;
		Element m=new Element();
		for(int j=n;j>=2;j--){
			t=1;
			for(int i=2;i<=j;i++){
				if(e[t].getKey()<e[i].getKey()){
					t=i;
				}
			}
			//����i����ļ����ڵ�i��λ����
			m.setKey(e[t].getKey());
			e[t].setKey(e[j].getKey());
			e[j].setKey(m.getKey());
		}
	}
	/**
	 * <p>�Ľ��Ĳ��������㷨</p>
	 * <p>�Ѽ�¼���±��һ���������飬��ÿ��ʹ��ֱ�Ӳ������򷨡�</p>
	 * <p>���������𽥼��٣�ÿ������ļ�¼Խ��Խ�࣬����������1ʱ�������ļ�Ϊһ�飬�㷨��ֹ��</p>
	 * @param list �������ļ�
	 * @param n �ļ�����
	 */
	public static void shellSort(Element[] list,int n){
		int gap=n;//����
		int i;
		while(gap>=1){
			gap=gap/2;
			for(i=1;i<=gap;i++){//����
				Element e=new Element();
				int t;
				for(int j=i+gap;j<=n;j=j+gap){//ֱ�Ӳ�������
					e.setKey(list[j].getKey());
					t=j-gap;
					while(e.getKey()<list[t].getKey()){
						list[t+gap].setKey(list[t].getKey());
						t=t-gap;
						if(t<=i){
							break;
						}
					}
					list[t+gap].setKey(e.getKey());
				}
			}
		}
	}
	/**
	 * �����ź��������sourceList[t][t+1]...[m]��sourceList[m+1][m+2]...[n]�ϲ�Ϊһ������mergedList[t]...[n]
	 * @param sourceList
	 * @param mergedList
	 * @param t
	 * @param m
	 * @param n
	 */
	public static void merge(Element[] sourceList,Element[] mergedList,int t,int m,int n){
		int i,j,k;
		i=t;j=m+1;k=t;
		while(i<=m&&j<=n){
			if(sourceList[i].getKey()<=sourceList[j].getKey()){
				mergedList[k]=sourceList[i];
				i++;
			}
			else{
				mergedList[k]=sourceList[j];
				j++;
			}
			k++;
		}
		if(i>m){
			for(int p=k;p<=n;p++){
				mergedList[p]=sourceList[j+p-k];
			}
		}
		else{
			for(int p=k;p<=n;p++){
				mergedList[p]=sourceList[i+p-k];
			}
		}
	}
	/**
	 * һ�˺ϲ��㷨��ִ��һ�˺ϲ����̣���sourceList�ļ��г���Ϊlength���������ļ��ϲ���mergedList�ļ���
	 * @param sourceList Դ�ļ�
	 * @param mergedList Ŀ���ļ�
	 * @param n ԭ�ļ��м�¼����
	 * @param length ���ļ�����
	 */
	public static void mergePass(Element[] sourceList,Element[] mergedList,int n,int length){
		int j;
		for(j=1;j<=n-2*length+1;j+=2*length){
			//�ϲ����ڵ��������ļ�
			merge(sourceList,mergedList,j,j+length-1,j+2*length-1);
		}
		if(j+length-1<n){//���������ĳ���С��2*length�����ļ�
			merge(sourceList,mergedList,j,j+length-1,n);
		}
		else{
			for(int p=j;p<=n;p++){
				mergedList[p]=sourceList[p];
			}
		}
	}
	/**
	 * ֱ����·�ϲ������㷨
	 * @param list �������ļ�
	 * @param n �ļ�����
	 */
	public static void mergeSort(Element[] list,int n){
		Element[] tempList=new Element[n+1];
		for(int i=0;i<n+1;i++){
			tempList[i]=new Element();
		}
		for(int j=1;j<n;j*=2){//����ϲ�
			mergePass(list,tempList,n,j);
			j*=2;
			mergePass(tempList,list,n,j);
		}
	}
	/**
	 * ��������ĵݹ��㷨�����ļ�rm,...,rn��������
	 * @param r �������ļ�
	 * @param m ���
	 * @param n �յ�
	 */
	public static void recQuickSort(Element[] r,int m,int n){
		int i,j,k,temp;
		if(m<n){
			i=m;
			j=n+1;
			k=r[m].getKey();
			while(i<j){//���Ͻ��������
				i++;
				while(r[i].getKey()<k)i++;
				j--;
				while(r[j].getKey()>k)j--;
				if(i<j){
					temp=r[i].getKey();
					r[i].setKey(r[j].getKey());
					r[j].setKey(temp);
				}
			}
			temp=r[m].getKey();
			r[m].setKey(r[j].getKey());
			r[j].setKey(temp);
			recQuickSort(r, m, j-1);
			recQuickSort(r, j+1, n);
		}
	}
	/**
	 * ���������±�Ϊm��n��Ԫ��λ��
	 * @param list ��¼��
	 * @param m λ��m
	 * @param n λ��n
	 */
	public static void interChange(Element[] list,int m,int n){
		int temp=list[m].getKey();
		list[m].setKey(list[n].getKey());
		list[n].setKey(temp);
	}
	/**
	 * ����ȡ��ֵ�ķֻ��㷨
	 * @param list �ļ�
	 * @param m ���
	 * @param n �յ�
	 * @return �ֻ�Ԫ��������ڵ�λ��
	 */
	public static int part(Element[] list,int m,int n){
		int i,j,k;
		//��֤list[m].key��list[m],list[(m+n)/2],list[n]���м�ֵ
		interChange(list, (int)(m+n)/2, m+1);
		if(list[m+1].getKey()>list[n].getKey())interChange(list, m+1, n);
		if(list[m].getKey()>list[n].getKey())interChange(list, m, n);
		if(list[m+1].getKey()>list[m].getKey())interChange(list, m+1, m);
		i=m;j=n+1;
		k=list[m].getKey();
		//��������
		while(i<j){
			i++;
			while(list[i].getKey()<k)i++;
			j--;
			while(list[j].getKey()>k)j--;
			if(i<j)interChange(list, i, j);
		}
		interChange(list, m, j);
		return j;
	}
	/**
	 * <p>��������ķǵݹ��㷨����r1��...��rn��������,����m������5<=m<=15</p>
	 * <p>r����n+2����¼��r[0]=MINKEY,r[n+1]=MAXKEY</p>
	 * @param n ��¼����
	 * @param r �ļ�
	 * @param m 
	 */
	public static void hoareQuickSort(int n,Element[] r,int m){
		class StackType{
			private int x;
			private int y;
		}
		AStack<StackType> stackptr=new AStack<StackType>(30);
		StackType temp=new StackType();
		int f,t,j;
		temp.x=temp.y=0;
		stackptr.push(temp);//����ջ��Ԫ��
		f=1;t=n;
		while(f<t){
			j=part(r,f,t);
			if(j-f<m&&t-j<m){
				temp=stackptr.pop();
				f=temp.x;
				t=temp.y;
				continue;
			}
			if(j-f<m&&t-j>=m){
				f=j+1;
				continue;
			}
			if(j-f>=m&&t-j<m){
				t=j-1;
				continue;
			}
			if(j-f>=m&&t-j>=m){//�������ļ������ڵ���mʱ���洢�ϴ�����ļ�
				if(j-f>t-j){
					temp.x=f;
					temp.y=j-1;
					stackptr.push(temp);
					f=j+1;
				}
				else{
					temp.x=j+1;
					temp.y=t;
					stackptr.push(temp);
					t=j-1;
				}
			}
		}
		insertSortA(r, n);
	}
	/**
	 * �ؽ��ѣ���Ϊtree[rot]�Ķ���������������ڵ���<=n
	 * @param tree ������
	 * @param root ��
	 * @param n ����
	 */
	public static void restore(Element[] tree,int root,int n){
		int m;
		int j=root;
		while(j<=(int)n/2){
			if((2*j<n)&&(tree[2*j].getKey()<tree[2*j+1].getKey())){
				m=2*j+1;
			}
			else m=2*j;
			if(tree[j].getKey()<tree[m].getKey()){
				interChange(tree, j, m);
				j=m;
			}
			else j=n;
		}
	}
	/**
	 * �������㷨�����ļ�r1��...��rn��������
	 * @param r �������ļ�
	 * @param n ����
	 */
	public static void heapSort(Element[] r,int n){
		int i;
		for(i=n/2;i>=1;i--){//��ʼ����
			restore(r, i, n);
		}
		for(i=n;i>1;i--){//����
			interChange(r, 1, i);
			restore(r, 1, i-1);
		}
	}
	//����
	public static void main(String[] args){
		int num;
		Scanner s=new Scanner(System.in);
		System.out.println("�������ļ�������");
		num=s.nextInt();
		Element[] e=new Element[num+2];
		System.out.println("�����������ļ��ؼ��ʣ�");
		e[0]=new Element();
		e[0].setKey(MINKEY);
		e[num+1]=new Element();
		e[num+1].setKey(MAXKEY);
		for(int i=1;i<num+1;i++){
			e[i]=new Element();
			e[i].setKey(s.nextInt());
		}
		Sort.insertSortA(e, num);
		for(int i=1;i<num+1;i++){
			System.out.print(e[i].getKey()+" ");
		}
		System.out.println();	
		Sort.bubble(e, num);
		for(int i=1;i<num+1;i++){
			System.out.print(e[i].getKey()+" ");
		}
		System.out.println();
		Sort.selectSort(e, num);
		for(int i=1;i<num+1;i++){
			System.out.print(e[i].getKey()+" ");
		}
		System.out.println();
		Sort.shellSort(e, num);
		for(int i=1;i<num+1;i++){
			System.out.print(e[i].getKey()+" ");
		}
		System.out.println();
		Sort.mergeSort(e, num);
		for(int i=1;i<num+1;i++){
			System.out.print(e[i].getKey()+" ");
		}
		System.out.println();
		Sort.recQuickSort(e, 1, num);
		for(int i=1;i<num+1;i++){
			System.out.print(e[i].getKey()+" ");
		}
		System.out.println();
		Sort.hoareQuickSort(num, e, 1);
		for(int i=1;i<num+1;i++){
			System.out.print(e[i].getKey()+" ");
		}
		System.out.println();
		Sort.heapSort(e, num);
		for(int i=1;i<num+1;i++){
			System.out.print(e[i].getKey()+" ");
		}
		System.out.println();
	}
}
