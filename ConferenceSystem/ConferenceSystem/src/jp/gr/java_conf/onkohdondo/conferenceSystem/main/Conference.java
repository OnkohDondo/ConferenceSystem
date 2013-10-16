package jp.gr.java_conf.onkohdondo.conferenceSystem.main;

import java.util.ArrayList;

import jp.gr.java_conf.onkohdondo.conferenceSystem.other.Person;
import jp.gr.java_conf.onkohdondo.conferenceSystem.other.Speak;

public abstract class Conference {
	public static int displayWidth;
	public static int displayHeight;
	public String conferenceName = "";
	public String conferencePresident = "";
	public ArrayList<Person> people;
	public ArrayList<Speak> chat;
	
	protected Conference() {
	}
}