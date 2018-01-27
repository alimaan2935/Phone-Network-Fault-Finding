package au.edu.uq.itee.comp3506.assn2.DataStructures;

import au.edu.uq.itee.comp3506.assn2.Searching.SwitchToDateMap;

import java.util.Comparator;

/**
 * Comparator for comparing two switchToDateMap objects based on the switch identifiers.
 *
 * @author Ali Nawaz Maan
 */
public class SwitchToDateMapComparator implements Comparator<SwitchToDateMap> {

    /**
     * Compare two switchToDateMap objects based on their switch identifiers
     * @param o1
     * @param o2
     * @return
     */
    @Override
    public int compare(SwitchToDateMap o1, SwitchToDateMap o2) {

        return Integer.compare(o1.getSwitch(), o2.getSwitch());
    }
}
