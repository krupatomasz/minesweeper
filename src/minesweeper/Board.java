/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

/**
 *
 * @author Tomek
 */
import Pola.NumberField;
import Pola.Field;
import Pola.MineField;
import Pola.BlankField;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
public class Board extends Frame{
    private final int mines = 40;
    private final int fields = 16;
    private Field board[][];
    
    public Board(){
        super("Saper");
        
        setSize(350, 400);
        addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent windowEvent){
           System.exit(0);
        }});
        board = new Field[fields][fields];
        generateMap();
        add(new Map(this, fields));
    }
    
    public int getNMines(){
        return mines;
    }
    public void generateMap(){
        for(int i=0; i<fields; i++)
            for(int j=0; j<fields; j++)
                board[i][j] = new BlankField(i, j, this);
        
        generateMines();
        generateNumbers();
    }
    
    private void generateMines(){
        Random generator = new Random();
        for(int i=0; i<mines; i++){
            int x = generator.nextInt(fields);
            int y = generator.nextInt(fields);
            if(board[x][y] instanceof MineField ){
                i--;
                continue;
            }
            board[x][y] = new MineField(x, y, this);            
        }
    }
    
    private void generateNumbers(){
        for(int i=0; i<fields; i++){
            for(int j=0; j<fields; j++){
                if(board[i][j] instanceof MineField)
                    continue;
                int nMines = countAdjacentMines(i, j);
                if(nMines > 0)
                    board[i][j] = new NumberField(i, j, this, nMines);
            }
        }
    }
    
    private int countAdjacentMines(int x, int y){
        int counter = 0;
        for(int i = x-1; i <= x+1; i++){
            for(int j=y-1; j <= y+1; j++){
                if(withinBoard(i, j)){
                    if(board[i][j] instanceof MineField){
                        counter++;
                    }
                }
            }
        }
        return counter;
    }
    
    public boolean withinBoard(int x, int y){
        boolean within = true;
        if(x < 0 || x >= fields)
            within = false;
        if(y < 0 || y >= fields)
            within = false;
        return within;
    }
    
   public Field[][] getBoard(){
       return this.board;
   }
   
}