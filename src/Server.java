import java.net.*;
import java.util.concurrent.ThreadLocalRandom;
import java.io.*;

public class Server {
	private static final int BUFSIZE = 32;
	public static void main(String[] args) throws IOException {
		if(args.length != 2){
			throw new IllegalArgumentException("Usage: server <port>");
		}
		String random[] = {"Rock", "Paper", "Scissors"};
		String result;
		String rps = null;
	
		int servPort = Integer.parseInt(args[1]);
		
		ServerSocket servSock = new ServerSocket(servPort);
		
		for(;;){
			Socket clntSock = servSock.accept();
			System.out.println("Client " + clntSock.getInetAddress().getHostAddress() + " connected on port" + clntSock.getPort());
			DataInputStream in = new DataInputStream(clntSock.getInputStream());
			DataOutputStream out = new DataOutputStream(clntSock.getOutputStream());
			
			while(true){
				byte MessageType = in.readByte();
				
				switch(MessageType){
				case 1:
					rps = in.readUTF();
					System.out.println("Client chose: " + rps);
				}
				break;
			}
			
			String opponent = random[ThreadLocalRandom.current().nextInt(0, 3)];
			System.out.println("Server Chose: " + opponent);
			out.writeByte(3);
			out.writeUTF(opponent);
			
			if(rps.equalsIgnoreCase("Rock") == true && opponent.equalsIgnoreCase("Scissors") == true){
				out.writeByte(2);
				out.writeUTF("You win");
				out.flush();
			}
			else if(rps.equalsIgnoreCase("Rock") == true && opponent.equalsIgnoreCase("Paper") == true){
				out.writeByte(2);
				out.writeUTF("You lose");
				out.flush();
			}
			else if(rps.equalsIgnoreCase("Paper") == true && opponent.equalsIgnoreCase("Rock") == true){
				out.writeByte(2);
				out.writeUTF("You win");
				out.flush();
			}
			else if(rps.equals("Paper") == true && opponent.equals("Scissors") == true){
				out.writeByte(2);
				out.writeUTF("You lose");
				out.flush();
			}
			else if(rps.equals("Scissors") == true && opponent.equals("Paper") == true){
				out.writeByte(2);
				out.writeUTF("You lose");
				out.flush();
			}
			else if(rps.equals("Scissors") == true && opponent.equals("Rock") == true){
				out.writeByte(2);
				out.writeUTF("You lose");
				out.flush();
			}
			else if(rps.equals(opponent)){
				out.writeByte(2);
				out.writeUTF("It's a draw");
				out.flush();
			}
			
			
			clntSock.close();
		}
		
		
	}
}