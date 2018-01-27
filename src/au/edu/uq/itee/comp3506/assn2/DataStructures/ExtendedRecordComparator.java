package au.edu.uq.itee.comp3506.assn2.DataStructures;

import au.edu.uq.itee.comp3506.assn2.entities.ExtendedRecord;

import java.util.Comparator;

/**
 * Comparator for comparing two Extended record objects based on timestamp.
 *
 * @author Ali Nawaz Maan
 */
public class ExtendedRecordComparator implements Comparator<ExtendedRecord> {

    /**
     * Compare two ExtendedRecord objects based on timestamp
     * @param o1
     * @param o2
     * @return
     */
    @Override
    public int compare(ExtendedRecord o1, ExtendedRecord o2) {

        if (o1.getTimeStamp().isBefore(o2.getTimeStamp())) {
            return -1;
        }else if (o1.getTimeStamp().isAfter(o2.getTimeStamp())) {
            return 1;
        }else {
            return 0;
        }
    }

}
