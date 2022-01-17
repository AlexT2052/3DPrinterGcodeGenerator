/**
 * Generates GCode for poking and swiping somthing randomly using the GCode class
 *
 * @author (Skyblaze103)
 * @version (1.3)
 */
public class GCodeGenerator
{
    public static void main(String args[]){
        //Nozzle offset already built in.
        //Values are device width (boundx), device height (boundy), x offset, y offset, z offset.
        GCode g = new GCode(68, 153, 10, 10, 26);

        double ranX, ranY, ranX2, ranY2;
        double distanceX, distanceY, xySpeed, zSpeed, confSpeed, zHeightAmount, swipe, swipeChance;
        xySpeed = 2500;
        zSpeed = 2000; 
        confSpeed = 1000; //Configuration motor speed
        zHeightAmount = g.getOffsetz() + 5; //Stylis travel 5 mm above device when repositioning, can be changed by changing the 5.
        swipeChance = 0.3; //Number between 0 and 1, chance you want the program to make a swipe of random length each iteration.
        int iterations = 40; //Number of movements/taps you want the program to make.

        
        g.startGCode();
        
        System.out.println("G1 Z" + zHeightAmount + " F" + confSpeed);
        System.out.println("G1 X" + g.getOffsetx() + " Y" + g.getOffsety() + " F" + confSpeed);
        System.out.println("G1 X" + g.getOffsetx() + " Y" + g.getOffsety() + " F" + confSpeed);

        //Rectangle around parameter area
        System.out.println("G1 X" + (g.getOffsetx() + g.getBoundx()) + " F" + confSpeed);
        System.out.println("G1 Y" + (g.getOffsety() + g.getBoundy()) + " F" + confSpeed);
        System.out.println("G1 X" + g.getOffsetx() + " F" + confSpeed);
        System.out.println("G1 Y" + g.getOffsety() + " F" + confSpeed);
        
        //
        for (int i = 0; i < iterations; i++) {

            ranX = (int)(Math.random()*(g.getBoundx()*10))/10.0;
            ranY = (int)(Math.random()*(g.getBoundy()*10))/10.0;
            
            swipe = Math.random();
            
            //30% chance of making a swipe a random distance within the length of Boundx.
            if (swipe < swipeChance) {
                
                ranX2 = (int)(Math.random()*(g.getBoundx()*10))/10.0;
                ranY2 = (int)(Math.random()*(g.getBoundy()*10))/10.0;
                
                distanceX = Math.pow(ranX2 - ranX, 2);
                distanceY = Math.pow(ranY2 - ranY, 2);
                
                while (Math.sqrt(distanceX + distanceY) > g.getBoundx()) {
                    
                    ranX = (int)(Math.random()*(g.getBoundx()*10))/10.0;
                    ranY = (int)(Math.random()*(g.getBoundy()*10))/10.0;
                    
                    distanceX = Math.pow(ranX2 - ranX, 2);
                    distanceY = Math.pow(ranY2 - ranY, 2);
                }
                
                System.out.println("G1 X" + (ranX + g.getOffsetx()) + " Y" + (ranY + g.getOffsety()) + " F" + xySpeed);
                System.out.println("G1 Z" + g.getOffsetz() + " F" + zSpeed);
                System.out.println("G1 X" + (ranX2 + g.getOffsetx()) + " Y" + (ranY2 + g.getOffsety()) + " F" + xySpeed);
                System.out.println("G1 Z" + zHeightAmount + " F" + zSpeed);
                
            } else {
            
            System.out.println("G1 X" + (ranX + g.getOffsetx()) + " Y" + (ranY + g.getOffsety()) + " F" + xySpeed);
            System.out.println("G1 Z" + g.getOffsetz() + " F" + zSpeed);
            System.out.println("G1 Z" + zHeightAmount + " F" + zSpeed);
            }
        }
        
        g.endGCode();
    }
}