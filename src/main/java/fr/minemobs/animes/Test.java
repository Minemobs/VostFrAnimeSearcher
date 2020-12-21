package fr.minemobs.animes;

import java.io.IOException;
import java.util.Locale;

public class Test {

    public static void main(String[] args) {
        //Instantiate the class AnimeSearcherAPI
        AnimeSearcherAPI animes = new AnimeSearcherAPI();
        //Set the name of the anime
        String animeTitle = "Jujutsu kaisen";
        //Create the variable anime which get the result of the function getJSONFromTitle()
        Anime anime = animes.getJSONFromTitle(animeTitle);
        try {
            //Create the variable animeHtml and
            //Specify the episode searched
            AnimeHtml animeHtml = animes.getHtmlPageOfTheAnime(anime, 12);
            //Print the link of the episode
            System.out.println(animeHtml.getLinkOfTheEpisode());
            //Print the synopsis of the anime
            System.out.println(animeHtml.getSynop());
        } catch (Exception e) {
            //If there is an exception, print the error
            e.printStackTrace();
        }
    }

}
