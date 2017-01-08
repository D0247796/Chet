package chet;

interface AbstractRule {
	public Boolean isMove();

	public Boolean isEat();

	public Boolean isVictory();
}
<<<<<<< HEAD:chet/src/chet/Rule.java
//�W�h
public class Rule  implements AbstractRule {
=======
//規則
public class Rule extends java.util.Observable implements AbstractRule {
>>>>>>> origin/master:src/chet/Rule.java
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

<<<<<<< HEAD:chet/src/chet/Rule.java
	public Boolean isMove(Chess a, Chess b) {
		if(b.getWeight()!=-1){
			return false;
			
		}else {//����
			int x = a.getCoordinate().getX() - b.getCoordinate().getX();
			int y = a.getCoordinate().getY() - b.getCoordinate().getY();
			if ((x <= 1 && x >= -1 && y <= 1 && y >= -1) && (x == 0 || y == 0)) {
				
				return true;

			} else {
				return false;
=======
	public void isMove(Chess c, Coordinate coo) {
		int eatCheck = 0;
		if (c.getCoordinate().toString().equals(coo.toString())) {//避免重複按在同一棋子
			System.out.println("請勿重複點，並重新來過");
			eatCheck = 1;
		} else {
			for (int i = 0; i < allChess.length; i++) {// 判斷移動位子上是否有棋，有的話則移到吃棋
				if (allChess[i].getCoordinate().toString().equals(coo.toString())) {
					isEat(c, allChess[i]);
					eatCheck = 1;
					break;
				}

			}
		}
		if (eatCheck == 0) {//移動
			int a = coo.getX() - c.getCoordinate().getX();
			int b = coo.getY() - c.getCoordinate().getY();
			if ((a <= 1 && a >= -1 && b <= 1 && b >= -1) && (a == 0 || b == 0)) {
				c.setCoordinate(coo);
				System.out.println("移動了"+c.getName());
				this.setChanged();
				this.notifyObservers();

			} else {
				System.out.println("超過移動範圍");
>>>>>>> origin/master:src/chet/Rule.java
			}
			
		}

	}

<<<<<<< HEAD:chet/src/chet/Rule.java
	public Boolean isEat(Chess a, Chess b) {//�Y
		
=======
	public void isEat(Chess a, Chess b) {//吃
>>>>>>> origin/master:src/chet/Rule.java
		int x = a.getCoordinate().getX() - b.getCoordinate().getX();
		int y = a.getCoordinate().getY() - b.getCoordinate().getY();
		if (a.getWeight() == 2 && !a.getPlayer().toString().equals(b.getPlayer().toString())) {//炮的吃法跟不能吃同隊
			if (x != 0 && y != 0) {
<<<<<<< HEAD:chet/src/chet/Rule.java
				return false;
=======
				System.out.println("違反規定，不能吃");
>>>>>>> origin/master:src/chet/Rule.java
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
<<<<<<< HEAD:chet/src/chet/Rule.java
				if (number == 1) {//������number�|�W�[�A�u���@�ӫh��Y
					return true;
				} else {
					System.out.println("�o�X���D");
					return false;
=======
				if (number == 1) {//有的話number會增加，只有一個則能吃
					a.setCoordinate(b.getCoordinate());
					b.setState(2);// 改為死亡
					b.setCoordinate(new Coordinate(1000, 1000));// 移出棋盤
					isVictory();
					this.setChanged();
					this.notifyObservers();
				} else {
					System.out.println("違反規定，不能吃");
>>>>>>> origin/master:src/chet/Rule.java
				}
			}
		} else if ((x == 0 || y == 0) && (x > -2 && x < 2 && y > -2 && y < 2)
				&& !a.getPlayer().toString().equals(b.getPlayer().toString())) {//其餘棋子吃法根不能吃同隊
			if (a.getWeight() >= b.getWeight()) {
<<<<<<< HEAD:chet/src/chet/Rule.java
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

	public Boolean isVictory() {// ��M�O�_�@�観�s���Ѥl
=======
				System.out.println(a.getName() + "吃了" + b.getName());
				a.setCoordinate(b.getCoordinate());
				b.setState(2);// 改為死亡
				b.setCoordinate(new Coordinate(1000, 1000));// 移出棋盤
				isVictory();
				this.setChanged();
				this.notifyObservers();
			} else if (a.getWeight() == 0 && b.getWeight() == 6) {
				System.out.println(a.getName() + "吃了" + b.getName());
				a.setCoordinate(b.getCoordinate());
				b.setState(2);// 改為死亡
				b.setCoordinate(new Coordinate(1000, 1000));// 移出棋盤
				isVictory();
				this.setChanged();
				this.notifyObservers();
			} else if (b.getWeight() == 2) {
				System.out.println(a.getName() + "吃了" + b.getName());
				a.setCoordinate(b.getCoordinate());
				b.setState(2);// 改為死亡
				b.setCoordinate(new Coordinate(1000, 1000));// 移出棋盤
				isVictory();
				this.setChanged();
				this.notifyObservers();
			} else {
				System.out.println("違反規定，不能吃");
			}
		} else {

			System.out.println("違反規定，不能吃");
		}
	}

	public void isVictory() {// 找尋是否一方有存活棋子
>>>>>>> origin/master:src/chet/Rule.java
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
<<<<<<< HEAD:chet/src/chet/Rule.java
		if (p1Chess == 0 || p2Chess == 0) {
			return true;
		
		} else {
			return false;
=======
		if (p1Chess == 0) {
			System.out.println("p2勝利");
		} else if (p2Chess == 0) {
			System.out.println("p1勝利");
		} else {
			System.out.println("還沒分出勝負，再努力點!");
>>>>>>> origin/master:src/chet/Rule.java
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
