package edu.grinnell.csc207.texteditor;
/**
 * A naive implementation of a text buffer using a <code>String</code>.
 */
public class SimpleStringBuffer {

    //Holds the size of the text buffer
    private int sz;
    //Holds position of the text buffer
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
        String newString = retString.substring(0, pos) + ch + retString.substring(pos, sz);
        pos++;
        sz++;
        retString = newString;
    }

    public void delete() {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    public int getCursorPosition() {
        throw new UnsupportedOperationException("Unimplemented method 'getCursorPosition'");
    }

    public void moveLeft() {
        throw new UnsupportedOperationException("Unimplemented method 'moveLeft'");
    }

    public void moveRight() {
        throw new UnsupportedOperationException("Unimplemented method 'moveRight'");
    }

    public int getSize() {
        throw new UnsupportedOperationException("Unimplemented method 'getSize'");
    }

    public char getChar(int i) {
        throw new UnsupportedOperationException("Unimplemented method 'getChar'");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("Unimplemented method 'toString'");
    }
}
