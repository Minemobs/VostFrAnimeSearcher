package fr.minemobs.animes;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class AnimeSearcherAPI {

    String jsonUrl = "https://neko-sama.fr/animes-search.json";

    public Anime getJSONFromTitle(String animeTitle) {
        try{
            //String json = readUrl(jsonUrl);
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

    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }
}
