package fr.minemobs.animes;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * @author minemobs
 * @version v1.1
 */

public class Test {

    private static final Logger logger = Logger.getLogger("Test NekoSama API");

    public static void main(String[] args) {
        logger.info("Choose what anime you want to search.");
        Scanner scanner = new Scanner(System.in);
        //Instantiate the class AnimeSearcherAPI
        AnimeSearcherAPI animes = new AnimeSearcherAPI();
        //Set the name of the anime
        String animeTitle = scanner.nextLine();
        logger.info("Equals or contains this string ? \n" +
                "[YES] or [No]");
        scanner = new Scanner(System.in);
        switch (scanner.nextLine().toLowerCase()){
            case "yes":
                /**
                 * Call the function animeSearchEquals()
                 * @param animes the variable AnimeSearcherAPI
                 * @param animeTitle the name of the anime
                 */
                animeSearchEquals(animes, animeTitle);
                break;
            case "no":
                /**
                 * Call the function animeSearchEquals()
                 * @param animes the variable AnimeSearcherAPI
                 * @param animeTitle the name of the anime
                 */
                animeSearchContains(animes, animeTitle);
                break;
            default:
                logger.severe("Please relaunch the code because I don't want to relaunch the function myself.");
                break;
        }
    }

    private static void animeSearchEquals(AnimeSearcherAPI animes, String animeTitle) {
        //Create the variable anime which get the result of the function getJSONFromTitle()
        Optional<Anime> anime = animes.getJSONFromTitle(animeTitle);
        if (!anime.isPresent()) {
            logger.severe("This anime is not on NekoSama");
            return;
        }
        try {
            //Create the variable animeHtml and
            //Specify the episode searched
            AnimeHtml animeHtml;
            if(anime.get().getType().equalsIgnoreCase("tv")){
                animeHtml = animes.getHtmlPageOfTheAnime(anime.get(), 12);
            }else{
                animeHtml = animes.getHtmlPageOfTheAnime(anime.get(), 1);
            }
            //Print the link of the episode
            logger.info(animeHtml.getLinkOfTheEpisode());
            //Print the synopsis of the anime
            logger.info(animeHtml.getSynop());
        } catch (Exception e) {
            //If there is an exception, print the error
            e.printStackTrace();
        }
    }

    private static void animeSearchContains(AnimeSearcherAPI animes, String animeTitle) {
        //Create the variable anime which get the result of the function getJSONFromTitle()
        List<Anime> animeList = Objects.requireNonNull(animes.getJSONFromTitleContains(animeTitle), "This anime is not on NekoSama");
        AnimeHtml animeHtml;
        for (Anime anime : animeList) {
            try {
                //Create the variable animeHtml and
                //Specify the episode searched
                int episodeSearched;
                if(anime.getType().equalsIgnoreCase("tv")){
                    logger.info("Please specify the episode wanted \n" +
                            "if you don't want to search an episode please write 1 (it will search the episode 1 anyways)");
                    Scanner scanner = new Scanner(System.in);
                    if (scanner.hasNextInt()){
                        episodeSearched = scanner.nextInt();
                        animeHtml = animes.getHtmlPageOfTheAnime(anime, episodeSearched);
                    }else{
                        logger.severe("I want a number!");
                        break;
                    }
                }else{
                    animeHtml = Objects.requireNonNull(animes.getHtmlPageOfTheAnime(anime, 1));
                }
                //Print everything
                logger.info(
                        "-------------------------------------------------------------" + "\n" +
                        "Title: " + anime.getTitle() + "\n" +
                        "Url:" + anime.getUrl() + "\n" +
                        "Synopsis: " + animeHtml.getSynop() + "\n" +
                        "Url of episode: " + animeHtml.getLinkOfTheEpisode() + "\n" +
                        "Url of the cover: " + animeHtml.getUrlOfTheCover() + "\n" +
                        "-------------------------------------------------------------"
                );
            } catch (IOException e) {
                //If there is an error, print the error
                e.printStackTrace();
            }
        }
    }
}
