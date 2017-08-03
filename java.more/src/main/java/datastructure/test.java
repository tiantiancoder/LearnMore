package datastructure;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.html.HTMLDocument.Iterator;
public class test extends JFrame{
	public JButton display=null;
	public JTextField text=null;
	public JLabel content=null;
	public test(){
		
//		this.display=new JButton("��ʾ");
//		this.text=new JTextField("������");
//		this.content=new JLabel("����");
//		display.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				// TODO Auto-generated method stub
//				content.setText(text.getText());
//			}
//		});
//		this.setLayout(null);
//		this.text.setBounds(50, 0, 100, 25);
//		this.display.setBounds(160, 0, 50, 25);
//		this.display.setMargin(new Insets(0, 0, 0, 0));
//		this.content.setBounds(50, 30, 100, 25);
//		this.getContentPane().add(text);
//		this.getContentPane().add(display);
//		this.getContentPane().add(content);
////		
////		
//		this.setSize(500, 300);
//		this.setVisible(true);
//		this.setSize(400,400);
//		this.setTitle("shili");
//		CardLayout card=new CardLayout();
//		this.setLayout(card);
//		Container c=this.getContentPane();
//		c.add(new JLabel("1"),"first");
//		c.add(new JLabel("2"),"second");
//		c.add(new JLabel("3"),"third");
//		this.setVisible(true);
//		card.show(c,"first");
//		for(int i=1;i<9;i++){
//			try {
//				Thread.sleep(3000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			card.next(c);
//
//		}
//		
		
		
		
		this.setTitle("shiliyi");
		this.setSize(500, 500);
		this.setLayout(new BorderLayout());
		this.add(new JButton(),BorderLayout.NORTH);
		this.add(new JButton(),BorderLayout.EAST);
		this.add(new JButton(),BorderLayout.SOUTH);
		this.add(new JButton(),BorderLayout.WEST);
		Container a=new Container();
		a.setLayout(new BorderLayout());
		a.add(new JButton(),BorderLayout.NORTH);
		a.add(new JButton(),BorderLayout.EAST);
		a.add(new JButton(),BorderLayout.SOUTH);
		a.add(new JButton(),BorderLayout.WEST);
		a.add(new JButton(),BorderLayout.CENTER);
		this.add(a,BorderLayout.CENTER);
		this.setVisible(true);
		
		
		
		
	}
	public static void main(String args[]){
		//test b=new test();
		for(int i=2;i<=3;i++){
			System.out.println(14);
		}
		
	}
}

