package Floor;
import java.lang.String;
import Constants.FloorNumber;
import Constants.Direction;

public class FloorJobSimulation {

    String time;
    FloorNumber currentfloornumber;
    Direction direction;
    FloorNumber destinationfloornumber;
    
    public FloorJobSimulation(String job) {
        String inputs[] = job.split(" ");

        this.time = inputs[0];
        
        switch(Integer.parseInt(inputs[1])) {
            case 0:
                this.currentfloornumber = FloorNumber.ZERO;
            case 1:
                this.currentfloornumber = FloorNumber.ONE;
            case 2:
                this.currentfloornumber = FloorNumber.TWO;
            case 3:
                this.currentfloornumber = FloorNumber.THREE;
            case 4:
                this.currentfloornumber = FloorNumber.FOUR;
            case 5:
                this.currentfloornumber = FloorNumber.FIVE;
            case 6:
                this.currentfloornumber = FloorNumber.SIX;
            case 7:
                this.currentfloornumber = FloorNumber.SEVEN;    
        }
        if(inputs[2] == "Up"){
            this.direction = Direction.UP;
        } else {
            this.direction = Direction.DOWN;
        }
        switch(Integer.parseInt(inputs[3])) {
            case 0:
                this.destinationfloornumber = FloorNumber.ZERO;
            case 1:
                this.destinationfloornumber = FloorNumber.ONE;
            case 2:
                this.destinationfloornumber = FloorNumber.TWO;
            case 3:
                this.destinationfloornumber = FloorNumber.THREE;
            case 4:
                this.destinationfloornumber = FloorNumber.FOUR;
            case 5:
                this.destinationfloornumber = FloorNumber.FIVE;
            case 6:
                this.destinationfloornumber = FloorNumber.SIX;
            case 7:
                this.destinationfloornumber = FloorNumber.SEVEN;    
        }
    }

}