package fr.minemobs.animes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * @author minemobs
 * @version v1.1
 */

public class Test {

    static Test test = new Test();

    public static void main(String[] args) {
        System.out.println("Choose what anime you want to search.");
        Scanner scanner = new Scanner(System.in);
        //Instantiate the class AnimeSearcherAPI
        AnimeSearcherAPI animes = new AnimeSearcherAPI();
        //Set the name of the anime
        String animeTitle = scanner.nextLine();
        System.out.println("Equals or contains this string ? \n" +
                "[YES] or [No]");
        scanner = new Scanner(System.in);
        switch (scanner.nextLine().toLowerCase()){
            case "yes":
                /**
                 * Call the function animeSearchEquals()
                 * @param animes the variable AnimeSearcherAPI
                 * @param animeTitle the name of the anime
                 */
                test.animeSearchEquals(animes, animeTitle);
                break;
            case "no":
                /**
                 * Call the function animeSearchEquals()
                 * @param animes the variable AnimeSearcherAPI
                 * @param animeTitle the name of the anime
                 */
                test.animeSearchContains(animes, animeTitle);
                break;
            default:
                System.out.println("Please relaunch the code because i don't want to relaunch the function myself.");
                break;
        }
    }

    private void animeSearchEquals(AnimeSearcherAPI animes, String animeTitle) {
        //Create the variable anime which get the result of the function getJSONFromTitle()
        if (animes.getJSONFromTitle(animeTitle) == null) {
            throw new NullPointerException("This anime is not on NekoSama");
        }
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

    private void animeSearchContains(AnimeSearcherAPI animes, String animeTitle){
        //Create the variable anime which get the result of the function getJSONFromTitle()
        if (animes.getJSONFromTitleContains(animeTitle) == null) {
            throw new NullPointerException("This anime is not on NekoSama");
        }
        List<Anime> animeList = animes.getJSONFromTitleContains(animeTitle);
        AnimeHtml animeHtml = null;
        for (Anime anime : animeList) {
            try {
                //Create the variable animeHtml and
                //Specify the episode searched
                int episodeSearched;
                if(anime.getType().equalsIgnoreCase("tv")){
                    System.out.println("Please specify the episode wanted \n" +
                            "if you don't want to search an episode please write 1 (it will search the episode 1 anyways)");
                    Scanner scanner = new Scanner(System.in);
                    if (scanner.hasNextInt()){
                        episodeSearched = scanner.nextInt();
                        animeHtml = animes.getHtmlPageOfTheAnime(anime, episodeSearched);
                    }else{
                        System.out.println("I want a number!");
                    }
                }else{
                    animeHtml = animes.getHtmlPageOfTheAnime(anime, 1);
                }
                //Print everything
                System.out.println(
                        "-------------------------------------------------------------" + "\n" +
                        "Title: " + anime.getTitle() + "\n" +
                        "Url:" + anime.getUrl() + "\n" +
                        "Synopsis: " + animeHtml.getSynop() + "\n" +
                        "Url of episode: " + animeHtml.getLinkOfTheEpisode() + "\n" +
                        "Url of the cover: " + animeHtml.getUrlOfTheCover() + "\n" +
                        "-------------------------------------------------------------");
            } catch (Exception e) {
                //If there is an error, print the error
                e.printStackTrace();
            }
        }
    }

}
