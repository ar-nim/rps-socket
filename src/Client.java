import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) throws IOException{
		String rps;
		Scanner scan = new Scanner(System.in);
		if(args.length != 3){
			throw new IllegalArgumentException("Usage: client <Server> <Port>");
		}
		String server = args[1];
		int servPort = Integer.parseInt(args[2]);
		
		Socket socket = new Socket(server, servPort);
		
		System.out.println("Connected to " + args[1] + " on port " + args[2]);
		
		do{
			System.out.println("Rock/Paper/Scissors: ");
			rps = scan.nextLine();
		}while(rps.equalsIgnoreCase("Rock") == false && rps.equalsIgnoreCase("Paper") == false && rps.equalsIgnoreCase("Scissors") == false);
		rps = Character.toUpperCase(rps.charAt(0)) + rps.substring(1);
		System.out.println("You chose: " + rps);
		
		DataInputStream in = new DataInputStream(socket.getInputStream());
		DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		out.writeByte(1);
		out.writeUTF(rps);
		out.flush();
		
		
		if(in.readByte() == 3){
			System.out.println("Opponent Chose: " + in.readUTF());
		}
		
		if(in.readByte() == 2){
			System.out.println(in.readUTF());
		}
		
		socket.close();
		
		scan.nextLine();
	}
}