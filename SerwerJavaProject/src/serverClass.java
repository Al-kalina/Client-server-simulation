import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class serverClass extends Thread{
    static int clients = 0;
    private PrintStream strumienWy;
    private BufferedReader strumienWe;
    private Socket gniazdo;
    private String request;

    private String html = "<html>\n\t<body>\n\t\tInzynieria Biomedyczna\n\t</body>\n</html>";//strona do przesłania
    private ArrayList<String> args = new ArrayList(){{add(html);}};//lista argumentów do przesłania

    public serverClass(Socket gniazdo)
    {
        clients += 1;
        this.gniazdo = gniazdo;
    }
    public void run()
    {
            try {
                    strumienWe = new BufferedReader(new InputStreamReader(gniazdo.getInputStream()));
                    request = strumienWe.readLine();
                    strumienWy = new PrintStream(gniazdo.getOutputStream());
                    if(request.equals("GET,/,HTTP/1.0"))
                    {
                        this.args.add("IP serwera: "+String.valueOf(gniazdo.getInetAddress()));
                        this.args.add("Data przesylu: "+(new Date()).toString());
                        for(String arg:args)
                        {
                            strumienWy.println(arg);
                        }
                    }
                    else if (request.equals("GET,/,DATA/1.0"))
                    {
                        strumienWy.println("Data na serwerze: "+(new Date()).toString());
                    }
                    else
                    {
                        strumienWy.println("Nieznana operacja :(");
                    }
                    strumienWy.flush();
            } catch (Exception e) {
                
            }
        clients--;//dekrementuje ilość podłączonych klientów, przez co nowy klient może się połączyć
        System.out.println("Koniec przesyłu!");//
    }//koniec metody run
}
