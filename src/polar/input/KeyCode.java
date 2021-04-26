package polar.input;

public enum KeyCode {
	KEY_A ("A",30),
	KEY_B ("B",48),
	KEY_C ("C",46),
	KEY_D ("D",32),
	KEY_E ("E",18),
	KEY_F ("F",33),
	KEY_G ("G",34),
	KEY_H ("H",35),
	KEY_I ("I",23),
	KEY_J ("J",36),
	KEY_K ("K",37),
	KEY_L ("L",38),
	KEY_M ("M",50),
	KEY_N ("N",49),
	KEY_O ("O",24),
	KEY_P ("P",25),
	KEY_Q ("Q",16),
	KEY_R ("R",19),
	KEY_S ("S",31),
	KEY_T ("T",20),
	KEY_U ("U",22),
	KEY_V ("V",47),
	KEY_W ("W",17),
	KEY_X ("X",45),
	KEY_Y ("Y",21),
	KEY_Z ("Z",44),
	KEY_1 ("1",2),
	KEY_2 ("2",3),
	KEY_3 ("3",4),
	KEY_4 ("4",5),
	KEY_5 ("5",6),
	KEY_6 ("6",7),
	KEY_7 ("7",8),
	KEY_8 ("8",9),
	KEY_9 ("9",10),
	KEY_0 ("0",11),
	KEY_LCTRL("LCTRL",29),
	KEY_LSHIFT("LSHIFT",42),
	KEY_CAPS("CAPS",58),
	KEY_TAB("TAB",15),
	KEY_ENTER("ENTER",28),
	KEY_LEFT("LEFT",203),
	KEY_RIGHT("RIGHT",205),
	KEY_UP("UP",200),
	KEY_DOWN("DOWN",208),
	KEY_ESCAPE("ESCAPE",1),
	KEY_SPACE("SPACE",57);
	
	private final String s;
	private final int val;
	
	KeyCode(String s, int val) {
		this.s=s;
		this.val=val;
	}
	
	public String toString() {return s;}
	public int getVal() {return val;}
}
