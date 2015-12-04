import java.util.*;

/**
 * Basic Yut 11/20
 */
public class Yut {


    private String[] yut = {"DOE", "GAE", "GIRL", "YUT", "MOE"};
    private final int raceRoom = 20;    // ���� ���� 20ĭ���� �����Ǿ��ִµ� 16ĭ���� �ʱ� �����Ͽ� �ϴ� �����մϴ�.
    // ���� �������� 29�� �밢�� ���°� 20������ �ٲ�ߵ˴ϴ�.
    private final int numOfDevice = 2;
    private ArrayList<Device> device =new ArrayList<Device>(numOfDevice * 2);// device;
    private String[] board; // ������ 1���� �迭�� �Ǿ��վ� �̰��� ������ ��ġ�� �ְ� ����մϴ�.
    private final String with = "W";
    private final String user = "U";
    private final String com = "C";
    // done�� start method ������ ���� ������
    // selectComputerHorse method���� ���� �԰ų� ������ �ѹ��� ���� ������ �Ǳ� �����Դϴ�.
    private boolean done; // �������� �ݺ��� üũ�Դϴ�. false�Ͻ� �ݺ����� �ʰ� true�� ��� ����ؼ� �ݺ��մϴ�.

    // Initialize Yut Matrix
    public Yut() {

        // ���� ��� �� �迭 0�� ��ġ�� ������ �������� ���� ��ġ�� ������� �ʽ��ϴ�.
        board = new String[raceRoom+1];

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
        Scanner in = new Scanner(System.in);
        int index = 0;
        clean();
        System.out.println("-----�ڡڡ�    Yutnori start !!  �ڡڡ�-----\n");

        // ������ _ ���� �ʱ�ȭ�մϴ�.
        // ������ ������ �ٽ� �����ϱ� ���ؼ� start method ���� �ʱ�ȭ�մϴ�.
//        for(int i=1; i<=16; i++)
//            board[i] = blank;

        //���� ������ ���� 0 �̻��� ���ڸ� �����ϰ� ��������!

        showFirstBackground();
//        System.out.println("pick num>0 and put to throw Yut for you!");

        /*
            ���� ���� �� �� ���� ���ο� ���ؼ� �ڵ尡 �ʹ� ����Ǿ �ϴ� ���ܽ��׽��ϴ�.
            �Ŀ� �Ͽ� ���ؼ� ����� �ҽ� �ڵ忡�� �������ֽø� �����ϰڽ��ϴ�.
            ����� ���ʿ� ��ǻ�� ���ʰ� ������ ���鼭 ����ǰ� �ֽ��ϴ�.
         */
        ex:       while(true){
            // 0 �Է½� �������� , 0�� �ƴҶ��� ������ ���������� ������ �����մϴ�.

            // ���� ������ �� ����� �Դϴ�.
            int result;

//            System.out.println("\nPlayer Phase");
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

//                index = checkIndex();

                // ���õ� ���� ArrayList���� �����ϴ�.
                // ������ ������ 0 ~ ���� ������ŭ�� ��ȣ���� ArrayList�� �ԷµǾ��ֽ��ϴ�.


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
            //���� ������ ���� 0 �̻��� ���ڸ� �����ϰ� ��������!
            System.out.println("pick num>0 and put to throw Yut for you!");
        }

    }


    private void showFirstBackground() {
        System.out.println(". . . . �� . �� . �� �� . . . . [   User's turn!          ]");
        System.out.println(". . . . �� . �� . . �� . . . . [   Put any num here!     ]");
        System.out.print(". . . . �� . �� . . �� . . . . [   Your lucky num is.. ");
    }


    //pile���� ����̽����� ������ �޾ƿ� ����. ���������� �ƴ����� Ȯ���ϴ� ��.
    private void showMiddleBackground(int result, boolean pile) {

        System.out.println("\n---��   board preview ��---");
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



    // reversePrint method�� �Բ� �ֿܼ� ����ϴ� ����� �����Ͽ���
    // ����� ������ �߻� �� �� ������ ������ ���� ���� �ʴ� �������� ���� ��Ź�帳�ϴ�.
    // �������ڸ� ����/��ǻ���� ���� �Է¹ް� ���忡 �Էµ� ������ ��ġ��
    // �ܼ�â�� �������� ������� ����Ѵٰ� ���ø� �˴ϴ�.
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
        /*
        // �����ǿ� �÷��̾�� ��ǻ���� ���� ��ġ�� �Է��ϴ� ����Դϴ�.
        for(int i = 1; i <= raceRoom; i++) {
            for(int j=0; j < device.size(); j++) {
                Device d = device.get(j);
                // ���� ��ġ�� ���� ������ �����ǿ� ���� �ֽ��ϴ�.
                if(i == d.getLocate())
                    board[i] = d.getType();
            }
        }

        // ���忡 �Է¹��� ���� ��ġ�� �ܼ�â�� ����ϴ� ����Դϴ�.
        for(int i=0; i<3 ; i++){
            if(i==0){
                reversePrint(10);
                System.out.println();
            }
            else if(i==1){
                int n=11;
                for(int j=0; j!=6; j+=2) {
                    System.out.println(board[n] + "  \t" + board[n - 7-j]);
                    n++;
                }
            }
            else{
                for(int j=15; j<20; j++) System.out.print(board[j]);
                System.out.println(board[0]);
            }
        }
        */
    }

    // Throw yut
    public int throwYut() {
        Random r = new Random();
//        System.out.println("Throws Yut!");
        int result = r.nextInt(5);
//        System.out.println("Resulit is " + yut[result]);
        return result;
    }

    // ��ǻ�Ͱ� ���� �����Ͽ� ���� ��ȣ�� �����ݴϴ�.
    public int selectComputerHorse(int result) {

        int temp1 = isCatch(com, result, 2);
        int temp2 = isCatch(com, result, 3);

        if(temp1<0) {
            //temp2�� ���� ��
            if (temp2 < 0) {

                //compareLocate(String horse, int result, int index)
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




        //return 2;




/*
//        int locateNum = 0;
//        // ��ǻ���� ���鸸 üũ�մϴ�.
//        for(int i=0+numOfDevice; i<device.size(); i++) {
//            Device d = device.get(i);
//            // ������� ���� ���� �ִ��� üũ
//            locateNum += d.getLocate();
//        }
//
//        // ���� �ϳ��� ������ �ȳ��� ���
//        if(locateNum == 0) {
//            // ù��° ���� �����Դϴ�. (�ε��� ��ȣ 0)
//            return 0;
//        }
//        else {
//            // 1���� ��븻�� ���� �� ������
//            for(int i=0; i<numOfDevice; i++) {
//                Device d = device.get(i);
//                for(int j=0+numOfDevice; i<device.size(); i++) {
//                    Device comd = device.get(j);
//                    // ������� ���� ��ġ�� ��ǻ���� ���� ������ ������ ��ġ�� ���� ��
//                    if(d.getLocate() == comd.getLocate() + move) {
//                        // ��ǻ���� ���� ��ȣ�� ��ȯ�մϴ�.
//                        // �������� �ƴϸ� ��ȯ�մϴ�.
//                        if(!d.getType().equals(blank))
//                            return comd.getLocate();
//                    }
//                }
//            }
//            // 2���� ���� ���� �� ������
//            for(int i=0+numOfDevice; i<device.size(); i++) {
//                Device d = device.get(i);
//                for(int j=0+numOfDevice; j<device.size(); j++) {
//                    Device d2 = device.get(j);
//                    // ��ǻ���� ���� ��ġ�� ��ǻ���� ���� ������ ������ ��ġ�� ���� ��
//                    if (d.getLocate() == d2.getLocate() + move) {
//                        // ��ǻ���� ���� ��ȣ�� ��ȯ�մϴ�.
//                        // �������� �ƴϸ� ��ȯ�մϴ�.
//                        if(!d.getType().equals(blank))
//                            return d2.getNumber();
//                    }
//                }
//            }
//            // �ƹ��͵� �ƴҶ� : ���� �ռ����� �����Դϴ�.
//            int fast = 0;
//            // ���� �տ� �ִ� ���� ã���ϴ�
//            Device fastd = null;
//            for(int i=0+numOfDevice; i<device.size(); i++){
//                Device d = device.get(i);
//                if(d.getLocate() > fast) {
//                    fastd = d;
//                }
//            }
//            // ��ǻ���� ���� ��ȣ�� ��ȯ�մϴ�.
//            // �������� �ƴϸ� ��ȯ�մϴ�.
//            if(!fastd.getType().equals(blank))
//                return fastd.getNumber();
//        }
//        // ���̾��� ��� ù��° ���� �����Դϴ�.
//        return 0;
//        */

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
        // �ε����� 2�� ���� ������
        if(device.get(0).getCarryOn()) return 2;
        else{
            do{
                index = in.nextInt();

            }while(index!=0 && index!=1);
        }

        return index;
        /*
        // �÷��̾�� � ���� �������� ���� ���ϴ� ���� �����̰� �մϴ�.
        do {
//                    System.out.print("Select Horse (0 ~ " + (numOfDevice - 1) + ") : ");
            do{
                index= in.nextInt(); //���⼭ ������ ���� ���õ�
            }
            while(index!=0 && index!=1);   // ����ȣ�� �Է� �޽��ϴ�.
            // �������� ���� ��� ���� �������� ���ϰ� �մϴ�.
            if (device.get(index).getType().equals(blank))
                System.out.println("This horse can't select");
        } while(device.get(index).getType().equals(blank)); // ������ ���� �ƴҶ����� �ݺ��մϴ�.
            */
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
/*
        for(int i=0; i<device.size(); i++) {
            Device d = device.get(i);
            for (int j = 0; j < device.size(); j++) {
                Device d2 = device.get(j);
                // ���� ������ġ�� �ִ°� �ƴϰ� ���� ���� �ƴϸ鼭
                // ���� ��ġ�� ���� �ִٸ� �԰ų� ���� ���̹Ƿ� �ݺ��� �ϵ��� true�� ��ȯ �մϴ�.
                if (d.getNumber() != d2.getNumber()
                        && d.getLocate()!=0 && d2.getLocate()!=0
                        && d.getLocate() == d2.getLocate()) {
                    // ��ǻ�� ���� ��� (��ǻ�Ͱ� �԰ų� ���� ���)
                    if (horse.equals(com)) {
                        // ��ǻ�Ͱ� ���� ���
                        // i or j �� ������� ��� ���� ���� ���̹Ƿ� ������� ���� ��ġ�� 0���� �ٲߴϴ�.
                        if (d.getType().equals(user) || d2.getType().equals(user)) {
                            if (d.getType().equals(user)) {
                                d.setLocate(0);
                            }
                            else if (d2.getType().equals(user))
                                d.setLocate(0);
                        }
                        // ��ǻ�Ͱ� ���� ���
                        // i j�� �Ѵ� ��ǻ���� ��� ���� ���� ��� �̹Ƿ�
                        if (d.getType().equals(com) && d.getType().equals(com)) {
                            // ���� ��ȣ�� ���� Ÿ���� �ٲ������ ���� ��ȣ�� ���� �������Ͽ� �ϳ��� ���� ���ƽ��ϴ�.
                            if (d.getNumber() < d2.getNumber()) {
                                d.setType(com + com);
                                d2.setType(blank);
                                d2.setLocate(0);
                            }
                            else {
                                d2.setType(com + com);
                                d.setType(blank);
                                d.setLocate(0);
                            }
                        }
                    }
                    // ����� ���� ��� (����ڰ� �԰ų� ���� ���)
                    else if (horse.equals(user)) {
                        // ����ڰ� ���� ���
                        // i or j �� ��ǻ���� ��� ���� ���� ���̹Ƿ� ��ǻ���� ���� ��ġ�� 0���� �ٲߴϴ�.
                        if (d.getType().equals(com) || d2.getType().equals(com)) {
                            if (d.getType().equals(com)) {
                                d.setLocate(0);
                            }
                            else if (d2.getType().equals(com))
                                d.setLocate(0);
                        }
                        // ����ڰ� ���� ���
                        // i j�� �Ѵ� ������� ��� ���� ���� ��� �̹Ƿ�
                        if (d.getType().equals(user) && d.getType().equals(user)) {
                            // ���� ��ȣ�� ���� Ÿ���� �ٲ������ ���� ��ȣ�� ���� �������Ͽ� �ϳ��� ���� ���ƽ��ϴ�.
                            if (d.getNumber() < d2.getNumber()) {
                                System.out.println(d.getNumber() + " " + d2.getNumber());
                                d.setType(user + user);
                                d2.setType(blank);
                                d2.setLocate(0);
                            }
                            else {
                                System.out.println(d2.getNumber() + " " + d.getNumber());
                                d2.setType(user + user);
                                d.setType(blank);
                                d.setLocate(0);
                            }
                        }
                    }
                    // �԰ų� ���� ��� �ѹ� �� �ݺ��ϵ��� true�� ��ȯ�մϴ�.
                    return true;
                }
            }
        }
        return false;
    }
*/
    }
    // Clean Console �ϴ� �̷������� ���°Ŷ� �̷��� �غ� �ݺ� Ƚ���� ��º��� ����
    public void clean() {
        for (int i=0; i<30; i++)
            System.out.println();
    }
}


