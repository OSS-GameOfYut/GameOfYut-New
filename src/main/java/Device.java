
public class Device {

    private int index;
    private String type;

    public Device(){
        index = 0;
        type = "__";
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
