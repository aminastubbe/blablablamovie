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
        BufferedReader dis = new BufferedReader(new InputStreamReader(bis, Charset.forName("ISO-8859-1")));
        Patterns p = new Patterns();
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
        if (filename.equals("actresses.list")) {
            fw = new FileWriter("actresses.csv");
        }
        if (filename.equals("running-times.list")) {
            fw = new FileWriter("running-times.csv");
        }

        while (dis.ready()) {
            if (filename.equals("running-times.list")) {
                String originalLine = "RUNNING TIMES LIST";
                String line = dis.readLine();
                String moviedata = null;
                String overige = null;
                String minutes = null;
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
                    Boolean first = true;
                    for (int i = 0; i <= ss.length - 1; i++) {
                        if (i == 0) {
                            moviedata = ss[i];
                        } else if (!ss[i].isEmpty()) {
                            if (!first) {

                                overige = ss[i];
                            }
                            if (first) {
                                minutes = ss[i];
                                first = false;
                            }
                        }
                    }
                    executeRunningTimeList(fw, moviedata, minutes, overige);
                }

            }
            if (filename.equals("release-dates.list")) {
            }
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
                            String moviedata = line;

                            year = getMatchedData(getMatcher(p.YearPattern(),moviedata));
                            moviedata = moviedata.replaceFirst(p.YearPattern(), "");
                            role = getMatchedData(getMatcher(p.RolePattern(),moviedata));
                            moviedata = moviedata.replaceFirst(p.RolePattern(), "");

                            episode = getMatchedData(getMatcher(p.EpisodePattern(),moviedata));
                            moviedata = moviedata.replaceFirst(p.EpisodePattern(), "");
                            Pattern palternatename = Pattern.compile(p.AlternateNamePattern());
                            Matcher malternatename = palternatename.matcher(moviedata);
                            if (malternatename.find()) {

                                alternatename = (malternatename.group(0)).substring(4, malternatename.group(0).length() - 1);
                            }

                            moviedata = moviedata.replaceFirst(p.AlternateNamePattern(), "");
                            credits = !getMatcher(p.CreditsPattern(), moviedata).find();
                            

                            moviedata = moviedata.replaceFirst(p.CreditsPattern(), "");
                            moviedata = moviedata.replaceAll("\\<.*\\>", "").trim();
                            executeActorList(fw, actor, alternatename, moviedata, year, role, credits, episode);

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

                                    year = getMatchedData(getMatcher(p.YearPattern(), moviedata));
                                    moviedata = moviedata.replaceFirst(p.YearPattern(), "");
                                    role = getMatchedData(getMatcher(p.RolePattern(), moviedata));
                                    moviedata = moviedata.replaceFirst(p.RolePattern(), "");

                                    episode = getMatchedData(getMatcher(p.EpisodePattern(),moviedata));
                                    moviedata = moviedata.replaceFirst(p.EpisodePattern(), "");

                                    Matcher malternatename = getMatcher(p.AlternateNamePattern(),moviedata);
                                    if (malternatename.find()) {

                                        alternatename = (malternatename.group(0)).substring(4, malternatename.group(0).length() - 1);
                                    }

                                    moviedata = moviedata.replaceFirst(p.AlternateNamePattern(), "").trim();

                                    credits = !getMatcher(p.CreditsPattern(), moviedata).find();
                                    moviedata = moviedata.replaceFirst(p.CreditsPattern(), "");
                                    moviedata = moviedata.replaceAll("\\<.*\\>", "").trim();
                                    executeActorList(fw, actor, alternatename, moviedata, year, role, credits, episode);
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
                            String moviedata = line;
                            year = getMatchedData(getMatcher(p.YearPattern(), moviedata));
                            moviedata = moviedata.replaceFirst(p.YearPattern(), "");
                            role = getMatchedData(getMatcher(p.RolePattern(), moviedata));
                            moviedata = moviedata.replaceFirst(p.RolePattern(), "");
                            episode = getMatchedData(getMatcher(p.EpisodePattern(), moviedata));
                            moviedata = moviedata.replaceFirst(p.EpisodePattern(), "");
                            Matcher malternatename = getMatcher(p.AlternateNamePattern(), moviedata);
                            if (malternatename.find()) {

                                alternatename = (malternatename.group(0)).substring(4, malternatename.group(0).length() - 1);
                            }
                            moviedata = moviedata.replaceFirst(p.AlternateNamePattern(), "");
                            credits = !getMatcher(p.CreditsPattern(), moviedata).find();
                            moviedata = moviedata.replaceFirst(p.CreditsPattern(), "");
                            moviedata = moviedata.replaceAll("\\<.*\\>", "").trim();
                            executeActorList(fw, actor, alternatename, moviedata, year, role, credits, episode);

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
                                    String moviedata = line;
                                    year = getMatchedData(getMatcher(p.YearPattern(), moviedata));
                                    moviedata = moviedata.replaceFirst(p.YearPattern(), "");
                                    role = getMatchedData(getMatcher(p.RolePattern(), moviedata));
                                    moviedata = moviedata.replaceFirst(p.RolePattern(), "");
                                    episode = getMatchedData(getMatcher(p.EpisodePattern(), moviedata));
                                    moviedata = moviedata.replaceFirst(p.EpisodePattern(), "");
                                    Matcher malternatename = getMatcher(p.AlternateNamePattern(), moviedata);
                                    if (malternatename.find()) {

                                        alternatename = (malternatename.group(0)).substring(4, malternatename.group(0).length() - 1);
                                    }
                                    moviedata = moviedata.replaceFirst(p.AlternateNamePattern(), "");
                                    credits = !getMatcher(p.CreditsPattern(), moviedata).find();
                                    moviedata = moviedata.replaceFirst(p.CreditsPattern(), "");
                                    moviedata = moviedata.replaceAll("\\<.*\\>", "").trim();
                                    executeActorList(fw, actor, alternatename, moviedata, year, role, credits, episode);
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
                                year = getMatchedData(getMatcher(p.YearPattern(), moviedata));
                                moviedata = moviedata.replaceFirst(p.YearPattern(), "");
                                episode = getMatchedData(getMatcher(p.EpisodePattern(), moviedata));
                                moviedata = moviedata.replaceFirst(p.EpisodePattern(), "").trim();
                            } else {
                                yearSeason = ss[i];
                            }
                        }
                    }
                    executeMovieList(fw, moviedata, yearSeason, year, episode);
                    
                }
            }
        }

        closeConnections(fw, fis, bis, dis);
    }

    private String getMatchedData(Matcher m) {
        if (m.find()) {
            return matchedValue(m);
        } else {
            return null;
        }

    }

    private void closeConnections(FileWriter fw, FileInputStream fis, BufferedInputStream bis, BufferedReader dis) throws IOException {
        fw.flush();
        fw.close();
        fis.close();
        bis.close();
        dis.close();
    }

    private Matcher getMatcher(String pattern, String moviedata) {
        Pattern p = Pattern.compile(pattern);
        return p.matcher(moviedata);
    }

    private String matchedValue(Matcher m) {
        return m.group(0).substring(1, m.group(0).length() - 1);
    }

    private void executeMovieList(FileWriter fw, String moviedata, String yearSeason, String year, String episode) throws IOException {
        fw.append(moviedata);
        fw.append(";");
        fw.append(yearSeason);
        fw.append(";");
        fw.append(year);
        fw.append(";");
        fw.append(episode);
        fw.append("\n");
    }

    private void executeActorList(FileWriter fw, String actor, String alternatename, String moviedata, String year, String role, boolean credits, String episode) throws IOException {
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
        fw.append(String.valueOf(credits));
        fw.append(";");
        fw.append(episode);
        fw.append('\n');
    }

    private void executeRunningTimeList(FileWriter fw, String moviedata, String minutes, String overige) throws IOException{
                        fw.append(moviedata);
                    fw.append(";");
                    fw.append(minutes);
                    fw.append(";");
                    fw.append(overige);
                    fw.append('\n');
    }
}
