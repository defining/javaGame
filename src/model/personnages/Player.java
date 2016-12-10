package model.personnages;
import java.util.ArrayList;

import model.interfaces.Deplacement;
import model.interfaces.Observer;
import model.interfaces.Subject;
import model.other.Game;
import model.other.ObjetGenericGame;

public abstract class Player extends ObjetGenericGame implements Deplacement, Subject{
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	private boolean detection=false;
	public int attack;
	protected int waitTime;
	public ArrayList<Integer> positions= new ArrayList<Integer>();
	public int level; // Niveau du joueur
	public int levelAttack;
	public int levelDefense;
	
	
	
	public Player(int X, int Y){
		super(X,Y);
		this.health=100;
		this.attack=10;
		this.positions.add(0);//X left
		this.positions.add(0);//Y Left
		this.positions.add(0);
		this.positions.add(0);
		this.positions.add(0);
		this.positions.add(0);
		this.positions.add(0);
		this.positions.add(0);
		this.attaquable=true; //à changer pr les objets indestructibles
		this.level=0;
		this.levelAttack=1;
		this.levelDefense=1;
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
		this.lastPosition='l';
		if (isCanMoveLeft()==0 && this.getPosX()-1!=0 ){
		this.move(-1, 0);
		}
		}
	public void movePlayerRight(){
		this.lastPosition='r';
		if (isCanMoveRight()==0 && this.getPosX()+1!=Game.size-1){
		this.move(1,0);
		}
		
	}
	public void movePlayerDown(){
		this.lastPosition='d';
		//Ne se déplace que si la case est libre
		if (isCanMoveDown()==0 && this.getPosY()+1!=Game.size-1){
		this.move(0, 1);
		}
		
	}
	public void movePlayerUp(){
		this.lastPosition='u';
		if (isCanMoveUp()==0 && this.getPosY()-1!=0){
		this.move(0, -1);
		}
		
	}

	public boolean isDetection() {
		return detection;
	}

	public void setDetection(boolean detection) {
		this.detection = detection;
	}
	
	/**
	 * En fonction de l'objet, il invoquera la bonne méthode ou n'attaquera pas l'objet si il n'est pas attaquable
	 * @param attacked c'est l'objet attaqué
	 */
	public void attack(ObjetGenericGame attacked){
		if(attacked!=null){
			if(attacked.attaquable){
				if (attacked instanceof PlayerPrincipal){
					PlayerPrincipal principal=(PlayerPrincipal) attacked;
					principal.hurt(this.attack-principal.levelDefense);
				}
				else{
			attacked.hurt(this.attack);//vérifier qu'il est mort d'abord!
				}
			}
			else{
				System.out.print("Pas attaquable!!");	
			}
		}
		else{
			System.out.print("Pas d'objets à attaquer");
		}
		notifyObservers();
	}
	
	
	
	
	/**
	 * Fonction qui pose les coordonnées de présence au tableau positions
	 */
	
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
