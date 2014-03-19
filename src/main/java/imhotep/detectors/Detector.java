package imhotep.detectors;

public interface Detector {

    Integer countTestsIn(String content);

    String getName();
}
