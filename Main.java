import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    static class Yatzy {
        private boolean isGameOver = false;
        private Dice[] dice;
        private ScoreBoard scoreBoard;

        public String showDice() {
            String result = "";
            for (int i = 0; i < dice.length; i++) {
                result += dice[i].getValue() + " ";
            }
            return result;
        }

        public int sumDices(int option) {
            int sum = 0;
            for (int i = 0; i < dice.length; i++) {
                if (dice[i].getValue() == option) {
                    sum += dice[i].getValue();
                }
            }
            return sum;
        }

        public Yatzy() {
            this.scoreBoard = new ScoreBoard();
            dice = new Dice[5];
            for (int i = 0; i < dice.length; i++) {
                dice[i] = new Dice();
            }
        }

        public void playRound() {
            // three "dice rolls" per round
            for (int i = 0; i < 3; i++) {
                // Roll all the dice
                for (int j = 0; j < dice.length; j++) {
                    dice[j].roll();
                }
                System.out.println("Dice throw: " + String.valueOf(i + 1));
                System.out.println("Result: " + this.showDice());
                // if we are on the final dice roll, we're not allowed to rethrow any dice
                if (i < 2) {
                    System.out.println("Do you want to rethrow the dice? y/N");
                    String choice = scanner.nextLine();
                    if (choice.equalsIgnoreCase("Y")) {
                        continue;
                    }
                }
                System.out.println("What position do you want to score to?");
                if (this.scoreBoard.getScore(1) == 0) {
                    System.out.println("1. Ones");
                }
                if (this.scoreBoard.getScore(2) == 0) {
                    System.out.println("2. Twos");
                }
                if (this.scoreBoard.getScore(3) == 0) {
                    System.out.println("3. Threes");
                }
                if (this.scoreBoard.getScore(4) == 0) {
                    System.out.println("4. Fours");
                }
                if (this.scoreBoard.getScore(5) == 0) {
                    System.out.println("5. Fives");
                }
                if (this.scoreBoard.getScore(6) == 0) {
                    System.out.println("6. Sixes");
                }

                int option = Integer.parseInt(scanner.nextLine());
                this.scoreBoard.setScore(option, this.sumDices(option));
                this.scoreBoard.printScoreBoard();

                System.out.println("Do you want to exit the game? y/N");
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("Y")) {
                    this.isGameOver = true;
                    System.out.println("Thank you for playing... Filip Andersson, wishes you a nice day! :-)");
                    System.out.println("© 2023 Wing Ling Software Taiwan");
                    break;
                }
                break;
            }

        }
    }

    static class ScoreBoard {
        private Map<Integer, Integer> scores;

        public ScoreBoard() {
            // Initialize all options with a score of 0
            this.scores = new HashMap<>();
            this.scores.put(1, 0);
            this.scores.put(2, 0);
            this.scores.put(3, 0);
            this.scores.put(4, 0);
            this.scores.put(5, 0);
            this.scores.put(6, 0);
        }

        public void setScore(int option, int score) {
            if (this.scores.get(option) != 0) {
                System.out.println("You have already scored for this option!");
                return;
            }
            this.scores.put(option, score);
        }

        public int getScore(int option) {
            return this.scores.get(option);
        }

        public void printScoreBoard() {
            System.out.println("Scoreboard:");
            System.out.println("Ones: " + this.scores.get(1));
            System.out.println("Twos: " + this.scores.get(2));
            System.out.println("Threes: " + this.scores.get(3));
            System.out.println("Fours: " + this.scores.get(4));
            System.out.println("Fives: " + this.scores.get(5));
            System.out.println("Sixes: " + this.scores.get(6));
            int sum = 0;
            for (int score : this.scores.values()) {
                sum += score;
            }
            System.out.println("Your total score: " + sum);
        }

    }

    static class Dice {
        private int value;

        public void roll() {
            this.value = (int) (Math.random() * 6) + 1;
        }

        public int getValue() {
            return this.value;
        }

    }

    public static void main(String[] args) {
        Yatzy game = new Yatzy();
        while (!game.isGameOver) {
            game.playRound();
        }
    }
}