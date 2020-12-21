package fr.minemobs.animes;

public class Test {

    public static void main(String[] args) throws Exception {
        AnimeSearcherAPI animes = new AnimeSearcherAPI();
        Anime anime = animes.getJSONFromTitle(AnimeSearcherAPI.TitleType.TITLE, "Noblesse");
        System.out.println(anime.getUrl());
    }

}
