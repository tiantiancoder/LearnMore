package datastructure;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;
import java.util.HashMap;
import java.util.Map;

import javax.swing.text.html.HTMLDocument.Iterator;
public class sample {
//	public static void main(String[] args) throws Exception { // �쳣�׳���������
//		// ��1����ʹ��File���ҵ�һ���ļ�
//		File f = new File("d:" + File.separator + "test.txt"); // ����File����
//		// ��2����ͨ������ʵ�����������
//		InputStream input = null; 		// ׼����һ������Ķ���
//		input = new FileInputStream(f); 		// ͨ�������̬�ԣ�����ʵ����
//		// ��3�������ж�����
//		byte b[] = new byte[(int) f.length()]; 	// ���е����ݶ���������֮��
//		for (int i = 0; i < b.length; i++) {
//			b[i] = (byte)input.read(); 	// �����ݶ���
//		}
//		// ��4�����ر�������
//		input.close(); 				// �ر�������
//		System.out.println("����Ϊ��" + new String(b)); // ��byte�����Ϊ�ַ������
//	}
	public static void main(String args[]){
		
		
		
		//test b=new test();
		ArrayList<Integer> a=new ArrayList<Integer>();
		a.add(5);
		a.add(6);
		a.add(7);
		a.add(8);
		java.util.Iterator<Integer> it=a.iterator();
		while(it.hasNext()){
			if(it.next().intValue()==6){
				it.remove();
			}
		}
	}
}