package chet;

//�W�h
public class Rule extends java.util.Observable {
	private Chess[] allChess;
	private Player p1, p2;

	public Rule(Chess[] allChess, Player p1, Player p2) {
		this.allChess = allChess;
		this.p1 = p1;
		this.p2 = p2;
	}

	public void isMove(Chess c, Coordinate coo) {
		int eatCheck = 0;// �P�_���ʦ�l�W�O�_���ѡA�����ܫh����Y��
		for (int i = 0; i < allChess.length; i++) {
			if (allChess[i].getCoordinate().toString().equals(coo.toString())) {
				System.out.println("���ʨ�Y");
				isEat(c, allChess[i]);
				eatCheck = 1;
				break;
			}

		}
		if (eatCheck == 0) {
			int a = coo.getX() - c.getCoordinate().getX();
			int b = coo.getY() - c.getCoordinate().getY();
			if ((a <= 1 && a >= -1 && b <= 1 && b >= -1) && (a==0||b==0)) {
				c.setCoordinate(coo);
				this.setChanged();
				this.notifyObservers();
				
			} else {
				System.out.println("�W�L���ʽd��");
			}
		}

	}

	public void isEat(Chess a, Chess b) {
		int x = a.getCoordinate().getX() - b.getCoordinate().getX();
		int y = a.getCoordinate().getY() - b.getCoordinate().getY();
		System.out.println(a.getPlayer().toString()+b.getPlayer().toString());
		if (a.getWeight() == 2 && !a.getPlayer().toString().equals(b.getPlayer().toString())) {
			if (x != 0 && y != 0) {
				System.out.println("�H�ϳW�w�A����Y");
			} else {
				int number = 0;
				if (x == 0 && y > 0) {
					// �M����褤�O�_���Ѥl
					for (int i = a.getCoordinate().getY() - 1; i > b.getCoordinate().getY(); i--) {
						Coordinate coo = new Coordinate(a.getCoordinate().getX(), i);
						for (int j = 0; j < allChess.length; j++) {
							if (allChess[j].getCoordinate().toString().equals(coo.toString())) {
								number++;
							}
						}
					}
				} else if (x == 0 && y < 0) {
					for (int i = a.getCoordinate().getY() + 1; i < b.getCoordinate().getY(); i++) {
						Coordinate coo = new Coordinate(a.getCoordinate().getX(), i);
						for (int j = 0; j < allChess.length; j++) {
							if (allChess[j].getCoordinate().toString().equals(coo.toString())) {
								number++;
							}
						}
					}
				} else if (y == 0 && x > 0) {
					for (int i = a.getCoordinate().getX() - 1; i > b.getCoordinate().getX(); i--) {
						Coordinate coo = new Coordinate(i, a.getCoordinate().getY());
						for (int j = 0; j < allChess.length; j++) {
							if (allChess[j].getCoordinate().toString().equals(coo.toString())) {
								number++;
							}
						}
					}
				} else if (y == 0 && x < 0) {
					for (int i = a.getCoordinate().getX() + 1; i < b.getCoordinate().getX(); i++) {
						Coordinate coo = new Coordinate(i, a.getCoordinate().getY());
						for (int j = 0; j < allChess.length; j++) {
							if (allChess[j].getCoordinate().toString().equals(coo.toString())) {
								number++;
							}
						}
					}

				}
				System.out.println(number);
				if (number == 1) {
					a.setCoordinate(b.getCoordinate());
					b.setState(2);// �אּ���`
					b.setCoordinate(new Coordinate(1000, 1000));// ���X�ѽL
					isVictory();
					this.setChanged();
					this.notifyObservers();
				} else {
					System.out.println("�H�ϳW�w�A����Y");
				}
			}
		} else if ((x == 0 || y == 0) && (x > -2 && x < 2 && y > -2 && y < 2) && !a.getPlayer().toString().equals(b.getPlayer().toString())) {
			if (a.getWeight() >= b.getWeight()) {
				System.out.println(a.getName()+"�Y�F"+b.getName());
				a.setCoordinate(b.getCoordinate());
				b.setState(2);// �אּ���`
				b.setCoordinate(new Coordinate(1000, 1000));// ���X�ѽL
				isVictory();
				this.setChanged();
				this.notifyObservers();
			} else if (a.getWeight() == 0 && b.getWeight() == 6) {
				System.out.println(a.getName()+"�Y�F"+b.getName());
				a.setCoordinate(b.getCoordinate());
				b.setState(2);// �אּ���`
				b.setCoordinate(new Coordinate(1000, 1000));// ���X�ѽL
				isVictory();
				this.setChanged();
				this.notifyObservers();
			} else if ( b.getWeight() == 2) {
				System.out.println(a.getName()+"�Y�F"+b.getName());
				a.setCoordinate(b.getCoordinate());
				b.setState(2);// �אּ���`
				b.setCoordinate(new Coordinate(1000, 1000));// ���X�ѽL
				isVictory();
				this.setChanged();
				this.notifyObservers();
			}else {
				System.out.println("�H�ϳW�w�A����Y");
			}
		} else {

			System.out.println("�H�ϳW�w�A����Y");
		}
	}

	public void isVictory() {// ��M�O�_�@�観�s���Ѥl
		int p1Chess = 0;
		int p2Chess = 0;
		for (int i = 0; i < allChess.length; i++) {
			if (allChess[i].getPlayer().toString().equals(p1.getName()) && allChess[i].getState() != 2) {
				p1Chess++;
			}
			if (allChess[i].getPlayer().toString().equals(p2.getName()) && allChess[i].getState() != 2) {
				p2Chess++;
			}
		}
		if (p1Chess == 0) {
			System.out.println("p2�ӧQ");
		}else  if (p2Chess == 0) {
			System.out.println("p1�ӧQ");
		} else {
			System.out.println("�٨S���X�ӭt�A�A�V�O�I!");
		}

	}

}
