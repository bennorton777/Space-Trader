package com.TableFlip.SpaceTrader.DataStructure.SparseArray;

/**
 * Created with IntelliJ IDEA.
 * User: Venea
 * Date: 10/30/12
 * Time: 4:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class RowList<T> {

    private RowNode<T> head, tail;
    /**
     * the constructor sets head and tail equal to null
     */
    public RowList(){
        head = tail = null;
    }
    /**
     * isEmpty tests if the list is empty by checking if the head node is null
     * @return true or false, depending on if the head is null or something else
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Add adds a Row Node to anywhere in the list
     * @param i The index of the node to add
     */
    public void add(int i){
        if (!isEmpty()) {
            if (i < head.getIndex()){
                head = new RowNode(null, head, null, i);
                head.getNext().setPrev(head);
            }
            else if (i > tail.getIndex()){
                tail = new RowNode(null, null, tail, i);
                tail.getPrev().setNext(tail);
            }
            else {

                RowNode<T> curr = head;
                while(curr.getNext()!=null && curr.getNext().getIndex()<i){
                    curr = curr.getNext();
                }

                RowNode<T> newNode = new RowNode(null, curr.getNext(), curr, i);
                curr.getNext().setPrev(newNode);
                curr.setNext(newNode);
            }
        }
        else
            head = tail = new RowNode(null, null, null, i);
    }

    /**
     * Checks if the row node at the given index is in the list
     * @param i The index of the node we're looking for
     * @return true or false
     */
    public boolean isInList(int i){
        RowNode<T> curr;
        for (curr = head; curr != null; curr = curr.getNext()){
            if (curr.getIndex() == i) {
                return true;
            }
        }
        return false;
    }

    /**
     * getters and setters for the head and tail
     */
    public RowNode<T> getHead(){return head;}
    public RowNode<T> getTail(){return tail;}
    public void setHead(RowNode<T> h){head = h;}
    public void setTail(RowNode<T> t){tail = t;}
}
