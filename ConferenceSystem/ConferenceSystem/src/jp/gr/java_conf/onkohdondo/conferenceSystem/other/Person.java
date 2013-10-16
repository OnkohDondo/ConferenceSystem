package jp.gr.java_conf.onkohdondo.conferenceSystem.other;

import javax.swing.JButton;

public class Person {
	private int id;
	private String name;
	private JButton button;

	public Person(int i, String string) {
		setId(i);
		setName(string);
	}

	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}
	
}
