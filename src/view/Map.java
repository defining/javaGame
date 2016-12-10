package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class Map extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private int[][] mapMatrix;
	
	

	
	
	public Map(){
		
		this.setFocusable(true);
		this.requestFocusInWindow();
		
	
		
		
		
	}
	
public void paint(Graphics g) {
	/**
	 * Les images des differents objets de la map.
	 */
	Image img = Toolkit.getDefaultToolkit().getImage("img/face.png");
	Image img2 = Toolkit.getDefaultToolkit().getImage("img/tiles.png");
	Image img3 = Toolkit.getDefaultToolkit().getImage("img/ennemis.png");
	Image img4 = Toolkit.getDefaultToolkit().getImage("img/arbre.png");
	Image img5 = Toolkit.getDefaultToolkit().getImage("img/boules2.png");
	Image img6 = Toolkit.getDefaultToolkit().getImage("img/potions3.png");
	Image img7 = Toolkit.getDefaultToolkit().getImage("img/armes.png");
	Image img8 = Toolkit.getDefaultToolkit().getImage("img/armure2.png");
		
		
		if(mapMatrix == null){
		}else{
			for(int i = 0; i<mapMatrix.length; i++){
				for(int j = 0; j<mapMatrix.length; j++){
					int x = i;
					int y = j;
					int color = mapMatrix[i][j];
					
					
					if(color == 0){
		                g.drawImage(img2 , x*35 , y*35, this);
					}else if(color == 1){
						g.drawImage(img2 , x*35 , y*35, this);
						g.drawImage(img4 , x*35 , y*35, this);
					}else if(color == 2){ 
						g.drawImage(img2 , x*35 , y*35, this);
						g.drawImage(img , x*35 , y*35, this);
					}
					else if(color == 5){
						g.drawImage(img2 , x*35 , y*35, this);
						g.drawImage(img5 , x*35 , y*35, this);
					}
					else if(color == 3){
						g.drawImage(img2 , x*35 , y*35, this);
						g.drawImage(img3 , x*35 , y*35, this);
					}
					
					else if(color == 6){
						g.drawImage(img2 , x*35 , y*35, this);
						g.drawImage(img6 , x*35 , y*35, this);
					}
					else if(color == 7){
						g.drawImage(img2 , x*35 , y*35, this);
						g.drawImage(img7 , x*35 , y*35, this);
					}
					else if(color == 8){
						g.drawImage(img2 , x*35 , y*35, this);
						g.drawImage(img8 , x*35 , y*35, this);
						
					}
				}
			}
		}
	}
	
					
                     
					
				
				
	
	public void setMapMatrix(int[][] mapMatrix){
		this.mapMatrix = mapMatrix;
		this.repaint();
	}

}

