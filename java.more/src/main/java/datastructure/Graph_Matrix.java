package datastructure;
import java.util.Scanner;

/**
 * �ڽӾ���洢��ͼ
 * @author liangxiamoyi
 *
 */
public class Graph_Matrix {
	/**
	 * ͼ����󶥵����
	 */
	public static int MAXGRAPHSIZE=256;
	/**
	 * ͼ�����Ȩֵ
	 */
	public static int MAXWEIGHT=1000;
	/**
	 * �ڽӾ���
	 */
	private int[][] edge;
	/**
	 * ��ǰͼ�ж������
	 */
	private int graphSize;
	/**
	 * ���췽��
	 */
	public Graph_Matrix(){
		this.edge=new int[MAXGRAPHSIZE][MAXGRAPHSIZE];
		System.out.println("�����붥�������");
		Scanner sc=new Scanner(System.in);
		this.graphSize=sc.nextInt();
		System.out.println("����������ÿ���ߵ�Ȩֵ���������Ϊ1000������СΪ0��");
		for(int i=0;i<this.graphSize;i++){
			for(int j=0;j<this.graphSize;j++){
				this.edge[i][j]=sc.nextInt();
			}
		}
	}
	/**
	 * ȡ�����Ϊv�Ķ���ĵ�һ���ڽӶ�������
	 * @param v ���v
	 * @return ��һ���ڽӶ�������
	 */
	public int getFirstNeighbor(int v){
		if(v==-1)return -1;
		for(int i=0;i<this.graphSize;i++){
			if(this.edge[v][i]>0&&this.edge[v][i]<MAXWEIGHT){
				return i;
			}
		}
		return -1;
	}
	/**
	 * ȡ��v1���������v2�������һ���ڽӶ���
	 * @param v1 ����v1
	 * @param v2 ����v2
	 * @return ��һ���ڽӶ�������
	 */
	public int getNextNeighbor(int v1,int v2){
		if(v1==-1||v2==-1){
			return -1;
		}
		for(int i=v2+1;i<this.graphSize;i++){
			if(this.edge[v1][i]>0&&this.edge[v1][i]<MAXWEIGHT){
				return i;
			}
		}
		return -1;
	}
	/**
	 * ����һ������
	 */
	public void insertVertex(){
		for(int i=0;i<this.graphSize;i++){
			this.edge[i][graphSize]=MAXWEIGHT;
		}
		for(int i=0;i<this.graphSize;i++){
			this.edge[graphSize][i]=MAXWEIGHT;
		}
		this.edge[graphSize][graphSize]=0;
		this.graphSize++;
		System.out.println("����ɹ���");
		return ;
	}
	/**
	 * ɾ�� һ������
	 * @param v ɾ���Ķ���
	 */
	public void deleteVertex(int v){
		if(v>=this.graphSize){
			System.out.println("�޴˶���");
			return;
		}
		for(int i=0;i<this.graphSize;i++){
			this.edge[v][i]=0;
			this.edge[i][v]=0;
		}
		if(v==this.graphSize-1){
			this.graphSize--;
			return;
		}
		for(int i=v+1;i<this.graphSize;i++){
			for(int j=0;j<this.graphSize;j++){
				this.edge[i-1][j]=this.edge[i][j];
			}
		}
		this.graphSize--;
	}
	/**
	 * ����һ����
	 * @param v1 ���
	 * @param v2 �յ�
	 * @param weight Ȩֵ
	 */
	public void insertEdge(int v1,int v2,int weight){
		if(v1==v2||v1>this.graphSize||v2>this.graphSize||this.edge[v1][v2]!=MAXWEIGHT){
			System.out.println("����ʧ�ܣ�");
			return;
		}
		this.edge[v1][v2]=weight;
		System.out.println("����ɹ���");
		return;
	}
	/**
	 * ɾ��һ����
	 * @param v1 ���
	 * @param v2 �յ�
	 */
	public void deleteEdge(int v1,int v2){
		if(v1==v2||v1>this.graphSize||v2>this.graphSize||this.edge[v1][v2]==MAXWEIGHT){
			System.out.println("ɾ��ʧ�ܣ�");
			return;
		}
		this.edge[v1][v2]=MAXWEIGHT;
		System.out.println("ɾ���ɹ�");
		return;
	}
	/**
	 * ��ͼ��������������·��
	 */
	public void allLengths(){
		int n=this.graphSize;
		int[][] path=new int[n][n];//��Ӧ·���϶���j��ǰһ������
		int[][] a=new int[n][n];//�������·������
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				a[i][j]=edge[i][j];
				if(i!=j&&a[i][j]<MAXGRAPHSIZE)path[i][j]=i;
				else path[i][j]=-1;
			}
		}
		for(int k=0;k<n;k++){
			for(int i=0;i<n;i++){
				if(i!=k){
					for(int j=0;j<n;j++){
						if(j!=k&&j!=i&&a[i][k]<MAXGRAPHSIZE&&a[k][j]<MAXGRAPHSIZE&&a[i][k]+a[k][j]<a[i][j]){
							a[i][j]=a[i][k]+a[k][j];
							path[i][j]=path[k][j];
						}
					}
				}
			}
		}
		//���
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				if(i!=j&&a[j][i]<MAXGRAPHSIZE){
					System.out.println("����"+j+"��"+"����"+i+"�����·�����ȣ�"+a[j][i]);
					System.out.print("���·��Ϊ��"+j);
					int k=j;
					while(path[i][k]!=i){
						System.out.print(" "+path[i][k]);
						k=path[i][k];
					}
					System.out.print(" "+i);
				}
				
			}
		}
	}
	/**
	 * �������Ȩ��ͨͼ������С֧������prim�㷨
	 */
	public void prim(){
		int n=this.graphSize;
		//Ȩֵ��С�ı���
		class LV{
			int lowCost;//Ȩֵ
			int vex;//�ߵ��յ�
		}
		LV[] closeEdge=new LV[n];
		//֧�����ı���
		class Edge{
			int head;//ͷ
			int tail;//β
			int cost;//Ȩֵ
		}
		Edge[] te=new Edge[n-1];
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				if(edge[i][j]==0)edge[i][j]=MAXWEIGHT;
			}
		}
		//�Զ���0Ϊ��ʼ����,��ʼ������closeEdge
		for(int i=0;i<n;i++){
			closeEdge[i]=new LV();
			closeEdge[i].lowCost=edge[0][i]; 
			closeEdge[i].vex=0;
		}
		closeEdge[0].vex=-1;//����0���뼯��
		int count=0;//֧�����ı���
		for(int i=0;i<n;i++){
			int min=MAXWEIGHT+1;
			int v=0;
			for(int j=0;j<n;j++){//��ǰȨֵ��С�ıߺ͸ñߵ��յ�v
				if(closeEdge[j].vex!=-1&&closeEdge[j].lowCost<min){
					v=j;
					min=closeEdge[j].lowCost;
				}
			}
			//��֧�����ı߼��������һ����
			if(v!=0){
				te[count]=new Edge();
				te[count].head=closeEdge[v].vex;
				te[count].tail=v;
				te[count].cost=closeEdge[v].lowCost;
				count++;
				//����v���뼯��U
				closeEdge[v].lowCost=0;//�޸���ֵ
				closeEdge[v].vex=-1;
				for(int j=0;j<n;j++){
					if(closeEdge[j].vex!=-1&&edge[v][j]<closeEdge[j].lowCost){
						closeEdge[j].lowCost=edge[v][j];
						closeEdge[j].vex=v;
					}
				}
			}
		}
		for(int i=0;i<n-1;i++){
			System.out.println("("+te[i].head+","+te[i].tail+","+te[i].cost+")");
		}
	}
	//����
	public static void main(String[] args){
//      ��������
//		0
//		2
//		7
//		1000
//		1
//		1000
//		0
//		1000
//		1000
//		1000
//		1000
//		1000
//		0
//		5
//		1000
//		1000
//		1000
//		1000
//		0
//		1000
//		3
//		1000
//		1000
//		4
//		0
		Graph_Matrix gm=new Graph_Matrix();
		System.out.println(gm.getFirstNeighbor(0));
		System.out.println(gm.getNextNeighbor(0, 1));
		gm.insertVertex();
		gm.insertEdge(5, 0, 2);
		gm.insertEdge(5, 1, 2);
		gm.insertEdge(5, 4, 2);
		System.out.println(gm.getFirstNeighbor(5));
		System.out.println(gm.getNextNeighbor(5, 0));
		gm.deleteEdge(5, 1);
		System.out.println(gm.getFirstNeighbor(5));
		System.out.println(gm.getNextNeighbor(5, 0));
		gm.deleteVertex(5);
		System.out.println(gm.getFirstNeighbor(5));
//		��������
//		0
//		1
//		4
//		3
//		1
//		0
//		1000
//		2
//		4
//		1000
//		0
//		5
//		3
//		2
//		5
//		0
		Graph_Matrix g=new Graph_Matrix();
		System.out.println("��С֧����Ϊ��");
		g.prim();
		
	}
}
