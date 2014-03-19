package imhotep;

import imhotep.detectors.Detector;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestCounterTest {

    private TestCounter builder;

    @Before public void
    takingIntoAccountANewPyramidBuilder() {
        builder = new TestCounter();
    }

    @Test public void
    itStartsWithTestsCountZeroForADetector() {
        Detector detector = mock( Detector.class );
        builder.use( detector );

        assertThat( builder.getTestCountBy( detector ), equalTo( 0 ));
    }

    @Test public void
    itDelegatesTheInspectionToTheGivenDetector() {
        Detector detector = mock( Detector.class );
        builder.use( detector );
        builder.inspect( "any content" );

        verify( detector ).countTestsIn( "any content" );
    }

    @Test public void
    setsTestsCountToTheValuesReturnedByTheDetector() {
        Detector detector = mock( Detector.class );
        builder.use( detector );
        when( detector.countTestsIn( "any content" ) ).thenReturn( 18 );
        builder.inspect( "any content" );

        assertThat( builder.getTestCountBy( detector ), equalTo( 18 ) );
    }

    @Test public void
    incrementsTestsCountToTheValuesReturnedByTheDetector() {
        Detector detector = mock( Detector.class );
        builder.use( detector );
        when( detector.countTestsIn( "first content" ) ).thenReturn( 2 );
        when( detector.countTestsIn( "second content" ) ).thenReturn( 3 );
        builder.inspect( "first content" );
        builder.inspect( "second content" );

        assertThat( builder.getTestCountBy( detector ), equalTo( 5 ) );
    }

    @Test public void
    byTheWayItDelegatesTheInspectionToAllGivenDetectors() {
        Detector detector = mock( Detector.class );
        builder.use( detector );
        builder.use( mock( Detector.class ) );
        builder.inspect( "any content" );

        verify( detector ).countTestsIn( "any content" );
    }

}
