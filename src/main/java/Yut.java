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
    // done�� start method ������ ���� ������
    // selectComputerHorse method���� ���� �԰ų� ������ �ѹ��� ���� ������ �Ǳ� �����Դϴ�.
    private boolean done; // �������� �ݺ��� üũ�Դϴ�. false�Ͻ� �ݺ����� �ʰ� true�� ��� ����ؼ� �ݺ��մϴ�.

    // Initialize Yut Matrix
    public Yut() {
        // ������ ������ ArrayList�� ���� / ��ǻ�� ������ ����ֽ��ϴ�.
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

        // ��Ű �ѹ� ��� ��Ű ���� �����ֵ��� �߽��ϴ�.
        showFirstBackground(yut[new Random().nextInt(5)]);

        // ����� ���ʿ� ��ǻ�� ���ʰ� ������ ���鼭 ����˴ϴ�.
        ex: while(true){
            // ���� ������ �� ����� �Դϴ�.
            int result;

            do {
                // �÷��̾��� �����Դϴ�.
                done = false; // �ݺ� �� done�� �ʱ�ȭ�մϴ�.

                // ���� ���� ����� �����մϴ�.
                result = throwYut();

                // �����ϳ��� ������ Ȯ���ϴ°� true�� pile�� false�� �־� ��¹� ����
                showMiddleBackground(result, !device.get(0).getCarryOn() || !device.get(1).getCarryOn());

                //���� ����
                int a = checkIndex();
                if(a==2) index=0;
                else index = a;
                System.out.println();

                // �ش� ���� ���� ���� ���� ����� �Է��մϴ�. ���̳� ���Ͻ� ����ؼ� �ݺ��մϴ�.
                // ���� �԰ų� ���� ��� ������ ��ġ�� �����Ű�� �ݺ��� �Ͽ��� �Ͽ� üũ�մϴ�.
                // ��������� ��ǻ������ Ȯ���ϵ��� �Ķ���͸� �ֽ��ϴ�. //�԰ų� ���� ��� �� �ѹ� �� �����ٰ� ����.
                done = checkHorese(user, result, index); //���(������..),����������,������ ��

                if(device.get(index).getCarryOn()) {
                    if(a==2){//a�� 2�� �����ִٴ� �ǹ�
                        device.get(0).move(result);
                        device.get(1).move(result);
                    }
                    else if(index==0) {//���� ����
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

                // ������ ����մϴ�.
                showBoard();

                // ���� ������ �� ���ӿ��� �̱��� üũ �Ͽ� �̱�ٸ� ������ �����ϴ�.
                if (device.get(0).getLocate() > raceRoom && device.get(1).getLocate() > raceRoom) {
                    System.out.println("user win!\n");
                    break ex;
                }
            } while(done);

            System.out.println("\n>> Computer Phase   *---------------------------------------------------------------------------------------------------------");
            do {
                // ��ǻ���� �����Դϴ�.
                done = false; // �ݺ� �� done�� �ʱ�ȭ�մϴ�.

                // ���� ���� ��� ���� �����մϴ�.
                result = throwYut();

                // ��ǻ�ʹ� selectComputerHorse method�� ���� ������ ���� �����մϴ�.
                index = selectComputerHorse(result);

                // �ش� ���� ���� ���� ���� ����� �Է��մϴ�. ���̳� ���Ͻ� ����ؼ� �ݺ��մϴ�.
                // ���� �԰ų� ���� ��� ������ ��ġ�� �����Ű�� �ݺ��� �Ͽ��� �Ͽ� üũ�մϴ�.
                // ��������� ��ǻ������ Ȯ���ϵ��� �Ķ���͸� �ֽ��ϴ�.
                done = checkHorese(com, result, index);
                int a = 0;

                if(device.get(2).getCarryOn()) a=2;
                if(device.get(index).getCarryOn()) {
                    if(a==2){//a�� 2�� �����ִٴ� �ǹ�
                        device.get(2).move(result);
                        device.get(3).move(result);
                    }
                    else if(index==2) {//���� ����
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

                // ������ ����մϴ�.
                showBoard();

                // ���� ������ �� ���ӿ��� �̱��� üũ �� �̱�ٸ� ������ �����ϴ�.
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

    //pile���� ����̽����� ������ �޾ƿ� ����. ���������� �ƴ����� Ȯ���ϴ� ��.
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

    // �ܼ�â�� �������� ������� ����մϴ�.
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
        // ������ Ȯ�� 6:4:4:1:1

// �� 0,1,3,4,5 (6/16)
        if(result <= 5)
            return 0;

// �� 6,7,8,9 (4/16)
        else if (result <= 9)
            return 1;

// �� 10,11,12,13 (4/16)
        else if (result <= 13)
            return 2;

// �� 14 (1/16)
        else if (result <= 14)
            return 3;

// �� 15 (1/16)
        else
            return 4;
    }

    // ��ǻ�Ͱ� ���� �����Ͽ� ���� ��ȣ�� �����ݴϴ�.
    public int selectComputerHorse(int result) {
        if(device.get(2).getLocate()>raceRoom) return 3;
        else if(device.get(3).getLocate()>raceRoom) return 2;

        int temp1 = isCatch(result, 2);
        int temp2 = isCatch(result, 3);

        if(temp1 < 0) {
            //temp2�� ���� ��
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
            //3���� ������ ������
            return 2;
        }
    }

    // ��븻�� �������ִ��� Ȯ���ϰ� ������ �ִ� ���� index�� return����
    public int isCatch(int result, int index) {
        // ���� ������� ������� ��� ������� ����
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

    // User���� Ȥ�� Computer���� ���� ��ġ�� ����
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

// user�� index�� �˻��ϴ� �Լ�
    public int checkIndex() {
        String input;
        int index;

        Scanner in = new Scanner(System.in);
        // ���� ��� index�� 2������ ��ȯ�մϴ�.

        if(device.get(0).getCarryOn()){
            index = 2;
            askToKeepGoing();
        }
        else{
            // ����ڿ��Լ� index���� �Է¹޴µ� ����ȣ�� �ƴҽ� ����ȣ�� �Է��϶�� ������ �߰��սô�.��
            do{
                index = 99;
                input = in.nextLine();

                if(input.length() != 1)
                    System.out.println("Wrong Input!");
                else if (input.charAt(0) == '0' || input.charAt(0) == '1') {
                    index = Integer.parseInt(input);
                    // ������ ���� �������� ���
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

    // ���� �԰ų� ������ ��츦 ó���ϰ� �ݺ��ؾ������� �����ݴϴ�.
    public boolean checkHorese(String horse, int result, int index) {
        int temp = isCatch(result, index);

        // ���� �Դ� ���
        if (temp >= 0) {
            // ���� ���� ������ ���� ���
            if (device.get(temp).getCarryOn())
            {
                // �Դ� ���� ����� ���� ��� ��ǻ�� ������ �ʱ�ȭ
                if(horse.equals(user)){
                    device.get(2).setLocate(0);
                    device.get(3).setLocate(0);
                    device.get(2).setCarryOn(false);
                    device.get(3).setCarryOn(false);
                }
                // �Դ� ���� ��ǻ�� ���� ��� ����� ������ �ʱ�ȭ
                else{
                    device.get(0).setLocate(0);
                    device.get(1).setLocate(0);
                    device.get(0).setCarryOn(false);
                    device.get(1).setCarryOn(false);
                }
            }
            // ���� ���� �Ϲ����� ��� �� ���� �ʱ�ȭ
            else device.get(temp).setLocate(0);
            return true;
        }
        // ���� ���� ���
        else if (compareLocate(result, index)){
            // �� ���� setCarryOn�� true
            device.get(index).setCarryOn(true);
            return true;
        }

        // ���̳� ���� ���
        else if (result == 3 || result == 4)
            return true;
        return false;
    }

    // �ܼ�â�� �����ݴϴ�.
    public void clean() {
        for (int i=0; i<30; i++)
            System.out.println();
    }
}