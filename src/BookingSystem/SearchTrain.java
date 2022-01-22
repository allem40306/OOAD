package BookingSystem;

import backend.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.*;

import static java.lang.Integer.*;

public class SearchTrain implements ISearchTrainDictionary{
    private TimeTable timeTable = new TimeTable();
   

    // input: date, YYYY-MM-DD
    // output: one of weekDays, such as "Sunday"
    public static String getWeekOfDate(Date date) {
        String[] weekDays = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
        Calendar calendarTool = Calendar.getInstance();
        calendarTool.setTime(date);
        int w = calendarTool.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) w = 0;
        return weekDays[w];
    }


    public ArrayList<Dictionary<String, String>> searchTicketByTime(Dictionary<String, String> searchConditionDict){
        ArrayList<Dictionary<String,String>> dict = new ArrayList<Dictionary<String, String>>() ;
        Date searchDate = null, searchTime = null;
        DateFormat hoursMinutesDateFormat = new SimpleDateFormat("HH:mm");

	    try {
	    	searchDate = new SimpleDateFormat("yyyy-MM-dd").parse(searchConditionDict.get("date"));
//            searchTime = hoursMinutesDateFormat.parse(searchConditionDict.get("time"));
	    } catch (ParseException e) {
	    	// TODO Auto-generated catch block
	    	e.printStackTrace();
	    }
        
        String searchStartStationID = searchConditionDict.get("startStationID");
        String searchEndStationID = searchConditionDict.get("endStationID");
        
        // 每筆資料檢查是否符合 searchCondition
        // searchCodition: {"startStationID", "endStationID", "date", "time"}
        for (int row = 0; row < timeTable.getSize(); row++) {
            // check whether available in that day
            if (!timeTable.get(row, "GeneralTimetable", "ServiceDay", getWeekOfDate(searchDate)).equals("1"))
            	continue;
            
            // check if the direction is right, 
            // 車站代號是由北向南遞增，最南邊車站代號最大、最北邊車站代號最小
            // 1 是北上, 0 是南下
            int direction = parseInt(searchStartStationID) > parseInt(searchEndStationID) ? 1 : 0;
            if(direction != parseInt(timeTable.get(row, "GeneralTimetable", "GeneralTrainInfo", "Direction")))
            	continue;
            
            // check if the searchStart and searchEnd station ID are in the range of (this row of the time table)'s beginning and terminal station
            String trainStartStationID = timeTable.get(row, "GeneralTimetable", "GeneralTrainInfo", "StartingStationID");
            String trainEndStationID = timeTable.get(row, "GeneralTimetable", "GeneralTrainInfo", "EndingStationID");
            
            if((direction == 0 && parseInt(searchStartStationID) >= parseInt(trainStartStationID) && parseInt(searchEndStationID) <= parseInt(trainEndStationID))
            	||(direction == 1 && parseInt(searchStartStationID) <= parseInt(trainStartStationID) && parseInt(searchEndStationID) >= parseInt(trainEndStationID))) {
	             
            	Dictionary<String, String> temp = new Hashtable<String, String> ();
	            String trainNo = timeTable.get(row, "GeneralTimetable", "GeneralTrainInfo", "TrainNo");
	            String startTime = null;
	            String endTime = null;
	            for (int j = 0; j < timeTable.getNumOfStopTimes(row); j++)
	            {
		            if(searchStartStationID.equals(timeTable.get(row, j, "GeneralTimetable", "StopTimes", "StationID")))
		            	startTime = timeTable.get(row, j, "GeneralTimetable", "StopTimes", "DepartureTime");
		            
		            if(searchEndStationID.equals(timeTable.get(row, j, "GeneralTimetable", "StopTimes", "StationID")))
		            	endTime = timeTable.get(row, j, "GeneralTimetable", "StopTimes", "DepartureTime");
	            }
               
	            // timeTable.get(timeTable.getRowIndex(listOfValidTrainNo.get(i)[3]), j, "GeneralTimetable", "StopTimes", "DepartureTime");
	            long intervalTime = 0;
	            
		    	try {
			        intervalTime = hoursMinutesDateFormat.parse(endTime).getTime() - hoursMinutesDateFormat.parse(startTime).getTime();
			        long hour = intervalTime / 60000 / 60;
			        long ms = intervalTime / 60000 % 60;
		            String travelTime = Long.toString(hour) + ":" + Long.toString(ms); 
		            temp.put("train number", trainNo);
		            temp.put("departure time", startTime);
		            temp.put("arrival time", endTime);
		            temp.put("travel time", travelTime);
		            dict.add(temp);
		    	} catch (ParseException e) {
		    	    // TODO Auto-generated catch block
		    	    e.printStackTrace();
		    	} catch(NullPointerException e2) {
		    	   
		    	}
		    	
            }
            
        }
        
        return dict;
    }
    
    public ArrayList<Dictionary<String, String>> searchTicketByStationID(Dictionary<String, String> searchConditionDict){
    	return new ArrayList<Dictionary<String, String>>();
    }
}
