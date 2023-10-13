package classPerson;
import classWindows.*;
import classWindows.WindowP2.RndMines1;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import myimage.*;
public class PersonOne extends JLabel  {
	public int wNum;
	public int hNum;
	public int life;
	public int pointer;
	public int flag=0;
	public int f;
	public int minesNum=1;
	public int areaMine=1;
	public int missileNum=0;
	public int layMinesNum=0;
	public int faceouter=0;
	String pathPersonOne[]= {"src/myimage/p1前.png","src/myimage/p1左.png","src/myimage/p1右.png","src/myimage/p1后.png"};
	//String pathPersonOne[]= {"p2前.png","p2左.png","p2右.png","p2后.png"};
	String pathPersonTwo[]= {"src/myimage/p2前.png","src/myimage/p2左.png","src/myimage/p2右.png","src/myimage/p2后.png"};
	public PersonOne(int fl)
	
	{
		super("fskjj");
		
		life=3;
		f=fl;
		if(f==2)
		{
			wNum=12;
			hNum=17;
		}
		faceTo(0);
		//setIcon(new ImageIcon("src/myimage/test1.png"));
	
	}
	public void changeNum(int gh)
	{
		if(gh==5)
		{
			minesNum=minesNum+1;
		}
		if(gh==6)
		{
			life=life+1;
		}
		if(gh==7)
		{
			missileNum=missileNum+1;
			
		}
		if(gh==8)
		{
			areaMine=areaMine+1;
		}
	}

	//更新和获取人物位置信息
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
		if(hNum+t<18&&hNum>=0)
		{
			hNum=hNum+t;
		}
	}
	
	
	//改变人物朝向
	public void faceTo (int t) {
		//setIcon(new ImageIcon(pathPersonOne[t]));
		if(f==1)
		//setIcon(new ImageIcon(this.getClass().getResource(pathPersonOne[t])));
		{
		setIcon(new ImageIcon(pathPersonOne[t]));
		faceouter=t;
		}
		else if(f==2){
			//setIcon(new ImageIcon(this.getClass().getResource(pathPersonTwo[t])));
			faceouter=t;
			setIcon(new ImageIcon(pathPersonTwo[t]));
		}
		
	}
	

	

}
