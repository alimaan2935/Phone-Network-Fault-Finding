package au.edu.uq.itee.comp3506.assn2.Searching;

import au.edu.uq.itee.comp3506.assn2.ADTs.CustomList;
import au.edu.uq.itee.comp3506.assn2.DataStructures.OwnList;
import au.edu.uq.itee.comp3506.assn2.DataStructures.Path;
import au.edu.uq.itee.comp3506.assn2.DataStructures.PhoneNode;
import au.edu.uq.itee.comp3506.assn2.entities.CallRecord;

import java.time.LocalDateTime;

public class Network {

    CustomList<PhoneNode> phoneNodeList;

    public Network(CustomList<PhoneNode> list) {
        this.phoneNodeList = list;
    }

    public CustomList<Long> getDialledPhones(PhoneNode dialer) {
        int index = phoneNodeList.get(dialer);

        CustomList<Long> dialledPhones = new OwnList<>();

        if (index != -1) {
            CustomList<Path> pathList = phoneNodeList.get(index).getDialingPaths();

            for (int i = 0; i < pathList.size(); i++) {
                dialledPhones.add(pathList.get(i).getPath().getReceiver());
            }
        }
        return dialledPhones;
    }

    public CustomList<Long> getDialledPhones(PhoneNode dialer, LocalDateTime start, LocalDateTime end) {
        int index = phoneNodeList.get(dialer);

        CustomList<Long> dialledPhones = new OwnList<>();

        if (index != -1) {
            CustomList<Path> pathList = phoneNodeList.get(index).getDialingPaths();

            for (int i = 0; i < pathList.size(); i++) {
                CallRecord record = pathList.get(i).getPath();

                if (record.getTimeStamp().isAfter(start) && record.getTimeStamp().isBefore(end)) {
                    dialledPhones.add(record.getReceiver());
                }
            }
        }
        return dialledPhones;
    }

    public CustomList<Long> getReceivedPhones(PhoneNode receiver) {
        int index = phoneNodeList.get(receiver);

        CustomList<Long> receivedPhones = new OwnList<>();

        if (index != -1) {
            CustomList<Path> pathList = phoneNodeList.get(index).getReceivingPaths();

            for (int i = 0; i < pathList.size(); i++) {
                receivedPhones.add(pathList.get(i).getPath().getDialler());
            }
        }
        return receivedPhones;
    }

    public CustomList<Long> getReceivedPhones(PhoneNode receiver, LocalDateTime start, LocalDateTime end) {
        int index = phoneNodeList.get(receiver);

        CustomList<Long> receivedPhones = new OwnList<>();

        if (index != -1) {
            CustomList<Path> pathList = phoneNodeList.get(index).getReceivingPaths();

            for (int i = 0; i < pathList.size(); i++) {
                CallRecord record = pathList.get(i).getPath();

                if (record.getTimeStamp().isAfter(start) && record.getTimeStamp().isBefore(end)) {
                    receivedPhones.add(record.getDialler());
                }
            }
        }
        return receivedPhones;
    }

    public CustomList<Integer> findConnectionFaults(PhoneNode dialler) {
        int index = phoneNodeList.get(dialler);

        CustomList<Integer> faults = new OwnList<>();

        if (index != -1) {
            CustomList<Path> pathList = phoneNodeList.get(index).getDialingPaths();

            for (int i = 0 ; i < pathList.size(); i++) {
                if (pathList.get(i).isDiallerFaulty()) {
                    faults.add(pathList.get(i).getDiallerFaultSwitch());
                }
            }
        }
        return faults;
    }

    public CustomList<Integer> findConnectionFaults(PhoneNode dialler, LocalDateTime start, LocalDateTime end) {
        int index = phoneNodeList.get(dialler);

        CustomList<Integer> faults = new OwnList<>();

        if (index != -1) {
            CustomList<Path> pathList = phoneNodeList.get(index).getDialingPaths();

            for (int i = 0 ; i < pathList.size(); i++) {
                Path record = pathList.get(i);
                if (record.isDiallerFaulty() && record.getPath().getTimeStamp().isAfter(start) && record.getPath().getTimeStamp().isBefore(end)) {
                    faults.add(pathList.get(i).getDiallerFaultSwitch());
                }
            }
        }
        return faults;
    }

    public CustomList<Integer> findReceivingFaults(PhoneNode receiver) {
        int index = phoneNodeList.get(receiver);

        CustomList<Integer> faults = new OwnList<>();

        if (index != -1) {
            CustomList<Path> pathList = phoneNodeList.get(index).getReceivingPaths();

            for (int i = 0 ; i < pathList.size(); i++) {
                if (pathList.get(i).isReceiverFaulty()) {
                    faults.add(pathList.get(i).getReceiverFaultSwitch());
                }
            }
        }
        return faults;
    }

    public CustomList<Integer> findReceivingFaults(PhoneNode receiver, LocalDateTime start, LocalDateTime end) {
        int index = phoneNodeList.get(receiver);

        CustomList<Integer> faults = new OwnList<>();

        if (index != -1) {
            CustomList<Path> pathList = phoneNodeList.get(index).getReceivingPaths();

            for (int i = 0 ; i < pathList.size(); i++) {
                Path record = pathList.get(i);
                if (record.isReceiverFaulty() && record.getPath().getTimeStamp().isAfter(start) && record.getPath().getTimeStamp().isBefore(end)) {
                    faults.add(pathList.get(i).getReceiverFaultSwitch());
                }
            }
        }
        return faults;
    }

    public Integer maxConnections(CustomList<SwitchToDateMap> switches) {
        Integer maxConSwitch = 0;
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

    public Integer maxConnections(CustomList<SwitchToDateMap> switches, LocalDateTime start, LocalDateTime end) {
        Integer maxConSwitch = 0;
        int connections = 0;

        for (int i=0; i < switches.size(); i++) {
            SwitchToDateMap map = switches.get(i);
            CustomList<LocalDateTime> validTimes = new OwnList<>();

            for (int j = 0; j < map.getTimestamps().size(); j++) {
                LocalDateTime timeStamp = map.getTimestamps().get(j);
                if (timeStamp.isBefore(end) && timeStamp.isAfter(start)) {
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

    public Integer minConnections(CustomList<SwitchToDateMap> switches) {
        Integer minConSwitch = 0;
        int connections = Integer.MAX_VALUE;

        for (int i=0; i < switches.size(); i++) {
            SwitchToDateMap map = switches.get(i);
            if (map.getTimestamps().size() < connections) {
                minConSwitch = map.getSwitch();
                connections = map.getTimestamps().size();
            }
        }
        return minConSwitch;
    }

    public Integer minConnections(CustomList<SwitchToDateMap> switches, LocalDateTime start, LocalDateTime end) {
        Integer minConSwitch = 0;
        int connections = Integer.MAX_VALUE;

        for (int i=0; i < switches.size(); i++) {
            SwitchToDateMap map = switches.get(i);
            CustomList<LocalDateTime> validTimes = new OwnList<>();

            for (int j = 0; j < map.getTimestamps().size(); j++) {
                LocalDateTime timeStamp = map.getTimestamps().get(j);
                if (timeStamp.isBefore(end) && timeStamp.isAfter(start)) {
                    validTimes.add(timeStamp);
                }
            }

            if (validTimes.size() < connections) {
                minConSwitch = map.getSwitch();
                connections = validTimes.size();
            }
        }
        return minConSwitch;
    }



}
