package back;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import front.MainFront;
public class FoodFactory {
    private static final Map<String,Food> foodsMap = new HashMap<String,Food>();
    private static ArrayList<Snake> snakes = new ArrayList<Snake>();//this is to allow multiple snakes on same map
    public static Food getFood(String key){
        if(key.equals(""))
            return null;
        Food p = (Food)foodsMap.get(key);
        if(p == null){
            switch (key) {
                case "red":
                    p = new Food(key){
                        @Override
                        public void drawMe(MainFront mf){
                            mf.print(this, 255, 0, 0);
                        }
                        @Override
                        public void gotEaten(Snake s){
                            super.gotEaten(s);
                            s.eatFood();
                            s.boostScore(10);
                        }
                    };
                    break;
                case "blue":
                    p = new Food(key){
                        @Override
                        public void drawMe(MainFront mf){
                            mf.print(this, 0, 0, 255);
                        }
                        @Override
                        public void gotEaten(Snake s){
                            super.gotEaten(s);
                            s.boostScore(5);
                        }
                    };
                    break;
                case "yellow":
                    p = new Food(key){
                        @Override
                        public void drawMe(MainFront mf){
                            mf.print(this, 255, 255, 0);
                        }
                        @Override
                        public void gotEaten(Snake s){
                            super.gotEaten(s);
                            MyMainThread.goFast();
                            s.boostScore(20);
                        }
                    };
                    break;
                case "purple":
                    p = new Food(key){
                        @Override
                        public void drawMe(MainFront mf){
                            mf.print(this, 128, 0, 128);
                        }
                        @Override
                        public void gotEaten(Snake s){
                            super.gotEaten(s);
                            s.eatPoison();
                            s.boostScore(-15);
                        }
                    };
                    break;
                case "cyan":
                    p = new Food(key){
                        @Override
                        public void drawMe(MainFront mf){
                            mf.print(this, 0, 255, 255);
                        }
                        @Override
                        public void gotEaten(Snake s){
                            super.gotEaten(s);
                            MyMainThread.goSlow();
                            s.boostScore(5);
                        }
                    };
                    break;
                default:
                    p = new Food(key);
                    break;
            }
            foodsMap.put(key, p);
            p.setRandomPosition();
            for (Snake snake : snakes) {
                snake.addObserver(p);
            }
            System.out.println("Creating Food: " + key);
        }
        return p;
    }
    public static void putInRandomPosition(String key){
        Food p=getFood(key);
        while(!PointFactory.isEmpty((MyPoint)p) ){
            p.setRandomPosition();
        }
    }
    public static boolean isEmpty(String key){
        Food p=getFood(key);
        for(String k:foodsMap.keySet()){
            if(!k.equals(key)){
                if(p.isEqual(foodsMap.get(k))){
                    return false;
                }
            }
        }
        return true;
    }
    public static String isEmpty(MyPoint p){
        for(String k:foodsMap.keySet()){
            if(p.isEqual(foodsMap.get(k))){
                return k;
            }
        }
        return "";
    }
    
    public static void addSnake(Snake s){
        snakes.add(s);
    }
    public static void removeSnake(Snake s){
        snakes.remove(s);
    }
}