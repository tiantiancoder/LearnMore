package datastructure;
import java.util.Scanner;

/**
 * ����������
 * @author liangxiamoyi
 *
 */
public class HuffmanTree {
	/**
	 * ���ڵ�
	 */
	private HuffmanNode root;
	/**
	 * data����
	 */
	private String[] data;
	/**
	 * weight����
	 */
	private int[] weight;
	/**
	 * �����
	 */
	private int nodeNum;
	/**
	 * ���췽��
	 * @param t ���ڵ�
	 * @param nodeNum �ڵ���
	 */
	public HuffmanTree(HuffmanNode t,int nodeNum){
		this.root=t;
		this.nodeNum=nodeNum;
		this.data=new String[nodeNum];
		this.weight=new int[nodeNum];
	}
	/**
	 * ������������
	 */
	public void createHuffmanTree(){
		inputNodes();
		HuffmanNode[] h=new HuffmanNode[nodeNum];
		HuffmanNode p1,p2,p,t;
		for(int i=0;i<nodeNum;i++){
			h[i]=new HuffmanNode(null, null, null);
			h[i].item=new String(data[i]);
			h[i].weight=weight[i];
			h[i].left=h[i].right=h[i].father=null;
		}
		for(int i=0;i<nodeNum-1;i++){
			t=new HuffmanNode("#", null, null);
			p1=h[i];
			p2=h[i+1];
			t.weight=p1.weight+p2.weight;
			t.left=p1;
			t.right=p2;
			p1.father=p2.father=t;
			p=t;
			int j=i+2;
			while(j<nodeNum&&p.weight>h[j].weight){
				h[j-1]=h[j];
				j=j+1;
			}
			h[j-1]=p;
		}
		root=h[nodeNum-1];
	}
	/**
	 * ��������Ϣ
	 */
	public void inputNodes(){
		Scanner sc=new Scanner(System.in);
		System.out.println("������data���飺");
		for(int i=0;i<nodeNum;i++){
			data[i]=sc.next();
		}
		System.out.println("������weight���飺");
		for(int i=0;i<nodeNum;i++){
			weight[i]=sc.nextInt();
		}
		//��С�����������
		for(int i=0;i<weight.length-1;i++){
			for(int j=i+1;j<weight.length;j++){
				int temp;
				String str=new String();
				if(weight[i]>weight[j]){
					temp=weight[i];
					weight[i]=weight[j];
					weight[j]=temp;
					str=data[i];
					data[i]=data[j];
					data[j]=str;
				}
			}
		}
	}
	/**
	 * ����Ҷ�ڵ�
	 * @param root ���ڵ�
	 * @param item ����ֵ
	 * @return ����Ҷ�ڵ�
	 */
	public HuffmanNode find(HuffmanNode t,String item){
		HuffmanNode p;
		if(t==null)return null;
		else if(t.item.equals(item))return t;//������ͬ
		else if((p=find(t.left,item))!=null)return p;
		else return find(t.right,item);
	}
	/**
	 * ��ù���������
	 * @param item ����ֵ
	 */
	public void getHuffmanNode(String item){
		HuffmanNode p=find(this.root,item);
		if(p!=null){
			System.out.println("chenggong");
		}
		StringBuffer code=new StringBuffer();
		while(p!=null&&p.father!=null){
			if(p.father.left==p){
				code.append('0');
			}
			else{ 
				code.append('1');
			}
			p=p.father;
		}
		System.out.println(code.reverse());
	}
	/**
	 * �ȸ�����
	 * @param t ���ڵ�
	 */
	public void preOrder(HuffmanNode t){
		if(t!=null){
			System.out.print(t.item+" ");
			preOrder(t.left);
			preOrder(t.right);
		}
	}
	//����
	public static void main(String[] args){
		HuffmanTree huffman=new HuffmanTree(null, 7);
		huffman.createHuffmanTree();
		huffman.preOrder(huffman.root);
		huffman.getHuffmanNode("s");
	}
}
