/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hvo123
 */
public class Black_pawn extends Piece {
    
    public Black_pawn(int y, int x,String name, boolean is_white, String file_path,boolean alive)
    {
        super(y, x, name,is_white,file_path,alive);
    }
    
     @Override
    public boolean canMove(int y, int x)
    {
        
      int y_distance = y-current_row;
	
		
        if(count_move == 1)
        {
            if((1 <= y_distance) &&( y_distance <= 2)&&(current_col == x) )
            {
               count_move++;
                return true;
            }
        
        }
        else 
        {
             if( (y_distance == 1) &&(current_col == x))
            {
              // count_move++;
                return true;
            }
        }
	
	return false;
    }
}
