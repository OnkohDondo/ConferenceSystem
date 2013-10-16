package jp.gr.java_conf.onkohdondo.conferenceSystem.main;

import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import jp.gr.java_conf.onkohdondo.conferenceSystem.frame.ConferenceFrame;
import jp.gr.java_conf.onkohdondo.conferenceSystem.other.Person;

public class ConferenceServer extends Conference{
	public ConferenceFrame conferenceFrame;
	protected ConferenceServer(){
		GraphicsEnvironment env
		= GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle desktopBounds = env.getMaximumWindowBounds();
		displayWidth=desktopBounds.width;
		displayHeight=desktopBounds.height;
		
		new ConfigurateForm();
		
		people=new ArrayList<Person>();
		people.add(new Person(0,conferencePresident));
		people.add(new Person(1, "Guest"));
		
		
		conferenceFrame=new ConferenceFrame(this);
		new Timer().schedule(new DrawChat(),0, 100);
		
		
	}
	private class DrawChat extends TimerTask{
		public void run(){
			
		}
	}
	private class ConfigurateForm extends JDialog{
		private static final long serialVersionUID
			= -4440170788235901854L;
		private JTextField nameField;
		private JLabel noName;
		private JTextField conferenceNameField;
		private JLabel noConferenceName;
		private JButton OK,cancel;
		ConfigurateForm(){
			super();
			setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			setSize(600,300);
			setLocationRelativeTo(null);
			setLayout(new GridLayout(3,1));
			setAlwaysOnTop(true);
			setTitle("新しい会議のセットアップ");
			
			JPanel p1=new JPanel();
			add(p1);
			p1.add(new JLabel("名前"));
			nameField=new JTextField(45);
			p1.add(nameField);
			noName=new JLabel("名前が入力されていません");
			noName.setVisible(false);
			noName.setForeground(Color.RED);
			p1.add(noName);
			
			JPanel p2=new JPanel();
			add(p2);
			p2.add(new JLabel("会議名"));
			conferenceNameField=new JTextField(45);
			p2.add(conferenceNameField);
			noConferenceName=new JLabel("会議名が入力されていません");
			noConferenceName.setVisible(false);
			noConferenceName.setForeground(Color.RED);
			p2.add(noConferenceName);

			JPanel p3=new JPanel();
			add(p3);
			OK=new JButton("決定");
			p3.add(OK);
			OK.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					boolean error=false;
					if(nameField.getText().equals("")){
						noName.setVisible(true);
						error=true;
					}else noName.setVisible(false);
					if(conferenceNameField.getText().equals("")){
						noConferenceName.setVisible(true);
						error=true;
					}else noConferenceName.setVisible(false);
					if(error) return;
					conferencePresident=
							nameField.getText();
					conferenceName=
							conferenceNameField.getText();
					setVisible(false);
				}
			});
			cancel=new JButton("キャンセル");
			p3.add(cancel);
			cancel.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					dispose();
					System.exit(0);
				}
			});
			setModal(true);
			setVisible(true);
		}
	}
}
