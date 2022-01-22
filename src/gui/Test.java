package gui;
import java.util.ArrayList;
public class Test {

	public static void main(String[] args) {
		System.out.println("launching...");
		new Controller();
	}
	
//	parameters: paras: ["uid", "起站/終站", " 標準車廂/商務車廂", "無/靠窗/走道", “一時間搜尋/輸入車次”, "2021-02-02","3"      ,  "1920", "2"     , "3" ]
//	parameters: paras: ["uid", "起迄站"   , " 車廂種類"       , "座位喜好"    , “訂位方式”         , "日期"       ,"幾點出發" , "車次", "全票票數", "大學生票數" ]
//	return: [index, "train number", "departure time", "arrival time", "travel time"]
	public ArrayList<String> searchCandidate(ArrayList<String> paras) {
		ArrayList<String> a = null;
		return a;
	}
	
//  parameters: index: corresponding searchCandidate's return index, buy the selected ticket 
//	return: boolean: successful booking or not
	public Boolean selectedCandidate(int index) {
		Boolean a = null;
		return a;
	}
}


