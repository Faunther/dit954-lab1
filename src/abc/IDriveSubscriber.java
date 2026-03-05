package src.abc;

public interface IDriveSubscriber extends ISubscriber {
    public void onGasEvent(int gasAmount);

    public void onBrakeEvent(int brakeAmount);

    public void onStopEngineEvent();

    public void onStartEngineEvent();

    public void onMoveTickEvent();
}
