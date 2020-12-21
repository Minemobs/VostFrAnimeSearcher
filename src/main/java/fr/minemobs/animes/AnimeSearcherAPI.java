package fr.minemobs.animes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class AnimeSearcherAPI {

    String jsonUrl = "https://neko-sama.fr/animes-search.json";

    public Anime getJSONFromTitle(TitleType titleType, String animeTitle) throws Exception {

        switch (titleType){
            case TITLE:
                //String json = readUrl(jsonUrl);
                InputStream is = new URL(jsonUrl).openStream();
                Reader reader = new InputStreamReader(is,"UTF-8");

                Gson gson = new Gson().newBuilder().setPrettyPrinting().create();
                List<Anime> animes = Arrays.asList(gson.fromJson(reader, Anime[].class));
                reader.close();
                is.close();
                for (Anime anime : animes){
                    if(anime.getTitle().equalsIgnoreCase(animeTitle)){
                        return anime;
                    }else{
                        return null;
                    }
                }
            case TITLE_ENGLISH:
            case TITLE_ROMANJI:
            default:
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

    /*public void getJSONFromEnglishTitle(String title_english) {

    }

    public void getJSONFromRomanjiTitle(String title_romanji) {

    }

    public void getJSONFromId(String url, int id) {

    }*/

    public enum TitleType {
        TITLE,
        TITLE_ENGLISH,
        TITLE_ROMANJI
    }
}
