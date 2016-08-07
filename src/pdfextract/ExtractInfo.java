/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfextract;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hany
 */
public class ExtractInfo {

    public void fileInfo() throws MalformedURLException, IOException {
      //  Map<String, String> map = new HashMap<String, String>();

        String line;
        String lineOneData, lineTwoData, lineThreeData, type, documentNumber, dateOfBirth, dateOfExpiry, issuer,
                nationality, lastName, firstName, discretionary1, discretionary2, gender;

        String[] stringArrayVar = {"lineOneData", "lineTwoData", "lineThreeData", "type", "documentNumber", "dateOfBirth", "dateOfExpiry", "issuer",
            "nationality", "lastName", "firstName", "discretionary1", "discretionary2", "gender"};
        String[] stringArrayNames = {"Line One Data ", "Line Two Data ", "Line Three Data ", "Type ", "Document Number ", "Date of Birth ", "Date of Expiry ", "Issuer ", "Nationality ", "Last Names ", "First Names ", "Discretionary 1 ", "Discretionary 2 ", "Gender "

        };
        try {
            InputStream fis = new FileInputStream("test.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            //line = br.readLine();
            int i = 0;
            int n = 14;
            while ((line = br.readLine()) != null) {
                // Deal with the line

                for (i = 0; i < stringArrayVar.length; i++) {
                    
                    if (line.contains(stringArrayNames[i])) {
                    stringArrayVar [i]= line.replace(stringArrayNames[i], "");
                    System.out.println(stringArrayNames[i]+" "+ stringArrayVar [i]);
                    }
                    
                }
 
            }
            
           
               /**
                * here i will call the function sendDataToServer
                * it take 3 parameter 
                * @param yourUrl server URL
                * @param colNames Names of Col in database
                * @param postArray variable will be send 
                */
               
               
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }

    }

    /**
     *
     * @param yourUrl
     * @param colNames
     * @param postArray
     * @throws MalformedURLException
     * @throws IOException
     */
    public void sendDataToServer(String yourUrl, String[] colNames, String[] postArray) throws MalformedURLException, IOException {
        URL url = new URL(yourUrl);
        URLConnection con = url.openConnection();
        // activate the output
        con.setDoOutput(true);
        PrintStream ps = new PrintStream(con.getOutputStream());
        // send your parameters to your site
        for (int i = 0; i <= postArray.length; i++) {

            ps.print("&" + colNames[i] + "=" + postArray[i] + "");
        }

        // we have to get the input stream in order to actually send the request
        con.getInputStream();

        // close the print stream
        ps.close();
    }

    public static void main(String[] args) throws MalformedURLException, IOException {
        ExtractInfo up = new ExtractInfo();
        up.fileInfo();
    }
}
