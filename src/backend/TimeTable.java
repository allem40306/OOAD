package backend;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import org.json.*;

/**
 * This is the class used to handle timeTable.json
 * @author kobemary
 * 
 * timeTable.json:
 * JSONArray, 存放182筆JSON, 
 * 每筆的JSON["GeneralTimetable"]["GeneralTrainInfo"]["TrainNo"]都是 unique (因為是車次代碼)
 * [{
    "UpdateTime": "2021-03-27",
    "EffectiveDate": "2020-10-12",
    "ExpiringDate": "",
    "GeneralTimetable": {
      "GeneralTrainInfo": {
        "TrainNo": "0639",
        "Direction": 0,               0往南 -> "StartingStationID" < "EndingStationID"
                                      1往北 -> "StartingStationID" > "EndingStationID"
        "StartingStationID": "0990",
        "StartingStationName": {
          "Zh_tw": "南港",
          "En": "Nangang"
        },
        "EndingStationID": "1070",
        "EndingStationName": {
          "Zh_tw": "左營",
          "En": "Zuoying"
        }
      },
      "StopTimes": [
        {
          "StopSequence": 1,
          "StationID": "0990",
          "StationName": {
            "Zh_tw": "南港",
            "En": "Nangang"
          },
          "DepartureTime": "12:35"
        }],
      "ServiceDay": {
        "Monday": 1,
        "Tuesday": 1,
        "Wednesday": 1,
        "Thursday": 1,
        "Friday": 1,
        "Saturday": 1,
        "Sunday": 1
      },
      "SrcUpdateTime": "2021-03-27T15:26:02+08:00"
    }
  }]
 */
public class TimeTable {

    private static final String filePath = new String(System.getProperty("user.dir")
                                                        + "/src/backend/file/timeTable.json");
    private ArrayList<JSONObject> timetTableArrayList = new ArrayList<JSONObject>();

    public TimeTable() {

        // Read JSON file and convert String.
        String jsonString = new String();
        try {
            // load file
            File file =  new File(filePath);
            Scanner scan = new Scanner(file);
            while(scan.hasNext()) {
                jsonString = jsonString + scan.nextLine();
            }
        } catch(Exception e) {
            System.out.println("There may be something wrong with file path:" + filePath);
        }

        // Convert the json string to JSONArray
        JSONArray timetTableArray = new JSONArray(jsonString);

        // Convert JSONArray to the ArrayList of the JSONObject
        // JSONObject 才是一個物件概念 (JSONArray只是Array)，可以用JSONObject.get去抓資訊。
        for (int i = 0; i < timetTableArray.length(); i++) {
            JSONObject temp = new JSONObject(timetTableArray.getJSONObject(i).toString());
            timetTableArrayList.add(temp);
        }
    }


    /**
     * 輸入你要第幾row的StopTimes當作參數即可得StopTimes數量
     * @param row an int type to indicate the index of row
     * @return the int-type number of elements in StopTimes array
     */
    public int getNumOfStopTimes(int row) {
        int num = timetTableArrayList.get(row).getJSONObject("GeneralTimetable").getJSONArray("StopTimes").length();
        return num;
    }

    /**
     * Get the row index of a specific train in timeTable.json
     * @param trainNo String type to indicate the train number
     * @return int type to indicate the row index of the train
     */
    public int getRowIndex(String trainNo) {
        int i;
        for (i = 0; i < 182; i++) {
            if (trainNo.equals(timetTableArrayList.get(i).getJSONObject("GeneralTimetable").getJSONObject("GeneralTrainInfo").getString("TrainNo"))) {
                return i;
            }
        }
        return 0;
    }

    /**
     * 這專門用來拿取StopTimes裡的內容
     * eg object.get(0, 0, "GeneralTimetable", "StopTimes", "StationID"))
     * eg object.get(0, 0, "GeneralTimetable", "StopTimes", "StationName", "Zh_tw")
     * @param row int type to indicate which row in table you want to get
     * @param StopTimesIndex int type to indicate the index of StopTimes array
     * @param location String type arguments. You MUST specify clearly each key you want to get
     * @return A string type value you want to get
     */
    public String get(int row, int StopTimesIndex, String... location) {
        switch(location.length){
            case 3:
                if (location[2].equals("StopSequence")) {
                    //get StopSequence
                    int tmp = timetTableArrayList.get(row).getJSONObject(location[0]).getJSONArray(location[1]).getJSONObject(StopTimesIndex).getInt(location[2]);
                    return ""+tmp;

                }
                else{
                    //get StationID
                    return timetTableArrayList.get(row).getJSONObject(location[0]).getJSONArray(location[1]).getJSONObject(StopTimesIndex).getString(location[2]);

                }
            case 4:
                return timetTableArrayList.get(row).getJSONObject(location[0]).getJSONArray(location[1]).getJSONObject(StopTimesIndex).getJSONObject(location[2]).getString(location[3]);
            default:
                return "There may be something wrong with your location array: " +  Arrays.toString(location) + "which MUST be limited up to 4";
        }

    }


    /**
     * 用來拿timeTable的內容
     * 必須嚴謹地輸入你想要找的value中，所有會用到的key值
     * eg object.get(0, "GeneralTimetable", "SrcUpdateTime")
     * eg object.get(0, "GeneralTimetable", "GeneralTrainInfo", "StartingStationName", "Zh_tw")
     *
     * @param row int type to indicate which row in table you want to get
     * @param location string type arguments. You MUST specify clearly each key you want to get
     * @return A string type value you want to get
     */
    public String get(int row, String... location) {
        //System.out.println(location);

        switch(location.length) {
            case 1:
                // get UpdateTime, EffectiveDate or ExpiringDate
                return timetTableArrayList.get(row).getString(location[0]);
            case 2:
                //get SrcUpdateTime
                return timetTableArrayList.get(row).getJSONObject(location[0]).getString(location[1]);
            case 3:
                // get GeneralTrainInfo
                //System.out.println("case3");
                if (location[2].equals("Direction")) {
                    //get GeneralTrainInfo StopTimes TrainNo
                    return ""+timetTableArrayList.get(row).getJSONObject(location[0]).getJSONObject(location[1]).getInt(location[2]);
                }
                else if (location[1].equals("ServiceDay")) {
                    //get WeekDay in ServiceDay
                    return ""+timetTableArrayList.get(row).getJSONObject(location[0]).getJSONObject(location[1]).getInt(location[2]);
                }

                return timetTableArrayList.get(row).getJSONObject(location[0]).getJSONObject(location[1]).getString(location[2]);
            case 4:
                //get GeneralTimetable GeneralTrainInfo StartingStationName Zh_tw
                //System.out.println("case4");
                return timetTableArrayList.get(row).getJSONObject(location[0]).getJSONObject(location[1]).getJSONObject(location[2]).getString(location[3]);
            default:
                return "There may be something wrong with your location array: " +  Arrays.toString(location) + "which MUST be limited up to 4";

        }
    }


    private JSONObject getRow(int row) {
        return timetTableArrayList.get(row);
    }


    public String getRowString(int row) {
        return timetTableArrayList.get(row).toString();
    }

    /**
     * how many row in timeTable
     * @return int type that indicate the number of row
     */
    public int getSize() {
        return timetTableArrayList.size();
    }


    


//	TimeTable(String path){
//		String jsonString = new String();
//		try {
//			// load file
//			File file =  new File(filePath);
//			Scanner scan = new Scanner(file);
//			while(scan.hasNext()) {
//				jsonString = jsonString + scan.nextLine();
//			}
//		}catch(Exception e) {
//			System.out.println("There may be something wrong with file path:"+filePath);
//		}
//
//		this.timetTableArray = new JSONArray(jsonString);
//
//		// store as AarrayList
//		for (int i=0; i<timetTableArray.length(); i++) {
//			JSONObject tmp = new JSONObject(timetTableArray.getJSONObject(i).toString());
//			timetTableArrayList.add(tmp);
//		}
//	}

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        TimeTable t = new TimeTable();
        System.out.println(t.get(0, "UpdateTime"));
        System.out.println(t.get(0, "GeneralTimetable", "SrcUpdateTime"));
        System.out.println(t.get(0, "GeneralTimetable", "GeneralTrainInfo", "TrainNo"));
        System.out.println(t.get(0, "GeneralTimetable", "GeneralTrainInfo", "Direction"));
        System.out.println(t.get(0, "GeneralTimetable", "GeneralTrainInfo", "StartingStationName", "Zh_tw"));
        // get StopTimes
        System.out.println(t.get(0, 0, "GeneralTimetable", "StopTimes", "StopSequence"));
        System.out.println(t.get(0, 0, "GeneralTimetable", "StopTimes", "StationID"));
        System.out.println(t.get(0, 0, "GeneralTimetable", "StopTimes", "DepartureTime"));
        System.out.println(t.get(0, 0, "GeneralTimetable", "StopTimes", "StationName", "Zh_tw"));
        System.out.println(t.getRow(0));
        System.out.println(t.getRowString(0));
        // get the number of elements in StopTimes array
        System.out.println(t.getNumOfStopTimes(0));
        System.out.println(t.getNumOfStopTimes(1));
        // get ServiceDay
        System.out.println(t.get(0, "GeneralTimetable", "ServiceDay", "Monday"));
        System.out.println(t.get(0, "GeneralTimetable", "ServiceDay", "Wednesday"));



    }

}

