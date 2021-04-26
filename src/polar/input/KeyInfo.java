package polar.input;

import java.util.*;
import org.lwjgl.input.Keyboard;

public class KeyInfo {
	
	private Set<KeyCode> keysDown;
	private Set<KeyCode> keysPressed;
	private Set<KeyCode> keysReleased;
	
	public KeyInfo() {
		keysDown = new HashSet<KeyCode>();
		keysPressed=new HashSet<KeyCode>();
		keysReleased=new HashSet<KeyCode>();
	}
	
	public void updateKeys() {
		//keysDown.clear();
		keysPressed.clear();
		keysReleased.clear();
		for(KeyCode key : KeyCode.values()) {
			if(Keyboard.isKeyDown(key.getVal())) {
				if(!keysDown.contains(key)) {
					keysPressed.add(key);
				}
				keysDown.add(key);
			}
			else {
				if(keysDown.contains(key)) {
					keysReleased.add(key);
				}
				keysDown.remove(key);
			}
		}
	}
	
	@Override
	public String toString() {
		return keysPressed.toString();
	}
	
	public boolean isKeyDown(KeyCode key) {
		return keysDown.contains(key);
	}
	public boolean isKeyPressed(KeyCode key) {
		return keysPressed.contains(key);
	}
	public boolean isKeyReleased(KeyCode key) {
		return keysReleased.contains(key);
	}
}
