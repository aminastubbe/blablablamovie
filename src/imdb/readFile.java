/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imdb;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Toshiba
 */
public class readFile {

    public readFile() {
    }

    public void read(BufferedReader dis) throws IOException {
        while (dis.ready()) {
            String line = dis.readLine();
            System.out.println(line);
        }
    }

    public void readFile(File f, String filename) throws FileNotFoundException, IOException, SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        FileInputStream fis = new FileInputStream(f);
        BufferedInputStream bis = new BufferedInputStream(fis);
        BufferedReader dis = new BufferedReader(new InputStreamReader(bis, Charset.forName("UTF-8")));

        String lastLine = null;
        Boolean equal = false;
        Boolean readnext = false;
        String actor = null;
        FileWriter fw = null;
        int counter = 0;
        if (filename.equals("actors.list")) {
            fw = new FileWriter("actors.csv");
        }
        if (filename.equals("movies.list")) {
            fw = new FileWriter("movies.csv");
        }
        if(filename.equals("actresses.list")){
            fw = new FileWriter("actresses.csv");
        }
        while (dis.ready()) {
if (filename.equals("actresses.list")) {

                String originalLine = "THE ACTRESSES LIST";
                String line = dis.readLine();
                if (!equal) {
                    if (line.equals(originalLine)) {
                        equal = true;
                        lastLine = line;
                    }
                } else {
                    counter++;
                    if (!readnext) {
                        readnext = line.matches("([=]{" + lastLine.length() + "})");
                    } else if (!line.isEmpty() && counter > 4) {
                        if (line.matches("^[\\s].*")) {
                            String movie = null;
                            String year = null;
                            String episode = null;
                            String role = null;
                            String alternatename = null;
                            Boolean credits = false;
                            String moviedata = line.trim();
                            String pattern = "\\(\\d{4}\\)|\\(\\d{4}\\/.*\\)";
                            Pattern pyear = Pattern.compile(pattern);
                            Matcher myear = pyear.matcher(moviedata);
                            if (myear.find()) {
                                year = (myear.group(0)).substring(1, 5);
                            }
                            moviedata = moviedata.replaceFirst(pattern, "").trim();
                            pattern = "\\[.*\\]";
                            Pattern prole = Pattern.compile(pattern);
                            Matcher mrole = prole.matcher(moviedata);
                            if (mrole.find()) {

                                role = (mrole.group(0)).substring(1, mrole.group(0).length() - 1);
                            }
                            moviedata = moviedata.replaceFirst(pattern, "").trim();
                            pattern = "\\{.*\\}";
                            Pattern pepisode = Pattern.compile(pattern);
                            Matcher mepisode = pepisode.matcher(moviedata);
                            if (mepisode.find()) {

                                episode = (mepisode.group(0)).substring(1, mepisode.group(0).length() - 1);
                            }
                            moviedata = moviedata.replaceFirst(pattern, "").trim();
                            pattern = "\\(as .*\\)";
                            Pattern palternatename = Pattern.compile(pattern);
                            Matcher malternatename = palternatename.matcher(moviedata);
                            if (malternatename.find()) {

                                alternatename = (malternatename.group(0)).substring(4, malternatename.group(0).length() - 1);
                            }

                            moviedata = moviedata.replaceFirst(pattern, "").trim();
                            pattern = "\\(uncredited\\)";
                            Pattern pcredit = Pattern.compile(pattern);
                            Matcher mcredit = pcredit.matcher(moviedata);
                            credits = !mcredit.find();
                            moviedata = moviedata.replaceFirst(pattern, "").trim();
                            moviedata = moviedata.replaceAll("\\<.*\\>", "").trim();
                            fw.append(actor);
                            fw.append(';');
                            fw.append(alternatename);
                            fw.append(';');
                            fw.append(moviedata);
                            fw.append(";");
                            fw.append(year);
                            fw.append(";");
                            fw.append(role);
                            fw.append(";");
                            fw.append(credits.toString());
                            fw.append(";");
                            fw.append(episode);
                            fw.append('\n');

                        } else {
                            String[] a = line.split("\t");
                            for (int i = 0; i <= a.length - 1; i++) {
                                if (i == 0) {
                                    actor = a[i].trim();
                                } else if (!a[i].isEmpty()) {
                                    String movie = null;
                                    String year = null;
                                    String episode = null;
                                    String role = null;
                                    String alternatename = null;
                                    Boolean credits = false;
                                    String moviedata = line.trim();
                                    String pattern = "\\(\\d{4}\\)|\\(\\d{4}\\/.*\\)";
                                    Pattern pyear = Pattern.compile(pattern);
                                    Matcher myear = pyear.matcher(moviedata);
                                    if (myear.find()) {
                                        year = (myear.group(0)).substring(1, 5);
                                    }
                                    moviedata = moviedata.replaceFirst(pattern, "").trim();
                                    pattern = "\\[.*\\]";
                                    Pattern prole = Pattern.compile(pattern);
                                    Matcher mrole = prole.matcher(moviedata);
                                    if (mrole.find()) {

                                        role = (mrole.group(0)).substring(1, mrole.group(0).length() - 1);
                                    }
                                    moviedata = moviedata.replaceFirst(pattern, "").trim();
                                    pattern = "\\{.*\\}";
                                    Pattern pepisode = Pattern.compile(pattern);
                                    Matcher mepisode = pepisode.matcher(moviedata);
                                    if (mepisode.find()) {

                                        episode = (mepisode.group(0)).substring(1, mepisode.group(0).length() - 1);
                                    }
                                    moviedata = moviedata.replaceFirst(pattern, "").trim();
                                    pattern = "\\(as .*\\)";
                                    Pattern palternatename = Pattern.compile(pattern);
                                    Matcher malternatename = palternatename.matcher(moviedata);
                                    if (malternatename.find()) {

                                        alternatename = (malternatename.group(0)).substring(4, malternatename.group(0).length() - 1);
                                    }

                                    moviedata = moviedata.replaceFirst(pattern, "").trim();
                                    pattern = "\\(uncredited\\)";
                                    Pattern pcredit = Pattern.compile(pattern);
                                    Matcher mcredit = pcredit.matcher(moviedata);
                                    credits = !mcredit.find();
                                    moviedata = moviedata.replaceFirst(pattern, "").trim();
                                    moviedata = moviedata.replaceAll("\\<.*\\>", "").trim();
                                    fw.append(actor);
                                    fw.append(';');
                                    fw.append(alternatename);
                                    fw.append(';');
                                    fw.append(moviedata);
                                    fw.append(";");
                                    fw.append(year);
                                    fw.append(";");
                                    fw.append(role);
                                    fw.append(";");
                                    fw.append(credits.toString());
                                    fw.append(";");
                                    fw.append(episode);
                                    fw.append('\n');
                                }
                            }
                        }
                    }
                }
            }
            if (filename.equals("actors.list")) {

                String originalLine = "THE ACTORS LIST";
                String line = dis.readLine();
                if (!equal) {
                    if (line.equals(originalLine)) {
                        equal = true;
                        lastLine = line;
                    }
                } else {
                    counter++;
                    if (!readnext) {
                        readnext = line.matches("([=]{" + lastLine.length() + "})");
                    } else if (!line.isEmpty() && counter > 4) {
                        if (line.matches("^[\\s].*")) {
                            String movie = null;
                            String year = null;
                            String episode = null;
                            String role = null;
                            String alternatename = null;
                            Boolean credits = false;
                            String moviedata = line.trim();
                            String pattern = "\\(\\d{4}\\)|\\(\\d{4}\\/.*\\)";
                            Pattern pyear = Pattern.compile(pattern);
                            Matcher myear = pyear.matcher(moviedata);
                            if (myear.find()) {
                                year = (myear.group(0)).substring(1, 5);
                            }
                            moviedata = moviedata.replaceFirst(pattern, "").trim();
                            pattern = "\\[.*\\]";
                            Pattern prole = Pattern.compile(pattern);
                            Matcher mrole = prole.matcher(moviedata);
                            if (mrole.find()) {

                                role = (mrole.group(0)).substring(1, mrole.group(0).length() - 1);
                            }
                            moviedata = moviedata.replaceFirst(pattern, "").trim();
                            pattern = "\\{.*\\}";
                            Pattern pepisode = Pattern.compile(pattern);
                            Matcher mepisode = pepisode.matcher(moviedata);
                            if (mepisode.find()) {

                                episode = (mepisode.group(0)).substring(1, mepisode.group(0).length() - 1);
                            }
                            moviedata = moviedata.replaceFirst(pattern, "").trim();
                            pattern = "\\(as .*\\)";
                            Pattern palternatename = Pattern.compile(pattern);
                            Matcher malternatename = palternatename.matcher(moviedata);
                            if (malternatename.find()) {

                                alternatename = (malternatename.group(0)).substring(4, malternatename.group(0).length() - 1);
                            }

                            moviedata = moviedata.replaceFirst(pattern, "").trim();
                            pattern = "\\(uncredited\\)";
                            Pattern pcredit = Pattern.compile(pattern);
                            Matcher mcredit = pcredit.matcher(moviedata);
                            credits = !mcredit.find();
                            moviedata = moviedata.replaceFirst(pattern, "").trim();
                            moviedata = moviedata.replaceAll("\\<.*\\>", "").trim();
                            fw.append(actor);
                            fw.append(';');
                            fw.append(alternatename);
                            fw.append(';');
                            fw.append(moviedata);
                            fw.append(";");
                            fw.append(year);
                            fw.append(";");
                            fw.append(role);
                            fw.append(";");
                            fw.append(credits.toString());
                            fw.append(";");
                            fw.append(episode);
                            fw.append('\n');

                        } else {
                            String[] a = line.split("\t");
                            for (int i = 0; i <= a.length - 1; i++) {
                                if (i == 0) {
                                    actor = a[i].trim();
                                } else if (!a[i].isEmpty()) {
                                    String movie = null;
                                    String year = null;
                                    String episode = null;
                                    String role = null;
                                    String alternatename = null;
                                    Boolean credits = false;
                                    String moviedata = line.trim();
                                    String pattern = "\\(\\d{4}\\)|\\(\\d{4}\\/.*\\)";
                                    Matcher myear = getMatcher(pattern, moviedata);
                                    if (myear.find()) {
                                        year = (myear.group(0)).substring(1, 5);
                                    }
                                    moviedata = moviedata.replaceFirst(pattern, "").trim();
                                    pattern = "\\[.*\\]";
                                    Pattern prole = Pattern.compile(pattern);
                                    Matcher mrole = prole.matcher(moviedata);
                                    if (mrole.find()) {

                                        role = (mrole.group(0)).substring(1, mrole.group(0).length() - 1);
                                    }
                                    moviedata = moviedata.replaceFirst(pattern, "").trim();
                                    pattern = "\\{.*\\}";
                                    Pattern pepisode = Pattern.compile(pattern);
                                    Matcher mepisode = pepisode.matcher(moviedata);
                                    if (mepisode.find()) {

                                        episode = (mepisode.group(0)).substring(1, mepisode.group(0).length() - 1);
                                    }
                                    moviedata = moviedata.replaceFirst(pattern, "").trim();
                                    pattern = "\\(as .*\\)";
                                    Pattern palternatename = Pattern.compile(pattern);
                                    Matcher malternatename = palternatename.matcher(moviedata);
                                    if (malternatename.find()) {

                                        alternatename = (malternatename.group(0)).substring(4, malternatename.group(0).length() - 1);
                                    }

                                    moviedata = moviedata.replaceFirst(pattern, "").trim();
                                    pattern = "\\(uncredited\\)";
                                    Pattern pcredit = Pattern.compile(pattern);
                                    Matcher mcredit = pcredit.matcher(moviedata);
                                    credits = !mcredit.find();
                                    moviedata = moviedata.replaceFirst(pattern, "").trim();
                                    moviedata = moviedata.replaceAll("\\<.*\\>", "").trim();
                                    fw.append(actor);
                                    fw.append(';');
                                    fw.append(alternatename);
                                    fw.append(';');
                                    fw.append(moviedata);
                                    fw.append(";");
                                    fw.append(year);
                                    fw.append(";");
                                    fw.append(role);
                                    fw.append(";");
                                    fw.append(credits.toString());
                                    fw.append(";");
                                    fw.append(episode);
                                    fw.append('\n');
                                }
                            }
                        }
                    }
                }
            }
            if (filename.equals("movies.list")) {
                String originalLine = "MOVIES LIST";
                String line = dis.readLine();
                String moviedata = null;
                String yearSeason = null;
                String year = null;
                String pattern = null;
                String episode = null;
                if (!equal) {
                    if (line.equals(originalLine)) {
                        equal = true;
                        lastLine = line;
                    }
                } else if (!readnext) {
                    String regex = "([=]{" + lastLine.length() + "})";
                    readnext = line.matches(regex);
                } else if (!line.isEmpty()) {
                    String[] ss = line.split("\t");
                    for (int i = 0; i <= ss.length - 1; i++) {
                        if (!ss[i].isEmpty()) {
                            if (i == 0) {
                                moviedata = ss[i];
                                Matcher myear = getMatcher(getYearPattern(), moviedata);
                                if (myear.find()) {
                                    year = matchedValue(myear);
                                }
                                moviedata = moviedata.replaceFirst(getYearPattern(), "").trim();
                                Matcher mepisode = getMatcher(getEpisodePattern(), moviedata);
                                if (mepisode.find()) {
                                    episode = matchedValue(mepisode);
                                }
                                moviedata = moviedata.replaceFirst(getEpisodePattern(), "").trim();
                            } else {
                                yearSeason = ss[i];
                            }
                        }
                    }
                    fw.append(moviedata);
                    fw.append(";");
                    fw.append(yearSeason);
                    fw.append(";");
                    fw.append(year);
                    fw.append(";");
                    fw.append(episode);
                    fw.append("\n");
                }
            }
        }
        closeConnections(fw, fis, bis, dis);
    }
    private void closeConnections(FileWriter fw, FileInputStream fis, BufferedInputStream bis,BufferedReader dis ) throws IOException{      
        fw.flush();
        fw.close();
        fis.close();
        bis.close();
        dis.close();
    }
    private String getYearPattern(){
        return "\\(\\d{4}\\)|\\(\\d{4}\\/.*\\)";
    }
    private String getEpisodePattern(){
        return  "\\{.*\\}";
    }
    
    private Matcher getMatcher(String pattern, String moviedata ){
        Pattern p = Pattern.compile(pattern);
        return p.matcher(moviedata);
    }

    private String matchedValue(Matcher m)    {
        return m.group(0).substring(1, m.group(0).length()-1);
    }
}
