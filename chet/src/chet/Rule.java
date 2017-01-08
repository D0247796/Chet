package chet;

interface AbstractRule {
	public Boolean isMove();

	public Boolean isEat();

	public Boolean isVictory();
}
//規則
public class Rule  implements AbstractRule {
	private Chess[] allChess;
	private Player p1, p2;
	private static Rule singleRule=null;//singleton
	
	static public Rule instance(Chess[] allChess, Player p1, Player p2){
		if(singleRule==null){
			singleRule=new Rule(allChess,p1,p2);
		}
		return singleRule;
	}

	private Rule(Chess[] allChess, Player p1, Player p2) {
		this.allChess = allChess;
		this.p1 = p1;
		this.p2 = p2;
	}

	public Boolean isMove(Chess a, Chess b) {
		if(b.getWeight()!=-1){
			return false;
			
		}else {//移動
			int x = a.getCoordinate().getX() - b.getCoordinate().getX();
			int y = a.getCoordinate().getY() - b.getCoordinate().getY();
			if ((x <= 1 && x >= -1 && y <= 1 && y >= -1) && (x == 0 || y == 0)) {
				
				return true;

			} else {
				return false;
			}
			
		}

	}

	public Boolean isEat(Chess a, Chess b) {//吃
		
		int x = a.getCoordinate().getX() - b.getCoordinate().getX();
		int y = a.getCoordinate().getY() - b.getCoordinate().getY();
		if (a.getWeight() == 2 && !a.getPlayer().toString().equals(b.getPlayer().toString())) {//炮的吃法跟不能吃同隊
			if (x != 0 && y != 0) {
				return false;
			} else {
				int number = 0;
				// 尋找路鏡中是否有棋子
				if (x == 0 && y > 0) {
					for (int i = a.getCoordinate().getY() - 1; i > b.getCoordinate().getY(); i--) {
						Coordinate coo = new Coordinate(a.getCoordinate().getX(), i);
						for (int j = 0; j < allChess.length; j++) {
							if (allChess[j].getCoordinate().toString().equals(coo.toString())&&allChess[j].getState()!=2) {
								number++;
							}
						}
					}
				} else if (x == 0 && y < 0) {
					for (int i = a.getCoordinate().getY() + 1; i < b.getCoordinate().getY(); i++) {
						Coordinate coo = new Coordinate(a.getCoordinate().getX(), i);
						for (int j = 0; j < allChess.length; j++) {
							if (allChess[j].getCoordinate().toString().equals(coo.toString())&&allChess[j].getState()!=2) {
								number++;
							}
						}
					}
				} else if (y == 0 && x > 0) {
					for (int i = a.getCoordinate().getX() - 1; i > b.getCoordinate().getX(); i--) {
						Coordinate coo = new Coordinate(i, a.getCoordinate().getY());
						for (int j = 0; j < allChess.length; j++) {
							if (allChess[j].getCoordinate().toString().equals(coo.toString())&&allChess[j].getState()!=2) {
								number++;
							}
						}
					}
				} else if (y == 0 && x < 0) {
					for (int i = a.getCoordinate().getX() + 1; i < b.getCoordinate().getX(); i++) {
						Coordinate coo = new Coordinate(i, a.getCoordinate().getY());
						for (int j = 0; j < allChess.length; j++) {
							if (allChess[j].getCoordinate().toString().equals(coo.toString())&&allChess[j].getState()!=2) {
								number++;
							}
						}
					}

				}
				if (number == 1) {//有的話number會增加，只有一個則能吃
					return true;
				} else {
					System.out.println("這出問題");
					return false;
				}
			}
		} else if ((x == 0 || y == 0) && (x > -2 && x < 2 && y > -2 && y < 2)
				&& !a.getPlayer().toString().equals(b.getPlayer().toString())) {//其餘棋子吃法根不能吃同隊
			if (a.getWeight() >= b.getWeight()) {
				return true;
			} else if (a.getWeight() == 0 && b.getWeight() == 6) {
				return true;
			} else if (b.getWeight() == 2) {
				
				return true;
			} else {
				return false;
			}
		} else {

			return false;
		}
	}

	public Boolean isVictory() {// 找尋是否一方有存活棋子
		int p1Chess = 0;
		int p2Chess = 0;
		for (int i = 0; i < allChess.length-1; i++) {
			if (allChess[i].getPlayer().toString().equals(p1.getName()) && allChess[i].getState() != 2) {
				p1Chess++;
			}
			if (allChess[i].getPlayer().toString().equals(p2.getName()) && allChess[i].getState() != 2) {
				p2Chess++;
			}
		}
		if (p1Chess == 0 || p2Chess == 0) {
			return true;
		
		} else {
			return false;
		}

	}

	@Override
	public Boolean isMove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isEat() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
