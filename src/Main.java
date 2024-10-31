import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
public class Main {
    public static int numberOfGreenNumbers;
    public static String greenDot = "\uD83D\uDFE2";
    public static String yellowDot = "\uD83D\uDFE1";
    public static String redDot = "\uD83D\uDD34";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        printGreeting(sc);

        boolean isShowRules  = Objects.equals(sc.nextLine(), "rules");
        clearScreen();
        if (isShowRules) {
            showRules(sc);
            clearScreen();
        }



        System.out.print("Enter how many numbers should a password contain: ");
        int passwordSize = sc.nextInt();

        ArrayList<Integer> password = new ArrayList<>(passwordSize);
        ArrayList<Integer> guessArrayList = new ArrayList<>(passwordSize);

        ArrayList<Integer> greenNumbersPlaces = new ArrayList<>(passwordSize);
        ArrayList<Integer> yellowNumbersPlaces = new ArrayList<>(passwordSize);


        createPassword(passwordSize, password, rand);

        System.out.println("Please, enter your guess:");
        getFirstGuess(passwordSize, guessArrayList, sc);

        findGreenNumbers(passwordSize, guessArrayList, password, greenNumbersPlaces);

        makeArrayListOfZeroes(passwordSize, yellowNumbersPlaces);


        int numberOfAttemptsRemaining = 5; //the most optimal number of attempts
        for (; numberOfAttemptsRemaining > 0; numberOfAttemptsRemaining--)
        {
            if (numberOfAttemptsRemaining < 5) {
                getNewGuess(passwordSize, sc, guessArrayList);
                getNewGreenNumbers(passwordSize, guessArrayList, password, greenNumbersPlaces);
            }

            getYellowNumbers(passwordSize, yellowNumbersPlaces);

            getNewYellowNumbers(passwordSize, guessArrayList, password, greenNumbersPlaces, yellowNumbersPlaces);


            numberOfGreenNumbers = 0;

            displayColoursOfNumbers(passwordSize, guessArrayList, password, greenNumbersPlaces, yellowNumbersPlaces);

            if (numberOfGreenNumbers != passwordSize) {
                System.out.print("\nPlease, enter your next guess:");
            }

            System.out.println(" ");

            if (numberOfGreenNumbers == passwordSize)
            {
                System.out.println(printWinMessage());
                break;
            }
        }

        if (numberOfAttemptsRemaining == 0) {
            printLoseMessage(passwordSize, password);
        }
    }




    public static void printGreeting(Scanner sc) {
        System.out.println("Welcome to the game Guess The Password!");
        System.out.println("Enter «rules», if you want to see the rules.");
        System.out.println("Or just press the «Enter» button to start the game.");
    }

    public static void clearScreen () {
        System.out.print("\n\n\n\n\n\n\n");
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void showRules (Scanner sc) {
        System.out.println("RULES:");
        System.out.println("\nYou should enter a number that denotes how many digits will be in the password.");
        System.out.println("After that you should enter your guess and digits in your guess should be divided by the space.");
        System.out.println("As soon as you press enter you will see colors of digits in your guess:");

        System.out.println("\nIf a number is RED " + redDot);
        System.out.println("It means that password does not contain that digit at all.");
        System.out.println("If a number is YELLOW " + yellowDot);
        System.out.println("It means that password contains that digit but it is placed not in the right spot.");
        System.out.println("If a number is GREEN " + greenDot);
        System.out.println("It means that password does contain that digit and it placed in the right spot.");

        System.out.println("\nYou have FIVE attempts to guess the password.");
        System.out.println("If you guess the password in five attempts, you win.");
        System.out.println("Otherwise you lose.");

        System.out.println("\nNOTE:");
        System.out.println("1" + redDot + " 4" + yellowDot + " 4" + yellowDot + " 8" + redDot + " DOES NOT mean that the password has two '4', it contains AT LEAST one '4'");
        System.out.println("2" + redDot + " 0" + greenDot + " 0" + redDot + " 7" + redDot + " means that the password contains exactly one '0'");
        System.out.println("1" + redDot + " 2" + greenDot + " 3" + redDot + " 2" + yellowDot + " means that the password contains AT LEAST two '2'");

        System.out.println("\nPress the «Enter» button (you will probably press it twice) to start the game.");
        sc.nextLine();
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
            System.out.print(guess.get(i) + greenDot + " ");
            numberOfGreenNumbers++;
        }
    }

    public static void showFoundYellowNumbers (int arrayListSize, int i, ArrayList<Integer> guess, ArrayList<Integer> password, ArrayList<Integer> isGreenOrNot) {
        for (int j = 0; j < arrayListSize; j++) {
            if (Objects.equals(guess.get(i), password.get(j)) && isGreenOrNot.get(j) == 0) {
                System.out.print(guess.get(i) + yellowDot + " ");
                break;
            }
        }
    }

    public static void showFoundRedNumbers (int arrayListSize, int i, ArrayList<Integer> isYellowOrNot, ArrayList<Integer> guess, ArrayList<Integer> password, ArrayList<Integer> isGreenOrNot) {
        for (int j = 0; j < arrayListSize; j++) {
            if (isYellowOrNot.get(i) == 0 && (!Objects.equals(guess.get(i), password.get(j)) || isGreenOrNot.get(j) == 1)) {
                System.out.print(guess.get(i) + redDot + " ");
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
            if (i < arrayListSize - 1) {
                System.out.print(password.get(i) + " ");
            }
            else {
                System.out.print(password.get(i));
            }
        }
    }

}