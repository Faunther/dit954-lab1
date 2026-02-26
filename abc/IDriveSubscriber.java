package abc;

public interface IDriveSubscriber {
    public void onGasEvent(int gasAmount);

    public void onBrakeEvent(int brakeAmount);

    public void onStopEngineEvent();

    public void onStartEngineEvent();

    public void onMoveTickEvent();
}
