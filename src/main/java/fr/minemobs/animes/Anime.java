package fr.minemobs.animes;

import java.util.Arrays;

public class Anime {

    private String title;
    private String title_english;
    private String title_romanji;
    private int id;
    private String other;
    private String[] genres;
    private String url;
    private String nbrOfEps;
    private String type;

    public Anime(String title, String title_english, String title_romanji, int id, String other, String[] genres, String url, String nbrOfEps,
                 String type) {
        this.title = title;
        this.title_english = title_english;
        this.title_romanji = title_romanji;
        this.id = id;
        this.other = other;
        this.genres = genres;
        this.url = url;
        this.nbrOfEps = nbrOfEps;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getNbrOfEps() {
        return nbrOfEps;
    }

    public int getNbrOfEpsAsInt(){
        String epsString = nbrOfEps.replace(" ", "").replace("Eps", "");
        int episodes = Integer.getInteger(epsString);
        return episodes;
    }

    public String getUrl() {
        return "https://neko-sama.fr" + url;
    }

    public String getTitle() {
        return title;
    }

    public String getTitle_english() {
        return title_english;
    }

    public String getTitle_romanji() {
        return title_romanji;
    }

    public int getId() {
        return id;
    }

    public String getOther() {
        return other;
    }

    public String[] getGenres() {
        return genres;
    }

    @Override
    public String toString() {
        return "Anime{" +
                "title='" + title + '\'' +
                ", title_english='" + title_english + '\'' +
                ", title_romanji='" + title_romanji + '\'' +
                ", id=" + id +
                ", other='" + other + '\'' +
                ", genres=" + Arrays.toString(genres) +
                ", url='" + url + '\'' +
                ", nbrOfEps='" + nbrOfEps + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
