package imhotep;

import imhotep.detectors.NonWindowLickerDetector;
import imhotep.detectors.WindowLickerDetector;
import imhotep.harvesters.JavaTestFileHarvester;

import java.io.IOException;

public class Imhotep {

    public static void main(String... arg) throws IOException {

        ReportBuilder reporter = new ReportBuilder();
        reporter.useFileHarvester( new JavaTestFileHarvester( "src/test/java" ) );
        reporter.use( new WindowLickerDetector() );
        reporter.use( new NonWindowLickerDetector() );
        reporter.writeReportInDirectory( "reports" );

        reporter.execute();
    }
}
