package classWindows;
import classPerson.*;
import classMusic.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.omg.CORBA.ARG_IN;

import classMainWindows.startpaper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
public class WindowP2 extends JFrame {
	//地图长宽
	int wNum=18;
	int hNum=13;
	
	//两个角色
	//PersonOne p1=new PersonOne(1);
	PersonOne p1=new PersonOne(1);
	PersonOne p2=new PersonOne(2);
	//两个画布 显示道具
	String back1 = "src/myimage/t1back.jpg";
	HomePanel hp1 = new HomePanel(back1);
	String back2 = "src/myimage/t2back.jpg";
	HomePanel hp2 =new HomePanel(back2);
	HomePanel hp3=new HomePanel(back2);
	//地图类
	//myJLabel mymapLabel[][]=new myJLabel[hNum][wNum];
	myJLabel myBarrLabel[][]=new myJLabel[hNum+5][wNum+5];
	myJLabel myProLabel[][]=new myJLabel[hNum+5][wNum+5];
	myJLabel myTexiao[][]  = new myJLabel[hNum+5][wNum+5];
	myJLabel mymap[][]=new myJLabel[hNum+5][wNum+5];
	//两个随机数
	Random rnd1,rnd2;
	//游戏结束tag
	int tag=0;
	
	//各种图
	String mapPath="src/myimage/house.png";
	String pathBarrier[]=new String[4];
	String pathProp[]=new String[4];
	//开始暂停按钮
	Button stopContinue = new Button();
	//音乐类
public	Music bMusic=new Music("src/myvideo/background.wav");

public	Music bombMusic =  new Music("src/myvideo/explode.wav");
public	Music dieMusic =  new Music("src/myvideo/die.wav");
public	Music winbMusic =  new Music("src/myvideo/win.wav");
public  Music jiandaojuMusic=new Music("src/myvideo/jiandaoju.wav");
	//Timer类
	Timer t1=new Timer();
	Timer t2=new Timer();
public	Timer getDifferTimer = new Timer();
	//得分标签
	JLabel p1PointJLabel =new JLabel();
	JLabel p2PointJLabel = new JLabel();
	//角色名称标签
	JLabel p1NameJLabel=new JLabel();
	JLabel p2NameJLabel=new JLabel();
	//雷数标签
	JLabel p1MineJLabel=new JLabel();
	JLabel p2MineJLabel = new JLabel();
	//炸区标签
	JLabel p1MineAreaJLabel = new JLabel();
	JLabel p2MineAreaJLabel = new JLabel();
	//事件标签
	JLabel timeJLabel = new JLabel();
	
	
	//随机地雷图
	RndMines1 myRndMines[][]=new RndMines1[13][18];
	myJLabel RndMines[][]=new myJLabel[13][18];
	//RndMines1 myRndMines[][];
	//时间量
	Date startDate=new Date();
	Date nowDate= new Date();
	long timetepDiffer=0;
	long  timerDiffer=0;
	long suretimerDiffer=0;
	long starttime=startDate.getTime();
	
	//导弹标签
	JLabel p1missileJLabel = new JLabel();
	JLabel p2missileJLabel=  new JLabel();
	//生命值标签
	JLabel p1LifeJLabel = new JLabel();
	JLabel p2LifeJLabel = new JLabel();
	//结束按钮
	Button endButton=new Button();
	
	public WindowP2()
	{
		super("双人地图");
		
	setSize(1500, 955);
	setLayout(new BorderLayout());
	Image ilImage =new ImageIcon("src/myimage/ico1.ico").getImage();
	
	try {
		Image icoImage;
		icoImage = ImageIO.read(new File("src/myimage/6.jpg"));
		this.setIconImage(icoImage);
	} catch (IOException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
	
	
	//固定窗体大小
	setResizable(false);
	//初始化地址数组
	getPathBarrier();
	getPathProp();
	add(hp2,BorderLayout.WEST);
	
	hp2.setPreferredSize(new Dimension(200, 955));
	hp2.setSize(150, 955);
	hp2.setVisible(true);
	hp2.setLayout(null);
	add(hp3,BorderLayout.EAST);
	hp3.setPreferredSize(new Dimension(200, 955));
	hp3.setSize(150, 955);
	hp3.setVisible(true);
	hp3.setLayout(null);
	add(hp1,BorderLayout.CENTER);
	hp1.setPreferredSize(new Dimension(900, 955));
	hp1.setSize(900, 955);
	hp1.setVisible(true);
	hp1.setLayout(null);
	//人物出场
	hp1.add(p1);
	hp1.add(p2);
	p1.setBounds(0, 0, 60, 70);
	p2.setBounds(17*60,12*70 , 60, 70);
	Font myFont=new Font("华文行楷", Font.BOLD, 17);
	stopContinue.setVisible(true);
	stopContinue.setLabel("暂停");
	stopContinue.setBackground(Color.black);
	stopContinue.setFont(myFont);
	//stopContinue.setSize(180, 180);
	stopContinue.setBounds(0,hp2.getHeight()-150, 200, 100);
	stopOrcontinue sc = new stopOrcontinue();
	stopContinue.addActionListener(sc);
	hp2.add(stopContinue);
	//为人物绑定移动键盘事件
	MyKeylistener laction=new MyKeylistener();
	addKeyListener(laction);
	hp1.addKeyListener(laction);
	hp1.requestFocus();
	MyKeylistener2 laction2=new MyKeylistener2();
	addKeyListener(laction2);
	hp1.addKeyListener(laction2);
	
	//默认关闭
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	int blockWidth=60;
/*	
	myRndMines[2][2]=new RndMines1();
	hp1.add(myRndMines[2][2]);
	myRndMines[2][2].setIcon(new ImageIcon("src/myimage/mybomb.png"));
	*/
	//lifeLabelOne
	//主题背景音乐循环播放
	bMusic.start(true);
	//字体
	Font ft1 = new Font("楷体", Font.PLAIN, 25);
	//生命值标签
	hp2.add(p1LifeJLabel);
    p1LifeJLabel.setBounds(0,hp2.getHeight()-850, 200, 200);
	p1LifeJLabel.setVisible(true);
	p1LifeJLabel.setForeground(Color.YELLOW);
	p1LifeJLabel.setFont(ft1);
	p1LifeJLabel.setText("生命值: "+String.valueOf(p1.life));
	
	hp3.add(p2LifeJLabel);
    p2LifeJLabel.setBounds(0,hp3.getHeight()-850, 200, 200);
	p2LifeJLabel.setVisible(true);
	p2LifeJLabel.setForeground(Color.YELLOW);
	p2LifeJLabel.setFont(ft1);
	p2LifeJLabel.setText("生命值: "+String.valueOf(p2.life));
	//角色名标签
	hp2.add(p1NameJLabel);
    p1NameJLabel.setBounds(hp2.getWidth()/2,-50, 200, 200);
	p1NameJLabel.setVisible(true);
	p1NameJLabel.setForeground(Color.YELLOW);
	p1NameJLabel.setFont(ft1);
	p1NameJLabel.setText("勇 士");
	
	hp3.add(p2NameJLabel);
    p2NameJLabel.setBounds(hp3.getWidth()/2,-50, 200, 200);
	p2NameJLabel.setVisible(true);
	p2NameJLabel.setForeground(Color.YELLOW);
	p2NameJLabel.setFont(ft1);
	p2NameJLabel.setText("邪 巫");
	
	
	
	//得分标签
	p1PointJLabel.setBounds(hp2.getWidth()/2+50, hp2.getHeight()/2+400, 180, 180);
	p1PointJLabel.setVisible(true);
	p1PointJLabel.setSize(180,180);
	p1PointJLabel.setText("得 分: 0");
	
	p1PointJLabel.setForeground(Color.YELLOW);
	p1PointJLabel.setFont(ft1);
	p1PointJLabel.setBackground(Color.BLUE);
	//雷数标签
	
	hp2.add(p1MineJLabel);
    p1MineJLabel.setBounds(0,hp2.getHeight()-350, 200, 200);
	p1MineJLabel.setVisible(true);
	p1MineJLabel.setForeground(Color.YELLOW);
	p1MineJLabel.setFont(ft1);
	p1MineJLabel.setText("雷 数: 1");
	
	hp3.add(p2MineJLabel);
    p2MineJLabel.setBounds(0,hp3.getHeight()-350, 200, 200);
	p2MineJLabel.setVisible(true);
	p2MineJLabel.setForeground(Color.YELLOW);
	p2MineJLabel.setFont(ft1);
	p2MineJLabel.setText("雷 数: 1");
	//雷区标签
	hp2.add(p1MineAreaJLabel);
    p1MineAreaJLabel.setBounds(0,hp2.getHeight()-550, 200, 200);
	p1MineAreaJLabel.setVisible(true);
	p1MineAreaJLabel.setForeground(Color.YELLOW);
	p1MineAreaJLabel.setFont(ft1);
	p1MineAreaJLabel.setText("雷 区: 1");
	
	hp3.add(p2MineAreaJLabel);
    p2MineAreaJLabel.setBounds(0,hp3.getHeight()-550, 200, 200);
	p2MineAreaJLabel.setVisible(true);
	p2MineAreaJLabel.setForeground(Color.YELLOW);
	p2MineAreaJLabel.setFont(ft1);
	p2MineAreaJLabel.setText("雷 区: 1");
	//时间标签
   
	
	hp3.add(timeJLabel);
    timeJLabel.setBounds(0,hp3.getHeight()-150, 200, 100);
    timeJLabel.setVisible(true);
    timeJLabel.setForeground(Color.YELLOW);
	timeJLabel.setFont(ft1);
	timeJLabel.setText("雷 区: 1");
	
	getDifferTimer.schedule(new timerDiffer(), 0,1000);
	//导弹数标签
	hp2.add(p1missileJLabel);
    p1missileJLabel.setBounds(0,hp2.getHeight()-750, 400, 300);
	p1missileJLabel.setVisible(true);
	p1missileJLabel.setForeground(Color.YELLOW);
	p1missileJLabel.setFont(ft1);
	p1missileJLabel.setText("铁锤数: 0");
	
	hp3.add(p2missileJLabel);
    p2missileJLabel.setBounds(0,hp3.getHeight()-750, 400, 300);
	p2missileJLabel.setVisible(true);
	p2missileJLabel.setForeground(Color.YELLOW);
	p2missileJLabel.setFont(ft1);
	p2missileJLabel.setText("铁锤数: 0");
	//结束按钮
		endButton.setVisible(false);
		hp1.add(endButton);
		endButton.setFont(ft1);
		endButton.setBounds(hp1.getWidth()/2-50, hp1.getHeight()/2-150, 300, 200);
		endButton.setBackground(Color.black);
		backforEnd bdEnd =new backforEnd(this);
		endButton.addActionListener(bdEnd);
		endButton.setForeground(Color.yellow);
	//确定固定通路
	initMap();
	//画随机地图
	paintMap();
	hp1.requestFocus();
	}
		
	public class timerDiffer extends TimerTask{

		@Override
		public void run() {
			// TODO 自动生成的方法存根
			if(stopContinue.getLabel()=="暂停")
			{
			nowDate=new Date();
			suretimerDiffer=suretimerDiffer+timetepDiffer;
			timetepDiffer=0;
			}
			else {
			Date	pDate=new Date();
				timetepDiffer= pDate.getTime()-nowDate.getTime();
			}
			
			timerDiffer=nowDate.getTime()-starttime-suretimerDiffer;
			long days=timerDiffer/(1000*60*60*24);
			long hours=(timerDiffer-days*1000*60*60*24)/(1000*60*60);
			long minutes=(timerDiffer-days*1000*60*60*24-hours*1000*60*60)/(1000*60);
			long second=(timerDiffer-days*1000*60*60*24-hours*1000*60*60-minutes*1000*60)/(1000);
			timeJLabel.setText(String.valueOf(hours)+" 时 "+String.valueOf(minutes)+" 分 "+String.valueOf(second)+" 秒");
			hp1.requestFocus();
			winOrLose();
			
		}
		
	}
	
	
	
	
	//public static void main(String ags[])
	//{
	//	WindowP2 wP2 =new WindowP2();
	//	wP2.setVisible(true);
	//}
	
	
	//退出
	public class backforEnd implements ActionListener
	{
		WindowP2 w1;
		
		public  backforEnd(WindowP2 w) {
			w1=w;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
		
			startpaper startpaper=new startpaper();
			bMusic.stop();
			w1.dispose();
			
			
		}
		
		
	}
	
	
	public void initMap()
	{
	
	for(int i=0;i<hNum;i++)
	{
		for(int j=0;j<wNum;j++)
		{
			
			mymap[i][j]=new myJLabel(mapPath);
			mymap[i][j].flag=0;
			mymap[i][j].setVisible(false);
		}
	}
	
	mymap[0][0].flag=1;
	mymap[0][1].flag=1;
	mymap[0][2].flag=1;
	mymap[0][3].flag=1;
	mymap[0][4].flag=1;
	mymap[0][5].flag=1;
	mymap[0][8].flag=1;
	mymap[0][9].flag=1;
	mymap[0][10].flag=1;
	mymap[1][0].flag=1;
	mymap[1][1].flag=1;
	mymap[1][5].flag=1;
	mymap[1][8].flag=1;
	mymap[1][10].flag=1;
	mymap[2][1].flag=1;
	mymap[2][5].flag=1;
	mymap[2][6].flag=1;
	mymap[2][7].flag=1;
	mymap[2][8].flag=1;
	mymap[2][10].flag=1;
	mymap[3][1].flag=1;
	mymap[3][10].flag=1;
	mymap[3][14].flag=1;
	mymap[3][15].flag=1;
	mymap[3][16].flag=1;
	mymap[4][1].flag=1;
	mymap[4][2].flag=1;
	mymap[4][8].flag=1;
	mymap[4][9].flag=1;
	mymap[4][10].flag=1;
	mymap[4][14].flag=1;
	mymap[4][16].flag=1;
	mymap[5][2].flag=1;
	mymap[5][8].flag=1;
	mymap[5][12].flag=1;
	mymap[5][13].flag=1;
	mymap[5][14].flag=1;
	mymap[5][16].flag=1;
	mymap[6][2].flag=1;
	mymap[6][8].flag=1;
	mymap[6][12].flag=1;
	mymap[6][16].flag=1;
	mymap[7][2].flag=1;
	mymap[7][8].flag=1;
	
	mymap[7][11].flag=1;
	mymap[7][12].flag=1;
	mymap[7][14].flag=1;
	mymap[7][15].flag=1;
	mymap[7][16].flag=1;
	mymap[8][2].flag=1;
	mymap[8][3].flag=1;
	mymap[8][4].flag=1;
	mymap[8][5].flag=1;
	mymap[8][6].flag=1;
	mymap[8][7].flag=1;
	mymap[8][8].flag=1;
	mymap[8][9].flag=1;
	mymap[8][10].flag=1;
	mymap[8][11].flag=1;
	mymap[8][14].flag=1;
	mymap[9][14].flag=1;
	mymap[10][12].flag=1;
	mymap[10][13].flag=1;
	mymap[10][14].flag=1;
	mymap[11][12].flag=1;
	mymap[11][16].flag=1;
	mymap[11][17].flag=1;
	mymap[12][12].flag=1;
	mymap[12][13].flag=1;
	mymap[12][14].flag=1;
	mymap[12][15].flag=1;
	mymap[12][16].flag=1;
	mymap[12][17].flag=1;
	
	hp1.requestFocus();
	}

public void paintMap () {
	//mymap[12][22]=0;
	rnd1=new Random(new Date().getTime());
	rnd2=new Random(new Date().getTime()*5+22);

	
	int num;
	int item1;
	for(int i=0;i<hNum;i++)
	{
		for(int j=0;j<wNum;j++)
		{
			//特效初始化
			myTexiao[i][j] = new myJLabel("src/myimage/guang.png");
			//myTexiao[j][i].setIcon(new ImageIcon("src/myimage/guang.png"));
			//myTexiao[j][i].setIcon(new ImageIcon(this.getClass().getResource("hengguang.png")));
			hp1.add(myTexiao[i][j]);
			myTexiao[i][j].setBounds(j*60, i*70, 60, 70);
			myTexiao[i][j].setVisible(false);
			//炸弹图初始化
			RndMines[i][j]=new myJLabel("src/myimage/mybomb.png");
			hp1.add(RndMines[i][j]);
			RndMines[i][j].setBounds(j*60, i*70, 60, 70);
			RndMines[i][j].setVisible(false);
			//可摧毁障碍初始化
			//myBarrLabel[i][j]=new myJLabel("src/myimage/girl.png");
		//	myBarrLabel[i][j].setVisible(false);
			if(mymap[i][j].flag==1)
				continue;

			if(i<2&&j<2||i>10&&j>15)
				continue;
			int k=rnd1.nextInt(100);
			if(k>56&&mymap[i][j].flag!=1)
			{
				//if(mymap[i][j]==null)
				//mymap[i][j]=new myJLabel(mapPath);
				mymap[i][j].flag=2;
				mymap[i][j].setVisible(true);
			hp1.add(mymap[i][j]);
			mymap[i][j].setBounds(j*60, i*70, 60, 70);
			mymap[i][j].setSize(60, 70);
			}
		}
	}
	
	for(int i=0;i<hNum;i++)
	{
		for(int j=0;j<wNum;j++)
		{
			if(i<2&&j<2||i>10&&j>15)
				continue;
			int k=rnd2.nextInt(100);
			int t=rnd1.nextInt(4)+5;
			if(k>75&&mymap[i][j].flag!=2)
			{
				if(myProLabel[i][j]==null)
				myProLabel[i][j]=new myJLabel(pathProp[t-5]);
				myProLabel[i][j].flag=t;
				myProLabel[i][j].setBounds(j*60, i*70, 60, 70);
				myProLabel[i][j].setVisible(true);
				hp1.add(myProLabel[i][j]);
			}
		}
	}
	
	for(int i=0;i<hNum;i++)
		
	{
		for(int j=0;j<wNum;j++)
		{

			if(i<2&&j<2||i>10&&j>15)
				continue;
			int k=rnd2.nextInt(200);
			int t=rnd1.nextInt(3)+5;
			
			if(k>45&&mymap[i][j].flag!=2)
			{
				if(myBarrLabel[i][j]==null)
					myBarrLabel[i][j]=new myJLabel(pathBarrier[t-5]);
				myBarrLabel[i][j].flag=t;
				myBarrLabel[i][j].setBounds(j*60, i*70, 60, 70);
				myBarrLabel[i][j].setVisible(true);
				hp1.add(myBarrLabel[i][j]);
			}
			
		}
	}
	

	hp1.requestFocus();	
	
}

public void getPathBarrier() {
	pathBarrier[0]="src/myimage/cat.png";
	pathBarrier[1]="src/myimage/girl.png";
	pathBarrier[2]="src/myimage/dragon.png";
}


public void getPathProp() {
	pathProp[0]="src/myimage/bomb.png";
	pathProp[1]="src/myimage/hudun.png";
	pathProp[2]="src/myimage/hammer.png";
	pathProp[3]="src/myimage/potion.png";
			
	
}

//暂停开始按钮
public class stopOrcontinue implements ActionListener{

	
	public void actionPerformed(ActionEvent e) {
		/*if(flag)
		{
			stopContinue.setLabel("继续");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		}
		else {
			stopContinue.setLabel("暂停");
		}*/
		hp1.requestFocus();
		
		if(stopContinue.getLabel()=="继续")
		{
			stopContinue.setLabel("暂停");
		/*	try {
				getDifferTimer.wait(3000);
			} catch (InterruptedException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}*/
		}
		else {
			stopContinue.setLabel("继续");
			//getDifferTimer.notifyAll();
		}
		
	}
	
}
//死亡/输赢响应
public void winOrLose()
{
	if(p1.life<=0&&p1.flag==0||p2.life<=0&&p2.flag==0 &&tag==0)
		
	{
		stopContinue.setLabel("继续");
		endButton.setVisible(true);
		tag=5;
		//p1.flag=1;
		//p2.life=1;
		dieMusic=new Music("src/myvideo/die.wav");
		dieMusic.start(false);
		winbMusic=new Music("src/myvideo/win.wav");
		
		winbMusic.start(false);
		//stopContinue.setLabel("继续");
		
		//p2_j2.setText("继续");
		/*int re= JOptionPane.showConfirmDialog(jP1, "你的得分是: "+per1.pointer+" !");
	dieMusic.start(false);
		if(re==JOptionPane.OK_OPTION)
			System.exit(0);
			*/
		if(p1.life<=0&&p2.life>0)
		{
			p1.flag=1;
		//int re= JOptionPane.showConfirmDialog(hp1,p2NameJLabel.getText()+"赢了");
		dieMusic.start(false);
		//if(re==JOptionPane.OK_OPTION)
			//System.exit(0);
		
		endButton.setLabel(p2NameJLabel.getText()+"赢了");
		}
		else if(p1.life>0&&p2.life<=0)
		{
			p2.flag=1;
			//int re= JOptionPane.showConfirmDialog(hp1,p1NameJLabel.getText()+"赢了");
			dieMusic.start(false);
			//if(re==JOptionPane.OK_OPTION)
			//	System.exit(0);
			endButton.setLabel(p1NameJLabel.getText()+"赢了");
		}
		else if(p1.life<=0&&p2.life<=0)
		{
			int re= JOptionPane.showConfirmDialog(hp1,"平局");
			dieMusic.start(false);
			if(re==JOptionPane.OK_OPTION)
				System.exit(0);
		}
	}
	
}

//锤子攻击

public void p2hammerdecreaselife(int face)
{
	int p1x=p1.wNum;
	int p1y=p1.hNum;
	int p2x=p2.wNum;
	int p2y=p2.hNum;
	if(p1.life>=1)
	{	
	if(face==0)
	{
		if((p1x==p2x+1||p1x==p2x+2)&&p1y==p2y)
		{
			p1.life=p1.life-1;
		}
		
	}
	if(face==1)
	{
		if(p1x==p2x&&(p1y==p2y-1||p1y==p2y-2))
		{
			p1.life=p1.life-1;
		}
	}
	if(face==2)
	{
		if(p1x==p2x&&(p1y==p2y+1||p1y==p2x+2))
		{
			p1.life=p1.life-1;
		}
	}
	if(face==3)
	{
		if((p1x==p2x-1||p1x==p2x-2)&&p1y==p2y)
		{
			p1.life=p1.life-1;
		}
	}
	}
	p2.missileNum=p2.missileNum-1;
	p2missileJLabel.setText("铁锤数: "+String.valueOf(p2.missileNum));
	p2LifeJLabel.setText("生命值: "+String.valueOf(p2.life));
	p1LifeJLabel.setText("生命值: "+String.valueOf(p1.life));
}

public void p1hammerdecreaselife(int face)
{
	int p1x=p1.wNum;
	int p1y=p1.hNum;
	int p2x=p2.wNum;
	int p2y=p2.hNum;
	if(p2.life>1)
	{
	
	if(face==0)
	{
		if((p2x==p1x+1||p2x==p1x+2)&&p2y==p1y)
		{
			p2.life=p2.life-1;
		}
		
	}
	if(face==1)
	{
		if(p2x==p1x&&(p2y==p1y-1||p2y==p1y-2))
		{
			p2.life=p2.life-1;
		}
	}
	if(face==2)
	{
		if(p2x==p1x&&(p2y==p1y+1||p2y==p1y+2))
		{
			p2.life=p2.life-1;
		}
	}
	if(face==3)
	{
		if((p2x==p1x-1||p2x==p1x-2)&&p2y==p1y)
		{
			p2.life=p2.life-1;
		}
	}
	}
	p1.missileNum=p1.missileNum-1;
	p1missileJLabel.setText("铁锤数: "+String.valueOf(p1.missileNum));
	p1LifeJLabel.setText("生命值: "+String.valueOf(p1.life));
	p2LifeJLabel.setText("生命值: "+String.valueOf(p2.life));
}

public class MyKeylistener2 implements KeyListener {

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		hp1.requestFocus();
		// TODO 自动生成的方法存根
		if(stopContinue.getLabel()=="暂停")
		{	
		
		int locationx=p1.getW();//得到横坐标
		int locationy=p1.getH();//得到纵坐标
		int lx=p2.getW();
		int ly=p2.getH();
		
		if(e.getKeyCode()==0||e.getKeyCode()==KeyEvent.VK_0||e.getKeyChar()=='0') {
		
			if(p2.missileNum>0)
			p2hammerdecreaselife(p2.faceouter);
		}
	
		
		
		
		if(e.getKeyCode()==KeyEvent.VK_ENTER )
		{
			//myRndMines[lx][ly]=new RndMines1(lx,ly);	
			
			//hp1.add(myRndMines[lx][ly]);
			
			//myRndMines[lx][ly].setIcon(new ImageIcon("src/myimage/mybomb.png"));
			//myRndMines[i][j].setIcon(new ImageIcon(this.getClass().getResource("mybomb.png")));
			//myRndMines[lx][ly].setVisible(true);
		
			//myRndMines[lx][ly].setBounds(ly*60, lx*70, 60, 70);
			//System.out.println("jdks");
			//myRndMines[lx][ly].getxy(lx, ly);
			if(p2.layMinesNum<p2.minesNum)
			{	
				p2.layMinesNum=p2.layMinesNum+1;
			RndMines[lx][ly].setVisible(true);
			
				
			myRndMines[lx][ly] =new RndMines1(lx, ly,2);
			}
			
		}
		
		if(e.getKeyCode()==KeyEvent.VK_UP )
		{
			
			if(lx-1<0||mymap[lx-1][ly].flag==2 )
				p2.changeW(0);
			else 
			{
				
			
			lx=lx-1;
			if(myBarrLabel[lx][ly]==null||myBarrLabel[lx][ly].flag<5)
			{
			p2.changeW(-1);
			p2.setLocation(ly*60,lx*70 );
			p2.faceTo(3);
			
			//捡道具
			if(myProLabel[lx][ly]!=null)
			{
				myProLabel[lx][ly].setVisible(false);
				
				p2.changeNum(myProLabel[lx][ly].flag);
				myProLabel[lx][ly].flag=-1;
				jiandaojuMusic=new Music("src/myvideo/jiandaoju.wav");
				jiandaojuMusic.start(false);
			}
				}
				}
		}
		
		if(e.getKeyCode()==KeyEvent.VK_DOWN )
		{
			
			if(lx+1>=13||mymap[lx+1][ly].flag==2)
				p2.changeW(0);
			else 
				{
			
			lx=lx+1;
			
			if(myBarrLabel[lx][ly]==null||myBarrLabel[lx][ly].flag<5)
			{
			p2.changeW(1);
			p2.setLocation(ly*60,lx*70 );
			p2.faceTo(0);
			
			if(myProLabel[lx][ly]!=null)
			{
				myProLabel[lx][ly].setVisible(false);
				
				p2.changeNum(myProLabel[lx][ly].flag);
				myProLabel[lx][ly].flag=-1;
				jiandaojuMusic=new Music("src/myvideo/jiandaoju.wav");
				jiandaojuMusic.start(false);
			}
				}
		}
		}
		
		if(e.getKeyCode()==KeyEvent.VK_RIGHT )
		{
			
			if(ly+1>17||mymap[lx][ly+1].flag==2)
				p2.changeH(0);
			else 
				{
			
			ly=ly+1;
			if(myBarrLabel[lx][ly]==null||myBarrLabel[lx][ly].flag<5)
			{
			p2.changeH(1);
			p2.setLocation(ly*60,lx*70 );
			p2.faceTo(2);
			
			if(myProLabel[lx][ly]!=null)
			{
				myProLabel[lx][ly].setVisible(false);
				
				p2.changeNum(myProLabel[lx][ly].flag);
				myProLabel[lx][ly].flag=-1;
				jiandaojuMusic=new Music("src/myvideo/jiandaoju.wav");
				jiandaojuMusic.start(false);
			}
				}
				}
		}
		
		if(e.getKeyCode()==KeyEvent.VK_LEFT )
		{
			
			if(ly-1<0||mymap[lx][ly-1].flag==2)
				p2.changeH(0);
			else 
				{
			
			ly=ly-1;
			if(myBarrLabel[lx][ly]==null||myBarrLabel[lx][ly].flag<5)
			{
			p2.changeH(-1);
			p2.setLocation(ly*60,lx*70 );
			p2.faceTo(1);
			if(myProLabel[lx][ly]!=null)
			{
				myProLabel[lx][ly].setVisible(false);
				
			p2.changeNum(myProLabel[lx][ly].flag);
			myProLabel[lx][ly].flag=-1;
			jiandaojuMusic=new Music("src/myvideo/jiandaoju.wav");
			jiandaojuMusic.start(false);
			}
			}
				}
		}
		
		p2MineJLabel.setText("雷数: "+String.valueOf(p2.minesNum-p2.layMinesNum));
		p2MineAreaJLabel.setText("雷区: "+String.valueOf(p2.areaMine));
		p2missileJLabel.setText("铁锤数: "+String.valueOf(p2.missileNum));
		winOrLose();
	//}
	}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO 自动生成的方法存根
		
	}
		
	

}



public class MyKeylistener implements KeyListener {

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO 自动生成的方法存根
		
	}

	
	public void keyPressed(KeyEvent e) {
		hp1.requestFocus();
		// TODO 自动生成的方法存根
		
	
		
		if(stopContinue.getLabel()=="暂停")
		{	
		
		int locationx=p1.getW();//得到横坐标
		int locationy=p1.getH();//得到纵坐标
		int lx=p2.getW();
		int ly=p2.getH();
	/*	if(e.getKeyCode()==KeyEvent.VK_UP )
		{
			
			if(lx-1<0||mymap[lx-1][ly].flag==2)
				p2.changeW(0);
			else 
				{
			
			lx=lx-1;
			p2.changeW(-1);
			p2.setLocation(ly*60,lx*70 );
			p2.faceTo(3);
				}
		}
		
		if(e.getKeyCode()==KeyEvent.VK_DOWN )
		{
			
			if(lx+1>=13||mymap[lx+1][ly].flag==2)
				p2.changeW(0);
			else 
				{
			
			lx=lx+1;
			p2.changeW(1);
			p2.setLocation(ly*60,lx*70 );
			p2.faceTo(0);
				}
		}
		
		if(e.getKeyCode()==KeyEvent.VK_RIGHT )
		{
			
			if(ly+1>17||mymap[lx][ly+1].flag==2)
				p2.changeH(0);
			else 
				{
			
			ly=ly+1;
			p2.changeH(1);
			p2.setLocation(ly*60,lx*70 );
			p2.faceTo(2);
				}
		}
		
		if(e.getKeyCode()==KeyEvent.VK_LEFT )
		{
			
			if(ly-1<0||mymap[lx][ly-1].flag==2)
				p2.changeH(0);
			else 
				{
			
			ly=ly-1;
			p2.changeH(-1);
			p2.setLocation(ly*60,lx*70 );
			p2.faceTo(1);
				}
		}
		
		*/
		if(e.getKeyChar()=='Q'||e.getKeyChar()=='q')
		{
			if(p1.missileNum>0)
		p1hammerdecreaselife(p1.faceouter);
		}
		
		
		if(e.getKeyCode()==KeyEvent.VK_SPACE )
		{
			//if(myRndMines[locationx][locationy]==null)
			//{myRndMines[locationx][locationy]=new RndMines1(locationx,locationy);
			//hp1.add(myRndMines[locationx][locationy]);}
			//int dis=r1.nextInt(15000)+100 ;
			//myRndMines[i][j].timeDis=dis;
			//myRndMines[locationx][locationy].setIcon(new ImageIcon("src/myimage/mybomb.png"));
			//myRndMines[i][j].setIcon(new ImageIcon(this.getClass().getResource("mybomb.png")));
			//myRndMines[locationx][locationy].setVisible(true);
			//t1.schedule(new Explosion(i,j), dis );
			//myRndMines[i][j].timeDis=dis;
			//myRndMines[locationx][locationy].setBounds(locationy*60, locationx*70, 60, 70);
			//myRndMines[locationx][locationy].getxy(locationx, locationy);
			if(p1.layMinesNum<p1.minesNum)
			{	
				p1.layMinesNum=p1.layMinesNum+1;
			
			RndMines[locationx][locationy].setVisible(true);
			myRndMines[locationx][locationy] =new RndMines1(locationx, locationy,1);
			myRndMines[locationx][locationy].getxy(locationx, locationy);
			}
		}
		
		if(e.getKeyChar()=='w'||e.getKeyChar()=='W')
		{//a=iftouch()&&ifout()//&&iftouch()&&ifout()函数判断是否碰到建筑或者超出窗体
			
			if(locationx-1<0||mymap[locationx-1][locationy].flag==2)
				p1.changeW(0);
			else 
				{
					
			locationx=locationx-1;
			if(myBarrLabel[locationx][locationy]==null||myBarrLabel[locationx][locationy].flag<5)
			{
			p1.changeW(-1);
			p1.setLocation(locationy*60,locationx*70 );
			p1.faceTo(3);
			
			if(myProLabel[locationx][locationy]!=null)
			{	myProLabel[locationx][locationy].setVisible(false);
			p1.changeNum(myProLabel[locationx][locationy].flag);
			myProLabel[locationx][locationy].flag=-1;
			jiandaojuMusic=new Music("src/myvideo/jiandaoju.wav");
			jiandaojuMusic.start(false);
			}
				}
				}
		}
		if(e.getKeyChar()=='s'||e.getKeyChar()=='S')
		{
		if(locationx+1>13||mymap[locationx+1][locationy].flag==2)
			p1.changeW(0);
		else 
			{
			
		locationx=locationx+1;
		if(myBarrLabel[locationx][locationy]==null||myBarrLabel[locationx][locationy].flag<5)
		{
		p1.changeW(1);
		p1.setLocation(locationy*60,locationx*70 );
		
		p1.faceTo(0);
		if(myProLabel[locationx][locationy]!=null)
		{	myProLabel[locationx][locationy].setVisible(false);
		p1.changeNum(myProLabel[locationx][locationy].flag);
		myProLabel[locationx][locationy].flag=-1;
		jiandaojuMusic=new Music("src/myvideo/jiandaoju.wav");
		jiandaojuMusic.start(false);
		}
			}
			}
		}
		if(e.getKeyChar()=='a'||e.getKeyChar()=='A')
		{
			if(locationy-1<0||mymap[locationx][locationy-1].flag==2)
				p1.changeH(0);
			else 
				{
				
			locationy=locationy-1;
			if(myBarrLabel[locationx][locationy]==null||myBarrLabel[locationx][locationy].flag<5)
			{
			p1.changeH(-1);
			p1.setLocation(locationy*60,locationx*70 );
			
			p1.faceTo(1);
			if(myProLabel[locationx][locationy]!=null)
			{	myProLabel[locationx][locationy].setVisible(false);
			p1.changeNum(myProLabel[locationx][locationy].flag);
			myProLabel[locationx][locationy].flag=-1;
			jiandaojuMusic=new Music("src/myvideo/jiandaoju.wav");
			jiandaojuMusic.start(false);
			}
			
				}
				}
		}
		if(e.getKeyChar()=='d'||e.getKeyChar()=='D')
		{
			if(locationy+1>=23||mymap[locationx][locationy+1].flag==2)
				p1.changeH(0);
			else {
				
			
			locationy=locationy+1;
			if(myBarrLabel[locationx][locationy]==null||myBarrLabel[locationx][locationy].flag<5)
			{
			p1.changeH(1);
			p1.setLocation(locationy*60,locationx*70 );
		p1.faceTo(2);
		
		if(myProLabel[locationx][locationy]!=null)
		{	myProLabel[locationx][locationy].setVisible(false);
		
			p1.changeNum(myProLabel[locationx][locationy].flag);
			myProLabel[locationx][locationy].flag=-1;
			jiandaojuMusic=new Music("src/myvideo/jiandaoju.wav");
			jiandaojuMusic.start(false);
		}
				}
			}
		}
		p1MineJLabel.setText("雷数: "+String.valueOf(p1.minesNum-p1.layMinesNum));
		p1MineAreaJLabel.setText("雷区: "+String.valueOf(p1.areaMine));
		p1missileJLabel.setText("铁锤数: "+String.valueOf(p1.missileNum));
		winOrLose();
		}
		//p1MineJLabel.setText("雷数: "+String.valueOf(p1.minesNum));
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO 自动生成的方法存根
		
	}
	
}
//爆炸减少生命值方法
public void decreaseByexplode(int i,int j)
{
	int ax=i,by=j;
	if(ax==p1.wNum&&by==p1.hNum&&p1.life>0)
	{
		p1.life=p1.life-1;
	}

	if(ax==p2.wNum&&by==p2.hNum&&p2.life>0)
	{
		p2.life=p2.life-1;
	}

	p1LifeJLabel.setText("生命值: "+String.valueOf(p1.life));
	p2LifeJLabel.setText("生命值: "+String.valueOf(p2.life));
	
}


//随机地雷类



public class RndMines1 
{
	public int timeDis;
	public int x,y,who;
	public int area=1;
	public 	Timer mer1,mer2;
	public RndMines1(int i,int j,int w)
	{
		mer1=new Timer();
		mer2=new Timer();
		x=i;
		y=j;
		//area=2;
		who=w;
		timeDis=rnd1.nextInt(2000)+100;
		//setIcon(new ImageIcon("src/myimage/mybomb.png"));
		mer1.schedule(new barrDis(),timeDis+ 500);
		mer2.schedule(new texiaoDis(),timeDis+ 1000);
	
	}
	void getxy(int i,int j)
	{
		x=i;
		y=j;
		
	}
	
	public class barrDis extends TimerTask
	{
	/*	public barrDis()
		{
			run();
		}
*/
		
		public void run() {
			if(who==1)
			{
				p1.layMinesNum=p1.layMinesNum-1;
				p1MineJLabel.setText("雷数: "+String.valueOf(p1.minesNum-p1.layMinesNum));
				area=p1.areaMine;
			}
			else if(who==2)
			{
				p2.layMinesNum=p2.layMinesNum-1;
				p2MineJLabel.setText("雷数: "+String.valueOf(p2.minesNum-p2.layMinesNum));
				area=p2.areaMine;
			}
			RndMines[x][y].setVisible(false);
			bombMusic=new Music("src/myvideo/explode.wav");
			bombMusic.start(false);
			for(int i=x-1;i>=x-area;i--)
			{
				//myTexiao[i][y].setVisible(true);
				//测试
				//myBarrLabel[i][y].setVisible(false);
				if(i>=0&&i<13)
				{	
				if(mymap[i][y].flag==2)
					break;
				if(myBarrLabel[i][y]!=null)
				{
				myBarrLabel[i][y].flag=-1;
				myBarrLabel[i][y].setVisible(false);
				}
				myTexiao[i][y].setVisible(true);
				}
				
				decreaseByexplode(i, y);
			}
			
			for(int i=x;i<=x+area;i++)
			{
				if(i>=0&&i<13)
				{		
				if(mymap[i][y].flag==2)
					break;
				if(myBarrLabel[i][y]!=null)
				{
				myBarrLabel[i][y].flag=-1;
				myBarrLabel[i][y].setVisible(false);
				}
				myTexiao[i][y].setVisible(true);
				}
				decreaseByexplode(i, y);
			}
			
			for(int j=y-1;j>=y-area;j--)
			{
				if(j>=0&&j<18)
				{		
				if(mymap[x][j].flag==2)
					break;
				if(myBarrLabel[x][j]!=null)
				{
				myBarrLabel[x][j].flag=-1;
				myBarrLabel[x][j].setVisible(false);
				}
				myTexiao[x][j].setVisible(true);
				}
				decreaseByexplode(x, j);
			}
	
			for(int j=y+1;j<=y+area;j++)
			{
				if(j>=0&&j<18)
				{		
				if(mymap[x][j].flag==2)
					break;
				if(myBarrLabel[x][j]!=null)
				{
				myBarrLabel[x][j].flag=-1;
				myBarrLabel[x][j].setVisible(false);
				}
				myTexiao[x][j].setVisible(true);
				}
				decreaseByexplode(x, j);
			}
	
			
		}
	}
	
	//特效消失类
	public class texiaoDis extends TimerTask
	{
	/*	public texiaoDis()
		{
			run();
		}
		*/
		public void run() {
			
			/*for(int i=x-area;i<=x+area;i++)
			{
				if(i>=0&&i<13)
				myTexiao[i][y].setVisible(false);
			}
			
			for(int j=y-area;j<=y+area;j++)
			{
				if(j>=0&&j<18)
					
					myTexiao[x][j].setVisible(false);
			}*/
			
			for(int i=x;i>=x-area;i--)
			{

				if(i>=0&&i<13)
					
			
				myTexiao[i][y].setVisible(false);
			}
			
			for(int i=x+1;i<=x+area;i++)
			{

				if(i>=0&&i<13)
			
				myTexiao[i][y].setVisible(false);
			}
			
			for(int j=y;j>=y-area;j--)
			{
		
				if(j>=0&&j<18)
					
				myTexiao[x][j].setVisible(false);
			}
	
			for(int j=y+1;j<=y+area;j++)
			{
	
				if(j>=0&&j<18)
				myTexiao[x][j].setVisible(false);
			}
			
		}
		
	}
	
}



}





	 class HomePanel extends JPanel {
		ImageIcon icon;
		Image img;
		public HomePanel(String s) {
			//  /img/HomeImg.jpg 是存放在你正在编写的项目的bin文件夹下的img文件夹下的一个图片
			//icon=new ImageIcon("src/myimage/1back.jpg");
			icon= new ImageIcon(s);
			img=icon.getImage();
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			//下面这行是为了背景图片可以跟随窗口自行调整大小，可以自己设置成固定大小
			g.drawImage(img, 0, 0,this.getWidth(), this.getHeight(), this);
		}
	 
	}


//地图标签类
  class myJLabel extends JLabel
{
	public int flag=0;
	//String pathString;
	public myJLabel(String pathString)
	{
		flag=100;
		setIcon(new ImageIcon(pathString));
		setVisible(false);
	}
}


