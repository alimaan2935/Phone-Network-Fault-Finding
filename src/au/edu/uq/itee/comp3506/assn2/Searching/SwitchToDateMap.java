package au.edu.uq.itee.comp3506.assn2.Searching;

import au.edu.uq.itee.comp3506.assn2.ADTs.CustomList;
import au.edu.uq.itee.comp3506.assn2.DataStructures.OwnList;

import java.time.LocalDateTime;

public class SwitchToDateMap {

    private int switchIdentifier;
    CustomList<LocalDateTime> timestamps = new OwnList<>();

    public SwitchToDateMap(int switchIdentifier) {
        this.switchIdentifier = switchIdentifier;
    }

    public void addTimestamp(LocalDateTime timestamp) {
        this.timestamps.add(timestamp);
    }

    public int getSwitch() {
        return switchIdentifier;
    }

    public CustomList<LocalDateTime> getTimestamps() {
        return timestamps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SwitchToDateMap)) return false;

        SwitchToDateMap that = (SwitchToDateMap) o;

        return switchIdentifier == that.switchIdentifier;
    }

    @Override
    public int hashCode() {
        return switchIdentifier;
    }
}
