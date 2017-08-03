package datastructure;
/**
 *˫����������
 * @author liangxiamoyi
 * @param <T>
 */
public class DLNode<T> {
	/**
	 * ������
	 */
	protected T data;
	/**
	 * ��һ�����
	 */
	protected DLNode<T> nextNode;
	/**
	 * ��һ�����
	 */
	protected DLNode<T> lastNode;
	/**
	 * ���췽��
	 * @param last ��һ�����
	 * @param next ��һ�����
	 */
	public DLNode(DLNode<T> last,DLNode<T> next ){
		this.lastNode=last;
		this.nextNode=next;
	}
	/**
	 * ���췽��
	 * @param item ������
	 * @param last ��һ�����
	 * @param next ��һ�����
	 */
	public DLNode(T item,DLNode<T> last,DLNode<T> next){
		this.data=item;
		this.nextNode=next;
		this.lastNode=last;
	}
}
