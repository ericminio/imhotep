package imhotep;

import java.util.List;

public interface PyramidRenderer {

    void setPyramidName(String name);

    void render(List<String> levels, List<Integer> sizes);
}
