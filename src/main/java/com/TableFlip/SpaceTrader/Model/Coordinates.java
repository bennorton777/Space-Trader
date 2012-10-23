package com.TableFlip.SpaceTrader.Model;

/**
 * Information Holder Class, contains coordinates of a planet
 */
public class Coordinates implements Comparable{
    private int _xPos;
    private int _yPos;

    public Coordinates() {

    }

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

    /**
     * Equals method needed for collision detection
     * @param other
     * @return
     */
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
        return "Port is located at x: "+_xPos+" and y: "+_yPos;
    }

    /**
     * Compare to method needed for collision detection
     * @param o
     * @return
     */
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
