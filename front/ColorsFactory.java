package front;
import java.awt.Color;
import java.util.Map;
import java.util.HashMap;
public class ColorsFactory{//This is the FlyWeight pattern 2
    private static final Map<String, Color> colorsMap = new HashMap<String, Color>();
    public static Color getColor(int r, int g, int b){
        Color c = (Color)colorsMap.get(r+","+g+","+b);
        if(c == null){
            c = new Color(r, g, b);
            colorsMap.put((r+","+g+","+b), c);
        }
        return c;
    }
}