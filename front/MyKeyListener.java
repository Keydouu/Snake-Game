package front;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class MyKeyListener implements KeyListener {
    private static char direction='R';
    public static char getDirection() {
        return direction;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if((KeyEvent.getKeyText(e.getKeyCode()).equals("Up"))&&(direction!='D'))
            direction='U';
        else if ((KeyEvent.getKeyText(e.getKeyCode()).equals("Down"))&&(direction!='U'))
            direction='D';
        else if ((KeyEvent.getKeyText(e.getKeyCode()).equals("Left"))&&(direction!='R'))
            direction='L';
        else if ((KeyEvent.getKeyText(e.getKeyCode()).equals("Right"))&&(direction!='L'))
            direction='R';
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
