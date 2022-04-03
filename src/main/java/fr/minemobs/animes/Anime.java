package fr.minemobs.animes;

import com.google.gson.annotations.SerializedName;
import fr.minemobs.animes.utils.StringUtils;

import java.util.Arrays;

public class Anime {

    private final String title;
    @SerializedName("title_english") private final String titleEnglish;
    @SerializedName("title_romanji") private final String titleRomanji;
    private final int id;
    private final String others;
    private final String[] genres;
    private final String url;
    @SerializedName("nb_eps") private final String nbrOfEps;
    private final String type;

    /**
     * You should not use this constructor.
     */
    public Anime() {
        this.title = "";
        this.titleEnglish = "";
        this.titleRomanji = null;
        this.id = 0;
        this.others = "";
        this.genres = null;
        this.url = null;
        this.nbrOfEps = "0 Eps";
        this.type = null;
    }

    public boolean containsTitle(String title) {
        return StringUtils.containsIgnoreCase(title, this.title, this.titleEnglish, this.titleRomanji, this.others);
    }

    public boolean equalsTitle(String title) {
        return StringUtils.equalsIgnoreCase(title, this.title, this.titleEnglish, this.titleRomanji, this.others);
    }

    public String getType() {
        return type;
    }

    public String getNbrOfEps() {
        return nbrOfEps;
    }

    public int getNbrOfEpsAsInt(){
        String nbr = nbrOfEps.replace(" Eps", "");
        return StringUtils.isNumeric(nbr) ? Integer.parseInt(nbr) : 1;
    }

    public String getUrl() {
        return "https://neko-sama.fr" + url;
    }

    public String getTitle() {
        return title;
    }

    public String getTitleEnglish() {
        return titleEnglish;
    }

    public String getTitleRomanji() {
        return titleRomanji;
    }

    public int getId() {
        return id;
    }

    public String getOthers() {
        return others;
    }

    public String[] getGenres() {
        return genres;
    }

    @Override
    public String toString() {
        return "Anime{" +
                "title='" + title + '\'' +
                ", title_english='" + titleEnglish + '\'' +
                ", title_romanji='" + titleRomanji + '\'' +
                ", id=" + id +
                ", others='" + others + '\'' +
                ", genres=" + Arrays.toString(genres) +
                ", url='" + url + '\'' +
                ", nbrOfEps='" + nbrOfEps + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
