/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hvo123
 */
public class Knight extends Piece {
    
    public Knight(int y, int x,String name, boolean is_white, String file_path,boolean alive)
    {
        super(y,x,name,is_white,file_path,alive);
    }
    
     @Override
    public boolean canMove(int y, int x)
    {
        
     int y_distance = Math.abs(y-current_row);
	int x_distance = Math.abs(x-current_col);
	
	if((y_distance == 1 ) && (x_distance == 2))
	{
		return true;
	}
	if((y_distance == 2 ) && (x_distance == 1))
	{
		return true;
	}

	return false;
    }
}
