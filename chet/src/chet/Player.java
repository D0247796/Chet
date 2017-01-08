package chet;

interface AbstractPlayer {
	public void setName(String name);

	public String getName();
}

public class Player implements AbstractPlayer {
	private String name;

	public Player(String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return name;
	}

}