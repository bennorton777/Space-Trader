package com.TableFlip.SpaceTrader.Model;

/**
 * Created with IntelliJ IDEA.
 * User: ben
 * Date: 9/7/12
 * Time: 11:37 AM
 * To change this template use File | Settings | File Templates.
 */
public class Coordinates implements Comparable{
    private int _xPos;
    private int _yPos;
    public Coordinates(int x, int y){
        _xPos=x;
        _yPos=y;
    }

    public int getxPos() {
        return _xPos;
    }

    public void setxPos(int xPos) {
        _xPos = xPos;
    }

    public int getyPos() {
        return _yPos;
    }

    public void setyPos(int yPos) {
        _yPos = yPos;
    }
    public int hashCode(){
        return 1000*_xPos+_yPos;
    }
    public boolean equals(Object other){
        try {
            Coordinates otherCoord=(Coordinates) other;
            if ((otherCoord.getxPos()==_xPos)&&(otherCoord.getyPos()==_yPos)){
                return true;
            }
            return false;
        }
        catch (Exception e){
            return false;
        }
    }

    public String toString(){
        return "Planet is located at x: "+_xPos+" and y: "+_yPos;
    }

    // Returns negative if parameter is smaller, positive if larger, 0 if equal;
    public int compareTo(Object o) {
        if (!(o instanceof Coordinates)) {
            return Integer.MAX_VALUE;
        }
        else {
            Coordinates temp = (Coordinates) o;
            int yDiff = temp.getyPos() - _yPos, xDiff = temp.getxPos() - _xPos;

            if (yDiff != 0)
                return yDiff;
            else
                return xDiff;
        }
    }
}
