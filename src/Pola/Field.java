/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pola;

/**
 *
 * @author Tomek
 */
import java.awt.Color;
import java.awt.Graphics;
import minesweeper.Board;
public class Field {
    protected Color color, secondColor;
    protected int x, y;
    protected boolean hidden, marked;
    protected Board board;
    
    public Field(int x, int y, Board board){
        this.x = x;
        this.y = y;
        color = Color.GRAY;
        hidden = true;
        marked = false;
        this.board = board;
    }
    public void reveal(){
        if(!hidden)
            return;
        hidden = false;
        marked = false;
        color = secondColor;
    }
    
    public void paint(Graphics g){
        draw(g);
    }
    
    protected void draw(Graphics g){
        g.setColor(color);
        g.fillRect(20*x + x, 20*y + y, 20, 20);
        if(marked){
            g.setColor(Color.BLACK);
            g.drawString("#", 21*x + 5, 21*y + 15);
        }
    }
    
    public boolean isHidden(){
        return hidden;
    }
    
    public void toggleMark(){
        if(hidden)
            marked = !marked;
    }
    
    public boolean isMarked(){
        return marked;
    }
    
}