package com.TableFlip.SpaceTrader.DataStructure.SparseArray;

/**
 * Created with IntelliJ IDEA.
 * User: Venea
 * Date: 10/30/12
 * Time: 3:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class Node<T> {

    private Node<T> yPrev, yNext, xPrev, xNext;
    private T data;
    private int row, col;

    /**
     * The constructor assigns all the parameters to their respective
     * variables.
     * @param yPrev The previous node in the Y direction.
     * @param yNext The next node in the Y direction
     * @param xPrev The previous node in the X direction
     * @param xNext The next node in the X direction
     * @param data The T data that the Node holds
     * @param row The row that the node is in
     * @param col The column that the node is in
     */
    public Node (Node<T> yPrev, Node<T> yNext, Node<T> xPrev, Node<T>
            xNext, T data, int row, int col) {
        this.yPrev = yPrev;
        this.yNext = yNext;
        this.xPrev = xPrev;
        this.xNext = xNext;
        this.data = data;
        this.row = row;
        this.col = col;
    }

    /**
     * getters and setters for yPrev, yNext, xPrev, xNext, data, row, and col
     */
    public Node<T> getYPrev() {return yPrev;}
    public Node<T> getYNext() {return yNext;}
    public Node<T> getXPrev() {return xPrev;}
    public Node<T> getXNext() {return xNext;}
    public T getData() {return data;}
    public int getRow() {return row;}
    public int getCol(){return col;}
    public void setYPrev (Node<T> yp){yPrev = yp;}
    public void setYNext (Node<T> yn){yNext = yn;}
    public void setXPrev (Node<T> xp){xPrev = xp;}
    public void setXNext (Node<T> xn){xNext = xn;}
    public void setData (T d){data = d;}
    public void setRow (int r){row = r;}
    public void setCol (int c){col = c;}
}
