import java.net.*;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.io.*;

public class Server {
	private static final int BUFSIZE = 32;
	public static void main(String[] args) throws IOException {
		if(args.length != 2){
			throw new IllegalArgumentException("Usage: server <port>");
		}
		String opponent = " ";
		//String random[] = {"Rock", "Paper", "Scissors"};
		boolean flagclient, flagserver;
		Scanner scan = new Scanner(System.in);
		String result;
		String rps = null;
	
		int servPort = Integer.parseInt(args[1]);
		
		ServerSocket servSock = new ServerSocket(servPort);
		
		Socket clntSock = servSock.accept();
		System.out.println("Client " + clntSock.getInetAddress().getHostAddress() + " connected on port" + clntSock.getPort());
		DataInputStream in = new DataInputStream(clntSock.getInputStream());
		DataOutputStream out = new DataOutputStream(clntSock.getOutputStream());
		do{
			out.writeByte(2);
			out.writeUTF(" ");
			System.out.println("Rock/Paper/Scissors (Type Exit to quit): ");
			opponent = scan.nextLine();
		}while(opponent.equalsIgnoreCase("Rock") == false && opponent.equalsIgnoreCase("Paper") == false && opponent.equalsIgnoreCase("Scissors") == false);
		
		opponent = Character.toUpperCase(opponent.charAt(0)) + opponent.toLowerCase().substring(1);
		
		System.out.println("You Chose: " + opponent);
		System.out.println("Waiting for client..");
		do{
			//do nothing until client sends a data
		}while(in.readByte() == 1 && in.readUTF() == " ");
		
		while(true){
			byte MessageType = in.readByte();
			
			if(MessageType == 1)
				rps = in.readUTF();
				System.out.println("Opponent chose: " + rps);
				break;
		}
			
		out.writeByte(3);
		out.writeUTF(opponent);
			
		if(rps.equalsIgnoreCase("Rock") == true && opponent.equalsIgnoreCase("Scissors") == true){
			out.writeByte(2);
			out.writeUTF("You win");System.out.println("You Lose");
			out.flush();
		}
		else if(rps.equalsIgnoreCase("Rock") == true && opponent.equalsIgnoreCase("Paper") == true){
			out.writeByte(2);
			out.writeUTF("You lose");System.out.println("You Win");
			out.flush();
		}
		else if(rps.equalsIgnoreCase("Paper") == true && opponent.equalsIgnoreCase("Rock") == true){
			out.writeByte(2);
			out.writeUTF("You win");System.out.println("You Lose");
			out.flush();
		}
		else if(rps.equals("Paper") == true && opponent.equals("Scissors") == true){
			out.writeByte(2);
			out.writeUTF("You lose");System.out.println("You Win");
			out.flush();
		}
		else if(rps.equals("Scissors") == true && opponent.equals("Paper") == true){
			out.writeByte(2);
			out.writeUTF("You lose");System.out.println("You Win");
			out.flush();
		}
		else if(rps.equals("Scissors") == true && opponent.equals("Rock") == true){
			out.writeByte(2);
			out.writeUTF("You lose");System.out.println("You Win");
			out.flush();
		}
		else if(rps.equals(opponent)){
			out.writeByte(2);
			out.writeUTF("It's a draw");System.out.println("It's a draw");
			out.flush();
		}
			
		clntSock.close();
		scan.nextLine();
		}
}