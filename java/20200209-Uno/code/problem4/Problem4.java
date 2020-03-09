import java.util.ArrayList;
import java.util.Scanner;

public class Problem4 {

	public static void main(String[] args) {

		// get input
		Scanner in = new Scanner(System.in);
		String playerNum = in.nextLine();

		// players
		ArrayList<Player> players = new ArrayList<>();
		for (int i=0; i < Integer.parseInt(playerNum); i++) {
			Player player = new Player(in.nextLine());
			players.add(player);
		}

		// match
		String matchString = in.nextLine();

		// deck
		String deckString = in.nextLine();
		ArrayList<String> deck = new ArrayList<>();
		for (String card: deckString.split(" ")) {
			deck.add(card);
		}

		// origin
		String curCard = matchString;
		int playIndex = 0;
		int curPlayerIndex;
		boolean playing  = true;
		Player curPlayer;
		boolean ifTakeTwo = true;
		String nextCard;

		System.out.println("0: " + curCard);
		while(playing) {
			curPlayerIndex = playIndex % players.size();
			curPlayer = players.get(curPlayerIndex);

			// judge '2' first
			if (curCard.charAt(1) == '2' && ifTakeTwo == true) {
				nextCard = curPlayer.getTwo();
				// no '2' exist
				if (nextCard == null) {
					System.out.println(curPlayerIndex + 1 + ": TAKE TWO");
					String drawCard = deck.get(0);
					curPlayer.addCard(drawCard);
					deck.remove(drawCard);
					drawCard = deck.get(0);
					curPlayer.addCard(drawCard);
					deck.remove(drawCard);
					ifTakeTwo = false;
				} else {
					curPlayer.removeCard(nextCard);
					if (curPlayer.isEmpty()) {
						System.out.println(curPlayerIndex+1 + ": " + nextCard + " (WINNER)");
						playing = false;
					} else {
						System.out.println(curPlayerIndex+1 + ": " + nextCard);
						curCard = nextCard;
					}
					ifTakeTwo = true;
				}
			} else {
				// a new color
				if (curCard.charAt(1) == '9') {
					nextCard = curPlayer.getCardByColor(curCard);
					curPlayer.removeCard(nextCard);
					if (curPlayer.isEmpty()) {
						System.out.println(curPlayerIndex + 1 + ": " + nextCard + " (WINNER)");
						playing = false;
					} else {
						// if loco card get a new color
						if (nextCard == null) {
							// draw a card
							String drawCard = deck.get(0);
							curPlayer.addCard(drawCard);
							deck.remove(drawCard);
							System.out.println(curPlayerIndex+1 + ": DRAW");
						} else{
							if (nextCard.charAt(1) == '8') {
								String newColor = curPlayer.getNewColor();
								System.out.println(curPlayerIndex + 1 + ": " + nextCard + " LOCO " + newColor.charAt(0));
								curCard = newColor;
							} else {
								System.out.println(curPlayerIndex + 1 + ": " + nextCard);
								curCard = nextCard;
							}
							ifTakeTwo = true;
						}
					}
				} else {
					nextCard = curPlayer.getNextCard(curCard);
					if (nextCard == null) {
						// draw a card
						String drawCard = deck.get(0);
						curPlayer.addCard(drawCard);
						deck.remove(drawCard);
						System.out.println(curPlayerIndex+1 + ": DRAW");
					} else {
						curPlayer.removeCard(nextCard);
						if (curPlayer.isEmpty()) {
							System.out.println(curPlayerIndex+1 + ": " + nextCard + " (WINNER)");
							playing = false;
						} else {
							if (nextCard.charAt(1) == '8') {
								String newColor = curPlayer.getNewColor();
								System.out.println(curPlayerIndex + 1 + ": " + nextCard + " LOCO " + newColor.charAt(0));
								curCard = newColor;
							} else {
								System.out.println(curPlayerIndex + 1 + ": " + nextCard);
								curCard = nextCard;
							}
						}
						ifTakeTwo = true;
					}
				}
			}
			playIndex += 1;
		}
	}
}