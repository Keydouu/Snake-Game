package back;
public class MyPoint {
    private int x;
    private int y;
    private static int maxX, maxY;
    public MyPoint() {}
    public boolean isEqual(MyPoint p){
        return ( (this.getX()==p.getX()) && (this.getY()==p.getY()) );
    }
    public String toString(){
        return "("+this.x+","+this.y+")";
    }
    public void setPoint(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void setRandomPosition(){
        this.x = (int) (Math.random() * maxX);
        this.y = (int) (Math.random() * maxY);
    }
    public void setPoint(MyPoint p){
        this.x = p.getX();
        this.y = p.getY();
    }
    public static void enterBoardDimensions(int x, int y){
        maxX = x;
        maxY = y;
    }
    public int getX() {return this.x;}
    public int getY() {return this.y;}
    public void advanceX() {
        this.x++;
        if(this.x == maxX)
            this.x = 0;
    }
    public void advanceY() {
        this.y++;
        if(this.y == maxY)
            this.y = 0;
    }
    public void retreatX() {
        if(this.x == 0)
            this.x = maxX-1;
        else
            this.x--;
    }
    public void retreatY() {
        if(this.y == 0)
            this.y = maxY-1;
        else
            this.y--;
    }
}
