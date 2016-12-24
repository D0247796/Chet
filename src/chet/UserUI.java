package chet;

public class UserUI {

	public static void main(String[] args) {
		
	
		Player p1 = new Player("p1");
		Player p2 = new Player("p2");
		Chess[] allChess = new Chess[32];
		
		for(int i=0;i<5;i++){
			allChess[i]=new Chess("卒",0,p1);
		}
		for(int i=5;i<7;i++){
			allChess[i]=new Chess("馬",1,p1);
		}
		for(int i=7;i<9;i++){
			allChess[i]=new Chess("包",2,p1);
		}
		for(int i=9;i<11;i++){
			allChess[i]=new Chess("車",3,p1);
		}
		for(int i=11;i<13;i++){
			allChess[i]=new Chess("象",4,p1);
		}
		for(int i=13;i<15;i++){
			allChess[i]=new Chess("士",5,p1);
		}
		
		allChess[15]=new Chess("將",6,p1);
		for(int i=0;i<5;i++){
			allChess[i]=new Chess("兵",0,p1);
		}
		for(int i=5;i<7;i++){
			allChess[i]=new Chess("傌",1,p1);
		}
		for(int i=7;i<9;i++){
			allChess[i]=new Chess("炮",2,p1);
		}
		for(int i=9;i<11;i++){
			allChess[i]=new Chess("俥",3,p1);
		}
		for(int i=11;i<13;i++){
			allChess[i]=new Chess("相",4,p1);
		}
		for(int i=13;i<15;i++){
			allChess[i]=new Chess("仕",5,p1);
		}
		
		allChess[15]=new Chess("帥",6,p1);
		
		Game game = new Game(allChess,p1,p2);
		
		for (int i = 0; i < allChess.length; i++) {
			allChess[i].addObserver(game);
		}

	}

}
