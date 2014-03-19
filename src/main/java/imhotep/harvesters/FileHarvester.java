package imhotep.harvesters;

public interface FileHarvester {

    String getDirectory();

    String nextContent();
}
