import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class UrlVerifier {
    public static void verifyIfLocationLink(String s) {
        FileWriter finalOutput = null;
        try {
            finalOutput= new FileWriter("valori.csv",true);
            String adresaDeReferinta = "https://operationworld.org";
            String[] stringarray = s.split(" ");
            for (int i = 0; i < stringarray.length; i++) {
                if (stringarray[i].contains("href=\"/locations/")) {
                    try {
                        String referintaCatreTara = stringarray[i].substring(stringarray[i].indexOf("/locations/"),stringarray[i].lastIndexOf("\">"));
                        String adresaTarii = adresaDeReferinta + referintaCatreTara;
                        String tara = referintaCatreTara.substring(referintaCatreTara.lastIndexOf('/') + 1);
                        finalOutput.write(tara+" ");
                        URL addres = new URL(adresaTarii);
                        Scanner scan = new Scanner(addres.openStream());
                        FileWriter writer = new FileWriter("fisier_tara.txt");
                        while (scan.hasNextLine()) {
                            writer.append(scan.nextLine() + "\n");
                        }
                        writer.close(); // Close the FileWriter after use
                        BufferedReader reader = new BufferedReader(new FileReader("fisier_tara.txt"));
                        String line=reader.readLine();
                        while(line!=null)
                        {
                            String[] stringarray2 = line.split("\t");
                            for(int j=0;j<stringarray2.length;j++)
                            {
                                if(stringarray2[j].contains("Population:"))
                                {   
                                    System.out.println(stringarray2[j]);
                                    line=reader.readLine();
                                    String population = line.substring(line.indexOf(">")+1, line.lastIndexOf("<"));
                                    System.out.println(population);  
                                                                
                                }
                            line=reader.readLine();
                            }
                        }  
                        reader.close(); // Close the BufferedReader after use
                        scan.close();
                        
                    } catch (MalformedURLException e) {
                        // Handle MalformedURLException
                        e.printStackTrace();
                    } catch (IOException e) {
                        // Handle IOException
                        e.printStackTrace();
                    }
                }
            }
            finalOutput.close(); // Close the FileWriter after use
        } catch (IOException e) {
            // Handle IOException
            e.printStackTrace();
        }
    }
}