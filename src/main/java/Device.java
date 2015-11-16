
//디바이스 클래스에서 구현할 메소드
// -위치 출력, 위치 변경

public class Device {

    private int locate; // 말의 위치
    private String type; // 말의 상태

    public Device(){
        locate = 0;
        type = "__";
    }

    void showMove(int result) {
        System.out.println("moved like "+result);
    }

    public void setLocate(int num) {locate = num;}

    public int getLocate() {
        return locate;
    }

    public String getType() {
        return type;
    }


    void input(int yut){
        yut++;  // yut 0~4 의 값이므로 + 1
        locate+=yut;
        showMove(yut);
    }

}
