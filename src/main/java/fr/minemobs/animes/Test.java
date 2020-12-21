package fr.minemobs.animes;

import java.util.Locale;

public class Test {

    public static void main(String[] args) {
        AnimeSearcherAPI animes = new AnimeSearcherAPI();
        String animeTitle = "Dogeza de Tanondemita";
        Anime anime = animes.getJSONFromTitle(animeTitle);
        System.out.println(anime.getTitle());

    }

}
