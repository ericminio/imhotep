package imhotep.detectors;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class NonWindowLickerTestDetectorTest {

    NonWindowLickerDetector detector = new NonWindowLickerDetector();

    @Test
    public void
    name() {
        assertThat( detector.getName(), equalTo( NonWindowLickerDetector.NAME ) );
    }

    @Test public void
    returnsOneWhenThereIsOneTestAndGrameDriverIsNotInstantiated() {
        String content = "blabla @Test blabla GameDriver blabla";

        assertThat( detector.countTestsIn( content ), equalTo( 1 ) );
    }

    @Test public void
    returnsZeroWhenGrameDriverIsInstantited() {
        String content = "blabla new GameDriver() blabla @Test blabla";

        assertThat( detector.countTestsIn( content ), equalTo( 0 ) );
    }

    @Test public void
    returnsTwoWhenThereAreTwoTestsAndGrameDriverIsNotInstantiated() {
        String content = "blabla @Test blabla GameDriver blabla @Test toto";

        assertThat( detector.countTestsIn( content ), equalTo( 2 ) );
    }
}
