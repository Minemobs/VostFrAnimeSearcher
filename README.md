# Anime VOSTFR API
[![](https://jitpack.io/v/Minemobs/VostFrAnimeSearcher.svg)](https://jitpack.io/#Minemobs/VostFrAnimeSearcher)

## By Minemobs

- ### How do I add the api to my build.gradle ?

You just need to add jitpack as a repository

Like this:

```
repositories {
maven { url 'https://jitpack.io' }
}
```

And add in your dependencies this :

`implementation 'com.github.Minemobs:VostFrAnimeSearcher:-SNAPSHOT'`

- ### How do I use the API ?

One example of a possible use.

```java
public class Main {

    public static void main(String[] args) {
        System.out.println("Choose what anime you want to search.");
        Scanner scanner = new Scanner(System.in);
        //Instantiate the class AnimeSearcherAPI
        AnimeSearcherAPI animes = new AnimeSearcherAPI();
        //Set the name of the anime
        String animeTitle = scanner.nextLine();
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

}
```