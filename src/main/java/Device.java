
//디바이스 클래스에서 구현할 메소드
// -위치 출력, 위치 변경

public class Device {

    private int index;
    private String type;

    int locate=0;
    int number;

    public Device(){
        index = 0;
        type = "__";
    }

    void move(int result) {

        System.out.println("moved like "+result);
    }

    public int getIndex() {
        return locate;
    }

    public String getType() {
        return type;
    }


    void input(int yut){
        yut++;
        locate+=yut;
        move(yut);
    }


}
