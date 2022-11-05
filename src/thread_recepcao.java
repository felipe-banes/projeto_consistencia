import java.io.*;
import java.net.*;

public class thread_recepcao extends Thread{
    
    private Socket no;
    
    public thread_recepcao(Socket node){
        no = node;
    }
    
    public void run(){
        System.out.println("THREAD ATIVADA");
        try{
            InputStreamReader entrada = new InputStreamReader(no.getInputStream());
            BufferedReader reader = new BufferedReader(entrada);


            OutputStream saida =no.getOutputStream();
            DataOutputStream writer = new DataOutputStream(saida);

            String texto = reader.readLine();
            writer.writeBytes(texto.toUpperCase()+"\n");
            System.out.println("MENSAGEM DEVOLVIDA");

    } catch(Exception e){System.out.println("Queda no servidor");}
    }
}