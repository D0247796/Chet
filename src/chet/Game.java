package chet;

import java.util.Observable;

public class Game {
	private Rule rule;
	private Chess[] allChess;
	private Player p1,p2,firstPlayer,secondPlayer;
	int playerNumber=1;
	public Game(Chess[] allChess ,Player p1 ,Player p2){
		this.allChess=allChess;
		this.p1=p1;
		this.p2=p2;
		rule=new Rule(allChess,p1,p2);
	}
	
	public void isPlayer(Player p){
		if(p.getName().equals("p1")){
			firstPlayer=p1;
			secondPlayer=p2;
		}else{
			firstPlayer=p2;
			secondPlayer=p1;
		}
	}
	public Player whoPlay(){
		if(playerNumber==1){
			return firstPlayer;
		}else{
			return secondPlayer;
		}	
	}
	public void changePlayer(){
		
		if(playerNumber==1){
			playerNumber=2;
			System.out.println("´«ª±®a"+secondPlayer.getName());
		}else{
			playerNumber=1;
			System.out.println("´«ª±®a"+firstPlayer.getName());
		}	
	}
	
	public Chess[] getAllChess(){
		return allChess;
	}
	
	public void change(Chess c,Coordinate coo){
		rule.isMove(c, coo);
	}
	public Rule getRule(){
		return rule;
	}
	
}
