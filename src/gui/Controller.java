package gui;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.ZonedDateTime;

import javax.swing.JOptionPane;
import BookingSystem.*;
import backend.SoldBuySeatException;
import backend.TicketManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Controller {
	private ISearchTrainDictionary searchTrainDict;
	private HomeView homeView;
	private BookView bookView;
	private CancelView cancelView;
	private SearchBookView searchBookView;
	private BookTicket bt;
	ArrayList<Dictionary<String, String>> ISD;
	
	public Controller() {
		//aTicketManager = new TicketManager();
		searchTrainDict = new SearchTrain();
		bt = new BookTicket();
		// 主頁面
		homeView = new HomeView();
		homeView.addGoBookButtonListener(new GoBookButtonListener());
		homeView.addGoCancelButtonListener(new GoCancelButtonListener());
		homeView.addGoSearchButtonListener(new GoSearchBookButtonListener());
		
		// 訂票頁面
		bookView = new BookView();
		bookView.addSearchingButtonListener(new SearchingButtonListener());
		bookView.addBookingButtonListener(new BookingButtonListener());
		bookView.addHomeButtonListener(new GoHomeListener());

		// 退票頁面
		cancelView = new CancelView();
		cancelView.addCancelButtonListener(new CancelButtonListerner());
		cancelView.addHomeButtonListener(new GoHomeListener());

		// 訂票查詢頁面
		searchBookView = new SearchBookView();
		searchBookView.addSearchingButtonListener(new GoSearchButtonListener());
		searchBookView.addHomeButtonListener(new GoHomeListener());

		homeView.setVisible(true);
	}
	

	// GoHomeView
	public class GoHomeListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			homeView.setVisible(true);
			bookView.setVisible(false);
			bookView.clear();
			cancelView.setVisible(false);
			cancelView.clear();
			searchBookView.setVisible(false);
			searchBookView.clear();
		}
	}

	// HomeView Begins
	public class GoBookButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			bookView.setVisible(true);
			homeView.setVisible(false);
		}
	}
	
	public class GoCancelButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			cancelView.setVisible(true);
			homeView.setVisible(false);
		}
	}

	public class GoSearchBookButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			searchBookView.setVisible(true);
			homeView.setVisible(false);
		}
	}
	// HomeView Ends
	
	
	// CancelView Begins
	// handle cancelView enter button
	public class CancelButtonListerner implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String result = bt.Unbooking(cancelView.getReferNumber(), cancelView.getuidIn());
			try {
				JOptionPane.showMessageDialog(searchBookView, "Canceled tickets: " + result);
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
		// SearchBookView Begins
	public class GoSearchButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			String a = searchBookView.getReferNumber() + " " + searchBookView.getuidIn();
	        System.out.println(a);
			try {
				JOptionPane.showMessageDialog(searchBookView, "BookingHistory: " + bt.BookingHistory(searchBookView.getReferNumber(), searchBookView.getuidIn()));
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	// SearchBookView Ends

	// BookView Begins
	// BookView 查詢車次
	public class SearchingButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// search candidate tickets
			Dictionary<String, String> inputForSearchCandidate = new Hashtable<String, String>();
			inputForSearchCandidate.put("startStationID", "1000");
			inputForSearchCandidate.put("endStationID", "1030");
			inputForSearchCandidate.put("date","2021-02-02");
			System.out.println("The Dictionary is: " + inputForSearchCandidate); 
			ISD = searchTrainDict.searchTicketByTime(inputForSearchCandidate);
			System.out.println("ISD: " + ISD); 
			for (int i=0; i<ISD.size(); i++) {
				bookView.output("("+(i+1)+")\n");
				bookView.output("Train number: "+ ISD.get(i).get("train number")+"\n");
				bookView.output("departure time: "+ ISD.get(i).get("departure time")+"\n");
				bookView.output("arrival time: "+ ISD.get(i).get("arrival time")+"\n");
				bookView.output("travel time: "+ ISD.get(i).get("travel time")+"\n");
				bookView.output("----------------------\n");
			}
			
			
//			order.setCode(index);
//			aTicketManager = new TicketManager();
//			try {
//				order.setRoundTrip("0");
//			} catch (wrongInputException e2) {
//				e2.printStackTrace();
//			}
//			try {
//				order.setUid(bookView.getuidIn());
//			} catch (wrongInputException e2) {
//				e2.printStackTrace();
//			}
//			order.setStartStationID(bookView.getStationFromID());
//			order.setEndStationID(bookView.getStationToID());
//			order.setTicketType(bookView.getStandardOrBusiness());
//			order.setPreference(bookView.getSeatPrefer());
//			try {
//				order.setGeneralTicket(String.valueOf(bookView.getNumberOfTicket()));
//			} catch (wrongInputException e2) {
//				e2.printStackTrace();
//			}
//			try {
//				order.setUniversityTicket(String.valueOf(bookView.getNumberOfDiscount()));
//			} catch (wrongInputException e2) {
//				e2.printStackTrace();
//			}
//			String searchChoice = bookView.getOrderType();
//			if (searchChoice.equals("0")) {
//				order.setDate(bookView.getTimeIn());
//				try {
//					order.findValidTrainNo(bookView.getToTime());
//				} catch (SoldBuySeatException e2) {
//					e2.printStackTrace();
//				}
//				try {
//					bookView.output(order.showValidTrainInfo());
//				} catch (ParseException e1) {
//					e1.printStackTrace();
//				}
//			} else if (searchChoice.equals("1")) {
//				order.setDate(bookView.getTimeIn());
//				order.setListOfValidTrainNo2(bookView.getTrainNumber());
//				try {
//					bookView.output(order.showValidTrainInfo());
//				} catch (ParseException e1) {
//					e1.printStackTrace();
//				}
//			} else
//				System.out.println("Invalid input!");
		}
	}
	
	// BookView 確認購票button綁定function
	public class BookingButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Dictionary<String, String> inputForselectCandidate = new Hashtable<String, String>();
			inputForselectCandidate.put("standard_ticket_count", bookView.getNumberOfTicket()+"");
			inputForselectCandidate.put("student_ticket_count", bookView.getNumberOfDiscount()+"");
			inputForselectCandidate.put("uid", bookView.getuidIn());
			inputForselectCandidate.put("train_number", ISD.get( bookView.getOrderIndex() ).get( "train number" ) );
			inputForselectCandidate.put("departure_time",  ISD.get( bookView.getOrderIndex() ).get( "departure time" ) );
			inputForselectCandidate.put("arrival_time", ISD.get( bookView.getOrderIndex() ).get( "arrival time" ) );
			inputForselectCandidate.put("travel_time", ISD.get( bookView.getOrderIndex() ).get( "travel time" ) );
			inputForselectCandidate.put("start", bookView.getStationFromID());
			inputForselectCandidate.put("end", bookView.getStationToID());
			
//			inputForselectCandidate.put("seat", "0413A");
			
//			inputForselectCandidate.put("standard_ticket_count", "1");
//			inputForselectCandidate.put("student_ticket_count", "1");
//			inputForselectCandidate.put("uid", "A123456789");
//			inputForselectCandidate.put("train_number", "0108" );
//			inputForselectCandidate.put("departure_time",  "" );
//			inputForselectCandidate.put("arrival_time", "11:11" );
//			inputForselectCandidate.put("travel_time", "11:11" );
//			inputForselectCandidate.put("start", "0990");
//			inputForselectCandidate.put("end", "100");
//			inputForselectCandidate.put("seat", ISD.get( bookView.getOrderIndex() ).get( "travel time" ));
			
			
			System.out.println("inputForselectCandidate "+inputForselectCandidate);
			
			IBookTicketDictionary bt = new BookTicket();
			boolean success = bt.selectCandidate(inputForselectCandidate);
			bookView.getuidIn();
			ISD.get( bookView.getOrderIndex() ).get( "departure time" );
			ISD.get( bookView.getOrderIndex() ).get( "arrival time" );
			if(success)
				JOptionPane.showMessageDialog(bookView, "Succeess");
			System.out.println("success or not "+bt);
			
			
//			order.setValidIndex(bookView.getOrderIndex());
//			try {
//				order.sellTicket();
//			} catch (SoldBuySeatException e1) {
//				e1.printStackTrace();
//			}
//			try {
//				order.paymentDeadlineCalculator();
//			} catch (ParseException e1) {
//				e1.printStackTrace();
//			}
//			order.totalPriceCalculator();
//			aTicketManager.addTicket(order.getaTicket());
//			aTicketManager.save("booking.json");
//			String code = aTicketManager.getTicketObj(index + 1).getCode();
//			String uid = aTicketManager.getTicketObj(index + 1).getUid();
//			String date_0 = aTicketManager.getTicketObj(index + 1).getTicketInfo(0, "date");
//			String type_0 = aTicketManager.getTicketObj(index + 1).getTicketInfo(0, "ticketsType");
//			String generalTicket_0 = aTicketManager.getTicketObj(index + 1).getTicketInfo(0, "ticketsCountA");
//			String uniTicket_0 = aTicketManager.getTicketObj(index + 1).getTicketInfo(0, "ticketsCountB");
//			String start_0 = aTicketManager.getTicketObj(index + 1).getTicketInfo(0, "start");
//			String end_0 = aTicketManager.getTicketObj(index + 1).getTicketInfo(0, "end");
//			String seat_0 = aTicketManager.getTicketObj(index + 1).getTicketInfo(0, "seats");
//			String price = aTicketManager.getTicketObj(index + 1).getPayment();
//			String deadline = aTicketManager.getTicketObj(index + 1).getPayDeadLine();
//			String information = "Ticket code: " + code + "\nUser ID: " + uid + "\nDate: " + date_0 + "\nTicket type: "
//					+ type_0 + "\nNumber of general tickets: " + generalTicket_0
//					+ "\nNumber of university discount tickets: " + uniTicket_0 + "\nStart station: " + start_0
//					+ "\nEnd station: " + end_0 + "\nSeat: " + seat_0 + "\nTotal price: " + price
//					+ "\nThe payment deadline: " + deadline;
//			JOptionPane.showMessageDialog(bookView, information);
//			order = new generalBooking();
//			index = index + 1;
		}
	}
	//BookView Ends

}
