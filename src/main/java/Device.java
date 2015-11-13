
public class Device {

    private int index;
    private String type;
    private int count = 0;

    public Device(){
        index = 0;
        type = "__";
    }

    public Device(int num){
        index = 0;
        count = num;
    }

    public void move(int result) {
        index += result;
    }

    public int getIndex() {
        return index;
    }

    public String getType() {
        return type;
    }

}
