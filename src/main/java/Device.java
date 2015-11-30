// Device Version 11/20

public class Device {

    private String type;        // 누구의( ex)player or com ) device 인지 구분하기위한 타잎
    private int locate = 0;     // 초기값은 무조건 0
    private int numberOfType;   // 말의 번호

    public Device(){
        type = "";
        numberOfType = 0;
    }

    public Device(String inputType, int num){
        type = inputType;
        numberOfType = num;
    }

    // 말의 위치를 입력받으면 말의 움직임을 콘솔에 출력합니다.
    public void move(int result) {
        System.out.println("moved like " + result);
    }

    // 말의 위치를 반환합니다.
    public int getLocate() {
        return locate;
    }

    // 말의 위치를 입력합니다.
    public void setLocate(int locate) { this.locate = locate; }

    // 말의 번호를 반환합니다.
    public int getNumber() { return numberOfType; }

    public String getType() {
        return type;
    }

    public void setType(String type) { this.type = type; }

    // 말의 위치를 입력합니다.
    public boolean input(int yut){
        // 도 개 걸 윷 모 가 0~4의 인덱스 번호를 갖고 있어서 위치를 입력하기전 1을 더합니다.
        locate += yut+1;
        move(yut+1);

        // 윷 또는 모 일 경우 한번더 던질 수 있도록 true값을 반환합니다.
        if(yut == 4 || yut == 5 )
            return true;
        else
            return false;
    }

    // 객체가 말을 잡을수있으면 true 아니면 false를 return
    private boolean isCatch(Device d2) {
        return this.locate == d2.locate;
    }

}