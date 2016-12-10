package model.personnages;

import java.util.ArrayList;

import model.interfaces.InteractionObjets;
import model.objets.ArmesAttaque;
import model.objets.ArmesDefense;
import model.objets.Objets;
import model.objets.Potions;
import model.other.Game;
import model.other.ObjetGenericGame;

public class PlayerPrincipal extends Player implements Runnable,InteractionObjets{
	private int experience; //points avant le prochain niveau
	public int armureAttack;
	public int armureDefense;
	public int maxHealth;
	public int[][] zoneSuicide;
	public int[] inventaire;
	public int type;

	public PlayerPrincipal(int X, int Y) {
		super(X, Y);
		this.armureAttack=0;
		this.armureDefense=0;
		this.maxHealth=this.health;
		this.inventaire=new int[]{0,0,0,0,0}; //inventaire à 5 entrées
		this.type=2;
	}
	
	public void movePlayerLeft(){
		this.lastPosition='l';
		if (isCanMoveLeft()==0 && this.getPosX()-1!=0 ){
		this.move(-1, 0);
		}
		else if (isCanMoveLeft()==6 || isCanMoveLeft()==7 || isCanMoveLeft()==8){
			this.move(-1, 0);
		}
		}
	public void movePlayerRight(){
		this.lastPosition='r';
		if (isCanMoveRight()==0 && this.getPosX()+1!=Game.size-1){
		this.move(1,0);
		}
		else if (isCanMoveRight()==6 || isCanMoveRight()==7 || isCanMoveRight()==8){
			this.move(1, 0);
		}
	}
	public void movePlayerDown(){
		this.lastPosition='d';
		if (isCanMoveDown()==0 && this.getPosY()+1!=Game.size-1){
		this.move(0, 1);
		}
		else if (isCanMoveDown()==6 || isCanMoveDown()==7 || isCanMoveDown()==8){
			this.move(0, 1);
		}
	}
	public void movePlayerUp(){
		this.lastPosition='u';
		if (isCanMoveUp()==0 && this.getPosY()-1!=0){
		this.move(0, -1);
		}
		else if (isCanMoveUp()==6 || isCanMoveUp()==7 || isCanMoveUp()==8){
			this.move(0, -1);
		}
	}

	@Override
	public void run() {
		
	}
	
	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		if(this.experience+experience>=100){
			this.levelUp();
		}
		else{
			this.experience += experience;
		}
		
	}
	
	public void levelUp(){
		this.experience=0;
		this.level+=1;
		this.levelAttack+=1;
		this.levelDefense+=1;
		this.maxHealth+=10;
		this.health+=10;
		System.out.println("Level up!");
		System.out.println("Ton niveau actuel est " + this.level);
	}
	
	public void attack(ObjetGenericGame attacked){
		if(attacked!=null){
			if(attacked.attaquable){
				if (attacked instanceof PlayerEnnemis){
					PlayerEnnemis ennemis=(PlayerEnnemis) attacked;
					ennemis.hurt((this.attack*(this.levelAttack+this.armureAttack))-ennemis.levelDefense);
				}
				else{
			attacked.hurt(this.attack*(this.levelAttack+this.armureAttack));//vérifier qu'il est mort d'abord!
				}
			if(!attacked.alive){
				this.setExperience(attacked.experienceToWin);
				System.out.print("Tu a gagné de l'experience car il est mort!");
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
	
public void setHealth(int valeur){
		
		if (this.health-valeur<=0){
			this.health=0;
			this.alive=false;
			System.out.println("Dead!");
		}
		
		//POUR LES POTIONS par exemple
		else if (this.health-valeur>=this.maxHealth){
			this.health=this.maxHealth;
			System.out.println("Vie au maximum!!");
		}
		else{
			this.health-=valeur;
			}
		
	}
/* fonction suicide pas implémenté
//Met 1 comme valeur à toutes les cases autour du joeur pour l'attaque suicide
public void setRectangle(int X, int Y){
	/*this.zoneSuicide[X-1][Y-1]=7; // en haut à gauche
	this.zoneSuicide[X][Y-1]=7;// en haut
	this.zoneSuicide[X+1][Y-1]=7;
	this.zoneSuicide[X-1][Y]=7;
	this.zoneSuicide[X+1][Y]=7;
	this.zoneSuicide[X-1][Y+1]=7;
	this.zoneSuicide[X][Y+1]=7;
	this.zoneSuicide[X+1][Y+1]=7;
	this.zoneSuicide[X-1][Y-1]=7; // en haut à gauche
	this.zoneSuicide[X][Y-1]=7;// en haut
	this.zoneSuicide[X+1][Y-1]=7;
	this.zoneSuicide[X-1][Y]=7;
	this.zoneSuicide[X+1][Y]=7;
	this.zoneSuicide[X-1][Y+1]=7;
	this.zoneSuicide[X][Y+1]=7;
	this.zoneSuicide[X+1][Y+1]=7;
}

public void suicide(Game game) {
	int X=this.getPosX();
	int Y= this.getPosY();
	this.setRectangle(X, Y);
	//game.getGoodObject()	
	
}
*/
public void useObjets(int x){
	if (x==6){
		this.setHealth(-Potions.additionnalHealth);
	}
	//Si c'est une arme
	else if(x==7){
		this.armureAttack+=ArmesAttaque.additionnalAttak;
	}
	else if(x==8){
		this.armureDefense+=ArmesDefense.additionnalDefense;
	}
	else{
		System.out.print("Y a pas d'objets dans cet emplacement!");
	}
}

@Override
public void InteractionWithObjets(ArrayList<? extends Objets> list) {
	int compteur=0;
	for (int j=list.size()-1;j>=0;j--){
		if (list.get(j).getPosX() == this.getPosX() && list.get(j).getPosY() == this.getPosY() ){
			for(int i=0;i<=4;i++){
				if (inventaire[i]==0){
					if(compteur==0){
					inventaire[i]=list.get(j).type;
					compteur+=1;
					}
				}
				else{
					System.out.println("Plus de place dans l'inventaire");
				}
		}
	
	}
	}
	
}
	
}
	
