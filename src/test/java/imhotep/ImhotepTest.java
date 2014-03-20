package imhotep;

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
    private String thirdFile = "" +
            "@Imhotep(level=\"Domain\")" +
            "public void ThirdTest {" +
            "   @Test public void one() { }"+
            "   @Test public void two() { }"+
            "   @Test public void three() { }"+
            "   @Test public void four() { }"+
            "   @Test public void five() { }"+
            "}";
    private PyramidRenderer renderer;

    @Before public void
    whenImhotepHasTheFollowingInspiration() throws IOException {
        FileHarvester stub = mock( FileHarvester.class );
        when( stub.getDirectory() ).thenReturn( "The best pyramid ever" );
        when( stub.nextContent() ).thenReturn( firstFile, secondFile, thirdFile, null );

        PyramidBuilder imhotep = new PyramidBuilder();
        imhotep.givenThisMaterial( stub );
        imhotep.havingInMindThoseLevels( "Domain", "Service" );

        renderer = mock( PyramidRenderer.class);
        imhotep.givenThisFreeLand( renderer );

        imhotep.buildPyramid();
    }

    @Test public void
    heGivesANameToThePyramid() {
        verify(renderer).setPyramidName( "The best pyramid ever" );
    }

    @Test public void
    heBuildsABeautifulPyramid() {
        List<String> levels = Arrays.asList( "Domain", "Service" );
        List<Integer> sizes = Arrays.asList( 7, 3 );

        verify(renderer).render(levels, sizes);
    }
}
