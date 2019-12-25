/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hvo123
 */
public class Rook extends Piece {
    
    public Rook(int y, int x, String name, boolean is_white, String file_path, boolean alive)
    {
        super(y,x,name,is_white,file_path,alive);
    }
    
     @Override
    public boolean canMove(int y, int x)
    {
       //int y_distance = Math.abs(y-current_row);
	// x_distance = Math.abs(x-current_col);
	
	if(current_col == x) 
	{
		return true;
	}
	if(current_row == y) 
	{
		return true;
	}

	return false;
    }
}
