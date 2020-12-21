package fr.minemobs.animes;

public class AnimeHtml {

    private Anime anime;
    private String synop;
    private String linkOfTheBasePage;
    private String linkOfTheEpisodeX;
    private int episodeSearched;
    private int nbrOfEps;

    public AnimeHtml(Anime anime, String synop, String linkOfTheBasePage, String linkOfTheEpisodeX, int episodeSearched) {
        this.anime = anime;
        this.synop = synop;
        this.linkOfTheBasePage = linkOfTheBasePage;
        this.linkOfTheEpisodeX = linkOfTheEpisodeX;
        this.episodeSearched = episodeSearched;
        this.nbrOfEps = anime.getNbrOfEpsAsInt();
    }

    public Anime getAnime() {
        return anime;
    }

    public String getSynop() {
        return synop;
    }

    public String getLinkOfTheBasePage() {
        return linkOfTheBasePage;
    }

    public String getLinkOfTheEpisodeX() {
        return linkOfTheEpisodeX;
    }

    public int getEpisodeSearched() {
        return episodeSearched;
    }
}
