package au.edu.uq.itee.comp3506.assn2.entities;

import au.edu.uq.itee.comp3506.assn2.ADTs.CustomList;
import au.edu.uq.itee.comp3506.assn2.DataStructures.OwnList;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Extension of the CallRecord class to find faults in the connection whenever the
 * record object is created.
 *
 * @memoryEfficiency O(1) (Constant) because it represents a single object with references to other objects.
 *
 * @author Ali Nawaz Maan
 */
public class ExtendedRecord extends CallRecord {

    // Boolean fault value if the connection path has fault in dialler switch.
    protected boolean isFaulty;
    // Faulty switch
    protected int faultySwitch;

    /**
     * A Constructor for extended call record object. Checks for faults as the call record objects are made.
     * @runtimeEfficiency O(n) (Linear time) where n is the number of switches in the connection path.
     * It initializes the object and checks for faults at the same time.
     *
     * @param dialler The phone number that initiated the call.
     * @param receiver The phone number that should have received the call.
     * @param diallerSwitch The identifier of the switch to which the dialler was connected.
     * @param receiverSwitch The identifier of the switch to which the receiver was connected.
     * @param connectionPath List of all the identifiers of the switches through which the call was routed.
     * @param timeStamp The date and time at which the call was initiated.
     */
    public ExtendedRecord(long dialler, long receiver,
                      int diallerSwitch, int receiverSwitch,
                      List<Integer> connectionPath, LocalDateTime timeStamp) {
        super(dialler, receiver, diallerSwitch, receiverSwitch, connectionPath, timeStamp);
        faultCheck();
    }

    /**
     * @runtimeEfficiency O(1) (Constant time).
     * @return true if the dialler switch is faulty, false otherwise.
     */
    public boolean isFaulty() {
        return isFaulty;
    }


    /**
     * @runtimeEfficiency O(1) (Constant time) because it just returns the value of instance variable.
     * @return the faulty dialler switch
     */
    public int getFaultSwitch() {
        return faultySwitch;
    }


    /**
     * Checks for fault in the connection path and sets the appropriate faulty switches.
     */
    private void faultCheck() {
        int dialerSwitch = getDiallerSwitch();
        int receiverSwitch = getReceiverSwitch();
        CustomList<Integer> recordPath = new OwnList<>();
        for (int i : getConnectionPath()) {
            recordPath.add(i);
        }
        if (recordPath.size() == 0) {
            isFaulty = true;
            faultySwitch = dialerSwitch;
        } else if (dialerSwitch != recordPath.get(0)) {
            isFaulty = true;
            faultySwitch = dialerSwitch;
        }else if (receiverSwitch != recordPath.get(recordPath.size()-1)) {
            isFaulty = true;
            faultySwitch = recordPath.get(recordPath.size()-1);
        }else {
            isFaulty = false;
        }
    }


}
