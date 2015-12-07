// Device Version 11/27

public class Device {

    private String type;        // ������( ex)player or com ) device ���� �����ϱ����� Ÿ��
    private int locate = 0;     // �ʱⰪ�� ������ 0
    private int numberOfType;   // ���� ��ȣ
    private boolean carryOn = false;


    public Device(String inputType, int num){
        type = inputType;
        numberOfType = num;
    }

    // ���� ��ġ�� �Է¹����� ���� �������� �ֿܼ� ����մϴ�.
    public void move(int result) {
        // �� �� �� �� �� �� 0~4�� �ε��� ��ȣ�� ���� �־ ��ġ�� �Է��ϱ��� 1�� ���մϴ�.
        locate += (result + 1);

    }

    // ���� ��ġ�� ��ȯ�մϴ�.
    public int getLocate() {
        return locate;
    }

    // ���� ��ġ�� �Է��մϴ�.
    public void setLocate(int locate) {
        this.locate = locate;
    }

    // ���� ��ħ���¸� �ٲ��ݴϴ�.
    public void setCarryOn(boolean carryOn) {
        this.carryOn = carryOn;
    }

    // ���� ��ȣ�� ��ȯ�մϴ�.
    public boolean getCarryOn() {
        return carryOn;
    }

    // ���� ��ȣ�� ��ȯ�մϴ�.
    public int getNumber() {
        return numberOfType;
    }

    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }


}