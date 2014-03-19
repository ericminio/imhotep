package imhotep;

import imhotep.harvesters.FileHarvester;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PyramidBuilder {

    private List<String> levels;
    private FileHarvester fileHarvester;
    private PyramidRenderer renderer;

    public void useHarvester(FileHarvester fileHarvester) {
        this.fileHarvester = fileHarvester;
    }

    public void buildLevels(String... levels) {
        this.levels = Arrays.asList( levels );
    }

    public void useTools(PyramidRenderer renderer) {
        this.renderer = renderer;
    }

    public void go() {
        List<Integer> sizes = new ArrayList<Integer>();
        String fileContent;
        while ((fileContent = fileHarvester.nextContent()) != null) {
            int size = fileContent.split( "@Test" ).length - 1;
            sizes.add( size );
        }
        renderer.render( levels, sizes );
    }

}
