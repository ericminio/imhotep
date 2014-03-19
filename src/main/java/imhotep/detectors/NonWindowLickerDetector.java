package imhotep.detectors;

public class NonWindowLickerDetector implements Detector {

    public static final String NAME = "Unit tests";

    @Override
    public Integer countTestsIn(String content) {
        Integer count = 0;
        if (content.indexOf( "new GameDriver()" ) == -1) {
            count = content.split( "@Test" ).length - 1;
        }
        return count;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
