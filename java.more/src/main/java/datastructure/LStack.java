package datastructure;
/**
 * ��ʽջ��
 * @author liangxiamoyi
 *
 * @param <T>
 */
public class LStack<T> {
	/**
	 * ջ������
	 */
	 private  SLNode<T> top;
	 /**
	  * ���캯��
	  */
	 public LStack(){
		 top=null;
	 }
	 /**
	  * �ж�ջ�Ƿ�Ϊ��
	  * @return
	  */
	 public boolean isEmpty(){
		 return top==null;
	 }
	 /**
	  * ���ջ
	  */
	 public void clear(){
		 while(!isEmpty()){
			 top=top.nextNode; 
		 }
	 }
	 /**
	  * ѹջ
	  * @param item ��ջԪ��ֵ
	  * @return �ɹ�����true
	  */
	 public boolean push(T item){
		 top=new SLNode<T>(item,top);
		 return true;
	 }
	 /**
	  * ��ջ
	  * @return ���ص���Ԫ��ֵ
	  */
	 public T pop(){
		 if(isEmpty()){
			 throw new RuntimeException("empty stack");
		 }
		 SLNode<T> temp=top;
		 top=top.nextNode;
		 return temp.data;
	 }
	 /**
	  * ��ȡջ��Ԫ��ֵ
	  * @return
	  */
	 public T peek(){
		 if(isEmpty()){
			 throw new RuntimeException("empty stack");
		 }
		 return top.data;
	 }
	 //����
	 public static void main(String[] args){
		 LStack<Character> lstack=new LStack<Character>();
		 lstack.push('a');
		 lstack.push('b');
		 lstack.push('c');
		 lstack.push('d');
		 System.out.println(lstack.peek());
		 lstack.pop();
		 System.out.println(lstack.peek());
		 lstack.clear();
		 System.out.println(lstack.peek());
	 }
}
