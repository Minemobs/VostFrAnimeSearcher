package fr.minemobs.animes;

import org.jetbrains.annotations.Nullable;

public class AnimeHtml {

    private final String synop;
    private final String linkOfTheEpisode;
    private final int nbrOfEps;
    private final String urlOfTheCover;
    private final String playerURL;

    public AnimeHtml(String synop, String linkOfTheEpisode, int nbrOfEps, String urlOfTheCover, String playerURL) {
        this.synop = synop;
        this.linkOfTheEpisode = linkOfTheEpisode;
        this.nbrOfEps = nbrOfEps;
        this.urlOfTheCover = urlOfTheCover;
        this.playerURL = playerURL;
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

    @Nullable
    public String getPlayerURL() {
        return playerURL;
    }
}
