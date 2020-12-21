package fr.minemobs.animes;

public class Test {

    public static void main(String[] args) {
        try {
            AnimeSearcherAPI animes = new AnimeSearcherAPI();
            String animeTitle = "Jujutsu Kaisen";
            Anime anime = animes.getJSONFromTitle(AnimeSearcherAPI.TitleType.TITLE, animeTitle);
            System.out.println(anime.getUrl());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
