package classWindows;

import classMainWindows.*;


import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;





public class WindowP111 extends JFrame{

	int n=13;
	int m=21;
	Timer tm1,tm2,tm3;
	JLabel mymapLabel[][]=new JLabel[13][24];
	JLabel myBarrLabel[][]=new JLabel[13][24];
	JLabel myProLabel[][]=new JLabel[13][24];
	JLabel myTexiao[][]  = new JLabel[13][24];
	int mymap[][]=new int[13][24];
	int myBarrier[][]=new int [13][24];
	int myProp[][]=new int[13][24];
	
	class RndPoint extends JLabel
	{
		public int x,y,i,j;
		Random rp1=new Random(new Date().getTime()*5+1);
		public RndPoint()
		{
			 i=rp1.nextInt(13);
			 j=rp1.nextInt(23);
			while(mymap[i][j]==1)
			{
				 i=rp1.nextInt(13);
				 j=rp1.nextInt(23);
			}
			x=i;
			y=j;
			if(mymap[x][y]==1)
			{
				x=11;
				y=10;
			}
			setIcon(new ImageIcon("src/myimage/grade.png"));
			//setIcon(new ImageIcon(this.getClass().getResource("grade.png")));
		
		}
	}
	RndPoint myPoint = new RndPoint();
	
	//����
	public class Rankings  {
		CustomTextArea rankarea;
		JLabel jl;
		public String  score;
		public int flag;//�����ж��Ƿ�Ϊ���ļ�����Ϊ���ļ�����0������1���ڵ�һ��д��ʱ�޸�
		String txt="src/Rank/rankings.txt";
		File file =new File("src/Rank/rankings.txt");
		public Rankings (CustomTextArea ra,JLabel l) {
		
					jl=l;
					rankarea=ra;
			try {

				BufferedReader reader =null;
				InputStreamReader isrInputStreamReader=null;
				FileInputStream inputStream =new FileInputStream(file);
				isrInputStreamReader=new InputStreamReader(inputStream);
				reader=new BufferedReader(isrInputStreamReader);
			
		
			}catch(IOException e) {
				System .out.println(e);
			}
		
			

			
		}
		
		public  void txtwrite(String score) {
			File file =new File(txt);
			try {
				BufferedWriter bfWriter  =null;
				FileWriter fWriter =new FileWriter(file,true);
				bfWriter =new BufferedWriter(fWriter);
				
				BufferedReader reader =null;
				InputStreamReader isrInputStreamReader=null;
				FileInputStream inputStream =new FileInputStream(file);
				isrInputStreamReader=new InputStreamReader(inputStream);
				reader=new BufferedReader(isrInputStreamReader);
				String string;
				string=reader .readLine();
					fWriter.write(score+"\n");
					fWriter.flush();
					fWriter .close();
			}catch(Exception e) {
				System .out.println("txtsort�����⣡");
			}
		}
		
		public  void txtsort(String score) {
			try {
				File file =new File(txt);
				FileInputStream fis=new FileInputStream(file);
				BufferedReader in=new BufferedReader(new InputStreamReader (fis));
				
				String aLineString;
				ArrayList <Integer >al=new ArrayList<Integer>();
				int flag=100000;
				int i=0;
				while((aLineString =in.readLine())!=null) {
					al.add(Integer.parseInt(aLineString));
				}
				Collections .sort(al);
				Collections.reverse(al);
				file.delete();
				File file2=new File("src/Rank/rankings.txt");
				file.createNewFile();
				FileOutputStream fos=new FileOutputStream(file2);
				BufferedWriter out=new BufferedWriter(new OutputStreamWriter (fos));
				String r="";
				for(Integer s:al) {
					//System.out.println(s);
					r+="NO"+(i+1)+": "+s.toString()+"\n";
					txtwrite( s.toString());
					i++;
					if(s.toString().equals(score)&&flag>i) {
						flag=i;
						//jl.setBackground(Color.red);
						//jl.setOpaque(true);
						
						jl.setText("��"+flag+"��!");
					}
				}
				rankarea.setText(r);
			}catch(Exception e) {
				System.out.println("txtsort�����⣡");
			}
		}
		
		
		//public static void main(String args[]) {
		//	Rankings rankings =new Rankings("9754");
			
		//}
	}
	
	
	
	//���������
	
	class RndMines extends JLabel
	{
		
		public int x,y,timeDis;
		public String path="src/myimage/mybomb.png";
		
		public RndMines () {
			jP1.requestFocus();
			setIcon(new ImageIcon(path));
			setVisible(true);
			Timer t1=new Timer();
			timeDis=r1.nextInt(6000)+100;
			//t1.schedule(new Explosion(x,y), 3000);
		
			if(stopContinue.getLabel()=="��ͣ")
			{
			t1.schedule(new dis(),timeDis+1000);
			t1.schedule(new cov(),timeDis+ 1250);
			}
		}
		
		public class dis extends TimerTask {

			public dis()
			{
				run();
			
			}
			public void run() {
			bombMusic.start(false);
				// TODO �Զ����ɵķ������
				setVisible(false);
				for(int i=x-1;i<=x+1;i++)
				{
					if(i<3&&y<3)
						continue;
					if(i>=0&&i<13&&mymap[i][y]!=1)
					{
						if(i==per1.wNum&&y==per1.hNum)
							per1.life=per1.life-1;
						/*
						if(per1.life<=0)
							{int re= JOptionPane.showConfirmDialog(jP1, "��ĵ÷���: "+per1.pointer+" !");
							dieMusic.start(false);
							if(re==JOptionPane.OK_OPTION)
								System.exit(0);}
						*/
						myBarrier[i][y]=-1;
						if(myBarrLabel[i][y]!=null)
						jP1.remove(myBarrLabel[i][y]);
				
						myTexiao[i][y].setIcon(new ImageIcon("src/myimage/shuguang.png"));
					//myTexiao[i][y].setIcon(new ImageIcon(this.getClass().getResource("shuguang.png")));
						
						myTexiao[i][y].setVisible(true);
						//t1.schedule(new cov(i, y),timeDis+ 3000);
						//dtm.schedule(new changeColor(i, y),118300);
					}
				}
				
				for(int j=y-1;j<=y+1;j++)
				{
					if(x<3&&j<3)
						continue;
					if(j>=0&&j<23&&mymap[x][j]!=1)
					{
						if(x==per1.wNum&&j==per1.hNum&&per1.life>0)
							per1.life=per1.life-1;
						/*
						if(per1.life<=0)
							{int re= JOptionPane.showConfirmDialog(jP1, "��ĵ÷���: "+per1.pointer+" !");
							dieMusic.start(false);
								if(re==JOptionPane.OK_OPTION)
									System.exit(0);
							}
						*/
						
						myBarrier[x][j]=-1;
						if(myBarrLabel[x][j]!=null)
						jP1.remove(myBarrLabel[x][j]);
						myTexiao[x][j].setIcon(new ImageIcon("src/myimage/hengguang.png"));
						//myTexiao[x][j].setIcon(new ImageIcon(this.getClass().getResource("hengguang.png")));
						myTexiao[x][j].setVisible(true);
						//dtm.schedule(new changeColor(x, j),118300);
						//t1.schedule(new cov(x, j),timeDis+ 2000);
					}
				}
				p2_j2.setText("����ֵ: "+String.valueOf(per1.life));
				
				if(per1.life<=0&&per1.flag==0)
					
				{
					per1.flag=1;
				//p2_j2.setText("����");
					stopContinue.setLabel("����");
					//Rankings rk =new Rankings(String.valueOf( per1.pointer));
					//rk.setVisible(true);
					//jP1.add(rk);
					//int re= JOptionPane.showConfirmDialog(jP1, "��ĵ÷���: "+per1.pointer+" !");
				dieMusic.start(false);
				//bMusic.stop();
				endHomePanel.setVisible(true);
				myRankings.txtwrite(String.valueOf(per1.pointer));
				myRankings.txtsort(String.valueOf(per1.pointer));
				endHomePanel.setVisible(true);
					//if(re==JOptionPane.OK_OPTION)
						//System.exit(0);
				}
				
			}
			
		}
		
		public class cov extends TimerTask{

			
			public cov()
			{
		
				run();
			}
			public void run() {
				//myTexiao[i][j].setVisible(false);
				
			
			
			for(int i=x-1;i<=x+1;i++)
			{
				if(i>=0&&i<13&&mymap[i][y]!=1)
				{
				
					myTexiao[i][y].setVisible(false);
					//t1.schedule(new cov(i, y),timeDis+ 3000);
					//dtm.schedule(new changeColor(i, y),118300);
				}
			}
			
			for(int j=y-1;j<=y+1;j++)
			{
				if(j>=0&&j<23&&mymap[x][j]!=1)
				{
					
					myTexiao[x][j].setVisible(false);
					//dtm.schedule(new changeColor(x, j),118300);
					//t1.schedule(new cov(x, j),timeDis+ 2000);
				}
			}
			}
			
		}
		public void getxy(int i,int j)
		{
			x=i;
			y=j;
		}
		
	}
	//������׶�ʱ��ը��
	/* class Explosion extends TimerTask
	{
		int x,y;
		public  Explosion(int xh,int yh) {

			x=xh;
			y=yh;
			myRndMines[x][y].fasle=1;
			myRndMines[x][y].setVisible(true);
			//run();
		Timer tm1=new Timer();
		tm1.schedule(new dispearMines(x, y), myRndMines[x][y].timeDis);
			run();
			//myRndMines[x][y].setVisible(false);
		}

	
		public void run() {

		}
		
	}
	 
	 //��ը����ʱ��ɫ
	 class changeColor extends TimerTask
	 {

		 int x,y;
		 public changeColor (int i,int j) {
			 x=i;
			 y=j;
			 run();
			
		}
		
		public void run() {
			//myBarrLabel[x][y].setIcon(new ImageIcon());
			//myBarrLabel[x][y].setVisible(false);
			//myTexiao[x][y].setVisible(false);
		}
		 
	 }
	 
	 //�׶�ʱ��ʧ
	 class dispearMines extends TimerTask
	 {

		 int x,y;
		 public dispearMines(int i,int j)
		 {
			 x=i;
			 y=j;
		 }
		
		public void run() {
			myRndMines[x][y].setVisible(false);
			
		Timer dtm=new Timer();
			for(int i=x-3;i<=x+3;i++)
			{
				if(i>=0&&i<13&&mymap[i][y]!=1)
				{
					myBarrier[i][y]=-1;
					jP1.remove(myBarrLabel[i][y]);
			
					myTexiao[i][y].setVisible(true);
					
					//dtm.schedule(new changeColor(i, y),118300);
				}
			}
			
			for(int j=y-3;j<=y+3;j++)
			{
				if(j>=0&&j<23&&mymap[x][j]!=1)
				{
					myBarrier[x][j]=-1;
					jP1.remove(myBarrLabel[x][j]);
					myTexiao[x][j].setVisible(true);
					//dtm.schedule(new changeColor(x, j),118300);
			
				}
			}
		}
		 
	 }*/
	//�������ͼ
	//int myMines[][]=new int[13][24];
	RndMines myRndMines[][]=new RndMines[13][24];
	//����ģʽ��ͼ
	public void initmap() {
		mymap[8][0]=1;
		mymap[1][1]=1;
		mymap[3][1]=1;
		mymap[5][1]=1;
		mymap[10][1]=1;
		mymap[12][1]=1;
		mymap[7][2]=1;
		mymap[2][3]=1;
		mymap[4][3]=1;
		mymap[8][3]=1;
		mymap[9][3]=1;
		mymap[11][3]=1;
		mymap[1][4]=1;
		mymap[2][4]=1;
		mymap[3][4]=1;
		mymap[6][4]=1;
		mymap[2][5]=1;
		mymap[8][5]=1;
		mymap[10][5]=1;
		mymap[5][6]=1;
		mymap[12][6]=1;
		mymap[0][7]=1;
		mymap[2][7]=1;
		mymap[8][7]=1;
		mymap[10][7]=1;
		mymap[4][8]=1;
		mymap[7][8]=1;
		mymap[1][9]=1;
		mymap[3][9]=1;
		mymap[4][9]=1;
		mymap[5][9]=1;
		mymap[9][9]=1;
		mymap[12][9]=1;
		mymap[4][10]=1;
		mymap[7][10]=1;
		mymap[9][10]=1;
		mymap[2][11]=1;
		mymap[11][11]=1;
		mymap[0][12]=1;
		mymap[6][12]=1;
		mymap[8][12]=1;
		mymap[0][13]=1;
		mymap[3][13]=1;
		mymap[4][13]=1;
		mymap[11][13]=1;
		mymap[12][13]=1;
		mymap[0][14]=1;
		mymap[5][14]=1;
		mymap[7][14]=1;
		mymap[9][14]=1;
		mymap[3][15]=1;
		mymap[7][15]=1;
		mymap[2][16]=1;
		mymap[4][16]=1;
		mymap[6][16]=1;
		mymap[7][16]=1;
		mymap[8][16]=1;
		mymap[11][16]=1;
		mymap[0][17]=1;
		mymap[4][17]=1;
		mymap[10][17]=1;
		mymap[8][18]=1;
		mymap[1][19]=1;
		mymap[3][19]=1;
		mymap[5][19]=1;
		mymap[6][19]=1;
		mymap[9][19]=1;
		mymap[12][19]=1;
		mymap[1][21]=1;
		mymap[4][21]=1;
		mymap[6][21]=1;
		mymap[9][21]=1;
		mymap[11][21]=1;
		mymap[7][22]=1;
					
	}
	


	Music bMusic=new Music("src/myvideo/background.wav");
	//this.getClass().getResource("house.png")
//String	pathpp=this.getClass().getResource("background.wav");
//	Music bMusic=new Music("background.wav");
	Music bombMusic =  new Music("src/myvideo/explode.wav");
	Music dieMusic =  new Music("src/myvideo/die.wav");
	Music winbMusic =  new Music("src/myvideo/win.wav");
	int blockWidth=60;
	int blockHight=70;
	//JPanel jP1=new JPanel();
	HomePanel jP1=new HomePanel("src/myimage/1back.jpg");
	//JPanel j2P1=new JPanel();

	HomePanel j2P1 = new HomePanel("src/myimage/2back.jpg");
	//�����
	Random rnd1,rnd2,rnd3,rnd4;
	//�ϰ����ַ
	String pathBarrier[]=new String[3];
	String pathProp[]=new String[4];
	PersonOneP1 per1=new PersonOneP1();
	Timer t1 = new  Timer();
	Random r1=new Random(new Date().getTime());
	JLabel p2_j1= new JLabel();
	JLabel p2_j2 =new JLabel();
	Button stopContinue = new Button();
	//���㻭��
	HomePanel endHomePanel= new HomePanel("src/myimage/mybomb.png");
public	Button endButton = new Button();
	//TextArea rankArea = new TextArea();
CustomTextArea rankArea = new CustomTextArea();
	JLabel pointJLabel =new JLabel();
	Rankings myRankings=new Rankings(rankArea,pointJLabel);
	

	
	public WindowP111 () {
		super("���˵�ͼ");
		setLayout(new BorderLayout());
		setSize(1700, 958);
		setBackground(Color.black);
		
		try {
			Image icoImage;
			icoImage = ImageIO.read(new File("src/myimage/6.jpg"));
			this.setIconImage(icoImage);
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		//System.out.println("p11");
		tm3=new Timer();
		//��ͼ����
		//JPanel jP1=new JPanel();
		jP1.setSize(500,300);
		//jP1.setflowLayout(new FlowLayout().LEFT);
		//jP1.setLayout(new BorderLayout());
		//ȥ����׼����
		jP1.setLayout(null);
		j2P1.setLayout(null);
		jP1.setPreferredSize(new Dimension(1380,810));
		jP1.setBackground(Color.green);
		//������屳��ͼ
		jP1.setOpaque(false);
		//jP1.setLayout(new BorderLayout()); JLabel background = new JLabel(new ImageIcon("src/1back.jpg")); jP1.add(background); background.setLayout(new FlowLayout());
	
		//
		//Music bMusic=new Music(this.getClass().getResource("background.wav"));
		
		
		add(jP1,BorderLayout.WEST);
		//��ȡ����
		jP1.requestFocus();
		//�����
		jP1.add(myPoint);
		myPoint.setBounds(myPoint.y*60, myPoint.x*70, 60, 70);
	
		//��������
		bMusic.start(true);
		//Ĭ�Ϲر�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		//���ֻ���
		//JPanel j2P1=new JPanel();
		j2P1.setPreferredSize(new Dimension(302,1024));
		j2P1.setBackground(Color.red);
		add(j2P1,BorderLayout.EAST);
		//�������
		jP1.add(per1);
		per1.setBounds(0, 0, 60, 70);
		
		MyKeylistener laction=new MyKeylistener();
		addKeyListener(laction);
		jP1.addKeyListener(laction);
		
		//Ϊ��������ӻ��ֱ�ǩ
		j2P1.add(p2_j1);
		p2_j1.setBounds(j2P1.getWidth()/2+50, j2P1.getHeight()/2+400, 180, 180);
		p2_j1.setVisible(true);
		p2_j1.setSize(180,180);
		p2_j1.setText("�÷�: 0");
		Font ft1 = new Font("����", Font.PLAIN, 25);
		p2_j1.setForeground(Color.red);
		p2_j1.setFont(ft1);
		p2_j1.setBackground(Color.BLUE);
		//��ʼ���ϰ�������
		//getPathBarrier();
		//getPathProp();
		//��ͣ/������ť
		stopContinue.setBackground(Color.black);
		j2P1.add(stopContinue);
		stopContinue.setVisible(true);
		stopContinue.setLabel("��ͣ");
		//stopContinue.setSize(100, 100);
		stopContinue.setBounds(j2P1.getWidth()+50,j2P1.getHeight()+ 800, 200, 110);
		stopOrcontinue sc = new stopOrcontinue();
		stopContinue.addActionListener(sc);
		
		//t1.schedule(new stopPower(), new Date(), 2000);
		//����
		Font myFont=new Font("�����п�", Font.BOLD, 17);
		Font myFont2=new Font("�����п�", Font.BOLD, 25);
		//���㻭��
		jP1.add(endHomePanel);
	
		endHomePanel.setBackground(Color.blue);
		endHomePanel.setLayout(null);
		endHomePanel.setBounds(jP1.getWidth()/2+200, jP1.getHeight()/2, 560, 560);
		endHomePanel.setOpaque(false);
		endHomePanel.setVisible(false);
		
		endHomePanel.add(pointJLabel);
		pointJLabel.setBounds(endHomePanel.getWidth()/2-150, endHomePanel.getHeight()/2-170,200, 120);
		pointJLabel.setFont(myFont2);
		pointJLabel.setText("������");
		//pointJLabel.setHorizontalTextPosition(pointJLabel.CENTER);
		//pointJLabel.setVerticalTextPosition(pointJLabel.CENTER);
		pointJLabel.setVerticalAlignment(JLabel.CENTER);
		pointJLabel.setHorizontalAlignment(JLabel.CENTER);
		pointJLabel.setForeground(Color.yellow);
		//pointJLabel.setBackground(Color.green);
		
		
		endHomePanel.add(endButton);
		endButton.setLabel("����");
		endButton.setBounds(endHomePanel.getWidth()/2-130, endHomePanel.getHeight()/2,150, 100);
		endButton.setBackground(Color.black);
		endButton.setFont(myFont);
		backforEnd bd = new backforEnd(this);
		endButton.addActionListener(bd);
	
		
		endHomePanel.add(rankArea);
		rankArea.setBounds(endHomePanel.getWidth()/2+120, 0,160, 560);
		rankArea.setFont(myFont);
		rankArea.setForeground(Color.orange);
		rankArea.setOpaque(false);
		
		//����
		p2_j2.setText("����ֵ: "+String.valueOf(per1.life));
		p2_j2.setForeground(Color.red);
		p2_j2.setFont(ft1);
		p2_j2.setBackground(Color.BLUE);
		p2_j2.setBounds(j2P1.getWidth()+50, j2P1.getHeight()+200, 180, 180);
		j2P1.add(p2_j2);
		//��ʼ����ͼ
		initmap();
		paintMap();
		t1=new Timer();
		t1.schedule(new newRndMines (), new Date(),2000);
		//testmap();
	
		
		/*RndMines r1=new RndMines ();
		r1.setIcon(new ImageIcon("src/myimage/mybomb.png"));
		jP1.add(r1);
		r1.setBounds(0, 0, 60, 70);
		
		*/
		jP1.requestFocus();
	}
	//�ƶ��¼�
	
	public class MyKeylistener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO �Զ����ɵķ������
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO �Զ����ɵķ������
			if(stopContinue.getLabel()=="��ͣ")
			{	
			
			int locationx=per1.getW();//�õ�������
			int locationy=per1.getH();//�õ�������
			
			if(e.getKeyChar()=='w')
			{//a=iftouch()&&ifout()//&&iftouch()&&ifout()�����ж��Ƿ������������߳�������
				
				if(locationx-1<0||mymap[locationx-1][locationy]==1)
					per1.changeW(0);
				else 
					{
				
				locationx=locationx-1;
				per1.changeW(-1);
				per1.setLocation(locationy*60,locationx*70 );
				per1.faceTo(3);
					}
			}
			if(e.getKeyChar()=='s')
			{
			if(locationx+1>13||mymap[locationx+1][locationy]==1)
				per1.changeW(0);
			else 
				{
			
			locationx=locationx+1;
			per1.changeW(1);
			per1.setLocation(locationy*60,locationx*70 );
			
			per1.faceTo(0);
				}
			}
			if(e.getKeyChar()=='a')
			{
				if(locationy-1<0||mymap[locationx][locationy-1]==1)
					per1.changeH(0);
				else 
					{
				
				locationy=locationy-1;
				per1.changeH(-1);
				per1.setLocation(locationy*60,locationx*70 );
				
				per1.faceTo(1);
					}
			}
			if(e.getKeyChar()=='d')
			{
				if(locationy+1>=23||mymap[locationx][locationy+1]==1)
					per1.changeH(0);
				else {
					
				
				locationy=locationy+1;
				per1.changeH(1);
				per1.setLocation(locationy*60,locationx*70 );
			per1.faceTo(2);
				}
			}
			
			if(myPoint.x==locationx&&myPoint.y==locationy)
			{
				jP1.remove(myPoint);
				myPoint=new RndPoint();
				jP1.add(myPoint);
				myPoint.setBounds(myPoint.y*60, myPoint.x*70, 60, 70);
				per1.pointer=per1.pointer+1;
				p2_j1.setText("�÷�: "+ String.valueOf( per1.pointer));
				/*if(per1.pointer==5)
				{
					int re= JOptionPane.showConfirmDialog(jP1, "��Ӯ��");
					winbMusic.start(false);
					if(re==JOptionPane.OK_OPTION)
						System.exit(0);
				
				}*/
			}
			}
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO �Զ����ɵķ������
			
		}
		
	}
	
	
	//stop/continue
	public class stopOrcontinue implements ActionListener{

		
		public void actionPerformed(ActionEvent e) {
			/*if(flag)
			{
				stopContinue.setLabel("����");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
			}
			else {
				stopContinue.setLabel("��ͣ");
			}*/
			jP1.requestFocus();
			if(stopContinue.getLabel()=="����")
			{
				stopContinue.setLabel("��ͣ");
			}
			else {
				stopContinue.setLabel("����");
				
			}
		}
		
	}
	
	
	//�˳�
	public class backforEnd implements ActionListener
	{
		WindowP111 w1;
		
		public  backforEnd(WindowP111 w) {
			w1=w;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
		
			startpaper startpaper=new startpaper();
			bMusic.stop();
			t1.cancel();
			
			w1.dispose();
			
			
		}
		
		
	}
	
	class stopPower extends TimerTask
	{

	
	public stopPower()
	{
		run();
	}
		public void run() {
			if(stopContinue.getLabel()=="��ͣ")
			{
				//stopContinue.setLabel("����");
				try {
					Thread.sleep(2000);
				
					//System.out.print(stopContinue.getLabel());
				} catch (InterruptedException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
			}
			else {
				//stopContinue.setLabel("��ͣ");
			}
			
		}
		
	}
	
	//���Ƶ�ͼ
	public void paintMap () {
		//mymap[12][22]=0;
		rnd1=new Random(new Date().getTime());
		rnd2=new Random(new Date().getTime()*5+22);

		int num;
		int item1;
		
		for (int i=0;i<23;i++)
		{
			for(int j=0;j<13;j++)
			{
				myTexiao[j][i] = new JLabel();
				//myTexiao[j][i].setIcon(new ImageIcon("src/myimage/guang.png"));
				//myTexiao[j][i].setIcon(new ImageIcon(this.getClass().getResource("hengguang.png")));
				jP1.add(myTexiao[j][i]);
				myTexiao[j][i].setBounds(i*60, j*70, 60, 70);
				myTexiao[j][i].setVisible(false);
				if(i==0&&j==0)
					continue;
				JLabel jl=new JLabel();
				//jl.setLocation(i*60, j*70);
				//jl.setBounds(i*60, j*70, 60, 70);
				if(mymap[j][i]==1)
				{
					//myBarrier[j][i]=500;
				jl.setIcon(new ImageIcon("src/myimage/house.png"));
				//jl.setIcon(new ImageIcon(this.getClass().getResource("house.png")));
				jl.setBounds(i*60, j*70, 60, 70);
				//jP1.add(jl);
				}
				else {
					/*
					//rnd1=new Random(new Date().getTime());
					
					//mymap[j][i]=0;
					
					num=rnd2.nextInt(70);
					item1=(rnd1.nextInt(4))+20;
					if(num<35)
					{
					
						myProLabel[j][i]=new JLabel();
						
						myProLabel[j][i].setIcon(new ImageIcon(pathProp[item1-20]));
						myProp[j][i]=item1;//�ϰ�����Ϊ20-24
						jP1.add(myProLabel[j][i]);
						myProLabel[j][i].setBounds(i*60, j*70, 60, 70);
					}
					
					 num=rnd1.nextInt(900);
					//int randomNum = ThreadLocalRandom.current().nextInt(0, 500 + 1);
					 item1=(rnd1.nextInt(2+1))+100;
					
					if(num<685)
					{
						//System.out.println(num);
						myBarrLabel[j][i]=new JLabel();
						myBarrLabel[j][i].setIcon(new ImageIcon(pathBarrier[item1-100]));
						myBarrier[j][i]=item1;//�ϰ�����Ϊ100-102
						jP1.add(myBarrLabel[j][i]);
						myBarrLabel[j][i].setBounds(i*60, j*70, 60, 70);
					}
					*/
					//continue;
					//jl.setIcon(new ImageIcon("src/apple .png"));
					
					/*
					num=rnd2.nextInt(70);
					item1=(rnd1.nextInt(4))+5;
					if(num<35)
					{
						jl.setIcon(new ImageIcon(pathProp[item1-5]));
						myProp[j][i]=item1;//�ϰ�����Ϊ5-9
						jl.setBounds(i*60, j*70, 60, 70);
					}
					
					*/
					
				}
				jP1.add(jl);
			
			}
		}
		
	}
	
	
	public void testmap() {
		for(int i=0;i<13;i++)
			
		{
			for(int j=0;j<23;j++)
			{
				
				
				System.out.println(i+" "+j+" "+mymap[i][j]);
			}
		}
	}
	
	
	//��ʱ�������������
	
	public class newRndMines extends TimerTask
	{
		
		public newRndMines()
		{
	
			if(stopContinue.getLabel()=="��ͣ")
			run();
			jP1.requestFocus();
		}
		//Random r1=new Random(new Date().getTime());
		
		public void run() {
			//Timer t1=new Timer();
			
			for(int i=0;i<13;i++)
			{
				for(int j=0;j<23;j++)
				{
					if(stopContinue.getLabel()=="����")
						continue;
					
					int num=r1.nextInt(70);
					if (per1.pointer>15) {
						num=num-3;
					}
					if(num>3)
						continue;
					//if(i==0&&j==0||i==1&&j==0||i==0&&j==1||i==1&&j==1||)
					//	continue;
					if(i<=3&&j<=3)
						continue;
					if(mymap[i][j]!=1)
					{
						if(myRndMines[i][j]!=null)
						jP1.remove(myRndMines[i][j]);
						myRndMines[i][j]=new RndMines();
						jP1.add(myRndMines[i][j]);
						//int dis=r1.nextInt(15000)+100 ;
						//myRndMines[i][j].timeDis=dis;
						myRndMines[i][j].setIcon(new ImageIcon("src/myimage/mybomb.png"));
						//myRndMines[i][j].setIcon(new ImageIcon(this.getClass().getResource("mybomb.png")));
						myRndMines[i][j].setVisible(true);
						//t1.schedule(new Explosion(i,j), dis );
						//myRndMines[i][j].timeDis=dis;
						myRndMines[i][j].setBounds(j*60, i*70, 60, 70);
						myRndMines[i][j].getxy(i, j);
					}
				}
			}
		}
		
		
	}
	
	 public class CustomTextArea extends JTextArea {
		    private BufferedImage backgroundImage;

		    public CustomTextArea() {
		        try {
		            backgroundImage = ImageIO.read(new File("src/myimage/2back.jpg")); // �滻Ϊ��ı���ͼ���ļ�·��
		         
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
	
	public void getPathBarrier() {
		pathBarrier[0]="src/myimage/apple .png";
		pathBarrier[1]="src/myimage/pear.png";
		pathBarrier[2]="src/myimage/watermelon.png";
	}
	
	
	public void getPathProp() {
		pathProp[0]="src/myimage/bomb.png";
		pathProp[1]="src/myimage/hammber.png";
		pathProp[2]="src/myimage/missile.png";
		pathProp[3]="src/myimage/potion.png";
				
		
	}
	
	

	
	
	//public static void main(String agr[]) {
		//WindowP111 wP1=new WindowP111();
		//wP1.setVisible(true);
		//System.out.println("jdksa");
	//}
	
	//������ҳ����ͼƬ��JPnel��
	public class HomePanel extends JPanel {
		ImageIcon icon;
		Image img;
		public HomePanel(String s) {
			//  /img/HomeImg.jpg �Ǵ���������ڱ�д����Ŀ��bin�ļ����µ�img�ļ����µ�һ��ͼƬ
			icon=new ImageIcon("src/myimage/t1back.jpg");
			//icon= new ImageIcon(this.getClass().getResource(s));
			img=icon.getImage();
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			//����������Ϊ�˱���ͼƬ���Ը��洰�����е�����С�������Լ����óɹ̶���С
			g.drawImage(img, 0, 0,this.getWidth(), this.getHeight(), this);
		}
	 
	}

	
	
	
	
	
	
	
	
	
	
}

//���˵��ױ�ը����¼�




 
  class Music {
    private String musicPath;//��Ƶ�ļ�

	private volatile boolean run = true;  //��¼��Ƶ�Ƿ񲥷�
	private Thread mainThread;   //������Ƶ�������߳�
	
	private AudioInputStream audioStream;
	private AudioFormat audioFormat;
	private SourceDataLine sourceDataLine;
	
	public Music(String musicPath) {
		this.musicPath = musicPath;
		prefetch();
	}
	
	
	
	//����׼��
	private void prefetch(){
		try{
		//��ȡ��Ƶ������
	    audioStream = AudioSystem.getAudioInputStream(new File(musicPath));
		//��ȡ��Ƶ�ı������
		audioFormat = audioStream.getFormat();
		//��װ��Ƶ��Ϣ
		DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class,
				audioFormat,AudioSystem.NOT_SPECIFIED);
		//ʹ�ð�װ��Ƶ��Ϣ���Info�ഴ��Դ�����У��䵱��Ƶ����Դ
		sourceDataLine = (SourceDataLine)AudioSystem.getLine(dataLineInfo);
		
		sourceDataLine.open(audioFormat);
		sourceDataLine.start();
		
		}catch(UnsupportedAudioFileException ex){
			ex.printStackTrace();
		}catch(LineUnavailableException ex){
			ex.printStackTrace();
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
	}
	//��������:�ر���Ƶ��ȡ����������
	protected void finalize() throws Throwable{
		super.finalize();
		sourceDataLine.drain();
		sourceDataLine.close();
		audioStream.close();
	}
	
	//������Ƶ:ͨ��loop���������Ƿ�ѭ������
	private void playMusic(boolean loop)throws InterruptedException {
		try{
				if(loop){
					while(true){
						playMusic();
					}
				}else{
					playMusic();
					//��������в��ر�
					sourceDataLine.drain();
					sourceDataLine.close();
					audioStream.close();
				}
			
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
		
	}
	private void playMusic(){
		try{
			synchronized(this){
				run = true;
			}
			//ͨ�������ж�ȡ��Ƶ�����������͵�������;
			//������������̣�AudioInputStream -> SourceDataLine;
			audioStream = AudioSystem.getAudioInputStream(new File(musicPath));
			int count;
			byte tempBuff[] = new byte[1024];
			
				while((count = audioStream.read(tempBuff,0,tempBuff.length)) != -1){
					synchronized(this){
					while(!run)
						wait();
					}
					sourceDataLine.write(tempBuff,0,count);
							
			}
 
		}catch(UnsupportedAudioFileException ex){
			ex.printStackTrace();
		}catch(IOException ex){
			ex.printStackTrace();
		}catch(InterruptedException ex){
			ex.printStackTrace();
		}
		
	}
	
	
	//��ͣ������Ƶ
	private void stopMusic(){
		synchronized(this){
			run = false;
			notifyAll();
		}
	}
	//������������
	private void continueMusic(){
		synchronized(this){
			 run = true;
			 notifyAll();
		}
	}
	
	
	//�ⲿ���ÿ��Ʒ���:������Ƶ���̣߳�
	public void start(boolean loop){
		mainThread = new Thread(new Runnable(){
			public void run(){
				try {
					playMusic(loop);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		mainThread.start();
	}
	
	//�ⲿ���ÿ��Ʒ�������ͣ��Ƶ�߳�
	public void stop(){
		new Thread(new Runnable(){
			public void run(){
				stopMusic();
				
			}
		}).start();
	}
	//�ⲿ���ÿ��Ʒ�����������Ƶ�߳�
	public void continues(){
		new Thread(new Runnable(){
			public void run(){
				continueMusic();
			}
		}).start();
	}  
 }

  
  class PersonOneP1 extends JLabel  {
		public int wNum;
		public int hNum;
		public int life;
		public int pointer;
		public int flag=0;
		String pathPersonOne[]= {"src/myimage/p2ǰ.png","src/myimage/p2��.png","src/myimage/p2��.png","src/myimage/p2��.png"};
		//String pathPersonOne[]= {"p2ǰ.png","p2��.png","p2��.png","p2��.png"};
		public PersonOneP1()
		
		{
			super("fskjj");
			wNum=0;
			hNum=0;
			life=3;
			faceTo(0);
			//setIcon(new ImageIcon("src/myimage/test1.png"));
		
		}
		

		//���ºͻ�ȡ����λ����Ϣ
		public int  getW() {
			return wNum;
			
		}
		public void changeW(int t) {
			if(wNum+t<13 && wNum+t>=0)
			{
				wNum=wNum+t;
			}
			
		}
		
		public int getH() {
			return hNum;
			
		}
		
		public void changeH(int t) {
			if(hNum+t<23&&hNum>=0)
			{
				hNum=hNum+t;
			}
		}
		
		
		//�ı����ﳯ��
		public void faceTo (int t) {
			setIcon(new ImageIcon(pathPersonOne[t]));
			//setIcon(new ImageIcon(this.getClass().getResource(pathPersonOne[t])));
			
		}
		


	}






