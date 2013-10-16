package jp.gr.java_conf.onkohdondo.conferenceSystem.main;

import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Main extends JFrame {
	private static final long serialVersionUID = 2093570213653938095L;

	public Main() throws HeadlessException {
		super();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(200,200);
		setResizable(false);
		setLocationRelativeTo(null);
		
		setLayout(new GridLayout(3,1));
		JButton serverButton=new JButton();
		serverButton.setText("�V������c���쐬");
		serverButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ConferenceServer();
			}
		});
		add(serverButton);
		JButton clientButton=new JButton();
		clientButton.setText("�����̉�c�ɎQ��");
		add(clientButton);
		JButton cancelButton=new JButton();
		cancelButton.setText("�L�����Z��");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		add(cancelButton);
		
		setVisible(true);
	}

	public static void main(String[] args) {
		new Main();
	}

}
