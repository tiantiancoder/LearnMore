package datastructure;
/**
 * ��ʽ����
 * @author liangxiamoyi
 *
 * @param <T>
 */
public class LQueue<T> {
	/**
	 * ����ָ��
	 */
	private SLNode<T> front;
	/**
	 * ��βָ��
	 */
	private SLNode<T> rear;
	/**
	 * ����Ԫ�صĸ���
	 */
	private int count;
	/**
	 * �ж��Ƿ�Ϊ��
	 * @return
	 */
	public boolean isEmpty(){
		return count==0;
	}
	/**
	 * �����βԪ��
	 * @param item Ԫ��ֵ
	 */
	public void insert(T item){
		if(isEmpty()){
			front=rear=new SLNode<>(item,null);
			count=1;
		}
		else{
			rear.nextNode=new SLNode<>(item,null);
			rear=rear.nextNode;
			count++;
		}
	}
	/**
	 * ɾ������Ԫ��
	 * @return
	 */
	public T delete(){
		if(isEmpty()){
			throw new RuntimeException("empty queue");
		}
		SLNode<T> temp=front;
		front=front.nextNode;
		count--;
		if(count==0){
			rear=null;
		}
		return temp.data;
	}
	/**
	 * ��ö���Ԫ��
	 * @return ����T���͵Ķ���Ԫ��ֵ
	 */
	public T getFront(){
		if(isEmpty()){
			throw new RuntimeException("empty queue");
		}
		return front.data;
	}
	/**
	 * ��ն���
	 */
	public void clear(){
		front=rear=null;
		count=0;
	}
	//����
	public static void main(String[] args){
		LQueue<Integer> lqueue=new LQueue<Integer>();
		lqueue.insert(1);  
		lqueue.insert(2); 
		lqueue.insert(3); 
		lqueue.insert(4); 
		lqueue.delete();
		System.out.println(lqueue.getFront());
		lqueue.delete();
		System.out.println(lqueue.getFront());
		lqueue.delete();
		System.out.println(lqueue.getFront());
		lqueue.delete();
		System.out.println(lqueue.getFront());
	}
}
