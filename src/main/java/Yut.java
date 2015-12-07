import java.util.*;

/**
 * Yut 11/27
 */
public class Yut {


    private String[] yut = {"DOE", "GAE", "GIRL", "YUT", "MOE"};
    private final int raceRoom = 20;    // 실제 윷판 20칸으로 구성되어있는데 16칸으로 초기 구성하여 일단 고정합니다.
    // 추후 최종판은 29판 대각선 없는건 20판으로 바꿔야됩니다.
    private final int numOfDevice = 2;
    private ArrayList<Device> device =new ArrayList<Device>(numOfDevice * 2);// device;
    private final String user = "U";
    private final String com = "C";
    // done을 start method 밖으로 꺼낸 이유는
    // selectComputerHorse method에서 말을 먹거나 엎을시 한번더 윷을 던져야 되기 때문입니다.
    private boolean done; // 윷던지는 반복문 체크입니다. false일시 반복하지 않고 true인 경우 계속해서 반복합니다.

    // Initialize Yut Matrix
    public Yut() {

        // type과 type에 따른 index(몇번 말인지)를 정해 device객체를 생성
        int userIndex = 0;
        int comIndex = 0;

        // 각각의 말들은 ArrayList의 유저 / 컴퓨터 순으로 들어있으며
        // 각각의 유저와 컴퓨터의 말들의 각각의 갯수만큼 번호를 가지고 0번부터 시작하고 있습니다.
        for (int i = 0 ; i < numOfDevice*2; i++) {
            if (i < numOfDevice)
                device.add(new Device(user, userIndex++));
            else
                device.add(new Device(com, comIndex++));
        }

        done = false;
    }

    // Game start
    public void start() {

        int index = 0;
        clean();
        System.out.println("-----***    Yutnori start !!  ***-----\n");

        // 럭키 넘버 대신 럭키 윷을 보여주도록 했습니다.
        showFirstBackground(yut[new Random().nextInt(5)]);


        // 사용자 차례와 컴퓨터 차례가 번갈아 가면서 진행됩니다.

        ex:       while(true){
            // 윷을 던지고 난 결과값 입니다.
            int result;

            do {
                // 플레이어의 차례입니다.
                done = false; // 반복 후 done을 초기화합니다.
                // 윷을 던진 결과를 저장합니다.
                result = throwYut();

                showMiddleBackground(result, true);

                //말을 선택
                int a = checkIndex();
                if(a==2) index=0;
                else index = a;


                // 해당 말의 윷을 던져 나온 결과를 입력합니다. 윷이나 모일시 계속해서 반복합니다.
                // 말을 먹거나 엎은 경우 말들의 위치를 변경시키고 반복을 하여야 하여 체크합니다.
                // 사용자인지 컴퓨터인지 확인하도록 파라미터를 넣습니다. //먹거나 업은 경우 윷 한번 더 던진다고 하자.
                done = checkHorese(user, result, index); //결과(도개걸..),내가던졌음,움직인 말
                if(device.get(index).getCarryOn()) {
                    device.get(0).move(result);
                    device.get(1).move(result);
                }
                else
                    device.get(index).move(result);

                // 말을 움직인 후 게임에서 이길지 체크 하여 이긴다면 게임을 끝냅니다.
                if (device.get(0).getLocate() > raceRoom && device.get(1).getLocate() > raceRoom) {
                    System.out.println("user win!\n");
                    break ex;
                }

                // 윷판을 출력합니다.
                showBoard();

            } while(done);

            System.out.println("\nComputer Phase");
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
                if(device.get(index).getCarryOn()) {
                    device.get(2).move(result);
                    device.get(3).move(result);
                }
                else
                    device.get(index).move(result);

                // 말을 움직인 후 게임에서 이길지 체크 후 이긴다면 게임을 끝냅니다.
                if (device.get(2).getLocate() > raceRoom && device.get(3).getLocate() > raceRoom) {
                    System.out.println("computer win!\n");
                    break ex;
                }

                // 윷판을 출력합니다.
                showBoard();
            } while(done);


        }

    }


    private void showFirstBackground(String yut) {
        System.out.println(". . . . G A M E . . . . [   User's turn!          ]");
        System.out.println(". . . . . o f . . . . . [   Put any num here!     ]");
        System.out.println(". . . .  Y u t  . . . . [   Your lucky Yut is.. " + yut + "]");
    }

    //pile값은 디바이스에서 정보를 받아와 넣음. 겹쳐졌는지 아닌지만 확인하는 것.
    private void showMiddleBackground(int result, boolean pile) {

        System.out.println("\n---*   board preview *---");
        showBoard();
        System.out.println();

        if(yut[result].equals("DOE")){
            System.out.println(". . . . [ ][x][x][x] . . .  [   Result is ' DOE ' !   ]");
            System.out.println(". . . . [ ][x][x][x] . . .  [   You can go one step   ]");
            if(pile) System.out.print(". . . . [ ][x][x][x] . . .  [ 0~1 Horse to move is.. ");
            else System.out.println(". . . . [ ][x][x][x] . . .  [                         ]");
        }
        else if(yut[result].equals("GAE")){
            System.out.println(". . . . [ ][ ][x][x] . . .  [   Result is ' GAE ' !   ]");
            System.out.println(". . . . [ ][ ][x][x] . . .  [   You can go two steps  ]");
            if(pile) System.out.print(". . . . [ ][ ][x][x] . . .  [ 0~1 Horse to move is.. ");
            else System.out.println(". . . . [ ][ ][x][x] . . .  [                         ]");
        }
        else if(yut[result].equals("GIRL")){
            System.out.println(". . . . [ ][ ][ ][x] . . .  [   Result is ' GIRL ' !  ]");
            System.out.println(". . . . [ ][ ][ ][x] . . .  [   You can go three steps]");
            if(pile) System.out.print(". . . . [ ][ ][ ][x] . . .  [ 0~1 Horse to move is.. ");
            else System.out.println(". . . . [ ][ ][ ][x] . . .  [                         ]");
        }
        else if(yut[result].equals("YUT")){
            System.out.println(". . . . [ ][ ][ ][ ] . . .  [   Result is ' YUT ' !   ]");
            System.out.println(". . . . [ ][ ][ ][ ] . . .  [   You can go four steps ]");
            if(pile) System.out.print(". . . . [ ][ ][ ][ ] . . .  [ 0~1 Horse to move is.. ");
            else System.out.println(". . . . [ ][ ][ ][ ] . . .  [                         ]");
        }
        else if(yut[result].equals("MOE")){
            System.out.println(". . . . [x][x][x][x] . . .  [   Result is ' MOE ' !   ]");
            System.out.println(". . . . [x][x][x][x] . . .  [   You can go one step   ]");
            if(pile) System.out.print(". . . . [x][x][x][x] . . .  [ 0~1 Horse to move is.. ");
            else System.out.println(". . . . [x][x][x][x] . . .  [                         ]");
        }

    }

    // 콘솔창에 윷놀이판 모양으로 출력합니다.
    private void showBoard() {
        int u1=device.get(0).getLocate(), u2=device.get(1).getLocate();
        int c1=device.get(2).getLocate(), c2=device.get(3).getLocate();
        int temp;
        String output="S";
        for(int i=0; i<=raceRoom; i++){
            output+="[";
            temp=(i==0)?(4):(2);
            if(u1==i) { output+="u1"; temp--;}
            if(u2==i) { output+="u2"; temp--;}
            if(c1==i) { output+="c1"; temp--;}
            if(c2==i) { output+="c2"; temp--;}

            for(;temp>0;temp--) output+="  ";
            output+="]";

            if(i==0) output+=" ";
        }
        System.out.println(output);
    }

    // Throw yut
    public int throwYut() {
        Random r = new Random();
        int result = r.nextInt(5);
        return result;
    }

    // 컴퓨터가 말을 선택하여 말의 번호를 돌려줍니다.
    public int selectComputerHorse(int result) {

        int temp1 = isCatch(com, result, 2);
        int temp2 = isCatch(com, result, 3);

        if(temp1<0) {
            //temp2를 쓰면 됨
            if (temp2 < 0) {
                if(compareLocate(com, result, 2))
                    return 2;
                else{
                    if(compareLocate(com, result, 3)){
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
    public int isCatch(String horse, int result, int index) {
        if (horse.equals(user)) {
            if (index == 0) {
                if (device.get(0).getLocate() + result == device.get(2).getLocate()) return 2;
                if (device.get(0).getLocate() + result == device.get(3).getLocate()) return 3;
            }
            else {
                if (device.get(1).getLocate() + result == device.get(2).getLocate()) return 2;
                if (device.get(1).getLocate() + result == device.get(3).getLocate()) return 3;
            }
        }
        else {
            if (index == 2) {
                if (device.get(2).getLocate() + result == device.get(0).getLocate()) return 0;
                if (device.get(2).getLocate() + result == device.get(1).getLocate()) return 1;
            }
            else {
                if (device.get(3).getLocate() + result == device.get(0).getLocate()) return 0;
                if (device.get(3).getLocate() + result == device.get(1).getLocate()) return 1;
            }
        }
        return -1;
    }

    // User끼리 혹은 Computer끼리 말의 위치를 비교함
    public boolean compareLocate(String horse, int result, int index) {
        if (horse.equals(user)) {
            if (index == 0)
                return device.get(0).getLocate() + result == device.get(1).getLocate();
            else
                return device.get(1).getLocate() + result == device.get(0).getLocate();
        }
        else {
            if (index == 2)
                return device.get(2).getLocate() + result == device.get(3).getLocate();
            else
                return device.get(3).getLocate() + result == device.get(2).getLocate();
        }
    }

    // user의 index를 검사하는 함수
    public int checkIndex() {
        int index;

        Scanner in = new Scanner(System.in);
        // 엎은 경우 index를 2값으로 반환합니다.
        if(device.get(0).getCarryOn())
            index = 2;
        else{
            // 사용자에게서 index값을 입력받는데 말번호가 아닐시 말번호를 입력하라는 문구를 추가합시다.ㅏ
            do{
                index = in.nextInt();
            }while(index!=0 && index!=1);
        }

        return index;
    }

    // 말을 먹거나 엎었을 경우를 처리하고 반복해야할지를 정해줍니다.
    public boolean checkHorese(String horse, int result, int index) {

        int temp = isCatch(horse, result + 1, index);
        if (temp >= 0) {
            if (device.get(temp).getCarryOn())
            {
                if(horse.equals(user)){
                    device.get(2).setLocate(0);
                    device.get(3).setLocate(0);
                    device.get(2).setCarryOn(false);
                    device.get(3).setCarryOn(false);
                }
                else{

                    device.get(0).setLocate(0);
                    device.get(1).setLocate(0);
                    device.get(0).setCarryOn(false);
                    device.get(1).setCarryOn(false);
                }
            }
            else device.get(temp).setLocate(0);
            return true;
        }

        else if (compareLocate(horse, result + 1, index)){
            if(horse.equals(user)) {
                device.get(0).setCarryOn(true);
                device.get(1).setCarryOn(true);
            }
            else {
                device.get(2).setCarryOn(true);
                device.get(3).setCarryOn(true);
            }
            return true;
        }

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


