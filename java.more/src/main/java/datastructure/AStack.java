package datastructure;
/**
 * ˳��ջ
 * @author liangxaimoyi
 *
 * @param <T>
 */
public class AStack<T> {
	/**
	 * ����Ĵ�С
	 */
	private int size;
	/**
	 * ���Ԫ�ص�����
	 */
	private Object[] stackArray;
	/**
	 * ջ��Ԫ�ص��±�
	 */
	private int top;
	/**
	 * ���캯��
	 * @param maxSize �����С
	 */
	public AStack(int maxSize){
		this.size=maxSize;
		this.stackArray=new Object[size];
		this.top=-1;
	}
	/**
	 * �ж��Ƿ�ջ��
	 * @return
	 */
	public boolean isFull(){
		return top==size-1;
	}
	/**
	 * ��ջ��ѹ��һ��Ԫ��
	 * @param item Ҫ��ջ��Ԫ��ֵ
	 * @return �ɹ�����true
	 */
	public boolean push(T item){
		if(isFull()){
			System.out.println("full stack");
			return false;
		}
		else{
			stackArray[++top]=item;
			return true;
		}
	}
	/**
	 * �ж�ջ�Ƿ�Ϊ��
	 * @return
	 */
	public boolean isEmpty(){
		return top==-1;
	}
	/**
	 * ��ջ������һ��Ԫ��
	 * @return ����ջ��Ԫ��ֵ
	 */
	public T pop(){
		if(isEmpty()){
			throw new RuntimeException("Empty stack");
		}
		return (T)stackArray[top--];
	}
	/**
	 * ��ȡջ��Ԫ��
	 * @return ����ջ��Ԫ��ֵ
	 */
	public T peek(){
		if(isEmpty()){
			throw new RuntimeException("Empty stack");
		}
		return (T)stackArray[top];
	}
	/**
	 * ���ջ
	 */
	public void clear(){
		top=-1;
	}
	//����
	public static void main(String[] args){
		AStack<Character> astack=new AStack<Character>(10);
		astack.push('a');
		astack.push('b');
		astack.push('c');
		astack.push('d');
		System.out.println(astack.peek());
		astack.pop();
		System.out.println(astack.peek());
		astack.clear();
		System.out.println(astack.peek());
	}
}
