package sysTrayCounter;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public class systemTrayCounter implements ActionListener {
	static int points = 1;
	static int intSavedPoints;
	static String stringPoints;
	static String savedPoints;
	int end;
	static Scanner fileReader;
	static File file;
	static long fileLength = 1;
	static JLabel outputLabel;


	
	public static void main(String[] args) throws IOException {
		Runtime.getRuntime().addShutdownHook(new Thread() {
	        public void run(){
	            //save data here
	            System.out.println("Exiting");
	        }
	    });
		//FileWriter writer = new FileWriter("D:/Program Files/Point Adder/points.txt");
		file = new File("D:/Program Files/Point Adder/points.txt");
		fileReader = new Scanner(file);
		while(fileReader.hasNext()) {
			savedPoints = fileReader.nextLine();
		}
		
		intSavedPoints = Integer.parseInt(savedPoints);
		
		points = intSavedPoints;
		
		JFrame frame = new JFrame("My Points");
		frame.setSize(500, 500);
		frame.setLayout(null);

		JButton addPoints = new JButton("+");
		JButton removePoints = new JButton("-");
		outputLabel = new JLabel();

		addPoints.setBounds(75, 100, 150, 50);
		addPoints.setFont(new Font("Arial", Font.BOLD, 32));
		removePoints.setBounds(225, 100, 150, 50);
		removePoints.setFont(new Font("Arial", Font.BOLD, 32));
		outputLabel.setBounds(218, 200, 200, 50);
		outputLabel.setFont(new Font("Arial", Font.BOLD, 32));
		outputLabel.setForeground(Color.BLUE);
		outputLabel.setText("" + points);

		frame.add(addPoints);
		frame.add(removePoints);
		frame.add(outputLabel);

		addPoints.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				systemTrayCounter.addPoint();
			}
		});
		removePoints.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				systemTrayCounter.removePoint();
			}
		});

		frame.setVisible(true);
		
		if(SystemTray.isSupported() == true) {
			frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		}
		
		SystemTray systemTray = SystemTray.getSystemTray();
		TrayIcon trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().getImage("res/penguin1.jpg"));
		PopupMenu popMenu = new PopupMenu();
		
		MenuItem menuShowPoint = new MenuItem("Current Points: " + points);
		
		MenuItem menuAddPoint = new MenuItem("+");
		menuAddPoint.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				systemTrayCounter.addPoint();
			}
			
		});
		MenuItem menuRemovePoint = new MenuItem("- ");
		menuRemovePoint.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				systemTrayCounter.removePoint();
			}
			
		});
		
		MenuItem menuSeperate = new MenuItem("-");
		
		MenuItem menuShow = new MenuItem("Show");
		menuShow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(true);
			}
			
		});
		
		MenuItem menuExit = new MenuItem("Exit");
		menuExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
		
		popMenu.add(menuShowPoint);
		popMenu.add(menuAddPoint);
		popMenu.add(menuRemovePoint);
		popMenu.add(menuSeperate);
		popMenu.add(menuShow);
		popMenu.add(menuExit);
		
		trayIcon.setPopupMenu(popMenu);
		
		try {
			systemTray.add(trayIcon);
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static void save() throws IOException {
		PrintWriter writer = new PrintWriter("D:/Program Files/Point Adder/points.txt");
		writer.write(stringPoints);
		writer.close();
	}
	
	public static void addPoint(){
		points++;
		fileLength++;
		outputLabel.setText("" + points);
		
		System.out.println(points);
		stringPoints = String.valueOf(points);
		try {
			systemTrayCounter.save();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static void removePoint() {
		points--;
		fileLength--;
		outputLabel.setText("" + points);
		
		System.out.println(points);
		stringPoints = String.valueOf(points);
		try {
			systemTrayCounter.save();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	
		
	}
	
}