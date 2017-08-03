package datastructure;
/**
 * ˫��������
 * @author liangxiamoyi
 *
 * @param <T>
 */
public class DLList<T> {
	/**
	 * ��ͷ
	 */
	private DLNode<T> head;
	/**
	 * ��β
	 */
	private DLNode<T> tail;
	/**
	 * ��ǰ���
	 */
	private DLNode<T> currNode;
	/**
	 * �����С
	 */
	private int size;
	/**
	 * ����ֻ��һ����λ���Ŀձ�
	 */
	public DLList(){
		this.head=new DLNode<T>(null,null);
		this.size=0;
	}
	/**
	 * ������һ�����ĵ�����
	 * @param item ������
	 */
	public DLList(T item){
		this.head=new DLNode<T>(null, null);
		tail=currNode=new DLNode<T>(item,head,null);
		head.nextNode=tail;
		size=1;
	}
	/**
	 * �ж��Ƿ�Ϊ��
	 * @return
	 */
	public boolean isEmpty(){
		return head.nextNode==null;
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
	 * ɾ����ǰ��㣬���������ݷ���
	 * @return 
	 */
	public T delete(){
		if(currNode==head||isEmpty()){
			throw new RuntimeException("head node or empty list");
		}
		DLNode<T> temp=currNode;
		currNode.nextNode.lastNode=currNode.lastNode;
		currNode.lastNode.nextNode=currNode.nextNode;
		size--;
		if(currNode==tail){
			tail=currNode.lastNode;
		}
		currNode=currNode.lastNode;
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
		DLNode<T> temp = head.nextNode;
		head.nextNode = head.nextNode.nextNode;
		size--;
		if(temp==tail){
			tail=head;
		}
		else{
			head.nextNode.lastNode=head;
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
		DLNode<T> p=tail;
		tail=tail.lastNode;
		tail.nextNode=null;
		size--;
		return p.data;
	}
	/**
	 * ɾ����ǰ������һ�����
	 * @return
	 */
	public T deleteNext(){
		if(isEmpty()||currNode.nextNode==null){
			throw new RuntimeException("empty list or the next node is null");
		}
		DLNode<T> p=currNode.nextNode;
		if(p==tail){
			tail=currNode;
			currNode.nextNode=null;
		}
		else{
			currNode.nextNode=p.nextNode;
			p.nextNode.lastNode=currNode;
		}
		size--;
		return p.data;
		
	}
	/**
	 * �ڵ�ǰ�������һ������Ϊitem�Ľ��
	 * @param item ����
	 */
	public void insert(T item){
		if(isEmpty()){
			tail=head.nextNode=new DLNode<T>(item,head,null);
			size++;
			return;
		}
		DLNode<T> p=new DLNode<T>(item,currNode,currNode.nextNode);
		currNode.nextNode.lastNode=p;
		currNode.nextNode=p;
		size++;
		if(tail==currNode){
			tail=p;
		}
	}
	/**
	 * �ӱ�β����һ������Ϊitem�Ľ��
	 * @param item
	 */
	public void insertFromTail(T item){
		tail.nextNode=new DLNode<T>(item,tail,null);
		tail=tail.nextNode;
		size++;
	}
	/**
	 * ����λ�������һ������Ϊitem�Ľ��
	 * @param item
	 */
	public void insertFromHead(T item){
		if(isEmpty()){
			head.nextNode=new DLNode<T>(item,head,null);
			tail=head.nextNode;
			size++;
			return;
		}
		else{
			DLNode<T> p=new DLNode<T>(item, head,head.nextNode);
			head.nextNode.lastNode=p;
			head.nextNode=p;
			size++;
		}
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
	public static void main(String[] args){
		DLList<Integer> list=new DLList<Integer>(5);
		list.insertFromHead(4);
		list.insertFromTail(3);
		list.insertFromHead(2);
		list.showNodes();
		System.out.println(list.search(4));
		System.out.println(list.find(2));
		list.deleteFromHead();
		list.deleteFromTail();
		list.showNodes();
	}
}
