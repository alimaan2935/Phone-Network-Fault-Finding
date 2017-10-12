package au.edu.uq.itee.comp3506.assn2.DataStructures;

import au.edu.uq.itee.comp3506.assn2.ADTs.CustomList;
import au.edu.uq.itee.comp3506.assn2.entities.CallRecord;

import java.time.LocalDateTime;

public class Path {

    protected PhoneNode dialer;
    protected PhoneNode receiver;
    protected CallRecord path;
    protected LocalDateTime timestamp;
    protected boolean isDiallerFaulty;
    protected boolean isReceiverFaulty;
    protected int diallerFaultSwitch;
    protected int receiverFaultSwitch;

    public Path(CallRecord path) {
        this.dialer = new PhoneNode(path.getDialler());
        this.receiver = new PhoneNode(path.getReceiver());
        this.path = path;
        this.timestamp = path.getTimeStamp();
        faultCheck();
    }

    public PhoneNode getDialer() {
        return dialer;
    }

    public PhoneNode getReceiver() {
        return receiver;
    }

    public CallRecord getPath() {
        return path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public boolean isDiallerFaulty() {
        return isDiallerFaulty;
    }

    public void setDiallerFaulty(boolean diallerFaulty) {
        isDiallerFaulty = diallerFaulty;
    }

    public boolean isReceiverFaulty() {
        return isReceiverFaulty;
    }

    public void setReceiverFaulty(boolean receiverFaulty) {
        isReceiverFaulty = receiverFaulty;
    }

    public int getDiallerFaultSwitch() {
        return diallerFaultSwitch;
    }

    public void setDiallerFaultSwitch(int diallerFaultSwitch) {
        this.diallerFaultSwitch = diallerFaultSwitch;
    }

    public int getReceiverFaultSwitch() {
        return receiverFaultSwitch;
    }

    public void setReceiverFaultSwitch(int receiverFaultSwitch) {
        this.receiverFaultSwitch = receiverFaultSwitch;
    }

    private void faultCheck() {
        int dialerSwitch = path.getDiallerSwitch();
        int receiverSwitch = path.getReceiverSwitch();
        CustomList<Integer> recordPath = new OwnList<>();
        for (int i : path.getConnectionPath()) {
            recordPath.add(i);
        }

        if (dialerSwitch != recordPath.get(0)) {
            isDiallerFaulty = true;
            diallerFaultSwitch = dialerSwitch;
        }else if (receiverSwitch != recordPath.get(recordPath.size()-1)) {
            isReceiverFaulty = true;
            receiverFaultSwitch = recordPath.get(recordPath.size()-1);
        }else {
            isDiallerFaulty = false;
            isReceiverFaulty = false;
        }
    }

}
