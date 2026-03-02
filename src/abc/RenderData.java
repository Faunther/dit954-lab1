package src.abc;

import java.awt.*;
import java.awt.geom.Point2D;

public class RenderData {
    String id;
    Point2D.Double pos;

    RenderData(String id, Point2D.Double pos){
        this.id = id;
        this.pos = pos;
    }

    public Point2D.Double getPos() {
        return pos;
    }

    public String getId() {
        return id;
    }


}
