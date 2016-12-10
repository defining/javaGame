package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import model.other.Game;

public class Keyboard implements KeyListener{
	private Game game;
	public Keyboard(Game game){
		this.game = game;
	}

	@Override
	public void keyPressed(KeyEvent event) {
		int key = event.getKeyCode();
		
		switch (key){
		/**
		 * Chaque touche fait reference a un mouvement du player qui le fera bouger SI c'est possible.
		 */
			case KeyEvent.VK_RIGHT: 
				System.out.println("Right");
				game.getPlayers().get(0).movePlayerRight();
				break;
			case KeyEvent.VK_LEFT:
				System.out.println("Left");
				game.getPlayers().get(0).movePlayerLeft();
				break;
			case KeyEvent.VK_DOWN:
				System.out.println("Down");
				game.getPlayers().get(0).movePlayerDown();
				break;
			case KeyEvent.VK_UP:
				System.out.println("Up");
				game.getPlayers().get(0).movePlayerUp();
				break;	
				/**
				 * L'attaque indirecte n'est disponible que si le personnage principal est niveau >=2
				 */
			case KeyEvent.VK_A: 
				System.out.println("Boule de feu!");
				game.indirectAttack();
				break;
				/**
				 * L'attaque directe est, elle, disponible des le depart. C'est la méthode - getGoodObject - qui etablit la cible (case adjacente).
				 */
			case KeyEvent.VK_SPACE:
				game.getPlayers().get(0).attack(game.getGoodObject(game.getPlayers(),0));
				System.out.println("Attaque lancée!");
				break;
			/*case KeyEvent.VK_ENTER:
				game.getPlayers().get(0).suicide(game);
				System.out.println("Attaque suicide!");
				break;*/
			//Toutes les fonctions d'utilisation de l'inventaire
			case KeyEvent.VK_Z:
				game.getPlayers().get(0).useObjets(game.getPlayers().get(0).inventaire[0]);
				game.getPlayers().get(0).inventaire[0]=0;
				System.out.println("Emplacement 1: utilisé! ");
				break;
			case KeyEvent.VK_E:
				game.getPlayers().get(0).useObjets(game.getPlayers().get(0).inventaire[1]);
				game.getPlayers().get(0).inventaire[1]=0;
				System.out.println("Emplacement 2: utilisé!");
				break;
			case KeyEvent.VK_R:
				game.getPlayers().get(0).useObjets(game.getPlayers().get(0).inventaire[2]);
				game.getPlayers().get(0).inventaire[2]=0;
				System.out.println("Emplacement 3: utilisé!");
				break;
			case KeyEvent.VK_T:
				game.getPlayers().get(0).useObjets(game.getPlayers().get(0).inventaire[3]);
				game.getPlayers().get(0).inventaire[3]=0;
				System.out.println("Emplacement 4: utilisé!");
				break;
			case KeyEvent.VK_Y:
				game.getPlayers().get(0).useObjets(game.getPlayers().get(0).inventaire[4]);
				game.getPlayers().get(0).inventaire[4]=0;
				System.out.println("Emplacement 5: utilisé!");
				break;
			case KeyEvent.VK_Q:
				game.indirectAttack(game.getEnnemis().get(0));
				System.out.println("Boule de feu de l'ennemi!");
				break;
			
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
