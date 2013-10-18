package jp.gr.java_conf.onkohdondo.conferenceSystem.frame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import jp.gr.java_conf.onkohdondo.conferenceSystem.main.Conference;
import jp.gr.java_conf.onkohdondo.conferenceSystem.other.Person;

public class ConferenceFrame extends JFrame {
	private static final long serialVersionUID
		= -2954993183907838891L;
	public int left,right,top,bottom;
	private TopPanel topPanel;
	public LeftPanel leftPanel;
	private RightPanel rightPanel;
	private BottomPanel bottomPanel;
	public Conference conference;
	public ConferenceFrame(Conference c){
		conference=c;
		Area a=new Area();
		left=Conference.displayWidth/7;
		right=Conference.displayWidth/10;
		top=Conference.displayHeight/10;
		bottom=Conference.displayHeight/6;
		a.add(new Area(new Rectangle(0,0,Conference.displayWidth,
				top)));
		a.add(new Area(new Rectangle(0,top,left,
				Conference.displayHeight-top)));
		a.add(new Area(new Rectangle(Conference.displayWidth-right,
				top,right,Conference.displayHeight-top)));
		a.add(new Area(new Rectangle(left,
				Conference.displayHeight-bottom,
				Conference.displayWidth-left-right, bottom)));
		
		setUndecorated(true);
		setBounds(a.getBounds());
		setShape(a);
		setLayout(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		
		topPanel=new TopPanel(0,0,
				Conference.displayWidth,top);
		add(topPanel);
		leftPanel=new LeftPanel(0,top,left,
				Conference.displayHeight-top);
		add(leftPanel);
		rightPanel=new RightPanel(Conference.displayWidth-right,
				top,right,Conference.displayHeight-top);
		add(rightPanel);
		bottomPanel=new BottomPanel(left,
				Conference.displayHeight-bottom,
				Conference.displayWidth-left-right, bottom);
		add(bottomPanel);
		
		setVisible(true);
	}
	private class TopPanel extends JPanel{
		private static final long serialVersionUID
				= -353460535492254790L;
		private JButton quit;
		private JButton alwaysTop;
		private JButton minimum;
		private JLabel presidentNameLabel;
		private JLabel conferenceNameLabel;
		private ArrayList<JButton> personButton;
		TopPanel(int x,int y,int width, int height){
			super();
			setBounds(0, 0, width, height);
			setLayout(null);
			
			quit=new JButton("☓");
			quit.setBounds(Conference.displayWidth-50,0,50,20);
			quit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			quit.setToolTipText("閉じる");
			quit.setVisible(true);
			add(quit);
			
			alwaysTop=new JButton("□");
			alwaysTop.setBounds(Conference.displayWidth-100, 0, 50,20);
			alwaysTop.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0){
					ConferenceFrame.this.setAlwaysOnTop(!
							ConferenceFrame.this.isAlwaysOnTop());
				}
			});
			alwaysTop.setToolTipText("常に全面に表示/非表示");
			alwaysTop.setVisible(true);
			add(alwaysTop);
			
			minimum=new JButton("＿");
			minimum.setBounds(Conference.displayWidth-150, 0, 50, 20);
			minimum.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0){
				}
			});
			minimum.setToolTipText("最小化");
			minimum.setVisible(true);
			add(minimum);
			
			conferenceNameLabel=new JLabel(conference.conferenceName);
			conferenceNameLabel.setBounds(0, height/2, 200, height/2);
			conferenceNameLabel.setVisible(true);
			add(conferenceNameLabel);
			
			presidentNameLabel=new JLabel("会議開催者:"+conference.
					conferencePresident);
			presidentNameLabel.setBounds(0, 0, 200, height/2);
			presidentNameLabel.setVisible(true);
			add(presidentNameLabel);
			
			drawPersons();
		}
		
		public void drawPersons(){
			if(personButton==null || 
					conference.people.size()!=personButton.size()){
				personButton=new ArrayList<JButton>(
						conference.people.size());
			}
			for(int i=0;i<conference.people.size();i++){
				System.out.println(0);
				Person p=conference.people.get(i);
				JButton b=new JButton(){
					private static final long serialVersionUID
						= -6331661558457538999L;
					public void paint(Graphics g){
						Graphics2D graphics=(Graphics2D) g;
						graphics.setBackground(Color.WHITE);
						graphics.clearRect(0, 0,
								getHeight(), getHeight());
						graphics.fillOval(getHeight()/2-30,30,
								60,60);
						System.out.println(getWidth());
					}
				};
				personButton.add(b);
				b.setText(p.getName());
				b.setBounds(200+getHeight()*i, 0, getHeight(),
						getHeight());
				b.setVisible(true);
				add(b);
			}
		}
	}
	public class LeftPanel extends JPanel{
		private static final long serialVersionUID = 
				8619455034884574134L;
		JTextArea chatArea;
		public ChatPanel chatPanel;
		
		LeftPanel(int x,int y,int width, int height){
			super();
			setBounds(x,y, width, height);
			setLayout(null);
			
			chatArea=new JTextArea();
			chatArea.setBounds(0,height/5*4,width,height/5);
			chatArea.setLineWrap(true);
			chatArea.setWrapStyleWord(true);
			chatArea.setToolTipText("チャットを入力");
			JScrollPane jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(chatArea);
			jScrollPane.setBounds(0,height/5*4,width,height/5);
			jScrollPane.setVerticalScrollBarPolicy(
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			add(jScrollPane);
			chatArea.setVisible(true);
			
			chatPanel = new ChatPanel();
			chatPanel.setBounds(0,0,width,height/5*4);
			add(chatPanel);
		}
	}
	
	public class ChatPanel extends JPanel{
		private static final long serialVersionUID = 7618880842648944746L;
//		private Graphics2D graphics;
		ChatPanel(){
			System.out.println();
			repaint();
		}
		public void paintComponent(Graphics graphics){
			Graphics2D g = (Graphics2D)graphics;
			g.setBackground(Color.BLACK);
			g.clearRect(0, 0, getWidth(), getHeight());
		}
	}
	
	private class RightPanel extends JPanel{
		private static final long serialVersionUID = 
				-3606085147113093173L;
		RightPanel(int x,int y,int width, int height){
			super();
			setBounds(x,y, width, height);
		}
	}
	
	private class BottomPanel extends JPanel{
		private static final long serialVersionUID = 
				-8684970877558624465L;
		BottomPanel(int x,int y,int width, int height){
			super();
			setBounds(x,y, width, height);
		}
	}
}
