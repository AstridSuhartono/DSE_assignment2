package Database;

import java.util.ArrayList;


/**
 * Uses a binary search tree to store a database of PlayStation users. Nodes are
 * ordered by user unique key (see the User class for more detail). Created for
 * Data Structures, SP2 2017
 * 
 * @author James Baumeister
 * @author Astrid Suhartono
 * @version 1.0
 */
public class BinaryTree {
	public User root;

	public BinaryTree() {
		root = null;
	}

	protected BinaryTree(User root) {
		this.root = root;
	}

	/**
	 * Making new friends is great. This method should add your new bestie to your
	 * database (tree). Remember that they should be added according to their key.
	 * 
	 * @param friend The friend to be added
	 * @return true if successfully added, false for all error cases
	 * @throws IllegalArgumentException if friend is null
	 */
	public boolean beFriend(User friend) throws IllegalArgumentException {
		if (friend == null) {
			throw new IllegalArgumentException();
		}
		// add friend to the root if binarytree is still null
		if (root == null) {
			root = friend;
			return true;
		}

		User current = root;
		User parent = null;

		while (true) {
			parent = current;
			// friend added has the same key with friend reference, return false and exit
			// the while loop
			if (friend.getKey() == current.getKey()) {
				return false;
			}
			// friend added has less key value than current friend reference
			else if (friend.getKey() < current.getKey()) {
				current = current.getLeft();
				if (current == null) {
					parent.setLeft(friend);
					return true;
				}
				// friend added has more key value than current friend reference
			} else {
				current = current.getRight();
				if (current == null) {
					parent.setRight(friend);
					return true;
				}
			}
		}
	}

	/**
	 * Sometimes friendships don't work out. In those cases it's best to remove that
	 * "friend" altogether. This method should remove all trace of that "friend" in
	 * the database (tree).
	 * 
	 * @param friend the "friend" to remove
	 * @return true if successfully removed, false for all error cases
	 * @throws IllegalArgumentException if "friend" is null
	 */
	public boolean deFriend(User friend) throws IllegalArgumentException {
		if (friend == null) {
			throw new IllegalArgumentException();
		}
		if (!find(friend)) {
			return false;
		}
		root = deFriend(root, friend);
		return true;
	}

	//recursive deFriend method
	private User deFriend(User localRoot, User friend) {
		if (localRoot == null) {
			return null;
		}
		//friend defriended has less key value than the current reference
		if (friend.getKey() < localRoot.getKey()) {
			localRoot.setLeft(deFriend(localRoot.getLeft(),friend));
			return localRoot;
		//friend defriended has more key value than the current reference	
		} else if(friend.getKey() > localRoot.getKey()) {
			localRoot.setRight(deFriend(localRoot.getRight(),friend));
			return localRoot;
		//friend defriended has the same key value than the current reference
		} else {
			//no left child
			if (localRoot.getLeft() == null) {
				return localRoot.getRight();
			//no right child
			} else if (localRoot.getRight() == null) {
				return localRoot.getLeft();
			//has two children left and right
			} else {
				User userRef = localRoot;
				if(localRoot.getLeft().getRight() == null) {
					localRoot = localRoot.getLeft();
					localRoot.setLeft(localRoot.getLeft().getLeft());
					localRoot.setRight(userRef.getRight());
					return localRoot;
				} else {
					localRoot = findLargestChild(localRoot.getLeft());
					localRoot.setLeft(userRef.getLeft());
					localRoot.setRight(userRef.getRight());
					return localRoot;
				}
			}
		}
	}

	//get the largest child value under the friend node in the binary search tree
	private User findLargestChild(User friend) {
		if (friend.getRight().getRight() == null) {
			User replacementChild = friend.getRight();
			friend.setRight(friend.getRight().getLeft());
			return replacementChild;
		} else {
			return findLargestChild(friend.getRight());
		}
	}

	/**
	 * In your quest to be the very best you need to know how many of your friends
	 * are ranked higher than you. This method should return the number of higher
	 * ranked users that the provided reference user, or zero if there are none
	 * (woot!).
	 * 
	 * @param reference The starting point in the search
	 * @return Number of higher ranked users or -1 if user not found
	 * @throws IllegalArgumentException if reference is null
	 */
	public int countBetterPlayers(User reference) throws IllegalArgumentException {
		if (reference == null) {
			throw new IllegalArgumentException();
		}
		if (!find(reference)) {
			return -1;
		}
		return countBetterPlayers(root, reference);
	}
	
	//recursive countBetterPlayers method
	private int countBetterPlayers(User localRoot, User reference) {
		if (localRoot == null) {
			return 0;
		}
		int count = 0;
		if (localRoot.getLevel() > reference.getLevel()) {
			count++;
		}
		return count + countBetterPlayers(localRoot.getLeft(),reference) + countBetterPlayers(localRoot.getRight(),reference);
	}

	/**
	 * Bragging rights are well earned, but it's good to be sure that you're
	 * actually better than those over whom you're lording your achievements. This
	 * method should find all those friends who have a lower level than you, or zero
	 * if there are none (you suck).
	 * 
	 * @param reference The starting point in the search
	 * @return Number of lower ranked users
	 * @throws IllegalArgumentException if reference is null
	 */
	public int countWorsePlayers(User reference) throws IllegalArgumentException {
		if (reference == null) {
			throw new IllegalArgumentException();
		}
		if (!find(reference)) {
			return -1;
		}
		return countWorsePlayers(root, reference); 
	}
	
	//recursive countWorsePlayers method
	private int countWorsePlayers(User localRoot, User reference) {
		if (localRoot == null) {
			return 0;
		}
		int count = 0;
		if (localRoot.getLevel() < reference.getLevel()) {
			count++;
		}
		return count + countWorsePlayers(localRoot.getLeft(),reference) + countWorsePlayers(localRoot.getRight(),reference);
	}

	
	/**
	 * The best player may not necessarily be measured by who has the highest level.
	 * Platinum trophies are the holy grail, so it would be good to know who has the
	 * most. This method should return the user with the highest number of platinum
	 * trophies.
	 * 
	 * @return the user with the most platinum trophies, or null if there are none
	 */
	public User mostPlatinums() {
		return mostPlatinums(root);
	}
	
	//first stage recursive mostPlatinums method
	private User mostPlatinums(User reference) {
		if (reference == null) {
			throw new IllegalArgumentException();
		}
		return mostPlatinums(root, reference);
	}
	
	//second stage recursive mostPlatinums method
	//currently the function is still not working properly, but based on testing should compare gold if the friends has same platinum count
	private User mostPlatinums(User localRoot, User friendWithPlatinums) {
		if (localRoot == null) {
			return null;
		}
		if (!localRoot.equals(friendWithPlatinums)) {
			int localRootPlatinumCount = getPlatinumTrophyCount(localRoot);
			int friendPlatinumCount = getPlatinumTrophyCount(friendWithPlatinums);
			//compare gold count trophy if the friends have same platinum count
			if(localRootPlatinumCount == friendPlatinumCount) {
				int localRootGoldCount = getGoldTrophyCount(localRoot);
				int friendGoldCount = getGoldTrophyCount(friendWithPlatinums);
				if (localRootGoldCount > friendGoldCount) {
					friendWithPlatinums = localRoot;
				}
			//compare platinum count trophy
			} else if (localRootPlatinumCount > friendPlatinumCount) {
				friendWithPlatinums = localRoot;
			}
		}
		mostPlatinums(localRoot.getLeft(), friendWithPlatinums);
		mostPlatinums(localRoot.getRight(), friendWithPlatinums);
		return friendWithPlatinums; 
	}
	
	//count a friend total number of platinum trophies 
	private int getPlatinumTrophyCount(User friend) {
		int count = 0;
		ArrayList<Trophy> trophies = friend.getTrophies();
		for (Trophy trophy : trophies) {
			if (trophy.getRank() == Trophy.Rank.PLATINUM) {
				count++;
			}
		}
		return count;
	}
	
	//count a friend total number of gold trophies 
	private int getGoldTrophyCount(User friend) {
		int count = 0;
		ArrayList<Trophy> trophies = friend.getTrophies();
		for (Trophy trophy : trophies) {
			if (trophy.getRank() == Trophy.Rank.GOLD) {
				count++;
			}
		}
		return count;
	}

	/**
	 * You or one of your friends bought a new game! This method should add it to
	 * their GameList.
	 * 
	 * @param username The user who has bought the game
	 * @param game     The game to be added
	 */
	public void addGame(String username, Game game) throws IllegalArgumentException {
		if (username == null || game == null) {
			throw new IllegalArgumentException();
		}
		User friend = find(root, username);

		if (!friend.getGames().contains(game)) {
			friend.getGames().addGame(game);
		}
	}

	/**
	 * You or one of your friends achieved a new trophy! This method should add it
	 * to their trophies.
	 * 
	 * @param username The user who has earned a new trophy
	 * @param trophy   The trophy to be added
	 */
	public void addTrophy(String username, Trophy trophy) throws IllegalArgumentException {
		if (username == null || trophy == null) {
			throw new IllegalArgumentException();
		}
		User friend = find(root, username);
		if (!friend.getTrophies().contains(trophy)) {
			friend.getTrophies().add(trophy);
		}
	}

	/**
	 * You or one of your friends has gained one level! This is great news, except
	 * that it may have ruined your tree structure! A node move may be in order.
	 * 
	 * @param username The user whose level has increased
	 */
	public void levelUp(String username) throws IllegalArgumentException {
		if (username == null) {
			throw new IllegalArgumentException();
		}
		User friend = find(root, username);
		friend.setLevel(friend.getLevel() + 1); 
		friend.setKey(friend.calculateKey());
		deFriend(friend);
		beFriend(friend);
	}

	/**
	 * As your friends list grows, adding with regular binary tree rules will result
	 * in an unbalanced and inefficient tree. One approach to fix this is to
	 * implement an add method that uses AVL balancing. This method should work in
	 * the same way as beFriend, but maintain a balanced tree according to AVL
	 * rules.
	 * 
	 * @param friend The friend to be added
	 * @return true if successfully added, false for all error cases
	 * @throws IllegalArgumentException if friend is null
	 */
	public boolean addAVL(User friend) throws IllegalArgumentException {
		if (friend == null) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * A nice, neat print-out of your friends would look great in the secret
	 * scrap-book that you keep hidden underneath your pillow. This method should
	 * print out the details of each user, traversing the tree in order.
	 * 
	 * @return A string version of the tree
	 */
	public String toString() {
		String originalString = toString(root);
		return originalString.trim();
	}

	//recursive toString method
	private String toString(User root) {
		String result = "";
		if (root == null) {
			return "";
		}
		result += toString(root.getLeft());
		result += root.toString() + "\n";
		result += toString(root.getRight());
		return result;
	}

	//finding the friend based on username of the User by traversing the binary tree and return the User if found
	private User find(User localRoot, String username) {
		if (localRoot != null) {
			if (localRoot.getUsername().equals(username)) {
				return localRoot;
			} else {
				User tempNode = find(localRoot.getLeft(), username);
				if (tempNode == null) {
					tempNode = find(localRoot.getRight(), username);
				}
				return tempNode;
			}
		} else {
			return null;
		}
	}

	//finding the friend based on the User itself by traversing the binary tree and return true if found
	private boolean find(User friend) {
		User current = root;
		while (friend.getKey() != current.getKey()) {
			if (friend.getKey() < current.getKey()) {
				current = current.getLeft();
				if (current == null) {
					return false;
				}
			} else {
				current = current.getRight();
				if (current == null) {
					return false;
				}
			}
		}
		return true;
	}

}

