package src.abc;

import java.awt.*;

public interface IViewActionsHandler {
    public void onClickGas(int amount);

    public void onClickBrake(int amount);

    public void onClickTurboOn();

    public void onClickTurboOff();

    public void onClickStartEngine();

    public void onClickStopEngine();

    public void onClickLowerBed();

    public void onClickRaiseBed();

    public void onClickAddCar();

    public void onClickRemoveCar();

    public void onWindowResize(String target, Dimension dim);
}
