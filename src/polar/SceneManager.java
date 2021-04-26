package polar;

class SceneManager {

	private Scene activeScene;
	
	SceneManager() {
	}
	
	void update() {
		if(activeScene!=null)
			activeScene.step();
	}
	
	void setScene(Scene s) {
		if(activeScene!=null)
			activeScene.close();
		activeScene = s;
		activeScene.open();
		activeScene.addActorsForFrame();
	}
}
