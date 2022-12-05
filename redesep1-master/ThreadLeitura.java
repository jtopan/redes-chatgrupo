import java.io.*;
import java.net.*;

public class ThreadLeitura implements Runnable
{
    private BufferedReader in;
    private Socket socket;

    public ThreadLeitura(Socket socket, Client cliente) throws IOException
    {
        this.socket = socket;

        try
        {
            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void run()
    {
        while (true)
        {
            try
            {
                String msgservidor = in.readLine();
                System.out.println("\n" + msgservidor);
            }
            catch (IOException e)
            {
                e.printStackTrace();
                break;
            }       
        }
    }
}