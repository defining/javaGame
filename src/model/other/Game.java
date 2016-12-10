package model.other;
import view.Window;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import model.interfaces.Observer;
import model.objets.ArmesAttaque;
import model.objets.ArmesDefense;
import model.objets.Block;
import model.objets.MursIndestructibles;
import model.objets.Objets;
import model.objets.Potions;
import model.personnages.Player;
import model.personnages.PlayerEnnemis;
import model.personnages.PlayerPrincipal;
import model.projectiles.Projectiles;
import model.projectiles.Sorts;

public class Game implements Observer{
	private ArrayList<PlayerPrincipal> players = new ArrayList<PlayerPrincipal>();
	private ArrayList<MursIndestructibles> blocks = new ArrayList<MursIndestructibles>();
	private ArrayList<PlayerEnnemis> ennemis = new ArrayList<PlayerEnnemis>();
	public ArrayList<Sorts> sorts = new ArrayList<Sorts>();
	public ArrayList<Potions> potions = new ArrayList<Potions>();
	public ArrayList<ArmesAttaque> armureAttak = new ArrayList<ArmesAttaque>();
	public ArrayList<ArmesDefense> armureDefense = new ArrayList<ArmesDefense>();
	public static int nbreSorts=0;
	private Window window;
	public static int size = 15;
	/**
	 * Dans la fonction update on a respectivement:
	 * - Vérification des objets "morts" pour les retirer de la map
	 * - Dessin de la nouvelle map après notification
	 * - Détermination des coordonnées des positions des joueurs et des ennemis (en cas d'attaque)
	 * - Ramassage des objets si un personnage PRINCIPAL se trouve sur une case où il y a un objet
	 *-  Un rafraichissement de l'inventaire sur la console
	 */
	public void update(){
		this.isAlive(ennemis);
		this.isAlive(potions);
		this.isAlive(armureAttak);
		this.isAlive(armureDefense);
		this.isAlive2(sorts);
		window.draw(this.getMap());
		this.setPresenceObjets(getPlayers());
		this.setPresenceObjets(getEnnemis());
		this.setPresenceObjets2(sorts);	
		this.setObjetsLoops(potions);
		this.setObjetsLoops(armureAttak);
		this.setObjetsLoops(armureDefense);
		this.seeInventaire();
		
	}
	
	public Game(Window window){
		this.window = window;
		Random rand = new Random(); //Pour l'utilisation de nombre aléatoire
		// Map building 
		for(int i = 0; i < size; i++){
			blocks.add(new MursIndestructibles(i,0));
			blocks.add(new MursIndestructibles(0,i));
			blocks.add(new MursIndestructibles(i, size-1));
			blocks.add(new MursIndestructibles(size-1, i));
			}
		
		
		// Creating one Player at position (10,10) + lancement du thread
		getPlayers().add(new PlayerPrincipal(10,10));
		getPlayers().get(0).attach(this);

		// Creating equipment + thread
		potions.add(new Potions(7,7));
		armureAttak.add(new ArmesAttaque(8,8));
		armureDefense.add(new ArmesDefense(6,9));
		
		
		// Creating one ennemis at random position + thread
		int max=size-2;
		int min=2;
		int nbreEnnemis=10;
		
		for(int i=0;i<nbreEnnemis; i++){
		getEnnemis().add(new PlayerEnnemis(rand.nextInt(max - min + 1) + min,rand.nextInt(max - min + 1) + min,this));	
		getEnnemis().get(i).attach(this);
		}
		
		update();
	}	
	/**
	 * Attribue à chaque case un nombre pour son dessin par la view
	 * @return la matrice de valeur de la map
	 */
	
	public int[][] getMap(){
		int[][] map = new int[Game.size][Game.size];
		for(int i = 0; i<Game.size; i++)
			for(int j = 0; j<Game.size; j++)
				map[i][j] = 0;
		
		
		for(Block block : blocks){
			int x = block.getPosX();
			int y = block.getPosY();
			map[x][y] = 1;
		}
		//Attribution d'un numéro à chaque case de la matrice pour permettre son dessin dans la classe Map
		for(PlayerEnnemis ennemi : getEnnemis()){
			int x = ennemi.getPosX();
			int y = ennemi.getPosY();
			if (ennemi.getHealth()!=0){
				map[x][y] = 3;
				}
			else{
			map[x][y] = 0;
				}
		}
		for(Sorts sort : sorts){
			int x = sort.getPosX();
			int y = sort.getPosY();
			map[x][y] = 5;
		}
		
		for(Potions potions : potions){
			int x = potions.getPosX();
			int y = potions.getPosY();
			map[x][y] = 6;
		}
		
		for(ArmesAttaque armeAttaque : armureAttak){
			int x = armeAttaque.getPosX();
			int y = armeAttaque.getPosY();
			map[x][y] = 7;
		}
		
		for(ArmesDefense armeDefense : armureDefense){
			int x = armeDefense.getPosX();
			int y = armeDefense.getPosY();
			map[x][y] = 8;
		}
		
		for(PlayerPrincipal player : getPlayers()){
			int x = player.getPosX();
			int y = player.getPosY();
			map[x][y] = 2;			
		}
		System.out.println(map);
		return map;
	}
	
	
	
	

	/**
	 * Etablis qui sont les voisins de chaque objets des listes players pour savoir qui se trouve à droite, à gauche, en haut en bas du player.
	 * @param list La liste représente la liste analysée.
	 */
	public void setPresenceObjets(ArrayList<? extends Player> list){
			int[][] map=this.getMap();
			for (Player player: list){
			int x=player.getPosX();
			int y=player.getPosY();
			if (map[x+1][y]!=0){
				player.setCanMoveRight(map[x+1][y],x+1,y);
			}
			else{
				player.setCanMoveRight(0,0,0); //sans cette ligne de code, au moindre obstacle, elle reste bloqué à vie
			}
			if(map[x-1][y]!=0){
				player.setCanMoveLeft(map[x-1][y],x-1,y);
			}
			else{
				player.setCanMoveLeft(0,0,0);
			}
			if(map[x][y+1]!=0){
				player.setCanMoveDown(map[x][y+1],x,y+1);
			}
			else{
				player.setCanMoveDown(0,0,0);
			}
			if(map[x][y-1]!=0){
				player.setCanMoveUp(map[x][y-1],x,y-1);
			}
			else{
				player.setCanMoveUp(0,0,0);
			}
			player.setAttackParameters();
		}
		
	}
	
	public void setPresenceObjets2(ArrayList<? extends Projectiles> list){
		int[][] map=this.getMap();
		for (Projectiles player: list){
		int x=player.getPosX();
		int y=player.getPosY();
		if (map[x+1][y]!=0){
			player.setCanMoveRight(map[x+1][y],x+1,y);
		}
		else{
			player.setCanMoveRight(0,0,0); //sans cette ligne de code, au moindre obstacle, elle reste bloqué à vie
		}
		if(map[x-1][y]!=0){
			player.setCanMoveLeft(map[x-1][y],x-1,y);
		}
		else{
			player.setCanMoveLeft(0,0,0);
		}
		if(map[x][y+1]!=0){
			player.setCanMoveDown(map[x][y+1],x,y+1);
		}
		else{
			player.setCanMoveDown(0,0,0);
		}
		if(map[x][y-1]!=0){
			player.setCanMoveUp(map[x][y-1],x,y-1);
		}
		else{
			player.setCanMoveUp(0,0,0);
		}
		player.setAttackParameters();
	}
	
}
	
	
	//Getters et setters des listes d'objets
	
	public ArrayList<PlayerPrincipal> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<PlayerPrincipal> players) {
		this.players = players;
	}

	public ArrayList<PlayerEnnemis> getEnnemis() {
		return ennemis;
	}

	public void setEnnemis(ArrayList<PlayerEnnemis> ennemis) {
		this.ennemis = ennemis;
	}
	
	/**
	 * Permet de récupérer l'objet de celui qu'on veut attaquer. lastPosition représente le dernier mouvement du player et donc on ne regarde que ce qui fait face au joueur pour attaquer, pas les autres directions
	 * @param list Pour quel joueur on fait ca
	 * @param pos A quelle position il se situe dans sa liste
	 * @return l'objet en question qu'on désire attaquer (si il y en a)
	 */
	
	public ObjetGenericGame getGoodObject(ArrayList<? extends ObjetGenericGame> list, int pos){
		int x=0;
		int y=0;
		if (list.get(pos).lastPosition == 'd'){
			x=list.get(pos).CoordonneePresenceDownX;
			y=list.get(pos).CoordonneePresenceDownY;
		}
		else if (list.get(pos).lastPosition == 'u'){
			x=list.get(pos).CoordonneePresenceUpX;
			y=list.get(pos).CoordonneePresenceUpY;
		}
		else if (list.get(pos).lastPosition == 'r'){
			x=list.get(pos).CoordonneePresenceRightX;
			y=list.get(pos).CoordonneePresenceRightY;
		}
		else if (list.get(pos).lastPosition == 'l'){
			x=list.get(pos).CoordonneePresenceLeftX;
			y=list.get(pos).CoordonneePresenceLeftY;
		}
		ObjetGenericGame objet=null;
	
		if (objet==null){
		for(int j=0;j<ennemis.size();j++){
			if (ennemis.get(j).getPosX()== x && ennemis.get(j).getPosY()==y){
				objet=ennemis.get(j);
				}
		
		}
		}
		return objet;
	}
		
		public PlayerEnnemis getGoodObject2(Projectiles sort){
			int x=0;
			int y=0;
			if (sort.lastPosition == 'd'){
				x=sort.CoordonneePresenceDownX;
				y=sort.CoordonneePresenceDownY;
				
			}
			else if (sort.lastPosition == 'u'){
				x=sort.CoordonneePresenceUpX;
				y=sort.CoordonneePresenceUpY;
				System.out.println("Coordonnées notés!");
			}
			else if (sort.lastPosition == 'r'){
				x=sort.CoordonneePresenceRightX;
				y=sort.CoordonneePresenceRightY;
			}
			else if (sort.lastPosition == 'l'){
				x=sort.CoordonneePresenceLeftX;
				y=sort.CoordonneePresenceLeftY;
			}
			PlayerEnnemis objet=null;
			
			if (objet==null){
			for(int j=0;j<ennemis.size();j++){
				if (ennemis.get(j).getPosX()== x && ennemis.get(j).getPosY()==y){
					objet=ennemis.get(j);
					}
			}
			}
		return objet;
		
		
	
	}
		
		
	/**
	 * Détermine tous les éléments qui sont morts dans une liste et les retire
	 * @param list la liste analysée
	 */
	
	
	
	public void isAlive(ArrayList<? extends ObjetGenericGame> list){
		for (int i = list.size() - 1; i >= 0; --i){
			if(!list.get(i).alive){
				if(list.get(i) instanceof PlayerEnnemis){
				Random rand = new Random();
				int max1=3;
				int min1=1;
				int aleatoire=rand.nextInt(max1 - min1 + 1) + min1;
				if(aleatoire == 1){
					potions.add(new Potions(list.get(i).getPosX(),list.get(i).getPosY()));
				}
				else if(aleatoire == 2){
					armureAttak.add(new ArmesAttaque(list.get(i).getPosX(),list.get(i).getPosY()));
				}
				else if(aleatoire == 3){
					armureDefense.add(new ArmesDefense(list.get(i).getPosX(),list.get(i).getPosY()));
				}
				}
				list.remove(i);
				
				i=0;
			}
	}

}
	
	public void isAlive2(ArrayList<? extends Projectiles> list){
		Iterator<? extends Projectiles> it = list.iterator();
		while (it.hasNext()) {
			System.out.println("Ca marche");
		    if (!it.next().alive) {
		        it.remove();
		        System.out.println("Il a été retiré");
		    }
		}
	}
	
	/**
	 * Si 1 playerPrincipal est sur la case d'un objet (potions ou autres), alors elle lance leur méthode respective pour que l'objet soit ramassé
	 * @param list liste des objets qu'on vérifie si ils sont sur le chemin du player
	 */
	public void setObjetsLoops(ArrayList<? extends Objets> list){
		for (int i = list.size() - 1; i >= 0; --i){
				if(list.get(i).getPosX()==players.get(0).getPosX() && list.get(i).getPosY()==players.get(0).getPosY()){
					players.get(0).InteractionWithObjets(list);
					list.get(i).InteractionWithObjets(list);
				
			}
			
		}
		
	}
	
	/**
	 * Affiche l'inventaire à défaut de l'avoir implémenté graphiquement, on le voit sur la console
	 */
	
	public void seeInventaire(){
		for(int i=players.get(0).inventaire.length-1;i>=0;i--){
			System.out.println("A l'emplacement " + i + " il y a l'objet numéro " + players.get(0).inventaire[i]);
		}
	}
	
	/**
	 * Lance une attaque indirecte (et donc un sort qui aura son propre thread pour exister)
	 */
	
	public void indirectAttack(){
		if (players.get(0).level>=2){
		sorts.add(new Sorts(40,players.get(0)));
		nbreSorts+=1;
		for(int i=sorts.size()- 1; i >= 0; --i){
			sorts.get(i).attach(this);
		}
		update();
		}
		else{
			System.out.println("T'as pas le niveau! Tu n'es que niveau " + players.get(0).level);
		}
	}
	
	/**
	 * Méthode surchargé
	 * @param player
	 */
	public void indirectAttack(PlayerEnnemis player){
		sorts.add(new Sorts(40,player));
		nbreSorts+=1;
		for(int i=sorts.size()- 1; i >= 0; --i){
			sorts.get(i).attach(this);
		}
		update();
		}
	}


