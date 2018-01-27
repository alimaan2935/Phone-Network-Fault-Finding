package au.edu.uq.itee.comp3506.assn2.Searching;

import au.edu.uq.itee.comp3506.assn2.ADTs.CustomList;
import au.edu.uq.itee.comp3506.assn2.DataStructures.OwnList;
import au.edu.uq.itee.comp3506.assn2.DataStructures.PhoneNode;
import au.edu.uq.itee.comp3506.assn2.entities.CallRecord;
import au.edu.uq.itee.comp3506.assn2.entities.ExtendedRecord;

import java.time.LocalDateTime;

/**
 * Represents the network of phone nodes containing dialling and receiving connection paths.
 * Aids in searches.
 *
 * @memoryEfficiency O(n) (Linear) where n is the number of phone objects in the list.
 * @author Ali Nawaz Maan
 */
public class Network {

    // List of phone node objects containing both sided connection paths
    private CustomList<PhoneNode> phoneNodeList;

    /**
     * Initializes the network with phone node list
     * @runtimeEfficiency O(1) (Constant time) assigning variable values.
     * @param list of phone node objects
     */
    public Network(CustomList<PhoneNode> list) {
        this.phoneNodeList = list;
    }

    /**
     * Returns the dialled path objects associated with a particular dialler node.
     * @runtimeEfficiency O(n) (Linear time) for finding the phone node from the list and constant time getting the
     * dialler path list from that phone node object.
     * @param dialer phone node
     * @return list of dialled connection paths
     */
    public CustomList<Long> getDialledPhones(PhoneNode dialer) {
        int index = phoneNodeList.get(dialer);
        CustomList<Long> dialledPhones = new OwnList<>();
        if (index != -1) {
            CustomList<ExtendedRecord> pathList = phoneNodeList.get(index).getDialingPaths();
            for (int i = 0; i < pathList.size(); i++) {
                dialledPhones.add(pathList.get(i).getReceiver());
            }
        }
        return dialledPhones;
    }

    /**
     * Returns the dialled path objects associated with a particular dialler node within a specific period of time.
     * @runtimeEfficiency O(nlogn) (Linear time) for finding the phone node from the list and logarithmic time for getting the
     * list of connection paths within a specific time.
     * @param dialer dialler phone node
     * @param start start time
     * @param end end time
     * @return list of dialled connection paths
     */
    public CustomList<Long> getDialledPhones(PhoneNode dialer, LocalDateTime start, LocalDateTime end) {
        int index = phoneNodeList.get(dialer);
        CustomList<Long> dialledPhones = new OwnList<>();
        if (index != -1) {
            CustomList<ExtendedRecord> pathList = phoneNodeList.get(index).getDialingPaths();
            for (int i = 0; i < pathList.size(); i++) {
                CallRecord record = pathList.get(i);
                if ((record.getTimeStamp().isAfter(start) || record.getTimeStamp().isEqual(start))
                        && (record.getTimeStamp().isBefore(end) || record.getTimeStamp().isEqual(end))) {
                    dialledPhones.add(record.getReceiver());
                }
            }
        }
        return dialledPhones;
    }

    /**
     * Returns the receiving path objects associated with a particular receiver node.
     * @runtimeEfficiency O(n) (Linear time) for finding the phone node from the list and constant time getting the
     * receiver path list from that phone node object.
     * @param receiver phone node
     * @return list of receiving connection paths
     */
    public CustomList<Long> getReceivedPhones(PhoneNode receiver) {
        int index = phoneNodeList.get(receiver);
        CustomList<Long> receivedPhones = new OwnList<>();
        if (index != -1) {
            CustomList<ExtendedRecord> pathList = phoneNodeList.get(index).getReceivingPaths();
            for (int i = 0; i < pathList.size(); i++) {
                receivedPhones.add(pathList.get(i).getDialler());
            }
        }
        return receivedPhones;
    }

    /**
     * Returns the received path objects associated with a particular receiver node within a specific period of time.
     * @runtimeEfficiency O(nlogn) for finding the phone node from the list and logarithmic time for getting the
     * list of connection paths within a specific time.
     * @param receiver receiver phone node
     * @param start start time
     * @param end end time
     * @return list of receiving connection paths
     */
    public CustomList<Long> getReceivedPhones(PhoneNode receiver, LocalDateTime start, LocalDateTime end) {
        int index = phoneNodeList.get(receiver);
        CustomList<Long> receivedPhones = new OwnList<>();
        if (index != -1) {
            CustomList<ExtendedRecord> pathList = phoneNodeList.get(index).getReceivingPaths();
            for (int i = 0; i < pathList.size(); i++) {
                CallRecord record = pathList.get(i);
                if ((record.getTimeStamp().isAfter(start) || record.getTimeStamp().isEqual(start))
                        && (record.getTimeStamp().isBefore(end) || record.getTimeStamp().isEqual(end))) {
                    receivedPhones.add(record.getDialler());
                }
            }
        }
        return receivedPhones;
    }

    /**
     * Find the connection fault in a particular phone node
     * @runtimeEfficiency O(nlogn) n for finding the phone node from the list and logarithmic time for getting the
     * faults from every connection path.
     * @param dialler dialler phone node
     * @return list of faulty switches
     */
    public CustomList<Integer> findConnectionFaults(PhoneNode dialler) {
        int index = phoneNodeList.get(dialler);
        CustomList<Integer> faults = new OwnList<>();
        if (index != -1) {
            CustomList<ExtendedRecord> pathList = phoneNodeList.get(index).getDialingPaths();
            for (int i = 0 ; i < pathList.size(); i++) {
                if (pathList.get(i).isFaulty()) {
                    faults.add(pathList.get(i).getFaultSwitch());
                }
            }
        }
        return faults;
    }

    /**
     * Find the connection fault in a particular phone node within a specified time
     * @runtimeEfficiency O(nlogn) n for finding the phone node from the list and logarithmic time for getting the
     * faults from every connection path.
     * @param dialler dialler phone node
     * @param start start time
     * @param end end time
     * @return list of faulty switches
     */
    public CustomList<Integer> findConnectionFaults(PhoneNode dialler, LocalDateTime start, LocalDateTime end) {
        int index = phoneNodeList.get(dialler);
        CustomList<Integer> faults = new OwnList<>();
        if (index != -1) {
            CustomList<ExtendedRecord> pathList = phoneNodeList.get(index).getDialingPaths();
            for (int i = 0 ; i < pathList.size(); i++) {
                ExtendedRecord record = pathList.get(i);
                if (record.isFaulty() &&
                        (record.getTimeStamp().isAfter(start) || record.getTimeStamp().isEqual(start)) &&
                        (record.getTimeStamp().isBefore(end) || record.getTimeStamp().isEqual(end))) {
                    faults.add(pathList.get(i).getFaultSwitch());
                }
            }
        }
        return faults;
    }

    /**
     * Find the receiving fault in a particular phone node
     * @runtimeEfficiency O(nlogn) n for finding the phone node from the list and logarithmic time for getting the
     * faults from every connection path.
     * @param receiver phone node
     * @return list of faulty switches
     */
    public CustomList<Integer> findReceivingFaults(PhoneNode receiver) {
        int index = phoneNodeList.get(receiver);
        CustomList<Integer> faults = new OwnList<>();
        if (index != -1) {
            CustomList<ExtendedRecord> pathList = phoneNodeList.get(index).getReceivingPaths();
            for (int i = 0 ; i < pathList.size(); i++) {
                if (pathList.get(i).isFaulty()) {
                    faults.add(pathList.get(i).getFaultSwitch());
                }
            }
        }
        return faults;
    }

    /**
     * Find the connection fault in a particular phone node within a specific period of time
     * @runtimeEfficiency O(nlogn) n for finding the phone node from the list and logarithmic time for getting the
     * faults from every connection path.
     * @param receiver phone node
     * @param start start time
     * @param end end time
     * @return list of faulty switches
     */
    public CustomList<Integer> findReceivingFaults(PhoneNode receiver, LocalDateTime start, LocalDateTime end) {
        int index = phoneNodeList.get(receiver);

        CustomList<Integer> faults = new OwnList<>();

        if (index != -1) {
            CustomList<ExtendedRecord> pathList = phoneNodeList.get(index).getReceivingPaths();

            for (int i = 0 ; i < pathList.size(); i++) {
                ExtendedRecord record = pathList.get(i);
                if (record.isFaulty() &&
                        (record.getTimeStamp().isAfter(start) || record.getTimeStamp().isEqual(start)) &&
                        (record.getTimeStamp().isBefore(end) || record.getTimeStamp().isEqual(end))) {
                    faults.add(pathList.get(i).getFaultSwitch());
                }
            }
        }
        return faults;
    }

    /**
     * Find a switch with max number of connections
     * @runtimeEfficiency O(n) (Linear time) for accessing each switch node and checking timestamps
     * @param switches list of switchesToDateMap
     * @return max connection switch
     */
    public int maxConnections(CustomList<SwitchToDateMap> switches) {
        int maxConSwitch = 0;
        int connections = 0;
        for (int i=0; i < switches.size(); i++) {
            SwitchToDateMap map = switches.get(i);
            if (map.getTimestamps().size() > connections) {
                maxConSwitch = map.getSwitch();
                connections = map.getTimestamps().size();
            }
        }
        return maxConSwitch;
    }

    /**
     * Find a switch with max number of connections within a specific period of time
     * @runtimeEfficiency O(nlogn) n for accessing each switch node and log for checking timestamps
     * @param switches list of switchesToDateMap
     * @param start start time
     * @param end end time
     * @return max connection switch
     */
    public int maxConnections(CustomList<SwitchToDateMap> switches, LocalDateTime start, LocalDateTime end) {
        int maxConSwitch = 0;
        int connections = 0;
        for (int i=0; i < switches.size(); i++) {
            SwitchToDateMap map = switches.get(i);
            CustomList<LocalDateTime> validTimes = new OwnList<>();
            for (int j = 0; j < map.getTimestamps().size(); j++) {
                LocalDateTime timeStamp = map.getTimestamps().get(j);
                if ((timeStamp.isBefore(end) || timeStamp.isEqual(end)) && (timeStamp.isAfter(start) || timeStamp.isEqual(start))) {
                    validTimes.add(timeStamp);
                }
            }
            if (validTimes.size() > connections) {
                maxConSwitch = map.getSwitch();
                connections = validTimes.size();
            }
        }
        return maxConSwitch;
    }

    /**
     * Find a switch with min number of connections within a specific period of time
     * @runtimeEfficiency O(n) (Linear time) for accessing each switch node and and checking timestamps
     * @param switches list of switchesToDateMap
     * @return min connection switch
     */
    public int minConnections(CustomList<SwitchToDateMap> switches) {
        int minConSwitch = 0;
        int connections = Integer.MAX_VALUE;
        for (int i=0; i < switches.size(); i++) {
            SwitchToDateMap map = switches.get(i);
            if (map.getTimestamps().size() < connections && map.getTimestamps().size() != 0) {
                minConSwitch = map.getSwitch();
                connections = map.getTimestamps().size();
            }
        }
        return minConSwitch;
    }

    /**
     * Find a switch with min number of connections within a specific period of time
     * @runtimeEfficiency O(nlogn) n for accessing each switch node and log for checking timestamps
     * @param switches list of switchesToDateMap
     * @param start start time
     * @param end end time
     * @return min connection switch
     */
    public int minConnections(CustomList<SwitchToDateMap> switches, LocalDateTime start, LocalDateTime end) {
        int minConSwitch = 0;
        int connections = Integer.MAX_VALUE;
        for (int i=0; i < switches.size(); i++) {
            SwitchToDateMap map = switches.get(i);
            CustomList<LocalDateTime> validTimes = new OwnList<>();
            for (int j = 0; j < map.getTimestamps().size(); j++) {
                LocalDateTime timeStamp = map.getTimestamps().get(j);
                if ((timeStamp.isBefore(end) || timeStamp.isEqual(end)) && (timeStamp.isAfter(start) || timeStamp.isEqual(start))) {
                    validTimes.add(timeStamp);
                }
            }
            if (validTimes.size() < connections && map.getTimestamps().size() != 0) {
                minConSwitch = map.getSwitch();
                connections = validTimes.size();
            }
        }
        return minConSwitch;
    }



}
