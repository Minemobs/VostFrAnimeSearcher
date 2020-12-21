package fr.minemobs.animes;

public class AnimeHtml {

    private String synop;
    private String linkOfTheEpisode;
    private int nbrOfEps;

    public AnimeHtml(String synop, String linkOfTheEpisode, int nbrOfEps) {
        this.synop = synop;
        this.linkOfTheEpisode = linkOfTheEpisode;
        this.nbrOfEps = nbrOfEps;
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
}
