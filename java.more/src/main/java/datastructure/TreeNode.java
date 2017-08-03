package datastructure;
/**
 * �������
 * @author liangxiamoyi
 *
 */
public class TreeNode {
	/**
	 * ����ֵ
	 */
	protected char data;
	/**
	 * ���ӽ��
	 */
	protected TreeNode firstChild;
	/**
	 * ���ֵܽ��
	 */
	protected TreeNode nextBrother;
	/**
	 * ���췽��
	 * @param data ����ֵ
	 * @param first ���ӽ��
	 * @param next ���ֵܽ��
	 */
	public TreeNode(char data,TreeNode first,TreeNode next){
		this.data=data;
		this.firstChild=first;
		this.nextBrother=next;
	}
}
