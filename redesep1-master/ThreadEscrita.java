import java.io.*;
import java.net.*;

public class ThreadEscrita implements Runnable
    {
        private PrintWriter out;
        private Client cliente;
        private Socket socket;

        public ThreadEscrita(Socket socket, Client cliente) throws IOException
        {
            this.socket = socket;
            this.cliente = cliente;

            try
            {
                this.out = new PrintWriter(this.socket.getOutputStream(), true);  
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        public void run()
        {
            String nome = System.console().readLine("\nInforme seu nome de usuario: ");
            cliente.nome = nome;
            out.println(nome);

            String msgcliente;
            String msg;

            do
            {
                msg = System.console().readLine();
                msgcliente = "[" + nome + "]: " + msg;
                out.println(msgcliente);

            } while (!msg.equals("-d"));
        }
    }