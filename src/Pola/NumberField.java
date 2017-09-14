/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pola;

import java.awt.Color;
import java.awt.Graphics;
import minesweeper.Board;

/**
 *
 * @author Tomek
 */
public class NumberField extends Field {
    private final int number;
    public NumberField(int x, int y, Board board, int number){
        super(x, y, board);
        this.number = number;
        secondColor = Color.LIGHT_GRAY;
    }
    
    @Override
    public void paint(Graphics g){
        draw(g);
        if(!hidden){
            g.setColor(Color.RED);
            g.drawString(Integer.toString(number), 21*x + 5, 21*y + 15);
        }
    }
}
