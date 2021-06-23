import java.util.Random;

public class Vehicle {
    protected String license;
    protected Strategy strategyRuns = new OperationRuns();
    /** 
     * @return String
     * 
     */
    public String getLicense() {
        return license;
    }
    public void unlocked() {
        System.out.println(this + " unlocked");
    }
    // TuneUp method on Vehicle is executed by Strategy pattern
    public void executeRunStrategy() {
        strategyRuns.doOperation(this);
    }
    //Special case for wash command
    public void shines() {
        Random r = new Random();
        if (r.nextDouble() < 0.3) {
            this.sparkles();
        }
        else {
            System.out.println(this + " shines");
        }
    }
    public void sparkles() {
        System.out.println(this + " sparkles");
    }
    
    /** 
     * @return boolean
     */
    public boolean drives() {
        System.out.println(this + " driven");
        return true;
    }
    public void locked() {
        System.out.println(this + " locked");
    }
    
    /** 
     * @return String
     */
    public String toString() {
        return "Vehicle " + license;
    }

}
