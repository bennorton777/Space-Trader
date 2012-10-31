package com.TableFlip.SpaceTrader.DataStructure.SparseArray;

/**
 * Created with IntelliJ IDEA.
 * User: Venea
 * Date: 10/30/12
 * Time: 4:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class ColumnList<T> {
    private ColNode<T> head, tail;

    /**
     * the constructor sets head and tail equal to null
     */
    public ColumnList(){
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
     * Add adds a Column Node to anywhere in the list
     * @param i The index of the node to add
     */
    public void add(int i){
        if (!isEmpty()) {
            if (i < head.getIndex()){
                head = new ColNode(null, head, null, i);
                head.getNext().setPrev(head);
            }
            else if (i > tail.getIndex()){
                tail = new ColNode(null, null, tail, i);
                tail.getPrev().setNext(tail);
            }
            else {
                ColNode<T> curr = head;
                while(curr.getNext()!=null && curr.getNext().getIndex()<i){
                    curr = curr.getNext();
                }

                ColNode<T> newNode = new ColNode(null, curr.getNext(), curr, i);
                curr.getNext().setPrev(newNode);
                curr.setNext(newNode);

            }
        }
        else
            head = tail = new ColNode(null, null, null, i);
    }


    /**
     * Checks if the column node at the given index is in the list
     * @param i The index of the node we're looking for
     * @return true or false
     */
    public boolean isInList(int i){
        ColNode<T> curr;
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
    public ColNode<T> getHead(){return head;}
    public ColNode<T> getTail(){return tail;}
    public void setHead(ColNode<T> h){head = h;}
    public void setTail(ColNode<T> t){tail = t;}
}
