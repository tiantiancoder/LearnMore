package datastructure;
import java.util.Scanner;

/**
 * �ڽӱ�洢��ͼ
 * @author liangxiamoyi
 *
 */
public class Graph_List {
	/**
	 * ָ�򶥵�������
	 */
	private Vertex[] head;
	/*
	 * ��ǰ����ĸ���
	 */
	private int vertexNum;
	/**
	 * ���췽��
	 */
	public Graph_List(){
		int e,from,to,weight;
		Scanner sc=new Scanner(System.in);
		System.out.println("�����붥�����;");
		this.vertexNum=sc.nextInt();
		this.head=new Vertex[this.vertexNum];
		for(int i=0;i<this.vertexNum;i++){
			head[i]=new Vertex();
			head[i].verName=i;
			head[i].adjacent=null;
		}
		System.out.println("������ߵĸ�����");
	    e=sc.nextInt();
	    System.out.println("����������ߵ���㣬�յ��Ȩֵ��");
	    for(int i=0;i<e;i++){
	    	from=sc.nextInt();
	    	to=sc.nextInt();
	    	weight=sc.nextInt();
	    	Edge p=new Edge();
	    	p.verAdj=to;
	    	p.cost=weight;
	    	p.link=null;
	    	Edge q=head[from].adjacent;
	    	if(q==null){
	    		head[from].adjacent=p;
	    	}
	    	else{
	    		while(q.link!=null){
	    			q=q.link;
	    		}
	    		q.link=p;
	    	}
	    }	
	}
	/**
	 * ���ָ���ߵ�Ȩֵ
	 * @param v1 ����v1
	 * @param v2 ����v2
	 * @return Ȩֵ
	 */
	public int getWeight(int v1,int v2){
		if(v1==-1||v2==-1)return -1;
		Edge p=head[v1].adjacent;
		while(p!=null){
			if(p.verAdj==v2)return p.cost;
			p=p.link;
		}
		return -1;
	}
	/**
	 * ���v�ĵ�һ���ڽӶ���
	 * @param v ����v
	 * @return ��һ���ڽӶ�������
	 */
	public int getFirstNeighbor(int v){
		if(v==-1)return -1;
		Edge p=head[v].adjacent;
		if(p!=null)return p.verAdj;
		else return -1;
	}
	/**
	 * ���v1�����v2�ĵ�һ������
	 * @param v1 ����v1
	 * @param v2 ����v2
	 * @return ��������
	 */
	public int getNextNeighbor(int v1,int v2){
		if(v1==-1||v2==-1)return -1;
		Edge p=head[v1].adjacent;
		while(p.verAdj!=v2&&p!=null){
			p=p.link;
		}
		if(p==null)return -1;
		p=p.link;
		if(p==null)return -1;
		return p.verAdj;
	}
	/**
	 * ͼ��������ȱ���
	 */
	public void depthFirstSearch(){
		int[] visited=new int[this.vertexNum];
		for(int i=0;i<this.vertexNum;i++){
			visited[i]=0;
		}
		recDepthFirstSearch(0,visited);
	}
	/**
	 * �Ӷ���v����������ȱ����ĵݹ��㷨
	 * @param v ����v
	 * @param visited ��ʶ������������
	 */
	public void recDepthFirstSearch(int v,int[] visited){
		System.out.print(v+" ");
		visited[v]=1;
		int w=this.getFirstNeighbor(v);
		while(w!=-1){
			if(visited[w]==0){//��wδ�����ʹ�����w�ݹ����
				recDepthFirstSearch(w, visited);
			}
			w=getNextNeighbor(v, w);//wΪv���w����һ������
		}
	}
	/**
	 * ������ȱ����ĵ����㷨
	 * @param v ��ʼ����v
	 */
	public void iteDepthFirstSearch(int v){
		int[] visited=new int[this.vertexNum];
		for(int i=0;i<this.vertexNum;i++){
			visited[i]=0;
		}
		AStack<Integer> s=new AStack<Integer>(20);
		s.push(v);
		int w;
		while(!s.isEmpty()){
			w=s.pop();
			visited[w]=1;
			System.out.print(w+" ");
			int k=getFirstNeighbor(w);
			while(k!=-1){
				if(visited[k]==0)s.push(k);//û�����ʹ���ѹջ
				k=getNextNeighbor(w, k);
			}
		}
	}
	/**
	 * ������ȱ����ĵ����㷨
	 * @param v ��ʼ����v
	 */
	public void broadFirstSearch(int v){
		int[] visited=new int[this.vertexNum];
		for(int i=0;i<this.vertexNum;i++){
			visited[i]=0;
		}
		System.out.print(v+" ");
		visited[v]=1;
		AQueue<Integer> a=new AQueue<Integer>(20);
		a.insert(v);
		while(!a.isEmpty()){
			int q=a.delete();
			int p=getFirstNeighbor(q);
			while(p!=-1){
				if(visited[p]==0){
					System.out.print(p+" ");
					visited[p]=1;
					a.insert(p);
				}
				p=getNextNeighbor(q, p);
			}
		}
	}
	/**
	 * �Ժ���n�������AOV��������������
	 */
	public void topoOrder(){
		int n=this.vertexNum;
		int[] count=new int[n];
		for(int i=0;i<n;i++){
			count[i]=0;
		}
		for(int i=0;i<n;i++){
			Edge p=head[i].adjacent;
			while(p!=null){
				count[p.verAdj]++;
				p=p.link;
			}
		}
		int top=-1;
		for(int i=0;i<n;i++){
			if(count[i]==0){//�����Ϊ0 �Ķ�����ջ
				count[i]=top;
				top=i;
			}
		}
		for(int i=0;i<n;i++){
			if(top==-1){
				System.out.println("a cycle in the network");
				return;
			}
			int j=top;
			top=count[top];//��ջ
			System.out.print(j+" ");
			Edge p=head[j].adjacent;
			while(p!=null){//ɾ����j�����ı�
				int k=p.verAdj;
				if(--count[k]==0){//k����ȼ�1����Ϊ0��k��ջ
					count[k]=top;
					top=k;
				}
				p=p.link;
			}
		}
	}
	/**
	 * ����ؼ�·��
	 */
	public void criticalPath(){
		int i,k,e,l;
		int n=this.vertexNum;
		int[] ve=new int[n];//�¼������緢��ʱ��
		int[] vl=new int[n];//�¼�����ٷ���ʱ��
		for(i=0;i<n;i++){
			ve[i]=0;
		}
		for(i=0;i<n;i++){//������˳�������¼���������緢��ʱ��
			Edge p=head[i].adjacent;
			while(p!=null){
				k=p.verAdj;
				if(ve[i]+p.cost>ve[k])ve[k]=ve[i]+p.cost;
				p=p.link;
			}
		}
		for(i=0;i<n;i++){
			vl[i]=ve[n-1];
		}
		for(i=n-2;i>=0;i--){//��������¼�����ٷ���ʱ��
			Edge p=head[i].adjacent;
			while(p!=null){
				k=p.verAdj;
				if(vl[k]-p.cost<vl[i])vl[i]=vl[k]-p.cost;
				p=p.link;
			}
		}
		for(i=0;i<n;i++){//���������翪ʼʱ�������ʼʱ��
			Edge p=head[i].adjacent;
			while(p!=null){
				k=p.verAdj;
				e=ve[i];
				l=vl[k]-p.cost;
				if(l==e){
					System.out.println(i+"-"+k);
				}
				p=p.link;
			}
		}
	}
	/**
	 * <p>��Ȩ��Դ���·���㷨</p>
	 * <p>�󶥵�v����������������·��</p>
	 * @param v ����v
	 */
	public void shortestPath(int v){
		int u,k;
		Edge p;
		int n=this.vertexNum;
		int[] path=new int[n];
		int[] dist=new int[n];
		AQueue<Integer> q=new AQueue<Integer>(20);
		for(int i=0;i<n;i++){
			path[i]=-1;
			dist[i]=-1;
		}
		dist[v]=0;
		q.insert(v);
		while(!q.isEmpty()){//��u��δ���ʵĶ�����ӣ��޸���path��distֵ
			u=q.delete();
			p=head[u].adjacent;
			while(p!=null){
				k=p.verAdj;
				if(dist[k]==-1){
					q.insert(k);
					dist[k]=dist[u]+1;
					path[k]=u;
				}
				p=p.link;
			}
		}
		for(int i=0;i<n;i++){
			int w=i;
			StringBuffer st=new StringBuffer();
			st.append(i);
			while(path[w]!=v&&path[w]!=-1){
				int temp=path[w];
				st.append(" "+temp);
				w=path[w];
			}
			st.append(" "+v);
			st.reverse();
			System.out.println(v+"��"+i+"���·�����ȣ�"+dist[i]+" "+"���·����"+st);
		}
	}
	/**
	 * <p>�����Ȩ��Դ���·�������Dijkstra�㷨</p>
	 * <p>��ӳ�ʼ����v����������������·��</p>
	 * @param v ��ʼ����
	 */
	public void dShortestPath(int v){
		int u,k;
		int max=1000;
		Edge p;
		int n=this.vertexNum;
		int[] path=new int[n];
		int[] dist=new int[n];
		int[] s=new int[n];//��¼i�Ƿ񱻷��ʹ�
		for(int i=0;i<n;i++){
			path[i]=-1;
			dist[i]=max;
			s[i]=0;
		}
		dist[v]=0;s[v]=1;
		p=head[v].adjacent;
		u=v;//uΪ�������ʵĶ���
		for(int j=0;j<n;j++){
			while(p!=null){
				k=p.verAdj;
				if(s[k]!=1&&dist[u]+p.cost<dist[k]){
					dist[k]=dist[u]+p.cost;
					path[k]=u;
				}
				p=p.link;
			}
			//ȷ�����������ʵĶ���u
			int ldist=max;
			for(int i=0;i<n;i++){
				if(dist[i]>0&&dist[i]<ldist&&s[i]==0){
					ldist=dist[i];
					u=i;
				}
			}
			s[u]=1;//����u����
			p=head[u].adjacent;
		}
		for(int i=0;i<n;i++){
			int w=i;
			StringBuffer st=new StringBuffer();
			st.append(i);
			while(path[w]!=v&&path[w]!=-1){
				st.append(" "+path[w]);
				w=path[w];
			}
			st.append(" "+v);
			st.reverse();
			System.out.println(v+"��"+i+"���·�����ȣ�"+dist[i]+" "+"���·����"+st);
		}
	}
	//����
	public static void main(String[] args){
//		��������
//		7
//		9
//		0  1 1 1 2 5 2 3 9 2 4 6 3 4 8 4 5 7 5 0 4 6 5 3 0 6 2
//		5
//		6
//		0 1 1 0 2 1 2 1 1 0 3 1 3 4 1 2 4 1 
//		9
//		12
//		0 1 6 0 2 4 0 3 5 1 4 1 2 4 1 2 5 1 3 5 2 4 6 9 4 7 8 5 7 4 6 8 2 7 8 4
//		7
//		12
//		0 1 1 2 0 1 0 3 1 1 3 1 1 4 1 3 2 1 3 4 1 2 5 1 3 5 1 3 6 1 4 6 1 6 5 1
//		6
//		20
//		0 1 12 1 0 12 0 2 5 2 0 5 1 2 5 2 1 5 0 3 8 3 0 8 1 4 20 4 1 20 2 3 6 3 2 6 2 4 10 4 2 10 2 5 8 5 2 8 3 5 4 5 3 4 4 5 9 5 4 9
		Graph_List g=new Graph_List();
		System.out.println("�ݹ�������ȱ�����");
		g.depthFirstSearch();
		System.out.println("����������ȱ�����");
		g.iteDepthFirstSearch(0);
		System.out.println("������ȱ�����");
		g.broadFirstSearch(0);
		Graph_List gl=new Graph_List();
		System.out.println("��������");
		gl.topoOrder();
		Graph_List gr=new Graph_List();
		System.out.println("�ؼ�·��Ϊ��");
		gr.criticalPath();
		Graph_List gs=new Graph_List();
		System.out.println("����0������������·��(��Ȩ)��");
		gs.shortestPath(0);
		Graph_List gt=new Graph_List();
		gt.dShortestPath(0);
	}
}
