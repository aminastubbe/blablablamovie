/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imdb;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author Toshiba
 */
public class IMDB {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public IMDB(){
    
    }
    public void publicUpdataCSVFiles() throws IOException, FileNotFoundException, SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException{
        executeFile("movies.list");
        //read file 2
        executeFile("actors.list");
        //read file 3
        executeFile("actresses.list");
        //read file 4
        executeFile("running-times.list");
  //      CSVDifference cd = new CSVDifference();
    //   cd.getDifference();
    }
   
    public static void executeFile(String filename) throws IOException, FileNotFoundException, SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException{
         File file=new File("C:\\Users\\Toshiba\\Desktop\\imdb\\"+filename);
         readFile rf = new readFile();
        rf.readFile(file,filename);
        System.out.println("File Done: " + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));

    }
   
}
