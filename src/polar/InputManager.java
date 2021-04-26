package polar;

import polar.input.*;

class InputManager {
	
	private KeyInfo ki;
	
	InputManager() {
		ki = new KeyInfo();
	}
	
	void update() {
		ki.updateKeys();
	}
	
	KeyInfo getKeyInfo() {
		return ki;
	}
}
