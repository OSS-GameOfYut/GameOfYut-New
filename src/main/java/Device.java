
// Device

public class Device {

    private int locate;
    private boolean carryOn;

    public Device(){
        locate = 0;
        carryOn = false;
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

    // ���� ��ȣ�� ��ȯ�մϴ�.
    public boolean getCarryOn() {
        return carryOn;
    }

    // ���� ��ġ�� �Է��մϴ�.
    public void setLocate(int locate) {
        this.locate = locate;
    }

    // ���� ��ħ���¸� �ٲ��ݴϴ�.
    public void setCarryOn(boolean carryOn) {
        this.carryOn = carryOn;
    }
}


