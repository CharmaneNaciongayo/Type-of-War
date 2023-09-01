public class Player {
    int playerID;
    int low, med, high;
    int speed;

    public Player(int id) {
        this.playerID = id;
        this.speed = 1;
        this.low = 2;
        this.med = 4;
        this.high = 6;
    }

    public void calculateSpeed(int clicks) {
        if (clicks == 0) {
            speed = 0;
        } else if(clicks ==1) {
            speed = low;
        } else if (clicks <= 4) {
            speed = med;
        } else if (clicks <= 8) {
            speed = high;
        }
        
        if (playerID == 1){
            speed = speed * -1;
        }
    }

    public int getSpeed() {
        return speed;
    }
}
