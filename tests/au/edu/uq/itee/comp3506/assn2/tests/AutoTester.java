package au.edu.uq.itee.comp3506.assn2.tests;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import au.edu.uq.itee.comp3506.assn2.ADTs.CustomList;
import au.edu.uq.itee.comp3506.assn2.DataStructures.*;
import au.edu.uq.itee.comp3506.assn2.Searching.Network;
import au.edu.uq.itee.comp3506.assn2.Searching.SwitchToDateMap;
import au.edu.uq.itee.comp3506.assn2.api.TestAPI;
import au.edu.uq.itee.comp3506.assn2.entities.*;

/**
 * Hook class used by automated testing tool.
 * The testing tool will instantiate an object of this class to test the functionality of your assignment.
 * You must implement the method and constructor stubs below so that they call the necessary code in your application.
 * 
 * @author Ali Nawaz Maan
 */
public final class AutoTester implements TestAPI {

	// Filename of switches
	private String switchesFile = "./data/switches.txt";
	// Filename of call records
	private String callRecordsFile = "./data/call-records.txt";
	// List of phone nodes
	private CustomList<PhoneNode> phoneNodesList = new OwnList<>();
	// List of SwitchToDateMaps
	private CustomList<SwitchToDateMap> switches;
	// List of Extended call record objects
	private CustomList<ExtendedRecord> extendedRecords = new SortedList<>();
	// A comparator for SwitchToDateMap
	private Comparator<SwitchToDateMap> switchToDateMapComparator = new SwitchToDateMapComparator();
	// A comparator for SwitchToDateMap
	private Comparator<ExtendedRecord> extendedRecordComparator = new ExtendedRecordComparator();
	// Network object for search implementations
	private Network network;


	/**
	 * Constructor for loading files and generating objects
	 */
	public AutoTester() {

		System.out.println(Runtime.getRuntime().maxMemory());
		long bef = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long beftime = System.currentTimeMillis();


		// Load switches
		try {
			BufferedReader reader = new BufferedReader(new FileReader(switchesFile));
			int switchesSize = Integer.parseInt(reader.readLine());
			CustomList<SwitchToDateMap> switches = new SortedList<>(switchesSize);
			String line;
			while ((line = reader.readLine()) != null) {

				switches.add(new SwitchToDateMap(Integer.parseInt(line)));
			}
			switches.sort(switchToDateMapComparator);
			this.switches = switches;
		}catch (FileNotFoundException f) {
			f.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}



		//Load call records
		Comparator<SwitchToDateMap> comparator = new SwitchToDateMapComparator();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(callRecordsFile));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] list = line.split(" +");
				try {
					Long dialler = Long.parseLong(list[0]);
					Long receiver = Long.parseLong(list[list.length - 2]);
					int diallerSwitch = Integer.parseInt(list[1]);
					int receiverSwitch = Integer.parseInt(list[list.length - 3]);
					LocalDateTime timetamp = LocalDateTime.parse(list[list.length - 1]);
					SwitchToDateMap map;
					List<Integer> path = new ArrayList<>();
					if (diallerSwitch != Integer.parseInt(list[2])) {
						throw new Exception(" Invalid");
					}
					int index;
					int s = 0;
					for (int i = 2; i < list.length - 3; i++) {
						map = new SwitchToDateMap(Integer.parseInt(list[i]));
						if (map.getSwitch() > 9999 && map.getSwitch() < 100000 && (index = switches.search(map, comparator)) != -1
								&& s != map.getSwitch()) {
							path.add(map.getSwitch());
							switches.get(index).addTimestamp(timetamp);
							s = map.getSwitch();
						}else {
							throw new Exception("Invalid");
						}
					}
					ExtendedRecord callRecord = new ExtendedRecord(dialler, receiver, diallerSwitch,receiverSwitch, path, timetamp);
					PhoneNode diallerNode = new PhoneNode(callRecord.getDialler());
					PhoneNode receiverNode = new PhoneNode(callRecord.getReceiver());
					int dialerIndex = phoneNodesList.get(diallerNode);
					if (dialerIndex != -1) {
						phoneNodesList.get(dialerIndex).addDialingPath(callRecord);
					}else {
						diallerNode.addDialingPath(callRecord);
						phoneNodesList.add(diallerNode);
					}
					int receiverIndex = phoneNodesList.get(receiverNode);
					if (receiverIndex != -1) {
						phoneNodesList.get(receiverIndex).addReceivingPath(callRecord);
					}else {
						receiverNode.addReceivingPath(callRecord);
						phoneNodesList.add(receiverNode);
					}
					this.extendedRecords.add(callRecord);
				} catch (Exception e) {
					//e.printStackTrace();
				}
			}
		} catch (FileNotFoundException f) {
			f.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		extendedRecords.sort(extendedRecordComparator);



		long aftime = System.currentTimeMillis();
		long af = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long diff = af-bef;
		System.out.println(diff);
		System.out.println((diff/1024)/1000);
		System.out.println("Time: " + (aftime-beftime));
		System.out.println("------------");
		this.network = new Network(this.phoneNodesList);

	}

	/**
	 * Tests search 1 from the assignment specification.
	 *
	 * @param dialler The phone number that initiated the calls.
	 * @return List of all the phone numbers called by dialler.
	 *         The list will contain duplicates of the receiver if the dialler called the receiver multiple times.
	 */
	@Override
	public List<Long> called(long dialler) {
		PhoneNode diallerPhone = new PhoneNode(dialler);
		CustomList<Long> called = network.getDialledPhones(diallerPhone);

		List<Long> list = new ArrayList<>();
		for (int i = 0; i < called.size(); i++) {
			list.add(called.get(i));
		}

		return list;
	}

	/**
	 * Tests search 1 from the assignment specification.
	 *
	 * @param dialler The phone number that initiated the calls.
	 * @param startTime Start of time period.
	 * @param endTime End of time period.
	 * @return List of all the phone numbers called by dialler between start and end time.
	 *         The list will contain duplicates of the receiver if the dialler called the receiver multiple times.
	 */
	@Override
	public List<Long> called(long dialler, LocalDateTime startTime, LocalDateTime endTime) {
		PhoneNode diallerPhone = new PhoneNode(dialler);
		CustomList<Long> called = network.getDialledPhones(diallerPhone, startTime, endTime);

		List<Long> list = new ArrayList<>();
		for (int i = 0; i < called.size(); i++) {
			list.add(called.get(i));
		}

		return list;
	}

	/**
	 * Tests search 2 from the assignment specification.
	 *
	 * @param receiver The phone number that received the calls.
	 * @return List of all the phone numbers that called the receiver.
	 *         The list will contain duplicates of the caller if they called the receiver multiple times.
	 */
	@Override
	public List<Long> callers(long receiver) {
		PhoneNode recieverPhone = new PhoneNode(receiver);
		CustomList<Long> received = network.getReceivedPhones(recieverPhone);

		List<Long> list = new ArrayList<>();
		for (int i = 0; i < received.size(); i++) {
			list.add(received.get(i));
		}

		return list;
	}

	/**
	 * Tests search 2 from the assignment specification.
	 *
	 * @param receiver The phone number that received the calls.
	 * @param startTime Start of time period.
	 * @param endTime End of time period.
	 * @return List of all the phone numbers that called the receiver between start and end time.
	 *         The list will contain duplicates of the caller if they called the receiver multiple times.
	 */
	@Override
	public List<Long> callers(long receiver, LocalDateTime startTime, LocalDateTime endTime) {
		PhoneNode recieverPhone = new PhoneNode(receiver);
		CustomList<Long> received = network.getReceivedPhones(recieverPhone, startTime, endTime);

		List<Long> list = new ArrayList<>();
		for (int i = 0; i < received.size(); i++) {
			list.add(received.get(i));
		}

		return list;
	}

	/**
	 * Tests search 3 from the assignment specification.
	 *
	 * @param dialler The phone number that initiated the calls.
	 * @return The list of identifiers of the faulty switches or an empty list if no fault was found.
	 */
	@Override
	public List<Integer> findConnectionFault(long dialler) {
		PhoneNode diallerPhone = new PhoneNode(dialler);
		CustomList<Integer> faults = network.findConnectionFaults(diallerPhone);
		List<Integer> faultsToReturn = new ArrayList<>();

		for (int i = 0; i < faults.size(); i++) {
			faultsToReturn.add(faults.get(i));
		}

		return faultsToReturn;
	}

	/**
	 * Tests search 3 from the assignment specification.
	 *
	 * @param dialler The phone number that initiated the calls.
	 * @param startTime Start of time period.
	 * @param endTime End of time period.
	 * @return The list of identifiers of the faulty switches;
	 *         or an empty list if no fault was found between start and end time.
	 */
	@Override
	public List<Integer> findConnectionFault(long dialler, LocalDateTime startTime, LocalDateTime endTime) {
		PhoneNode diallerPhone = new PhoneNode(dialler);
		CustomList<Integer> faults = network.findConnectionFaults(diallerPhone, startTime, endTime);
		List<Integer> faultsToReturn = new ArrayList<>();

		for (int i = 0; i < faults.size(); i++) {
			faultsToReturn.add(faults.get(i));
		}

		return faultsToReturn;
	}

	/**
	 * Tests search 3 from the assignment specification.
	 *
	 * @param reciever The phone number that should have received the calls.
	 * @return The list of identifiers of the faulty switches or an empty list if no fault was found.
	 */
	@Override
	public List<Integer> findReceivingFault(long reciever) {
		PhoneNode receiverPhone = new PhoneNode(reciever);
		CustomList<Integer> faults = network.findReceivingFaults(receiverPhone);
		List<Integer> faultsToReturn = new ArrayList<>();

		for (int i = 0; i < faults.size(); i++) {
			faultsToReturn.add(faults.get(i));
		}

		return faultsToReturn;
	}

	/**
	 * Tests search 3 from the assignment specification.
	 *
	 * @param reciever The phone number that should have received the calls.
	 * @param startTime Start of time period.
	 * @param endTime End of time period.
	 * @return The list of identifiers of the faulty switches;
	 *         or an empty list if no fault was found between start and end time.
	 */
	@Override
	public List<Integer> findReceivingFault(long reciever, LocalDateTime startTime, LocalDateTime endTime) {
		PhoneNode receiverPhone = new PhoneNode(reciever);
		CustomList<Integer> faults = network.findReceivingFaults(receiverPhone, startTime, endTime);
		List<Integer> faultsToReturn = new ArrayList<>();

		for (int i = 0; i < faults.size(); i++) {
			faultsToReturn.add(faults.get(i));
		}

		return faultsToReturn;
	}

	/**
	 * Tests search 4 from the assignment specification.
	 *
	 * @return The identifier of the switch that had the most connections.
	 *         If multiple switches have the most connections, the smallest switch identifier is returned.
	 */
	@Override
	public int maxConnections() {
		return network.maxConnections(switches);
	}

	/**
	 * Tests search 4 from the assignment specification.
	 *
	 * @param startTime Start of time period.
	 * @param endTime End of time period.
	 * @return The identifier of the switch that had the most connections between start and end time.
	 *         If multiple switches have the most connections, the smallest switch identifier is returned.
	 */
	@Override
	public int maxConnections(LocalDateTime startTime, LocalDateTime endTime) {
		return network.maxConnections(switches, startTime, endTime);
	}

	/**
	 * Tests search 5 from the assignment specification.
	 *
	 * @return The identifier of the switch that had the fewest connections.
	 *         If multiple switches have the fewest connections, the smallest switch identifier is returned.
	 */
	@Override
	public int minConnections() {
		return network.minConnections(switches);
	}

	/**
	 * Tests search 5 from the assignment specification.
	 *
	 * @param startTime Start of time period.
	 * @param endTime End of time period.
	 * @return The identifier of the switch that had the fewest connections between start and end time.
	 *         If multiple switches have the fewest connections, the smallest switch identifier is returned.
	 */
	@Override
	public int minConnections(LocalDateTime startTime, LocalDateTime endTime) {
		return network.minConnections(switches, startTime, endTime);
	}

	/**
	 * Tests search 6 from the assignment specification.
	 *
	 * @param startTime Start of time period.
	 * @param endTime End of time period.
	 * @return List of details of all calls made between start and end time.
	 */
	@Override
	public List<CallRecord> callsMade(LocalDateTime startTime, LocalDateTime endTime) {
		List<CallRecord> toReturn = new ArrayList<>();

		int startIndex = -1;
		for (int i = 0; i<extendedRecords.size(); i++) {
			if (extendedRecords.get(i).getTimeStamp().isEqual(startTime) || extendedRecords.get(i).getTimeStamp().isAfter(startTime)) {
				startIndex = i;
				break;
			}
		}
		ExtendedRecord e;

		if (startIndex != -1) {
			do {
				e = extendedRecords.get(startIndex);
				toReturn.add(e);
				startIndex++;
			}while (e.getTimeStamp().isBefore(endTime) || e.getTimeStamp().isEqual(endTime));
		}

		return toReturn;
	}
	
	public static void main(String[] args) {

		AutoTester tester = new AutoTester();

		LocalDateTime start = LocalDateTime.parse("2017-09-07T03:04:55.529");
		LocalDateTime end = LocalDateTime.parse("2017-09-16T16:40:29.461");

		long stime = System.currentTimeMillis();

		System.out.println(tester.called(5618941102L));
		System.out.println(tester.called(5618941102L, start, end));
		System.out.println(tester.callers(5618941102L));
		System.out.println(tester.callers(5618941102L, start, end));
		System.out.println(tester.findConnectionFault(5618941102L));
		System.out.println(tester.findConnectionFault(5618941102L, start, end));
		System.out.println(tester.findReceivingFault(5618941102L));
		System.out.println(tester.findReceivingFault(5618941102L, start, end));
		System.out.println(tester.maxConnections());
		System.out.println(tester.maxConnections(start, end));
		System.out.println(tester.minConnections());
		System.out.println(tester.minConnections(start, end));
		System.out.println(tester.callsMade(start, end));

		long etime = System.currentTimeMillis();
		System.out.println("Time: " + (etime-stime));
	}
}