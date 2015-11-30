// Device Version 11/20

public class Device {

    private String type;        // ������( ex)player or com ) device ���� �����ϱ����� Ÿ��
    private int locate = 0;     // �ʱⰪ�� ������ 0
    private int numberOfType;   // ���� ��ȣ

    public Device(){
        type = "";
        numberOfType = 0;
    }

    public Device(String inputType, int num){
        type = inputType;
        numberOfType = num;
    }

    // ���� ��ġ�� �Է¹����� ���� �������� �ֿܼ� ����մϴ�.
    public void move(int result) {
        System.out.println("moved like " + result);
    }

    // ���� ��ġ�� ��ȯ�մϴ�.
    public int getLocate() {
        return locate;
    }

    // ���� ��ġ�� �Է��մϴ�.
    public void setLocate(int locate) { this.locate = locate; }

    // ���� ��ȣ�� ��ȯ�մϴ�.
    public int getNumber() { return numberOfType; }

    public String getType() {
        return type;
    }

    public void setType(String type) { this.type = type; }

    // ���� ��ġ�� �Է��մϴ�.
    public boolean input(int yut){
        // �� �� �� �� �� �� 0~4�� �ε��� ��ȣ�� ���� �־ ��ġ�� �Է��ϱ��� 1�� ���մϴ�.
        locate += yut+1;
        move(yut+1);

        // �� �Ǵ� �� �� ��� �ѹ��� ���� �� �ֵ��� true���� ��ȯ�մϴ�.
        if(yut == 4 || yut == 5 )
            return true;
        else
            return false;
    }

    // ��ü�� ���� ������������ true �ƴϸ� false�� return
    private boolean isCatch(Device d2) {
        return this.locate == d2.locate;
    }

}