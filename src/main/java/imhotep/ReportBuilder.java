package imhotep;

import imhotep.detectors.Detector;
import imhotep.harvesters.FileHarvester;
import support.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReportBuilder {

    private FileHarvester fileHarvester;
    private TestCounter testCounter;
    private List<Detector> detectors;
    private String path;

    public ReportBuilder() {
        testCounter = new TestCounter();
        detectors = new ArrayList<Detector>();
    }

    public void execute() throws IOException {
        String report = "";

        String fileContent;
        while ((fileContent = fileHarvester.nextContent()) != null) {
            testCounter.inspect( fileContent );
        }
        for (Detector detector: detectors) {
            report += detector.getName() + ": " + testCounter.testsCount.get( detector ) + "\n";
        }

        FileUtils.writeStringToFile( report, path + "/pyramid.html" );
    }

    public void use(Detector detector) {
        testCounter.use( detector );
        detectors.add( detector );
    }

    public void useFileHarvester(FileHarvester fileHarvester) {
        this.fileHarvester = fileHarvester;
    }

    public void writeReportInDirectory(String path) {
        this.path = path;
        FileUtils.createDirectoryIfNotExists( path );
    }
}
