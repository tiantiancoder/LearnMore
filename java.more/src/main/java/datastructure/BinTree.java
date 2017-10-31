package datastructure;
import java.util.Scanner;
/**
 * ��������
 * @author liangxiamoyi
 *
 */
public class BinTree {
	/**
	 * ���ڵ�
	 */
	private BinTreeNode root;
	/**
	 * ���������ʱ�����������
	 */
	private char stop;
	/**
	 * ���췽��
	 * @param root ���ڵ�
	 */
	public BinTree(BinTreeNode root){
		this.root=root;
	}
	/**
	 * ��ø��ڵ�
	 * @return
	 */
	public BinTreeNode getRoot(){
		return root;
	}
	/**
	 * ���ø��ڵ�
	 * @param root ���ڵ�
	 */
	public void setRoot(BinTreeNode root){
		this.root=root;
	}
	/**
	 * ���stop����
	 * @return
	 */
	public char getStop(){
		return this.stop;
	}
	/**
	 * ����stop
	 * @param stop �����ַ�
	 */
	public void setStop(char stop){
		this.stop=stop;
	}
	/**
	 * ����rootΪ���ڵ�Ķ��������ҽ��p�ĸ��ڵ�
	 * @param root ���ڵ�
	 * @param p ���
	 * @return p�ĸ��ڵ�
	 */
	public BinTreeNode getFather(BinTreeNode root,BinTreeNode p){
		BinTreeNode t;
		if(root==null||p==null){
			return null;
		}
		if(root.left==p||root.right==p){
			return root;
		}
		if((t=getFather(root.left,p))!=null){
			return t;
		}
		else return getFather(root.right, p);
	}
	/**
	 * ����rootΪ���ڵ�Ķ�������������Ϊitem�Ľ��
	 * @param root ���ڵ�
	 * @param item ����
	 * @return ����Ϊitem�Ľ��
	 */
	public BinTreeNode find(BinTreeNode root,char item){
		BinTreeNode p;
		if(root==null)return null;
		else if(root.data==item)return root;
		else if((p=find(root.left,item))!=null)return p;
		else return find(root.right,item);
	}
	/**
	 * ɾ����tΪ���ڵ������
	 * @param t �����ĸ��ڵ�
	 */
	public void delSubTree(BinTreeNode t){
		if(t==null)return;
		else if(t==root){
			root=null;
			return;
		}
		else{
			BinTreeNode p=getFather(root, t);
			if(p!=null){
				if(p.left==t){
					p.left=null;
				}
				else p.right=null;
			}
		}
	}
	/**
	 *
	 */
	public void preOrder(BinTreeNode t){
		if(t!=null){
			System.out.print(t.data+" ");
			preOrder(t.left);
			preOrder(t.right);
		}
	}
	/**
	 *
	 */
	public void NorecPreOrder(BinTreeNode t){
		if(t==null){
			return ;
		}
		AStack<BinTreeNode> a=new AStack<BinTreeNode>(20);
		while(t!=null||!a.isEmpty()){
			while(t!=null){
				a.push(t);
				System.out.print(t.data+" ");
				t=t.left;
			}
			if(a.isEmpty())return;
			t=a.pop();
			t=t.right;
		}
	}
	/**
	 * �и�����
	 * @param t
	 */
	public void inOrder(BinTreeNode t){
		if(t!=null){
			inOrder(t.left);
			System.out.print(t.data+" ");
			inOrder(t.right);
		}
	}
	/**
	 *
	 */
	public void norecInOrder(BinTreeNode t){
		if (t==null){
			return ;
		}
		//˳��ջ
		AStack<BinTreeNode> s=new AStack<BinTreeNode>(20);
		while(t!=null||!s.isEmpty()){
			while(t!=null){
				s.push(t);
				t=t.left;
			}
			if(s.isEmpty())return;
			t=s.pop();
			System.out.print(t.data+" ");
			t=t.right;
		}
	}
	/**
	 *
	 */
	public void postOrder(BinTreeNode t){
		if(t!=null){
			postOrder(t.left);
			postOrder(t.right);
			System.out.print(t.data+" ");
		}
	}
	/**
	 *
	 */
	public void norecPostOrder(BinTreeNode t){
		if(t==null)return;
		AStack<AssBinTreeNode> s=new AStack<>(20);
		AssBinTreeNode ass,lass,rass;
		ass=new AssBinTreeNode(t, 0);
		s.push(ass);
		int i=0;
		while(!s.isEmpty()){
			ass=s.pop();
			t=ass.ptr;
			i=ass.flag;
			if(i==0){
				ass.setFlag(1);
				s.push(ass);
				if(t.left!=null){
					lass=new AssBinTreeNode(t.left, 0);
					s.push(lass);
				}
			}
			if(i==1){
				ass.setFlag(2);
				s.push(ass);
				if(t.right!=null){
					rass=new AssBinTreeNode(t.right, 0);
					s.push(rass);
				}
			}
			if(i==2){
				System.out.print(t.data+" ");
			}
		}
	}

	public void levelOrder(BinTreeNode t){
		if(t==null){
			return ;
		}
		AQueue<BinTreeNode> p=new AQueue<BinTreeNode>(20);
		p.insert(t);
		BinTreeNode q;
		while(!p.isEmpty()){
			q=p.delete();
			System.out.print(q.data+" ");
			if(q.left!=null)p.insert(q.left);
			if(q.right!=null)p.insert(q.right);
		}
	}
	/**
	 * ����һ�ö�����������create()����
	 * @param stop �����ַ�
	 */
	public void createBinTree(char stop){
		setStop(stop);
		root=create();
	}
	/**
	 * ����������
	 * @return ���ڵ�
	 */
	public BinTreeNode create(){
		BinTreeNode t,l,r;
		char item;
		Scanner sc=new Scanner(System.in);
		item=sc.next().charAt(0);
		if(item==stop){
			t=null;
			return t;
		}
		else{
			t=new BinTreeNode(item, null, null);
			l=create();
			t.left=l;
			r=create();
			t.right=r;
			return t;
		}
	}
	//����
	public static void main(String[] args){
		//��������Ϊ��abzcdzzzefzzz
		BinTree bt=new BinTree(null);
		bt.createBinTree('z');
		System.out.println("�ȸ�������");
		bt.preOrder(bt.root);
		System.out.println();
		bt.NorecPreOrder(bt.root);
		System.out.println();
		System.out.println("�и�������");
		bt.inOrder(bt.root);
		System.out.println();
		bt.norecInOrder(bt.root);
		System.out.println();
		System.out.println("���������");
		bt.postOrder(bt.root);
		System.out.println();
		bt.norecPostOrder(bt.root);
		System.out.println();
		System.out.println("��α�����");
		bt.levelOrder(bt.root);
		System.out.println();
		BinTreeNode btn=bt.find(bt.root, 'd');
		System.out.println("d�ĸ��ڵ�Ϊ��");
		System.out.println(bt.getFather(bt.root, btn).data);
		bt.delSubTree(bt.find(bt.root, 'c'));
		System.out.println("ɾ���������ȸ�������");
		bt.preOrder(bt.root);
		
	}
}
