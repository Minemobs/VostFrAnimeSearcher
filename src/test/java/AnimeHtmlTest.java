import fr.minemobs.animes.AnimeHtml;
import fr.minemobs.animes.AnimeSearcherAPI;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AnimeHtmlTest {
    
    @Test
    void url() throws IOException {
        AnimeSearcherAPI api = new AnimeSearcherAPI(true);
        AnimeHtml htmlPageOfTheAnime = api.getHtmlPageOfTheAnime(api.getJSONFromTitle("Hunter x Hunter (2011)").get(), 100);
        System.out.println(htmlPageOfTheAnime.getLinkOfTheEpisode());
        assertEquals("https://neko-sama.fr/anime/episode/5691-hunter-x-hunter-2011-100-vf", htmlPageOfTheAnime.getLinkOfTheEpisode());
    }

}
