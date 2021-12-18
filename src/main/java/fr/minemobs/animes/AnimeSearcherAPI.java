package fr.minemobs.animes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnimeSearcherAPI {

    private static final String urlOfNekoSama = "http://neko-sama.fr";
    private static final String jsonUrl = urlOfNekoSama + "/animes-search.json";
    private static final Logger LOGGER = Logger.getLogger(AnimeSearcherAPI.class.getName());
    private static final Gson gson = new GsonBuilder().create();
    private final OkHttpClient httpClient = new OkHttpClient.Builder().build();
    private final Request request = new Request.Builder().url(jsonUrl).build();
    private String responseBody = "";

    public Optional<Anime> getJSONFromTitle(String animeTitle) {
        try (Response response = httpClient.newCall(request).execute()) {
            responseBody = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Type animeListType = new TypeToken<List<Anime>>() {}.getType();
        return Stream.of(gson.fromJson(responseBody, animeListType)).filter(Anime.class::isInstance).map(Anime.class::cast)
                .filter(anime -> anime.equalsTitle(animeTitle)).findFirst();
    }

    public List<Anime> getJSONFromTitleContains(String animeTitle) {
        try (Response response = httpClient.newCall(request).execute()) {
            responseBody = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Type animeListType = new TypeToken<List<Anime>>() {
        }.getType();
        List<Anime> animes = gson.fromJson(responseBody, animeListType);
        return animes.stream()
                .filter(anime -> anime.containsTitle(animeTitle)).collect(Collectors.toList());
    }

    public AnimeHtml getHtmlPageOfTheAnime(Anime anime, int episodeSearched) throws IOException {
        if(anime.getUrl() == null) {
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
        return new AnimeHtml(synopsis, url, nbrOfEps.intValue(), urlOfTheCover);
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
