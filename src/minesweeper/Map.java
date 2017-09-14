/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import Pola.Field;
import Pola.MineField;
import Pola.BlankField;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Tomek
 */
public class Map extends Panel {
    private int fields, correct, incorrect;
    private Board board;
    private Field[][] map;
    private boolean gameOver = false, won = false, firstClick = true;
    public Map(Board board, int fields){
        this.board = board;
        map = board.getBoard();
        this.fields = fields;
        correct = incorrect = 0;
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(gameOver)
                    return;
                int x = e.getX() / 21;
                int y = e.getY() / 21;
                switch(e.getModifiers()){
                    case InputEvent.BUTTON1_MASK:
                        if(map[x][y].isMarked())
                            return;
                        if(firstClick){
                            firstClick = false;
                            while(! (map[x][y] instanceof BlankField)){
                                board.generateMap();
                            }
                        }
                        
                        map[x][y].reveal();
                        if(map[x][y] instanceof MineField){
                            gameOver();
                        }
                        checkForWin();
                        break;
                        
                    case InputEvent.BUTTON3_MASK:
                        if(!map[x][y].isHidden()){
                            return;
                        }
                        if(map[x][y].isMarked()){
                            if (map[x][y] instanceof MineField)
                                correct--;
                            else
                                incorrect--;
                        } else {
                            if (map[x][y] instanceof MineField)
                                correct++;
                            else
                                incorrect++;
                        }
                        map[x][y].toggleMark();
                        checkForWin();
                        break;
                }
                
                repaint();
            }
        });
    }
    @Override
   public void paint(Graphics g) {
      if(gameOver){
          g.setColor(Color.BLACK);
          if(won)
              g.drawString("YOU WON!", 150, 360);
          else
            g.drawString("YOU LOST!", 150, 360);
        
      }
      for(int i=0; i<fields; i++){
          for(int j=0; j<fields; j++){
              map[i][j].paint(g);
          }
      }
      g.setColor(Color.BLACK);
      g.drawString("Mines: " + board.getNMines(), 10, 350);
      g.drawString("Marked: " + (correct+incorrect), 10, 365);
   }
   
   private void gameOver(){
       gameOver = true;
   }
   
   private void checkForWin(){
       if(allHiddenCorrect()){
           gameOver = true;
           won = true;
       }
   }
   
   private boolean allHiddenCorrect(){
       boolean hiddenIncorrectFound = false;
       for(int i=0; i<fields && !hiddenIncorrectFound; i++){
           for(int j=0; j<fields && !hiddenIncorrectFound; j++){
               if(map[i][j].isHidden() && !(map[i][j] instanceof MineField))
                   hiddenIncorrectFound = true;
           }
       }
       return !hiddenIncorrectFound;
   }
   
   
   
}
