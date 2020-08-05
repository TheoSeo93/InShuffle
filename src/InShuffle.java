import java.util.Arrays;

public class InShuffle {

    /**
     * Makes a standard 52 cards of deck.
     * @return standard deck
     */
    public static Card[] makeDeck(){
        Card[] deck = new Card[52];
        int idx = 0 ;
        for(Suite suite : Suite.values()){
            for(Name cardName : Name.values()){
                deck[idx] = new Card(suite, cardName);
                idx += 1;
            }
        }
        return deck;
    }

    /**
     * Performs in-shuffle
     * @param deck
     */
    public static void inShuffle(Card[] deck){
        Card[] topHalf = Arrays.copyOfRange(deck, 0, deck.length/2);
        Card[] bottomHalf = Arrays.copyOfRange(deck, deck.length/2, deck.length);
        for(int i = 0, bottomIdx = 0, topIdx = 0; i < deck.length ; i++) {
            if (i % 2 == 0) {
                deck[i] = bottomHalf[bottomIdx++];
            } else {
                deck[i] = topHalf[topIdx++];
            }
        }
    }

    /**
     * Performs in-shuffle by given iterations
     * @param deck
     * @param iterations
     */
    public static void inShuffle(Card[] deck, int iterations){
        for(int i = 0 ; i < iterations ; i ++){
            inShuffle(deck);
        }
    }

    /**
     * Finds the position of a card.
     * @param deck
     * @param topCard
     * @return
     */
    public static int findCardPosition(Card[] deck, Card topCard){
        int idx = 0;
        for(Card card : deck){
            if(card.equals(topCard))
                return idx;
            idx += 1;
        }
        return -1;
    }

    /**
     * Shows the result.
     * @param deck
     * @param iterations
     */
    public static void toString(Card[] deck, int iterations){
        Card[] initDeck = makeDeck();
        Card top = initDeck[0];
        Card bottom = initDeck[initDeck.length-1];
        for( Card card : deck ){
            System.out.println(card);
        }
        int topPos = findCardPosition(deck, top);
        int bottomPos = findCardPosition(deck, bottom);

        System.out.println("\nTop card position " + topPos);
        System.out.println("Bottom card position " + bottomPos);
        System.out.println("Top pos : " + topPos);

        int currentTopPos = 0;
        int step = 1;
        System.out.println("\nTwo positions touched each other at :");
        for(int i = 1 ; i <= iterations ; i++){
            if(currentTopPos >= deck.length/2 ){
                currentTopPos = (currentTopPos - deck.length/2) * 2;
                step = currentTopPos + 1;
            }else{
                currentTopPos = currentTopPos + step;
                step *= 2;
            }
            int currentBotPos = deck.length -1 - currentTopPos;
            if(Math.abs(currentBotPos - currentTopPos) == 1) {
                System.out.print(i + "th shuffle\t");
            }
        }
    }

    /**
     * Commandline execution example :
     * java -jar Inshuffle.jar -i 10
     * Executes 10 in-shuffles.
     * @param args
     */
    public static void main(String[] args) {
        Card[] deck = makeDeck();
        if(args.length != 2) {
            throw new IllegalArgumentException("Not a valid number of arguments: " + args.length + "\n ex) shuffle -i 10");
        } else {
          if(args[0].length() == 2 && args[0].charAt(0)=='-' && args[0].charAt(1) == 'i') {
              try{
                  int iterations = Integer.parseInt(args[1]);
                  inShuffle(deck, iterations);
                  toString(deck, iterations);
              }catch(NumberFormatException ex){
                  throw new IllegalArgumentException("Not a valid argument :" + args[1]);
              }
          } else {
              throw new IllegalArgumentException("Not a valid argument :" + args[0]);
          }
        }

    }
}
