import java.io.*;
import java.net.*;

public class Client
{
    public String nome;
    public Socket socket;

    public void executar()
    {
        // TO-DO MUDAR HOST
        try 
        {
            socket = new Socket("192.168.1.236", 5555);

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
        Client cliente = new Client();
        cliente.executar();
    }
}