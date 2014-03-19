package imhotep;

import imhotep.detectors.Detector;
import imhotep.harvesters.FileHarvester;
import org.junit.Before;
import org.junit.Test;
import support.FileUtils;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.internal.matchers.StringContains.containsString;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReportBuilderTest {

    ReportBuilder reporter;
    private String content;

    @Before public void
    executingAPyramidReport() throws IOException {
        FileUtils.createOrCleanDirectory( "data" );
        createReport();
    }

    private void createReport() throws IOException {
        reporter = new ReportBuilder();
        reporter.writeReportInDirectory( "data" );

        FileHarvester stub = mock( FileHarvester.class );
        when( stub.nextContent() ).thenReturn( "first file", "second file", null );
        reporter.useFileHarvester( stub );

        Detector one = mock( Detector.class );
        when( one.getName() ).thenReturn( "First category" );
        when( one.countTestsIn( "first file" )).thenReturn( 2 );
        when( one.countTestsIn( "second file" )).thenReturn( 3 );
        reporter.use( one );
        Detector two = mock( Detector.class );
        when( two.getName() ).thenReturn( "Second category" );
        when( two.countTestsIn( anyString() )).thenReturn( 18 );
        reporter.use( two );

        reporter.execute();
        content = FileUtils.readFileToString( new File( "data/pyramid.html" ) );
    }

    @Test public void
    buildsAnHtmlReport() {
        assertTrue( new File( "data/pyramid.html" ).exists() );
    }

    @Test public void
    containingTheTestCountOfOneGivenCategory() throws IOException {
        assertThat( content, containsString( "First category: 5" ));
    }

    @Test public void
    containingTheTestCountOfAllGivenCategories() throws IOException {
        assertThat( content, containsString( "Second category: 36" ));
    }

    @Test public void
    reportOrder() throws IOException {
        for (int i= 0; i < 20; i++) {
            createReport();

            assertThat( content.indexOf( "Second" ), greaterThan( content.indexOf( "First" ) ) );
        }
    }

    @Test public void
    createTargetDirectoryIfNotExists() throws IOException {
        FileUtils.createOrCleanDirectory( "dummy-dir" );
        File dir = new File( "dummy-dir" );
        dir.delete();
        reporter.writeReportInDirectory( "dummy-dir" );

        assertTrue( new File( "dummy-dir" ).exists() );
    }
}
