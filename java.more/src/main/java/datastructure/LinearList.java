package datastructure;
/**
 * ˳���
 * @author liangxiamoyi
 *
 * @param <T> 
 */
public class LinearList<T>{
	/**
	 * ��󳤶�
	 */
	private int maxSize;
	/**
	 * ʵ�ʳ���
	 */
	private int length;
	/**
	 * ���˳��������
	 */
	private Object[] element=null;
	/**
	 * <p>�޲ι��캯����������󳤶ȵ�Ĭ��ֵΪ10��</p>
	 * <p>c++ ���βο��Ը�Ĭ��ֵ��java �ǲ����Եġ����캯��һ��Ҳ�����ԡ�</p>
	 */
	public LinearList(){
		this.maxSize=10;
		element=new Object[maxSize];
		length=0;
	}
	/**
	 * ���캯��
	 * @param maxListSize ��󳤶�
	 */
	public LinearList(int maxListSize){
		this.maxSize=maxListSize;
		element=new Object[maxSize];
	    length=0;
	}
	/**
	 * ��ȡ�����±�Ϊk�Ľ����ֶη���
	 * @param k �±�
	 * @return �ɹ���������
	 */
	public T find(int k){
		if(k<0||k>length-1||length==0){
			throw new RuntimeException("empty list or unreasonable position!");
		}
		return (T)element[k];
	}
	/**
	 * ��ȡ�����±�Ϊk�����ݸ�ֵ��item
	 * @param k �±�
	 * @param item �����ݸ�ֵ��item
	 * @return �ɹ�����true��ʧ�ܷ���false
	 */
	public boolean find(int k ,T item){
		if(k<0||k>length-1||length==0){
			System.out.println("empty list or unreasonable position!");
			return false;
		}
		item=(T)element[k];
		return true;
	}
	/**
	 * ����
	 * @param item Ҫ���ҵ�����
	 * @return �ɹ���������λ���±꣬ʧ�ܷ���-1
	 */
	public int search(T item){
		for(int i=0;i<length;i++){
			if(element[i]==item)return i;
		}
		return -1;
	}
	/**
	 * ɾ��
	 * @param k �±�
	 * @param item Ҫ���ҵ�����
	 */
	public void delete(int k,T item){
		if(find(k,item)){
			for(int i=k+1;i<length;i++){
				element[i-1]=element[i];
			}
			length--;
		}
	}
	/**
	 * ���룬���±�Ϊk�Ľ��������item
	 * @param k �±�
	 * @param item Ҫ���������
	 */
	public void insert(int k,T item){
		if(isFull()){
			System.out.println("the list is full");
			return;
		}
		if(k<-1||k>maxSize-1){
			System.out.println("the node is not exist");
			return;
		}
		for(int i=length-1;i>=k+1;i--){
			element[i+1]=element[i];
		}
		element[k+1]=item;
		length++;
		return;
	}
	/**
	 * �ж��Ƿ�Ϊ��
	 * @return
	 */
	public boolean isEmpty(){
		return length==0;
	}
	/**
	 * �ж��Ƿ�Ϊ��
	 * @return
	 */
	public boolean isFull(){
		return length==maxSize;
	}
	//����
	public static void main(String[] args){
		LinearList<Integer> list=new LinearList<Integer>();
		for(int k=-1;k<5;k++){
			list.insert(k, k);
		}
		Integer item=list.find(4);
		System.out.println(item);
		list.delete(0, -1);
		System.out.println(list.find(4));
		System.out.println(list.search(4));
	}
}
