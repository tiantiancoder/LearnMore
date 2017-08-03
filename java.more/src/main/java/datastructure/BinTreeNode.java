package datastructure;
/**
 * �����������
 * @author liangxiamoyi
 *
 */
public class BinTreeNode {
	/**
	 * ���ӽڵ�
	 */
	protected BinTreeNode left;
	/**
	 * ���ӽڵ�
	 */
	protected BinTreeNode right;
	/**
	 * ������
	 */
	protected char data;
	/**
	 * ���췽��
	 * @param item ����
	 * @param left ���ӽڵ�
	 * @param right ���ӽڵ�
	 */
	public BinTreeNode(char item,BinTreeNode left,BinTreeNode right){
		this.data=item;
		this.left=left;
		this.right=right;
	}
}
