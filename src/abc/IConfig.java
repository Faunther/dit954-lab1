package src.abc;

import java.awt.*;

public interface IConfig {
    Dimension getDimension(String entryName);

    void setWorldSize(int x, int y);
}
