package datastructure;

public class AQueue<T> {
	/**
	 * ?????????????????±?
	 */
	private int front;
	/**
	 * ??β?????????????±?
	 */
	private int rear;
	/**
	 * ???????????
	 */
	private int count;
	/**
	 * ??????????????
	 */
	private Object[] queueArray;
	/**
	 * ???????
	 */
	private int size;
	/**
	 * ??????
	 */
	public AQueue(){
		this.size=10;
		this.queueArray=new Object[size];
		front=0;
		rear=0; 
		count=0;
	}
	/**
	 * ??????
	 * @param size ?????С
	 */
	public AQueue(int size){
		this.size=size;
		this.queueArray=new Object[size];
		front=0;
		rear=0; 
		count=0;
	}
	/**
	 * ?ж????????
	 * @return
	 */
	public boolean isFull(){
		return count==size;
	}
	/**
	 * ?ж???????
	 * @return
	 */
	public boolean isEmpty(){
		return count==0;
	}
	/**
	 * ???β????data?item?????
	 * @param item ??????
	 * @return ???????true
	 */
	public boolean insert(T item){
		if(isFull()){
			System.out.println("full queue");
			return false;
		}
		queueArray[rear]=item;
		rear=(rear+1)%size;//????β???
		count++;
		return true;
	}
	/**
	 * ??????????
	 * @return ???????????
	 */
	public T delete(){
		if(isEmpty()){
			throw new RuntimeException("empty queue");
		}
		T item=(T)queueArray[front];
		front=(front+1)%size;
		count--;
		return item;
	}
	/**
	 * ???????????
	 * @return
	 */
	public T getFront(){
		if(isEmpty()){
			throw new RuntimeException("empty queue");
		}
		return (T)queueArray[front];
	}
	/**
	 * ??????
	 */
	public void clear(){
		front=rear=count=0;
	}
	//????
	public static void main(String[] args){
		AQueue<Integer> aqueue=new AQueue<Integer>();
		aqueue.insert(1);  
		aqueue.insert(2); 
		aqueue.insert(3); 
		aqueue.insert(4); 
		aqueue.delete();
		System.out.println(aqueue.getFront());
		aqueue.delete();
		System.out.println(aqueue.getFront());
		aqueue.delete();
		System.out.println(aqueue.getFront());
		aqueue.delete();
		System.out.println(aqueue.getFront());
	}
}
