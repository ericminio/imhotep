package imhotep.detectors;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class WindowLickerTestDetectorTest {

    WindowLickerDetector detector = new WindowLickerDetector();

    @Test public void
    name() {
        assertThat( detector.getName(), equalTo( WindowLickerDetector.NAME ) );
    }

    @Test public void
    returnsOneWhenThereIsOneTestAndGrameDriverIsInstantiated() {
        String content = "blabla new GameDriver() blabla @Test blabla";

        assertThat( detector.countTestsIn( content ), equalTo( 1 ) );
    }

    @Test public void
    returnsZeroWhenThereIsNoInstantiationOfGrameDriver() {
        String content = "blabla GameDriver blabla @Test blabla";

        assertThat( detector.countTestsIn( content ), equalTo( 0 ) );
    }

    @Test public void
    returnsZeroWhenThereIsNoTest() {
        String content = "blabla new GameDriver() blabla";

        assertThat( detector.countTestsIn( content ), equalTo( 0 ) );
    }

    @Test public void
    returnsTwoWhenThereAreTwoTestsAndGrameDriverIsInstantiated() {
        String content = "blabla new GameDriver() blabla @Test blabla @Test toto";

        assertThat( detector.countTestsIn( content ), equalTo( 2 ) );
    }

}
