package fr.minemobs.animes;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class AnimeSearcherAPI {

    String urlOfNekoSama = "https://neko-sama.fr";

    public Anime getJSONFromTitle(String animeTitle) {
        try{
            String jsonUrl = urlOfNekoSama + "/animes-search.json";
            InputStream is = new URL(jsonUrl).openStream();
            Reader json = new InputStreamReader(is,"UTF-8");

            Gson gson = new Gson();
            Type animeListType = new TypeToken<List<Anime>>(){}.getType();
            List<Anime> animes = gson.fromJson(json, animeListType);
            List<Anime> animes1 = animes.stream().filter(anime1 -> anime1.getTitle().equalsIgnoreCase(animeTitle)).collect(Collectors.toList());
            for (Anime anime : animes1) {
                return anime;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public AnimeHtml getHtmlPageOfTheAnime(Anime anime, int episodeSearched) throws Exception {
        Document doc = Jsoup.connect(anime.getUrl()).get();
        Elements titles = doc.getElementsByClass("episode");
        AtomicInteger nbrOfEps = new AtomicInteger();
        titles.forEach(element -> {
            nbrOfEps.getAndIncrement();});

        String synopsis = doc.getElementsByClass("synopsis").text();

        if(episodeSearched > nbrOfEps.get()){
            throw new Exception("L'episode cherché est supérieur au nombre d'épisode existant");
        }else if(episodeSearched <= 0){
            throw new Exception("L'episode cherché est inférieur ou égal à zero");
        }

        String url;
        if(episodeSearched < 10){
            url = anime.getUrl().replace("info", "episode").replace("-vostfr", "-0" + episodeSearched + "-vostfr");
        }else {
            url = anime.getUrl().replace("info", "episode").replace("-vostfr", "-" + episodeSearched + "-vostfr");
        }
        AnimeHtml animeHtml = new AnimeHtml(synopsis, url, nbrOfEps.intValue());
        return animeHtml;
    }
}
