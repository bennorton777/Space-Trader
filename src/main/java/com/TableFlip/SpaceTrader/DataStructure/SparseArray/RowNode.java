package com.TableFlip.SpaceTrader.DataStructure.SparseArray;

/**
 * Created with IntelliJ IDEA.
 * User: Venea
 * Date: 10/30/12
 * Time: 3:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class RowNode<T> {

    private Node<T> acrossNode;
    private RowNode<T> next, prev;
    private int index;

    /**
     * Constructor assigns all the parameters to their respective
     * variables.
     * @param downNode The Node<T> that the ColNode points to
     * @param next The ColNode following the current one
     * @param prev The ColNode previous to the current one
     * @param index The index of the ColNode
     */
    public RowNode (Node<T> acrossNode, RowNode<T> next, RowNode<T> prev, int index) {
        this.acrossNode = acrossNode;
        this.next = next;
        this.prev = prev;
        this.index = index;
    }

    /**
     * Getters and Setters for index, prev, next, and downNode.
     */
    public int getIndex(){return index;}
    public Node<T> getAcrossNode() {return acrossNode;}
    public RowNode<T> getNext() {return next;}
    public RowNode<T> getPrev() {return prev;}
    public void setIndex(int i){index = i;}
    public void setAcrossNode(Node<T> d) {acrossNode = d;}
    public void setNext(RowNode<T> n) {next = n;}
    public void setPrev(RowNode<T> p) {prev = p;}
}
