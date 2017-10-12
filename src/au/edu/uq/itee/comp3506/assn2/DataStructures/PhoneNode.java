package au.edu.uq.itee.comp3506.assn2.DataStructures;


import au.edu.uq.itee.comp3506.assn2.ADTs.CustomList;

public class PhoneNode {

    private long identifier;
    private CustomList<Path> dialingPaths;
    private CustomList<Path> receivingPaths;

    public PhoneNode(long identifier) {
        this.identifier = identifier;
        this.dialingPaths = new OwnList<>();
        this.receivingPaths = new OwnList<>();
    }

    public long getIdentifier() {
        return identifier;
    }

    public CustomList<Path> getDialingPaths() {
        return dialingPaths;
    }

    public void addDialingPath(Path dialingPath) {
        this.dialingPaths.add(dialingPath);
    }

    public CustomList<Path> getReceivingPaths() {
        return receivingPaths;
    }

    public void addReceivingPath(Path receivingPath) {
        this.receivingPaths.add(receivingPath);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhoneNode)) return false;

        PhoneNode phoneNode = (PhoneNode) o;

        return getIdentifier() == phoneNode.getIdentifier();
    }

    @Override
    public int hashCode() {
        return (int) (getIdentifier() ^ (getIdentifier() >>> 32));
    }
}
