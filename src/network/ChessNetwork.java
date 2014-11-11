package network;

import static org.mockito.Mockito.*;


/**
 * 
 */

/**
 * @author zeno
 * The sole purpose of this class is to generate a GUI-based 
 * chess board with the pieces on their initial location
 */
public class ChessNetwork {
	public static void main(String[] args){
		Server mockServer = mock(Server.class);
		Client mockClient = mock(Client.class);		
		mockServer.run();		
		mockClient.run();
	}
}
