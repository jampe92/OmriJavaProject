package Main_Manu;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import TheGameFrame.GameFrame;

public class MainFrame {

	@SuppressWarnings( "unused" )
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int diff = 0;
		boolean fContinue = false;
		System.out.println("In What diff Do you wish to play??\n  0) Easy\n  1)Hard");
		do{
			try{
				diff = s.nextInt();
				if(diff < 0 || diff>1){
					throw new IOException("Please enter a number fro 0 - 1");
				}
				s.close();
				fContinue = true;
			}catch(IOException io){
				s.nextLine();
				System.out.println(io.getMessage());
			}catch(InputMismatchException ex){
				s.nextLine();
				System.out.println("Please enter a number!!");
			}		
		}while(!fContinue);	
		GameFrame theGameFrame = new GameFrame(diff);
	}
}


