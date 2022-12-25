import fr.minemobs.animes.AnimeHtml;
import fr.minemobs.animes.AnimeSearcherAPI;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AnimeHtmlTest {
    
    @Test
    void url() throws IOException {
        AnimeSearcherAPI api = new AnimeSearcherAPI(true);
        AnimeHtml htmlPageOfTheAnime = api.getHtmlPageOfTheAnime(api.getJSONFromTitle("lookism").get(), 1);
        assertEquals("https://veestream.net/e/wOjORZ4X52Eo7Vb", htmlPageOfTheAnime.getPlayerURL());
    }

}
