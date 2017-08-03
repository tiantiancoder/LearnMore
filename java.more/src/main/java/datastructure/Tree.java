package datastructure;
import java.util.Scanner;

/**
 * ��
 * @author liangxiamoyi
 *
 */
public class Tree {
	/**
	 * ���ڵ�
	 */
	private TreeNode root;
	/**
	 * ������ֹ��
	 */
	private char stop;
	/**
	 * ���췽��
	 */
	public Tree(){
		this.root=null;
	}
	/**
	 * ����tΪ���ڵ�����У�����p��ָ���ĸ��ڵ�
	 * @param t ���ڵ�
	 * @param p 
	 * @return p�ĸ��ڵ�
	 */
	public TreeNode findFather(TreeNode t,TreeNode p){
		if(t==null||p==null)return null;
		TreeNode result=null;
		TreeNode q=t.firstChild;
		while(q!=null&&q!=p){
			result=findFather(q,p);
			if(result==null)q=q.nextBrother;
			else return result;
		}
		if(q==p)return t;
		else return null;
	}
	/**
	 * �ڸ��ڵ�Ϊt�����У�����������Ϊitem�Ľ��
	 * @param t ���ڵ�
	 * @param item ������
	 * @return ������Ϊitem�Ľ��
	 */
	public TreeNode find(TreeNode t,char item){
		if(t==null)return null;
		TreeNode result=null;
		if(t.data==item)return t;
		else{
			TreeNode p=t.firstChild;
			while(p!=null&&(result=find(p,item))==null){
				p=p.nextBrother;
			}
			return result;
		}
	}
	/**
	 * ����tΪ���ڵ������ɾ����pΪ��������
	 * @param t ���ڵ�
	 * @param p �����ĸ�
	 */
	public void delSubTree(TreeNode t,TreeNode p){
		if(t!=null&& p!=null){
			TreeNode q=null;
			TreeNode result=null;
			result=findFather(t, p);
			if(result!=null){
				if(result.firstChild==p){
					result.firstChild=p.nextBrother;
					return;
				}
				else{
					q=result.firstChild;
					while(q.nextBrother!=p)q=q.nextBrother;
					q.nextBrother=p.nextBrother;
					return ;
				}
			}
			else{
				root=null;
			}
		}
	}
	/**
	 * �ȸ�����
	 * @param t ���ڵ�
	 */
	public void preOrder(TreeNode t){
		if(t!=null){
			System.out.print(t.data+" ");
			TreeNode child=null;
			child=t.firstChild;
			while(child!=null){
				preOrder(child);
				child=child.nextBrother;
			}
		}
	}
	/**
	 * �ǵݹ��ȸ�����
	 * @param t ���ڵ�
	 */
	public void norecPreOrder(TreeNode t){
		if(t==null)return;
		AStack<TreeNode> s=new AStack<>(20);
		TreeNode p=t;
		do{
			while(p!=null){
				System.out.print(p.data+" ");
				s.push(p);
				p=p.firstChild;
			}
			while(p==null&&!s.isEmpty()){
				p=s.pop();
				p=p.nextBrother;
			}
		}while(!s.isEmpty());
	}
	/**
	 * ��α���
	 * @param t ���ڵ�
	 */
	public void levelOrder(TreeNode t){
		AQueue<TreeNode> a=new AQueue<TreeNode>(20);
		if(t!=null){
			TreeNode p;
			a.insert(t);
			while(!a.isEmpty()){
				p=a.delete();
				System.out.print(p.data+" ");
				p=p.firstChild;
				while(p!=null){
					a.insert(p);
					p=p.nextBrother;
				}
			}
		}
	}
	public void createTree(char stop){
		this.stop=stop;
		this.root=create();
	}
	/**
	 * <p>����һ����</p>
	 * <p>��firstChild��Ϊ���ӣ���nextBrother�����Һ���</p>
	 * <p>����ʱ���������ȸ�������˳������</p>
	 * @return ���ڵ�
	 */
	public TreeNode create(){
		TreeNode t,l,r;
		char item;
		Scanner sc=new Scanner(System.in);
		item=sc.next().charAt(0);
		if(item==stop){
			t=null;
			return t;
		}
		else{
			t=new TreeNode(item, null, null);
			l=create();
			t.firstChild=l;
			r=create();
			t.nextBrother=r;
			return t;
		}
	}
	//����
	public static void main(String[] args){
//		��������
//		a
//		b
//		z
//		c
//		e
//		z
//		f
//		z
//		z
//		d
//		z
//		z
//		z
		Tree tree=new Tree();
		tree.createTree('z');
		System.out.println("�ȸ�������");
		tree.preOrder(tree.root);
		System.out.println();
		System.out.println("�ǵݹ��ȸ�������");
		tree.norecPreOrder(tree.root);
		System.out.println();
		System.out.println("��α�����");
		tree.levelOrder(tree.root);
		System.out.println();
		System.out.println("�����������ѯ�Ľ������ݣ�");
		Scanner sc=new Scanner(System.in);
		char item=sc.next().charAt(0);
		System.out.println("����㣺");
		System.out.println(tree.findFather(tree.root, tree.find(tree.root, item)).data);
	}
}
