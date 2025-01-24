import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int boardheight=800;
    int boardwidth=700;//for the text on top and displaying winner

    JFrame frame =new JFrame("Tic-Tac-Toe");
    JLabel textLabel =new JLabel();
    JPanel textPanel=new JPanel();
    JPanel boardPanel =new JPanel();
    JPanel resetButton=new JPanel();
    JButton reset =new JButton("Reset");
    JButton board[][]=new JButton[3][3];
    String playerX="X";
    String playerO="O";
    String currentPlayer = playerX;

    boolean gameOver=false;
    int turns=0;

    TicTacToe(){
        frame.setSize(boardwidth,boardheight);
        frame.setLocationRelativeTo(null);//open window to center of the screen
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());


        resetButton.setBackground(Color.darkGray);
        resetButton.setLayout(null);
        resetButton.setPreferredSize(new Dimension(100,100));
        //resetButton
        reset.setBounds(300,25,100,50);
        reset.setBackground(Color.red);
        reset.setForeground(Color.BLACK);
        reset.setFont(new Font("Arial",Font.BOLD,20));
        resetButton.add(reset);

        

        //textlabel decoration which is added to the top
        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial",Font.BOLD,50));//name,bold,size of text
        textLabel.setHorizontalAlignment(JLabel.CENTER);//center the text
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);
        //add component to panel for the text which is added to the top
        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        
        //for the board we add another panel
        //gridalayout for 3x3 matrix 
        boardPanel.setLayout(new GridLayout(3,3));//3x3 array
        boardPanel.setBackground(Color.darkGray);
        //boardPanel.setPreferredSize(new Dimension(100,100));
        frame.add(boardPanel,BorderLayout.CENTER);
        frame.add(resetButton,BorderLayout.SOUTH);
        //border layout add to the north
        frame.add(textPanel,BorderLayout.NORTH);


        //buttons in 3x3 matrix 
        //all buttons have same work so we use array of buttons
        for(int r=0;r<3;r++){
            for(int c=0;c<3;c++){
                //we name each button as tile for easy use
                JButton tile=new JButton();
                board[r][c]=tile;
                boardPanel.add(tile);
                //add decoration to the buttons
                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial",Font.BOLD,120));
                tile.setFocusable(false);
                //tile.setText(currentPlayer);
                tile.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        if(gameOver) return;
                        JButton tile=(JButton)e.getSource();
                        if(tile.getText()==""){
                            tile.setText(currentPlayer);
                            //turns will be only 9
                            turns++;
                            checkWinner();
                            if(!gameOver){
                                currentPlayer=currentPlayer==playerX ? playerO :playerX;
                                /*
                                if(currentplayer==playerX){
                                currentPlayer=playerO;
                                else
                                currentPlayer=playerX;
                                } */
                                textLabel.setText(currentPlayer+"'s Turn.");  
                            }
                            
                        }
                        


                    }
                });
            }
        }
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                while(turns!=0){
                    textLabel.setText("Tic-Tac-Toe");
                    turns=0;
                    gameOver=false;
                    for(int r=0;r<3;r++){
                        for(int c=0;c<3;c++){
                            board[r][c].setText("");
                            board[r][c].setForeground(Color.WHITE);
                            board[r][c].setBackground(Color.darkGray);
                        }
                    }
                    
                }
            }
        });




        
        frame.setVisible(true);

    }
    void checkWinner(){
        //horizontal we check for rows
        for(int r=0;r<3;r++){
            for(int c=0;c<3;c++){
                if(board[r][0].getText()=="") continue;//if first row tile is empty

                if(board[r][0].getText()==board[r][1].getText()&&
                  board[r][1].getText()==board[r][2].getText()){
                    for(int i=0;i<3;i++){
                        setWinner(board[r][i]);
                    }
                    gameOver=true;
                    return;
                  }
            }
        }
        //vertical we check for columns
        for(int c=0;c<3;c++){
            if(board[0][c].getText()=="") continue;

            if(board[0][c].getText()==board[1][c].getText() &&
            board[1][c].getText() == board[2][c].getText()){
                for(int i=0;i<3;i++){
                    setWinner(board[i][c]);
                }
                gameOver=true;
                return;

            }
        }
        //digonall
        if(board[0][0].getText() == board[1][1].getText() &&
        board[1][1].getText()==board[2][2].getText() &&
        board[0][0].getText()!=""){
            for(int i=0;i<3;i++){
                setWinner(board[i][i]);
            }
            gameOver=true;
            return;
        }
        //anti digonal
        if(board[0][2].getText() == board[1][1].getText() &&
        board[1][1].getText()==board[2][0].getText() &&
        board[0][2].getText()!=""){
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver=true;
            return;
        }
        //if turns greater than 9
        if(turns==9){
            for(int r=0;r<3;r++){
                for(int c=0;c<3;c++){
                    checkTie(board[r][c]);
                }
            }
            gameOver=true;
        }
    }

    void setWinner(JButton tile){
        tile.setBackground(Color.green);
        tile.setForeground(Color.gray);
        textLabel.setText(currentPlayer+" is the winner");
    }
    void checkTie(JButton tile){
        tile.setForeground(Color.orange);
        tile.setBackground(Color.gray);
        textLabel.setText("Tie!");
    }
}
