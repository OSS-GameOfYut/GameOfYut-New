import java.util.*;

/**
 * Game of Yut 1.0 버전
 * (Basic Yut)
 * default 사항
 * 윷놀이판은 일직선으로 되어있다.
 * 유저와 말은 한마리 씩 가지고 있으며 말이 판에 끝을 넘어가면 게임이 종료된다.
 * 도 개 걸 윷 모를 던지고 1,2,3,4,5 만큼 움직이며 윷과 모는 한번 더 던지지 않는다.
 * 말을 던질 때 플레이어는 입력값을 받아 던지고 컴퓨터는 자동으로 던진다.
 * 윷놀이판의 출력은 같은 위치 x, u, c로 출력한다.
 */

public class Yut {
    private Device user, com;
    private boolean done;
    private Scanner in;

    private String[] yut = {"DOE", "GAE", "GIRL", "YUT", "MOE"};
    private final int playerNum = 2;
    private final int raceRoom = 20;

    // Initialize Yut Matrix
    public Yut() {
        done = false;
        in = new Scanner(System.in);

        user = new Device();
        com = new Device();
    }

    // Show Yut Matrix
    public void showBoard(Device user, Device com) {
        for(int i=1; i<raceRoom; i++){
            // 유저와 컴퓨터 같은 위치일때 표시
            if(i==user.getLocate() && i==com.getLocate()) System.out.println("x");
            // 유저의 위치 표시
            else if(i==user.getLocate()) System.out.print("u");
            // 컴퓨터의 위치 표시
            else if(i==com.getLocate()) System.out.print("c");
            // 아무도 없을때 위치 표시
            else System.out.print("_");
        }   System.out.println();
        //말 업고 잡는 코드 구현은 Yut 클래스 안에서 하는게 좋겠습니다.
    }
    // Game play
    public void play() {
        clean();
        System.out.println("Play Game of Yut !!");

        // 컴퓨터나 유저가 yutMatrix의 마지막 지점에 도착하면 반복문 종료
        while(!done)
        {
            // Player Phase
            // 윷 던지는걸 플레이어에게 입력받아 던짐
            // 1을 입력 받아서 윷 던지는걸로 default
            int num;
            num=in.nextInt();

            user.input(throwYut());
            showBoard(user, com);

            // Computer Phase
            // 컴퓨터는 자동으로 던짐

            com.input(throwYut());
            showBoard(user, com);

            // 골인 체크
            done = checkWin(user, com);

        }

    }

    // Throw yut
    // 윷 던지고 던진 결과 출력 후 반환
    public int throwYut() {
        Random r = new Random();
        System.out.println("Throws Yut!");
        int result = r.nextInt(5);
        System.out.println("Resulit is " + yut[result]);
        return result;
    }

    // Move hores 말이 여러마리일 때 움직이는 method
    // 사람은 입력값받아서 움직임 com은 상황에 맞게 움직임
    public void moveComputer() {

        // 컴퓨터에 경우 상황에 맞게 움직임

        // 말이 하나일 때
            // 상황 3: 말이 둘다 앞으로 나가 있을때 앞에 있는 말을 움직임

            // 상황 2: 말이 하나 앞으로 나가 있을때

            // 상황 1: 말이 어느것도 하나 앞으로 나가지 않을때
    }

    // 유저나 컴퓨터가 말을 움직여 골인하는지 체크
    public boolean checkWin(Device user, Device com) {
        // 유저가 골인지점을 넘어 섰을 떄
        if(user.getLocate() >= raceRoom) {
            System.out.println("\n\n\nPlayer Win!\n\n\n");

            // 게임이 끝나서 위치 초기화하여 다시 게임 시작 가능 할 수 있도록
            user.setLocate(0);
            com.setLocate(0);

            return true;
        }
        // 컴퓨터가 골인지점을 넘어 섰을 때
        if(com.getLocate() >= raceRoom) {
            System.out.println("\n\n\nPlayer Lose!\n\n\n");

            // 게임이 끝나서 위치 초기화하여 다시 게임 시작 가능 할 수 있도록
            user.setLocate(0);
            com.setLocate(0);

            return true;
        }
        // 그렇지 않을 때
        return false;
    }


    // Clean Console 콘솔 창일때 화면 지우는 method
    public void clean() {
        for (int i=0; i<30; i++)
            System.out.println();
    }
}
