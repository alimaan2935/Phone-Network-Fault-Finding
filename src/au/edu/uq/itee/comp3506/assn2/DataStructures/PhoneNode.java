package au.edu.uq.itee.comp3506.assn2.DataStructures;

import au.edu.uq.itee.comp3506.assn2.ADTs.CustomList;
import au.edu.uq.itee.comp3506.assn2.entities.ExtendedRecord;

/**
 * Represents a phone node object with list of dialling path edges and a list of receiving path edges.
 * Dialling paths represent the path edges and phone records where this phone number has
 * attempted to make the connection to other phone number.
 * Receiving paths represent the path edges and phone records where this phone number has been dialled
 * by another phone numbers.
 *
 * @memoryEfficiency O(1) (Constant) because it represents a single object with references to other objects.
 * @author Ali Nawaz Maan
 */
public class PhoneNode {

    // Phone number identifier in the phone node
    private long identifier;
    // List of dialling paths
    private CustomList<ExtendedRecord> dialingPaths;
    // List of receiving paths
    private CustomList<ExtendedRecord> receivingPaths;

    /**
     * Initializes the phone node object with provided phone identifier and empty
     * lists for dialling and receiving paths.
     * @runtimeEfficiency O(1) (Constant time)
     * @param identifier phone number
     */
    public PhoneNode(long identifier) {
        this.identifier = identifier;
        this.dialingPaths = new OwnList<>();
        this.receivingPaths = new OwnList<>();
    }

    /**
     * @runtimeEfficiency O(1) (Constant time) because it just returns the value of instance variable (phone identifier).
     * @return the phone number in the node
     */
    public long getIdentifier() {
        return identifier;
    }

    /**
     * @runtimeEfficiency O(1) (Constant time) because it just returns the value of instance variable (dialinf paths list).
     * @return list of dialling paths
     */
    public CustomList<ExtendedRecord> getDialingPaths() {
        return dialingPaths;
    }

    /**
     * @runtimeEfficiency O(1) (Constant time) because it just adds the path object to the end of the list.
     * @param dialingPath to add to the dialling paths list
     */
    public void addDialingPath(ExtendedRecord dialingPath) {
        this.dialingPaths.add(dialingPath);
    }

    /**
     * @runtimeEfficiency O(1) (Constant time) because it just returns the value of instance variable (receiving paths list).
     * @return the list of receiving paths
     */
    public CustomList<ExtendedRecord> getReceivingPaths() {
        return receivingPaths;
    }

    /**
     * @runtimeEfficiency O(1) (Constant time) because it just adds the path object to the end of the list.
     * @param receivingPath to add to the receiving paths list
     */
    public void addReceivingPath(ExtendedRecord receivingPath) {
        this.receivingPaths.add(receivingPath);
    }

    /**
     * Checks the equality of two phone node objects based on phone identifiers
     * @runtimeEfficiency O(1) (Constant time) because it just compares the 2 phone nodes.
     * @param o object to compare
     * @return true if two phone nodes are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhoneNode)) return false;

        PhoneNode phoneNode = (PhoneNode) o;

        return getIdentifier() == phoneNode.getIdentifier();
    }
}
