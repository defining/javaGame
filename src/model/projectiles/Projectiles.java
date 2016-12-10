package model.projectiles;

import java.util.ArrayList;

import model.interfaces.Deplacement;
import model.interfaces.Observer;
import model.interfaces.Subject;
import model.other.Game;
import model.other.ObjetGenericGame;
import model.personnages.PlayerEnnemis;
import model.personnages.PlayerPrincipal;

public abstract class Projectiles implements Deplacement,Subject{
	public ArrayList<Integer> positions= new ArrayList<Integer>();
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	private int posX;
	private int posY;
	public int PresenceUp= 0;
	public int PresenceDown= 0;
	public int PresenceRight=0;
	public int PresenceLeft=0;
	public int CoordonneePresenceUpX;
	public int CoordonneePresenceUpY;
	public int CoordonneePresenceDownX;
	public int CoordonneePresenceDownY;
	public int CoordonneePresenceLeftX;
	public int CoordonneePresenceLeftY;
	public int CoordonneePresenceRightX;
	public int CoordonneePresenceRightY;
	public char lastPosition;
	public boolean alive; 
    public static int puissance=10;
	public boolean attaquable;
	public PlayerPrincipal called;
	public PlayerEnnemis called2;
	public ObjetGenericGame objetToAttack;
	public Game game;
	
	

	public Projectiles(int puissance, PlayerPrincipal called) {
	if (called.lastPosition=='d'){
		this.posX=called.getPosX();
		this.posY=called.getPosY();
	}
	else if (called.lastPosition=='u'){
		this.posX=called.getPosX();
		this.posY=called.getPosY();
	}
	else if (called.lastPosition=='r'){
		this.posX=called.getPosX();
		this.posY=called.getPosY();
	}
	else{
		this.posX=called.getPosX();
		this.posY=called.getPosY();
	}
	this.lastPosition=called.lastPosition;
	this.alive=true;
	this.called=null;
	this.called=called;
	this.positions.add(0);//X left
	this.positions.add(0);//Y Left
	this.positions.add(0);
	this.positions.add(0);
	this.positions.add(0);
	this.positions.add(0);
	this.positions.add(0);
	this.positions.add(0);
		}
	public Projectiles(int puissance, PlayerEnnemis called) {
		if (called.lastPosition=='d'){
			this.posX=called.getPosX();
			this.posY=called.getPosY();
		}
		else if (called.lastPosition=='u'){
			this.posX=called.getPosX();
			this.posY=called.getPosY();
		}
		else if (called.lastPosition=='r'){
			this.posX=called.getPosX();
			this.posY=called.getPosY();
		}
		else{
			this.posX=called.getPosX();
			this.posY=called.getPosY();
		}
		this.lastPosition=called.lastPosition;
		this.alive=true;
		this.called2=null;
		this.called2=called;
		this.positions.add(0);//X left
		this.positions.add(0);//Y Left
		this.positions.add(0);
		this.positions.add(0);
		this.positions.add(0);
		this.positions.add(0);
		this.positions.add(0);
		this.positions.add(0);
			}
	
	public int getPosX(){
		return this.posX;
	}
	
	public int getPosY(){
		return this.posY;
	}
	
	// Toutes les méthodes qui permettent de lire si la case adjacente est libre ou pas
	
	public void setPosX(int posX){
		 this.posX = posX;
	}
	
	public void setPosY(int posY){
		 this.posY = posY;
	}
	
	public int isCanMoveUp() {
		return PresenceUp;
	}
	
	
	//En paramètre: le personage sur la case et ses coordonnées
	public void setCanMoveUp(int PresenceUp, int x, int y) {
		this.PresenceUp = PresenceUp; //c'est la ligne qui permet de ne pas traverser un objet
		this.CoordonneePresenceUpX= x;
		this.CoordonneePresenceUpY= y;
	}

	public int isCanMoveDown() {
		return PresenceDown;
	}

	public void setCanMoveDown(int PresenceDown, int x, int y) {
		this.PresenceDown = PresenceDown;
		this.CoordonneePresenceDownX= x;
		this.CoordonneePresenceDownY= y;
	}

	public int isCanMoveRight() {
		return PresenceRight;
	}

	public void setCanMoveRight(int PresenceRight, int x, int y) {
		this.PresenceRight = PresenceRight;
		this.CoordonneePresenceRightX= x;
		this.CoordonneePresenceRightY= y;
	}

	public int isCanMoveLeft() {
		return PresenceLeft;
	}

	public void setCanMoveLeft(int PresenceLeft, int x, int y) {
		this.PresenceLeft = PresenceLeft;
		this.CoordonneePresenceLeftX= x;
		this.CoordonneePresenceLeftY= y;
	}
	
	public void move(int X, int Y){
		this.posX = this.posX + X;
		this.posY = this.posY + Y;
		notifyObservers();
	}
	
	@Override
	public void attach(Observer o) {
		observers.add(o);
	}

	@Override
	public void notifyObservers() {
		for (Observer o : observers) {
			o.update();
		}
	}
	public void movePlayerLeft(){
		if (isCanMoveLeft()==0 || isCanMoveLeft()==5){
		this.move(-1, 0);
		}
		else{
			System.out.println("ici");
			this.alive=false;
			//ligne qui bug
			//PlayerEnnemis objet=game.getGoodObject2(this);//hurt(Sorts.puissance);
			
			//System.out.println("Voila l'objet" + objet);
			
			notifyObservers();
			System.out.println("Puis ici");
		}
		}
	public void movePlayerRight(){
		if (isCanMoveRight()==0|| isCanMoveRight()==5){
		this.move(1,0);
		}
		else{
			System.out.println("ici");
			
			//ligne qui bug
			//game.getGoodObject2(this).hurt(Sorts.puissance);
			this.alive=false;
			System.out.println("Puis ici 2");
			
			notifyObservers();
			System.out.println("Puis ici");
		}
	}
	public void movePlayerDown(){
		if (isCanMoveDown()==0){
		this.move(0, 1);
		}
		else{
			System.out.println("ici");
			this.alive=false;
			
			//ligne qui bug
			game.getGoodObject2(this).hurt(Sorts.puissance);
			System.out.println("Puis ici 2");
			
			notifyObservers();
			System.out.println("Puis ici");
		}
	}
	public void movePlayerUp(){
		if (isCanMoveUp()==0){
		this.move(0, -1);
		}
		else{
			System.out.println("ici");
			this.alive=false;
			notifyObservers();
		}
	}
	
	public synchronized void setGoodMovement(){
		if(this.lastPosition=='d'){
			this.movePlayerDown();
		}
		else if(this.lastPosition=='u'){
			this.movePlayerUp();
		}
		else if(this.lastPosition=='r'){
			this.movePlayerRight();
		}
		else if(this.lastPosition=='l'){
			this.movePlayerLeft();
		}
		
	}
	public void setAttackParameters(){
			this.positions.set(0,this.CoordonneePresenceLeftX);
			this.positions.set(1,this.CoordonneePresenceLeftY);
			this.positions.set(2,this.CoordonneePresenceUpX);
			this.positions.set(3,this.CoordonneePresenceUpY);
			this.positions.set(4,this.CoordonneePresenceDownX);
			this.positions.set(5,this.CoordonneePresenceDownY);
			this.positions.set(6,this.CoordonneePresenceRightX);
			this.positions.set(7,this.CoordonneePresenceRightY);
		
	}

}
