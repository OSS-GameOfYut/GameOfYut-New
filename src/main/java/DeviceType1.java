
public class DeviceType1 {

//    private String player = "o";
//    private String computer = "x";

    int locate=0;
    int number;

    void input(int yut){
        yut++;
        locate+=yut;
        move(yut);
    }

    void move(int number){
        System.out.println("moved like "+number);
    }

    int locate(){
        return locate;
    }


}
