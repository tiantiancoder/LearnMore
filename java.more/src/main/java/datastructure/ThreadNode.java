package datastructure;
/**
 * �����������
 * @author liangxiamoyi
 *
 */
public class ThreadNode {
	/**
	 * 1��ʶ��ڵ�Ϊǰ����㣬0��ʶ��ڵ�Ϊ���ӽڵ�
	 */
	protected int lThread;
	/**
	 * 1��ʶ�ҽڵ�Ϊ��̽�㣬0��ʶ�ҽڵ�Ϊ���ӽڵ�
	 */
	protected int rThread;
	/**
	 * ��ڵ�
	 */
	protected ThreadNode left;
	/**
	 * �ҽڵ�
	 */
	protected ThreadNode right;
	/**
	 * ����
	 */
	protected char data;
	/**
	 * ���캯��
	 * @param item ����ֵ
	 */
	public ThreadNode(char item){
		this.data=item;
		this.lThread=this.rThread=0;
		this.left=this.right=null;
	}
}
