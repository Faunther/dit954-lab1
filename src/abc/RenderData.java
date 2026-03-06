package src.abc;

import java.awt.*;

public class RenderData {
    String id;
    Point pos;

    RenderData(String id, Point pos) {
        this.id = id;
        this.pos = pos;
    }

    public Point getPos() {
        return pos;
    }

    public String getId() {
        return id;
    }

}
