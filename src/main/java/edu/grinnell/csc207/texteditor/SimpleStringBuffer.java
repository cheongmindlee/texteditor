package edu.grinnell.csc207.texteditor;
/**
 * A naive implementation of a text buffer using a <code>String</code>.
 */
public class SimpleStringBuffer {

    //Holds the size of the text buffer
    private int sz;
    //Holds position of the cursor
    private int pos;

    //Holds the built string.
    private String retString;
    /**
     * Creates an empty SimpleStringBuffer with sz = 0
     */
    public SimpleStringBuffer(){
        this.sz = 0;
        this.pos = 0;
        this.retString = "";
    }

    /**
     * Inserts a character at the current position of the cursor, and advances the cursor one position forward
     * @param ch The character we want to insert
     */
    public void insert(char ch) {


        //Create new string with new inserted element
        String newString = retString.substring(0, pos) + ch + retString.substring(pos, sz);
        sz++;
        pos++;
        retString = newString;
    }

    /**
     * Deletes the character to the left of cursor position and moves the cursor one position backwards
     */
    public void delete() {
        if(pos != 0){
            String newString = retString.substring(0, pos-1) + retString.substring(pos, sz);
            sz--;
            pos--;
            retString = newString;
        }

    }

    /**
     * @return the position of the cursor
     */
    public int getCursorPosition() {
        return pos;
    }

    /**
     * As long as the position of the cursor isn't at the ver y front, move the cursor one to the left
     */
    public void moveLeft() {
        if(pos > 0){
            pos--;
        }
    }

    /**
     * As long as the position isn't at the end of the buffer move it one to the right
     */
    public void moveRight() {
        if(pos < sz){
            pos++;
        }
    }

    /**
     * Returns the size of the string buffer
     * @return The size of the string buffer
     */
    public int getSize() {
        return sz;
    }

    /**
     * Returns the char at the inputed index
     * @param i The index of the character you want to get 
     * @return The character at that index, Index out of bounds if it is greater
     */
    public char getChar(int i) {
        if(i > sz){
            throw new IndexOutOfBoundsException();
        } else {
            return retString.charAt(i);
        }
    }

    /**
     * Returns the built string
     */
    @Override
    public String toString() {
        return retString;
    }
}
