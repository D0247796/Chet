package chet;

import java.io.File;
import java.util.Observable;

import chet.ChessBoard.MyButton;





public class Game extends java.util.Observable{
	File picture4 = new File("D:/編輯程式/eclipse/workspace/chet/src/chet/picture/桌面.jpg");
	
	private Chess[] allChess;
	private MyButton[] chooseButton=new MyButton[2];
	private Player[] p;
	private Player firstPlayer, secondPlayer;
	private Rule rule;
	private int playerNumber = 1;
	private ChessBoard chessBoard;

	public Game(ChetGame cg) {
		this.p=cg.creatPlayer();
		this.allChess = cg.creatChess(p);
		this.rule=cg.creatRule(allChess, p);
		this.firstPlayer = p[0];
		this.secondPlayer = p[1];
		this.chessBoard = cg.creatChessBoard(this);
	}

	public void chooseButton(MyButton b){
		if(chooseButton[0]==null && b.getChess().getWeight()==-1){
			System.out.println("請誤選空白");
		}
		else if(chooseButton[0]==null && b.getChess().getState()==0 && b.getChess().getWeight()!=-1){
			System.out.println(whoPlay().toString()+":翻開了棋子");
			b.getChess().setState(1);
			changePlayer();
			setChanged();
			this.notifyObservers("翻棋");
		}else if( chooseButton[0]==null && !b.getChess().getPlayer().toString().equals(whoPlay().toString())){
			System.out.println("請選擇自己顏色的棋子");
		}
		else if(chooseButton[0]==null && b.getChess().getWeight()!=-1){
			chooseButton[0]=b;
			System.out.println(whoPlay().toString()+":選擇了棋子");
		}else if(chooseButton[0]!=null && chooseButton[1]==null){
			chooseButton[1]=b;
			if(rule.isMove(chooseButton[0].getChess(),chooseButton[1].getChess())==true){
				chooseButton[1].setChess(chooseButton[0].getChess());
				chooseButton[0].setChess( new Chess(picture4, -1, new Player("game")));
				System.out.println(whoPlay().toString()+":移動了棋子");
				changePlayer();
				chooseButton[0]=null;
				chooseButton[1]=null;
				setChanged();
				this.notifyObservers("移動");
			}else if(rule.isEat(chooseButton[0].getChess(),chooseButton[1].getChess())==true){
				chooseButton[1].getChess().setState(2);
				chooseButton[1].setChess(chooseButton[0].getChess());
				chooseButton[0].setChess( new Chess(picture4, -1, new Player("game")));
				System.out.println(whoPlay().toString()+":吃掉了棋子");
				if(rule.isVictory()==true){
					System.out.println(whoPlay().toString()+"win");
				}
				changePlayer();
				chooseButton[0]=null;
				chooseButton[1]=null;
				
				setChanged();
				this.notifyObservers("吃棋");
			}else{
				System.out.println(whoPlay().toString()+"選擇錯誤，再來一次");
				chooseButton[0]=null;
				chooseButton[1]=null;
			}
		}else{
			
			chooseButton[0]=null;
			chooseButton[1]=null;
		}
	}
	

	public Player whoPlay() {
		if (playerNumber == 1) {
			return firstPlayer;
		} else {
			return secondPlayer;
		}
	}

	public void changePlayer() {

		if (playerNumber == 1) {
			playerNumber = 2;
			System.out.println("換玩家" + secondPlayer.getName());
		} else {
			playerNumber = 1;
			System.out.println("換玩家" + firstPlayer.getName());
		}
	}

	public Chess[] getAllChess() {
		return allChess;
	}

	public Rule getRule() {
		return rule;
	}

}
