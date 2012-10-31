package com.TableFlip.SpaceTrader.DataStructure.SparseArray;

import java.util.ArrayList;
import java.util.List;
/**
 * Created with IntelliJ IDEA.
 * User: Venea
 * Date: 10/30/12
 * Time: 4:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class SparseArray<T> implements ISparseArray2D<T>{

    private ColumnList<T> CList;
    private RowList<T> RList;
    private int rows, columns;

    /**
     * The constructor assigns the number of rows and columns, and
     * instantiates the RList and CList.
     *
     * @param rows The number of rows the Sparse Array is created with
     * @param columns The number of columns the Sparse Array is created with
     */
    public SparseArray(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        RList = new RowList<T>();
        CList = new ColumnList<T>();
    }
    /**
     * findNodeAt will only be called when both RList at index r and CList at index c
     * exist.  It returns the node at the intersection.
     *
     * @param r The index of the row list that we're looking for
     * @param c The index of the column list that we're looking for
     * @return The node at the intersection of the rowlist at the r index and the
     * column list at the c index.
     */
    public Node<T> findNodeAt(int r, int c){
        if (findRowNodeAt(r)==null){
            return null;}
        else {
            RowNode<T> rn = findRowNodeAt(r);

            Node<T> myNode = rn.getAcrossNode();
            int myNodeColIndex = myNode.getCol();
            while (myNodeColIndex<c){
                myNode = myNode.getXNext();
                myNodeColIndex = myNode.getCol();
            }
            return myNode;
        }
    }

    /**
     * findRowNodeAt finds the row node at the index given
     * @param r the index of the row node to find
     * @return The row node at index r.  Returns null if the RowList is empty
     * and if it is not in the list.
     */
    public RowNode<T> findRowNodeAt(int r){
        if (RList.getHead()==null)
            return null;
        else {
            RowNode<T> rn = RList.getHead();
            int ri = rn.getIndex();

            if (ri==r)
                return rn;
            else {
                while (ri<r && rn.getNext()!=null){
                    rn=rn.getNext();
                    ri = rn.getIndex();
                }

                if (ri==r)
                    return rn;
                else
                    return null;
            }
        }
    }

    /**
     * findColNodeAt finds the column node at the index given
     * @param c the index of the column node to find
     * @return The column node at index c.  Returns null if the ColList is empty
     * and if it is not in the list.
     */
    public ColNode<T> findColNodeAt(int c){
        if (CList.getHead()==null)
            return null;
        else {
            ColNode<T> cn = CList.getHead();
            int ci = cn.getIndex();

            if (ci==c)
                return cn;
            else {
                while (ci<c && cn.getNext()!=null){
                    cn=cn.getNext();
                    ci = cn.getIndex();
                }

                if (ci==c)
                    return cn;
                else
                    return null;
            }
        }
    }


    /**
     * Finds and returns the node found at the coordinates within the 2D array.
     * If the row and col node exist, it finds that node.
     * If either or both do not exist, it creates a node there using the add method
     * from the CList and RList classes. (which handles pointers, etc)
     * @param row The row index node that needs to be created
     * @param col The column index node that needs to be created
     * @return nothing, just exits the method when the appropriate nodes have
     * been created
     */
    public void createIndexes(int row, int col){
        if (RList.isInList(row)&&CList.isInList(col)) {
            return;
        }

        else if (CList.isInList(col)){//the colnode is there
            RList.add(row);
            return;
        }
        else if (RList.isInList(row)){//the rownode is there
            //stuff for if the col node is there but the row node isn't
            CList.add(col);
            return;
        }
        else{//neither node is in the lists.
            CList.add(col);
            RList.add(row);

            return;
        }
    }

    /**
     * Grabs the node above the row index so you can add a node at the row index
     * and hook it up to the previous one.
     * @param row the index
     * @param cn the ColNode to use to search down the array in that column.
     * @return the Node before the one we're wanting to add. If there is no
     * previous, return null.
     */
    public Node<T> getYPrevNode(int row, ColNode<T> cn){
        Node<T> countNode = cn.getDownNode();
        int countNodeR = countNode.getRow();

        if (countNodeR<row && countNode.getYNext()==null)
            return countNode;

        else if (countNodeR<row){
            while (countNodeR<row&&countNode.getYNext()!=null){
                countNode = countNode.getYNext();
                countNodeR = countNode.getRow();
            }

            if (countNodeR>row)
                countNode = countNode.getXPrev();
            return countNode;
        }

        else
            return null;//returns null if there is no xprev node
    }

    /**
     * Grabs the node previous the column index so you can add a node at the column
     * index and hook it up to the previous one.
     * @param col the index
     * @param rn the RowNode to use to search across the array in that row.
     * @return the Node before the one we're wanting to add. If there is no
     * previous, return null.
     */
    public Node<T> getXPrevNode(int col, RowNode<T> rn){
        Node<T> countNode = rn.getAcrossNode();
        int countNodeR = countNode.getRow();
        int countNodeC = countNode.getCol();

        if (countNodeC<col && countNode.getXNext()==null)
            return countNode;

        else if (countNodeC<col){
            while (countNodeR<col&&countNode.getXNext()!=null){
                countNode = countNode.getXNext();
                countNodeR = countNode.getRow();
            }

            if (countNodeR>col)
                countNode = countNode.getXPrev();
            return countNode;
        }

        else
            return null;//returns null if there is no xprev node
    }


    /**
     * adds value to the given row and column slot.  If nothing is
     * there already, then add the entry, otherwise overwrite the
     * existing value with value
     * Note: This seems really excessively long. Oops. But it works.
     *
     * @param row the row of the entry
     * @param col the column of the entry
     * @param value the value to add
     * @throws ArrayIndexOutOfBoundsException throws an exception is row or column is outside valid range
     */
    public void putAt(int row, int col, T value)
            throws ArrayIndexOutOfBoundsException {

        if (row>rows || col>columns || row<0 || col<0)
            throw new ArrayIndexOutOfBoundsException();


        createIndexes(row, col);
        RowNode<T> rn = findRowNodeAt(row);
        ColNode<T> cn = findColNodeAt(col);

        if (rn.getAcrossNode()==null && cn.getDownNode()==null) {
            Node<T> myNode = new Node<T>(null, null, null, null, value, row, col);
            rn.setAcrossNode(myNode);
            cn.setDownNode(myNode);
        }
        else if(rn.getAcrossNode()==null) {

            Node<T> YP = getYPrevNode(row, cn);
            if (YP==null){
                Node<T> YN = cn.getDownNode();
                Node<T> myNode = new Node<T>(null, YN, null, null, value,
                        row, col);
                YN.setYPrev(myNode);
                rn.setAcrossNode(myNode);
                cn.setDownNode(myNode);

            }
            else {
                Node<T> myNode = new Node<T>(YP, YP.getYNext(), null, null,
                        value, row, col);
                if (YP.getYNext()!=null)
                    YP.getYNext().setYPrev(myNode);
                YP.setYNext(myNode);
                rn.setAcrossNode(myNode);

            }
        }
        else if (cn.getDownNode()==null){
            Node<T> XP = getXPrevNode(col, rn);
            if (XP==null){
                Node<T> XN = rn.getAcrossNode();
                Node<T> myNode = new Node<T>(null, null, null, XN,
                        value, row, col);
                XN.setXPrev(myNode);
                cn.setDownNode(myNode);
                rn.setAcrossNode(myNode);

            }
            else {
                Node<T> myNode = new Node<T>(null, null, XP, XP.getXNext(),
                        value, row, col);
                if (XP.getXNext()!=null)
                    XP.getXNext().setXPrev(myNode);//doesn't work if getXNext=null
                XP.setXNext(myNode);
                cn.setDownNode(myNode);

            }
        }
        else {
            Node<T> YP = getYPrevNode(row, cn);
            Node<T> XP = getXPrevNode(col, rn);
            if (YP==null && XP==null){
                Node<T> YN = cn.getDownNode();
                Node<T> XN = rn.getAcrossNode();
                Node<T> myNode = new Node<T>(null, YN, null, XN, value,
                        row, col);
                YN.setYPrev(myNode);
                XN.setXPrev(myNode);
                rn.setAcrossNode(myNode);
                cn.setDownNode(myNode);
            }
            else if (YP==null){
                Node<T> YN = cn.getDownNode();
                Node<T> myNode = new Node<T>(null, YN, XP, XP.getXNext(), value,
                        row, col);
                YN.setYPrev(myNode);
                if (XP.getXNext()!=null)
                    XP.getXNext().setXPrev(myNode);//doesn't work if getXNext = null
                XP.setXNext(myNode);
                rn.setAcrossNode(myNode);
                cn.setDownNode(myNode);

            }
            else if (XP==null){
                Node<T> XN = rn.getAcrossNode();
                Node<T> myNode = new Node<T>(YP, YP.getYNext(), null, XN,
                        value, row, col);
                XN.setXPrev(myNode);
                if (YP.getYNext()!=null)
                    YP.getYNext().setYPrev(myNode);
                YP.setYNext(myNode);
                cn.setDownNode(myNode);
                rn.setAcrossNode(myNode);

            }
            else{
                Node<T> myNode = new Node<T>(YP, YP.getYNext(), XP, XP.getXNext(),
                        value, row, col);
                if (YP.getYNext()!=null)
                    YP.getYNext().setYPrev(myNode);
                YP.setYNext(myNode);
                if (XP.getXNext()!=null)
                    XP.getXNext().setXPrev(myNode);//doesn't work if getXNext = null
                XP.setXNext(myNode);
            }

        }
    }


    /**
     * Remove the array item at row, col.  If there is nothing in the array at
     * those coordinates, return null, otherwise return the value at that location.
     *
     * @param row the row of the entry to remove
     * @param col the col of the entry to remove
     * @return null if nothing at that entry, otherwise the value of the removed entry
     * @throws ArrayIndexOutOfBoundsException if row or col is invalid
     */
    public T removeAt(int row, int col) throws ArrayIndexOutOfBoundsException {
        if (row>rows || col>columns || row<0 || col<0)
            throw new ArrayIndexOutOfBoundsException();

        RowNode<T> rn = findRowNodeAt(row);
        ColNode<T> cn = findColNodeAt(col);

        Node<T> removeNode = findNodeAt(row, col);
        if (removeNode==null)
            return null;

        T data = removeNode.getData();
        if (removeNode==rn.getAcrossNode()){// if first in row
            if (removeNode.getXNext()==null)
                rn.setAcrossNode(null);
            else
                rn.setAcrossNode(removeNode.getXNext());
        }
        if (removeNode==cn.getDownNode()) {
            if (removeNode.getYNext()==null)
                cn.setDownNode(null);
            else
                cn.setDownNode(removeNode.getYNext());
        }
        if (removeNode.getXNext()==null && removeNode.getXPrev()!=null){
            removeNode.getXPrev().setXNext(null);
        }
        if (removeNode.getYNext()==null && removeNode.getYPrev()!=null){
            removeNode.getYPrev().setYNext(null);
        }
        if (removeNode.getXPrev()!=null && removeNode.getXNext()!=null){
            removeNode.getXPrev().setXNext(removeNode.getXNext());
            removeNode.getXNext().setXPrev(removeNode.getXPrev());
        }
        if (removeNode.getYPrev()!=null && removeNode.getYNext()!=null){
            removeNode.getYPrev().setXNext(removeNode.getYNext());
            removeNode.getYNext().setXPrev(removeNode.getYPrev());
        }

        return data;
    }


    /**
     * Returns the value at the given row and column.  If nothing is in array at
     * that coordinate, then return null.  If row and column are out of valid range, then
     * throw exception.
     *
     * @param row the row of the entry to find
     * @param col the column of the entry to find
     * @return the value at row, col if there, otherwise null
     * @throws ArrayIndexOutOfBoundsException if row or col is invalid
     */
    public T getAt(int row, int col) throws ArrayIndexOutOfBoundsException {
        if (row>rows || col>columns || row<0 || col<0)
            throw new ArrayIndexOutOfBoundsException();

        if (RList.getHead()==null && CList.getHead()==null)
            return null;

        if (RList.isInList(row) && CList.isInList(col)){
            Node<T> n = findNodeAt(row, col);
            return n.getData();
        }
        else
            return null;
    }


    /**
     * Returns a list of values stored in that row.  Return only the values for entries,
     * not nulls.  You do not need to preserve the column locations, just pack the values
     * into a list.  The columns however should be in the order they occur. Thus if I call getRowFor(3), and row three has data in columns 4, 100,
     * and 5000 of "A", "B" and "C", then I would return a simple list containing ("A","B","C");
     *
     * @param row the row to retrieve
     * @return a List of all the existing row entries
     * @throws ArrayIndexOutOfBoundsException if row is invalid
     */
    public List<T> getRowFor(int row) throws ArrayIndexOutOfBoundsException {
        if (row>rows || row<0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        List<T> RowList = new ArrayList<T>();

        if (RList.getHead()==null)
            return RowList;

        else {
            RowNode<T> rn = findRowNodeAt(row);
            if (rn==null)
                return RowList;

            Node<T> curr = rn.getAcrossNode();

            while(curr!=null){
                RowList.add(curr.getData());
                curr=curr.getXNext();
                System.out.println(RowList);
            }

            return (List<T>) RowList;
        }
    }


    /**
     * returns a list of values stored in that column.  Return only the values for entries,
     * not nulls.  You do not need to preserve the row locations, just pack the values
     * into a list.  They should be in the order they occur. Thus if I call getColumnFor(3), and column three has data in rows 4, 100,
     * and 5000 of "A", "B" and "C", then I would return a simple list containing ("A","B","C");
     *
     * @param col the column to retrieve
     * @return a list of all the existing column entries
     * @throws ArrayIndexOutOfBoundsException if col is invalid
     */
    public List<T> getColumnFor(int col) throws ArrayIndexOutOfBoundsException {
        if (col>columns || col<0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        List<T> ColList = new ArrayList<T>();

        if (CList.getHead()==null)
            return ColList;

        else {
            ColNode<T> cn = findColNodeAt(col);
            if (cn==null)
                return ColList;

            Node<T> curr = cn.getDownNode();

            while(curr!=null){
                ColList.add(curr.getData());
                curr=curr.getYNext();
                System.out.println(ColList);
            }

            return (List<T>) ColList;
        }
    }
}

