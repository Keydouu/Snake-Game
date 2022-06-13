package back;
import java.util.ArrayList;
import front.MainFront;
public class Food extends MyPoint implements Observer {
    private static ArrayList<String> foodColors = new ArrayList<String>();
    private String color;
    private boolean exists;
    static {
        foodColors.add("red");//food
        foodColors.add("blue");//shield
        foodColors.add("yellow");//speed up
        foodColors.add("purple");//poison
        foodColors.add("cyan");//slow down
    }
    public static ArrayList<String> getFoodColors() {
        return foodColors;
    }
    public Food(String color) {
        this.color = color;
        exists = false;
        //PointFactory.getThread().addObserver(this);
        //PointFactory.putInRandomPosition(color);
    }
    public void getUpdate(Snake snake){
        if(exists){
            if(PointFactory.getPoint("0").isEqual(this)){
                System.out.println("Food: " + color + " is eaten");
                this.gotEaten(snake);
            }
        }
	}
    static public void randomSpawn(MainFront mf) {
        if (Math.random() < 0.3) {
            int index = (int) (Math.random() * foodColors.size());
            String myColor = foodColors.get(index);
            FoodFactory.getFood(myColor).dispawn(mf);
            FoodFactory.putInRandomPosition(myColor);
            if(PointFactory.isEmpty((MyPoint)FoodFactory.getFood(myColor)))
                FoodFactory.getFood(myColor).drawMe(mf);
            FoodFactory.getFood(myColor).exists = true;
        }
    }
    public void drawMe(MainFront mf){
        mf.print(this, 255, 255, 255);
        System.out.println("Drawing Black");
    }
    public void dispawn(MainFront mf){
        if(exists){
            exists = false;
            mf.print(this, 0, 0, 0);
        }
    }
    public boolean isOnMap(){
        return exists;
    }
    public void spawned(){
        exists = true;
    }
    public void gotEaten(Snake s){
        exists = false;
    }
}
