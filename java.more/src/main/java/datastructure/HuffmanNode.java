package datastructure;
/**
 * �������������
 * @author liangxiamoyi
 *
 */
public class HuffmanNode {
	/**
	 * ���ӽڵ�
	 */
	protected HuffmanNode left;
	/**
	 * ���ӽڵ�
	 */
	protected HuffmanNode right;
	/**
	 * ���׽��
	 */
	protected HuffmanNode father;
	/**
	 * ����
	 */
	protected String item;
	/**
	 * Ȩֵ
	 */
	protected int weight;
	/**
	 * ���췽��
	 * @param item �ַ�
	 * @param left ���ӽڵ�
	 * @param right ���ӽڵ�
	 */
	public HuffmanNode(String item,HuffmanNode left,HuffmanNode right){
		this.item=item;
		this.left=left;
		this.right=right;
		this.weight=0;
		this.father=null;
	}
}
