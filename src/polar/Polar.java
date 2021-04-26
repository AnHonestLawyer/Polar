package polar;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.*;
import org.lwjgl.opengl.*;
import scenes.*;

public class Polar {
	//Control fields, can be edited
	static final int refresh = 60; //edit to modify game refresh rate, default 60 fps
	static final int width = 1000; //edit to modify screen width, default 1000 px
	static final int height = 800; //edit to modify screen height, default 800 px
	static final Scene initialScene = new ExampleScene(); //determines what scene will be loaded initially
	static final boolean showActorInfo = false;
	
	//Do not touch these fields, they make up the backbone of the entire engine
	static final InputManager im = new InputManager();
	static final SceneManager sm = new SceneManager();
	
	private Polar() {
		setupDisplay(width, height);
		
		sm.setScene(initialScene);
		
		while(!Display.isCloseRequested()) {
			glClear(GL_COLOR_BUFFER_BIT); 

			//insert game loop. 
			//By default, the update order will be: input, scene, graphics/sound.
			im.update();
			sm.update();
			
			//DEGUG PRINTS:
			//System.out.println(im.getKeyInfo()); //prints held down keys to console
			

			Display.update();
			Display.sync(refresh);
		}
		
		//im.destroy();
		Display.destroy();
		System.exit(0);
	}
	
	private void setupDisplay(int width, int height) {
		try {
			Display.setDisplayMode(new DisplayMode(width,height));
			Display.setTitle("Polar");
			Display.create();
		}
		catch(LWJGLException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}

		glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, width, height, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		
	}
	
	public static void main(String[] args) {
		new Polar();
	}
}
