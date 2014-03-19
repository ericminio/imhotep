package imhotep;

import imhotep.harvesters.JavaTestFileHarvester;

import java.io.IOException;

public class Boss {

    public static void main(String... arg) throws IOException {

        PyramidBuilder imhotep = new PyramidBuilder();
        imhotep.givenThisLand( new JavaTestFileHarvester( "src/test/java" ) );
        imhotep.givenThisTool( new TextFileRenderer( "reports" ) );
        imhotep.havingInMindThoseLevels( "layout", "windowlicker" );

        imhotep.buildPyramid();
    }
}
