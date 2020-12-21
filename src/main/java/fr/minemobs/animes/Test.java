package fr.minemobs.animes;

import java.util.Locale;

public class Test {

    public static void main(String[] args) {
        AnimeSearcherAPI animes = new AnimeSearcherAPI();
        String animeTitle = "Jujutsu kaisen";
        Anime anime = animes.getJSONFromTitle(animeTitle);
        System.out.println(anime.getUrl());
        /*String test = "https://neko-sama.fr/anime/info/161-whistle-vostfr";
        int ep = 5;
        System.out.println(test.replace("info", "episode").replace("-vostfr","-0"  + ep + "-vostfr"));*/
    }

}
