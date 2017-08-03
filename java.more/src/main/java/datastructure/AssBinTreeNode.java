package datastructure;

public class AssBinTreeNode {
	/**
	 * ���������
	 */
	protected BinTreeNode ptr;
	/**
	 * ��ʶ����ջ�Ĵ�����ȡֵΪ0,1,2
	 */
	protected int flag;
	/**
	 * ���췽��
	 * @param t ���������
	 * @param flag ��ʶ
	 */
	public AssBinTreeNode(BinTreeNode t,int flag){
		this.ptr=t;
		this.flag=flag;
	}
	/**
	 * ���flag
	 * @return
	 */
	public int getFlag(){
		return this.flag;
	}
	/**
	 * ����flag
	 * @param flag ��ʶ
	 */
	public void setFlag(int flag){
		this.flag=flag;
	}
}
