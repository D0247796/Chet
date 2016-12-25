package chet;

import java.util.Observable;

interface GameFactory {

	public Player[] creatPlayer();
	
	public Chess[] creatChess(Player[] p);

	public Rule creatRule(Chess[] allChess,Player[] p);
}

class ChetGame implements GameFactory {

	@Override
	public Chess[] creatChess(Player[] p) {
		Chess[] allChess = new Chess[32];// 新增棋子

		for (int i = 0; i < 5; i++) {
			allChess[i] = new Chess("卒", 0, p[0]);
		}
		for (int i = 5; i < 7; i++) {
			allChess[i] = new Chess("馬", 1, p[0]);
		}
		for (int i = 7; i < 9; i++) {
			allChess[i] = new Chess("包", 2, p[0]);
		}
		for (int i = 9; i < 11; i++) {
			allChess[i] = new Chess("車", 3, p[0]);
		}
		for (int i = 11; i < 13; i++) {
			allChess[i] = new Chess("象", 4, p[0]);
		}
		for (int i = 13; i < 15; i++) {
			allChess[i] = new Chess("士", 5,p[0]);
		}

		allChess[15] = new Chess("將", 6, p[0]);
		for (int i = 16; i < 21; i++) {
			allChess[i] = new Chess("兵", 0, p[1]);
		}
		for (int i = 21; i < 23; i++) {
			allChess[i] = new Chess("傌", 1, p[1]);
		}
		for (int i = 23; i < 25; i++) {
			allChess[i] = new Chess("炮", 2, p[1]);
		}
		for (int i = 25; i < 27; i++) {
			allChess[i] = new Chess("俥", 3, p[1]);
		}
		for (int i = 27; i < 29; i++) {
			allChess[i] = new Chess("相", 4, p[1]);
		}
		for (int i = 29; i < 31; i++) {
			allChess[i] = new Chess("仕", 5, p[1]);
		}

		allChess[31] = new Chess("帥", 6, p[1]);
		
		return allChess;

	}

	@Override
	public Player[] creatPlayer() {
		// TODO Auto-generated method stub
		Player[] p = new Player[2];
		p[0] = new Player("p1");
		p[1] = new Player("p2");
		return p;

	}

	@Override
	public Rule creatRule(Chess[] allChess,Player[] p) {
		Rule rule = Rule.instance(allChess, p[0], p[1]);
		return rule;
	}

}

public class Game {
	private Rule rule;
	private Chess[] allChess;
	private Player[] p;
	private Player p1, p2, firstPlayer, secondPlayer;
	int playerNumber = 1;

	public Game(ChetGame cg) {
		this.p=cg.creatPlayer();
		this.allChess = cg.creatChess(p);
		this.rule=cg.creatRule(allChess, p);
		this.p1 = p[0];
		this.p2 = p[1];
	}

	public void isPlayer(Player p) {
		if (p.getName().equals("p1")) {
			firstPlayer = p1;
			secondPlayer = p2;
		} else {
			firstPlayer = p2;
			secondPlayer = p1;
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

	public void change(Chess c, Coordinate coo) {
		rule.isMove(c, coo);
	}

	public Rule getRule() {
		return rule;
	}

}
