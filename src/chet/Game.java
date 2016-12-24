package chet;

import java.util.Observable;

public class Game implements java.util.Observer{
	Rule rule;
	Chess[] allChess;
	Player p1,p2;
	public Game(Chess[] allChess ,Player p1 ,Player p2){
		this.allChess=allChess;
		this.p1=p1;
		this.p2=p2;
		rule=new Rule(allChess,p1,p2);
	}
	public void update(Observable arg0, Object state) {
		rule.isVictory();
	}
	
	public void change(Chess c,Coordinate coo){
		rule.isMove(c, coo);
	}
	
}
