package chet;

public class UserUI {

	public static void main(String[] args) {
		
	
		
		ChetGame cg = new ChetGame();
		Game game = new Game(cg);
		ChessBoard chessBoard = ChessBoard.instance(game);
		
		

	}

}
