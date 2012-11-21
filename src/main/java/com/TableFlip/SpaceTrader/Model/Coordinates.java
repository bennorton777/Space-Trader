package com.TableFlip.SpaceTrader.Model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * Information Holder Class, contains coordinates of a planet
 */
public class Coordinates implements Comparable{
    private final static Logger LOGGER = Logger.getLogger(Coordinates.class .getName());
    private int _xPos;
    private int _yPos;
    public double getDistance (Coordinates other){
        return Math.sqrt(Math.pow(other.getxPos()-_xPos,2)+Math.pow(other.getyPos()-_yPos,2));
    }

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
        if (!(other instanceof Coordinates)) return false;
        if (other==this) return true;
        if (other.getClass()!=this.getClass()) return false;
        try {
            Coordinates otherCoord=(Coordinates) other;
            if ((otherCoord.getxPos()==_xPos)&&(otherCoord.getyPos()==_yPos)){
                return true;
            }
            return false;
        }
        catch (Exception e){
            LOGGER.log(Level.SEVERE, "Equality check threw a terrible error");
            return false;
        }
    }

    public String toString(){
        return ("(" + _xPos+",  "+_yPos) + ")";
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

    public JSONObject toJSON() {
        JSONObject ret = new JSONObject();

        try {
            ret.put("x", _xPos);
            ret.put("y", _yPos);
        } catch (JSONException e) {
            System.out.println("JSON creation error " + e.toString());
            LOGGER.log(Level.SEVERE, "JSON creation error "+e.toString());
        }
        return ret;
    }

    public static Coordinates hydrate(JSONObject dry) {
        try {
            return new Coordinates(dry.getInt("x"), dry.getInt("y"));
        } catch (JSONException e) {
            e.printStackTrace();
            LOGGER.log(Level.SEVERE, "Hydration failed");
        }
        return null;
    }
}
