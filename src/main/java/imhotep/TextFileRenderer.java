package imhotep;

import support.FileUtils;

import java.io.IOException;
import java.util.List;

public class TextFileRenderer implements PyramidRenderer {

    private String targetDirectory;
    private String name;

    public TextFileRenderer(String targetDirectory) {
        this.targetDirectory = targetDirectory;
    }

    @Override
    public void setPyramidName(String name) {
        this.name = name;
    }

    @Override
    public void render(List<String> levels, List<Integer> sizes) {
        String content = "Pyramid: " + name + "\n\n";

        for(int i=0; i<levels.size(); i++) {
            content += levels.get( i ) + ": " + levelSize( sizes, i ) + "\n";
        }

        try {
            FileUtils.writeStringToFile( content, targetDirectory + "/pyramid.html" );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int levelSize(List<Integer> sizes, int i) {
        int size = 0;
        if ( i < sizes.size() ) {
            size = sizes.get( i ) == null ? 0 : sizes.get( i );
        }
        return size;
    }
}
