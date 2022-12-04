import java.io.*;
import java.net.*;

public class Client
{
    public String nome;

    public void executar()
    {
        // TO-DO MUDAR HOST
        try (Socket socket = new Socket("127.0.0.1", 5555))
        {
            // capturar output stream
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);      

            // capturar input stream
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            new Thread(new ThreadLeitura(in, this)).start();
            new Thread(new ThreadEscrita(out, this)).start();
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