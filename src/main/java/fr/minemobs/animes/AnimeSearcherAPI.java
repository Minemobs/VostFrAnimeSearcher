package fr.minemobs.animes;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class AnimeSearcherAPI {

    String urlOfNekoSama = "http://neko-sama.fr";
    String jsonUrl = urlOfNekoSama + "/animes-search.json";

    private static Logger LOGGER = Logger.getLogger(AnimeSearcherAPI.class.getName());

    OkHttpClient httpClient = new OkHttpClient.Builder()
            .protocols(Collections.singletonList(Protocol.HTTP_1_1))
            .build();

    Request request = new Request.Builder().url(jsonUrl).build();

    String responseBody = "";

    public Anime getJSONFromTitle(String animeTitle) {
        try (Response response = httpClient.newCall(request).execute()) {
            responseBody = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        Type animeListType = new TypeToken<List<Anime>>() {
        }.getType();
        List<Anime> animes = gson.fromJson(responseBody, animeListType);
        List<Anime> animes1 = animes.stream().filter(anime1 -> {
            if (anime1.getTitle().equalsIgnoreCase(animeTitle)) {
                return anime1.getTitle().equalsIgnoreCase(animeTitle);
            } else if (anime1.getTitle_english().equalsIgnoreCase(animeTitle)) {
                return anime1.getTitle_english().equalsIgnoreCase(animeTitle);
            }
            return false;
        }).collect(Collectors.toList());
        for (Anime anime : animes1) {
            return anime;
        }
        return null;
    }

    public List<Anime> getJSONFromTitleContains(String animeTitle) {
        try (Response response = httpClient.newCall(request).execute()) {
            responseBody = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        Type animeListType = new TypeToken<List<Anime>>() {
        }.getType();
        List<Anime> animes = gson.fromJson(responseBody, animeListType);
        List<Anime> animes1 = animes.stream()
                .filter(anime1 -> anime1.getTitle().toLowerCase().contains(animeTitle.toLowerCase())).collect(Collectors.toList());
        return animes1;
    }

    public AnimeHtml getHtmlPageOfTheAnime(Anime anime, int episodeSearched) throws Exception {
        if(anime.getUrl() == null || Jsoup.connect(anime.getUrl()).get() == null){
            throw new NullPointerException("Cet anime n'a pas de page");
        }
        Document doc = Jsoup.connect(anime.getUrl()).get();
        Elements titles = doc.getElementsByClass("episode");
        AtomicInteger nbrOfEps = new AtomicInteger();
        titles.forEach(element -> nbrOfEps.getAndIncrement());

        String synopsis = doc.getElementsByClass("synopsis").text();

        String urlOfTheCover = doc.getElementsByClass("cover").html().substring(
                doc.getElementsByClass("cover").html().indexOf("https://"),
                doc.getElementsByClass("cover").html().indexOf(".jpg") + 4);

        if(episodeSearched > nbrOfEps.get()){
            throw new ArrayIndexOutOfBoundsException("L'episode cherché est supérieur au nombre d'épisode existant");
        }else if(episodeSearched <= 0){
            throw new ArrayIndexOutOfBoundsException("L'episode cherché est inférieur ou égal à zero");
        }

        String url;
        if(episodeSearched < 10){
            url = anime.getUrl().replace("info", "episode").replace("-vostfr", "-0" + episodeSearched + "-vostfr");
        }else {
            url = anime.getUrl().replace("info", "episode").replace("-vostfr", "-" + episodeSearched + "-vostfr");
        }
        AnimeHtml animeHtml = new AnimeHtml(synopsis, url, nbrOfEps.intValue(), urlOfTheCover);
        return animeHtml;
    }

    public String getJsonUrl() {
        return jsonUrl;
    }

    public String getUrlOfNekoSama() {
        return urlOfNekoSama;
    }

    public OkHttpClient getHttpClient() {
        return httpClient;
    }

    public Request getRequest() {
        return request;
    }

    public static Logger getLOGGER() {
        return LOGGER;
    }
}
