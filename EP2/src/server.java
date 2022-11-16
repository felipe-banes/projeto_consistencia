
import java.net.*;

public class server{
    private static Socket clientSocket;

    public static void main(String [] args) throws Exception{ 
        //DataInputStream entrada_dados = new DataInputStream(System.in);

        //System.out.print("Digite a porta deste servidor: ");       
        //String porta = entrada_dados.readUTF();
        
        ServerSocket s = new ServerSocket(9008);
        
        while(!s.isClosed())
        {
            System.out.println("Aguardando conexão...\n");
            clientSocket = s.accept();
            System.out.println(" Cliente aceitou conexão");

            //start the server side thread
            Thread client_build = new Thread(new client_build(clientSocket));
            client_build.start();
            
            s.isClosed();
        }

        
    }            
}