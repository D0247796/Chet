package chet;

interface AbstractChet {
	public void setName(String name);

	public void setPlayer(Player p);

	public void setWeight(int w);

	public void setState(int s);

	public void setCoordinate(Coordinate c);

	public String getName();

	public Player getPlayer();

	public int getState();

	public int getWeight();

	public Coordinate getCoordinate();
}

public class Chess implements AbstractChet {
	private String name;
	private Player player;
	private int weight;
	private int state = 0;
	private Coordinate c;

	public Chess(String name, int weight, Player player) {
		this.name = name;
		this.player = player;
		this.weight = weight;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPlayer(Player p) {
		this.player = p;
	}

	public void setWeight(int w) {
		this.weight = w;
	}

	public void setState(int s) {// 狀態 0:未翻 1:已翻 2:死亡
		this.state = s;
	}

	public void setCoordinate(Coordinate c) {
		this.c = c;

	}

	public String getName() {
		return name;
	}

	public Player getPlayer() {
		return player;
	}

	public int getWeight() {
		return weight;
	}

	public int getState() {
		return state;
	}

	public Coordinate getCoordinate() {
		return c;
	}

}

