package edu.grinnell.csc207.texteditor;

import org.junit.jupiter.api.Test;

import net.jqwik.api.Property;

import static org.junit.jupiter.api.Assertions.assertEquals;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;

public class GapBufferTests {
    @Test
    public void insertInEmptyBuffer(){
        GapBuffer s = new GapBuffer();
        s.insert('H');
        assertEquals(1, s.getSize());
    }

    @Test
    public void deleteOneElement(){
        GapBuffer s = new GapBuffer();
        s.insert('H');
        s.insert('e');
        s.insert('l');
        s.insert('l');
        s.insert('o');
        assertEquals(5, s.getSize());
        assertEquals('o', s.getChar(s.getSize() - 1));
        s.delete();
        assertEquals(4, s.getSize());
        assertEquals("Hell", s.toString());
    }

    @Test
    public void deleteManyElements(){
        GapBuffer s = new GapBuffer();
        s.insert('H');
        s.insert('e');
        s.insert('l');
        s.insert('l');
        s.insert('o');
        s.insert(' ');
        s.insert('W');
        s.insert('o');
        s.insert('r');
        s.insert('l');
        s.insert('d');
        assertEquals(11, s.getSize());
        s.delete();
        s.moveLeft();
        s.moveLeft();
        s.delete();
        s.delete();
        assertEquals("Hello rl", s.toString());
        s.insert('1');
        assertEquals("Hello 1rl", s.toString());
    }

    @Test
    public void moveCursorAround(){
        GapBuffer s = new GapBuffer();
        s.insert('H');
        s.insert('e');
        assertEquals(2, s.getCursorPosition());
        s.moveRight();
        s.moveRight();
        assertEquals(2, s.getCursorPosition());
        s.moveLeft();
        s.moveLeft();
        s.moveLeft();
        s.moveLeft();
        assertEquals(0, s.getCursorPosition());
    }

    @Test
    public void moveAroundThenInsert(){
        GapBuffer s = new GapBuffer();
        s.insert('H');
        s.insert('e');
        s.insert('l');
        s.insert('l');
        s.insert('o');
        s.moveLeft();
        s.moveLeft();
        s.moveRight();
        s.insert('9');
        assertEquals("Hell9o", s.toString());
    }

    @Property
    public boolean insertElements(@ForAll @IntRange(min = 0, max = 1000) int sz){
        GapBuffer s = new GapBuffer();
        for(int i = 0; i<sz; i++){
            s.insert('a');
        }
        return sz == s.getSize();
    }
}