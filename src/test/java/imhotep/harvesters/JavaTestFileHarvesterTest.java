package imhotep.harvesters;

import support.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.internal.matchers.StringContains.containsString;

public class JavaTestFileHarvesterTest {

    public static final String WORKING_DIRECTORY = "data";
    public static final String SUB_DIRECTORY = "sub-dir";

    @Before public void
    createDataDirectory() {
        FileUtils.createOrCleanDirectory( WORKING_DIRECTORY );
        FileUtils.createOrCleanDirectory( WORKING_DIRECTORY + "/" + SUB_DIRECTORY );
    }

    @Test public void
    providesTheContentOfFirstFile() throws IOException {
        FileUtils.writeStringToFile( "first content", WORKING_DIRECTORY + "/dummyTest.java" );
        String firstContent = new JavaTestFileHarvester( WORKING_DIRECTORY ).nextContent();

        assertThat( firstContent, equalTo( "first content" ) );
    }

    @Test public void
    providesTheContentOfSecondFile() throws IOException {
        FileUtils.writeStringToFile( "first", WORKING_DIRECTORY + "/aTest.java" );
        FileUtils.writeStringToFile( "second", WORKING_DIRECTORY + "/bTest.java" );
        FileHarvester harvester = new JavaTestFileHarvester( WORKING_DIRECTORY );
        String content = harvester.nextContent();
        content += harvester.nextContent();

        assertThat( content, containsString( "first" ));
        assertThat( content, containsString( "second" ));
    }

    @Test public void
    untilNull() throws IOException {
        FileUtils.writeStringToFile( "first", WORKING_DIRECTORY + "/aTest.java" );
        FileUtils.writeStringToFile( "second", WORKING_DIRECTORY + "/bTest.java" );
        FileHarvester harvester = new JavaTestFileHarvester( WORKING_DIRECTORY );
        String content = harvester.nextContent();
        content = harvester.nextContent();
        content = harvester.nextContent();

        assertThat( content, equalTo( null ));
    }

    @Test public void
    canCollectOneTestFilesOfOneDirectory() throws IOException {
        FileUtils.writeStringToFile( "any content", WORKING_DIRECTORY + "/dummyTest.java" );
        List<File> files = new JavaTestFileHarvester(WORKING_DIRECTORY).harvestTestFilesInDirectory();

        assertThat( files.get(0).getAbsolutePath(), containsString( "dummyTest" ));
    }

    @Test public void
    canCollectTwoTestFilesOfOneDirectory() throws IOException {
        FileUtils.writeStringToFile( "any content", WORKING_DIRECTORY + "/dummy.1.Test.java" );
        FileUtils.writeStringToFile( "any content", WORKING_DIRECTORY + "/dummy.2.Test.java" );
        List<File> files = new JavaTestFileHarvester(WORKING_DIRECTORY).harvestTestFilesInDirectory();

        assertThat( files.get(1).getAbsolutePath(), containsString( "dummy.2" ));
    }

    @Test public void
    doNotCollectRegularJavaFiles() throws IOException {
        FileUtils.writeStringToFile( "any content", WORKING_DIRECTORY + "/dummy.java" );
        List<File> files = new JavaTestFileHarvester(WORKING_DIRECTORY).harvestTestFilesInDirectory();

        assertThat( files.size(), equalTo( 0 ));
    }

    @Test public void
    collectFilesInSubDirectory() throws IOException {
        FileUtils.writeStringToFile( "any content", WORKING_DIRECTORY + "/" + SUB_DIRECTORY + "/dummyTest.java" );
        List<File> files = new JavaTestFileHarvester(WORKING_DIRECTORY).harvestTestFilesInDirectory();

        assertThat( files.get(0).getAbsolutePath(), containsString( "dummyTest" ));
    }

}
