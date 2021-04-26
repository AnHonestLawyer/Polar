package scenes;

import polar.*;
import actors.*;
import polar.geometry.*;
import static polar.Util.*;
import polar.input.KeyCode;

public class ExampleScene extends Scene{

	
	
	@Override
	public void open() {
		for(int i=0;i<15;i++) {
			addActor(new Ball(rand()*25+10,rand()*4+3), new Point(100,100));
		}
		addActor(new Box(),new Point(500,500));
	}

	@Override
	public void act() {
		if(isKeyPressed(KeyCode.KEY_R)) {
			setScene(new ExampleScene());
		}
	}

	@Override
	public void close() {
		
	}
}
