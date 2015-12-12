import java.util.Scanner;

/**
 * AppMain
 **/

public class AppMain {
    private Scanner in;
    private boolean done;
    Yut yut;

    public AppMain()   {
        in = new Scanner(System.in);
        done = false;
        yut = new Yut();
    }

    public void menu() {
        do {
            System.out.println("1. Game Start");
            System.out.println("2. Game Exit");
            System.out.print("Choose Number : ");

            String input = in.nextLine();

            if(input.length() != 1)
                System.out.println("Wrong Input!");
            else {
                if (input.charAt(0) == '1') {
                    yut = new Yut();
                    start();
                }
                else if (input.charAt(0) == '2') {
                    done = true;
                    System.out.println("Game Exit");
                }
                else
                    System.out.println("Wrong Input!");
            }

        } while(!done);
    }

    public void start() {
        System.out.println("Game start!");
        yut.start();
    }

    public static void main(String args[]) {
        AppMain main = new AppMain();
        main.menu();
    }
}



