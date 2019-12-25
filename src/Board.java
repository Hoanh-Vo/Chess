//work cited:
// chess images from https://github.com/psoder3/Program-Your-Own-Chess
//cover image from //https://www.123rf.com/photo_18932053_chess-pieces-on-a-background-of-fire-take-off-the-gloves.html
//
//
import java.awt.*;
import javax.swing.*;
import java.awt.color.*;
import java.awt.event.*;
import java.awt.image.*; 		//for images
import java.io.*;				//for images
import javax.imageio.ImageIO;	//for images
import java.util.*;

public class Board extends JFrame implements ActionListener{
	 private ArrayList<Piece> White_Pieces; //keeptrack of white pieces
	 private ArrayList<Piece> Black_Pieces; //keeptrack of black pieces
         private Map<JButton, Piece> map = new HashMap<JButton,Piece>();    //to keep track of the white king and black king
    //create buttons
	private JButton play = new JButton("Play");
	private JButton quit = new JButton("Quit"); //for gamePanel
	
	//create card panels 
	private JPanel greetingPanel = new JPanel();
	private JPanel gamePanel = new JPanel();
        private JPanel w_winning_Panel = new JPanel();
        private JPanel b_winning_Panel = new JPanel();
	
	//component panels for gamePanel
	private JPanel N_buttonsPanel = new JPanel();	
	private JPanel B_buttonsPanel = new JPanel();
	
	//set up as Card Layout
	CardLayout cardLayout = new CardLayout();
	

	//JLabel
	
        private JLabel black_Win_label = new JLabel();
        private JLabel white_Win_label = new JLabel();
	
	//=============for greetingPanel usage===================	       
        private BufferedImage topCover;        
	private BufferedImage bottomCover;
         
        private ImageIcon playIcon = new ImageIcon("images/playImage.gif");
            
	//=============for gamePanel usage===================
	//create a board
		private JLabel topLabel;
		private JLabel bottomLabel;
	
		//for the chess board	
		private final int ROWS = 8;
		private final int COLS = 8;
	
		//Create a game board
	
		private JPanel game_board = new JPanel (new GridLayout(ROWS,COLS));
		
		
		//create a tile
		protected JButton[][] tile = new JButton[ROWS][COLS];
	
		//chess board colors
		private Color white = Color.WHITE;
		private Color orage = Color.ORANGE;
		private Color tempColor;
//--------------->	     
            //create a 2D array of 0 and 1 for keeping track of white chess and black chess
                // 1 == white chess , 2 == black chess,0 for no (white and black)
                private int[][] color_array = new int[ROWS][COLS];
       
                int turn = 2;   //keep track of white turn(even) ,black turn (odd)
                int current_chess = 20; //to determine which chess is selected
                
                // white winning image
                
                private ImageIcon white_win = new ImageIcon("images/whitewin.gif");
                private Image tranformed_white_image = white_win.getImage().getScaledInstance(800, 800, java.awt.Image.SCALE_SMOOTH);
                //black winning image
                private  ImageIcon black_win = new ImageIcon("images/blackwin.gif");
                private Image tranformed_black_image = black_win.getImage().getScaledInstance(800,800, java.awt.Image.SCALE_SMOOTH);
                private JLabel win_label = new JLabel();
                
                
                Board()
        {
            
		//set up JFrame
		super("Timeless Chess");
                //set images for covers 
                try{
        topCover = ImageIO.read(new File("images/co1.gif"));
        bottomCover = ImageIO.read(new File("images/co2.gif"));
                }catch(Exception e)
                {}
            
		setLayout(cardLayout);
		setSize(800,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	
		 //images for cover
               	
		//add Action Perform for buttons
		play.addActionListener(this);
		quit.addActionListener(this);
		//initialize array list
                White_Pieces = new ArrayList();
                Black_Pieces = new ArrayList();
      
		//======================GreetingPanel (main1)===================
		greetingPanel.setLayout(new GridLayout(3,1,5,5)); //3 row , 1 column
		
		//add top picture to greeting panel
		JLabel top_cover_pic = new JLabel(new ImageIcon(topCover));
		greetingPanel.add(top_cover_pic,BorderLayout.CENTER);
			
		//add imageIcon to play-button
                play.setIcon(playIcon);
		greetingPanel.add(play);
		
		//add bottom picture to greeting panel
		
                JLabel bottom_cover_pic = new JLabel(new ImageIcon(bottomCover));
		greetingPanel.add(bottom_cover_pic,BorderLayout.CENTER);          
     //======================gamePanel (main2)=================
		
		gamePanel.setLayout(new BorderLayout());
		
		//top panel
		N_buttonsPanel.setLayout(new BorderLayout());
		N_buttonsPanel.setBackground(Color.GRAY);
		
		gamePanel.add(N_buttonsPanel,BorderLayout.NORTH);
		topLabel = new JLabel("               White chess goes first! ");
		topLabel.setFont(new Font("Arial",Font.PLAIN,40));
		N_buttonsPanel.add(topLabel,BorderLayout.CENTER);
		N_buttonsPanel.add(quit,BorderLayout.EAST);
		
		//bottom panel
		gamePanel.add(B_buttonsPanel,BorderLayout.SOUTH);
		bottomLabel = new JLabel("Click the piece; then, click to move!");
		bottomLabel.setFont(new Font("Arial",Font.PLAIN,40));
		B_buttonsPanel.add(bottomLabel);
		B_buttonsPanel.setBackground(Color.GRAY);
    
//imitialize chess pieces 
        White_Pieces.add(0,new Rook(7,0,"left_Rook",true,"images/WR.gif",true));
        White_Pieces.add(1,new Knight(7,1,"left_Knight",true,"images/WN.gif",true));
        White_Pieces.add(2,new Bishop(7,2,"left_Bishop",true,"images/WB.gif",true));
        White_Pieces.add(3,new Queen(7,3,"Queen",true,"images/WQ.gif",true));  
        White_Pieces.add(4,new King(7,4,"White_King",true,"images/WK.gif",true));  
        White_Pieces.add(5,new Bishop(7,5,"right_Bishop",true,"images/WB.gif",true));
        White_Pieces.add(6,new Knight(7,6,"right_Knight",true,"images/WN.gif",true));                                                            
        White_Pieces.add(7,new Rook(7,7,"right_Rook",true,"images/WR.gif",true));
        White_Pieces.add(8,new White_pawn(6,0,"Pawn_1",true,"images/WP.gif",true));
        White_Pieces.add(9,new White_pawn(6,1,"Pawn_2",true,"images/WP.gif",true));
        White_Pieces.add(10,new White_pawn(6,2,"Pawn_3",true,"images/WP.gif",true));
        White_Pieces.add(11,new White_pawn(6,3,"Pawn_4",true,"images/WP.gif",true));
        White_Pieces.add(12,new White_pawn(6,4,"Pawn_5",true,"images/WP.gif",true));
        White_Pieces.add(13,new White_pawn(6,5,"Pawn_6",true,"images/WP.gif",true));
        White_Pieces.add(14,new White_pawn(6,6,"Pawn_7",true,"images/WP.gif",true));
        White_Pieces.add(15,new White_pawn(6,7,"Pawn_8",true,"images/WP.gif",true));    
        
        Black_Pieces.add(0,new Rook(0,0,"left_Rook",false,"images/BR.gif",true));
        Black_Pieces.add(1,new Knight(0,1,"left_Knight",false,"images/BN.gif",true));  
        Black_Pieces.add(2,new Bishop(0,2,"left_Bishop",false,"images/BB.gif",true));                                 
        Black_Pieces.add(3,new Queen(0,3,"Queen",false,"images/BQ.gif",true));
        Black_Pieces.add(4,new King(0,4,"Black_King",false,"images/BK.gif",true));   
        Black_Pieces.add(5,new Bishop(0,5,"right_Bishop",false,"images/BB.gif",true));       
        Black_Pieces.add(6,new Knight(0,6,"right_Knight",false,"images/BN.gif",true));     
        Black_Pieces.add(7,new Rook(0,7,"right_Rook",false,"images/BR.gif",true));
        Black_Pieces.add(8,new Black_pawn(1,0,"Pawn_1",false,"images/BP.gif",true));
        Black_Pieces.add(9,new Black_pawn(1,1,"Pawn_2",false,"images/BP.gif",true));
        Black_Pieces.add(10,new Black_pawn(1,2,"Pawn_3",false,"images/BP.gif",true));
        Black_Pieces.add(11,new Black_pawn(1,3,"Pawn_4",false,"images/BP.gif",true));
        Black_Pieces.add(12,new Black_pawn(1,4,"Pawn_5",false,"images/BP.gif",true));
        Black_Pieces.add(13,new Black_pawn(1,5,"Pawn_6",false,"images/BP.gif",true));
        Black_Pieces.add(14,new Black_pawn(1,6,"Pawn_7",false,"images/BP.gif",true));
        Black_Pieces.add(15,new Black_pawn(1,7,"Pawn_8",false,"images/BP.gif",true));  
        
    //set position y,x for white chess    
                
           //----------the center panel ==== game panel------------

		
		//make a gameboard
		for(int row = 0; row <ROWS; ++row)
		{
			for(int col =0; col <COLS; ++col)
			{
				tile[row][col] = new JButton("");
				game_board.add(tile[row][col]);
	//add to the map , the JButton with default pawn to all JButton
            map.put(tile[row][col], new White_pawn(0,0,"Default_Pawn",true,"images/WP.gif",true));
				//set the color black and white for the gameboard
				if(col % COLS ==0)
				{
					tempColor = orage;
					orage = white;
					white = tempColor;
				}
				if(col % 2 ==0)
				{
					tile[row][col].setBackground(orage);
				}
				else
				{
					tile[row][col].setBackground(white);
				}
		//add action listener to each tile	
			tile[row][col].addActionListener(this);

                               
			}
		}
		
//adding black chesspieces to the board  
        for(int i = 0; i< Black_Pieces.size();++i)
        {
           //each piece object has correct y and x position 
            tile[Black_Pieces.get(i).current_y_position()][Black_Pieces.get(i).current_x_position()].setIcon( new ImageIcon(Black_Pieces.get(i).getFilePath())); 
//replace  Jbutton with Black Pieces inside map
             map.replace( tile[Black_Pieces.get(i).current_y_position()][Black_Pieces.get(i).current_x_position()], Black_Pieces.get(i));
        }

 //adding white chesspieces to the board  
        for(int i = 0; i< White_Pieces.size();++i)
        {
            tile[White_Pieces.get(i).current_y_position()][White_Pieces.get(i).current_x_position()].setIcon( new ImageIcon(White_Pieces.get(i).getFilePath()));  
           //replace  Jbutton with white Pieces inside map
           map.replace( tile[White_Pieces.get(i).current_y_position()][White_Pieces.get(i).current_x_position()], White_Pieces.get(i));
        }
//initialize 2D to keep track of black and white chess,  ,
		for(int row = 0; row < ROWS; ++row)
		{
			for(int col = 0; col<COLS; ++col)
			{
				//initialize individual cat_array with empty string
				color_array[row][col] = 0; // 0 for no check pieces
                               if( row == 0 || row == 1)
                                {
                                    color_array[row][col] = 2; //2 for black chess piece positions
                                }
                                if( row == 7 || row == 6)
                                {
                                    color_array[row][col] = 1; //1 for white chess piece positions
                                }
				
			}
		}
	

//add game board to gamePanel
		gamePanel.add(game_board,BorderLayout.CENTER);
//add winning images to panel
            black_Win_label.setIcon(new ImageIcon(tranformed_black_image));
             white_Win_label.setIcon(new ImageIcon(tranformed_white_image));
            w_winning_Panel.setLayout(new BorderLayout());
             w_winning_Panel.add(white_Win_label, BorderLayout.CENTER);
              b_winning_Panel.setLayout(new BorderLayout());
               b_winning_Panel.add(black_Win_label,BorderLayout.CENTER);
               //add Cards to JFrame
		add(greetingPanel,"WelcomePanel");
		add(gamePanel,"gamePanel");       
                add(w_winning_Panel,"wLabel");
                add(b_winning_Panel,"bLabel");
                //initialize chess Pieces 
                            
      
        //black winning and white winning images
       
                
        }               
private void invalid_move_message()
        {
            topLabel.setText("                       Unvalid move!"); 
            bottomLabel.setText("Move again! ");
             
        }                                 
  //implement the move for white chess 
private boolean white_pawn_move(int y, int x)
	{
    
           
   
            
            int ab_y = Math.abs(y - White_Pieces.get(current_chess).current_y_position());
            int ab_x = Math.abs(x - White_Pieces.get(current_chess).current_x_position());
        
//check if the next-move is a capture move, white pawn only capture upward direction
         if (((ab_x == ab_y)&&(ab_x == 1))&&((color_array[y][x] ==2) && (y < White_Pieces.get(current_chess).current_y_position())) )      //either diagonal 1 lelf or right
              {
               tile[White_Pieces.get(current_chess).current_y_position()][White_Pieces.get(current_chess).current_x_position()].setIcon(null); 
                 tile[y][x].setIcon(new ImageIcon(White_Pieces.get(current_chess).getFilePath()));
//update map
                map.remove(tile[White_Pieces.get(current_chess).current_y_position()][White_Pieces.get(current_chess).current_x_position()]);
                map.put(tile[y][x],White_Pieces.get(current_chess));

              //turn position to 0
              color_array[White_Pieces.get(current_chess).current_y_position()][White_Pieces.get(current_chess).current_x_position()] = 0;
		color_array[y][x] = 1; // 1 is for white
		
                // change color_array as well
               
		White_Pieces.get(current_chess).set_y_position(y);
		White_Pieces.get(current_chess).set_x_position(x);
                return true;
              }
            
       //if next-move is not a legit move 
            if(White_Pieces.get(current_chess).canMove(y,x) == false )
		{
			return false;
		}
		
            
            
                 //if the next-move is have black or white chess piece   
              if((color_array[y][x] == 1)|| (color_array[y][x] == 2) )//if same color 
              {
                 
                  return false;
		
              }
               //if title is emplty ,the move is granted
              if( color_array[y][x] == 0)         
              {
              tile[White_Pieces.get(current_chess).current_y_position()][White_Pieces.get(current_chess).current_x_position()].setIcon(null);
              tile[y][x].setIcon(new ImageIcon(White_Pieces.get(current_chess).getFilePath()));
              
  //update map
                map.remove(tile[White_Pieces.get(current_chess).current_y_position()][White_Pieces.get(current_chess).current_x_position()]);
                map.put(tile[y][x],White_Pieces.get(current_chess));
              //turn position to 0
              color_array[White_Pieces.get(current_chess).current_y_position()][White_Pieces.get(current_chess).current_x_position()] = 0;
		color_array[y][x] = 1; // 1 is for white
		
                // change color_array as well
               
		White_Pieces.get(current_chess).set_y_position(y);
		White_Pieces.get(current_chess).set_x_position(x);
                return true;
              }
	return false;
        }  
private boolean white_rook_move(int y, int x)
	{
	
       //if next-move is not a legit move 
            if(White_Pieces.get(current_chess).canMove(y,x) == false )
		{
			return false;
		}
		
            
            
                 //if the next-move legit and the tile has samecolor chess    
              if(color_array[y][x] == 1)  //if same color 
              {
                 
                  return false ;
		
              }
                //the case there some chess pieces in the path-way
         
                for(int i = 0; i < 8; ++i)
                {
                    //south-path
                               if((White_Pieces.get(current_chess).current_y_position() < i)&&( i < y) )
                                {
                                   if((color_array[i][x] == 1)||(color_array[i][x] == 2))
                                   {
                                       return false;
                                   }
                                }
                              //noth-path
                               if((y < i)&&(i < White_Pieces.get(current_chess).current_y_position() ) )
                                {
                                   if((color_array[i][x] == 1)||(color_array[i][x] == 2))
                                   {
                                       return false;
                                   }
                                }
                }
                 for(int i = 0; i < 8; ++i)
                {
                    //north-path
                               if((White_Pieces.get(current_chess).current_x_position() < i)&&( i < x) )
                                {
                                   if((color_array[y][i] == 1)||(color_array[y][i] == 2))
                                   {
                                       return false;
                                   }
                                }
                              //south-path
                               if((x < i)&&(i < White_Pieces.get(current_chess).current_x_position() ) )
                                {
                                   if((color_array[y][i] == 1)||(color_array[y][i] == 2))
                                   {
                                       return false;
                                   }
                                }
                }
       
               //if title is emplty or have black piece, white piece move is granted
              if(color_array[y][x] == 2 || color_array[y][x] == 0)         
              {
              tile[White_Pieces.get(current_chess).current_y_position()][White_Pieces.get(current_chess).current_x_position()].setIcon(null);
              tile[y][x].setIcon(new ImageIcon(White_Pieces.get(current_chess).getFilePath()));
 //update map
                map.remove(tile[White_Pieces.get(current_chess).current_y_position()][White_Pieces.get(current_chess).current_x_position()]);
                map.put(tile[y][x],White_Pieces.get(current_chess));
//turn position to 0
              color_array[White_Pieces.get(current_chess).current_y_position()][White_Pieces.get(current_chess).current_x_position()] = 0;
		color_array[y][x] = 1; // 1 is for white piece
		
                // change color_array as well
               
		White_Pieces.get(current_chess).set_y_position(y);
		White_Pieces.get(current_chess).set_x_position(x);
                return true;
              }
   
       return false;
	}               
private boolean white_knight_move(int y, int x)
	{
		
   
            
       //if next-move is not a legit move 
            if(White_Pieces.get(current_chess).canMove(y,x) == false )
		{
			return false;
		}
		
            
            
                 //if the next-move legit and the tile is empty     
              if(color_array[y][x] == 1)  //if same color 
              {
                 
                  return false;
		
              }
               //if title is emplty or have black piece, white piece move is granted
              if(color_array[y][x] == 2 || color_array[y][x] == 0)         
              {
              tile[White_Pieces.get(current_chess).current_y_position()][White_Pieces.get(current_chess).current_x_position()].setIcon(null);
              tile[y][x].setIcon(new ImageIcon(White_Pieces.get(current_chess).getFilePath()));
 //update map
                map.remove(tile[White_Pieces.get(current_chess).current_y_position()][White_Pieces.get(current_chess).current_x_position()]);
                map.put(tile[y][x],White_Pieces.get(current_chess));
              //turn position to 0
              color_array[White_Pieces.get(current_chess).current_y_position()][White_Pieces.get(current_chess).current_x_position()] = 0;
		color_array[y][x] = 1; // 1 is for white piece
		
                // change color_array as well
               
		White_Pieces.get(current_chess).set_y_position(y);
		White_Pieces.get(current_chess).set_x_position(x);
                return true;
              }
   return false;
         
	}           
private boolean white_bishop_move(int y, int x)
	{
		
   
           
            
       //if next-move is not a legit move 
            if(White_Pieces.get(current_chess).canMove(y,x) == false )
		{
			return false;
		}
		
            
            
                 //if the next-move legit and the tile is empty     
              if(color_array[y][x] == 1)  //if same color 
              {
                 
                  return false ;
		
              }
              //if there other chess pieces in the path
             
              int distance = Math.abs(y-White_Pieces.get(current_chess).current_y_position()); //square distance
             int y_distance = y - White_Pieces.get(current_chess).current_y_position();
             int x_distance = x - White_Pieces.get(current_chess).current_x_position();
             if(y_distance < 0)
             {
                 if(x_distance <0)
                 {
                 //move north-west
                     int tempX = x;
                     int tempY = y;
                     for(int i = 1; i< distance; ++i)
                     {
                         if(( color_array[tempY+i][tempX+i] == 1)||( color_array[tempY+i][tempX+i] == 2))
                         {
                             return false;
                         }
                     }
                 }else
                 {
                     //move north-East
                     int tempX = x;
                     int tempY = y;
                     for(int i = 1; i< distance; ++i)
                     {
                         if(( color_array[tempY + i][tempX-i] == 1)||( color_array[tempY+i][tempX-i] == 2))
                         {
                             return false;
                         }
                 }
             }
            }else
             {
                 if(x_distance <0)
                 {
                     //Move South-West
                      int tempX = x;
                     int tempY = y;
                     for(int i = 1; i< distance; ++i)
                     {
                         if(( color_array[tempY-i][tempX+i] == 1)||( color_array[tempY-i][tempX+i] == 2))
                         {
                             return false;
                         }
                     }
                 }else
                 {
                     //Move south-East
                      int tempX = x;
                     int tempY = y;
                     for(int i = 1; i< distance; ++i)
                     {
                         if(( color_array[tempY-i][tempX-i] == 1)||( color_array[tempY-i][tempX-i] == 2))
                         {
                             return false;
                         }
                     }
                 }
             }
             
              
//if title is emplty or have black piece, white piece move is granted
              if(color_array[y][x] == 2 || color_array[y][x] == 0)         
              {
              tile[White_Pieces.get(current_chess).current_y_position()][White_Pieces.get(current_chess).current_x_position()].setIcon(null);
              tile[y][x].setIcon(new ImageIcon(White_Pieces.get(current_chess).getFilePath()));
  //update map
                map.remove(tile[White_Pieces.get(current_chess).current_y_position()][White_Pieces.get(current_chess).current_x_position()]);
                map.put(tile[y][x],White_Pieces.get(current_chess));
              //turn position to 0
              color_array[White_Pieces.get(current_chess).current_y_position()][White_Pieces.get(current_chess).current_x_position()] = 0;
		color_array[y][x] = 1; // 1 is for white piece
		
                // change color_array as well
               
		White_Pieces.get(current_chess).set_y_position(y);
		White_Pieces.get(current_chess).set_x_position(x);
                return true;
              }
   
         return false;
	}  
private boolean white_queen_move(int y, int x)
	{
		
   
           
            
       //if next-move is not a legit move 
            if(White_Pieces.get(current_chess).canMove(y,x) == false )
		{
			return false;
		}
		
            
            
                 //if the next-move legit and the tile is empty     
              if(color_array[y][x] == 1)  //if same color 
              {
                 
                  return false;
		
              }
              
                  //if there other chess pieces in the path
                  
                  //the case there some chess pieces in the path-way when the queen is move horizontal or vertical
             if((White_Pieces.get(current_chess).current_x_position() == x)||(White_Pieces.get(current_chess).current_y_position() == y))
             {
                  
         
                for(int i = 0; i < 8; ++i)
                {
                    //north-path
                               if((White_Pieces.get(current_chess).current_y_position() < i)&&( i < y) )
                                {
                                   if((color_array[i][x] == 1)||(color_array[i][x] == 2))
                                   {
                                       return false;
                                   }
                                }
                              //south-path
                               if((y < i)&&(i < White_Pieces.get(current_chess).current_y_position() ) )
                                {
                                   if((color_array[i][x] == 1)||(color_array[i][x] == 2))
                                   {
                                       return false;
                                   }
                                }
                }
                 for(int i = 0; i < 8; ++i)
                {
                    //north-path
                               if((White_Pieces.get(current_chess).current_x_position() < i)&&( i < x) )
                                {
                                   if((color_array[y][i] == 1)||(color_array[y][i] == 2))
                                   {
                                       return false;
                                   }
                                }
                              //south-path
                               if((x < i)&&(i < White_Pieces.get(current_chess).current_x_position() ) )
                                {
                                   if((color_array[y][i] == 1)||(color_array[y][i] == 2))
                                   {
                                       return false;
                                   }
                                }
                }
             }
             else //the queen move diagonally 
             {
                 int distance = Math.abs(y-White_Pieces.get(current_chess).current_y_position()); //square distance
             int y_distance = y - White_Pieces.get(current_chess).current_y_position();
             int x_distance = x - White_Pieces.get(current_chess).current_x_position();
             if(y_distance < 0)
             {
                 if(x_distance <0)
                 {
                 //move north-west
                     int tempX = x;
                     int tempY = y;
                     for(int i = 1; i< distance; ++i)
                     {
                         if(( color_array[tempY+i][tempX+i] == 1)||( color_array[tempY+i][tempX+i] == 2))
                         {
                             return false;
                         }
                     }
                 }else
                 {
                     //move north-East
                     int tempX = x;
                     int tempY = y;
                     for(int i = 1; i< distance; ++i)
                     {
                         if(( color_array[tempY + i][tempX-i] == 1)||( color_array[tempY+i][tempX-i] == 2))
                         {
                             return false;
                         }
                 }
             }
            }else
             {
                 if(x_distance <0)
                 {
                     //Move South-West
                      int tempX = x;
                     int tempY = y;
                     for(int i = 1; i< distance; ++i)
                     {
                         if(( color_array[tempY-i][tempX+i] == 1)||( color_array[tempY-i][tempX+i] == 2))
                         {
                             return false;
                         }
                     }
                 }else
                 {
                     //Move south-East
                      int tempX = x;
                     int tempY = y;
                     for(int i = 1; i< distance; ++i)
                     {
                         if(( color_array[tempY-i][tempX-i] == 1)||( color_array[tempY-i][tempX-i] == 2))
                         {
                             return false;
                         }
                     }
                 }
             }
               
             }
              
             
               //if title is emplty or have black piece, white piece move is granted
              if(color_array[y][x] == 2 || color_array[y][x] == 0)         
              {
              tile[White_Pieces.get(current_chess).current_y_position()][White_Pieces.get(current_chess).current_x_position()].setIcon(null);
              tile[y][x].setIcon(new ImageIcon(White_Pieces.get(current_chess).getFilePath()));
              
    //update map
                map.remove(tile[White_Pieces.get(current_chess).current_y_position()][White_Pieces.get(current_chess).current_x_position()]);
                map.put(tile[y][x],White_Pieces.get(current_chess));           
              //turn position to 0
              color_array[White_Pieces.get(current_chess).current_y_position()][White_Pieces.get(current_chess).current_x_position()] = 0;
		color_array[y][x] = 1; // 1 is for white piece
		
                // change color_array as well
               
		White_Pieces.get(current_chess).set_y_position(y);
		White_Pieces.get(current_chess).set_x_position(x);
                return true;
              }
   return false;
         
	}    
private boolean white_king_move(int y, int x)
	{
		
      
           
            
       //if next-move is not a legit move 
            if(White_Pieces.get(current_chess).canMove(y,x) == false )
		{
			return false;
		}
		
            
            
                 //if the next-move legit and the tile is empty     
              if(color_array[y][x] == 1)  //if same color 
              {
                 
                  return false;
		
              }
               //if title is emplty or have black piece, white piece move is granted
              if(color_array[y][x] == 2 || color_array[y][x] == 0)         
              {
              tile[White_Pieces.get(current_chess).current_y_position()][White_Pieces.get(current_chess).current_x_position()].setIcon(null);
              tile[y][x].setIcon(new ImageIcon(White_Pieces.get(current_chess).getFilePath()));
              
    //update map
                map.remove(tile[White_Pieces.get(current_chess).current_y_position()][White_Pieces.get(current_chess).current_x_position()]);
                map.put(tile[y][x],White_Pieces.get(current_chess));          
              //turn position to 0
              color_array[White_Pieces.get(current_chess).current_y_position()][White_Pieces.get(current_chess).current_x_position()] = 0;
		color_array[y][x] = 1; // 1 is for white piece
		
                // change color_array as well
               
		White_Pieces.get(current_chess).set_y_position(y);
		White_Pieces.get(current_chess).set_x_position(x);
                return true;
              }
   
         return false;
	}   
   //implement the move for black chess
private boolean black_pawn_move(int y, int x)
	{
	
          
      int ab_y = Math.abs(y - Black_Pieces.get(current_chess).current_y_position());
    int ab_x = Math.abs(x - Black_Pieces.get(current_chess).current_x_position());
        
//check if the next-move is a capture move, black pawn can only capture in downward direction
         if (((ab_x == ab_y)&&(ab_x == 1))&&((color_array[y][x] ==1) && (y >Black_Pieces.get(current_chess).current_y_position()) ) )      //either diagonal 1 lelf or right
              {
               tile[Black_Pieces.get(current_chess).current_y_position()][Black_Pieces.get(current_chess).current_x_position()].setIcon(null);
              tile[y][x].setIcon(new ImageIcon(Black_Pieces.get(current_chess).getFilePath()));
              
    //update map
                map.remove(tile[Black_Pieces.get(current_chess).current_y_position()][Black_Pieces.get(current_chess).current_x_position()]);
                map.put(tile[y][x],Black_Pieces.get(current_chess));             
              //turn position to 0
              color_array[Black_Pieces.get(current_chess).current_y_position()][Black_Pieces.get(current_chess).current_x_position()] = 0;
		color_array[y][x] = 2; // 1 is for white
		
                // change color_array as well
               
		Black_Pieces.get(current_chess).set_y_position(y);
		Black_Pieces.get(current_chess).set_x_position(x);
                return true;
              }
            
       //if next-move is not a legit move 
            if(Black_Pieces.get(current_chess).canMove(y,x) == false )
		{
			return false;
		}
		
            
            
                 //if the next-move is have black or white chess piece   
              if((color_array[y][x] == 1)|| (color_array[y][x] == 2) )//if same color 
              {
                 
                  return false;
		
              }
               //if title is emplty ,the move is granted
              if( color_array[y][x] == 0)         
              {
              tile[Black_Pieces.get(current_chess).current_y_position()][Black_Pieces.get(current_chess).current_x_position()].setIcon(null);
              tile[y][x].setIcon(new ImageIcon(Black_Pieces.get(current_chess).getFilePath()));
  //update map
                map.remove(tile[Black_Pieces.get(current_chess).current_y_position()][Black_Pieces.get(current_chess).current_x_position()]);
                map.put(tile[y][x],Black_Pieces.get(current_chess));
              //turn position to 0
              color_array[Black_Pieces.get(current_chess).current_y_position()][Black_Pieces.get(current_chess).current_x_position()] = 0;
		color_array[y][x] = 2; // 1 is for white
		
                // change color_array as well
               
		Black_Pieces.get(current_chess).set_y_position(y);
		Black_Pieces.get(current_chess).set_x_position(x);
                return true;
              }
	return false;
        }   
private boolean black_rook_move(int y, int x)
	{
		
      
           
            
       //if next-move is not a legit move 
            if(Black_Pieces.get(current_chess).canMove(y,x) == false )
		{
			return false;
		}
		
            
            
                 //if the next-move legit and the tile is empty     
              if(color_array[y][x] == 2)  //if same color 
              {
                 
                  return false;
		
              }
                 //the case there some chess pieces in the path-way
         
                for(int i = 0; i < 8; ++i)
                {
                    //north-path
                               if((Black_Pieces.get(current_chess).current_y_position() < i)&&( i < y) )
                                {
                                   if((color_array[i][x] == 1)||(color_array[i][x] == 2))
                                   {
                                       return false;
                                   }
                                }
                              //south-path
                               if((y < i)&&(i < Black_Pieces.get(current_chess).current_y_position() ) )
                                {
                                   if((color_array[i][x] == 1)||(color_array[i][x] == 2))
                                   {
                                       return false;
                                   }
                                }
                }
                 for(int i = 0; i < 8; ++i)
                {
                    //north-path
                               if((Black_Pieces.get(current_chess).current_x_position() < i)&&( i < x) )
                                {
                                   if((color_array[y][i] == 1)||(color_array[y][i] == 2))
                                   {
                                       return false;
                                   }
                                }
                              //south-path
                               if((x < i)&&(i < Black_Pieces.get(current_chess).current_x_position() ) )
                                {
                                   if((color_array[y][i] == 1)||(color_array[y][i] == 2))
                                   {
                                       return false;
                                   }
                                }
                }
               //if title is emplty or have black piece, white piece move is granted
              if(color_array[y][x] == 1 || color_array[y][x] == 0)         
              {
              tile[Black_Pieces.get(current_chess).current_y_position()][Black_Pieces.get(current_chess).current_x_position()].setIcon(null);
              tile[y][x].setIcon(new ImageIcon(Black_Pieces.get(current_chess).getFilePath()));
   //update map
                map.remove(tile[Black_Pieces.get(current_chess).current_y_position()][Black_Pieces.get(current_chess).current_x_position()]);
                map.put(tile[y][x],Black_Pieces.get(current_chess));
              //turn position to 0
              color_array[Black_Pieces.get(current_chess).current_y_position()][Black_Pieces.get(current_chess).current_x_position()] = 0;
		color_array[y][x] = 2; // 1 is for white piece
		
                // change color_array as well
               
		Black_Pieces.get(current_chess).set_y_position(y);
		Black_Pieces.get(current_chess).set_x_position(x);
                return true;
              }
   
         return false;
	}
private boolean black_knight_move(int y, int x)
	{
		
    
            
       //if next-move is not a legit move 
            if(Black_Pieces.get(current_chess).canMove(y,x) == false )
		{
			return false;
		}
		
            
            
                 //if the next-move legit and the tile is empty     
              if(color_array[y][x] == 2)  //if same color 
              {
                 
                  return false;
		
              }
               //if title is emplty or have black piece, white piece move is granted
              if(color_array[y][x] == 1 || color_array[y][x] == 0)         
              {
              tile[Black_Pieces.get(current_chess).current_y_position()][Black_Pieces.get(current_chess).current_x_position()].setIcon(null);
              tile[y][x].setIcon(new ImageIcon(Black_Pieces.get(current_chess).getFilePath()));
              
    //update map
                map.remove(tile[Black_Pieces.get(current_chess).current_y_position()][Black_Pieces.get(current_chess).current_x_position()]);
                map.put(tile[y][x],Black_Pieces.get(current_chess));   
              //turn position to 0
              color_array[Black_Pieces.get(current_chess).current_y_position()][Black_Pieces.get(current_chess).current_x_position()] = 0;
		color_array[y][x] = 2; // 1 is for white piece
		
                // change color_array as well
               
		Black_Pieces.get(current_chess).set_y_position(y);
		Black_Pieces.get(current_chess).set_x_position(x);
                return true;
              }
   return false;
         
	}
private boolean black_bishop_move(int y, int x)
	{
		
    
           
            
       //if next-move is not a legit move 
            if(Black_Pieces.get(current_chess).canMove(y,x) == false )
		{
			return false;
		}
		
            
            
                 //if the next-move legit and the tile is empty     
              if(color_array[y][x] == 2)  //if same color 
              {
                 
                  return false;
		
              }
                 //if there other chess pieces in the path
             
              int distance = Math.abs(y-Black_Pieces.get(current_chess).current_y_position()); //square distance
             int y_distance = y - Black_Pieces.get(current_chess).current_y_position();
             int x_distance = x - Black_Pieces.get(current_chess).current_x_position();
             if(y_distance < 0)
             {
                 if(x_distance <0)
                 {
                 //move north-west
                     int tempX = x;
                     int tempY = y;
                     for(int i = 1; i< distance; ++i)
                     {
                         if(( color_array[tempY+i][tempX+i] == 1)||( color_array[tempY+i][tempX+i] == 2))
                         {
                             return false;
                         }
                     }
                 }else
                 {
                     //move north-East
                     int tempX = x;
                     int tempY = y;
                     for(int i = 1; i< distance; ++i)
                     {
                         if(( color_array[tempY + i][tempX-i] == 1)||( color_array[tempY+i][tempX-i] == 2))
                         {
                             return false;
                         }
                 }
             }
            }else
             {
                 if(x_distance <0)
                 {
                     //Move South-West
                      int tempX = x;
                     int tempY = y;
                     for(int i = 1; i< distance; ++i)
                     {
                         if(( color_array[tempY-i][tempX+i] == 1)||( color_array[tempY-i][tempX+i] == 2))
                         {
                             return false;
                         }
                     }
                 }else
                 {
                     //Move south-East
                      int tempX = x;
                     int tempY = y;
                     for(int i = 1; i< distance; ++i)
                     {
                         if(( color_array[tempY-i][tempX-i] == 1)||( color_array[tempY-i][tempX-i] == 2))
                         {
                             return false;
                         }
                     }
                 }
             }
             
               //if title is emplty or have black piece, white piece move is granted
              if(color_array[y][x] == 1 || color_array[y][x] == 0)         
              {
              tile[Black_Pieces.get(current_chess).current_y_position()][Black_Pieces.get(current_chess).current_x_position()].setIcon(null);
              tile[y][x].setIcon(new ImageIcon(Black_Pieces.get(current_chess).getFilePath()));
    //update map
                map.remove(tile[Black_Pieces.get(current_chess).current_y_position()][Black_Pieces.get(current_chess).current_x_position()]);
                map.put(tile[y][x],Black_Pieces.get(current_chess));     
              //turn position to 0
              color_array[Black_Pieces.get(current_chess).current_y_position()][Black_Pieces.get(current_chess).current_x_position()] = 0;
		color_array[y][x] = 2; // 1 is for white piece
		
                // change color_array as well
               
		Black_Pieces.get(current_chess).set_y_position(y);
		Black_Pieces.get(current_chess).set_x_position(x);
                return true;
              }
   return false;
         
	}      
private boolean black_queen_move(int y, int x)
	{
		
     
           
            
       //if next-move is not a legit move 
            if(Black_Pieces.get(current_chess).canMove(y,x) == false )
		{
			return false;
		}
		
            
            
                 //if the next-move legit and the tile is empty     
              if(color_array[y][x] == 2)  //if same color 
              {
                 
                  return false;
		
              }
              
                 //if there other chess pieces in the path
                  
                  //the case there some chess pieces in the path-way when the queen is move horizontal or vertical
             if((Black_Pieces.get(current_chess).current_x_position() == x)||(Black_Pieces.get(current_chess).current_y_position() == y))
             {
                  
         
                for(int i = 0; i < 8; ++i)
                {
                    //north-path
                               if((Black_Pieces.get(current_chess).current_y_position() < i)&&( i < y) )
                                {
                                   if((color_array[i][x] == 1)||(color_array[i][x] == 2))
                                   {
                                       return false;
                                   }
                                }
                              //south-path
                               if((y < i)&&(i < Black_Pieces.get(current_chess).current_y_position() ) )
                                {
                                   if((color_array[i][x] == 1)||(color_array[i][x] == 2))
                                   {
                                       return false;
                                   }
                                }
                }
                 for(int i = 0; i < 8; ++i)
                {
                    //north-path
                               if((Black_Pieces.get(current_chess).current_x_position() < i)&&( i < x) )
                                {
                                   if((color_array[y][i] == 1)||(color_array[y][i] == 2))
                                   {
                                       return false;
                                   }
                                }
                              //south-path
                               if((x < i)&&(i < Black_Pieces.get(current_chess).current_x_position() ) )
                                {
                                   if((color_array[y][i] == 1)||(color_array[y][i] == 2))
                                   {
                                       return false;
                                   }
                                }
                }
             }
             else //the queen move diagonally 
             {
                 int distance = Math.abs(y-Black_Pieces.get(current_chess).current_y_position()); //square distance
             int y_distance = y - Black_Pieces.get(current_chess).current_y_position();
             int x_distance = x - Black_Pieces.get(current_chess).current_x_position();
             if(y_distance < 0)
             {
                 if(x_distance <0)
                 {
                 //move north-west
                     int tempX = x;
                     int tempY = y;
                     for(int i = 1; i< distance; ++i)
                     {
                         if(( color_array[tempY+i][tempX+i] == 1)||( color_array[tempY+i][tempX+i] == 2))
                         {
                             return false;
                         }
                     }
                 }else
                 {
                     //move north-East
                     int tempX = x;
                     int tempY = y;
                     for(int i = 1; i< distance; ++i)
                     {
                         if(( color_array[tempY + i][tempX-i] == 1)||( color_array[tempY+i][tempX-i] == 2))
                         {
                             return false;
                         }
                 }
             }
            }else
             {
                 if(x_distance <0)
                 {
                     //Move South-West
                      int tempX = x;
                     int tempY = y;
                     for(int i = 1; i< distance; ++i)
                     {
                         if(( color_array[tempY-i][tempX+i] == 1)||( color_array[tempY-i][tempX+i] == 2))
                         {
                             return false;
                         }
                     }
                 }else
                 {
                     //Move south-East
                      int tempX = x;
                     int tempY = y;
                     for(int i = 1; i< distance; ++i)
                     {
                         if(( color_array[tempY-i][tempX-i] == 1)||( color_array[tempY-i][tempX-i] == 2))
                         {
                             return false;
                         }
                     }
                 }
             }
               
             }
               //if title is emplty or have black piece, white piece move is granted
              if(color_array[y][x] == 1 || color_array[y][x] == 0)         
              {
              tile[Black_Pieces.get(current_chess).current_y_position()][Black_Pieces.get(current_chess).current_x_position()].setIcon(null);
              tile[y][x].setIcon(new ImageIcon(Black_Pieces.get(current_chess).getFilePath()));
    //update map
                map.remove(tile[Black_Pieces.get(current_chess).current_y_position()][Black_Pieces.get(current_chess).current_x_position()]);
                map.put(tile[y][x],Black_Pieces.get(current_chess));     
              //turn position to 0
              color_array[Black_Pieces.get(current_chess).current_y_position()][Black_Pieces.get(current_chess).current_x_position()] = 0;
		color_array[y][x] = 2; // 1 is for white piece
		
                // change color_array as well
               
		Black_Pieces.get(current_chess).set_y_position(y);
		Black_Pieces.get(current_chess).set_x_position(x);
                return true;
              }
   return false;
         
	}    
private boolean black_king_move(int y, int x)
	{
		
   
           
            
       //if next-move is not a legit move 
            if(Black_Pieces.get(current_chess).canMove(y,x) == false )
		{
			return false;
		}
		
            
            
                 //if the next-move legit and the tile is empty     
              if(color_array[y][x] == 2)  //if same color 
              {
                 
                  return false;
		
              }
               //if title is emplty or have black piece, white piece move is granted
              if(color_array[y][x] == 1 || color_array[y][x] == 0)         
              {
              tile[Black_Pieces.get(current_chess).current_y_position()][Black_Pieces.get(current_chess).current_x_position()].setIcon(null);
              tile[y][x].setIcon(new ImageIcon(Black_Pieces.get(current_chess).getFilePath()));
    //update map
                map.remove(tile[Black_Pieces.get(current_chess).current_y_position()][Black_Pieces.get(current_chess).current_x_position()]);
                map.put(tile[y][x],Black_Pieces.get(current_chess));         
              //turn position to 0
              color_array[Black_Pieces.get(current_chess).current_y_position()][Black_Pieces.get(current_chess).current_x_position()] = 0;
		color_array[y][x] = 2; // 1 is for white piece
		
                // change color_array as well
               
		Black_Pieces.get(current_chess).set_y_position(y);
		Black_Pieces.get(current_chess).set_x_position(x);
                return true;
              }
   
         return false;
	} 
   
private boolean white_king_checkmate()
 {
    
     
    
    //the distance travel doward to the edge
     int downward_y = 8 - White_Pieces.get(4).current_y_position();
   
      //distance travel eastward to the edge
     int eastward_x = 8 - White_Pieces.get(4).current_x_position();
  
    
     //////////in the upward direction, distance == from 0 to current position
     for(int i = 1; i <  White_Pieces.get(4).current_y_position();++i)
     {
           if( White_Pieces.get(4).current_x_position() ==  Black_Pieces.get(0).current_x_position())   //check for the left black brook
           {
              if(White_Pieces.get(4).current_y_position()-i == Black_Pieces.get(0).current_y_position())
              {
                    if(straight_clear_path(White_Pieces.get(4),Black_Pieces.get(0).current_y_position(),Black_Pieces.get(0).current_x_position()) == true)
                    {
                         return true;
                    }                
              }
                  
             }
            if(White_Pieces.get(4).current_x_position() ==  Black_Pieces.get(7).current_x_position())   //check for the right black brook
           {
               if(White_Pieces.get(4).current_y_position()-i == Black_Pieces.get(7).current_y_position())
              {
                  if(straight_clear_path(White_Pieces.get(4),Black_Pieces.get(7).current_y_position(),Black_Pieces.get(7).current_x_position()) == true)
                    {
                         return true;
                    }   
              }
           }
             if(White_Pieces.get(4).current_x_position() ==  Black_Pieces.get(3).current_x_position())  //check for black queen
           {
                if(White_Pieces.get(4).current_y_position()-i == Black_Pieces.get(3).current_y_position())
              {
                  if(straight_clear_path(White_Pieces.get(4),Black_Pieces.get(3).current_y_position(),Black_Pieces.get(3).current_x_position()) == true)
                    {
                         return true;
                    }   
              }
           }
              
             if(i == 1)
             {
            	  if(White_Pieces.get(4).current_x_position() ==  Black_Pieces.get(4).current_x_position())    //check for black king
                  {
                       if(White_Pieces.get(4).current_y_position()-i == Black_Pieces.get(4).current_y_position())
                     {
                         return true;
                     }
                  }
            	 
             }
           
     }
       //////////in the downward direction
     for(int i = 1; i <  downward_y ;++i)
     {
           if( White_Pieces.get(4).current_x_position() ==  Black_Pieces.get(0).current_x_position()) //check for the black left brook
           {
              if(White_Pieces.get(4).current_y_position()+i == Black_Pieces.get(0).current_y_position())
              {
                   if(straight_clear_path(White_Pieces.get(4),Black_Pieces.get(0).current_y_position(),Black_Pieces.get(0).current_x_position()) == true)
                    {
                         return true;
                    }   
              }
                  
             }
            if(White_Pieces.get(4).current_x_position() ==  Black_Pieces.get(7).current_x_position())   //check for black right brook
           {
               if(White_Pieces.get(4).current_y_position()+i == Black_Pieces.get(7).current_y_position())
              {
                  if(straight_clear_path(White_Pieces.get(4),Black_Pieces.get(7).current_y_position(),Black_Pieces.get(7).current_x_position()) == true)
                    {
                         return true;
                    }   
              }
           }
             if(White_Pieces.get(4).current_x_position() ==  Black_Pieces.get(3).current_x_position()) //check for the queen
           {
                if(White_Pieces.get(4).current_y_position()+i == Black_Pieces.get(3).current_y_position())
              {
                  if(straight_clear_path(White_Pieces.get(4),Black_Pieces.get(3).current_y_position(),Black_Pieces.get(3).current_x_position()) == true)
                    {
                         return true;
                    }   
              }
           }
             
             if(i == 1)
             {
            	 if(White_Pieces.get(4).current_x_position() ==  Black_Pieces.get(4).current_x_position()) //check for the king
                 {
                      if(White_Pieces.get(4).current_y_position()+i == Black_Pieces.get(4).current_y_position())
                    {
                        return true;
                    }
                 }
            	 
             }
            
     }
      //////////in the Westward direction
     for(int i = 1; i <  White_Pieces.get(4).current_x_position();++i)
     {
           if( White_Pieces.get(4).current_y_position() ==  Black_Pieces.get(0).current_y_position())   //check for the black left brook
           {
              if(White_Pieces.get(4).current_x_position()-i == Black_Pieces.get(0).current_x_position())
              {
                  if(straight_clear_path(White_Pieces.get(4),Black_Pieces.get(0).current_y_position(),Black_Pieces.get(0).current_x_position()) == true)
                    {
                         return true;
                    }   
              }
                  
             }
            if(White_Pieces.get(4).current_y_position() ==  Black_Pieces.get(7).current_y_position())   //check for the black right brook
           {
               if(White_Pieces.get(4).current_x_position()-i == Black_Pieces.get(7).current_x_position())
              {
                  if(straight_clear_path(White_Pieces.get(4),Black_Pieces.get(7).current_y_position(),Black_Pieces.get(7).current_x_position()) == true)
                    {
                         return true;
                    }   
              }
           }
             if(White_Pieces.get(4).current_y_position() ==  Black_Pieces.get(3).current_y_position()) //check for the queen 
           {
                if(White_Pieces.get(4).current_x_position()-i == Black_Pieces.get(3).current_x_position())
              {
                   if(straight_clear_path(White_Pieces.get(4),Black_Pieces.get(3).current_y_position(),Black_Pieces.get(3).current_x_position()) == true)
                    {
                         return true;
                    }   
              }
           }
           if(i == 1)
           {
        	   if(White_Pieces.get(4).current_y_position() ==  Black_Pieces.get(4).current_y_position()) //check for king 
               {
                    if(White_Pieces.get(4).current_x_position()-i == Black_Pieces.get(4).current_x_position())
                  {
                      return true;
                  }
               }
        	   
           }
             
     }  
     //////////in the Eastward direction
     for(int i = 1; i <  eastward_x;++i)
     {
           if( White_Pieces.get(4).current_y_position() ==  Black_Pieces.get(0).current_y_position())   //check for the black left rook
           {
              if(White_Pieces.get(4).current_x_position()+i == Black_Pieces.get(0).current_x_position())
              {
                   if(straight_clear_path(White_Pieces.get(4),Black_Pieces.get(0).current_y_position(),Black_Pieces.get(0).current_x_position()) == true)
                    {
                         return true;
                    }   
              }
                  
             }
            if(White_Pieces.get(4).current_y_position() ==  Black_Pieces.get(7).current_y_position())   //check for the black right rook 
           {
               if(White_Pieces.get(4).current_x_position()+i == Black_Pieces.get(7).current_x_position())
              {
                   if(straight_clear_path(White_Pieces.get(4),Black_Pieces.get(7).current_y_position(),Black_Pieces.get(7).current_x_position()) == true)
                    {
                         return true;
                    }   
              }
           }
             if(White_Pieces.get(4).current_y_position() ==  Black_Pieces.get(3).current_y_position())  //check for the queen 
           {
                if(White_Pieces.get(4).current_x_position()+i == Black_Pieces.get(3).current_x_position())
              {
                  if(straight_clear_path(White_Pieces.get(4),Black_Pieces.get(3).current_y_position(),Black_Pieces.get(3).current_x_position()) == true)
                    {
                         return true;
                    }   
              }
           }
            if(i ==1)
            {
            	 if(White_Pieces.get(4).current_y_position() ==  Black_Pieces.get(4).current_y_position())  //check for the king 
                 {
                      if(White_Pieces.get(4).current_x_position()+i == Black_Pieces.get(4).current_x_position())
                    {
                        return true;
                    }
                 }
            	
            }
            
     }

  //check for black bishop and queen   
 
  if(downward_y < White_Pieces.get(4).current_y_position())
     {
         downward_y = White_Pieces.get(4).current_y_position(); //the longest distance in y direction
     }  
  for(int i = 1; i <downward_y;++i)
    {
        //Black_Pieces.get(2) == bishop
//north-west direction
       //not handle the case there other pieces in between
                //check bishop
                if(White_Pieces.get(4).current_y_position() -i == Black_Pieces.get(2).current_y_position() &&White_Pieces.get(4).current_x_position()-i == Black_Pieces.get(2).current_x_position())
                {
                    if(diagonal_clear_path(White_Pieces.get(4),Black_Pieces.get(2).current_y_position(),Black_Pieces.get(2).current_x_position()) == true)
                    {
                         return true;
                    }  
                }
              //check bishop
                if(White_Pieces.get(4).current_y_position() -i == Black_Pieces.get(5).current_y_position() &&White_Pieces.get(4).current_x_position()-i == Black_Pieces.get(5).current_x_position())
                {
                     if(diagonal_clear_path(White_Pieces.get(4),Black_Pieces.get(5).current_y_position(),Black_Pieces.get(5).current_x_position()) == true)
                    {
                         return true;
                    }  
                }
               //check queen
              
                  if( White_Pieces.get(4).current_y_position() -i == Black_Pieces.get(3).current_y_position() && White_Pieces.get(4).current_x_position()-i == Black_Pieces.get(3).current_x_position())         
                {
                    if(diagonal_clear_path(White_Pieces.get(4),Black_Pieces.get(3).current_y_position(),Black_Pieces.get(3).current_x_position()) == true)
                    {
                         return true;
                    }  
                }
        //for the pawn and king only thecase they are 1 step aways
        if(i == 1)
        {
             
                if(
                    (White_Pieces.get(4).current_y_position() -i == Black_Pieces.get(4).current_y_position() && White_Pieces.get(4).current_x_position()-i == Black_Pieces.get(4).current_x_position())||
                    (White_Pieces.get(4).current_y_position() -i == Black_Pieces.get(8).current_y_position() )&&(White_Pieces.get(4).current_x_position()-i == Black_Pieces.get(8).current_x_position())||
                    (White_Pieces.get(4).current_y_position() -i == Black_Pieces.get(9).current_y_position() )&&(White_Pieces.get(4).current_x_position()-i == Black_Pieces.get(9).current_x_position())||
                    (White_Pieces.get(4).current_y_position() -i == Black_Pieces.get(10).current_y_position() )&&(White_Pieces.get(4).current_x_position()-i == Black_Pieces.get(10).current_x_position())||
                    (White_Pieces.get(4).current_y_position() -i == Black_Pieces.get(11).current_y_position() )&&(White_Pieces.get(4).current_x_position()-i == Black_Pieces.get(11).current_x_position())||
                    (White_Pieces.get(4).current_y_position() -i == Black_Pieces.get(12).current_y_position() )&&(White_Pieces.get(4).current_x_position()-i == Black_Pieces.get(12).current_x_position())||
                    (White_Pieces.get(4).current_y_position() -i == Black_Pieces.get(13).current_y_position() )&&(White_Pieces.get(4).current_x_position()-i == Black_Pieces.get(13).current_x_position())||
                    (White_Pieces.get(4).current_y_position() -i == Black_Pieces.get(14).current_y_position() )&&(White_Pieces.get(4).current_x_position()-i == Black_Pieces.get(14).current_x_position())||
                    (White_Pieces.get(4).current_y_position() -i == Black_Pieces.get(15).current_y_position() )&&(White_Pieces.get(4).current_x_position()-i == Black_Pieces.get(15).current_x_position())    
                  )
                { return true;}
        }

        //north-east direction
        
                if(White_Pieces.get(4).current_y_position() -i == Black_Pieces.get(2).current_y_position() &&White_Pieces.get(4).current_x_position()+i == Black_Pieces.get(2).current_x_position())
                {
                    if(diagonal_clear_path(White_Pieces.get(4),Black_Pieces.get(2).current_y_position(),Black_Pieces.get(2).current_x_position()) == true)
                    {
                         return true;
                    }  
                }
               if(White_Pieces.get(4).current_y_position() -i == Black_Pieces.get(5).current_y_position() &&White_Pieces.get(4).current_x_position()+i == Black_Pieces.get(5).current_x_position())
                   {
                      if(diagonal_clear_path(White_Pieces.get(4),Black_Pieces.get(5).current_y_position(),Black_Pieces.get(5).current_x_position()) == true)
                    {
                         return true;
                    }    
                   }
                if( White_Pieces.get(4).current_y_position() -i == Black_Pieces.get(3).current_y_position() &&White_Pieces.get(4).current_x_position()+i == Black_Pieces.get(3).current_x_position())         
                 {   
                if(diagonal_clear_path(White_Pieces.get(4),Black_Pieces.get(3).current_y_position(),Black_Pieces.get(3).current_x_position()) == true)
                    {
                         return true;
                    }
                  }
         //for the pawn and the king only thecase they are 1 step aways       
        if(i ==1)
        {
            if(
                (White_Pieces.get(4).current_y_position() -i == Black_Pieces.get(4).current_y_position() &&White_Pieces.get(4).current_x_position()+i == Black_Pieces.get(4).current_x_position())||
                (White_Pieces.get(4).current_y_position() -i == Black_Pieces.get(8).current_y_position() )&&(White_Pieces.get(4).current_x_position()+i == Black_Pieces.get(8).current_x_position())||
                (White_Pieces.get(4).current_y_position() -i == Black_Pieces.get(9).current_y_position() )&&(White_Pieces.get(4).current_x_position()+i == Black_Pieces.get(9).current_x_position())||
                (White_Pieces.get(4).current_y_position() -i == Black_Pieces.get(10).current_y_position() )&&(White_Pieces.get(4).current_x_position()+i == Black_Pieces.get(10).current_x_position())||
                (White_Pieces.get(4).current_y_position() -i == Black_Pieces.get(11).current_y_position() )&&(White_Pieces.get(4).current_x_position()+i == Black_Pieces.get(11).current_x_position())||
                (White_Pieces.get(4).current_y_position() -i == Black_Pieces.get(12).current_y_position() )&&(White_Pieces.get(4).current_x_position()+i == Black_Pieces.get(12).current_x_position())||
                (White_Pieces.get(4).current_y_position() -i == Black_Pieces.get(13).current_y_position() )&&(White_Pieces.get(4).current_x_position()+i == Black_Pieces.get(13).current_x_position())||
                (White_Pieces.get(4).current_y_position() -i == Black_Pieces.get(14).current_y_position() )&&(White_Pieces.get(4).current_x_position()+i == Black_Pieces.get(14).current_x_position())||
                (White_Pieces.get(4).current_y_position() -i == Black_Pieces.get(15).current_y_position() )&&(White_Pieces.get(4).current_x_position()+i == Black_Pieces.get(15).current_x_position())   
              )
            {return true;}
        }
 
      
    //South-east direction
         
                if(White_Pieces.get(4).current_y_position() +i == Black_Pieces.get(2).current_y_position() &&White_Pieces.get(4).current_x_position()+i == Black_Pieces.get(2).current_x_position())
                {
                    if(diagonal_clear_path(White_Pieces.get(4),Black_Pieces.get(2).current_y_position(),Black_Pieces.get(2).current_x_position()) == true)
                    {
                         return true;
                    }
                }
               if(White_Pieces.get(4).current_y_position()  +i == Black_Pieces.get(5).current_y_position() &&White_Pieces.get(4).current_x_position()+i == Black_Pieces.get(5).current_x_position())
               {
                   if(diagonal_clear_path(White_Pieces.get(4),Black_Pieces.get(5).current_y_position(),Black_Pieces.get(5).current_x_position()) == true)
                    {
                         return true;
                    }
               }
                if(White_Pieces.get(4).current_y_position() +i == Black_Pieces.get(3).current_y_position() &&White_Pieces.get(4).current_x_position()+i == Black_Pieces.get(3).current_x_position())
               
                //black pawns can not capture backward              
                 { 
                    if(diagonal_clear_path(White_Pieces.get(4),Black_Pieces.get(3).current_y_position(),Black_Pieces.get(3).current_x_position()) == true)
                    {
                         return true;
                    }
                }
        if(i == 1)
        {
            if(White_Pieces.get(4).current_y_position() +i == Black_Pieces.get(4).current_y_position() &&White_Pieces.get(4).current_x_position()+i == Black_Pieces.get(4).current_x_position())
            {return true;}
        }    
         //South-west direction
        //not handle the case there other pieces in between
                if(White_Pieces.get(4).current_y_position() +i == Black_Pieces.get(2).current_y_position() &&White_Pieces.get(4).current_x_position()-i == Black_Pieces.get(2).current_x_position())
                { 
                    if(diagonal_clear_path(White_Pieces.get(4),Black_Pieces.get(2).current_y_position(),Black_Pieces.get(2).current_x_position()) == true)
                    {
                         return true;
                    }
                }
               if(White_Pieces.get(4).current_y_position()  +i == Black_Pieces.get(5).current_y_position() &&White_Pieces.get(4).current_x_position()-i == Black_Pieces.get(5).current_x_position())
               {
                    if(diagonal_clear_path(White_Pieces.get(4),Black_Pieces.get(5).current_y_position(),Black_Pieces.get(5).current_x_position()) == true)
                    {
                         return true;
                    }
               }
                if( White_Pieces.get(4).current_y_position() +i == Black_Pieces.get(3).current_y_position() &&White_Pieces.get(4).current_x_position()-i == Black_Pieces.get(3).current_x_position())
              //black pawns can not capture backward
                 { 
                     if(diagonal_clear_path(White_Pieces.get(4),Black_Pieces.get(3).current_y_position(),Black_Pieces.get(3).current_x_position()) == true)
                    {
                         return true;
                    }
                }
        if(i == 1)
        {
            if(White_Pieces.get(4).current_y_position() +i == Black_Pieces.get(4).current_y_position() &&White_Pieces.get(4).current_x_position()-i == Black_Pieces.get(4).current_x_position())
            {return true;}
        }  
    }
//for black knight   
//2steps
     int upper_y_2steps = (White_Pieces.get(4).current_y_position()-2);//rows
      int low_y_2steps = (White_Pieces.get(4).current_y_position()+2);//rows
     int uper_x_2steps = (White_Pieces.get(4).current_x_position()+2);//columns
       int lower_x_2steps = (White_Pieces.get(4).current_x_position()-2);//columns
     //1 step
      int upper_y_1step = (White_Pieces.get(4).current_y_position()-1);//rows
      int low_y_1step = (White_Pieces.get(4).current_y_position()+1);//rows
     int uper_x_1step = (White_Pieces.get(4).current_x_position()+1);//columns
       int lower_x_1step = (White_Pieces.get(4).current_x_position()-1);//columns
//check for black_ knight   
    //north +left upper
	if(Black_Pieces.get(1).current_y_position() == upper_y_2steps&&Black_Pieces.get(1).current_x_position() == lower_x_1step)   //check black left knight
	{
		return true;
	}
  //north +left lower	
        if((Black_Pieces.get(1).current_y_position() == upper_y_1step)&&(Black_Pieces.get(1).current_x_position() == lower_x_2steps))
	{
		return true;
	}
    //north + right upper
    if((Black_Pieces.get(1).current_y_position() == upper_y_2steps)&&(Black_Pieces.get(1).current_x_position() == uper_x_1step))
	{
		return true;
	}
 //north + right lower	
    if((Black_Pieces.get(1).current_y_position() == upper_y_1step)&&(Black_Pieces.get(1).current_x_position() == uper_x_2steps))
	{
		return true;
	}
    //South +left lower
	if((Black_Pieces.get(1).current_y_position() == low_y_2steps)&&(Black_Pieces.get(1).current_x_position() == lower_x_1step))
	{
		return true;
	}
	//South +left upper
        if((Black_Pieces.get(1).current_y_position() == low_y_1step)&&(Black_Pieces.get(1).current_x_position() == lower_x_2steps))
	{
		return true;
	}
    //South + right lower
    if((Black_Pieces.get(1).current_y_position() == low_y_2steps)&&(Black_Pieces.get(1).current_x_position() == uper_x_1step))
	{
		return true;
	}
	if((Black_Pieces.get(1).current_y_position() == low_y_1step)&&(Black_Pieces.get(1).current_x_position() == uper_x_2steps))
	{
		return true;
	}  
        //check for black_right_ knight
        //north +left
	if((Black_Pieces.get(6).current_y_position() == upper_y_2steps)&&(Black_Pieces.get(6).current_x_position() == lower_x_1step))    //check for black right knight 
	{
		return true;
	}
	if((Black_Pieces.get(6).current_y_position() == upper_y_1step)&&(Black_Pieces.get(6).current_x_position() == lower_x_2steps))
	{
		return true;
	}
    //north + right upper
    if((Black_Pieces.get(6).current_y_position() == upper_y_2steps)&&(Black_Pieces.get(6).current_x_position() == uper_x_1step))
	{
		return true;
	}
//north + right lower	
    if((Black_Pieces.get(6).current_y_position() == upper_y_1step)&&(Black_Pieces.get(6).current_x_position() == uper_x_2steps))
	{
		return true;
	}
    //South +left
	if((Black_Pieces.get(6).current_y_position() == low_y_2steps)&&(Black_Pieces.get(6).current_x_position() == lower_x_1step))
	{
		return true;
	}
	if((Black_Pieces.get(6).current_y_position() == low_y_1step)&&(Black_Pieces.get(6).current_x_position() == lower_x_2steps))
	{
		return true;
	}
    //South + right 
    if((Black_Pieces.get(6).current_y_position() == low_y_2steps)&&(Black_Pieces.get(6).current_x_position() == uper_x_1step))
	{
		return true;
	}
	if((Black_Pieces.get(6).current_y_position() == low_y_1step)&&(Black_Pieces.get(6).current_x_position() == uper_x_2steps))
	{
		return true;
	}  
        
        
     return false;
 }
private boolean black_king_checkmate()
 {
    //the distance travel doward to the edge
     int downward_y = 8 - Black_Pieces.get(4).current_y_position();
   
      //distance travel eastward to the edge
     int eastward_x = 8 - Black_Pieces.get(4).current_x_position();
  
    
     //////////in the upward direction
     for(int i = 1; i <  Black_Pieces.get(4).current_y_position();++i)
     {
           if( Black_Pieces.get(4).current_x_position() ==  White_Pieces.get(0).current_x_position()) //white left rook
           {
              if(Black_Pieces.get(4).current_y_position()-i == White_Pieces.get(0).current_y_position())
              {
                   if(straight_clear_path(Black_Pieces.get(4),White_Pieces.get(0).current_y_position(),White_Pieces.get(0).current_x_position()) == true)
                    {
                         return true;
                    }   
              }
                  
             }
            if(Black_Pieces.get(4).current_x_position() ==  White_Pieces.get(7).current_x_position())    //white right rook
           {
               if(Black_Pieces.get(4).current_y_position()-i == White_Pieces.get(7).current_y_position())
              {
                  if(straight_clear_path(Black_Pieces.get(4),White_Pieces.get(7).current_y_position(),White_Pieces.get(7).current_x_position()) == true)
                    {
                         return true;
                    } 
              }
           }
             if(Black_Pieces.get(4).current_x_position() ==  White_Pieces.get(3).current_x_position())  //white queen
           {
                if(Black_Pieces.get(4).current_y_position()-i == White_Pieces.get(3).current_y_position())
              {
                  if(straight_clear_path(Black_Pieces.get(4),White_Pieces.get(3).current_y_position(),White_Pieces.get(3).current_x_position()) == true)
                    {
                         return true;
                    } 
              }
           }
             if(i == 1)
             {
                   if(Black_Pieces.get(4).current_x_position() ==  White_Pieces.get(4).current_x_position())
                     {
                      if(Black_Pieces.get(4).current_y_position()-i == White_Pieces.get(4).current_y_position())
                         {
                               return true;
                         }
                     }
             }
           
     }
       //////////in the downward direction
     for(int i = 1; i <  downward_y ;++i)
     {
           if( Black_Pieces.get(4).current_x_position() ==  White_Pieces.get(0).current_x_position())     //white left rook
           {
              if(Black_Pieces.get(4).current_y_position()+i == White_Pieces.get(0).current_y_position())
              {
                   if(straight_clear_path(Black_Pieces.get(4),White_Pieces.get(0).current_y_position(),White_Pieces.get(0).current_x_position()) == true)
                    {
                         return true;
                    }   
              }
                  
             }
            if(Black_Pieces.get(4).current_x_position() ==  White_Pieces.get(7).current_x_position())
           {
               if(Black_Pieces.get(4).current_y_position()+i == White_Pieces.get(7).current_y_position())
              {
                   if(straight_clear_path(Black_Pieces.get(4),White_Pieces.get(7).current_y_position(),White_Pieces.get(7).current_x_position()) == true)
                    {
                         return true;
                    }   
              }
           }
             if(Black_Pieces.get(4).current_x_position() ==  White_Pieces.get(3).current_x_position())
           {
                if(Black_Pieces.get(4).current_y_position()+i == White_Pieces.get(3).current_y_position())
              {
                   if(straight_clear_path(Black_Pieces.get(4),White_Pieces.get(3).current_y_position(),White_Pieces.get(3).current_x_position()) == true)
                    {
                         return true;
                    }   
              }
           }
           if(i == 1)
           {
               
             if(Black_Pieces.get(4).current_x_position() ==  White_Pieces.get(4).current_x_position())
           {
                if(Black_Pieces.get(4).current_y_position()+i == White_Pieces.get(4).current_y_position())
              {
                  return true;
              }
           }
           }
             
     }
      //////////in the Westward direction
     for(int i = 1; i <  Black_Pieces.get(4).current_x_position();++i)
     {
           if( Black_Pieces.get(4).current_y_position() ==  White_Pieces.get(0).current_y_position())
           {
              if(Black_Pieces.get(4).current_x_position()-i == White_Pieces.get(0).current_x_position())
              {
                   if(straight_clear_path(Black_Pieces.get(4),White_Pieces.get(0).current_y_position(),White_Pieces.get(0).current_x_position()) == true)
                    {
                         return true;
                    }   
              }
                  
             }
            if(Black_Pieces.get(4).current_y_position() ==  White_Pieces.get(7).current_y_position())
           {
               if(Black_Pieces.get(4).current_x_position()-i == White_Pieces.get(7).current_x_position())
              {
                   if(straight_clear_path(Black_Pieces.get(4),White_Pieces.get(7).current_y_position(),White_Pieces.get(7).current_x_position()) == true)
                    {
                         return true;
                    }   
              }
           }
             if(Black_Pieces.get(4).current_y_position() ==  White_Pieces.get(3).current_y_position())
           {
                if(Black_Pieces.get(4).current_x_position()-i == White_Pieces.get(3).current_x_position())
              {
                   if(straight_clear_path(Black_Pieces.get(4),White_Pieces.get(3).current_y_position(),White_Pieces.get(3).current_x_position()) == true)
                    {
                         return true;
                    }   
              }
           }
           if(i ==1)
           {
                if(Black_Pieces.get(4).current_y_position() ==  White_Pieces.get(4).current_y_position())
           {
                if(Black_Pieces.get(4).current_x_position()-i == White_Pieces.get(4).current_x_position())
              {
                  return true;
              }
           }
           }
            
     }  
     //////////in the Eastward direction
     for(int i = 1; i <  eastward_x;++i)
     {
           if( Black_Pieces.get(4).current_y_position() ==  White_Pieces.get(0).current_y_position())
           {
              if(Black_Pieces.get(4).current_x_position()+i == White_Pieces.get(0).current_x_position())
              {
                   if(straight_clear_path(Black_Pieces.get(4),White_Pieces.get(0).current_y_position(),White_Pieces.get(0).current_x_position()) == true)
                    {
                         return true;
                    }   
              }
                  
             }
            if(Black_Pieces.get(4).current_y_position() ==  White_Pieces.get(7).current_y_position())
           {
               if(Black_Pieces.get(4).current_x_position()+i == White_Pieces.get(7).current_x_position())
              {
                   if(straight_clear_path(Black_Pieces.get(4),White_Pieces.get(7).current_y_position(),White_Pieces.get(7).current_x_position()) == true)
                    {
                         return true;
                    }   
              }
           }
             if(Black_Pieces.get(4).current_y_position() ==  White_Pieces.get(3).current_y_position())
           {
                if(Black_Pieces.get(4).current_x_position()+i == White_Pieces.get(3).current_x_position())
              {
                   if(straight_clear_path(Black_Pieces.get(4),White_Pieces.get(3).current_y_position(),White_Pieces.get(3).current_x_position()) == true)
                    {
                         return true;
                    }   
              }
           }
           if(i == 1)
           {
               if(Black_Pieces.get(4).current_y_position() ==  White_Pieces.get(4).current_y_position())
           {
                if(Black_Pieces.get(4).current_x_position()+i == White_Pieces.get(4).current_x_position())
              {
                  return true;
              }
           }
           }
             
     }

  //check for white bishop and queen   
 
  if(downward_y < Black_Pieces.get(4).current_y_position())
     {
         downward_y = Black_Pieces.get(4).current_y_position(); //the longest distance in y direction
     }  
  for(int i = 1; i <downward_y;++i)
    {
       //north-west direction
        //not handle the case there other pieces in between
                if(Black_Pieces.get(4).current_y_position() -i == White_Pieces.get(2).current_y_position() &&Black_Pieces.get(4).current_x_position()-i == White_Pieces.get(2).current_x_position())
                {
                    if(diagonal_clear_path(Black_Pieces.get(4),White_Pieces.get(2).current_y_position(),White_Pieces.get(2).current_x_position()) == true)
                    {
                         return true;
                    }
                }
               if(Black_Pieces.get(4).current_y_position() -i == White_Pieces.get(5).current_y_position() &&Black_Pieces.get(4).current_x_position()-i == White_Pieces.get(5).current_x_position())
               {
                    if(diagonal_clear_path(Black_Pieces.get(4),White_Pieces.get(5).current_y_position(),White_Pieces.get(5).current_x_position()) == true)
                    {
                         return true;
                    }
               }
                if(Black_Pieces.get(4).current_y_position() -i == White_Pieces.get(3).current_y_position() &&Black_Pieces.get(4).current_x_position()-i == White_Pieces.get(3).current_x_position()               
                //white pawn can not capture backward
                )
                { 
                     if(diagonal_clear_path(Black_Pieces.get(4),White_Pieces.get(3).current_y_position(),White_Pieces.get(3).current_x_position()) == true)
                    {
                         return true;
                    }
                }
        if(i ==1)
        {
            if(Black_Pieces.get(4).current_y_position() -i == White_Pieces.get(4).current_y_position() &&Black_Pieces.get(4).current_x_position()-i == White_Pieces.get(4).current_x_position())
            { return true;}
        }
        //north-east direction
       
                if(Black_Pieces.get(4).current_y_position() -i == White_Pieces.get(2).current_y_position() &&Black_Pieces.get(4).current_x_position()+i == White_Pieces.get(2).current_x_position())
                {
                     if(diagonal_clear_path(Black_Pieces.get(4),White_Pieces.get(2).current_y_position(),White_Pieces.get(2).current_x_position()) == true)
                    {
                         return true;
                    }
                }
               if(Black_Pieces.get(4).current_y_position() -i == White_Pieces.get(5).current_y_position() &&Black_Pieces.get(4).current_x_position()+i == White_Pieces.get(5).current_x_position())
               {
                    if(diagonal_clear_path(Black_Pieces.get(4),White_Pieces.get(5).current_y_position(),White_Pieces.get(5).current_x_position()) == true)
                    {
                         return true;
                    }
               }
                if(Black_Pieces.get(4).current_y_position() -i == White_Pieces.get(3).current_y_position() &&Black_Pieces.get(4).current_x_position()+i == White_Pieces.get(3).current_x_position()
           )
                 {  if(diagonal_clear_path(Black_Pieces.get(4),White_Pieces.get(3).current_y_position(),White_Pieces.get(3).current_x_position()) == true)
                    {
                         return true;
                    }
                 }
        if(i ==1)
        {
            if(Black_Pieces.get(4).current_y_position() -i == White_Pieces.get(4).current_y_position() &&Black_Pieces.get(4).current_x_position()+i == White_Pieces.get(4).current_x_position())
            { return true;}
        }
          //South-east direction
        
                if(Black_Pieces.get(4).current_y_position() +i == White_Pieces.get(2).current_y_position() &&Black_Pieces.get(4).current_x_position()+i == White_Pieces.get(2).current_x_position())
                {
                     if(diagonal_clear_path(Black_Pieces.get(4),White_Pieces.get(2).current_y_position(),White_Pieces.get(2).current_x_position()) == true)
                    {
                         return true;
                    }
                }
               if(Black_Pieces.get(4).current_y_position()  +i == White_Pieces.get(5).current_y_position() &&Black_Pieces.get(4).current_x_position()+i == White_Pieces.get(5).current_x_position())
               {
                    if(diagonal_clear_path(Black_Pieces.get(4),White_Pieces.get(5).current_y_position(),White_Pieces.get(5).current_x_position()) == true)
                    {
                         return true;
                    }
               }
                if(Black_Pieces.get(4).current_y_position() +i == White_Pieces.get(3).current_y_position() &&Black_Pieces.get(4).current_x_position()+i == White_Pieces.get(3).current_x_position())               
                { 
                     if(diagonal_clear_path(Black_Pieces.get(4),White_Pieces.get(3).current_y_position(),White_Pieces.get(3).current_x_position()) == true)
                    {
                         return true;
                    }
                }
            //check for the pawn1 and king in 1 step away only  
            if(i == 1)
            {
                if(      
                (Black_Pieces.get(4).current_y_position()  +i == White_Pieces.get(8).current_y_position() && Black_Pieces.get(4).current_x_position()+i == White_Pieces.get(8).current_x_position())||
                (Black_Pieces.get(4).current_y_position()  +i == White_Pieces.get(9).current_y_position() && Black_Pieces.get(4).current_x_position()+i == White_Pieces.get(9).current_x_position())||
                (Black_Pieces.get(4).current_y_position()  +i == White_Pieces.get(10).current_y_position() && Black_Pieces.get(4).current_x_position()+i == White_Pieces.get(10).current_x_position())||
                (Black_Pieces.get(4).current_y_position()  +i == White_Pieces.get(11).current_y_position() &&Black_Pieces.get(4).current_x_position()+i == White_Pieces.get(11).current_x_position())||
                (Black_Pieces.get(4).current_y_position()  +i == White_Pieces.get(12).current_y_position() &&Black_Pieces.get(4).current_x_position()+i == White_Pieces.get(12).current_x_position())||
                (Black_Pieces.get(4).current_y_position()  +i == White_Pieces.get(13).current_y_position() &&Black_Pieces.get(4).current_x_position()+i == White_Pieces.get(13).current_x_position())||
                (Black_Pieces.get(4).current_y_position()  +i == White_Pieces.get(14).current_y_position() &&Black_Pieces.get(4).current_x_position()+i == White_Pieces.get(14).current_x_position())||
                (Black_Pieces.get(4).current_y_position()  +i == White_Pieces.get(15).current_y_position() &&Black_Pieces.get(4).current_x_position()+i == White_Pieces.get(15).current_x_position())||
                (Black_Pieces.get(4).current_y_position() +i == White_Pieces.get(4).current_y_position() )&&(Black_Pieces.get(4).current_x_position()+i == White_Pieces.get(4).current_x_position())
                )
        { return true;}
            }
        
         //South-west direction
        if( //not handle the case there other pieces in between
                (Black_Pieces.get(4).current_y_position() +i == White_Pieces.get(2).current_y_position() &&Black_Pieces.get(4).current_x_position()-i == White_Pieces.get(2).current_x_position())||
               (Black_Pieces.get(4).current_y_position()  +i == White_Pieces.get(5).current_y_position() &&Black_Pieces.get(4).current_x_position()-i == White_Pieces.get(5).current_x_position())||
                (Black_Pieces.get(4).current_y_position() +i == White_Pieces.get(3).current_y_position() &&Black_Pieces.get(4).current_x_position()-i == White_Pieces.get(3).current_x_position())
          )     
            { return true;}
        //check for the pawn1 in 1 step away only
            if(i == 1)
            {
                  if(
                   (Black_Pieces.get(4).current_y_position()  +i == White_Pieces.get(8).current_y_position() &&Black_Pieces.get(4).current_x_position()-i == White_Pieces.get(8).current_x_position())||
                (Black_Pieces.get(4).current_y_position()  +i == White_Pieces.get(9).current_y_position() &&Black_Pieces.get(4).current_x_position()-i == White_Pieces.get(9).current_x_position())||
                (Black_Pieces.get(4).current_y_position()  +i == White_Pieces.get(10).current_y_position() &&Black_Pieces.get(4).current_x_position()-i == White_Pieces.get(10).current_x_position())||
                (Black_Pieces.get(4).current_y_position()  +i == White_Pieces.get(11).current_y_position() &&Black_Pieces.get(4).current_x_position()-i == White_Pieces.get(11).current_x_position())||
                (Black_Pieces.get(4).current_y_position()  +i == White_Pieces.get(12).current_y_position() &&Black_Pieces.get(4).current_x_position()-i == White_Pieces.get(12).current_x_position())||
                (Black_Pieces.get(4).current_y_position()  +i == White_Pieces.get(13).current_y_position() &&Black_Pieces.get(4).current_x_position()-i == White_Pieces.get(13).current_x_position())||
                (Black_Pieces.get(4).current_y_position()  +i == White_Pieces.get(14).current_y_position() &&Black_Pieces.get(4).current_x_position()-i == White_Pieces.get(14).current_x_position())||
                (Black_Pieces.get(4).current_y_position()  +i == White_Pieces.get(15).current_y_position() &&Black_Pieces.get(4).current_x_position()-i == White_Pieces.get(15).current_x_position())||
                (Black_Pieces.get(4).current_y_position() +i == White_Pieces.get(4).current_y_position() &&Black_Pieces.get(4).current_x_position()-i == White_Pieces.get(4).current_x_position())
               )
               
        { return true;}
            
            }
              
    }
  
  //for black_left_ knight   
//2steps
     int upper_y_2steps = (Black_Pieces.get(4).current_y_position()-2);//rows
      int low_y_2steps = (Black_Pieces.get(4).current_y_position()+2);//rows
     int uper_x_2steps = (Black_Pieces.get(4).current_x_position()+2);//columns
       int lower_x_2steps = (Black_Pieces.get(4).current_x_position()-2);//columns
     //1 step
      int upper_y_1step = (Black_Pieces.get(4).current_y_position()-1);//rows
      int low_y_1step = (Black_Pieces.get(4).current_y_position()+1);//rows
     int uper_x_1step = (Black_Pieces.get(4).current_x_position()+1);//columns
       int lower_x_1step = (Black_Pieces.get(4).current_x_position()-1);//columns
//check for white_left_ knight   
    //north +left
	if((White_Pieces.get(1).current_y_position() == upper_y_2steps)&&(White_Pieces.get(1).current_x_position() == lower_x_1step))   //white left knight 
	{
		return true;
	}
	if((White_Pieces.get(1).current_y_position() == upper_y_1step)&&(White_Pieces.get(1).current_x_position() == lower_x_2steps))
	{
		return true;
	}
    //north + right 
    if((White_Pieces.get(1).current_y_position() == upper_y_2steps)&&(White_Pieces.get(1).current_x_position() == uper_x_1step))
	{
		return true;
	}
	if((White_Pieces.get(1).current_y_position() == upper_y_1step)&&(White_Pieces.get(1).current_x_position() == uper_x_2steps))
	{
		return true;
	}
    //South +left
	if((White_Pieces.get(1).current_y_position() == low_y_2steps)&&(White_Pieces.get(1).current_x_position() == lower_x_1step))
	{
		return true;
	}
	if((White_Pieces.get(1).current_y_position() == low_y_1step)&&(White_Pieces.get(1).current_x_position() == lower_x_2steps))
	{
		return true;
	}
    //South + right 
    if((White_Pieces.get(1).current_y_position() == low_y_2steps)&&(White_Pieces.get(1).current_x_position() == uper_x_1step))
	{
		return true;
	}
	if((White_Pieces.get(1).current_y_position() == low_y_1step)&&(White_Pieces.get(1).current_x_position() == uper_x_2steps))
	{
		return true;
	}  
        //check for black_right_ knight
        //north +left
	if((White_Pieces.get(6).current_y_position() == upper_y_2steps)&&(White_Pieces.get(6).current_x_position() == lower_x_1step)) //white right knight
	{
		return true;
	}
	if((White_Pieces.get(6).current_y_position() == upper_y_1step)&&(White_Pieces.get(6).current_x_position() == lower_x_2steps))
	{
		return true;
	}
    //north + right 
    if((White_Pieces.get(6).current_y_position() == upper_y_2steps)&&(White_Pieces.get(6).current_x_position() == uper_x_1step))
	{
		return true;
	}
	if((White_Pieces.get(6).current_y_position() == upper_y_1step)&&(White_Pieces.get(6).current_x_position() == uper_x_2steps))
	{
		return true;
	}
    //South +left
	if((White_Pieces.get(6).current_y_position() == low_y_2steps)&&(White_Pieces.get(6).current_x_position() == lower_x_1step))
	{
		return true;
	}
	if((White_Pieces.get(6).current_y_position() == low_y_1step)&&(White_Pieces.get(6).current_x_position() == lower_x_2steps))
	{
		return true;
	}
    //South + right 
    if((White_Pieces.get(6).current_y_position() == low_y_2steps)&&(White_Pieces.get(6).current_x_position() == uper_x_1step))
	{
		return true;
	}
	if((White_Pieces.get(6).current_y_position() == low_y_1step)&&(White_Pieces.get(6).current_x_position() == uper_x_2steps))
	{
		return true;
	}  
        
        
     return false;
 }
   //ckeck if there another piece in the straight path 
private boolean straight_clear_path(Piece e,int y, int x)
   {
        //the case there some chess pieces in the path-way
         
                for(int i = 1; i < 8; ++i)
                {
                    if(e.current_x_position() == x)
                    {
                          //south-path
                               if((e.current_y_position() < i)&&( i < y) )
                                {
                                   if((color_array[i][x] == 1)||(color_array[i][x] == 2))
                                   {
                                       return false;
                                   }
                                }
                              //North-path
                               if((y < i)&&(i < e.current_y_position() ) )
                                {
                                   if((color_array[i][x] == 1)||(color_array[i][x] == 2))
                                   {
                                       return false;
                                   }
                                }
                    }
                  
                }
                 for(int i = 1; i < 8; ++i)
                {
                    if(e.current_y_position() == y)
                    {
                        //East-path
                               if((e.current_x_position() < i)&&( i < x) )
                                {
                                   if((color_array[y][i] == 1)||(color_array[y][i] == 2))
                                   {
                                       return false;
                                   }
                                }
                              //West-path
                               if((x < i)&&(i < e.current_x_position() ) )
                                {
                                   if((color_array[y][i] == 1)||(color_array[y][i] == 2))
                                   {
                                       return false;
                                   }
                                }
                    }

                }
       return true; 
   }
    //ckeck if there another piece in the diagonal path 
private boolean diagonal_clear_path(Piece e, int y, int x)
   {
         //if there other chess pieces in the path
             
              int distance = Math.abs(y-e.current_y_position()); //square distance
             int y_distance = y - e.current_y_position();
             int x_distance = x - e.current_x_position();
             if(y_distance < 0)
             {
                 if(x_distance <0)
                 {
                 //move north-west
                     int tempX = x;
                     int tempY = y;
                     for(int i = 1; i< distance; ++i)
                     {
                         if(( color_array[tempY+i][tempX+i] == 1)||( color_array[tempY+i][tempX+i] == 2))
                         {
                             return false;
                         }
                     }
                 }else
                 {
                     //move north-East
                     int tempX = x;
                     int tempY = y;
                     for(int i = 1; i< distance; ++i)
                     {
                         if(( color_array[tempY + i][tempX-i] == 1)||( color_array[tempY+i][tempX-i] == 2))
                         {
                             return false;
                         }
                 }
             }
            }else
             {
                 if(x_distance <0)
                 {
                     //Move South-West
                      int tempX = x;
                     int tempY = y;
                     for(int i = 1; i< distance; ++i)
                     {
                         if(( color_array[tempY-i][tempX+i] == 1)||( color_array[tempY-i][tempX+i] == 2))
                         {
                             return false;
                         }
                     }
                 }else
                 {
                     //Move south-East
                      int tempX = x;
                     int tempY = y;
                     for(int i = 1; i< distance; ++i)
                     {
                         if(( color_array[tempY-i][tempX-i] == 1)||( color_array[tempY-i][tempX-i] == 2))
                         {
                             return false;
                         }
                     }
                 }
             }
       return true; 
   }

 //check if the black king is presented on the board   
  //if white king is presented return true
  //otherwise return fasle               
private boolean check_black_king()
{
   Set<Map.Entry<JButton,Piece>> set = map.entrySet();
   for(Map.Entry<JButton,Piece> map_element:set)
   {
       if(map_element.getValue().getName() == "Black_King")
       {
           return true;
       }
   }
    return false;
}
 //check if the white king is presented on the board   
  // if the white king exists ,return true, else return false 
private boolean check_white_king()
{
    Set<Map.Entry<JButton,Piece>> set = map.entrySet();
   for(Map.Entry<JButton,Piece> map_element:set)
   {
       if(map_element.getValue().getName() == "White_King")
       {
           return true;
       }
   }
    return false;
}   
 
//==========================================================================================
   @Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
	
		//buttons handling
		if(source == play)
		{
			cardLayout.show(getContentPane(),"gamePanel");
		}
		if( source == quit)
		{
			super.dispose();
		}

 //check for the black king ,if white king is gone , black win
                if(check_black_king() == false )
                    {
                       cardLayout.show(getContentPane(),"wLabel");
                    }
    //check for the white king ,if white king is gone , black win
                 if(check_white_king() == false )
                    {  
                       cardLayout.show(getContentPane(),"bLabel");
                    }      

                 if(white_king_checkmate() == true)
                         {                                                     
                            topLabel.setText("White King's Checkmated!");                                                    
                          }   
                 if(black_king_checkmate() == true)
                       {                    
                         topLabel.setText("Black King's Checkmated!");
                       }
//chess moving handling,check for every button
		for(int i = 0; i < 8; ++i)
		{
			for(int j = 0; j<8 ; ++j )
			{
				if(source == tile[i][j])
                                {
                                        
                  //=========/white chess turn  ============
                                    if(turn % 2 == 0)        //white chess turn
                                   {
                                      
                                        //if user not click white
                                        if((color_array[i][j] != 1)&&(current_chess == 20))
                                       {
                                        
                                           bottomLabel.setText("You must select the white chess ");  
                                         return;
                                       }
                                        else // user select the white chess
                                        {
                                           topLabel.setText(" White turn finished, black turn next");
                                            bottomLabel.setText("Move the Piece! "); 
                                            if(current_chess == 20)   //first click is for chess selection
                                           {
                                            //--dertermine which white chess is picked
                                             for(int index = 0; index <White_Pieces.size();++index )   
                                             {
                                                 if((White_Pieces.get(index).current_y_position() == i)&&(White_Pieces.get(index).current_x_position() == j))
                                                 {
                                                     current_chess = index;
     /////////////display the picked piece===========================                                                
                                                     topLabel.setText(" it is "+White_Pieces.get(current_chess).getName());
                                                 }
                                             }
                                            
                                            }//end of first click(the pick)
                                       
                                  
                                            else if(current_chess != 20)   //second click is for making move
                                          {
                                             
                //we already know which chess
                                           if( White_Pieces.get(current_chess).getName()=="left_Rook" ||White_Pieces.get(current_chess).getName()=="right_Rook"  )
                                           {
                                                if(white_rook_move(i,j)== true)
                                                        {
                                                            current_chess = 20; turn++;
                                                        }
                                                         else // not an valid move was made continue the current's turn 
                                                          { invalid_move_message(); }  
                                                 
                                           }
                                             if( White_Pieces.get(current_chess).getName()=="left_Bishop" ||White_Pieces.get(current_chess).getName()=="right_Bishop"  )
                                           {
                                                if(white_bishop_move(i,j)== true)
                                                        {
                                                            current_chess = 20; turn++;
                                                        }
                                                         else // not an valid move was made continue the current's turn 
                                                          { invalid_move_message(); } 
                                                
                                           }
                                            if( White_Pieces.get(current_chess).getName()=="left_Knight" ||White_Pieces.get(current_chess).getName()=="right_Knight"  )
                                           {
                                                if(white_knight_move(i,j)== true)
                                                        {
                                                            current_chess = 20; turn++;
                                                        }
                                                         else // not an valid move was made continue the current's turn 
                                                          { invalid_move_message(); } 
                                                
                                           }
                                             if( White_Pieces.get(current_chess).getName()=="Queen"  )
                                           {
                                                 if(white_queen_move(i,j)== true)
                                                        {
                                                            current_chess = 20; turn++;
                                                        }
                                                         else // not an valid move was made continue the current's turn 
                                                          { invalid_move_message(); } 
                                                  
                                           }
                                              if( White_Pieces.get(current_chess).getName()=="White_King"  )
                                           {
                                                  if(white_king_move(i,j)== true)
                                                        {
                                                            current_chess = 20; turn++;
                                                        }
                                                         else // not an valid move was made continue the current's turn 
                                                          { invalid_move_message(); }   
                                                  
                                           }
                                              if( White_Pieces.get(current_chess).getName()=="Pawn_1" ||White_Pieces.get(current_chess).getName()=="Pawn_2" 
                                                      ||White_Pieces.get(current_chess).getName()=="Pawn_3" ||White_Pieces.get(current_chess).getName()=="Pawn_4" 
                                                    ||  White_Pieces.get(current_chess).getName()=="Pawn_5" ||White_Pieces.get(current_chess).getName()=="Pawn_6"
                                                      ||White_Pieces.get(current_chess).getName()=="Pawn_7" ||White_Pieces.get(current_chess).getName()=="Pawn_8" )
                                           {
                                                  if(white_pawn_move(i,j)== true)
                                                        {
                                                            current_chess = 20; turn++;
                                                        }
                                                         else // not an valid move was made continue the current's turn 
                                                          { invalid_move_message(); } 
                                                  
                                           } 
                                            
                                              
                                             
                                            }//end of second click (the move)
                                                                                                                                                                                   
                                           
                                       } //end of user select white chess
                                      }   //end of white chess turn
                                 
              //============ //black chess turn===========                   
                                    else    
                                   {
                                       
                                                                          
                                       
                                    //if user not click white
                                        if((color_array[i][j] != 2)&&(current_chess == 20))
                                       {
                                        
                                           bottomLabel.setText("You must select the black chess ");  
                                         return;
                                       }
                                        else // user select the black chess
                                        {
                                           topLabel.setText(" Black turn finished, white turn next");
                                            bottomLabel.setText("Move the Piece!  "); 
                                         if(current_chess == 20)   //first click is for chess selection
                                           {
                                            //--dertermine which white chess is picked
                                            for(int index = 0; index <Black_Pieces.size();++index )   
                                             {
                                                 if((Black_Pieces.get(index).current_y_position() == i)&&(Black_Pieces.get(index).current_x_position() == j))
                                                 {
                                                     current_chess = index;
                                                      topLabel.setText(" it is "+Black_Pieces.get(current_chess).getName());
                                                 }
                                             }
                                          }//end of first click
                                       
                                  
                                            else if(current_chess != 20)   //second click is for making move
                                          {
                                             //we already know which chess
                                           if( Black_Pieces.get(current_chess).getName()=="left_Rook" ||Black_Pieces.get(current_chess).getName()=="right_Rook"  )
                                           {
                                                if(black_rook_move(i,j)== true)
                                                        {
                                                            current_chess = 20; turn++;
                                                        }
                                                         else // not an valid move was made continue the current's turn 
                                                          { invalid_move_message(); } 
                                                 
                                           }
                                             if( Black_Pieces.get(current_chess).getName()=="left_Bishop" ||Black_Pieces.get(current_chess).getName()=="right_Bishop"  )
                                           {
                                                if(black_bishop_move(i,j)== true)
                                                        {
                                                            current_chess = 20; turn++;
                                                        }
                                                         else // not an valid move was made continue the current's turn 
                                                          { invalid_move_message(); } 
                                                 
                                           }
                                            if( Black_Pieces.get(current_chess).getName()=="left_Knight" ||Black_Pieces.get(current_chess).getName()=="right_Knight"  )
                                           {
                                                if(black_knight_move(i,j)== true)
                                                        {
                                                            current_chess = 20; turn++;
                                                        }
                                                         else // not an valid move was made continue the current's turn 
                                                          { invalid_move_message(); } 
                                                 
                                           }
                                             if( Black_Pieces.get(current_chess).getName()=="Queen"  )
                                           {
                                                 if(black_queen_move(i,j)== true)
                                                        {
                                                            current_chess = 20; turn++;
                                                        }
                                                         else // not an valid move was made continue the current's turn 
                                                          { invalid_move_message(); }  
                                                 
                                           }
                                              if( Black_Pieces.get(current_chess).getName()=="Black_King"  )
                                           {
                                                  if(black_king_move(i,j)== true)
                                                        {
                                                            current_chess = 20; turn++;
                                                        }
                                                         else // not an valid move was made continue the current's turn 
                                                          { invalid_move_message(); } 
                                                  
                                           }
                                              if( Black_Pieces.get(current_chess).getName()=="Pawn_1" ||Black_Pieces.get(current_chess).getName()=="Pawn_2" 
                                                      ||Black_Pieces.get(current_chess).getName()=="Pawn_3" ||Black_Pieces.get(current_chess).getName()=="Pawn_4" 
                                                    ||  Black_Pieces.get(current_chess).getName()=="Pawn_5" ||Black_Pieces.get(current_chess).getName()=="Pawn_6"
                                                      ||Black_Pieces.get(current_chess).getName()=="Pawn_7" ||Black_Pieces.get(current_chess).getName()=="Pawn_8" )
                                           {
                                                  if(black_pawn_move(i,j)== true)
                                                        {
                                                            current_chess = 20; turn++;
                                                        }
                                                         else // not an valid move was made continue the current's turn 
                                                          { invalid_move_message(); } 
                                                  
                                           } 
                                                       
                                             
                                           }//endl of second click 
                                        
                                        }
                                      
                                   
                                   }//end of black turn
    
                                }//end of tile check
                   }//end of column loop
               } //end of row loop                 
   
                                    
        }//end of actionlistener
}
