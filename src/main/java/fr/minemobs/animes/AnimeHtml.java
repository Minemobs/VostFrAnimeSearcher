package fr.minemobs.animes;

public class AnimeHtml {

    private final String synop;
    private final String linkOfTheEpisode;
    private final int nbrOfEps;
    private final String urlOfTheCover;

    public AnimeHtml(String synop, String linkOfTheEpisode, int nbrOfEps, String urlOfTheCover) {
        this.synop = synop;
        this.linkOfTheEpisode = linkOfTheEpisode;
        this.nbrOfEps = nbrOfEps;
        this.urlOfTheCover = urlOfTheCover;
    }

    public String getSynop() {
        return synop;
    }

    public String getLinkOfTheEpisode() {
        return linkOfTheEpisode;
    }

    public int getNbrOfEps() {
        return nbrOfEps;
    }

    public String getUrlOfTheCover() {
        return urlOfTheCover;
    }
}
