package BookingSystem;

import java.util.ArrayList;
import java.util.Dictionary;
public interface ISearchTrainDictionary {

//	parameters: paras: 				 {"startStationID", "endStationID", "date", "time"}
//  parameters: sample format: {"0990",  "0110", "2021-02-02", "MM:ss"}
	
// 
//	return: JSONArray: [{ticketID, "train number", "departure time", "arrival time", "travel time", "empty seat":{"full","university"}}]
	public ArrayList<Dictionary<String, String>> searchTicketByTime(Dictionary<String, String> searchConditionDict);

	
	//	parameters: paras: 				 {"startStationID", "endStationID", "TrainID"}
	//  parameters: sample format: {"0990",  "0110", "0565"}
	
	// return: a JSON {ticketID, "train number", "departure time", "arrival time", "travel time", "empty seat number":{"full","university"}}
	public ArrayList<Dictionary<String, String>> searchTicketByStationID(Dictionary<String, String> searchConditionDict);
}
