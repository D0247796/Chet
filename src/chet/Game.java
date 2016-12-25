package chet;

import java.util.Observable;

public class Game {
	Rule rule;
	Chess[] allChess;
	Player p1,p2;
	public Game(Chess[] allChess ,Player p1 ,Player p2){
		this.allChess=allChess;
		this.p1=p1;
		this.p2=p2;
		rule=new Rule(allChess,p1,p2);
	}
	
	public Chess[] getAllChess(){
		return allChess;
	}
	
	public void change(Chess c,Coordinate coo){
		rule.isMove(c, coo);
	}
	
}
