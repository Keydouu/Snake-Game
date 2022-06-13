package back;
import java.util.Map;
import java.util.HashMap;
public class PointFactory {
    private static final Map<String,MyPoint> pointsMap = new HashMap<String,MyPoint>();
    public static MyPoint getPoint(String key){
        MyPoint p = (MyPoint)pointsMap.get(key);
        if(p == null){
            p = new MyPoint();
            pointsMap.put(key, p);
            System.out.println("Creating Point : " + key );
        }
        return p;
    }
    public static boolean isEmpty(MyPoint p){//check if position where you want to put new food is empty
        for(Map.Entry<String,MyPoint> entry : pointsMap.entrySet()){
            if(entry.getValue().isEqual(p))
                    return false;
        }
        return true;
    }
}