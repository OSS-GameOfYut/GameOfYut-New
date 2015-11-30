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
    private final String blank = "_";
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

        int userPreIndex=0, comPreIndex=0, index;
        Scanner in = new Scanner(System.in);
        clean();
        System.out.println("Play Game of Yut !!");

        // ������ _ ���� �ʱ�ȭ�մϴ�.
        // ������ ������ �ٽ� �����ϱ� ���ؼ� start method ���� �ʱ�ȭ�մϴ�.
        for(int i=1; i<=16; i++)
            board[i] = blank;

        //���� ������ ���� 0 �̻��� ���ڸ� �����ϰ� ��������!
        System.out.println("pick num>0 and put to throw Yut for you!");

        /*
            ���� ���� �� �� ���� ���ο� ���ؼ� �ڵ尡 �ʹ� ����Ǿ �ϴ� ���ܽ��׽��ϴ�.
            �Ŀ� �Ͽ� ���ؼ� ����� �ҽ� �ڵ忡�� �������ֽø� �����ϰڽ��ϴ�.
            ����� ���ʿ� ��ǻ�� ���ʰ� ������ ���鼭 ����ǰ� �ֽ��ϴ�.
         */
        while(in.nextInt()!=0){
            // 0 �Է½� �������� , 0�� �ƴҶ��� ������ ���������� ������ �����մϴ�.

            // ���� ������ �� ����� �Դϴ�.
            int result;

            System.out.println("\nPlayer Phase");
            do {
                // �÷��̾��� �����Դϴ�.��
                done = false; // �ݺ� �� done�� �ʱ�ȭ�մϴ�.
                // ���� ���� ����� �����մϴ�.
                result = throwYut();
                // �÷��̾�� � ���� �������� ���� ���ϴ� ���� �����̰� �մϴ�.
                do {
                    System.out.print("Select Horse (0 ~ " + (numOfDevice - 1) + ") : ");
                    index = in.nextInt();   // ����ȣ�� �Է� �޽��ϴ�.
                    // �������� ���� ��� ���� �������� ���ϰ� �մϴ�.
                    if (device.get(index).getType().equals(blank))
                        System.out.println("This horse can't select");
                } while(device.get(index).getType().equals(blank)); // ������ ���� �ƴҶ����� �ݺ��մϴ�.

                // ���õ� ���� ArrayList���� �����ϴ�.
                // ������ ������ 0 ~ ���� ������ŭ�� ��ȣ���� ArrayList�� �ԷµǾ��ֽ��ϴ�.
                Device d = device.get(index);

                // �ش� ���� ���� ���� ���� ����� �Է��մϴ�. ���̳� ���Ͻ� ����ؼ� �ݺ��մϴ�.
                done = d.input(result);

                // ���� �԰ų� ���� ��� ������ ��ġ�� �����Ű�� �ݺ��� �Ͽ��� �Ͽ� üũ�մϴ�.
                // ��������� ��ǻ������ Ȯ���ϵ��� �Ķ���͸� �ֽ��ϴ�. //�԰ų� ���� ��� �� �ѹ� �� �����ٰ� ����.
                done = checkHorese(user);

                // ���� ������ �� ���ӿ��� �̱��� üũ �Ͽ� �̱�ٸ� ������ �����ϴ�.
                if (d.getLocate() > raceRoom) {
                    System.out.println("user win!\n");
                    break;
                }

                // ������ ���� �̵��ߴ� ���� ��ġ�� �ʱ�ȭ�մϴ�.
                board[userPreIndex] = "_";
                // ���� ���� ��ġ�� ���� �̵��� ��ġ�� �ֽ��ϴ�.
                userPreIndex = d.getLocate();

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

                // ���õ� ���� ArrayList���� �����ϴ�.
                // ��ǻ���� ������ ���� ���� ������ŭ�� �����־�� �մϴ�.
                Device d = device.get(index + numOfDevice);

                // �ش� ���� ���� ���� ���� ����� �Է��մϴ�. ���̳� ���Ͻ� ����ؼ� �ݺ��մϴ�.
                done = d.input(result);
                // ���� �԰ų� ���� ��� ������ ��ġ�� �����Ű�� �ݺ��� �Ͽ��� �Ͽ� üũ�մϴ�.
                // ��������� ��ǻ������ Ȯ���ϵ��� �Ķ���͸� �ֽ��ϴ�.
                done = checkHorese(com);

                // ���� ������ �� ���ӿ��� �̱��� üũ �� �̱�ٸ� ������ �����ϴ�.
                if (d.getLocate() > raceRoom) {
                    System.out.println("com win!\n");
                    break;
                }

                // ��ǻ�Ͱ� ���� �̵��ߴ� ���� ��ġ�� �ʱ�ȭ�մϴ�.
                board[comPreIndex] = "_";
                // ���� ���� ��ġ�� ���� �̵��� ���� ��ġ�� �ֽ��ϴ�.
                comPreIndex = d.getLocate();

                // ������ ����մϴ�.
                showBoard();
            } while(done);
            //���� ������ ���� 0 �̻��� ���ڸ� �����ϰ� ��������!
            System.out.println("pick num>0 and put to throw Yut for you!");
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

    // ���� ��½� �ʿ��� �޼ҵ� �̹Ƿ� ������ ������ �߻� �� �� �ֽ��ϴ�.
    // �ٸ� �ٸ� ������ �е��� ���� �� �� �ֵ��� �ּ��� �޾� �ֽø� �����ϰڽ��ϴ�.
    private void reversePrint(int n){
        if(n<=4) return;
        System.out.print(board[n]);
        reversePrint(n - 1);
    }

    // Throw yut
    public int throwYut() {
        Random r = new Random();
        System.out.println("Throws Yut!");
        int result = r.nextInt(5);
        System.out.println("Resulit is " + yut[result]);
        return result;
    }

    // ��ǻ�Ͱ� ���� �����Ͽ� ���� ��ȣ�� �����ݴϴ�.
    public int selectComputerHorse(int move) {

        int locateNum = 0;
        // ��ǻ���� ���鸸 üũ�մϴ�.
        for(int i=0+numOfDevice; i<device.size(); i++) {
            Device d = device.get(i);
            // ������� ���� ���� �ִ��� üũ
            locateNum += d.getLocate();
        }

        // ���� �ϳ��� ������ �ȳ��� ���
        if(locateNum == 0) {
            // ù��° ���� �����Դϴ�. (�ε��� ��ȣ 0)
            return 0;
        }
        else {
            // 1���� ��븻�� ���� �� ������
            for(int i=0; i<numOfDevice; i++) {
                Device d = device.get(i);
                for(int j=0+numOfDevice; i<device.size(); i++) {
                    Device comd = device.get(j);
                    // ������� ���� ��ġ�� ��ǻ���� ���� ������ ������ ��ġ�� ���� ��
                    if(d.getLocate() == comd.getLocate() + move) {
                        // ��ǻ���� ���� ��ȣ�� ��ȯ�մϴ�.
                        // �������� �ƴϸ� ��ȯ�մϴ�.
                        if(!d.getType().equals(blank))
                            return comd.getLocate();
                    }
                }
            }
            // 2���� ���� ���� �� ������
            for(int i=0+numOfDevice; i<device.size(); i++) {
                Device d = device.get(i);
                for(int j=0+numOfDevice; j<device.size(); j++) {
                    Device d2 = device.get(j);
                    // ��ǻ���� ���� ��ġ�� ��ǻ���� ���� ������ ������ ��ġ�� ���� ��
                    if (d.getLocate() == d2.getLocate() + move) {
                        // ��ǻ���� ���� ��ȣ�� ��ȯ�մϴ�.
                        // �������� �ƴϸ� ��ȯ�մϴ�.
                        if(!d.getType().equals(blank))
                            return d2.getNumber();
                    }
                }
            }
            // �ƹ��͵� �ƴҶ� : ���� �ռ����� �����Դϴ�.
            int fast = 0;
            // ���� �տ� �ִ� ���� ã���ϴ�
            Device fastd = null;
            for(int i=0+numOfDevice; i<device.size(); i++){
                Device d = device.get(i);
                if(d.getLocate() > fast) {
                    fastd = d;
                }
            }
            // ��ǻ���� ���� ��ȣ�� ��ȯ�մϴ�.
            // �������� �ƴϸ� ��ȯ�մϴ�.
            if(!fastd.getType().equals(blank))
                return fastd.getNumber();
        }
        // ���̾��� ��� ù��° ���� �����Դϴ�.
        return 0;
    }

    // ���� �԰ų� ������ ��츦 ó���ϰ� �ݺ��ؾ������� �����ݴϴ�.
    public boolean checkHorese(String horse) {

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

    // Clean Console �ϴ� �̷������� ���°Ŷ� �̷��� �غ� �ݺ� Ƚ���� ��º��� ����
    public void clean() {
        for (int i=0; i<30; i++)
            System.out.println();
    }
}
