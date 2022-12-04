import java.io.*;

public class ThreadEscrita implements Runnable
    {
        private PrintWriter out;
        private Client cliente;

        public ThreadEscrita(PrintWriter out, Client cliente) throws IOException
        {
            this.out = out;
            this.cliente = cliente;
        }

        public void run()
        {
            String nome = System.console().readLine("\nInforme seu nome de usuario: ");
            cliente.nome = nome;
            out.println(nome);

            String msgcliente;

            do
            {
                msgcliente = System.console().readLine("[" + nome + "]: ");
                out.println(msgcliente);

            } while (!msgcliente.equals("-d"));
        }
    }