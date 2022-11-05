import java.io.*;
import java.net.Socket;

public class client_tcp {    
    public static void main(String [] args) throws Exception{
    
        //Cria socket com porta definida do servidor
        Socket s = new Socket("127.0.0.1",9000);
        
        // Saida do pacote
        OutputStream saida = s.getOutputStream();
        DataOutputStream writer = new DataOutputStream(saida);
        System.out.println("Pacote saindo: "+writer);


        // recepção do pacote
        InputStreamReader entrada = new InputStreamReader (s.getInputStream());
        BufferedReader reader = new BufferedReader(entrada);
        System.out.println("Pacote recebido: "+writer);
        /*
        ---------------------------------------
        -------------- BUFFER -----------------
        ---------------------------------------
        */
        
        // Lê dados do teclado
        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
                
        // ENVIAR ALGO PARA SERVIDOR
        System.out.print("Digite algo para o server: ");
        String texto = buff.readLine();
        writer.writeBytes(texto +"\n");
        
        // RECEBER RESPOSTA PARA SERVIDOR
        String response = reader.readLine();
        System.out.println("Resposta do server: "+response);
        
        s.close();



    }   

}
