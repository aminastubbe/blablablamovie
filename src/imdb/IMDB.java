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
/**
 *
 * @author Toshiba
 */
public class IMDB {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
 
    public static void main(String[] args) throws IOException, FileNotFoundException, SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String filename1 = "movies.list";
        String filename2 = "actors.list";
        String filename3="actresses.list";
        
        readFile rf = new readFile();
        File file= new File("C:\\Users\\Toshiba\\Desktop\\imdb\\"+filename1);
        rf.readFile(file,filename1 );
        file= new File("C:\\Users\\Toshiba\\Desktop\\imdb\\"+filename2);
        rf.readFile(file,filename2 );
        file= new File("C:\\Users\\Toshiba\\Desktop\\imdb\\"+filename3);
        rf.readFile(file,filename3 );
        
        
        // TODO code application logic here
    }
   
}
