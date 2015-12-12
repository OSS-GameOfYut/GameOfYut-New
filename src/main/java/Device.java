
// Device

public class Device {

    private int locate;
    private boolean carryOn;

    public Device(){
        locate = 0;
        carryOn = false;
    }

    // 말의 위치를 입력받으면 말의 움직임을 콘솔에 출력합니다.
    public void move(int result) {
        // 도 개 걸 윷 모 가 0~4의 인덱스 번호를 갖고 있어서 위치를 입력하기전 1을 더합니다.
        locate += (result + 1);
    }

    // 말의 위치를 반환합니다.
    public int getLocate() {
        return locate;
    }

    // 말의 번호를 반환합니다.
    public boolean getCarryOn() {
        return carryOn;
    }

    // 말의 위치를 입력합니다.
    public void setLocate(int locate) {
        this.locate = locate;
    }

    // 말의 겹침상태를 바꿔줍니다.
    public void setCarryOn(boolean carryOn) {
        this.carryOn = carryOn;
    }
}


