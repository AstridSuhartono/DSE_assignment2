package Database;


/**
 * Class to represent a single linked-list of Database.Game objects.
 * Created for Data Structures, SP2 2017
 * @author James Baumeister
 * @author Astrid Suhartono
 * @version 1.0
 */
public class GameList {

    public Game head;

	public GameList(Game head) {
		this.head = head;
    }
	
	public Game getGame(String name) {
		if (name == null) {
			throw new IllegalArgumentException();
		}
		Game gameRef = head;
		while (gameRef != null) {
			if(gameRef.getName().equals(name)) {
				return gameRef;
			}
			gameRef = gameRef.getNext();;
		}
		return null;
	}
	
	public void addGame(Game game) {
		if (game == null) {
			throw new IllegalArgumentException();
		}
		else {
			//game list is empty then set the added game as the head of the linked list
			if(head == null) {
				head = game;
			}
			//set the added game into the end of the linked list
			else {	
				Game gameRef = head;
				if(!gameRef.equals(game)) {
					while(gameRef.getNext() != null) {
						gameRef = gameRef.getNext();
					}
					gameRef.setNext(game);
				}
			}
		}
	}
	
	//remove a game based on the game name
	public void removeGame(String name) {
		if (name == null) {
			throw new IllegalArgumentException();
		}
		if (head == null) {
			throw new NullPointerException();
		}
		//remove game at the head of the list
		else if (head.getName().equals(name)) {
			head = head.getNext();
		}
		else {
			Game gameRef = head.getNext();
			Game prevGameRef = head;
			//iterate through the list until the name of the game is found
			while(gameRef != null && !gameRef.getName().equals(name)) {
				prevGameRef = gameRef;
				gameRef = gameRef.getNext();
			}
			if(gameRef != null && gameRef.getName().equals(name)) {
				prevGameRef.setNext(gameRef.getNext());
			}
		}		
	}
	
	//remove a game based on the reference to the game itself
	public void removeGame(Game game) {
		if (game == null) {
			throw new IllegalArgumentException();
		}
		if (head == null) {
			throw new NullPointerException();
		}
		else if (head.equals(game)) {
			head = head.getNext();
		}
		else {
			Game gameRef = head.getNext();
			Game prevGameRef = head;
			while(gameRef != null && !gameRef.equals(game)) {
				prevGameRef = gameRef;
				gameRef = gameRef.getNext();
			}
			if(gameRef != null && gameRef.equals(game)) {
				prevGameRef.setNext(gameRef.getNext());
			}
		}		
	}
	
	public boolean contains(Game game) {
		if (game == null) {
			throw new NullPointerException();
		}
		Game gameRef = head;
		while (gameRef != null) {
			if (gameRef.equals(game)) {
				return true;
			}
			gameRef = gameRef.getNext();
		}
		return false;
	}
	
	public String toString() {
		String gameListInfo = "";
		Game gameRef = head;
		if (head == null) {
			return "Empty game list";
		}
		while (gameRef != null) {
			gameListInfo += gameRef.toString() + "\n";
			gameRef = gameRef.getNext();
		}

		return gameListInfo.trim();

	}

}
