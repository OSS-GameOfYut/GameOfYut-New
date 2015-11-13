import java.util.*;

/**
 * Basic Yut 11.13
 */
public class Yut {
    private String[] yutMatrix;
    private String[] yut = {"DOE", "GAE", "GIRL", "YUT", "MOE"};
    private String typeOfPlayer = "o ";
    private final int playerNum = 2;
    private final int raceRoom = 10;
// player 와 computer 어떻게 표현해야 되는지


    // Initialize Yut Matrix
    public Yut() {
        yutMatrix = new String[raceRoom]; // Basic 2 Line 10 room Yut race
        for (int i = 0 ; i < yutMatrix.length ; i++)
            yutMatrix[i] = "__";

        yutMatrix[0] = "ox";   // 이런식으로 표현해야되는데 일단 고민
    }

    // Show Yut Matrix
    public void show() {
        for(int i=0 ; i<yutMatrix.length ; i++)
            System.out.print(yutMatrix[i] + " ");
//                if(yutMatrix[i][j] == 1) // 표현 방식 바꾸려면 이렇게도 가능한데 어떤 거 쓸지는 고민
//                    System.out.print("X");
//                else if(yutMatrix[i][j] == 0)
//                    System.out.print("O");
        System.out.println();

    }

    // Game start
    public void start() {
        clean();
        System.out.println("Play Game of Yut !!");

        Device user, com;
        user = new Device();
        com = new Device();

        int num;
        Scanner in = new Scanner(System.in);
        num=in.nextInt();

        while(num!=0){

            user.input(throwYut());

            showBoard(user, com);

            num=in.nextInt();

            com.input(throwYut());

            showBoard(user, com);

            num=in.nextInt();
        }

//        show();
//
//        // Player or Compute Two Hores Goal in is Game Over
//        while (yutMatrix[9].equals("o ") || yutMatrix[9].equals("x "))
//        {
//            // Player Phase
//            int move = throwYut();
//            // 플레이어에게 선택지 제시하고 그에 따라 움직임
//
//            // 예제는 사람만 던져서 움직이는 식
//
//
//            show();
//
//            // Computer Phase
//            move = throwYut();
//            // 컴퓨터는 상황에 맞게 움직여야함 상황은 SudoCode 참고
//
//            show();
//        }


    }

    private void showBoard(Device user, Device com) {

        for(int i=1; i<20; i++){
            if(i==user.getIndex() && i==com.getIndex()) System.out.println("x");
            else if(i==user.getIndex()) System.out.print("u");
            else if(i==com.getIndex()) System.out.print("c");
            else System.out.print("_");
        }System.out.println();
        //말 업고 잡는 코드 구현은 Yut 클래스 안에서 하는게 좋겠습니다.
    }

    // Throw yut
    public int throwYut() {
        Random r = new Random();
        System.out.println("Throws Yut!");
        int result = r.nextInt(5);
        System.out.println("Resulit is " + yut[result]); // 그냥 출력해보려고 만든거 실제 결과값은 매트릭스를 움직여야되므로 int로 받음
        return result;
    }

    // Move hores
    public void moveHorse() {
        // 플레이어인 경우 물어보고 원하는 말을 움직임

        // 컴퓨터에 경우 상황에 맞게 움직임

        // 상황 3: 말이 둘다 앞으로 나가 있을때 앞에 있는 말을 움직임

        // 상황 2: 말이 하나 앞으로 나가 있을때

        // 상황 1: 말이 어느것도 하나 앞으로 나가지 않을때

    }



    // Clean Console 일단 이런식으로 쓰는거라서 이렇게 해봄 반복 횟수는 출력보고 결정
    public void clean() {
        for (int i=0; i<30; i++)
            System.out.println();
    }

}
