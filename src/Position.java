/**
 * Position
 */
public class Position {

    private int x;
    private int y;

    public Position() {
        this.x = 0;
        this.y = 0;
    }

    public Position(int pX, int pY) {
        this.x = pX;
        this.y = pY;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int pX) {
        this.x = pX;
    }

    public void setY(int pY) {
        this.y = pY;
    }

    public double getDistance(Position other) {
        double jarakX = (this.x - other.getX()) * (this.x - other.getX());
        double jarakY = (this.y - other.getY()) * (this.y - other.getY());
        double distance = java.lang.Math.sqrt(jarakX + jarakY);
        return distance;
    }

    // driver test
    // public static void main(String[] args) {
    //     Position p1 = new Position();
    //     Position p2 = new Position(10,10);
    //     System.out.println(p1.getDistance(p2));
    // }

}

