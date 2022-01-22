package BookingSystem;

import java.util.ArrayList;
import java.util.Dictionary;

public interface IBookTicketDictionary {
//  parameters: input: {"standard_ticket_count", "student_ticket_count", "uid", "train_number", "departure_time", "arrival_time", "travel_time", "start", "end", "seat"}
//	return: boolean: successful booking or not
//	public Boolean selectCandidate(ArrayList<String> input);

	/**  
	 * parameters: input: {"standard_ticket_count", "student_ticket_count", "uid", "train_number", 
	 * 						"departure_time", "arrival_time", "travel_time", 
	 * 						"start", "end", "seat"}
	*/
	public Boolean selectCandidate(Dictionary<String, String> input);
	public String Unbooking(String code, String uid);
	

}
