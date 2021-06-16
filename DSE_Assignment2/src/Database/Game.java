package Database;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Class to represent a PlayStation game. Created for Data Structures, SP2 2017
 * 
 * @author James Baumeister
 * @author Astrid Suhartono
 * @version 1.0
 */
public class Game {

	private String name;
	private Calendar released;
	private Game next;
	private int totalTrophies;

	public Game() {
	}

	public Game(String name, Calendar released, int totalTrophies) {
		this.name = name;
		this.released = released;
		this.totalTrophies = totalTrophies;
	}

	public Calendar getReleased() {
		return released;
	}

	public int getTotalTrophies() {
		return totalTrophies;
	}

	public String getName() {
		return name;
	}

	public void setNext(Game g2) {
		this.next = g2;
	}

	public Game getNext() {
		return next;
	}

	public String toString() {
		return "\"" + name + "\", released on: " + formatDate(released);
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (o == null || this.getClass() != o.getClass()) {
			return false;
		}
		Game otherGame = (Game) o;
		return this.name == otherGame.name && this.released == otherGame.released;
	}

	//format the date output for example into: May 10 2021
	private String formatDate(Calendar date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");
		String formatedDate = formatter.format(date.getTime());
		return formatedDate;
	}
}
