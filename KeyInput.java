package marshmallow;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	private Handler handler;
	private boolean[] keyDown = new boolean[4];
	
	public KeyInput(Handler handler){
		this.handler = handler;
		
//		keyDown[0] = false; //, 
//		keyDown[1] = false; //o
//		keyDown[2] = false; //a
//		keyDown[3] = false; //e
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.objectList.size(); i++){
			GameObject tempObject = handler.objectList.get(i);
			
			if(tempObject.getId() == ID.Player){
				// key event for player one
//				if(key == KeyEvent.VK_COMMA) { tempObject.setVelY(-5); keyDown[0]=true;}
//				if(key == KeyEvent.VK_O) {tempObject.setVelY(5); keyDown[1]=true;}
//				if(key == KeyEvent.VK_A) {tempObject.setVelX(-5); keyDown[2]=true;}
//				if(key == KeyEvent.VK_E) {tempObject.setVelX(5); keyDown[3]=true;}
			}
		}	
		
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
	}
	
	public void keyReleased(KeyEvent e){
//		int key = e.getKeyCode();

		for(int i = 0; i < handler.objectList.size(); i++){
			GameObject tempObject = handler.objectList.get(i);
		
			if(tempObject.getId() == ID.Player){
//				if(key == KeyEvent.VK_COMMA) keyDown[0] = false;//tempObject.setVelY(0);
//				if(key == KeyEvent.VK_O) keyDown[1] = false; //tempObject.setVelY(0);
//				if(key == KeyEvent.VK_A) keyDown[2] = false; //tempObject.setVelX(0);
//				if(key == KeyEvent.VK_E) keyDown[3] = false; //tempObject.setVelX(0);
				
				//vertical 
				if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
				if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
			}
		}
	}
}
