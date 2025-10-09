package edu.grinnell.csc207.texteditor;

import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;


/**
 * The driver for the TextEditor Application.
 */
public class TextEditor {

    /**
     * Draws the changes in the GapBuffer to the terminal.
     * @param buf a GapBuffer object
     * @param screen a Screen Object
     * @throws IOException if it fails to update the screen throw an exception
     */
    public static void drawBuffer(GapBuffer buf, Screen screen)throws IOException{
        //Clear the screen
        screen.clear();

        //Set the row and columns 
        int row = 0;
        int col = 0;

        //Write to the back buffer, and reset row if there is a return key pressed
        for(int i = 0; i < buf.getSize(); i++){
            if(buf.getChar(i) == '\n'){
                row++;
                col = 0;
                if(buf.getCursorPosition() == i+1){
                    //Draw the cursor at the front of the new line, and then increment col one more incase there were char after the cursor
                    screen.setCursorPosition(new TerminalPosition(col, row));
                    col++;
                }
            } else {
                char c = buf.getChar(i);
                //Is this col row or row col, why did row col not work - ask prof
                screen.setCharacter(col, row, TextCharacter.fromCharacter(c)[0]); //This is array? So dumb.
                col++;
                //Draw the cursor after the inputted char
                if(i+1 == buf.getCursorPosition()){
                    screen.setCursorPosition(new TerminalPosition(col, row));
                    col++;
                }
            }
        }

        try{
            screen.refresh();
        }catch (IOException e){
            System.out.println("Does this run");
            e.printStackTrace();
        }
    }       
    /**
     * The main entry point for the TextEditor application.
     * @param args command-line arguments.
     */
    public static void main(String[] args) throws IOException{
        if (args.length != 1) {
            System.err.println("Usage: java TextEditor <filename>");
            System.exit(1);
        }
        String stringPath = args[0];
        System.out.format("Loading %s...\n", stringPath);

        //Handle file input 
        Path path = Paths.get(stringPath);
        String initialContent = "";

        //Check if file already have input in it
        if (Files.exists(path) && Files.isRegularFile(path)) {
            initialContent = Files.readString(path);
        }

        // Create a GapBuffer
        GapBuffer s = new GapBuffer();
        boolean isRunning = true;

        //Add all previous characters in the file to our buffer.
        for(int i = 0; i < initialContent.length(); i++){
            s.insert(initialContent.charAt(i));
        }

        try{
        Screen screen = new DefaultTerminalFactory().createScreen();
        screen.startScreen();

        //If there is already existing string in the file first draw it onto the screen
        drawBuffer(s, screen);
        //Until the user presses escape take in user input and do methods on our string
        while(isRunning){
            KeyStroke stroke = screen.readInput();
            if(stroke.getKeyType() == KeyType.Escape){
                isRunning = false;
            } else if(stroke.getKeyType() == KeyType.ArrowLeft){
                s.moveLeft();
            } else if(stroke.getKeyType() == KeyType.ArrowRight){
                s.moveRight();
            } else if(stroke.getKeyType() == KeyType.Backspace){
                s.delete();
            } else{
                s.insert(stroke.getCharacter());
            }

            //Draw to the screen once change has been made
            drawBuffer(s, screen);
        }

        //If escape is presed exit the screen
        screen.stopScreen();
        //Add the new created string in stringbuffer to the file
        Files.writeString(path, s.toString());
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
