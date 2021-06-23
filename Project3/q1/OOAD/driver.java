import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

//import jdk.internal.jshell.tool.resources.version;

public class driver {
    
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, FileNotFoundException {
        //taking input of number of days
        Scanner scan = new Scanner(System.in);
        System.out.print("Number of days: ");

        // This method reads the number provided using keyboard
        int n = scan.nextInt();

        // Closing Scanner after the use
        scan.close();
        //Printing the output in the "garage_actions.out" text file
        PrintStream o = new PrintStream(new File("garage_actions.out"));
        System.setOut(o);
        //Creating the instance of garage class
        Garage g = new Garage();
        
        //Running loop for given number of days. Reading the mechanicName by creating an instance of Mechanic class (Polymorphism)
        for (int i=1; i<=n; i++) {
            Mechanic m = new Mechanic(g.GA);
            System.out.println("Mechanic " + m.employeeName + " arrives at Garage on Day " + i);
            //Running another loop of all mechanic methods on each vehicle in the garage 
            //catch event
            //methods list
            // at each time instance, take each method from list and run the enitre for loop code below
            
            for (Method a : Mechanic.class.getDeclaredMethods()) {
                int vehicleCount = g.vehicleList.size();
                if (a.getName() != "notifyObserver") {
                    //Observer Pattern is applied
                    m.notifyObserver(a.getName());
                    for (int j=0; j<vehicleCount; j++) {
                        //Creating vehicle identity for all vehicles in the garage
                        Vehicle v = g.vehicleList.get(j);
                        //Checking for special case (Monster vehicle and testDrive command)
                        if (v.getClass().equals(Monster.class) && a.equals(Mechanic.class.getMethod("testDrive", Vehicle.class))) {
                            //The below line invokes testDrive method in Mechanic class which further prints a line (Abstraction) 
                            boolean result = (boolean) a.invoke(m, v);
                            //Removing the vehicle Monster from the garage and replcing the mechanic
                            if (!result) {
                                g.vehicleList.remove(j);
                                vehicleCount = g.vehicleList.size();
                                m = new Mechanic(g.GA);
                                System.out.println("Mechanic " + m.employeeName + " replaces diff mechanic on Day " + i);
                                j--;
                            }
                        }
                        //For all other vehicles invoking methods of mechanic class for each vehilce object(Polymorphism)
                        else {
                            a.invoke(m, v);
                            
                        }
                    }
                }

            }
            // Garage Announcer leaves at end of day
            m.notifyObserver();
        }
    }
}