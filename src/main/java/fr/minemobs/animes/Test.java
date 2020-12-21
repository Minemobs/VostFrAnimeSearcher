package fr.minemobs.animes;

import java.io.IOException;
import java.util.Locale;

public class Test {

    public static void main(String[] args) {
        AnimeSearcherAPI animes = new AnimeSearcherAPI();
        String animeTitle = "Jujutsu kaisen";
        Anime anime = animes.getJSONFromTitle(animeTitle);
        //System.out.println(anime.getUrl());
        try {
            AnimeHtml animeHtml = animes.getHtmlPageOfTheAnime(anime, 12);
            System.out.println(animeHtml.getLinkOfTheEpisode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
