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
JButton one =new JButton("单人模式");
JButton two =new JButton("双人模式");
JButton over =new JButton("退出");
JButton music=new JButton("音乐:开");
JButton help=new JButton("帮助");
JButton money=new JButton("求打赏");
MyMouselistener laction1=new MyMouselistener (1,this);//监听单人模式按钮
MyMouselistener laction2=new MyMouselistener (2,this);//双人
MyMouselistener laction3=new MyMouselistener (3,this);//退出
MyMouselistener laction4=new MyMouselistener (4,this);//打赏
MyMouselistener laction5=new MyMouselistener (5,this);//帮助
MyMouselistener laction6=new MyMouselistener (6,this);//音乐
//JFrame app1=new JFrame("单人模式");
WindowP111 app1 ;
//JFrame app2=new JFrame("双人模式");
WindowP2 app2 ;
//Dialog app3=new Dialog(this);
JDialog app3=new JDialog(this,"帮助");
JFrame app4=new JFrame("求打赏");
startpaper bf ;
public startpaper() {
	
	super("开始界面");
	player.start(true);
	Image ilImage =new ImageIcon("src/myimage/ico1.ico").getImage();
	
	try {
		Image icoImage;
		icoImage = ImageIO.read(new File("src/myimage/6.jpg"));
		this.setIconImage(icoImage);
	} catch (IOException e) {
		// TODO 自动生成的 catch 块
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
    Font ft1 = new Font("楷体", Font.PLAIN, 25);

    app3.add(scrollPane);
  
    textArea.setFont(ft1);
    textArea.setOpaque(false);
    textArea.setForeground(Color.yellow);
    textArea.setBackground(Color.yellow);
    textArea.setEditable(false); // 设置文本区域为不可编辑
    textArea.setText("从前有一个年轻的勇士，他深爱着一只名叫小咪的猫咪。\r\n"+
   		 "\r\n" 
    		+ "有一天，勇士回到家里，发现小咪不见了。他十分着急，四处寻找小咪。\r\n"+
    		 "\r\n" 
    		+ "终于，他在森林深处找到了一座高塔，塔顶上盘旋着几只黑暗的乌鸦。\r\n" + 
    		"\r\n" 
    		+"勇士进入塔内，发现一名邪巫正在用黑魔法诅咒他的心爱的小猫咪。\r\n"
    		+ "勇士急忙向邪巫挑战，但是邪巫却一直保护着自己，勇士不得不打弱化邪巫保护罩的主意。\r\n"
    		+ "最终，他发现邪巫的魔法结界是通过四个符文柱子维持的，于是勇士决定摧毁这四个符文柱子。\r\n"
    		+ "经过了一番苦战，勇士终于成功摧毁了这四个符文柱子。\r\n" + 
    		"\r\n" + 
    		"这个时候，邪巫的黑暗魔法失效了，小咪得救了。\r\n"
    		+ "勇士打败了邪巫，解救了自己心爱的猫咪，带着小咪回到家中。\r\n"
    		+ "自此，勇士和小咪之间的友情更加深厚了。\r\n" + 
    		"\r\n" + 
    		"这就是一个传奇的故事，讲述了一位勇敢的勇士，\r\n"
    		+ "为了拯救心爱的猫咪，在危险中保护自己，\r\n"
    		+ "并打败了邪恶的黑魔法师，终于得以成功地救回自己心爱的小猫咪，他们之间的友谊也得到了更好的加强。\r\n"
    		+ "单人模式：在不断生成的炸弹里拾取宝箱比较计分\r\n"
    		+ "双人模式：利用炸弹击杀对方\r\n"
    		+"1p:w上a左s下d右 spaces释放炸弹（炸弹爆炸时间随机）,q丢铁锤  2P:小键盘操作,enter键释放炸弹，0丢铁锤"
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
		// TODO 自动生成的方法存根
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
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
			
			break;}
		case 5:app3.setVisible(true);break;
		case 6:{if(able==true)
		{
			music.setText("音乐:关");
			music.setIcon(new ImageIcon("src/myimage/yin2.png"));
			repaint();
			able=false;
			
			player.stop();
			
			
		}
		else
		{
			music.setText("音乐:开");
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
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		
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



	 public class CustomTextArea extends JTextArea {
		    private BufferedImage backgroundImage;

		    public CustomTextArea() {
		        try {
		            backgroundImage = ImageIO.read(new File("src/myimage/catback1.jpg")); // 替换为你的背景图像文件路径
		         
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
