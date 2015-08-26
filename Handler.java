package marshmallow;


import java.awt.Graphics;
import java.util.LinkedList;

//handles objects created
//loops through, updates and renders them
//TODO: Is it even possible to have an 'enemy-only' or 'player-only' Linked list?
public class Handler { 
	
	LinkedList<GameObject> objectList = new LinkedList<GameObject>();

	
	public void tick(){
		for(int i = 0; i < objectList.size(); i++){
			GameObject tempObject = objectList.get(i);
			tempObject.tick();
		}
	}
	
	public void render(Graphics g){
		for(int i = 0; i < objectList.size(); i++){
			GameObject tempObject = objectList.get(i);
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object){
		this.objectList.add(object);
	}
	
	public void removeObject(GameObject object){
		this.objectList.remove(object);
	}
	
	public void clear() {
		objectList.clear();
	}
}
