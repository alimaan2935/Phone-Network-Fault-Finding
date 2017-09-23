package au.edu.uq.itee.comp3506.assn2.api;

import java.util.List;
import java.time.LocalDateTime;

import au.edu.uq.itee.comp3506.assn2.entities.CallRecord;

/**
 * API used for automated testing of assignment 2.
 * Defines the methods used to test the application functionality.
 * 
 * @author Richard Thomas
 */
public interface TestAPI {
	/**
	 * Tests search 1 from the assignment specification.
	 * 
	 * @param dialler The phone number that initiated the calls.
	 * @return List of all the phone numbers called by dialler.
	 */
	List<Long> called(long dialler);
	
	/**
	 * Tests search 1 from the assignment specification.
	 * 
	 * @param dialler The phone number that initiated the calls.
	 * @param startTime Start of time period.
	 * @param endTime End of time period.
	 * @return List of all the phone numbers called by dialler between start and end time.
	 */
	List<Long> called(long dialler, LocalDateTime startTime, LocalDateTime endTime);
	
	/**
	 * Tests search 2 from the assignment specification.
	 * 
	 * @param receiver The phone number that received the calls.
	 * @return List of all the phone numbers that called the receiver.
	 */
	List<Long> callers(long receiver);
	
	/**
	 * Tests search 2 from the assignment specification.
	 * 
	 * @param receiver The phone number that received the calls.
	 * @param startTime Start of time period.
	 * @param endTime End of time period.
	 * @return List of all the phone numbers that called the receiver between start and end time.
	 */
	List<Long> callers(long receiver, LocalDateTime startTime, LocalDateTime endTime);
	
	/**
	 * Tests search 3 from the assignment specification.
	 * 
	 * @param dialler The phone number that initiated the calls.
	 * @return The list of identifiers of the faulty switches or an empty list if no fault was found.
	 */
	List<Integer> findConnectionFault(long dialler);

	/**
	 * Tests search 3 from the assignment specification.
	 * 
	 * @param dialler The phone number that initiated the calls.
	 * @param startTime Start of time period.
	 * @param endTime End of time period.
	 * @return The list of identifiers of the faulty switches;
	 *         or an empty list if no fault was found between start and end time.
	 */
	List<Integer> findConnectionFault(long dialler, LocalDateTime startTime, LocalDateTime endTime);
	
	/**
	 * Tests search 3 from the assignment specification.
	 * 
	 * @param reciever The phone number that should have received the calls.
	 * @return The list of identifiers of the faulty switches or an empty list if no fault was found.
	 */
	List<Integer> findReceivingFault(long reciever);

	/**
	 * Tests search 3 from the assignment specification.
	 * 
	 * @param reciever The phone number that should have received the calls.
	 * @param startTime Start of time period.
	 * @param endTime End of time period.
	 * @return The list of identifiers of the faulty switches;
	 *         or an empty list if no fault was found between start and end time.
	 */
	List<Integer> findReceivingFault(long reciever, LocalDateTime startTime, LocalDateTime endTime);
	
	/**
	 * Tests search 4 from the assignment specification.
	 * 
	 * @return The identifier of the switch that had the most connections.
	 */
	int maxConnections();
	
	/**
	 * Tests search 4 from the assignment specification.
	 * 
	 * @param startTime Start of time period.
	 * @param endTime End of time period.
	 * @return The identifier of the switch that had the most connections between start and end time.
	 */
	int maxConnections(LocalDateTime startTime, LocalDateTime endTime);
	
	/**
	 * Tests search 5 from the assignment specification.
	 * 
	 * @return The identifier of the switch that had the fewest connections.
	 */
	int minConnections();
	
	/**
	 * Tests search 5 from the assignment specification.
	 * 
	 * @param startTime Start of time period.
	 * @param endTime End of time period.
	 * @return The identifier of the switch that had the fewest connections between start and end time.
	 */
	int minConnections(LocalDateTime startTime, LocalDateTime endTime);
	
	/**
	 * Tests search 6 from the assignment specification.
	 * 
	 * @param startTime Start of time period.
	 * @param endTime End of time period.
	 * @return List of details of all calls made between start and end time.
	 */
	List<CallRecord> callsMade(LocalDateTime startTime, LocalDateTime endTime);
}