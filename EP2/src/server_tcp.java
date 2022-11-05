import java.net.*;

public class server_tcp{
    public static void main(String [] args) throws Exception{
        ServerSocket s_9000 = new ServerSocket(9000);
        
        System.out.print("Aguardando conexão...\n");
        while (true){
            Socket no = s_9000.accept();
            System.out.println("Conexão Estabelecida!");

            // ---------REALIZAR PASSO A PASSO VIA THREAD--------
            thread_recepcao t = new thread_recepcao(no); // DEFINIR PARAMETROS
            t.start(); // EXECUTAR A FUNÇÃO
            
        }
       
    }            
}