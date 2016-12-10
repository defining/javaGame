import java.util.Scanner;

import controller.Keyboard;
import model.other.Game;
import view.Window;

/**
 * La classe Main lance le jeu notamment en instanciant Game, Window et Keyboard
 * @author AMZUR Soufiane (000 328 725) et LOUHAJI Najlae (000 408 719)
 *
 */
public class Main {
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		Main.begin(reader);
		Main.setNewMap();
		
		
	}
	
	/**
	 * Instancie les differentes classes necessaire au jeu (Game, Keyboard, Window)
	 */
	
	public static void setNewMap(){
		Window window = new Window();
		
		Game game = new Game(window);
		
		
		Keyboard keyboard = new Keyboard(game);
		window.setKeyListener(keyboard);
		}
	
	/**
	 * La methode invite l'utilisateur a entrer la taille de la Map (borne)
	 * @param reader est une instance de Scanner qui nous permet de lire les inputs de l'utilisateur
	 * 
	 */
	public static void begin(Scanner reader){
		System.out.println("Entrez la taille du plateau entre 15 et 20. Une valeur hors de ces bornes sera ignoré: ");
		int n = reader.nextInt();
		if (n>=15 && n<=20){
		Game.size=n;
		}
	}
}
