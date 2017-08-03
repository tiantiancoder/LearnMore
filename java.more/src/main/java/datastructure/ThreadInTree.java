package datastructure;

import java.util.Scanner;

/**
 * ��������������
 * @author liangxiamoyi
 *
 */
public class ThreadInTree {
	/**
	 * ���ڵ�
	 */
	private ThreadNode root;
	/**
	 * ��ֹ�ַ�
	 */
	private char stop;
	/**
	 * �����������������,ָ���ϴα����Ľ��
	 */
	private ThreadNode pre;
	/**
	 * ���췽��
	 * @param t ���ڵ�
	 */
	public ThreadInTree(ThreadNode t){
		this.root=t;
		this.pre=null;
	}
	/**
	 * �����Խ��tΪ���ڵ�������������������и����еĵ�һ�����
	 * @param t ���ڵ�
	 * @return ���
	 */
	public ThreadNode firstInOrder(ThreadNode t){
		if(t==null){
			return null;
		}
		ThreadNode q=t;
		while(q.lThread==0){
			q=q.left;
		}
		return q;
	}
	/**
	 * �����Խ��tΪ���ڵ�������������������и����е����һ�����
	 * @param t ���ڵ�
	 * @return ���
	 */
	public ThreadNode lastInOrder(ThreadNode t){
		if(t==null){
			return null;
		}
		ThreadNode q=t;
		while(q.rThread==0){
			q=q.right;
		}
		return q;
	}
	/**
	 * ��������tΪ��������������������p���и�ǰ�����
	 * @param t �����
	 * @param p ���
	 * @return ǰ�����
	 */
	public ThreadNode preInOrder(ThreadNode t,ThreadNode p){
		if(t==null||p==null){
			return null;
		}
		ThreadNode q;
		if(p==firstInOrder(t))return null;
		if(p.lThread==1)return p.left;
		return lastInOrder(p.left);
	}
	/**
	 * ��������tΪ��������������������p���и���̽��
	 * @param t �����
	 * @param p ���
	 * @return ��̽��
	 */
	public ThreadNode postInOrder(ThreadNode t,ThreadNode p){
		if(t==null||p==null)return null;
		ThreadNode q;
		if(p==lastInOrder(t))return null;
		if(p.rThread==1)return p.right;
		return firstInOrder(p.right);
	}
	/**
	 * �и�������tΪ������������������
	 * @param t �����
	 */
	public void inOrder(ThreadNode t){
		if(t==null)return;
		ThreadNode q;
		for(q=firstInOrder(t);q!=null;q=postInOrder(t, q))
			System.out.print(q.data+" ");
	}
	/**
	 * ������p��Ϊ���s�����ӽڵ�
	 * @param p ����Ľ��
	 * @param s
	 */
	public void insertRight(ThreadNode p,ThreadNode s){
		if(s==null||p==null)return;
		p.right=s.right;
		p.rThread=s.rThread;
		p.left=s;
		p.lThread=1;
		if(s.rThread==0){
			ThreadNode q=s.right;
			q=firstInOrder(q);//��qΪs����������������
			q.left=p;//��q��ǰ��ָ��ָ��p
		}
		s.right=p;
		s.rThread=0;	
	}
	/**
	 * ������p��Ϊ���s�����ӽڵ�
	 * @param p ����Ľ��
	 * @param s
	 */
	public void insertLeft(ThreadNode p,ThreadNode s){
		if(s==null||p==null)return;
		p.left=s.left;
		p.lThread=s.lThread;
		p.right=s;
		p.rThread=1;
		if(s.lThread==1&&s.left!=null){
			s.left.right=p;
		}
		if(s.lThread==0){
			lastInOrder(s.left).right=p;//��p�������������ҽ��ĺ��ָ��ָ��p
		}
		s.left=p;
		s.lThread=0;	
	}
	/**
	 * ɾ�����s�����ӽڵ�p
	 * @param p
	 * @param s
	 */
	public void deleteRight(ThreadNode p,ThreadNode s){
		if(s==null||p==null)return;
		if(p.lThread==1&&p.rThread==1){
			s.right=p.right;
			s.rThread=1;
		}
		if(p.lThread==1&&p.rThread==0){
			ThreadNode temp=firstInOrder(p.right);
			s.right=p.right;
			temp.left=s;
		}
		if(p.lThread==0&&p.rThread==1){
			ThreadNode temp=lastInOrder(p.left);
			s.right=p.left;
			temp.right=p.right;
		}
		if(p.lThread==0&&p.rThread==0){
			ThreadNode temp1=firstInOrder(p.right);
			ThreadNode temp=lastInOrder(p.left);
			temp.right=p.right;
			temp.rThread=0;
			s.right=p.left;
			temp1.left=temp;
		}
	}
	/**
	 * ɾ�����s�����ӽڵ�p
	 * @param p
	 * @param s
	 */
	public void deleteLeft(ThreadNode p,ThreadNode s){
		if(s==null||p==null)return;
		if(p.lThread==1&&p.rThread==1){
			s.left=p.left;
			s.lThread=1;
		}
		if(p.lThread==0&&p.rThread==1){
			ThreadNode temp=lastInOrder(p.left);
			s.left=p.left;
			temp.right=s;
		}
		if(p.lThread==1&&p.rThread==0){
			ThreadNode temp=firstInOrder(p.right);
			s.left=p.right;
			temp.left=p.left;
		}
		if(p.lThread==0&&p.rThread==0){
			ThreadNode temp1=lastInOrder(p.left);
			ThreadNode temp=firstInOrder(p.right);
			temp1.right=p.right;
			temp1.rThread=0;
			s.left=p.left;
			temp.left=temp1;
		}
	}
	/**
	 * ������rootΪ���ڵ����������������
	 * @param stop ������ֹ��
	 */
	public void createThreadInTree(char stop){
		this.stop=stop;
		this.root=create();
		ThreadInTree(this.root);
	}
	/**
	 * ������rootΪ��������δ�������Ķ�����
	 * @return ���ڵ�
	 */
	public ThreadNode create(){
		 ThreadNode t,l,r;
		char item;
		Scanner sc=new Scanner(System.in);
		item=sc.next().charAt(0);
		if(item==stop){
			t=null;
			return t;
		}
		else{
			t=new ThreadNode(item);
			l=create();
			t.left=l;
			r=create();
			t.right=r;
			return t;
		}
	}
	/**
	 * ����rootΪ���ڵ�Ķ�������Ϊ��������������
	 */
	public void ThreadInTree(ThreadNode t){
		if(t==null)return;
		ThreadInTree(t.left);
		if(t.left==null){//������Ϊ�գ�����ǰ�����
			t.lThread=1;
			if(pre!=null){
				t.left=pre;
			}
		}
		if(t.right==null){//�ȱ�ǣ�����һ�α������ú�̽��
			t.rThread=1;
		}
		if(pre!=null&&pre.rThread==1){//Ϊ�ϴα���ǵĽ�����ú�̽��
			pre.right=t;
		}
		pre=t;//��¼��ǰ���
		ThreadInTree(t.right);//������
	}
	/**
	 * �����������ĵ�һ�����
	 * @return
	 */
	public ThreadNode getFirst(){
		return firstInOrder(root);
	}
	/**
	 * ���������������һ�����
	 * @return
	 */
	public ThreadNode getLast(){
		return lastInOrder(root);
	}
	/**
	 * ��������rootΪ������������Ϊitem�Ľ��
	 * @param root
	 * @param item
	 * @return
	 */
	public ThreadNode find(ThreadNode root,char item){
		if(root==null)return null;
		ThreadNode q;
		for(q=firstInOrder(root);q!=null;q=postInOrder(root, q)){
			if(q.data==item){
				return q;
			}
		}
		return null;
	}
	
	public static void main(String[] args){
		ThreadInTree tit=new ThreadInTree(null);
		tit.createThreadInTree('z');
		System.out.println("��������ĵ�һ���ڵ�Ϊ��");
		System.out.println(tit.getFirst().data);
		System.out.println("������������һ����㣺");
		System.out.println(tit.getLast().data);
		System.out.println("�������Ϊ��");
		tit.inOrder(tit.root);
		System.out.println("");
		ThreadNode a=new ThreadNode('g');
		ThreadNode c=new ThreadNode('h');
		ThreadNode b=tit.find(tit.root, 'c');
		System.out.println("��c�������ӽڵ�g�����������");
		tit.insertLeft(a,b);
		tit.inOrder(tit.root);
		System.out.println("");
		System.out.println("��c�������ӽڵ�h�����������");
		tit.insertRight(c,b);
		tit.inOrder(tit.root);
		System.out.println("");
		tit.deleteLeft(tit.find(tit.root, 'e'), tit.find(tit.root, 'd'));
		System.out.println("dɾ�����ӽڵ�e�����������");
		tit.inOrder(tit.root);
		System.out.println("");
		tit.deleteRight(tit.find(tit.root, 'h'), tit.find(tit.root, 'c'));
		System.out.println("cɾ�����ӽڵ�h�����������");
		tit.inOrder(tit.root);
	}
}

