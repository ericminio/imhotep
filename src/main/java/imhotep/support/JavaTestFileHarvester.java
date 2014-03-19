package imhotep.support;

import imhotep.FileHarvester;
import support.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JavaTestFileHarvester implements FileHarvester {

    private String path;
    private List<File> files;
    private int index;

    public JavaTestFileHarvester(String path) {
        this.path = path;
    }

    public List<File> harvestTestFilesInDirectory() {
        ArrayList<File> files = new ArrayList<File>();
        File dir = new File( path );

        if (! dir.exists() ) return files;

        for(File file:dir.listFiles()) {

            if ( file.isDirectory() ) {
                files.addAll( new JavaTestFileHarvester( file.getPath() ).harvestTestFilesInDirectory() );
            }

            if ( file.getName().endsWith( "Test.java" )) {
                files.add( file );
            }
        }

        return files;
    }

    @Override
    public String nextContent() {
        if (files == null) {
            files = harvestTestFilesInDirectory();
            index = 0;
        }
        try {
            return FileUtils.readFileToString( files.get( index++ ) );
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getDirectory() {
        return path;
    }
}
