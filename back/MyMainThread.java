package back;
import front.MainFront;
public class MyMainThread extends Thread {
    private MainFront printer;
    private Snake snake;
    private static int speed=80;
    public MyMainThread(Snake snakeHead, MainFront printer) {
        this.printer = printer;
        snakeHead.setInterface(this.printer);
        this.snake = snakeHead;
        FoodFactory.addSnake(snakeHead);
    }
    public void run() {
        while (true) {
            try {
                Thread.sleep(speed);
                snake.move();
                Food.randomSpawn(this.printer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void goFast(){
        speed=30;
        System.out.println("Going fast");
    }
    public static void goSlow(){
        speed=150;
        System.out.println("Going Slow");
    }
    public static void goNormal(){
        speed=80;
    }
}
