package au.edu.uq.itee.comp3506.assn2.Searching;

import au.edu.uq.itee.comp3506.assn2.ADTs.CustomList;
import au.edu.uq.itee.comp3506.assn2.DataStructures.OwnList;
import au.edu.uq.itee.comp3506.assn2.DataStructures.Path;
import au.edu.uq.itee.comp3506.assn2.DataStructures.PhoneNode;
import au.edu.uq.itee.comp3506.assn2.DataStructures.SortedList;
import au.edu.uq.itee.comp3506.assn2.DataStructures.SwitchToDateMapComparator;
import au.edu.uq.itee.comp3506.assn2.entities.CallRecord;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Loader {

    String switchesFile;
    String callRecordsFile;
    CustomList<PhoneNode> phoneNodesList = new OwnList<>();
    CustomList<SwitchToDateMap> switches;


    public Loader(String switchesFile, String callRecordsFile) {
        this.switchesFile = switchesFile;
        this.callRecordsFile = callRecordsFile;
        loadSwitches();
        loadCallRecords();
    }


    private void loadCallRecords() {

        Comparator<SwitchToDateMap> comparator = new SwitchToDateMapComparator();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(callRecordsFile));
            String line;

            while ((line = reader.readLine()) != null) {

                String[] list = line.split(" +");

                try {
                    Long dialler = Long.parseLong(list[0]);
                    Long receiver = Long.parseLong(list[list.length - 2]);
                    Integer diallerSwitch = Integer.parseInt(list[1]);
                    Integer receiverSwitch = Integer.parseInt(list[list.length - 3]);
                    LocalDateTime timetamp = LocalDateTime.parse(list[list.length - 1]);


                    SwitchToDateMap map;
                    List<Integer> path = new ArrayList<>();
                    int index;

                    for (int i = 2; i < list.length - 3; i++) {
                        map = new SwitchToDateMap(Integer.parseInt(list[i]));
                        if (map.getSwitch() > 9999 && map.getSwitch() < 100000 && (index = switches.search(map, comparator)) != -1) {
                            path.add(map.getSwitch());
                            switches.get(index).addTimestamp(timetamp);
                        }
                    }

                    CallRecord callRecord = new CallRecord(dialler, receiver, diallerSwitch,receiverSwitch, path, timetamp);


                    PhoneNode diallerNode = new PhoneNode(callRecord.getDialler());
                    PhoneNode receiverNode = new PhoneNode(callRecord.getReceiver());
                    Path callPath = new Path(callRecord);

                    int dialerIndex = phoneNodesList.get(diallerNode);
                    if (dialerIndex != -1) {
                        phoneNodesList.get(dialerIndex).addDialingPath(callPath);
                    }else {
                        diallerNode.addDialingPath(callPath);
                        phoneNodesList.add(diallerNode);
                    }

                    int receiverIndex = phoneNodesList.get(receiverNode);
                    if (receiverIndex != -1) {
                        phoneNodesList.get(receiverIndex).addReceivingPath(callPath);
                    }else {
                        receiverNode.addReceivingPath(callPath);
                        phoneNodesList.add(receiverNode);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException f) {
            f.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadSwitches() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(switchesFile));

            int switchesSize = Integer.parseInt(reader.readLine());

            CustomList<SwitchToDateMap> switches = new SortedList<>(switchesSize);
            String line;
            while ((line = reader.readLine()) != null) {

                switches.add(new SwitchToDateMap(Integer.parseInt(line)));
            }

            Comparator<SwitchToDateMap> comparator = new SwitchToDateMapComparator();
            switches.sort(comparator);

            this.switches = switches;

        }catch (FileNotFoundException f) {
            f.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public CustomList<PhoneNode> getPhoneNodesList() {
        return phoneNodesList;
    }

    public CustomList<SwitchToDateMap> getSwitches() {
        return switches;
    }
}
