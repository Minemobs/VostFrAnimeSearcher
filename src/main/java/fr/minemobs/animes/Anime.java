package fr.minemobs.animes;

import com.google.gson.annotations.SerializedName;

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

    private Anime() {
        this.title = "";
        this.titleEnglish = "";
        this.titleRomanji = null;
        this.id = 0;
        this.others = "";
        this.genres = new String[0];
        this.url = "";
        this.nbrOfEps = "";
        this.type = "";
    }

    public boolean containsTitle(String title) {
        return this.title.toLowerCase().contains(title.toLowerCase()) ||
                this.titleEnglish.toLowerCase().contains(title.toLowerCase()) ||
                (this.titleRomanji != null && this.titleRomanji.toLowerCase().contains(title.toLowerCase())) ||
                this.others.toLowerCase().contains(title.toLowerCase());
    }

    public boolean equalsTitle(String title) {
        return this.title.equalsIgnoreCase(title) || this.titleEnglish.equalsIgnoreCase(title) ||
                (this.titleRomanji != null && this.titleRomanji.equalsIgnoreCase(title)) ||
                Arrays.stream(this.others.split(",")).anyMatch(t -> t.equalsIgnoreCase(title));
    }

    public String getType() {
        return type;
    }

    public String getNbrOfEps() {
        return nbrOfEps;
    }

    public int getNbrOfEpsAsInt(){
        return Integer.getInteger(nbrOfEps.replace(" ", "").replace("Eps", ""));
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
