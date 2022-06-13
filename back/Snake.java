package back;
import front.*;
import java.util.ArrayList;
public class Snake implements Observable{
    private int snakeSize, editsize, score;
    private MainFront fP;
    private ArrayList<Observer> observers;
    //private String effect=null;
    public Snake() {
        PointFactory.getPoint("0").setRandomPosition();
        this.snakeSize = 12;//initial snake size
        for(int i=1; i<this.snakeSize; i++){
            PointFactory.getPoint(String.valueOf(i)).setPoint(PointFactory.getPoint(String.valueOf(i-1)));
            PointFactory.getPoint(String.valueOf(i)).retreatX();
        }
        this.editsize=0;
        this.score=0;
        this.observers = new ArrayList<Observer>();
    }
    public void setInterface(MainFront fP){
        this.fP = fP;
        fP.print(PointFactory.getPoint("0"), 0, 100, 0);
        for(int i=1; i<this.snakeSize; i++){
            fP.print(PointFactory.getPoint(String.valueOf(i)), 50, 205, 50);
        }
    }
    public boolean move(){// getUpdate
        PointFactory.getPoint("tmp1").setPoint(PointFactory.getPoint("0"));
        switch(MyKeyListener.getDirection()){
            case 'R':
                PointFactory.getPoint("0").advanceX();
                break;
            case 'L':
                PointFactory.getPoint("0").retreatX();
                break;
            case 'U':
                PointFactory.getPoint("0").retreatY();
                break;
            case 'D':
                PointFactory.getPoint("0").advanceY();
                break;
        }
        fP.print(PointFactory.getPoint("0"), 0, 100, 0);
        fP.print(PointFactory.getPoint("tmp1"), 50, 205, 50);
        if(editsize==0){
            String s=FoodFactory.isEmpty(PointFactory.getPoint(String.valueOf(this.snakeSize-1)));
            if(!s.equals("")&&FoodFactory.getFood(s).isOnMap())
                FoodFactory.getFood(s).drawMe(fP);
            else
                fP.print(PointFactory.getPoint(String.valueOf(this.snakeSize-1)), 0, 0, 0);
                
        }
        else if(editsize>0){
            editsize--;
            snakeSize++;
            System.out.println("snakeSize: "+snakeSize);
            //PointFactory.getPoint(String.valueOf(snakeSize)).setPoint(PointFactory.getPoint("tmp1"));
        }
        else if(editsize<0){
            editsize++;
            fP.print(PointFactory.getPoint(String.valueOf(this.snakeSize-1)), 0, 0, 0);
            snakeSize--;
            fP.print(PointFactory.getPoint(String.valueOf(this.snakeSize-1)), 0, 0, 0);
        }
        boolean gameOver=false;
        for(int i=1; i<this.snakeSize; i++){
            PointFactory.getPoint("tmp2").setPoint(PointFactory.getPoint(String.valueOf(i)));
            PointFactory.getPoint(String.valueOf(i)).setPoint(PointFactory.getPoint("tmp1"));
            if(PointFactory.getPoint("0").getX()==PointFactory.getPoint(String.valueOf(i)).getX() && PointFactory.getPoint("0").getY()==PointFactory.getPoint(String.valueOf(i)).getY())
                gameOver=true;
            PointFactory.getPoint("tmp1").setPoint(PointFactory.getPoint("tmp2"));
        }
        notifyObservers();
        return gameOver;
    }
    public void addObserver(Observer obs){
        this.observers.add(obs);
    }
	public void removeObderver(Observer obs){
        this.observers.remove(obs);
    }
	public void notifyObservers(){
        for(Observer obs: this.observers){
            obs.getUpdate(this);
        }
    }
    public void eatFood(){
        this.editsize++;
    }
    public void eatPoison(){
        this.editsize--;
    }
    public void boostScore(int i){
        this.score+=i;
        this.fP.updateScore(this.score);
    }
    public MainFront getInterface(){
        return this.fP;
    }
}