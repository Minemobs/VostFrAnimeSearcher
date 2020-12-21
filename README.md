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
        try {
            AnimeSearcherAPI animes = new AnimeSearcherAPI();
            //We specify the name of the searched anime
            String animeTitle = "Jujutsu Kaisen";
            Anime anime = animes.getJSONFromTitle(AnimeSearcherAPI.TitleType.TITLE, animeTitle);
            //We print the url of the anime
            System.out.println(anime.getUrl());
            //Get all genres
            //If genre is not found it will print nothing.
            for (String genre : anime.getGenres()) {
                System.out.println(genre);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
```