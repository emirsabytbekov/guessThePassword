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

        int p = 0;
        ArrayList<Integer> isFoundOrNot = new ArrayList<>(arrayListSize);

//        for (int i = 0; i < arrayListSize; i++) {
//            password.add(rand.nextInt(10));
//        }
        for (int i = 0; i < arrayListSize; i++) {
            password.add(sc.nextInt());
        }

        for (int i = 0; i < arrayListSize; i++) {
            int element = sc.nextInt();
            guess.add(element);
        }

        for (int numberOfAttempts = 5; numberOfAttempts > 0; numberOfAttempts--)
        {
            if (numberOfAttempts < 5) {
                for (int i = 0; i < arrayListSize; i++) {
                    int element = sc.nextInt();
                    guess.set(i, element);
                }
            }

            for (int i = 0; i < arrayListSize; i++) {
                for (; p < arrayListSize; p++) {
                    if (guess.get(p) == password.get(p)) isFoundOrNot.add(1);
                    else isFoundOrNot.add(0);
                }

                if (guess.get(i) == password.get(i)) {
                    System.out.print("\n" + guess.get(i) + "\uD83D\uDFE2");
                }
                else
                {
                    for (int j = 0; j < arrayListSize; j++) {
                        if (guess.get(i) == password.get(j) && isFoundOrNot.get(j) == 0) {
                            System.out.print("\n" + guess.get(i) + "\uD83D\uDFE1");
                            break;
                        }
                        else {
                            System.out.print("\n" + guess.get(i) + "\uD83D\uDD34");
                            break;
                        }
                    }
                }

            }
        }



        System.out.println(password);

    }
}