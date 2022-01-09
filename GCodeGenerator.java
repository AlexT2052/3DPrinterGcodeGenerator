
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
        double distanceX, distanceY, xySpeed, zSpeed, confSpeed, zHeightAmount;
        String action = "G1";
        xySpeed = 2500;
        zSpeed = 2000;
        confSpeed = 1000;
        //Stylis travel 5 mm above device when repositioning, can be changed by changing the 5.
        zHeightAmount = g.getOffsetz() + 5;
        
        GCode.startGCode();
        
        System.out.println("G1 Z" + zHeightAmount + " F" + confSpeed);
        System.out.println("G1 X" + g.getOffsetx() + " Y" + g.getOffsety() + " F" + confSpeed);
        System.out.println("G1 X" + g.getOffsetx() + " Y" + g.getOffsety() + " F" + confSpeed);
        
        for (int i = 0; i < 25; i++) {

            ranX = (int)(Math.random()*(g.getBoundx()*10))/10.0;
            ranY = (int)(Math.random()*(g.getBoundy()*10))/10.0;
            
            double swipe = Math.random();
            
            //30% chance of making a swipe a random distance within the length of Boundx.
            if (swipe < 0.3) {
                
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
                
                System.out.println(action + " X" + (ranX + g.getOffsetx()) + " Y" + (ranY + g.getOffsety()) + " F" + xySpeed);
                System.out.println(action + " Z" + g.getOffsetz() + " F" + zSpeed);
                System.out.println(action + " X" + (ranX2 + g.getOffsetx()) + " Y" + (ranY2 + g.getOffsety()) + " F" + xySpeed);
                System.out.println(action + " Z" + zHeightAmount + " F" + zSpeed);
                
            } else {
            
            System.out.println(action + " X" + (ranX + g.getOffsetx()) + " Y" + (ranY + g.getOffsety()) + " F" + xySpeed);
            System.out.println(action + " Z" + g.getOffsetz() + " F" + zSpeed);
            System.out.println(action + " Z" + zHeightAmount + " F" + zSpeed);
            }
        }
        
        GCode.endGCode();
    }
}


