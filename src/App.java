import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
public class App 
{
    public static void main(String[] args)  
    {
        try {
            URL addres = new URL("https://operationworld.org/locations/europe/");
            Scanner scan = new Scanner(addres.openStream());
            FileWriter htmlFile = new FileWriter("fisierhtml.txt");
            BufferedReader htmlFileReader = new BufferedReader(new FileReader("fisierhtml.txt"));
            while(scan.hasNextLine())
            {
                htmlFile.append(scan.nextLine()+"\n");
            }
            while((htmlFileReader.readLine())!=null)
            {
                UrlVerifier.verifyIfLocationLink(htmlFileReader.readLine());
            }
            scan.close();
            
            htmlFile.close();
            htmlFileReader.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
