import java.util.Random;

/**
 * Basic Yut 11.13
 */
public class Yut {
    private String[] yutMatrix;
    private String[] yut = {"DOE", "GAE", "GIRL", "YUT", "MOE"};
    private String[] typeOfPlayer = {"o", "x"};
    final int playerNum = 2;
    final int raceRoom = 10;
// player 와 computer 어떻게 표현해야 되는지


    // Initialize Yut Matrix
    public Yut() {
        yutMatrix = new String[raceRoom]; // Basic 2 Line 10 room Yut race
        yutMatrix[0][0] = 1; yutMatrix[0][1] = 1;   // 이런식으로 표현해야되는데 일단 고민
    }

    // Show Yut Matrix
    public void show() {
        for(int i=0; i<playerNum; i++) {
            for(int j=0; j<raceRoom; j++) {
                System.out.print(yutMatrix[i][j]);
//                if(yutMatrix[i][j] == 1) // 표현 방식 바꾸려면 이렇게도 가능한데 어떤 거 쓸지는 고민
//                    System.out.print("X");
//                else if(yutMatrix[i][j] == 0)
//                    System.out.print("O");
            }
            System.out.println();
        }
    }

    // Game start
    public void start() {
        clean();
        System.out.println("Play Game of Yut !!");
        show();

        // Player or Compute Two Hores Goal in is Game Over
        while (yutMatrix[0][10] == 2 || yutMatrix[1][10] == 2)
        {
            // Player Phase
            int move = throwYut();
            // 플레이어에게 선택지 제시하고 그에 따라 움직임

            // 예제는 사람만 던져서 움직이는 식


            show();

            // Computer Phase
            move = throwYut();
            // 컴퓨터는 상황에 맞게 움직여야함 상황은 SudoCode 참고

            show();
        }


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
    public void moveHorse() {}



    // Clean Console 일단 이런식으로 쓰는거라서 이렇게 해봄 반복 횟수는 출력보고 결정
    public void clean() {
        for (int i=0; i<30; i++)
            System.out.println();
    }

}
