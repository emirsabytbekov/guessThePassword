import java.util.Objects;
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
        int numberOfGreenNumbers = 0;
        ArrayList<Integer> isFoundOrNot = new ArrayList<>(arrayListSize);
        ArrayList<Integer> isHasToBeYellowOrNot = new ArrayList<>(arrayListSize);


        for (int i = 0; i < arrayListSize; i++) {
            password.add(rand.nextInt(10));
        }

//        for (int i = 0; i < arrayListSize; i++) {
//            password.add(sc.nextInt());
//        }

        for (int i = 0; i < arrayListSize; i++) {
            int element = sc.nextInt();
            guess.add(element);
        }

        for (; p < arrayListSize; p++) {
            if (guess.get(p) == password.get(p)) isFoundOrNot.add(1);
            else isFoundOrNot.add(0);
        }

        for (int i = 0; i < arrayListSize; i++) {
            isHasToBeYellowOrNot.add(0);
        }



        for (int numberOfAttempts = 5; numberOfAttempts > 0; numberOfAttempts--)
        {
            if (numberOfAttempts < 5) {
                for (int i = 0; i < arrayListSize; i++) {
                    int element = sc.nextInt();
                    guess.set(i, element);
                }

                for (int i = 0; i < arrayListSize; i++) {
                    if (guess.get(i) == password.get(i)) isFoundOrNot.set(i, 1);
                    else isFoundOrNot.set(i, 0);
                }

            }

            for (int i = 0; i < arrayListSize; i++) {
                isHasToBeYellowOrNot.set(i, 0);
            }

            for (int i = 0; i < arrayListSize; i++) {
                for (int j = 0; j < arrayListSize; j++) {
                    if (Objects.equals(guess.get(i), password.get(j)) && isFoundOrNot.get(j) == 0) {
                        isHasToBeYellowOrNot.set(i, 1);
                    }
                }
            }


            numberOfGreenNumbers = numberOfGreenNumbers - numberOfGreenNumbers;

            for (int i = 0; i < arrayListSize; i++) {

                if (guess.get(i) == password.get(i)) {
                    System.out.print(guess.get(i) + "\uD83D\uDFE2 "); //green
                    numberOfGreenNumbers++;
                }

                else
                {
                    for (int j = 0; j < arrayListSize; j++) {
                        if (Objects.equals(guess.get(i), password.get(j)) && isFoundOrNot.get(j) == 0) {
                            System.out.print(guess.get(i) + "\uD83D\uDFE1 "); //yellow
                            break;
                        }
                    }
                    for (int j = 0; j < arrayListSize; j++) {
                        if (isHasToBeYellowOrNot.get(i) == 0 && (!Objects.equals(guess.get(i), password.get(j)) || isFoundOrNot.get(j) == 1)) {
                            System.out.print(guess.get(i) + "\uD83D\uDD34 "); //red
                            break;
                        }
                    }
                }
            }
            System.out.println(" ");

            if (numberOfGreenNumbers == arrayListSize)
            {
                System.out.println("You have successfully guessed the password!");
                System.out.println("Congratulations!");
                break;
            }
        }



        System.out.println(password);

    }
}