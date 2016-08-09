/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imdb;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Toshiba
 */
public class CSVDifference {
    CSVDifference(){};
    public void getDifference(String newFile, String oldFile, String differenceFile, int numberOfOriginalCSVNodes) throws FileNotFoundException, IOException{
       // String path="C:\\Users\\Toshiba\\Desktop\\";
       // String newFile="file1.csv";
       // String oldFile="file2.csv";
       // String differenceFile="p3lang.csv";

       
        int lengthNewCSVFile=numberOfOriginalCSVNodes;
        ArrayList<String> al1=new ArrayList<String>();
        ArrayList<String> al2=new ArrayList<String>();
        //ArrayList al3=new ArrayList();
        
        BufferedReader CSVFile1 = new BufferedReader(new FileReader(newFile));

        while (CSVFile1.ready())
        {
            String dataRow1 = CSVFile1.readLine();
            String[] dataArray1 = dataRow1.split(";");
            for (String item1:dataArray1)
            { 
                
               al1.add(item1);
            }

        }
        
         CSVFile1.close();
         
        BufferedReader CSVFile2 = new BufferedReader(new FileReader(oldFile));

        while (CSVFile2.ready())
        {
            String dataRow2 = CSVFile2.readLine();
            String[] dataArray2 = dataRow2.split(";");
            for (int i =0; i < 3 ; i++)
            { 
               String item2= dataArray2[i];
               al2.add(item2);
               
               
            }

        }
         CSVFile2.close();
         
         for(String bs:al2)
         {
             al1.remove(bs);
         }
         

         System.out.println(al1.toString());
         try
            {
                FileWriter writer=new FileWriter(differenceFile);
                int index = 0;
                
                System.out.println(al1.size()+lengthNewCSVFile-1);
                for(int column = 0; column <al1.size()-1; column=column+lengthNewCSVFile ){

                    while(index < lengthNewCSVFile-1){
                        writer.append(al1.get((column+index)));
                        writer.append(";");
                        index++;
                    }
                    if(index == lengthNewCSVFile-1){
                        writer.append(al1.get(column+index));
                        index ++;
                    }
                    if (index > lengthNewCSVFile-1){
                        writer.append("\n");
                    }
                    
                    index=index%lengthNewCSVFile;
                }

                writer.flush();
                writer.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
    }
    
    
    
    
    
    
}
