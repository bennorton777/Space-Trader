package com.TableFlip.SpaceTrader.DataStructure.SparseArray;

/**
 * Created with IntelliJ IDEA.
 * User: Venea
 * Date: 10/30/12
 * Time: 3:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class ColNode<T> {

    private Node<T> downNode;
    private ColNode<T> next, prev;
    private int index;

    /**
     * Constructor assigns all the parameters to their respective
     * variables.
     * @param downNode The Node<T> that the ColNode points to
     * @param next The ColNode following the current one
     * @param prev The ColNode previous to the current one
     * @param index The index of the ColNode
     */
    public ColNode (Node<T> downNode, ColNode<T> next, ColNode<T> prev, int index){
        this.downNode = downNode;
        this.next = next;
        this.prev = prev;
        this.index = index;
    }

    /**
     * Getters and Setters for index, prev, next, and downNode.
     */
    public int getIndex(){return index;}
    public Node<T> getDownNode() {return downNode;}
    public ColNode<T> getNext() {return next;}
    public ColNode<T> getPrev() {return prev;}
    public void setIndex(int i){index = i;}
    public void setDownNode(Node<T> d) {downNode = d;}
    public void setNext(ColNode<T> n) {next = n;}
    public void setPrev(ColNode<T> p) {prev = p;}
}
