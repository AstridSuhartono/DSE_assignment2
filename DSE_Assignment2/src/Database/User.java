package Database;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Class to represent a PlayStation user. Created for Data Structures, SP2 2017
 * 
 * @author James Baumeister
 * @author Astrid Suhartono
 * @version 1.0
 */
public class User {

	private String username;
	private int level;
	private double key;
	private ArrayList<Trophy> trophies;
	private GameList games;
	private Calendar dob;
	private User left;
	private User right;

	public User(String username, Calendar dob, int level) {
		this.username = username;
		this.dob = dob;
		this.level = level;
		this.key = 0;
		this.trophies = null;
		this.games = null;
		this.left = null;
		this.right = null;
	}

	/**
	 * DO NOT MODIFY THIS METHOD This method uses the username and level to create a
	 * unique key. As we don't want the username's hash to increase the level, it's
	 * first converted to a floating point, then added to the level.
	 * 
	 * @return the unique key
	 */
	public double calculateKey() {
		int hash = Math.abs(username.hashCode());
		// Calculate number of zeros we need
		int length = (int) (Math.log10(hash) + 1);
		// Make a divisor 10^x
		double divisor = Math.pow(10, length);
		// Return level.hash
		return level + hash / divisor;
	}

	public String getUsername() {
		return username;
	}

	public Calendar getDob() {
		return dob;
	}

	public int getLevel() {
		return level;
	}

	public double getKey() {
		return key = calculateKey();
	}

	public User getLeft() {
		return left;
	}

	public User getRight() {
		return right;
	}

	public GameList getGames() {
		return games;
	}

	public ArrayList<Trophy> getTrophies() {
		return trophies;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setGames(GameList games) {
		this.games = games;
	}
	
	public void setDob(Calendar dob) {
		this.dob = dob;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}

	public void setKey(double key) {
		this.key = key;
	}

	public void setTrophies(ArrayList<Trophy> trophies) {
		this.trophies = trophies;
	}

	public void setLeft(User left) {
		this.left = left;
	}

	public void setRight(User right) {
		this.right = right;
	}

	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (o == null || this.getClass() != o.getClass()) {
			return false;
		}
		User otherUser = (User) o;
		return otherUser.username == this.username && otherUser.dob == this.dob && otherUser.key == this.key;

	}

	public String toString() {
		return "User: " + username + "\n" + "\nTrophies: \n" + formatTrophiesToString() + "\n" + "Games: \n"
				+ games.toString() + "\n" + "\nBirth Date: " + formatDate(dob);
	}

	//format the date output for example into: May 10 2021
	private String formatDate(Calendar date) {
		SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");
		String formatedDate = formatter.format(date.getTime());
		return formatedDate;
	}

	//format use trophies array list to string method by iterating through every data in the array and append
	//it into one string variable
	private String formatTrophiesToString() {
		String trophiesInfo = "";
		for (int i = 0; i < trophies.size(); i++) {
			trophiesInfo += trophies.get(i) + "\n";
		}
		return trophiesInfo;
	}

}
