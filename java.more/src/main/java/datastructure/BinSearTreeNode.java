package datastructure;
/**
 * ��������������
 * @author liangxiamoyi
 *
 */
public class BinSearTreeNode {
	/**
	 * ��ڵ�
	 */
	public BinSearTreeNode leftLink;
	/**
	 * �ҽڵ�
	 */
	public BinSearTreeNode rightLink;
	/**
	 * ����
	 */
	public int key;
	/**
	 * ���췽��
	 * @param item ����ֵ
	 * @param left ��ڵ�
	 * @param right �ҽڵ�
	 */
	public BinSearTreeNode(int item,BinSearTreeNode left,BinSearTreeNode right){
		this.key=item;
		this.leftLink=left;
		this.rightLink=right;
	}
}
