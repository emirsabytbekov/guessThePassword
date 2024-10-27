import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int arrayListSize = sc.nextInt();
        ArrayList<Integer> password = new ArrayList<>(arrayListSize);
        ArrayList<Integer> guess = new ArrayList<>(arrayListSize);


        int numberOfGreenNumbers = 0;
        ArrayList<Integer> positionsOfGreenNumbers = new ArrayList<>();

        for (int i = 0; i < arrayListSize; i++) {
            password.add(rand.nextInt(10));
        }

        for (int i = 0; i < arrayListSize; i++) {
            int element = sc.nextInt();
            guess.add(element);
        }

        for (int i = 0; i < arrayListSize; i++) {
            if (guess.get(i) == password.get(i)) {
                System.out.print("\n" + guess.get(i) + "\uD83D\uDFE2");
                numberOfGreenNumbers++;
                positionsOfGreenNumbers.add(i);
            }
            else if (guess.get(i) != password.get(i)) {
                for (int j = 0; j < arrayListSize; j++) {
                    if (guess.get(i) == password.get(j)) {
                        System.out.print("\n" + guess.get(i) + "\uD83D\uDFE1");
                        break;
                    }
                }
            }
            else System.out.print("\n" + guess.get(i) + "\uD83D\uDD34");
        }


    }
}