package datastructure;
import java.util.Scanner;

/**
 *
 */
public class Graph_List {

	private Vertex[] head;

	private int vertexNum;

	public Graph_List(){
		int e,from,to,weight;
		Scanner sc=new Scanner(System.in);
		System.out.println(";");
		this.vertexNum=sc.nextInt();
		this.head=new Vertex[this.vertexNum];
		for(int i=0;i<this.vertexNum;i++){
			head[i]=new Vertex();
			head[i].verName=i;
			head[i].adjacent=null;
		}
		System.out.println("");
	    e=sc.nextInt();
	    System.out.println("");
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
	 *
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
	 *
	 */
	public int getFirstNeighbor(int v){
		if(v==-1)return -1;
		Edge p=head[v].adjacent;
		if(p!=null)return p.verAdj;
		else return -1;
	}
	/**
	 *
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
	 *
	 */
	public void depthFirstSearch(){
		int[] visited=new int[this.vertexNum];
		for(int i=0;i<this.vertexNum;i++){
			visited[i]=0;
		}
		recDepthFirstSearch(0,visited);
	}

	public void recDepthFirstSearch(int v,int[] visited){
		System.out.print(v+" ");
		visited[v]=1;
		int w=this.getFirstNeighbor(v);
		while(w!=-1){
			if(visited[w]==0){//
				recDepthFirstSearch(w, visited);
			}
			w=getNextNeighbor(v, w);//
		}
	}

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
				if(visited[k]==0)s.push(k);//
				k=getNextNeighbor(w, k);
			}
		}
	}
	/**
	 *
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
	 *
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
			if(count[i]==0){//
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
			top=count[top];//
			System.out.print(j+" ");
			Edge p=head[j].adjacent;
			while(p!=null){//
				int k=p.verAdj;
				if(--count[k]==0){//
					count[k]=top;
					top=k;
				}
				p=p.link;
			}
		}
	}
	/**
	 *
	 */
	public void criticalPath(){
		int i,k,e,l;
		int n=this.vertexNum;
		int[] ve=new int[n];//
		int[] vl=new int[n];//
		for(i=0;i<n;i++){
			ve[i]=0;
		}
		for(i=0;i<n;i++){//
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
		for(i=n-2;i>=0;i--){//
			Edge p=head[i].adjacent;
			while(p!=null){
				k=p.verAdj;
				if(vl[k]-p.cost<vl[i])vl[i]=vl[k]-p.cost;
				p=p.link;
			}
		}
		for(i=0;i<n;i++){//
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
	 *
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
		while(!q.isEmpty()){//
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
			System.out.println(v+""+i+""+dist[i]+" "+""+st);
		}
	}
	/**
	 *
	 */
	public void dShortestPath(int v){
		int u,k;
		int max=1000;
		Edge p;
		int n=this.vertexNum;
		int[] path=new int[n];
		int[] dist=new int[n];
		int[] s=new int[n];//
		for(int i=0;i<n;i++){
			path[i]=-1;
			dist[i]=max;
			s[i]=0;
		}
		dist[v]=0;s[v]=1;
		p=head[v].adjacent;
		u=v;//
		for(int j=0;j<n;j++){
			while(p!=null){
				k=p.verAdj;
				if(s[k]!=1&&dist[u]+p.cost<dist[k]){
					dist[k]=dist[u]+p.cost;
					path[k]=u;
				}
				p=p.link;
			}
			//
			int ldist=max;
			for(int i=0;i<n;i++){
				if(dist[i]>0&&dist[i]<ldist&&s[i]==0){
					ldist=dist[i];
					u=i;
				}
			}
			s[u]=1;//
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
			System.out.println(v+""+i+""+dist[i]+" "+""+st);
		}
	}
	public static void main(String[] args){

	}
}
