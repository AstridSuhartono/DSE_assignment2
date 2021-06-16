package Database;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Class to represent a PlayStation game trophy. A trophy comes in four ranks:
 * bronze, silver, gold and platinum. The date the trophy was earned and its
 * respective game is also stored. Created for Data Structures, SP2 2017
 * 
 * @author James Baumeister
 * @author Astrid Suhartono
 * @version 1.0
 */
public class Trophy {
	public enum Rank {
		BRONZE, GOLD, SILVER, PLATINUM

	}

	public enum Rarity {
		VERY_RARE, RARE, ULTRA_RARE, UNCOMMON, COMMON

	}

	private String name;
	private Rank rank;
	private Rarity rarity;
	private Calendar obtained;
	private Game game;

	public Trophy() {
	}

	public Trophy(String name, Rank rank, Rarity rarity, Calendar obtained, Game game) {
		this.name = name;
		this.rank = rank;
		this.rarity = rarity;
		this.obtained = obtained;
		this.game = game;
	}

	public String getName() {
		return name;
	}

	public Rank getRank() {
		return rank;
	}

	public Rarity getRarity() {
		return rarity;
	}

	public Calendar getObtained() {
		return obtained;
	}

	public Game getGame() {
		return game;
	}

	public String toString() {
		return "\"" + name + "\", rank: " + rank + ", rarity: " + rarity + ", obtained on: " + formatDate(obtained);
	}
	
	//format the date output for example into: May 10 2021
	private String formatDate(Calendar date) {
		SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");
		String formatedDate = formatter.format(date.getTime());
		return formatedDate;
	}
}
