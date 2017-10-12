package au.edu.uq.itee.comp3506.assn2.DataStructures;

import au.edu.uq.itee.comp3506.assn2.Searching.SwitchToDateMap;

import java.util.Comparator;

public class SwitchToDateMapComparator implements Comparator<SwitchToDateMap> {

    @Override
    public int compare(SwitchToDateMap o1, SwitchToDateMap o2) {

        return Integer.compare(o1.getSwitch(), o2.getSwitch());
    }
}
