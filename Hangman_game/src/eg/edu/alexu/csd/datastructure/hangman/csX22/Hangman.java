package eg.edu.alexu.csd.datastructure.hangman.csX22;


public class Hangman {

	public static void main(String[] args) {
		Game_methods g = new Game_methods();
		String[] wordsfrmdic= new String[Game_methods.dicLength];
		g.readfrmfile (wordsfrmdic);
		g.setDictionary(wordsfrmdic);
		g.UIgame();
	}
	

}
