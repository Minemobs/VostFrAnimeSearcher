package fr.minemobs.animes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class AnimeSearcherAPI {

    private static final String urlOfNekoSama = "http://neko-sama.fr";
    private static String jsonUrl;
    private static final Logger LOGGER = Logger.getLogger(AnimeSearcherAPI.class.getName());
    private static final Gson gson = new GsonBuilder().create();
    private final OkHttpClient httpClient = new OkHttpClient.Builder().build();
    private final Request request;
    private final boolean dub;
    private String responseBody = "";

    public AnimeSearcherAPI(boolean dub) {
        this.dub = dub;
        jsonUrl = urlOfNekoSama + "/animes-search-" + (dub ? "vf" : "vostfr") + ".json";
        request = new Request.Builder().url(jsonUrl).build();
    }

    public Optional<Anime> getJSONFromTitle(String animeTitle) {
        try (Response response = httpClient.newCall(request).execute()) {
            responseBody = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Type animeListType = new TypeToken<List<Anime>>() {}.getType();
        List<Anime> animes = gson.fromJson(responseBody, animeListType);
        return animes.stream().filter(anime -> anime.equalsTitle(animeTitle)).findFirst();
    }

    public List<Anime> getJSONFromTitleContains(String animeTitle) {
        try (Response response = httpClient.newCall(request).execute()) {
            responseBody = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Type animeListType = new TypeToken<List<Anime>>() {}.getType();
        List<Anime> animes = gson.fromJson(responseBody, animeListType);
        return animes.stream().filter(anime -> anime.containsTitle(animeTitle)).toList();
    }

    public AnimeHtml getHtmlPageOfTheAnime(Anime anime, int episodeSearched) throws IOException {
        if(anime.getUrl() == null) {
            throw new NullPointerException("Cet anime n'a pas de page");
        }
        Document doc = Jsoup.connect(anime.getUrl()).get();
        int nbrOfEps = anime.getNbrOfEpsAsInt();

        String synopsis = doc.getElementsByClass("synopsis").text();

        String urlOfTheCover = doc.getElementsByClass("cover").html().substring(
                doc.getElementsByClass("cover").html().indexOf("https://"),
                doc.getElementsByClass("cover").html().indexOf(".jpg") + 4);

        if(episodeSearched > nbrOfEps){
            throw new ArrayIndexOutOfBoundsException("L'episode cherché est supérieur au nombre d'épisode existant");
        }else if(episodeSearched <= 0){
            throw new ArrayIndexOutOfBoundsException("L'episode cherché est inférieur ou égal à zero");
        }

        String url = anime.getUrl().replace("info", "episode").replace("-" + (dub ? "vf" : "vostfr"),
                "-" + (episodeSearched < 10 ? "0" : "") + episodeSearched + "-" +
                (dub ? "vf" : "vostfr"));
        Document docEpisode = Jsoup.connect(url).userAgent(HttpConnection.DEFAULT_UA).get();
        Optional<String> videoURLHtmlOp = docEpisode.body().getElementsByTag("script").html().lines()
                .map(String::strip)
                .filter(s -> s.startsWith("video[0] = 'https://www.pstream.net") || s.startsWith("video[0] = 'https://veestream.net/e/")).findFirst();
        String playerURL = videoURLHtmlOp.isEmpty() ? null : videoURLHtmlOp.get().substring("video[0] = '".length(), videoURLHtmlOp.get().length() - "';".length());
        return new AnimeHtml(synopsis, url, nbrOfEps, urlOfTheCover, playerURL);
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
