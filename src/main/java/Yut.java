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
    private String[] board = new String[17];
    Device user =new Device();
    Device com = new Device();
// player 와 computer 어떻게 표현해야 되는지


    // Initialize Yut Matrix
    public Yut() {
        yutMatrix = new String[raceRoom]; // Basic 2 Line 10 room Yut race
        for (int i = 0 ; i < yutMatrix.length ; i++)
            yutMatrix[i] = "__";

        yutMatrix[0] = "ox";   // 이런식으로 표현해야되는데 일단 고민
    }

    // Game start
    public void start() {
        clean();
        System.out.println("Play Game of Yut !!");


        int turn=0, userPreIndex=0, comPreIndex=0;
        Scanner in = new Scanner(System.in);
        for(int i=1; i<=16; i++) board[i]="_";



        //윷을 던지기 위해 0 이상의 숫자를 선택하고 넣으세요!
        System.out.println("pick num>0 and put to throw Yut for you!");
        while(in.nextInt()!=0){ //받은 값이 0이 아닐 때 계속 돌림
            if(turn%2==0) { //num이 짝수면 사용자 턴
                user.input(throwYut());

                if(user.getIndex()>16){
                    System.out.println("user win!\n");
                    break;
                }

                if(turn!=0) board[userPreIndex]="_";
                userPreIndex=user.getIndex();

                showBoard(turn);
                //board[user.getIndex()]="u";
                System.out.println("pick num>0 and put to throw Yut for enermy!");
            }
            else if(turn%2==1){ //num이 홀수면 컴퓨터 턴
                com.input(throwYut());

                if(user.getIndex()>16){
                    System.out.println("com win!\n");
                    break;
                }
                if(turn!=1) board[comPreIndex]="_";
                comPreIndex=com.getIndex();

                showBoard(turn);
                //board[com.getIndex()]="c";
                System.out.println("pick num>0 and put to throw Yut for you!");
            }
//            for(int i=1; i<17; i++) System.out.print(board[i]);
//            System.out.println();
            turn++;
        }
    }

    private void showBoard(int who) {

        if(user.getIndex()!=com.getIndex()){
            board[user.getIndex()]="u";
            board[com.getIndex()]="c";
        }
        else{//잡은 경우
            //catchYou(who);
        }

//        for(int i=1; i<17; i++) System.out.print(board[i]);
//            System.out.println();

            for(int i=0; i<3 ; i++){
                if(i==0){
                    reversePrint(9);
                    System.out.println();
                }
                else if(i==1){
                    int n=10;
                    for(int j=0; j!=6; j+=2) {
                        System.out.println(board[n] + " \t" + board[n - 6-j]);
                        n++;
                    }
                }
                else{
                    for(int j=13; j<17; j++) System.out.print(board[j]);
                    System.out.println(board[1]);
                }
        }

        //말 업고 잡는 코드 구현은 Yut 클래스 안에서 하는게 좋겠습니다.
    }

    //보드 출력 메소드이므로 건들면 안됩니다.
    private void reversePrint(int n){
        if(n<=4) return;
        System.out.print(board[n]);
        reversePrint(n-1);
    }

    // Throw yut 윷을 던지는 메소드.
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

    public void catchWho(int who){

    }

    // Clean Console 일단 이런식으로 쓰는거라서 이렇게 해봄 반복 횟수는 출력보고 결정
    public void clean() {
        for (int i=0; i<30; i++)
            System.out.println();
    }

}
