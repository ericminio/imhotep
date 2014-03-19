package imhotep;

import org.junit.Before;
import org.junit.Test;
import support.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.matchers.JUnitMatchers.containsString;

public class TextFileRendererTest {

    TextFileRenderer renderer;

    @Before
    public void
    executingAPyramidReport() throws IOException {
        FileUtils.createOrCleanDirectory( "data" );
        renderer = new TextFileRenderer( "data" );
        renderer.setPyramidName( "src/test/java" );
        renderer.render( Arrays.asList( "first", "second" ),
                         Arrays.asList( 18, 23 ));
    }

    @Test public void
    buildsAnHtmlReport() {
        assertTrue( new File( "data/pyramid.html" ).exists() );
    }

    @Test public void
    containingAHeaderWithTheInspectedDirectory() throws IOException {
        String content = FileUtils.readFileToString( new File( "data/pyramid.html" ) );

        assertThat( content, containsString( "Pyramid: src/test/java\n\n"));
    }

    @Test public void
    containingTestCounts() throws IOException {
        String content = FileUtils.readFileToString( new File( "data/pyramid.html" ) );

        assertThat( content, containsString( "first: 18\nsecond: 23" ));
    }

    @Test public void
    resistsEmptySizesParameter() throws IOException {
        renderer.render( Arrays.asList( "first", "second" ), new ArrayList<Integer>() );
        String content = FileUtils.readFileToString( new File( "data/pyramid.html" ) );

        assertThat( content, containsString( "first: 0\nsecond: 0" ) );
    }

    @Test public void
    resistNullValues() throws IOException {
        renderer.render( Arrays.asList( "first", "second" ), Arrays.asList( (Integer)null, (Integer)null ) );
        String content = FileUtils.readFileToString( new File( "data/pyramid.html" ) );

        assertThat( content, containsString( "first: 0\nsecond: 0" ) );
    }

}
