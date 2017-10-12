package au.edu.uq.itee.comp3506.assn2.tests;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import au.edu.uq.itee.comp3506.assn2.ADTs.CustomList;
import au.edu.uq.itee.comp3506.assn2.DataStructures.PhoneNode;
import au.edu.uq.itee.comp3506.assn2.Searching.Loader;
import au.edu.uq.itee.comp3506.assn2.Searching.Network;
import au.edu.uq.itee.comp3506.assn2.api.TestAPI;
import au.edu.uq.itee.comp3506.assn2.entities.*;

/**
 * Hook class used by automated testing tool.
 * The testing tool will instantiate an object of this class to test the functionality of your assignment.
 * You must implement the method and constructor stubs below so that they call the necessary code in your application.
 * 
 * @author 
 */
public final class AutoTester implements TestAPI {

	Network network;
	Loader loader;


	public AutoTester() {

		this.loader  = new Loader("data/switches.txt", "data/call-records-faulty.txt");
		CustomList<PhoneNode> phoneNodeList = loader.getPhoneNodesList();
		this.network = new Network(phoneNodeList);

	}
	
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

	@Override
	public int maxConnections() {
		return network.maxConnections(loader.getSwitches());
	}

	@Override
	public int maxConnections(LocalDateTime startTime, LocalDateTime endTime) {
		return network.maxConnections(loader.getSwitches(), startTime, endTime);
	}

	@Override
	public int minConnections() {
		return network.minConnections(loader.getSwitches());
	}

	@Override
	public int minConnections(LocalDateTime startTime, LocalDateTime endTime) {
		return network.minConnections(loader.getSwitches(), startTime, endTime);
	}

	@Override
	public List<CallRecord> callsMade(LocalDateTime startTime, LocalDateTime endTime) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) {
		//AutoTester test = new AutoTester();

		System.out.println("AutoTester Stub");
	}
}