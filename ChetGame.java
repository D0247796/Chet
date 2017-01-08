package chet;

import java.io.File;

interface GameFactory {

	public Player[] creatPlayer();
	
	public Chess[] creatChess(Player[] p);

	public Rule creatRule(Chess[] allChess,Player[] p);
	
	public ChessBoard creatChessBoard(Game g);
	
	
	
}

class ChetGame implements GameFactory {

	@Override
	public Chess[] creatChess(Player[] p) {
		Chess[] allChess = new Chess[32];// 新增棋子

		for (int i = 0; i < 5; i++) {
			File picture = new File("D:/編輯程式/eclipse/workspace/chet/src/chet/picture/卒.jpg");
			allChess[i] = new Chess(picture, 0, p[0]);
		}
		for (int i = 5; i < 7; i++) {
			File picture = new File("D:/編輯程式/eclipse/workspace/chet/src/chet/picture/馬.jpg");
			allChess[i] = new Chess(picture, 1, p[0]);
		}
		for (int i = 7; i < 9; i++) {
			File picture = new File("D:/編輯程式/eclipse/workspace/chet/src/chet/picture/包.jpg");
			allChess[i] = new Chess(picture, 2, p[0]);
		}
		for (int i = 9; i < 11; i++) {
			File picture = new File("D:/編輯程式/eclipse/workspace/chet/src/chet/picture/車.jpg");
			allChess[i] = new Chess(picture, 3, p[0]);
		}
		for (int i = 11; i < 13; i++) {
			File picture = new File("D:/編輯程式/eclipse/workspace/chet/src/chet/picture/象.jpg");
			allChess[i] = new Chess(picture, 4, p[0]);
		}
		for (int i = 13; i < 15; i++) {
			File picture = new File("D:/編輯程式/eclipse/workspace/chet/src/chet/picture/士.jpg");
			allChess[i] = new Chess(picture, 5,p[0]);
		}

		File picture2 = new File("D:/編輯程式/eclipse/workspace/chet/src/chet/picture/將.jpg");
		allChess[15] = new Chess(picture2, 6, p[0]);
		for (int i = 16; i < 21; i++) {
			File picture = new File("D:/編輯程式/eclipse/workspace/chet/src/chet/picture/兵.jpg");
			allChess[i] = new Chess(picture, 0, p[1]);
		}
		for (int i = 21; i < 23; i++) {
			File picture = new File("D:/編輯程式/eclipse/workspace/chet/src/chet/picture/傌.jpg");
			allChess[i] = new Chess(picture, 1, p[1]);
		}
		for (int i = 23; i < 25; i++) {
			File picture = new File("D:/編輯程式/eclipse/workspace/chet/src/chet/picture/炮.jpg");
			allChess[i] = new Chess(picture, 2, p[1]);
		}
		for (int i = 25; i < 27; i++) {
			File picture = new File("D:/編輯程式/eclipse/workspace/chet/src/chet/picture/俥.jpg");
			allChess[i] = new Chess(picture, 3, p[1]);
		}
		for (int i = 27; i < 29; i++) {
			File picture = new File("D:/編輯程式/eclipse/workspace/chet/src/chet/picture/相.jpg");
			allChess[i] = new Chess(picture, 4, p[1]);
		}
		for (int i = 29; i < 31; i++) {
			File picture = new File("D:/編輯程式/eclipse/workspace/chet/src/chet/picture/仕.jpg");
			allChess[i] = new Chess(picture, 5, p[1]);
		}
		File picture3 = new File("D:/編輯程式/eclipse/workspace/chet/src/chet/picture/帥.jpg");
		allChess[31] = new Chess(picture3, 6, p[1]);
		
		return allChess;

	}

	@Override
	public Player[] creatPlayer() {
		// TODO Auto-generated method stub
		Player[] p = new Player[2];
		p[0] = new Player("玩家-黑色");
		p[1] = new Player("玩家-紅色");
		return p;

	}

	@Override
	public Rule creatRule(Chess[] allChess,Player[] p) {
		Rule rule = Rule.instance(allChess, p[0], p[1]);
		return rule;
	}

	@Override
	public ChessBoard creatChessBoard(Game g) {
		// TODO Auto-generated method stub
		ChessBoard chessBoard = ChessBoard.instance(g);
		return null;
	}

}