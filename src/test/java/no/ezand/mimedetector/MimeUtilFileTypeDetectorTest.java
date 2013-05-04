package no.ezand.mimedetector;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class MimeUtilFileTypeDetectorTest {
    @org.junit.Test
    public void shouldGetMimeType() throws URISyntaxException, IOException {
        URL resource = getClass().getResource("/java.gif");
        System.out.println(resource);

        String mimeType = Files.probeContentType(Paths.get(getClass().getResource("/java.gif").toURI()));
        assertEquals("image/gif", mimeType);
    }
}