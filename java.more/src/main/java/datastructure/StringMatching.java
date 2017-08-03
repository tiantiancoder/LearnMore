package datastructure;
/**
 * ģʽƥ���㷨KMP
 * @author liangxiamoyi
 *
 */
public class StringMatching {
	/**
	 * ģʽ��
	 */
	private String pat;
	/**
	 * Ŀ�괮
	 */
	private String str;
	/**
	 * ƥ��ʧ��ʱӦ���ݵ�λ�ñ�����������
	 */
	private int[] f;
	/**
	 * ���췽��
	 * @param pat ģʽ��
	 * @param str Ŀ�괮
	 */
	public StringMatching(String pat,String str){
		this.pat=pat;
		this.str=str;
		f=new int[pat.length()];
		fail();
	}
	/**
	 * ��ӡf����
	 */
	public void getf(){
		for(int i=0;i<f.length;i++)
			System.out.print(f[i]+" ");
	}
	/**
	 * <p>ʧ�ܺ���</p>
	 * ȷ��ƥ��ʧ��ʱ����λ��
	 */
	public void fail(){
		f[0]=-1;//����ֵ
		int i;
		for(int j=1;j<pat.length();j++){
			i=f[j-1];//i����ǰһ��ʧ�ܺ���ֵ
			while((pat.toCharArray()[j]!=pat.toCharArray()[i+1])&& i>0){
				//������ǰ����ȵ��ִ�
				i=f[i];
			}
			if(pat.toCharArray()[j]==pat.toCharArray()[i+1]){
				f[j]=i+1;
			}
			else{
				f[j]=-1;
			}
		}
	}
	/**
	 * ����ƥ�䣬�ҵ�ģʽ����Ŀ�괮�е�һ�γ��ֵ�λ��
	 * @return ����һ����������ʾģʽ���״γ��ֵ�λ��
	 */
	public int fastFind(){
		/**
		 * ģʽ����ɨ��ָ��ĳ�ʼλ��
		 */
		int p=0;
		/**
		 * Ŀ�괮��ɨ��ָ��ĳ�ʼλ��
		 */
		int s=0;
		
		while(p<pat.length()&& s<str.length()){
			if(pat.toCharArray()[p]==str.toCharArray()[s]){
				p++;
				s++;
			}
			else if(p==0){//�����һ���ַ�ƥ��ʧ�ܣ����str����һ���ַ���ʼ
				s++;
			}
			else{
				p=f[p-1]+1;//ƥ��ʧ��ʱ��ʧ�ܺ���ȷ�����ݵ�λ��
			}
		}
		if(p<pat.length()){
			return -1;
		}
		return s-pat.length();
	}
	//����
	public static void main(String[] args){
		StringMatching s=new StringMatching("abab", "abababab");
		System.out.println("ģʽ���״γ��ֵ�λ��Ϊ��"+s.fastFind());
		StringMatching a=new StringMatching("abab", "abaaabab");
		System.out.println("ģʽ���״γ��ֵ�λ��Ϊ��"+a.fastFind());
		System.out.println("ʧ�ܺ�������ֵΪ��");
		a.getf();
	}
}
