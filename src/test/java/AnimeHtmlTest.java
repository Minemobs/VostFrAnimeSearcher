import fr.minemobs.animes.AnimeHtml;
import fr.minemobs.animes.AnimeSearcherAPI;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AnimeHtmlTest {
    
    @Test
    void url() throws IOException {
        AnimeSearcherAPI api = new AnimeSearcherAPI(true);
        AnimeHtml htmlPageOfTheAnime = api.getHtmlPageOfTheAnime(api.getJSONFromTitle("Haikyuu!!").get(), 21);
        assertEquals("https://www.pstream.net/e/q99Mn4O20NaA9zq", htmlPageOfTheAnime.getPlayerURL());
    }

}
