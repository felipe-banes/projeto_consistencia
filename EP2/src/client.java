import java.io.*;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class client{    
    static String menu_opcao;
    static String keyValueRegisterInfo;
    static Socket dhtClientSocket;
    static String paddedKeyValue = null;
    static boolean resultOfOperation;
    static String getKeyName = null;
    
    //Socket constructs
    private static ConcurrentHashMap <String, Socket> socketMapping = new ConcurrentHashMap<String, Socket>();	
    @SuppressWarnings("deprecation")
    public static void main(String [] args) throws Exception{
        
        DataInputStream entrada_dados = new DataInputStream(System.in);

        //Cria socket com porta definida do servidor
        //Socket s = new Socket();

		try{

			do{
				System.out.println("\n------ MENU -----");
				System.out.println("1. PUT");
				System.out.println("2. GET");
				System.out.println("3. EXIT");
				System.out.print("Escolha a opção: ");

				//read menu_opcao from client
				menu_opcao = entrada_dados.readLine();

                System.out.println("\n-----------");
                switch(menu_opcao){

				case "1":
                //accept the key value pair separated by ";"
					System.out.println("Entre com a chave/valor para cadastrar: ");
					keyValueRegisterInfo = entrada_dados.readLine();

					String[] keyValuePair = keyValueRegisterInfo.split(";");

					//find the hashValue, where to put this KEY,VALUE
					dhtClientSocket = myHashFunction(padKey(keyValuePair[0]));

					//put the value if local server, put into local hash table
					//else, put into other server, by connecting to other server using sockCommunicateStream
                    
                    System.out.println("VALOR DE dhtClientSocket --> "+ dhtClientSocket);
                    if(dhtClientSocket == null){
                        resultOfOperation = client_build.put(padKey(keyValuePair[0]), padValue(keyValuePair[1]));
						if(resultOfOperation == true)
								System.out.println("Success");
							else
								System.out.println("Failure");
					}
					else{
						paddedKeyValue = padKey(keyValuePair[0])+";"+padValue(keyValuePair[1]);
						sockCommunicateStream(dhtClientSocket,menu_opcao,paddedKeyValue);
					}
					
					break;
                    
                    
                    case "2": //GET

					System.out.println("Entre the CHAVE to get: ");
					getKeyName = entrada_dados.readLine();

					//get the Hashvalue where to get the value from
					dhtClientSocket = myHashFunction(padKey(getKeyName));
					
					//search in local hashtable,
					//else, find from other server
					if(dhtClientSocket == null){
						System.out.println("VALOR: "+client_build.get(padKey(getKeyName)));
					}
					else{
						sockCommunicateStream(dhtClientSocket,menu_opcao,padKey(getKeyName));
					}
					break;

                } 
            
            }while(!(menu_opcao.equals("3")));
        


        } catch(IOException e){e.printStackTrace();}

    }

/*This method finds the hashValue and return the Server Socket for Communication*/
public static Socket myHashFunction(String Key){
    System.out.println("LOCALIZANDO VALOR...");
    String hashValue = "server "+Math.abs((Key.hashCode())%8);
    Socket value = socketMapping.get(hashValue);
    System.out.println("SUCESSO: "+hashValue);
    
    return value;
}

/*This method is used to connect between sockets i.e. Servers, and send and receive
 * message and Communicate for key/value pair to get/put/delete */
 @SuppressWarnings("deprecation")
public static void sockCommunicateStream(Socket sckt, String menu_opcao2, String clientInpVal){
    
    try
    {
        //make send and receive for sockets to communicate
        DataInputStream dInpServer = new DataInputStream(sckt.getInputStream());
        DataOutputStream dOutServer = new DataOutputStream(sckt.getOutputStream());

        //send the server the choice and key/value
        dOutServer.writeUTF(menu_opcao2);
        dOutServer.writeUTF(clientInpVal);

        if(menu_opcao2.equals("2")){
            System.out.println("Value is: "+dInpServer.readLine());
        }

        if(menu_opcao2.equals("1") || menu_opcao2.equals("3"))
        {	
            String resultValue = dInpServer.readLine();
            if(resultValue.equals("true")){
                System.out.println("Success");
            }
            else{
                System.out.println("Failure");
            }
        }
        
    } catch(IOException e){e.printStackTrace();}
}

/* The entire message i.e KEY + VALUE is of 1024 bytes
 * out of which KEY is of 24 Bytes, here we pad the remaning bytes of the key with "*" 
 * while sending we send entire 1024 bytes*/
public static String padKey(String key)
{
    for(int i=key.length();i<24;i++)
    {
        key+="*";
    }
    return key;
}

/* The entire message i.e KEY + VALUE is of 1024 bytes
 * out of which VALUE is of 1000 Bytes, here we pad the remaning bytes of the value with "*" 
 * while sending we send entire 1024 bytes*/
public static String padValue(String value)
{
    for(int i=value.length();i<1000;i++)
    {
        value+="*";
    }
    return value;
}



}
