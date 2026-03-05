package src.abc;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Publisher implements IViewActionsHandler, IGameTickHandler {
    protected ArrayList<IDriveSubscriber> driveSubscribers = new ArrayList<>();
    protected ArrayList<ITurboSubscriber> turboSubscribers = new ArrayList<>();
    protected ArrayList<IRaiseLowerBedSubscriber> raiseLowerBedSubscribers = new ArrayList<>();
    protected ArrayList<IGameTickSubscriber> m_gameTickSubscribers = new ArrayList<>();

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer m_timer = new Timer(delay, new TimerListener());

    public void star() {
        m_timer.start();
    }

    private class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Tell all driveSubscribers to move.
            for (IDriveSubscriber driveSub : driveSubscribers) {
                driveSub.onMoveTickEvent();
            }

            // Implicit !!!
            // Tell view (GameTickSubscriber) to draw.
            for (IGameTickSubscriber gtSub : m_gameTickSubscribers) {
                gtSub.onGameTick();
            }
        }
    }

    @Override
    public void addSubscriber(IGameTickSubscriber gtSub) {
        this.m_gameTickSubscribers.add(gtSub);
    }

    public void addSubscriber(ISubscriber cs) {
        if (cs instanceof IDriveSubscriber) {
            this.driveSubscribers.add((IDriveSubscriber) cs);
        }
        if (cs instanceof ITurboSubscriber) {
            this.turboSubscribers.add((ITurboSubscriber) cs);
        }
        if (cs instanceof IRaiseLowerBedSubscriber) {
            this.raiseLowerBedSubscribers.add((IRaiseLowerBedSubscriber) cs);
        }
    }

    @Override
    public void onClickStartEngine() {
        for (IDriveSubscriber ds : driveSubscribers) {
            ds.onStartEngineEvent();
        }
    }

    @Override
    public void onClickStopEngine() {
        for (IDriveSubscriber ds : driveSubscribers) {
            ds.onStopEngineEvent();
        }
    }

    @Override
    public void onClickGas(int gasAmount) {
        for (IDriveSubscriber s : driveSubscribers) {
            s.onGasEvent(gasAmount);
        }
    }

    @Override
    public void onClickBrake(int brakeEvent) {
        for (IDriveSubscriber s : driveSubscribers) {
            s.onBrakeEvent(brakeEvent);
        }
    }

    @Override
    public void onClickTurboOn() {
        for (ITurboSubscriber s : turboSubscribers) {
            s.onTurboEvent(true);
        }
    }

    @Override
    public void onClickTurboOff() {
        for (ITurboSubscriber s : turboSubscribers) {
            s.onTurboEvent(false);
        }
    }

    @Override
    public void onClickLowerBed() {
        for (IRaiseLowerBedSubscriber s : raiseLowerBedSubscribers) {
            s.onLowerBed();
        }
    }

    @Override
    public void onClickRaiseBed() {
        for (IRaiseLowerBedSubscriber s : raiseLowerBedSubscribers) {
            s.onRaiseBed();
        }
    }
}
