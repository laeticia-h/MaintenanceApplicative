package trivia;

import java.util.*;

public class Game implements IGame {
   private static final int MAX_PLAYERS = 6;
   private static final int WINNING_SCORE = 6;

   private final List<String> players = new ArrayList<>();
   private final int[] positions = new int[MAX_PLAYERS];
   private final int[] scores = new int[MAX_PLAYERS];
   private final boolean[] inPenaltyBox = new boolean[MAX_PLAYERS];
   private final Map<String, LinkedList<String>> questions = new LinkedHashMap<>();

   private int currentPlayer = 0;
   private boolean isGettingOutOfPenaltyBox;

   public Game() {
      addCategory("Pop");
      addCategory("Science");
      addCategory("Sports");
      addCategory("Rock");
      addCategory("Geographie");
   }

   public void addCategory(String category) {
      LinkedList<String> questionList = new LinkedList<>();
      for (int i = 0; i < 50; i++) {
         questionList.add(category + " Question " + i);
      }
      questions.put(category, questionList);
   }

   public boolean add(String playerName) {
      if (players.contains(playerName) || players.size() >= MAX_PLAYERS) return false;
      players.add(playerName);
      positions[players.size() - 1] = 0;
      scores[players.size() - 1] = 0;
      inPenaltyBox[players.size() - 1] = false;
      System.out.println(playerName + " was added. They are player number " + players.size());
      return true;
   }

   public void roll(int roll) {
      String player = players.get(currentPlayer);
      System.out.println(player + " rolled a " + roll);

      if (inPenaltyBox[currentPlayer]) {
         if (roll % 2 != 0) {
            isGettingOutOfPenaltyBox = true;
            System.out.println(player + " is getting out of the penalty box");
            movePlayer(roll);
            askQuestion();
         } else {
            isGettingOutOfPenaltyBox = false;
            System.out.println(player + " is not getting out of the penalty box");
         }
      } else {
         movePlayer(roll);
         askQuestion();
      }
   }

   private void movePlayer(int roll) {
      positions[currentPlayer] = (positions[currentPlayer] + roll) % 12;
      System.out.println(players.get(currentPlayer) + "'s new location is " + positions[currentPlayer]);
      System.out.println("The category is " + getCurrentCategory());
   }

   private void askQuestion() {
      String category = getCurrentCategory();
      System.out.println(questions.get(category).removeFirst());
   }

    private String getCurrentCategory() {
      int position = positions[currentPlayer];
      return switch (position % 4) {
         case 0 -> "Pop";
         case 1 -> "Science";
         case 2 -> "Sports";
         default -> "Rock";
      };
   }

   public boolean handleCorrectAnswer() {
      if (inPenaltyBox[currentPlayer] && !isGettingOutOfPenaltyBox) {
         nextPlayer();
         return true;
      }

      System.out.println("Correct answer!");
      scores[currentPlayer]++;
      System.out.println(players.get(currentPlayer) + " now has " + scores[currentPlayer] + " points.");

      boolean winner = !didPlayerWin();
      nextPlayer();
      return winner;
   }

   public boolean wrongAnswer() {
      System.out.println(players.get(currentPlayer) + " answered incorrectly and is sent to the penalty box.");
      inPenaltyBox[currentPlayer] = true;
      nextPlayer();
      return true;
   }

   private void nextPlayer() {
      currentPlayer = (currentPlayer + 1) % players.size();
   }

   private boolean didPlayerWin() {
      return scores[currentPlayer] >= WINNING_SCORE;
   }
}
