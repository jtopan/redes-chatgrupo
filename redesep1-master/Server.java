import java.io.*;
import java.net.*;
import java.util.*;

public class Server 
{   
    private ServerSocket servidor;
    private ArrayList<ClientHandler> listaclientes;

    public Server()
    {
        this.servidor = null;
        this.listaclientes = new ArrayList<>();
    }

    public void executar()
    {
        try
        {
            this.servidor = new ServerSocket(5555);
            this.servidor.setReuseAddress(true);
            
            System.out.println("\n***Servidor Iniciado***\n");

            // loop infinito p/ capturar conexoes
            while (true)
            {
                Socket socket_cliente = servidor.accept();

                // mostra em tela a notificacao de conexao
                System.out.println("Nova conexão: " + socket_cliente.getInetAddress().getHostAddress());

                // cria uma nova thread
                ClientHandler thread_cliente = new ClientHandler(socket_cliente, this);

                // adiciona cliente a lista
                listaclientes.add(thread_cliente);

                new Thread(thread_cliente).start();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (this.servidor != null)
            {
                try
                {
                    this.servidor.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public void broadcast(String mensagem, ClientHandler remetente)
    {
        for (ClientHandler cliente : listaclientes)
        {
            if (cliente != remetente)
            {
                cliente.enviamsg(mensagem);
            }
        }
    }

    public void removeusuario(ClientHandler thread_cliente)
    {
        listaclientes.remove(thread_cliente);
        String msgservidor = thread_cliente.nome + " saiu do chat!";
        broadcast(msgservidor, thread_cliente);
    }

    public static void main(String[] args) 
    {
        Server servidor = new Server();
        servidor.executar();
    }
}



