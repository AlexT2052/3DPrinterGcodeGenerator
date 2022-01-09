/**
 * GCode is for creating objects and methods for G-Code genaration
 *
 * @author (Skyblaze103)
 * @version (1.3)
 */
public class GCode
{
    private double BOUNDX, BOUNDY, OFFSETX, OFFSETY, OFFSETZ;
    
    public GCode(int bx, int by, int ox, int oy, int oz) {
        
        BOUNDX = bx;
        BOUNDY = by;
        /* Offset of magnetic stylis from nozzle position on my Ender 3 Pro
         * If using and repeating this for whatever reason, these values will probably need to be changed.
         */
        OFFSETX = ox + 14; //In mm
        OFFSETY = oy + 25;
        OFFSETZ = oz;
    }
    
    public double getBoundx() {
        
        return BOUNDX;
    }
    
    public double getBoundy() {
        
        return BOUNDY;
    }
    
    public double getOffsetx() {
        
        return OFFSETX;
    }
    
    public double getOffsety() {
        
        return OFFSETY;
    }
    
    public double getOffsetz() {
        
        return OFFSETZ;
    }
    
    public void startGCode() {
        
        System.out.println("G28"); //Home all axis
        System.out.println("G92 E0"); //Reset Extruder (Possibly don't need)
        System.out.println("G1 " + OFFSETZ + " F1000.0"); //Move up
    }
    
    public void endGCode() {
        
        System.out.println("G4"); //Wait
        System.out.println("M220 S100\nM221 S100"); // Reset Speed and Extrude factor to default (100%)
        System.out.println("G90"); //Set coordinates to absolute
        System.out.println("M84"); //Disable stepper motors
        System.out.println("M117 Program Finished"); //End Printout
    }
}