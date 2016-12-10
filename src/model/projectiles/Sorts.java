package model.projectiles;

import model.personnages.PlayerEnnemis;
import model.personnages.PlayerPrincipal;

public class Sorts extends Projectiles implements Runnable{
	private static final long waitTime = 100;
	private Thread thread;
	public static int puissance=60;

	public Sorts(int puissance, PlayerPrincipal called) {
		super(puissance, called);
		this.thread=new Thread(this);
		thread.start();
	}
	public Sorts(int puissance, PlayerEnnemis called) {
		super(puissance, called);
		this.thread=new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		try{
		while(this.alive){
			this.setGoodMovement();
			Thread.sleep(waitTime);
		}
	}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Bug du thread Projectile!");
		}
	}

}
