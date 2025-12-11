package edu.grinnell.csc207.texteditor;

/**
 * A gap buffer-based implementation of a text buffer.
 */
public class GapBuffer {
    // Contains the built string
    private char[] retString;
    // The index of the text before the cursor
    private int gapIndex;
    // The index of the text after the cursor
    private int afterIndex;
    // The size occupied in the array
    private int sz;

    // The size of the array in the beginning
    private static final int ARRAY_START_SIZE = 2;

    /**
     * Constructor for GapBuffer class, initializes variables required for a
     * gapBuffer object
     */
    public GapBuffer() {
        this.retString = new char[ARRAY_START_SIZE];
        this.gapIndex = 0;
        this.afterIndex = ARRAY_START_SIZE;
        this.sz = ARRAY_START_SIZE;
    }

    /**
     * Inserts a character into the string at the location of the cursor.
     * 
     * @param ch character that is inserted
     */
    public void insert(char ch) {
        // Insert the ch first
        retString[gapIndex] = ch;
        gapIndex++;
        // Check if the retString needs to increase size
        if (gapIndex >= sz - 1 || gapIndex == afterIndex) {
            // Double the array size
            char[] newString = new char[sz * 2];

            // Put the characters before cursor into the newString
            for (int i = 0; i < gapIndex; i++) {
                newString[i] = retString[i];
            }

            // Put the characters after cursor at the back of newString
            int rightElCount = sz - afterIndex;
            int newSz = 2 * sz;
            for (int i = newSz - rightElCount; i < newSz; i++) {
                newString[i] = retString[afterIndex++];
            }

            // Set retString equal to this new larger array
            retString = newString;

            sz = newSz;
            afterIndex = sz - rightElCount;
        }
    }

    /**
     * Deletes a char to the left of the build string as long as the cursor is not
     * at the left most side
     */
    public void delete() {
        if (gapIndex > 0) {
            gapIndex--;
        }
    }

    /**
     * @return position of the cursor
     */
    public int getCursorPosition() {
        return gapIndex;
    }

    /**
     * Will move the cursor one to the left. If the cursor is at the left most
     * position do nothing
     * Decrease the afterIndex by one and move that character to the left of the
     * cursor to the new afterIndex
     */
    public void moveLeft() {
        if (gapIndex > 0) {
            retString[--afterIndex] = retString[--gapIndex];
        }
    }

    /**
     * Will move the cursor one to the right. If the cursor is at the right most
     * position do nothing
     * If there are no more elements to the right of cursor do nothing as well.
     * Move the character at afterIndex position to gapIndex, and then increment
     * gapIndex by one and increment
     * afterIndex by one
     */
    public void moveRight() {
        if (gapIndex < sz && afterIndex < sz) {
            retString[gapIndex++] = retString[afterIndex++];
        }
    }

    /**
     * Returns the size of the string
     * 
     * @return the size of the string
     */
    public int getSize() {
        return gapIndex + (sz - afterIndex);
    }

    /**
     * Returns the character at the index inputed. If it does not exist return throw
     * new IndexOutOfBoundsException();
     * 
     * @param i the index of the ch
     * @return the character at index i
     */
    public char getChar(int i) {
        // Store the size of the gap
        int gapSize = afterIndex - gapIndex;
        if (i < 0 || i >= sz - gapSize) {
            throw new IndexOutOfBoundsException();
        }
        // Make sure that there actually is elements on the right side of cursor
        // If there is no text to the right of the cursor then we do not have to
        // worry about adding the gap size to get to the second half of the string
        if (afterIndex >= sz) {
            return retString[i];
        } else if (i < gapIndex) {
            return retString[i];
        } else {
            return retString[i + gapSize];

        }
    }

    /**
     * Returns a string implementation of the array retString
     * 
     * @return the built string
     */
    public String toString() {

        // build a string from the array of characters
        String retString = "";
        for (int i = 0; i < gapIndex; i++) {
            retString = retString + this.retString[i];
        }
        for (int i = afterIndex; i < sz; i++) {
            retString = retString + this.retString[i];
        }

        return retString;
    }
}
