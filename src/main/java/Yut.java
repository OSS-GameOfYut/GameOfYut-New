import java.util.*;

/**
 * Yut
 */

public class Yut {

    private String[] yut = {"DOE", "GAE", "GIRL", "YUT", "MOE"};
    private final int raceRoom = 20;
    private final int numOfDevice = 2;
    private ArrayList<Device> device =new ArrayList<Device>(numOfDevice * 2);// device;
    private final String user = "U";
    private final String com = "C";
    // done을 start method 밖으로 꺼낸 이유는
    // selectComputerHorse method에서 말을 먹거나 엎을시 한번더 윷을 던져야 되기 때문입니다.
    private boolean done; // 윷던지는 반복문 체크입니다. false일시 반복하지 않고 true인 경우 계속해서 반복합니다.

    // Initialize Yut Matrix
    public Yut() {
        // 각각의 말들은 ArrayList의 유저 / 컴퓨터 순으로 들어있습니다.
        for (int i = 0 ; i < numOfDevice*2; i++)
            device.add(new Device());
        done = false;

        device.get(0).setLocate(0);
        device.get(1).setLocate(0);
        device.get(2).setLocate(0);
        device.get(3).setLocate(0);
    }

    // Game start
    public void start() {
        int index = 0;
        clean();
        System.out.println(">> Yutnori start !!  ***-----\n");

        // 럭키 넘버 대신 럭키 윷을 보여주도록 했습니다.
        showFirstBackground(yut[new Random().nextInt(5)]);

        // 사용자 차례와 컴퓨터 차례가 번갈아 가면서 진행됩니다.
        ex: while(true){
            // 윷을 던지고 난 결과값 입니다.
            int result;

            do {
                // 플레이어의 차례입니다.
                done = false; // 반복 후 done을 초기화합니다.

                // 윷을 던진 결과를 저장합니다.
                result = throwYut();

                // 둘중하나가 엎은걸 확인하는게 true면 pile의 false를 넣어 출력문 변경
                showMiddleBackground(result, !device.get(0).getCarryOn() || !device.get(1).getCarryOn());

                //말을 선택
                int a = checkIndex();
                if(a==2) index=0;
                else index = a;
                System.out.println();

                // 해당 말의 윷을 던져 나온 결과를 입력합니다. 윷이나 모일시 계속해서 반복합니다.
                // 말을 먹거나 엎은 경우 말들의 위치를 변경시키고 반복을 하여야 하여 체크합니다.
                // 사용자인지 컴퓨터인지 확인하도록 파라미터를 넣습니다. //먹거나 업은 경우 윷 한번 더 던진다고 하자.
                done = checkHorese(user, result, index); //결과(도개걸..),내가던졌음,움직인 말

                if(device.get(index).getCarryOn()) {
                    if(a==2){//a가 2면 업혀있다는 의미
                        device.get(0).move(result);
                        device.get(1).move(result);
                    }
                    else if(index==0) {//업는 과정
                        device.get(0).move(result);
                        device.get(1).setCarryOn(true);
                    }
                    else{
                        device.get(1).move(result);
                        device.get(0).setCarryOn(true);
                    }
                }
                else
                    device.get(index).move(result);

                // 윷판을 출력합니다.
                showBoard();

                // 말을 움직인 후 게임에서 이길지 체크 하여 이긴다면 게임을 끝냅니다.
                if (device.get(0).getLocate() > raceRoom && device.get(1).getLocate() > raceRoom) {
                    System.out.println("user win!\n");
                    break ex;
                }
            } while(done);

            System.out.println("\n>> Computer Phase   *---------------------------------------------------------------------------------------------------------");
            do {
                // 컴퓨터의 차례입니다.
                done = false; // 반복 후 done을 초기화합니다.

                // 윷을 던진 결과 값을 저장합니다.
                result = throwYut();

                // 컴퓨터는 selectComputerHorse method를 통해 움직일 말을 결정합니다.
                index = selectComputerHorse(result);

                // 해당 말의 윷을 던져 나온 결과를 입력합니다. 윷이나 모일시 계속해서 반복합니다.
                // 말을 먹거나 엎은 경우 말들의 위치를 변경시키고 반복을 하여야 하여 체크합니다.
                // 사용자인지 컴퓨터인지 확인하도록 파라미터를 넣습니다.
                done = checkHorese(com, result, index);
                int a = 0;

                if(device.get(2).getCarryOn()) a=2;
                if(device.get(index).getCarryOn()) {
                    if(a==2){//a가 2면 업혀있다는 의미
                        device.get(2).move(result);
                        device.get(3).move(result);
                    }
                    else if(index==2) {//업는 과정
                        device.get(2).move(result);
                        device.get(3).setCarryOn(true);
                    }
                    else{
                        device.get(3).move(result);
                        device.get(2).setCarryOn(true);
                    }
                }
                else
                    device.get(index).move(result);

                String out=yut[result];
                System.out.println(">> computer get "+out+"!\n");

                // 윷판을 출력합니다.
                showBoard();

                // 말을 움직인 후 게임에서 이길지 체크 후 이긴다면 게임을 끝냅니다.
                if (device.get(2).getLocate() > raceRoom && device.get(3).getLocate() > raceRoom) {
                    System.out.println("computer win!\n");
                    break ex;
                }

                askToKeepGoing();
                if(!done) System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
            } while(done);
        }
    }

    private void showFirstBackground(String yut) {
        System.out.println("   . . . . . G A M E . . . . . [   User's turn!             ]");
        System.out.println("   . . . . . . o f . . . . . . [   Put any num here!        ]");
        System.out.println("   . . . . .  Y u t  . . . . . [   Your lucky Yut is.. " + yut + "  ]\n");
    }

    //pile값은 디바이스에서 정보를 받아와 넣음. 겹쳐졌는지 아닌지만 확인하는 것.
    private void showMiddleBackground(int result, boolean pile) {
        System.out.println("\n>> board preview   *---------------------------------------------------------------------------------------------------------\n");
        showBoard();
        System.out.println("\n>> user phase--------------------------------------------------------------------------------------------------------------------\n");

        if(yut[result].equals("DOE")){
            System.out.println("   . . . . [ ][x][x][x] . . .  [  Result is ' DOE ' !       ]");
            System.out.println("   . . . . [ ][x][x][x] . . .  [  You can go one step       ]");
            if(pile) System.out.print("   . . . . [ ][x][x][x] . . .  [  Select Horse Number (0~1) ] : " );
            else System.out.println("   . . . . [ ][x][x][x] . . .  [                             ]");
        }
        else if(yut[result].equals("GAE")){
            System.out.println("   . . . . [ ][ ][x][x] . . .  [   Result is ' GAE ' !      ]");
            System.out.println("   . . . . [ ][ ][x][x] . . .  [   You can go two steps     ]");
            if(pile) System.out.print("   . . . . [ ][ ][x][x] . . .  [  Select Horse Number (0~1) ] : ");
            else System.out.println("   . . . . [ ][ ][x][x] . . .  [                             ]");
        }
        else if(yut[result].equals("GIRL")){
            System.out.println("   . . . . [ ][ ][ ][x] . . .  [   Result is ' GIRL ' !     ]");
            System.out.println("   . . . . [ ][ ][ ][x] . . .  [   You can go three steps   ]");
            if(pile) System.out.print("   . . . . [ ][ ][ ][x] . . .  [  Select Horse Number (0~1) ] : ");
            else System.out.println("   . . . . [ ][ ][ ][x] . . .  [                             ]");
        }
        else if(yut[result].equals("YUT")){
            System.out.println("   . . . . [ ][ ][ ][ ] . . .  [   Result is ' YUT ' !      ]");
            System.out.println("   . . . . [ ][ ][ ][ ] . . .  [   You can go four steps    ]");
            if(pile) System.out.print("   . . . . [ ][ ][ ][ ] . . .  [  Select Horse Number (0~1) ] : ");
            else System.out.println("   . . . . [ ][ ][ ][ ] . . .  [                             ]");
        }
        else if(yut[result].equals("MOE")){
            System.out.println("   . . . . [x][x][x][x] . . .  [   Result is ' MOE ' !      ]");
            System.out.println("   . . . . [x][x][x][x] . . .  [   You can go one step      ]");
            if(pile) System.out.print("   . . . . [x][x][x][x] . . .  [  Select Horse Number (0~1) ] : ");
            else System.out.println("   . . . . [x][x][x][x] . . .  [                             ]");
        }
    }

    // 콘솔창에 윷놀이판 모양으로 출력합니다.
    private void showBoard() {
        int u1=device.get(0).getLocate(), u2=device.get(1).getLocate();
        int c1=device.get(2).getLocate(), c2=device.get(3).getLocate();
        int temp;
        String output="   S";

        for(int i=0; i<=raceRoom; i++){
            output+="[";
            temp=(i==0)?(4):(2);
            if(u1==i) { output+="u0"; temp--;}
            if(u2==i) { output+="u1"; temp--;}
            if(c1==i) { output+="c0"; temp--;}
            if(c2==i) { output+="c1"; temp--;}

            for(;temp>0;temp--) output+="  ";

            output+="]";

            if(i==0) output+=" ";
        }
        System.out.println(output+"\n");
    }

    // Throw yut
    public int throwYut() {
        Random r = new Random();
        int result = r.nextInt(16);
        // 윷나올 확률 6:4:4:1:1

// 도 0,1,3,4,5 (6/16)
        if(result <= 5)
            return 0;

// 개 6,7,8,9 (4/16)
        else if (result <= 9)
            return 1;

// 걸 10,11,12,13 (4/16)
        else if (result <= 13)
            return 2;

// 윷 14 (1/16)
        else if (result <= 14)
            return 3;

// 모 15 (1/16)
        else
            return 4;
    }

    // 컴퓨터가 말을 선택하여 말의 번호를 돌려줍니다.
    public int selectComputerHorse(int result) {
        if(device.get(2).getLocate()>raceRoom) return 3;
        else if(device.get(3).getLocate()>raceRoom) return 2;

        int temp1 = isCatch(result, 2);
        int temp2 = isCatch(result, 3);

        if(temp1 < 0) {
            //temp2를 쓰면 됨
            if (temp2 < 0) {
                if(compareLocate(result, 2))
                    return 2;
                else{
                    if(compareLocate(result, 3)){
                        return 3;
                    }
                    else
                    {
                        if(device.get(2).getLocate()>=device.get(3).getLocate())
                            return 2;
                        else return 3;
                    }
                }
            }
            else{
                return 3;
            }
        }
        else{
            //3번이 무조건 움직임
            return 2;
        }
    }

    // 상대말을 잡을수있는지 확인하고 잡을수 있는 말의 index를 return해줌
    public int isCatch(int result, int index) {
        // 말이 결승점을 통과했을 경우 계산하지 않음
        result++;

        if (device.get(index).getLocate() + result > raceRoom) return -1;
        else if (index == 0 || index == 1) {
            if (device.get(index).getLocate() + result == device.get(2).getLocate()) return 2;
            if (device.get(index).getLocate() + result == device.get(3).getLocate()) return 3;
        }
        else if (index == 2 || index == 3) {
            if (device.get(index).getLocate() + result == device.get(0).getLocate()) return 0;
            if (device.get(index).getLocate() + result == device.get(1).getLocate()) return 1;
        }
        return -1;
    }

    // User끼리 혹은 Computer끼리 말의 위치를 비교함
    public boolean compareLocate(int result, int index) {
        result++;

        if (index == 0)
            return device.get(index).getLocate() + result == device.get(1).getLocate();
        else if(index == 1)
            return device.get(index).getLocate() + result == device.get(0).getLocate();
        else if(index == 2)
            return device.get(index).getLocate() + result == device.get(3).getLocate();
        else
            return device.get(index).getLocate() + result == device.get(2).getLocate();
    }

// user의 index를 검사하는 함수
    public int checkIndex() {
        String input;
        int index;

        Scanner in = new Scanner(System.in);
        // 엎은 경우 index를 2값으로 반환합니다.

        if(device.get(0).getCarryOn()){
            index = 2;
            askToKeepGoing();
        }
        else{
            // 사용자에게서 index값을 입력받는데 말번호가 아닐시 말번호를 입력하라는 문구를 추가합시다.ㅏ
            do{
                index = 99;
                input = in.nextLine();

                if(input.length() != 1)
                    System.out.println("Wrong Input!");
                else if (input.charAt(0) == '0' || input.charAt(0) == '1') {
                    index = Integer.parseInt(input);
                    // 도착한 말을 선택했을 경우
                    if(device.get(index).getLocate() > raceRoom ) {
                        System.out.println("Choose another horse!!");
                        index = 99;
                    }
                }
                else
                    System.out.println("Wrong Input!");
            }while(index!=0 && index!=1);
        }
        return index;
    }

    private void askToKeepGoing() {
        Scanner in = new Scanner(System.in);
        System.out.println("Put any key to keep going : ");
        in.nextLine();
    }

    // 말을 먹거나 엎었을 경우를 처리하고 반복해야할지를 정해줍니다.
    public boolean checkHorese(String horse, int result, int index) {
        int temp = isCatch(result, index);

        // 말을 먹는 경우
        if (temp >= 0) {
            // 먹을 말이 엎어진 말인 경우
            if (device.get(temp).getCarryOn())
            {
                // 먹는 말이 사용자 말인 경우 컴퓨터 말들을 초기화
                if(horse.equals(user)){
                    device.get(2).setLocate(0);
                    device.get(3).setLocate(0);
                    device.get(2).setCarryOn(false);
                    device.get(3).setCarryOn(false);
                }
                // 먹는 말이 컴퓨터 말인 경우 사용자 말들을 초기화
                else{
                    device.get(0).setLocate(0);
                    device.get(1).setLocate(0);
                    device.get(0).setCarryOn(false);
                    device.get(1).setCarryOn(false);
                }
            }
            // 먹을 말이 일반적인 경우 그 말을 초기화
            else device.get(temp).setLocate(0);
            return true;
        }
        // 말을 엎는 경우
        else if (compareLocate(result, index)){
            // 그 말의 setCarryOn을 true
            device.get(index).setCarryOn(true);
            return true;
        }

        // 윷이나 모인 경우
        else if (result == 3 || result == 4)
            return true;
        return false;
    }

    // 콘솔창을 지워줍니다.
    public void clean() {
        for (int i=0; i<30; i++)
            System.out.println();
    }
}