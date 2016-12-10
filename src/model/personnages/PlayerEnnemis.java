package model.personnages;

import model.other.Game;

public class PlayerEnnemis extends Player implements Runnable {
	private boolean detection;
	private Thread thread;
	public Game game;

	public PlayerEnnemis(int X, int Y, Game game) {
		super(X, Y);
		this.game=game;
		this.detection=false;
		this.waitTime=1000;
		this.experienceToWin=50;
		this.thread = new Thread(this);
		this.thread.start();
		
		
		
	}
    public void patrouille(){
    	try{
    	//while(!detection){
    	this.movePlayerUp();
    	Thread.sleep(waitTime);
    	this.movePlayerDown();
    	Thread.sleep(waitTime);
    	this.movePlayerDown();
    	Thread.sleep(waitTime);
    	this.movePlayerUp();
    	Thread.sleep(waitTime);
    	this.movePlayerRight();
    	Thread.sleep(waitTime);
    	this.movePlayerLeft();
    	Thread.sleep(waitTime);
    	this.movePlayerLeft();
    	Thread.sleep(waitTime);
    	this.movePlayerRight();
    	//}
    
    	}
    	catch (Exception e){
    		e.printStackTrace();
    		System.out.println("Bug du thread Ennemis!");
    	}
    	
	}
    
    public synchronized void attack(){
    	System.out.println("Boule de feu lancée");
    	try {
		Thread.sleep(2*waitTime);
		game.indirectAttack(this);
		Thread.sleep(2*waitTime);
		System.out.println("Boule de feu arrivé!");
    	} catch (InterruptedException e) {
    		System.out.println("Bug du lanceur de boules");
			e.printStackTrace();
		}
    }
	@Override
	public void run() {
			while(true){
				while (detection){
					this.patrouille();
					this.attack();
		    	}
				while(!detection){
				this.patrouille();	
				}
	}
			
	}

}