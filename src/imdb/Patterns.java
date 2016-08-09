/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imdb;

/**
 *
 * @author Toshiba
 */
public class Patterns {
    Patterns(){}
    
    public String YearPattern() {
        return "\\(\\d{4}\\)|\\(\\d{4}\\/.*\\)";
    }

    public String EpisodePattern() {
        return "\\{.*\\}";
    }

    public String CreditsPattern(){
        return "\\(uncredited\\)";
    }
    public String AlternateNamePattern(){
        return "\\(as .*\\)";
    }
    
    public String RolePattern(){
        return "\\[.*\\]";
    }
    
}
