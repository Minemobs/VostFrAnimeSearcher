package fr.minemobs.animes;

import java.util.Arrays;
import java.util.List;

public class AnimeList {

    private String title;
    private String title_english;
    private String title_romanji;
    private int id;
    private String other;
    private String[] genres;
    private String url;
    private List<Anime> animes;

    public AnimeList(String title, String title_english, String title_romanji, int id, String other, String[] genres, String url, List<Anime> animes) {
        this.title = title;
        this.title_english = title_english;
        this.title_romanji = title_romanji;
        this.id = id;
        this.other = other;
        this.genres = genres;
        this.url = url;
        this.animes = animes;
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

    public String getUrl() {
        return url;
    }

    public List<Anime> getAnimes() {
        return animes;
    }

    @Override
    public String toString() {
        return "AnimeList{" +
                "title='" + title + '\'' +
                ", title_english='" + title_english + '\'' +
                ", title_romanji='" + title_romanji + '\'' +
                ", id=" + id +
                ", other='" + other + '\'' +
                ", genres=" + Arrays.toString(genres) +
                ", url='" + url + '\'' +
                ", animes=" + animes +
                '}';
    }
}
