package com.baloise.confluence.digitalsignature;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static java.nio.file.Files.readAllLines;
import static java.nio.file.Paths.get;
import static org.junit.Assert.assertEquals;

public class MarkdownTest {
    private Markdown markdown;

    @Before
    public void setUp() {
        markdown = new Markdown();
    }

    @Test
    public void testToHTML() throws Exception {
        assertEquals("<p>This is <em>Sparta</em></p>\n", markdown.toHTML("This is *Sparta*"));
        assertEquals("<p>Link</p>\n", markdown.toHTML("[Link](http://a.com)"));
        assertEquals("<p></p>\n", markdown.toHTML("![Image](http://url/a.png)"));
        assertEquals("<p>&lt;b&gt;&lt;/b&gt;</p>\n", markdown.toHTML("<b></b>"));
        assertEquals(readResource("commonmark.html").trim(), markdown.toHTML(readResource("commonmark.md")).trim());
    }

    private String readResource(String name) throws IOException, URISyntaxException {
        return String.join("\n", readAllLines(get(getClass().getResource("/" + name).toURI())));
    }
}
