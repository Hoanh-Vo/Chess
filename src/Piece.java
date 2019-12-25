
public class Piece {
        protected int current_row ;
	protected int current_col ;
        protected int count_move = 1;     //to determine the 1st move
	private boolean is_white;       //true ==white chess, false == black chess
	private boolean alive;  // keep track of the king if it is captured
        private String name;
        private String file_path;
         		
	Piece(int y, int x,String name, boolean is_white, String file_path,boolean alive)
		{
                    current_row = y;
                    current_col =x;
                    this.name = name;
                    this.is_white = is_white;
                    this.file_path = file_path;
                    this.alive = alive;
		}

public String getFilePath()
    {
        return file_path;
    }
    
public void setFilePath(String path)
    {
        this.file_path = path;
    }
public String getName()
{
		    return name;
}		
public boolean isWhite()
{
    return is_white;
}

public void setWhite(boolean t)
{
	is_white = t;
}
public boolean is_alive()
{
    return alive;
}

public void set_alive(boolean t)
{
	is_white = t;
}
public int current_x_position()
	{
	return current_col;
	}
public int current_y_position()
	{
	return current_row;
	}
public void  set_x_position(int col)
{
	current_col = col;
}
public void  set_y_position(int row)
{
	current_row = row;
}
public boolean canMove(int destination_y, int destination_x)
{
    return false;
}

}
