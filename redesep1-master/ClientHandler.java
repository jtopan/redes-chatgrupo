import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable
    {
        public Socket socket;
        public Server servidor;
        public String nome;

        public PrintWriter out;
        public BufferedReader in;
        
        // socket do cliente
        public ClientHandler(Socket socket, Server servidor)
        {
            this.socket = socket;
            this.servidor = servidor;
        }

        // funcao para escrita 
        public void enviamsg(String mensagem)
        {
            out.println(mensagem);
        }

        public void run()
        {
            try
            {
                // captura in e output stream
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                // 4. recebe resposta
                this.nome = in.readLine();
                String msgservidor = "\n" + nome + " entrou no chat!";
                servidor.broadcast(msgservidor, this); 

                String msgcliente;
                Boolean sair = false;
                while (!sair)
                {
                    msgcliente = in.readLine();
                    if (msgcliente != null)
                    {
                        if (msgcliente.equals("bye"))
                        {
                            sair = true;
                            break;
                        }
                        msgservidor = "[" + nome + "]: " + msgcliente;
                        servidor.broadcast(msgservidor, this);
                    }
                }

                servidor.removeusuario(this);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    if (out != null)
                    {
                        out.close();
                    }
                    if (in != null)
                    {
                        in.close();
                        socket.close();
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }    