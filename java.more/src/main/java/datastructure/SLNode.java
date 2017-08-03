package datastructure;
/**
 * ����������
 * @author liangxiamoyi
 *
 * @param <T>
 */
public class SLNode<T> {
	/**
	 * ������
	 */
	protected T data;
	/**
	 * ��һ�����
	 */
	protected SLNode<T> nextNode;
	/**
	 * ���췽��
	 * @param node ��һ���
	 */
	public SLNode(SLNode<T> node){
		this.nextNode=node;
	}
	/**
	 * ���췽��
	 * @param data ����
	 * @param node ��һ���
	 */
	public SLNode(T data,SLNode<T> node){
		this.data=data;
		this.nextNode=node;
	}
	/**
	 * �������
	 * @return
	 */
	public T getData(){
		return data; 
	}
	/**
	 * �����һ���
	 * @return
	 */
	public SLNode<T> getNextNode(){
		return nextNode;
	}
	/**
	 * ��������
	 * @param data
	 */
	public void setData(T data){
		this.data=data;
	}
	/**
	 * ������һ���
	 * @param node
	 */
	public void setNextNode(SLNode<T> node){
		this.nextNode=node;
	}
}
