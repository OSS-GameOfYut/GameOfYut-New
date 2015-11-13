
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
