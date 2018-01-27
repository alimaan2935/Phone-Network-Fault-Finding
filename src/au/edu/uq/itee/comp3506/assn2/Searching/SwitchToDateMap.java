package au.edu.uq.itee.comp3506.assn2.Searching;

import au.edu.uq.itee.comp3506.assn2.ADTs.CustomList;
import au.edu.uq.itee.comp3506.assn2.DataStructures.OwnList;

import java.time.LocalDateTime;

/**
 * Represents a switch to date mapping object.
 * It is used to determine how many connections a particular switch initialized and when.
 *
 * @memoryEfficiency O(1) (Constant)
 *
 * @author Ali Nawaz Maan
 */
public class SwitchToDateMap {

    // Switch identifier
    private int switchIdentifier;
    // List of timestamps to represent connection times
    CustomList<LocalDateTime> timestamps = new OwnList<>();

    /**
     * initializes the object
     * @runtimeEfficiency O(1) (Constant time)
     * @param switchIdentifier
     */
    public SwitchToDateMap(int switchIdentifier) {
        this.switchIdentifier = switchIdentifier;
    }

    /**
     * Adds timestamp to the list of connection timestamps
     * @runtimeEfficiency O(1) (Constant time) because it takes constant time to add an element at the end of the list.
     * @param timestamp of the connection
     */
    public void addTimestamp(LocalDateTime timestamp) {
        this.timestamps.add(timestamp);
    }

    /**
     * @runtimeEfficiency O(1) (Constant time).
     * @return the switch identifier.
     */
    public int getSwitch() {
        return switchIdentifier;
    }

    /**
     * @runtimeEfficiency O(1) (Constant time)
     * @return the list of connection timestamps
     */
    public CustomList<LocalDateTime> getTimestamps() {
        return timestamps;
    }

    /**
     * Equality check based on switch identifier
     * @runtimeEfficiency O(1) (Constant time)
     * @param o object to compare
     * @return true if two objects are same based on their switch identifier, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SwitchToDateMap)) return false;

        SwitchToDateMap that = (SwitchToDateMap) o;

        return switchIdentifier == that.switchIdentifier;
    }
}
