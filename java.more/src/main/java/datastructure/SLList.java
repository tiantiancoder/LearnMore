package datastructure;
/**
 * ��������
 * @author liangxiamoyi
 *
 * @param <T>
 */
public class SLList<T>{
	/**
	 * ��ͷ
	 */
	private SLNode<T> head;
	/**
	 * ��β
	 */
	private SLNode<T> tail;
	/**
	 * ��ǰ���
	 */
	private SLNode<T> currNode;
	/**
	 * �������С
	 */
	private int size;
	/**
	 * ����ֻ��һ����λ���Ŀձ�
	 */
	public SLList(){
		this.head=new SLNode<T>(null);
		this.size=0;
	}
	/**
	 * ������һ�����ĵ�����
	 * @param item ������
	 */
	public SLList(T item){
		tail=currNode=new SLNode<T>(item,null);
		head=new SLNode<T>(currNode);
		size=1;
	}
	/**
	 * �ж��Ƿ�Ϊ��
	 * @return
	 */
	public boolean isEmpty(){
		return head.getNextNode()==null;
	}
	/**
	 * �õ�������ĳ���
	 * @return
	 */
	public int getSize(){
		return this.size;
	}
	/**
	 * ��ȡ���õ���k������������
	 * @param k ��k�����
	 * @return ���ص�k����������
	 */
	public T find(int k){
		if(k<0||k>size||size==0){
			throw new RuntimeException("empty list or unreasonable position!");
		}
		currNode=head.nextNode;
		for(int i=2;i<=k;i++){
			currNode=currNode.nextNode;
		}
		return currNode.data;
	}
	/**
	 * ���ң��õ���һ������Ϊitem�Ľ���λ��
	 * @param item ����
	 * @return λ��
	 */
	public int search(T item){
		currNode=head.nextNode;
		for(int i=1;i<=size;i++){
			if(currNode.data==item)return i;
			else{
				currNode=currNode.nextNode;
			}
		}
		return -1;
	}
	/**
	 * ɾ����ǰ���ĺ�̽�㣬���������ݷ���
	 * @return 
	 */
	public T delete(){
		if(currNode==tail||isEmpty()){
			throw new RuntimeException("no next node or empty list");
		}
		SLNode<T> temp=currNode.nextNode;
		currNode.setNextNode(temp.nextNode);
		size--;
		if(temp==tail){
			tail=currNode;
		}
		return temp.data;
	}
	/**
	 * ɾ����λ����ĵ�һ���������㣬�������ݷ���
	 * @return
	 */
	public T deleteFromHead(){
		if(isEmpty()){
			throw new RuntimeException("empty list");
		}
		SLNode<T> temp=head.nextNode;
		head.setNextNode(temp.nextNode);
		size--;
		if(temp==tail){
			tail=head;
		}
		return temp.data;
	}
	/**
	 * ɾ����β��㣬�������ݷ���
	 * @return
	 */
	public T deleteFromTail(){
		if(isEmpty()){
			throw new RuntimeException("empty list");
		}
		if(currNode==tail){
			currNode=head;
		}
		SLNode<T> temp=currNode;
		while(temp.nextNode!=tail){
			temp=temp.nextNode;
		}
		SLNode<T> node=tail;
		tail=temp;
		size--;
		return node.data;
		
	}
	/**
	 * �ڵ�ǰ�������һ������Ϊitem�Ľ��
	 * @param item
	 */
	public void insert(T item){
		currNode.setNextNode(new SLNode<T>(item,currNode.getNextNode()));
		if(tail==currNode){
			tail=currNode.getNextNode();
		}
		size++;
	}
	/**
	 * �ӱ�β����һ������Ϊitem�Ľ��
	 * @param item
	 */
	public void insertFromTail(T item){
		tail.setNextNode(new SLNode<T>(item,null));
		tail=tail.getNextNode();
		size++;
	}
	/**
	 * ����λ�������һ������Ϊitem�Ľ��
	 * @param item
	 */
	public void insertFromHead(T item){
		if(isEmpty()){
			head.setNextNode(new SLNode<T>(item,head.getNextNode()));
			tail=head.getNextNode();
		}
		else{
			head.setNextNode(new SLNode<T>(item,head.getNextNode()));
		}
		size++;
	}
	/**
	 * ��ӡ���н�������
	 */
	public void showNodes(){
		currNode=head.nextNode;
		for(int i=1;i<=size;i++){
			System.out.println(currNode.data);
			currNode=currNode.nextNode;
		}
	}
	//����
	public static void main(String[]args){
		SLList<Integer> list=new SLList<Integer>(1);
		list.insert(2);
		list.insertFromHead(3);
		list.insertFromTail(4);
		list.showNodes();
		System.out.println(list.search(1));
		System.out.println(list.find(2));
		list.deleteFromHead();
		list.deleteFromTail();
		list.showNodes();
	}
}
