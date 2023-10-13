package classMainWindows;
import java.awt.*;
import classMusic.*;
import classWindows.WindowP111;
import classWindows.WindowP111.newRndMines;

import java.applet.*;
import java.awt.*;
import java.net.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import classWindows.*;

public class startpaper extends JFrame implements ActionListener{
	
	Music player=new Music("src/myvideo/startmusic.wav");
	
	
	
	int times=0;boolean able=true;
JButton one =new JButton("����ģʽ");
JButton two =new JButton("˫��ģʽ");
JButton over =new JButton("�˳�");
JButton music=new JButton("����:��");
JButton help=new JButton("����");
JButton money=new JButton("�����");
MyMouselistener laction1=new MyMouselistener (1,this);//��������ģʽ��ť
MyMouselistener laction2=new MyMouselistener (2,this);//˫��
MyMouselistener laction3=new MyMouselistener (3,this);//�˳�
MyMouselistener laction4=new MyMouselistener (4,this);//����
MyMouselistener laction5=new MyMouselistener (5,this);//����
MyMouselistener laction6=new MyMouselistener (6,this);//����
//JFrame app1=new JFrame("����ģʽ");
WindowP111 app1 ;
//JFrame app2=new JFrame("˫��ģʽ");
WindowP2 app2 ;
//Dialog app3=new Dialog(this);
JDialog app3=new JDialog(this,"����");
JFrame app4=new JFrame("�����");
startpaper bf ;
public startpaper() {
	
	super("��ʼ����");
	player.start(true);
	Image ilImage =new ImageIcon("src/myimage/ico1.ico").getImage();
	
	try {
		Image icoImage;
		icoImage = ImageIO.read(new File("src/myimage/6.jpg"));
		this.setIconImage(icoImage);
	} catch (IOException e) {
		// TODO �Զ����ɵ� catch ��
		e.printStackTrace();
	}
	
	app4.setBounds(100, 100, 600, 900);
	app4.setLayout(new FlowLayout());
	//String s="src/myimage/1-1background.jpg";
	String s="src/myimage/t1back.jpg";
	HomePanel panel=new HomePanel(s);
	panel.setLayout(null);
	this.add(panel);
	
	panel.add(one);
	panel.add(two);
	panel.add(over);
	panel.add(help);
	panel.add(music);
	panel.add(money);
	Color c=new Color(0,0,255);
	music.setBackground(c);
	music.setOpaque(false);
	one.setIcon(new ImageIcon("src/myimage/one.png"));
	two.setIcon(new ImageIcon("src/myimage/two.png"));
	over.setIcon(new ImageIcon("src/myimage/out.png"));
	help.setIcon(new ImageIcon("src/myimage/help.png"));
	music.setIcon(new ImageIcon("src/myimage/yin.jpg"));
	money.setIcon(new ImageIcon("src/myimage/money1.png"));
	money.setLayout(new FlowLayout());
	one.setBounds(135,500,155,65);
	two.setBounds(135,600,155,65);
	over.setBounds(135,700,155,65);
	help.setBounds(135,800,155,65);
	music.setBounds(780, 10, 70,100 );
	money.setBounds(7,10,90,70);

	app3.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	 app3.setBounds(200,200,1200, 800);
	 CustomTextArea textArea = new CustomTextArea();
    JScrollPane scrollPane = new JScrollPane(textArea);
    app3.setVisible(false);
    Font ft1 = new Font("����", Font.PLAIN, 25);

    app3.add(scrollPane);
  
    textArea.setFont(ft1);
    textArea.setOpaque(false);
    textArea.setForeground(Color.yellow);
    textArea.setBackground(Color.yellow);
    textArea.setEditable(false); // �����ı�����Ϊ���ɱ༭
    textArea.setText("��ǰ��һ���������ʿ�������һֻ����С���è�䡣\r\n"+
   		 "\r\n" 
    		+ "��һ�죬��ʿ�ص��������С�䲻���ˡ���ʮ���ż����Ĵ�Ѱ��С�䡣\r\n"+
    		 "\r\n" 
    		+ "���ڣ�����ɭ����ҵ���һ�������������������ż�ֻ�ڰ�����ѻ��\r\n" + 
    		"\r\n" 
    		+"��ʿ�������ڣ�����һ��а�������ú�ħ�����������İ���Сè�䡣\r\n"
    		+ "��ʿ��æ��а����ս������а��ȴһֱ�������Լ�����ʿ���ò�������а�ױ����ֵ����⡣\r\n"
    		+ "���գ�������а�׵�ħ�������ͨ���ĸ���������ά�ֵģ�������ʿ�����ݻ����ĸ��������ӡ�\r\n"
    		+ "������һ����ս����ʿ���ڳɹ��ݻ������ĸ��������ӡ�\r\n" + 
    		"\r\n" + 
    		"���ʱ��а�׵ĺڰ�ħ��ʧЧ�ˣ�С��þ��ˡ�\r\n"
    		+ "��ʿ�����а�ף�������Լ��İ���è�䣬����С��ص����С�\r\n"
    		+ "�Դˣ���ʿ��С��֮��������������ˡ�\r\n" + 
    		"\r\n" + 
    		"�����һ������Ĺ��£�������һλ�¸ҵ���ʿ��\r\n"
    		+ "Ϊ�������İ���è�䣬��Σ���б����Լ���\r\n"
    		+ "�������а��ĺ�ħ��ʦ�����ڵ��Գɹ��ؾȻ��Լ��İ���Сè�䣬����֮�������Ҳ�õ��˸��õļ�ǿ��\r\n"
    		+ "����ģʽ���ڲ������ɵ�ը����ʰȡ����ȽϼƷ�\r\n"
    		+ "˫��ģʽ������ը����ɱ�Է�\r\n"
    		+"1p:w��a��s��d�� spaces�ͷ�ը����ը����ըʱ�������,q������  2P:С���̲���,enter���ͷ�ը����0������"
   		 );
	
	
	this.setBounds(400,100,900,955);
	this.setVisible(true);
	one.addMouseListener(laction1);
	two.addMouseListener(laction2);
	over.addMouseListener(laction3);
	money.addMouseListener(laction4);
	help.addMouseListener(laction5);
	music.addMouseListener(laction6);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
}
public static void main(String[] arguments) {
	 startpaper bf = new  startpaper();
	 
	 
}


public class MyMouselistener implements MouseListener
{int counter=0;

	startpaper s1;
	public MyMouselistener(int n,startpaper s)
	{
	counter=n;
	s1=s;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO �Զ����ɵķ������
		;
		switch (counter){
		case 1: app1 =new WindowP111();app1.setVisible(true);player.stop();s1.dispose();;break;
		case 2: app2 = new WindowP2();app2.setVisible(true);player.stop();s1.dispose(); break;
		case 3: dispose();System.exit(0);;break;
		case 4:{
			// app4.setLayout(new GridLayout(1, 5));
		//JLabel money1=	new JLabel();JLabel money2=	new JLabel();JLabel money3=	new JLabel();JLabel money4=	new JLabel();JLabel money5=	new JLabel();
		//money1.setIcon(new ImageIcon("src/myimage/money1.jpg"));
		//money2.setIcon(new ImageIcon("src/myimage/money2.jpg"));
		//money3.setIcon(new ImageIcon("src/myimage/money3.jpg"));
		//money4.setIcon(new ImageIcon("src/myimage/money4.jpg"));
		//money5.setIcon(new ImageIcon("src/myimage/money5.jpg"));
			 app4.setBounds(100, 100, 900, 850);
				app4.setSize(500, 650);
		HomePanel hmHomePanel =new HomePanel("src/myimage/mf1.jpg");
		hmHomePanel.setBounds(100, 100, 900, 850);
		hmHomePanel.setSize(900, 850);
		app4.setLayout(null);
		app4.setBounds(100, 100, 900, 850);
		app4.setSize(900, 850);
		//app4.add(money1);	app4.add(money2);app4.add(money3);	app4.add(money4);	app4.add(money5);
		app4.add(hmHomePanel);
		//app4.pack();
		app4.setVisible(true);
		
		try {
			Image icoImage;
			icoImage = ImageIO.read(new File("src/myimage/6.jpg"));
			app4.setIconImage(icoImage);
		} catch (IOException e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}
			
			break;}
		case 5:app3.setVisible(true);break;
		case 6:{if(able==true)
		{
			music.setText("����:��");
			music.setIcon(new ImageIcon("src/myimage/yin2.png"));
			repaint();
			able=false;
			
			player.stop();
			
			
		}
		else
		{
			music.setText("����:��");
			repaint();
			able=true;
			music.setIcon(new ImageIcon("src/myimage/yin.jpg"));
			repaint();
			player.continues();
		}
		}
		}
		
			
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
	}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		
	}
	
	 class HomePanel extends JPanel {
		ImageIcon icon;
		Image img;
		public HomePanel(String s) {
			//  /img/HomeImg.jpg �Ǵ���������ڱ�д����Ŀ��bin�ļ����µ�img�ļ����µ�һ��ͼƬ
			//icon=new ImageIcon("src/myimage/1back.jpg");
			icon= new ImageIcon(s);
			img=icon.getImage();
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			//����������Ϊ�˱���ͼƬ���Ը��洰�����е�����С�������Լ����óɹ̶���С
			g.drawImage(img, 0, 0,this.getWidth(), this.getHeight(), this);
		}
	 
	}



	 public class CustomTextArea extends JTextArea {
		    private BufferedImage backgroundImage;

		    public CustomTextArea() {
		        try {
		            backgroundImage = ImageIO.read(new File("src/myimage/catback1.jpg")); // �滻Ϊ��ı���ͼ���ļ�·��
		         
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }

		    @Override
		    protected void paintComponent(Graphics g) {
		        if (backgroundImage != null) {
		            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(),null);
		        }
		        super.paintComponent(g);
		    }
		}





}
