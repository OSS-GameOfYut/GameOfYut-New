import java.util.*;

/**
 * Yut 11/27
 */
public class Yut {


    private String[] yut = {"DOE", "GAE", "GIRL", "YUT", "MOE"};
    private final int raceRoom = 20;    // ���� ���� 20ĭ���� �����Ǿ��ִµ� 16ĭ���� �ʱ� �����Ͽ� �ϴ� �����մϴ�.
    // ���� �������� 29�� �밢�� ���°� 20������ �ٲ�ߵ˴ϴ�.
    private final int numOfDevice = 2;
    private ArrayList<Device> device =new ArrayList<Device>(numOfDevice * 2);// device;
    private final String user = "U";
    private final String com = "C";
    // done�� start method ������ ���� ������
    // selectComputerHorse method���� ���� �԰ų� ������ �ѹ��� ���� ������ �Ǳ� �����Դϴ�.
    private boolean done; // �������� �ݺ��� üũ�Դϴ�. false�Ͻ� �ݺ����� �ʰ� true�� ��� ����ؼ� �ݺ��մϴ�.

    // Initialize Yut Matrix
    public Yut() {

        // type�� type�� ���� index(��� ������)�� ���� device��ü�� ����
        int userIndex = 0;
        int comIndex = 0;

        // ������ ������ ArrayList�� ���� / ��ǻ�� ������ ���������
        // ������ ������ ��ǻ���� ������ ������ ������ŭ ��ȣ�� ������ 0������ �����ϰ� �ֽ��ϴ�.
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

        // ��Ű �ѹ� ��� ��Ű ���� �����ֵ��� �߽��ϴ�.
        showFirstBackground(yut[new Random().nextInt(5)]);


        // ����� ���ʿ� ��ǻ�� ���ʰ� ������ ���鼭 ����˴ϴ�.

        ex:       while(true){
            // ���� ������ �� ����� �Դϴ�.
            int result;

            do {
                // �÷��̾��� �����Դϴ�.
                done = false; // �ݺ� �� done�� �ʱ�ȭ�մϴ�.
                // ���� ���� ����� �����մϴ�.
                result = throwYut();

                showMiddleBackground(result, true);

                //���� ����
                int a = checkIndex();
                if(a==2) index=0;
                else index = a;


                // �ش� ���� ���� ���� ���� ����� �Է��մϴ�. ���̳� ���Ͻ� ����ؼ� �ݺ��մϴ�.
                // ���� �԰ų� ���� ��� ������ ��ġ�� �����Ű�� �ݺ��� �Ͽ��� �Ͽ� üũ�մϴ�.
                // ��������� ��ǻ������ Ȯ���ϵ��� �Ķ���͸� �ֽ��ϴ�. //�԰ų� ���� ��� �� �ѹ� �� �����ٰ� ����.
                done = checkHorese(user, result, index); //���(������..),����������,������ ��
                if(device.get(index).getCarryOn()) {
                    device.get(0).move(result);
                    device.get(1).move(result);
                }
                else
                    device.get(index).move(result);

                // ���� ������ �� ���ӿ��� �̱��� üũ �Ͽ� �̱�ٸ� ������ �����ϴ�.
                if (device.get(0).getLocate() > raceRoom && device.get(1).getLocate() > raceRoom) {
                    System.out.println("user win!\n");
                    break ex;
                }

                // ������ ����մϴ�.
                showBoard();

            } while(done);

            System.out.println("\nComputer Phase");
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
                if(device.get(index).getCarryOn()) {
                    device.get(2).move(result);
                    device.get(3).move(result);
                }
                else
                    device.get(index).move(result);

                // ���� ������ �� ���ӿ��� �̱��� üũ �� �̱�ٸ� ������ �����ϴ�.
                if (device.get(2).getLocate() > raceRoom && device.get(3).getLocate() > raceRoom) {
                    System.out.println("computer win!\n");
                    break ex;
                }

                // ������ ����մϴ�.
                showBoard();
            } while(done);


        }

    }


    private void showFirstBackground(String yut) {
        System.out.println(". . . . G A M E . . . . [   User's turn!          ]");
        System.out.println(". . . . . o f . . . . . [   Put any num here!     ]");
        System.out.println(". . . .  Y u t  . . . . [   Your lucky Yut is.. " + yut + "]");
    }

    //pile���� ����̽����� ������ �޾ƿ� ����. ���������� �ƴ����� Ȯ���ϴ� ��.
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

    // �ܼ�â�� �������� ������� ����մϴ�.
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

    // ��ǻ�Ͱ� ���� �����Ͽ� ���� ��ȣ�� �����ݴϴ�.
    public int selectComputerHorse(int result) {

        int temp1 = isCatch(com, result, 2);
        int temp2 = isCatch(com, result, 3);

        if(temp1<0) {
            //temp2�� ���� ��
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
            //3���� ������ ������
            return 2;
        }
    }

    // ��븻�� �������ִ��� Ȯ���ϰ� ������ �ִ� ���� index�� return����
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

    // User���� Ȥ�� Computer���� ���� ��ġ�� ����
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

    // user�� index�� �˻��ϴ� �Լ�
    public int checkIndex() {
        int index;

        Scanner in = new Scanner(System.in);
        // ���� ��� index�� 2������ ��ȯ�մϴ�.
        if(device.get(0).getCarryOn())
            index = 2;
        else{
            // ����ڿ��Լ� index���� �Է¹޴µ� ����ȣ�� �ƴҽ� ����ȣ�� �Է��϶�� ������ �߰��սô�.��
            do{
                index = in.nextInt();
            }while(index!=0 && index!=1);
        }

        return index;
    }

    // ���� �԰ų� ������ ��츦 ó���ϰ� �ݺ��ؾ������� �����ݴϴ�.
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
    // �ܼ�â�� �����ݴϴ�.
    public void clean() {
        for (int i=0; i<30; i++)
            System.out.println();
    }
}


