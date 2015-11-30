import java.util.Scanner;

/**
 * AppMain 1.1 11.27
 */
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
            int num = in.nextInt();
            switch(num) {
                case 1:
                    start();
                    break;
                case 2:
                    done = true;
                    System.out.println("Game Exit");
                    break;
                default:
                    System.out.println("Wrong Input!");
                    break;
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