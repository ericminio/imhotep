package imhotep;

import imhotep.support.JavaTestFileHarvester;
import imhotep.support.TextFileRenderer;
import imhotepLevel.Level;
import org.apache.tools.ant.BuildException;

import java.util.ArrayList;
import java.util.List;

public class ImhotepAntTask extends org.apache.tools.ant.Task {

    private String sourcedir;
    private String destdir;
    private List<String> levels;

    public ImhotepAntTask() {
        levels = new ArrayList<String>();
    }

    public void setSourcedir(String sourcedir) {
        this.sourcedir = sourcedir;
    }

    public void setDestdir(String destdir) {
        this.destdir = destdir;
    }

    public void addConfiguredLevel(Level level) {
        System.out.println( level.getName() );
        levels.add( level.getName() );
    }

    public void execute() throws BuildException {
        PyramidBuilder imhotep = new PyramidBuilder();
        imhotep.givenThisMaterial( new JavaTestFileHarvester( sourcedir ) );
        imhotep.givenThisFreeLand( new TextFileRenderer( destdir ) );
        imhotep.havingInMindThoseLevels( levels.toArray( new String[levels.size()]) );

        imhotep.buildPyramid();
    }
}
