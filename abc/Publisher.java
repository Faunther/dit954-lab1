package abc;

import java.util.ArrayList;

public class Publisher implements IViewActionsHandler {
    protected ArrayList<IDriveSubscriber> driveSubscribers = new ArrayList<>();
    protected ArrayList<ITurboSubscriber> turboSubscribers = new ArrayList<>();
    protected ArrayList<IRaiseLowerBedSubscriber> raiseLowerBedSubscribers = new ArrayList<>();

    public void addSubscriber(IDriveSubscriber cs) {
        this.driveSubscribers.add(cs);

        if (cs instanceof ITurboSubscriber) {
            this.turboSubscribers.add((ITurboSubscriber) cs);
        }
        if (cs instanceof IRaiseLowerBedSubscriber) {
            this.raiseLowerBedSubscribers.add((IRaiseLowerBedSubscriber) cs);
        }
    }

    public void onClickStartEngine() {
        for (IDriveSubscriber ds : driveSubscribers) {
            ds.onStartEngineEvent();
        }
    }

    public void onClickStopEngine() {
        for (IDriveSubscriber ds : driveSubscribers) {
            ds.onStopEngineEvent();
        }
    }

    public void onClickGas(int gasAmount) {
        for (IDriveSubscriber s : driveSubscribers) {
            s.onGasEvent(gasAmount);
        }
    }

    public void onClickBrake(int brakeEvent) {
        for (IDriveSubscriber s : driveSubscribers) {
            s.onBrakeEvent(brakeEvent);
        }
    }

    public void onClickTurboOn() {
        for (ITurboSubscriber s : turboSubscribers) {
            s.onTurboEvent(true);
        }
    }

    public void onClickTurboOff() {
        for (ITurboSubscriber s : turboSubscribers) {
            s.onTurboEvent(false);
        }
    }

    public void onClickLowerBed() {
        for (IRaiseLowerBedSubscriber s : raiseLowerBedSubscribers) {
            s.onLowerBed();
        }
    }

    public void onClickRaiseBed() {
        for (IRaiseLowerBedSubscriber s : raiseLowerBedSubscribers) {
            s.onRaiseBed();
        }
    }
}
