import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class serverMainClass {
    public static void main(String ... args)
    {
        ServerSocket serwer;
        Socket gniazdo;
        serverClass watekKlienta = null;
        try {
            serwer = new ServerSocket(1024);
            while(true)
            {
                System.out.println("Czekam na połączenie");
                if(serverClass.clients < 5)//obsługuje do 5 klientów
                {
                    gniazdo = serwer.accept();
                    System.out.println("Jest połączenie na porcie: "+gniazdo.getPort());
                    watekKlienta = new serverClass(gniazdo);
                    watekKlienta.start();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
