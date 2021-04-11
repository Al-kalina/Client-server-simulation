using System;
using System.IO;
using System.Net.Sockets;
using System.Text;
using System.Threading;

namespace KlientCsharp
{
    class Program
    {
        static void Main(string[] args)
        {
            string host = "localhost";
            int port = 1024;
            byte[] bufor;
            int iLength;
            string receive;
                try
                {
                    Console.WriteLine("Dokonaj wyboru : http/data");
                    string userRequst = Console.ReadLine().ToUpper();
                    TcpClient tc = new TcpClient(host, port);
                    NetworkStream ns = tc.GetStream();
                    StreamWriter sw = new StreamWriter(ns);                    
                    sw.WriteLine("GET,/,{0}/1.0\n", userRequst);
                    sw.Flush();
                    ns.Flush();
                while ((bufor = new byte[tc.ReceiveBufferSize]) != null)
                {
                    iLength = ns.Read(bufor, 0, tc.ReceiveBufferSize);
                    receive = Encoding.ASCII.GetString(bufor);
                    Console.WriteLine(receive.Substring(0, iLength));
                }
                ns.Close();
                sw.Close();
                tc.Close();
            }
                catch
                {
                    Console.WriteLine("Pojawił się wyjątek");
                }
        }
    }
}
