import java.io.*;

public class ThreadLeitura implements Runnable
{
    private BufferedReader in;
    private Client cliente;

    public ThreadLeitura(BufferedReader in, Client cliente) throws IOException
    {
        this.in = in;
        this.cliente = cliente;
    }

    public void run()
    {
        while (true)
        {
            try
            {
                String msgservidor = in.readLine();
                System.out.println("\n" + msgservidor);

                if (cliente.nome != null)
                {
                    System.out.println("[" + cliente.nome + "]: ");
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
                break;
            }       
        }
    }
}