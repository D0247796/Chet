package chet;


public class Chess {
	String name;
	Player player;
	int weight;
	int state=0;
	Coordinate c;

	public Chess(String name,int weight, Player player) {
		this.name=name;
		this.player=player;
		this.weight=weight;
	}
	
	
	public void setName(String name){
		this.name=name;
	}
	
	public void setPlayer(Player p){
		this.player=p;
	}
	
	public void setweight(int w){
		this.weight=w;
	}
	
	
	public void setState(int s){//ª¬ºA 0:¥¼Â½ 1:¤wÂ½ 2:¦º¤`
		this.state=s;
	}

	public void setCoordinate(Coordinate c){
		this.c=c;
		
	}
	
	public String getName() {
		return name;
	}
	public Player getPlayer() {
		return player;
	}

	public int getWeight(){
		return weight;
	}
	
	public int getState(){
		return state;
	}

	public Coordinate getCoordinate(){
		return c;
	}


}

class Chess1 extends Chess{

	public Chess1(String name, int weight, Player player) {
		super(name, weight, player);
		
	}

	
	
}