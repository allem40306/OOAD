package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.*;

//無訂購回程票的介面
public class BookView extends JFrame {
	private JPanel contentPane;
	private JTextField UIDTextField;
	private JLabel UIDLabel;
	private JLabel stationLabel;
	private JLabel stationFromLabel;
	private JLabel stationToLabel;
	private JComboBox stationFromComboBox;
	private JComboBox stationToComboBox;
	private JLabel carTypeLabel;
	private JRadioButton startardCarTypeRadioButton;
	private JRadioButton businessCarTypeRadioButton;
	private JLabel label_3;
	private JRadioButton radioButton2;
	private JRadioButton radioButton3;
	private JRadioButton radioButton4;
	private JLabel searchMethodLabel;
	private JRadioButton searchMethodTimeRadioButton;
	private JRadioButton searchMethodTrainNumberRadioButton;
	private JLabel dateLabel;
	private JLabel label_7;
	private JCheckBox checkBox;
	private JTextField dateTextField;
	private JTextField timeIn1;
	private JLabel dateFormatLabel;
	private JLabel label_9;
	private JLabel label_10;
	private JLabel label_11;
	private JComboBox comboBox2;
	private JComboBox comboBox3;
	private ButtonGroup buttonGroup;
	private ButtonGroup buttonGroup1;
	private ButtonGroup buttonGroup2;
	private JButton searchingButton;
	private JButton homeButton;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JTextField searchMethodTimeTextField;
	private JLabel searchMethodTimeLabel;
	private JLabel label_14;
	private JTextField fromTime;
	private JLabel label_15;
	private JLabel ticketNumberLabel;
	private JTextField ticketNumberTextField;
	private JButton enterButton;
	private JLabel searchMethodTrainNumberLabel;
	private JTextField searchMethodTrainNumberTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		BookView frame = new BookView();
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public BookView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		UIDLabel = new JLabel("UID");
		UIDLabel.setBounds(750, 401, 68, 19);
		contentPane.add(UIDLabel);

		UIDTextField = new JTextField("");
		UIDTextField.setEnabled(true);
		UIDTextField.setBounds(860, 400, 118, 19);
		contentPane.add(UIDTextField);
		UIDTextField.setColumns(10);

		stationLabel = new JLabel("起訖站");
		stationLabel.setBounds(392, 129, 68, 19);
		contentPane.add(stationLabel);

		stationFromLabel = new JLabel("起程站");
		stationFromLabel.setBounds(462, 129, 40, 19);
		contentPane.add(stationFromLabel);

		String[] station = { "南港", "台北", "板橋", "桃園", "新竹", "苗栗", "台中", "彰化", "雲林", "嘉義", "台南", "左營" };
		stationFromComboBox = new JComboBox(station);
		stationFromComboBox.setBounds(502, 129, 80, 19);
		contentPane.add(stationFromComboBox);

		stationToLabel = new JLabel("到達站");
		stationToLabel.setBounds(590, 129, 40, 19);
		contentPane.add(stationToLabel);

		stationToComboBox = new JComboBox(station);
		stationToComboBox.setBounds(630, 129, 90, 19);
		contentPane.add(stationToComboBox);

		carTypeLabel = new JLabel("車廂種類");
		carTypeLabel.setBounds(750, 432, 68, 19);
		contentPane.add(carTypeLabel);

		startardCarTypeRadioButton = new JRadioButton("標準車廂");
		startardCarTypeRadioButton.setBounds(860, 432, 100, 19);
		contentPane.add(startardCarTypeRadioButton);

		businessCarTypeRadioButton = new JRadioButton("商務車廂");
		businessCarTypeRadioButton.setBounds(972, 431, 100, 19);
		contentPane.add(businessCarTypeRadioButton);

		buttonGroup = new ButtonGroup();
		buttonGroup.add(startardCarTypeRadioButton);
		buttonGroup.add(businessCarTypeRadioButton);

		label_3 = new JLabel("座位喜好");
		label_3.setBounds(750, 468, 68, 19);
		contentPane.add(label_3);

		radioButton2 = new JRadioButton("無");
		radioButton2.setBounds(860, 468, 80, 19);
		contentPane.add(radioButton2);

		radioButton3 = new JRadioButton("靠窗優先");
		radioButton3.setBounds(971, 467, 90, 19);
		contentPane.add(radioButton3);

		radioButton4 = new JRadioButton("走道優先");
		radioButton4.setBounds(1087, 467, 100, 19);
		contentPane.add(radioButton4);

		buttonGroup1 = new ButtonGroup();
		buttonGroup1.add(radioButton2);
		buttonGroup1.add(radioButton3);
		buttonGroup1.add(radioButton4);

		searchMethodLabel = new JLabel("查詢方式");
		searchMethodLabel.setBounds(392, 209, 68, 19);
		contentPane.add(searchMethodLabel);

		searchMethodTimeRadioButton = new JRadioButton("依時間搜尋");
		searchMethodTimeRadioButton.setBounds(462, 208, 100, 19);
		contentPane.add(searchMethodTimeRadioButton);

		searchMethodTrainNumberRadioButton = new JRadioButton("輸入車次號碼");
		searchMethodTrainNumberRadioButton.setBounds(462, 240, 118, 19);
		contentPane.add(searchMethodTrainNumberRadioButton);

		buttonGroup2 = new ButtonGroup();
		buttonGroup2.add(searchMethodTimeRadioButton);
		buttonGroup2.add(searchMethodTrainNumberRadioButton);

		dateLabel = new JLabel("去程日期");
		dateLabel.setBounds(390, 168, 68, 19);
		contentPane.add(dateLabel);

//		label_7 = new JLabel("回程");
//		label_7.setBounds(460, 329, 68, 19);
//		contentPane.add(label_7);

//		checkBox = new JCheckBox("訂購回程");
//		checkBox.setBounds(700, 329, 150, 19);
//		contentPane.add(checkBox);
//		checkBox.addItemListener(new ItemListener() {
//			public void itemStateChanged(ItemEvent e) {
//				if (e.getStateChange() == 1) {
//					timeIn1.setEnabled(true);
//					fromTime.setEnabled(true);
//				}
//				else {
//					timeIn1.setEnabled(false);
//					fromTime.setEnabled(false);
//				}
//					
//			}
//		});

		dateTextField = new JTextField();
		dateTextField.setEnabled(true);
		dateTextField.setBounds(460, 168, 118, 19);
		contentPane.add(dateTextField);
		dateTextField.setColumns(10);

//		timeIn1 = new JTextField();
//		timeIn1.setEnabled(false);
//		timeIn1.setBounds(500, 329, 68, 19);
//		contentPane.add(timeIn1);
//		timeIn1.setColumns(10);

		dateFormatLabel = new JLabel("日期輸入格式 xxxx-xx-xx");
		dateFormatLabel.setBounds(598, 168, 169, 19);
		contentPane.add(dateFormatLabel);
		
		searchMethodTimeTextField = new JTextField();
		searchMethodTimeTextField.setEnabled(true);
		searchMethodTimeTextField.setBounds(653, 208, 50, 19);
		contentPane.add(searchMethodTimeTextField);
		
		searchMethodTimeLabel = new JLabel("發車時間");
		searchMethodTimeLabel.setBounds(588, 209, 60, 19);
		contentPane.add(searchMethodTimeLabel);
		
		searchMethodTrainNumberLabel = new JLabel("車次號碼");
		searchMethodTrainNumberLabel.setBounds(588, 241, 60, 19);
		contentPane.add(searchMethodTrainNumberLabel);
		
		searchMethodTrainNumberTextField = new JTextField();
		searchMethodTrainNumberTextField.setEnabled(true);
		searchMethodTrainNumberTextField.setBounds(653, 240, 50, 19);
		contentPane.add(searchMethodTrainNumberTextField);
		
//		label_14 = new JLabel("約");
//		label_14.setBounds(580, 329, 20, 19);
//		contentPane.add(label_14);
//		
//		fromTime = new JTextField();
//		fromTime.setEnabled(false);
//		fromTime.setBounds(600, 329, 20, 19);
//		contentPane.add(fromTime);
//		
//		label_15 = new JLabel("點出發");
//		label_15.setBounds(625, 329, 40, 19);
//		contentPane.add(label_15);

		label_9 = new JLabel("票數");
		label_9.setBounds(750, 502, 68, 19);
		contentPane.add(label_9);

		label_10 = new JLabel("全票");
		label_10.setBounds(860, 499, 68, 19);
		contentPane.add(label_10);

		label_11 = new JLabel("大學生優惠票");
		label_11.setBounds(860, 519, 80, 19);
		contentPane.add(label_11);

		Integer[] ticket = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		comboBox2 = new JComboBox(ticket);
		comboBox2.setBounds(960, 499, 68, 19);
		contentPane.add(comboBox2);

		comboBox3 = new JComboBox(ticket);
		comboBox3.setBounds(960, 519, 68, 19);
		contentPane.add(comboBox3);

		searchingButton = new JButton("搜尋");
		searchingButton.setBounds(785, 242, 68, 19);
		contentPane.add(searchingButton);

		homeButton = new JButton("回主畫面");
		homeButton.setBounds(800, 50, 90, 19);
		contentPane.add(homeButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(390, 292, 300, 438);
		contentPane.add(scrollPane);
		
		ticketNumberLabel = new JLabel("請輸入車票代號");
		ticketNumberLabel.setBounds(751, 369, 102, 19);
		contentPane.add(ticketNumberLabel);
		
		ticketNumberTextField = new JTextField();
		ticketNumberTextField.setEnabled(true);
		ticketNumberTextField.setBounds(860, 368, 40, 19);
		contentPane.add(ticketNumberTextField);
		ticketNumberTextField.setColumns(10);
		
		enterButton = new JButton("下一步");
		enterButton.setBounds(750, 567, 68, 19);
		contentPane.add(enterButton);
		
		textArea = new JTextArea();
		textArea.setBounds(390, 292, 296, 296);
		contentPane.add(textArea);
		textArea.setEditable(false);
		
		JLabel ticketTypeCategoryLabel = new JLabel("票種種類");
		ticketTypeCategoryLabel.setBounds(750, 338, 68, 19);
		contentPane.add(ticketTypeCategoryLabel);
		
		JRadioButton oneWayRadioButton = new JRadioButton("單程票");
		oneWayRadioButton.setBounds(861, 338, 80, 19);
		contentPane.add(oneWayRadioButton);
		
		JRadioButton roundTripRadioButton = new JRadioButton("來回票");
		roundTripRadioButton.setBounds(963, 337, 100, 19);
		contentPane.add(roundTripRadioButton);
		
		JLabel bookTicketLabel = new JLabel("訂票資訊");
		bookTicketLabel.setBounds(750, 304, 68, 19);
		contentPane.add(bookTicketLabel);
	}

	public String getuidIn() {
		return (UIDTextField.getText());
	}
	
	public String getStationFromID() {
		//comboBox.addActionListener(comboBox);
		String ID = "";
		if (stationFromComboBox.getSelectedIndex() == 0)
			ID = "0990";
		if (stationFromComboBox.getSelectedIndex() == 1)
			ID = "1000";
		if (stationFromComboBox.getSelectedIndex() == 2)
			ID = "1010";
		if (stationFromComboBox.getSelectedIndex() == 3)
			ID = "1020";
		if (stationFromComboBox.getSelectedIndex() == 4)
			ID = "1030";
		if (stationFromComboBox.getSelectedIndex() == 5)
			ID = "1035";
		if (stationFromComboBox.getSelectedIndex() == 6)
			ID = "1040";
		if (stationFromComboBox.getSelectedIndex() == 7)
			ID = "1043";
		if (stationFromComboBox.getSelectedIndex() == 8)
			ID = "1047";
		if (stationFromComboBox.getSelectedIndex() == 9)
			ID = "1050";
		if (stationFromComboBox.getSelectedIndex() == 10)
			ID = "1060";
		if (stationFromComboBox.getSelectedIndex() == 11)
			ID = "1070";
		return ID;
	}
	
	public String getStationToID() {
		String ID = "";
		if (stationToComboBox.getSelectedIndex() == 0)
			ID = "0990";
		if (stationToComboBox.getSelectedIndex() == 1)
			ID = "1000";
		if (stationToComboBox.getSelectedIndex() == 2)
			ID = "1010";
		if (stationToComboBox.getSelectedIndex() == 3)
			ID = "1020";
		if (stationToComboBox.getSelectedIndex() == 4)
			ID = "1030";
		if (stationToComboBox.getSelectedIndex() == 5)
			ID = "1035";
		if (stationToComboBox.getSelectedIndex() == 6)
			ID = "1040";
		if (stationToComboBox.getSelectedIndex() == 7)
			ID = "1043";
		if (stationToComboBox.getSelectedIndex() == 8)
			ID = "1047";
		if (stationToComboBox.getSelectedIndex() == 9)
			ID = "1050";
		if (stationToComboBox.getSelectedIndex() == 10)
			ID = "1060";
		if (stationToComboBox.getSelectedIndex() == 11)
			ID = "1070";
		return ID;
	}

	public String getStandardOrBusiness() {
		if (startardCarTypeRadioButton.isSelected())
			return "standard";
		else if (businessCarTypeRadioButton.isSelected())
			return "business";
		else
			return null;
	}

	public int getSeatPrefer() {
		if (radioButton2.isSelected())
			return 0;
		if (radioButton3.isSelected())
			return 1;
		if (radioButton4.isSelected())
			return 2;
		else
			return 3;
	}

	public String getOrderType() {
		if (searchMethodTimeRadioButton.isSelected())
			return "0";
		else if (searchMethodTrainNumberRadioButton.isSelected())
			return "1";
		else
			return null;
	}

	public String getTimeIn() {
		return (dateTextField.getText());
	}

	public String getTime1In() {
		return (timeIn1.getText());
	}

	public int getNumberOfTicket() {
		return (comboBox2.getSelectedIndex());
	}

	public int getNumberOfDiscount() {
		return (comboBox3.getSelectedIndex());
	}
	
	public int getToTime() {
		return (Integer.parseInt(searchMethodTimeTextField.getText()));
	}
	
	public int getFromTime() {
		return (Integer.parseInt(fromTime.getText()));
	}
	
	public int getOrderIndex() {
		return (Integer.parseInt(ticketNumberTextField.getText()));
	}
	
	public String getTrainNumber() {
		return (searchMethodTrainNumberTextField.getText());
	}
	
	//button for search result
	public void addSearchingButtonListener(ActionListener listener) {
		searchingButton.addActionListener(listener);
	}
	
	//button for booking
	public void addBookingButtonListener(ActionListener listener) {
		enterButton.addActionListener(listener);
	}

	//button for HomeView
	public void addHomeButtonListener(ActionListener listener) {
		homeButton.addActionListener(listener);
	}
	
	//output search result
	public void output(String a) {
		textArea.append(a);
	}
	
	public void clear() {
		UIDTextField.setText("");
		dateTextField.setText("");
		//timeIn1.setText("");
		searchMethodTimeTextField.setText("");
		//fromTime.setText("");
		searchMethodTrainNumberTextField.setText("");
		buttonGroup.clearSelection();
		buttonGroup1.clearSelection();
		buttonGroup2.clearSelection();
		textArea.setText("");
		stationFromComboBox.setSelectedIndex(0);
		stationToComboBox.setSelectedIndex(0);
		comboBox2.setSelectedIndex(0);
		comboBox3.setSelectedIndex(0);
		ticketNumberTextField.setText("");
	}
}