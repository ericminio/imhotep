package imhotep;

import imhotep.harvesters.FileHarvester;

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
        Integer[] sizes = new Integer[levels.size()];
        String fileContent;
        while ((fileContent = fileHarvester.nextContent()) != null) {

            for (int i = 0; i < levels.size(); i++) {
                if (fileContent.indexOf("@Imhotep(level=\"" + levels.get( i ) + "\")") != -1) {
                    int size = fileContent.split( "@Test" ).length - 1;
                    sizes[i] = size ;
                }
            }

        }
        renderer.render( levels, Arrays.asList( sizes ));
    }

}
