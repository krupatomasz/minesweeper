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
public class MineField extends Field {
    public MineField(int x, int y, Board board){
        super(x, y, board);
        secondColor = Color.RED;
    }
}
