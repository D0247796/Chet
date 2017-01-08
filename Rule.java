package chet;

interface AbstractRule {
	public Boolean isMove();

	public Boolean isEat();

	public Boolean isVictory();
}
//�W�h
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
			
		}else {//����
			int x = a.getCoordinate().getX() - b.getCoordinate().getX();
			int y = a.getCoordinate().getY() - b.getCoordinate().getY();
			if ((x <= 1 && x >= -1 && y <= 1 && y >= -1) && (x == 0 || y == 0)) {
				
				return true;

			} else {
				return false;
			}
			
		}

	}

	public Boolean isEat(Chess a, Chess b) {//�Y
		
		int x = a.getCoordinate().getX() - b.getCoordinate().getX();
		int y = a.getCoordinate().getY() - b.getCoordinate().getY();
		if (a.getWeight() == 2 && !a.getPlayer().toString().equals(b.getPlayer().toString())) {//�����Y�k�򤣯�Y�P��
			if (x != 0 && y != 0) {
				return false;
			} else {
				int number = 0;
				// �M����褤�O�_���Ѥl
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
				if (number == 1) {//������number�|�W�[�A�u���@�ӫh��Y
					return true;
				} else {
					System.out.println("�o�X���D");
					return false;
				}
			}
		} else if ((x == 0 || y == 0) && (x > -2 && x < 2 && y > -2 && y < 2)
				&& !a.getPlayer().toString().equals(b.getPlayer().toString())) {//��l�Ѥl�Y�k�ڤ���Y�P��
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

	public Boolean isVictory() {// ��M�O�_�@�観�s���Ѥl
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
