import java.io.*;
import java.net.*;

public class Client
{
    public String nome;
    public Socket socket;

    public void executar(String host)
    {
        // TO-DO MUDAR HOST
        try 
        {
            socket = new Socket(host, 5555);

            new Thread(new ThreadEscrita(socket, this)).start();
            new Thread(new ThreadLeitura(socket, this)).start();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) 
    {
        String host = System.console().readLine("Insira um endereço IP do servidor: ");
        Client cliente = new Client();
        cliente.executar(host);
    }
}