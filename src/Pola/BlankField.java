/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pola;

import java.awt.Color;
import minesweeper.Board;

/**
 *
 * @author Tomek
 */
public class BlankField extends Field{
    public BlankField(int x, int y, Board board){
        super(x, y, board);
        secondColor = Color.LIGHT_GRAY;
    }
    
    @Override
    public void reveal(){
        if(!hidden)
            return;
        hidden = false;
        marked = false;
        color = secondColor;
        Field[][] map = board.getBoard();
        boolean left = false, right = false, up = false, dwon = false;
        if(board.withinBoard(x-1, y)){
            map[x-1][y].reveal();
            left = true;
        }
        if(board.withinBoard(x+1, y)){
            map[x+1][y].reveal();
            right = true;
        }
        if(board.withinBoard(x, y-1)){
            map[x][y-1].reveal();
            if(left)
                map[x-1][y-1].reveal();
            if(right)
                map[x+1][y-1].reveal();
        }
        if(board.withinBoard(x, y+1)){
            map[x][y+1].reveal();
            if(left)
                map[x-1][y+1].reveal();
            if(right)
                map[x+1][y+1].reveal();
        }
    }
    
}
