package imhotep;

import imhotep.detectors.Detector;

import java.util.HashMap;
import java.util.Map;

public class TestCounter {

    public Map<Detector, Integer> testsCount;

    public TestCounter() {
        testsCount = new HashMap<Detector, Integer>();
    }

    public void use(Detector detector) {
        testsCount.put( detector, 0 );
    }

    public void inspect(String content) {
        for(Detector detector: testsCount.keySet()) {
            testsCount.put( detector, testsCount.get( detector ) + detector.countTestsIn( content ) );
        }
    }

    public Integer getTestCountBy(Detector detector) {
        return testsCount.get( detector );
    }
}
