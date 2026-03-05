package src.abc;

import java.awt.*;

public interface IWindowResizeSubscriber extends ISubscriber {
    void resizeTarget(String target, Dimension dim);
}
