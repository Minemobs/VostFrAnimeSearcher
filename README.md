# Anime VOSTFR API

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

It's very simple
You just need to do that

```java
public class Main {

    public static void main(String[] args) {
        //Instantiate the class AnimeSearcherAPI
        AnimeSearcherAPI animes = new AnimeSearcherAPI();
        //Set the name of the anime
        String animeTitle = "Jujutsu kaisen";
        //Create the variable anime which get the result of the function getJSONFromTitle()
        Anime anime = animes.getJSONFromTitle(animeTitle);
        //Create the variable animeHtml
        AnimeHtml animeHtml;
        try {
            //Specify the episode searched
            animeHtml = animes.getHtmlPageOfTheAnime(anime, 12);
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