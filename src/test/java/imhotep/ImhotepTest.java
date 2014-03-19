package imhotep;

import imhotep.harvesters.FileHarvester;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class ImhotepTest {

    private String report;
    private String firstFile = "" +
            "@Imhotep(level=\"Service\")" +
            "public void FirstTest {" +
            "   @Test public void one() { }"+
            "   @Test public void two() { }" +
            "   @Test public void three() { }" +
            "}";
    private String secondFile = "" +
            "@Imhotep(level=\"Domain\")" +
            "public void SecondTest {" +
            "   @Test public void one() { }"+
            "   @Test public void two() { }"+
            "}";
    private PyramidRenderer renderer;

    @Before public void
    whenImhotepHasTheFollowingInspiration() throws IOException {
        FileHarvester stub = mock( FileHarvester.class );
        when( stub.nextContent() ).thenReturn( firstFile, secondFile, null );

        PyramidBuilder imhotep = new PyramidBuilder();
        imhotep.useHarvester( stub );
        imhotep.buildLevels( "Domain", "Service" );

        renderer = mock( PyramidRenderer.class);
        imhotep.useTools(renderer);

        imhotep.go();
    }

    @Test public void
    heBuildsABeautifulPyramid() {
        List<String> levels = Arrays.asList( "Domain", "Service" );
        List<Integer> sizes = Arrays.asList( 2, 3 );

        verify(renderer).render(levels, sizes);
    }
}
