package chet;

public class Rule {
	Chess[] allChess;
	Player p1, p2;

	public Rule(Chess[] allChess, Player p1, Player p2) {
		this.allChess = allChess;
		this.p1 = p1;
		this.p2 = p2;
	}

	public void isMove(Chess c, Coordinate coo) {
		int eatCheck = 0;// �P�_�O�_�O�Y��
		for (int i = 0; i < allChess.length; i++) {
			if (allChess[i].getCoordinate().toString().equals(coo.toString())) {
				isEat(c, allChess[i]);
				eatCheck = 1;
			}

		}
		if (eatCheck == 0) {
			int a = coo.getX() - c.getCoordinate().getX();
			int b = coo.getY() - c.getCoordinate().getY();
			if (a <= 1 && a >= -1 && b <= 1 && b >= -1) {
				c.setCoordinate(coo);
			} else {
				System.out.println("�W�L���ʽd��");
			}
		}

	}

	public void isEat(Chess a, Chess b) {
		if (a.getWeight() == 2) {
			int x = a.getCoordinate().getX() - b.getCoordinate().getX();
			int y = a.getCoordinate().getY() - b.getCoordinate().getY();
			if (x != 0 && y != 0) {
				System.out.println("����Y");
			} else {
				int number = 0;
				if (x == 0 && y > 0) {
					for (int i = a.getCoordinate().getY() - 1; i > b.getCoordinate().getY(); i--) {
						Coordinate coo = new Coordinate(a.getCoordinate().getX(), i);
						for (int j = 0; j < allChess.length; j++) {
							if (allChess[i].getCoordinate().toString().equals(coo.toString())) {
								number++;
							}
						}
					}
				} else if (x == 0 && y < 0) {
					for (int i = a.getCoordinate().getY() + 1; i < b.getCoordinate().getY(); i++) {
						Coordinate coo = new Coordinate(a.getCoordinate().getX(), i);
						for (int j = 0; j < allChess.length; j++) {
							if (allChess[i].getCoordinate().toString().equals(coo.toString())) {
								number++;
							}
						}
					}
				} else if (y == 0 && x > 0) {
					for (int i = a.getCoordinate().getX() - 1; i > b.getCoordinate().getX(); i--) {
						Coordinate coo = new Coordinate(i, a.getCoordinate().getY());
						for (int j = 0; j < allChess.length; j++) {
							if (allChess[i].getCoordinate().toString().equals(coo.toString())) {
								number++;
							}
						}
					}
				} else if (y == 0 && x < 0) {
					for (int i = a.getCoordinate().getX() + 1; i > b.getCoordinate().getX(); i++) {
						Coordinate coo = new Coordinate(i, a.getCoordinate().getY());
						for (int j = 0; j < allChess.length; j++) {
							if (allChess[i].getCoordinate().toString().equals(coo.toString())) {
								number++;
							}
						}
					}

				}
				if (number == 1) {
					a.setCoordinate(b.getCoordinate());
					b.setState(3);
					b.setCoordinate(new Coordinate(1000, 1000));
					isVictory();
				}
			}
		} else if (a.getWeight() >= b.getWeight()) {
			a.setCoordinate(b.getCoordinate());
			b.setState(3);
			b.setCoordinate(new Coordinate(1000, 1000));
		} else if (a.getWeight() == 0 && b.getWeight() == 6) {
			a.setCoordinate(b.getCoordinate());
			b.setState(3);
			b.setCoordinate(new Coordinate(1000, 1000));
		} else {
			System.out.println("����Y");
		}
	}

	public void isVictory() {
		int p1Chess = 0;
		int p2Chess = 0;
		for (int i = 0; i < allChess.length; i++) {
			if (allChess[i].getPlayer().toString().equals(p1)) {
				p1Chess++;
			}
			if (allChess[i].getPlayer().toString().equals(p2)) {
				p2Chess++;
			}
		}
		if (p1Chess == 0) {
			System.out.println("p2�ӧQ");
		}
		if (p2Chess == 0) {
			System.out.println("p1�ӧQ");
		}

	}

}
