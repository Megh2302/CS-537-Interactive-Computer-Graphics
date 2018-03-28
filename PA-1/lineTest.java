import java.awt.*;
import java.awt.event.*;

public class lineTest {

    public lineTest () {}

        static public void main(String[] args){

        simpleCanvas S = new simpleCanvas(700, 700);
        Rasterizer R = new Rasterizer (700);

       S.setColor (0.0f, 0.0f, 0.0f);
       S.clear();
       //S.setColor (1.0f, 1.0f, 0.0f);
       
       S.setColor( 1.0f, 0.0f, 0.0f );
        R.drawLine( 80, 340, 220, 340, S );   // Horizontal left to right 
        R.drawLine( 40, 380, 80, 340, S );    // 315 degree slope        
        R.drawLine( 220, 340, 260, 380, S );  // 45 degree slope          
        R.drawLine( 260, 380, 260, 440, S );  // Vertical bottom to top
        R.drawLine( 260, 440, 180, 440, S );  // Horizontal right to left
        R.drawLine( 180, 440, 180, 400, S );
        R.drawLine( 180, 400, 220, 400, S );
        R.drawLine( 220, 400, 200, 380, S );
        R.drawLine( 200, 380, 100, 380, S );
        R.drawLine( 100, 380, 80, 400, S );
        R.drawLine( 80, 400, 80, 500, S );
        R.drawLine( 80, 500, 100, 520, S );
        R.drawLine( 100, 520, 200, 520, S );
        R.drawLine( 200, 520, 220, 500, S );
        R.drawLine( 220, 500, 220, 480, S );
        R.drawLine( 220, 480, 260, 480, S );
        R.drawLine( 260, 480, 260, 520, S );
        R.drawLine( 260, 520, 220, 560, S );  // 135 degree slope
        R.drawLine( 220, 560, 80, 560, S );
        R.drawLine( 80, 560, 40, 520, S );    // 225 degree slope
        R.drawLine( 40, 520, 40, 380, S );    // Vertical top to bottom

        // ######## The letter 'O' in orange ########
       S.setColor( 1.0f, 0.647f, 0.0f );
        
        R.drawLine( 420, 380, 480, 380, S );
        R.drawLine( 480, 380, 520, 420, S );
        R.drawLine( 520, 420, 520, 480, S );
        R.drawLine( 520, 480, 480, 520, S );
        R.drawLine( 480, 520, 420, 520, S );
        R.drawLine( 420, 520, 380, 480, S );
        R.drawLine( 380, 480, 380, 420, S );
        R.drawLine( 380, 420, 420, 380, S );
        
        R.drawLine( 393, 340, 515, 340, S );  // big O
        R.drawLine( 340, 518, 340, 395, S ); 
        R.drawLine( 393, 340, 340, 395, S ); 
        R.drawLine( 511, 560, 390, 560, S ); 
        R.drawLine( 390, 560, 340, 518, S ); 
        R.drawLine( 560, 390, 560, 511, S ); 
        R.drawLine( 560, 511, 511, 560, S ); 
        R.drawLine( 560, 400, 515, 340, S ); 
       
       
        // My Initial Megh Vankawala MV
         // ######## The letter 'M' in blue ########
        
        S.setColor( 0.0f, 0.0f, 1.0f );         //big M
        R.drawLine( 50, 100, 50, 300, S );      //vertical bottom to top
        R.drawLine( 50, 300, 150, 200, S );     //Diagonal 
        R.drawLine( 150, 200, 250, 300, S );    //Gentle_positive
        R.drawLine( 250, 300, 250, 100, S );    // vertical top to bottom
                                                //small M                
        R.drawLine( 80, 100, 80, 230, S );      //vertical bottom to top
        R.drawLine( 80, 230, 150, 150, S );     //Diagonal
        R.drawLine( 150, 160,220, 290, S );     //Gentle_Positive
        R.drawLine( 220, 230, 220, 100, S );    // vertical top to bottom
                                                
       
        R.drawLine( 50, 100, 80, 100, S );      //horizontal
        R.drawLine( 250, 100, 220, 100, S );    //horizontal
        
        // ######## The letter 'v' in blue ########
         
        S.setColor( 0.0f, 0.0f, 1.0f );         // big V
        R.drawLine( 260, 300, 310, 100, S ); 
        R.drawLine( 369, 100, 465, 320, S );
                                                //small V
        R.drawLine( 290, 300, 356, 165, S ); 
        R.drawLine( 358, 165, 435, 319, S ); 
        
        R.drawLine( 260, 300, 290, 300, S ); // horizontal
        R.drawLine( 465, 300, 435, 320, S ); // Gentle_positive
       
        Frame f = new Frame( "Line Test" );
        f.add("Center", S);
        f.pack();
        f.setResizable (false);
        f.setVisible(true);

        f.addWindowListener( new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        } );

    }

}