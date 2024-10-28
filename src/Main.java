import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
public class Main {
    public static int numberOfGreenNumbers;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int arrayListSize = sc.nextInt();
        ArrayList<Integer> password = new ArrayList<>(arrayListSize);
        ArrayList<Integer> guess = new ArrayList<>(arrayListSize);

        ArrayList<Integer> isGreenOrNot = new ArrayList<>(arrayListSize);
        ArrayList<Integer> isYellowOrNot = new ArrayList<>(arrayListSize);


        createPassword(arrayListSize, password, rand);

//        setPassword(arrayListSize, password, sc);

        getFirstGuess(arrayListSize, guess, sc);

        findGreenNumbers(arrayListSize, guess, password, isGreenOrNot);

        makeArrayListOfZeroes(arrayListSize, isYellowOrNot);


        int numberOfAttemptsRemaining = 5; //the most optimal number of attempts
        for (; numberOfAttemptsRemaining > 0; numberOfAttemptsRemaining--)
        {
            if (numberOfAttemptsRemaining < 5) {
                getNewGuess(arrayListSize, sc, guess);
                getNewGreenNumbers(arrayListSize, guess, password, isGreenOrNot);
            }

            getYellowNumbers(arrayListSize, isYellowOrNot);

            getNewYellowNumbers(arrayListSize, guess, password, isGreenOrNot, isYellowOrNot);


            numberOfGreenNumbers = 0;

            displayColoursOfNumbers(arrayListSize, guess, password, isGreenOrNot, isYellowOrNot);

            System.out.println(" ");

            if (numberOfGreenNumbers == arrayListSize)
            {
                System.out.println(printWinMessage());
                break;
            }
        }

        if (numberOfAttemptsRemaining == 0) {
            printLoseMessage(arrayListSize, password);
        }
    }




    public static void createPassword (int arrayListSize, ArrayList<Integer> password, Random rand) {
        for (int i = 0; i < arrayListSize; i++) {
            password.add(rand.nextInt(10));
        }
    }

    public static void setPassword (int arrayListSize, ArrayList<Integer> password, Scanner sc) {
        for (int i = 0; i < arrayListSize; i++) {
            password.add(sc.nextInt());
        }
    }

    public static void getFirstGuess(int arrayListSize, ArrayList<Integer> guess, Scanner sc) {
        for (int i = 0; i < arrayListSize; i++) {
            guess.add(sc.nextInt());
        }
    }

    public static void findGreenNumbers (int arrayListSize, ArrayList<Integer> guess, ArrayList<Integer> password, ArrayList<Integer> isGreenOrNot) {
        for (int i = 0; i < arrayListSize; i++) {
            if (Objects.equals(guess.get(i), password.get(i))) isGreenOrNot.add(1);
            else isGreenOrNot.add(0);
        }
    }

    public static void makeArrayListOfZeroes (int arrayListSize, ArrayList<Integer> isYellowOrNot) {
        for (int i = 0; i < arrayListSize; i++) {
            isYellowOrNot.add(0);
        }
    }

    public static void getNewGuess (int arrayListSize, Scanner sc, ArrayList<Integer> guess) {
        for (int i = 0; i < arrayListSize; i++) {
            int element = sc.nextInt();
            guess.set(i, element);
        }
    }

    public static void getNewGreenNumbers(int arrayListSize, ArrayList<Integer> guess, ArrayList<Integer> password, ArrayList<Integer> isGreenOrNot) {
        for (int i = 0; i < arrayListSize; i++) {
            if (Objects.equals(guess.get(i), password.get(i))) isGreenOrNot.set(i, 1);
            else isGreenOrNot.set(i, 0);
        }
    }

    public static void getYellowNumbers (int arrayListSize, ArrayList<Integer> isYellowOrNot) {
        for (int i = 0; i < arrayListSize; i++) {
            isYellowOrNot.set(i, 0);
        }
    }

    public static void getNewYellowNumbers(int arrayListSize, ArrayList<Integer> guess, ArrayList<Integer> password, ArrayList<Integer> isGreenOrNot, ArrayList<Integer> isYellowOrNot) {
        for (int i = 0; i < arrayListSize; i++) {
            for (int j = 0; j < arrayListSize; j++) {
                if (Objects.equals(guess.get(i), password.get(j)) && isGreenOrNot.get(j) == 0) {
                    isYellowOrNot.set(i, 1);
                }
            }
        }
    }

    public static void displayColoursOfNumbers (int arrayListSize, ArrayList<Integer> guess, ArrayList<Integer> password, ArrayList<Integer> isGreenOrNot, ArrayList<Integer> isYellowOrNot) {
        for (int i = 0; i < arrayListSize; i++) {

            showFoundGreenNumbers(i, guess, password);

            if (!Objects.equals(guess.get(i), password.get(i)))
            {
                showFoundYellowNumbers(arrayListSize, i, guess, password, isGreenOrNot);
                showFoundRedNumbers(arrayListSize, i, isYellowOrNot, guess, password, isGreenOrNot);
            }
        }
    }

    public static void showFoundGreenNumbers(int i, ArrayList<Integer> guess, ArrayList<Integer> password) {
        if (Objects.equals(guess.get(i), password.get(i))) {
            System.out.print(guess.get(i) + "\uD83D\uDFE2 "); //green
            numberOfGreenNumbers++;
        }
    }

    public static void showFoundYellowNumbers (int arrayListSize, int i, ArrayList<Integer> guess, ArrayList<Integer> password, ArrayList<Integer> isGreenOrNot) {
        for (int j = 0; j < arrayListSize; j++) {
            if (Objects.equals(guess.get(i), password.get(j)) && isGreenOrNot.get(j) == 0) {
                System.out.print(guess.get(i) + "\uD83D\uDFE1 "); //yellow
                break;
            }
        }
    }

    public static void showFoundRedNumbers (int arrayListSize, int i, ArrayList<Integer> isYellowOrNot, ArrayList<Integer> guess, ArrayList<Integer> password, ArrayList<Integer> isGreenOrNot) {
        for (int j = 0; j < arrayListSize; j++) {
            if (isYellowOrNot.get(i) == 0 && (!Objects.equals(guess.get(i), password.get(j)) || isGreenOrNot.get(j) == 1)) {
                System.out.print(guess.get(i) + "\uD83D\uDD34 "); //red
                break;
            }
        }
    }

    public static String printWinMessage() {
        return "\nYou have successfully guessed the password!\nCongratulations!";
    }

    public static void printLoseMessage(int arrayListSize, ArrayList<Integer> password) {
        System.out.print("\nYou have no attempts left.\nYou lost.\nThe password was: [");
        printPassword(arrayListSize, password);
        System.out.println("]");
    }

    public static void printPassword(int arrayListSize, ArrayList<Integer> password) {
        for (int i = 0; i < arrayListSize; i++) {
            if (i < arrayListSize - 1) System.out.print(password.get(i) + " ");
            else System.out.print(password.get(i));
        }
    }

}