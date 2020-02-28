package eg.edu.alexu.csd.datastructure.hangman.csX22;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;



public class Game_methods implements IHangman {
	public final static int dicLength = 41239;//the length of the dictionary I used 
	String [] secwords = new String [dicLength];//the array of secret words
	int maxWrong;
	char[] initialword = new char [20];
	String secWord;
	
	@Override
	public void setDictionary(String[] words) {
		secwords = words;
	}

	@Override
	public String selectRandomSecretWord() {
		int r =(int)(Math.random()*dicLength );
		return secwords[r];
	}

	@Override
	public String guess(Character c) throws Exception {
		int temp;
		String tempWord;
		temp = secWord.indexOf(c);
		if (this.buggyWords(secWord)) {//checking is the secret word from the dictionary is a buggy word
			throw new Exception("this is a buggy word");
		}
		if (!Character.isLetter(c)) {//check if the user didn't enter an english character
			System.out.println("you must enter an english character");
		}
		else if (temp==-1) {
			maxWrong--;
		}
		if (maxWrong <= 0) {
			return null;
		}
		while (temp!=-1) {
			initialword[temp]=c;
			temp = secWord.indexOf(c,temp+1);
		}
		tempWord=new String(initialword).substring(0,secWord.length());
		return tempWord;
	}

	@Override
	public void setMaxWrongGuesses(Integer max) {
		maxWrong=max;
	}
	
	
	public void readfrmfile (String a[]) {
		try {
			BufferedReader f = new BufferedReader (new FileReader ("dictionary.txt"));
			for (int i=0;i< dicLength ;i++) {
				a[i]= f.readLine();
			}
			f.close();
		} catch (FileNotFoundException e) {
			System.out.println("the dictionary file doesn't exist please insert the file with game file");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
	public void UIgame () {
		char check = 'y',c;
		Scanner s=new Scanner(System.in);
		int x;
		String finalWord = null;
		while (check=='y') {
			x=0;
			for (int i=0;i<20;i++)initialword[i]='-';
			secWord=this.selectRandomSecretWord();
			this.setMaxWrongGuesses(10);
			for (int i=0;i<20;i++)System.out.println();
			while (true) { 
				if (x==0) {
					try {
					for (int i=0;i<secWord.length();i++)System.out.print('-');
					System.out.println("\t\t"+maxWrong+" Attempts Left");
					x++;
					}catch (Exception e) {
						System.out.println("error in choosing the word from dictionary please insert the correct dictionary file then play again");
						break;
				}
				}
				
				System.out.println("please enter the character that might be in the word :");
				c=s.next().toLowerCase().charAt(0); //the program is case sensitivity but it's free to enter upper or lower case character
				for (int i=0;i<50;i++)System.out.println();
				try {
					finalWord=this.guess(c);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(finalWord +"\t\t"+maxWrong+" Attempts Left");
				if (finalWord==null) {
					System.out.println("\n\nGame Over \nthe secret word was : "+ secWord +"\nYou lose the game !!!!!!!Try again to win\n");break;
				}
				else if (finalWord.equals(secWord)){
					System.out.println("\nWow !!!!!! you won the game");break;
				}
			}
			System.out.println("Do you want to play again ? [y,n]");
			check=s.next().charAt(0);
		}
		s.close();
	}
	//checking for buggyWord(the dictionary doesn't have buggy words but this for check)
	
	public boolean buggyWords(String buggy) {
		for (int i=0;i<buggy.length();i++) {
			if (!Character.isLetter(buggy.charAt(i)) && buggy.charAt(i)!='-') {
				return true;
			}
		}
		return false ;
	}
}
