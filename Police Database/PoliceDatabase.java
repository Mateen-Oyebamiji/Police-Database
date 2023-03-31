import java.util.Date;
public class PoliceDatabase {

    public Vehicle[] vehicles = new Vehicle[1000];
    public int numVehicles;
    public Driver[] drivers = new Driver[2000];
    public int numDrivers; 
    public Infraction[] infractions= new Infraction[800];
    public int numinfractions;

    public static final int limitDrive=2000; 
    public static final int limitVehicle=1000;
    public static final int limitInfrac= 800;
    public static Driver[] clean;
    //public final int x;

    /** A Static helper method used in manpulation of vehicles array */
    public static void vehicleHelper( Vehicle[] list,Vehicle Str){
        if(list[0]==null){ 
            list[0]= Str;

        }else{
            for(int i=1;i<=(list.length-1);i+=1){
                if(list[i]==null){
                    list[i]=Str;
                    break;
                }

            }
        }

        
    }
    // A helper
    public static String hasInfractionHelper(Driver d,Infraction[] A){
        for(int i=0;i<limitInfrac;i+=1){
            if(A[i]!=null){
                if((A[i]).driver==d){
                    return "true";
                }else{
                    return "false";
                }

            }

        }
        return "null";
    }
     



    public PoliceDatabase(){
        this.vehicles= new Vehicle[limitVehicle];
        this.drivers= new Driver[limitDrive];
        this.infractions= new Infraction[limitInfrac];
    }

    public void registerDriver(Driver aDriver){
        Driver[] arr= this.drivers;
        if(arr[0]==null){
            arr[0]=aDriver;

        }else{
            for(int i=1;i<=(limitDrive-1);i+=1){
                if(arr[i]==null){
                    arr[i]=aDriver;
                    break;
                }
            }
        }

    }

    public void registerVehicle(Vehicle aVehicle,String license){
        int c=0;
        for(int a=0;a<limitDrive;a+=1){
            if((this.drivers)[a]!=null){
                c+=1;
                if(((this.drivers)[a]).license==license){
                    aVehicle.owner= (this.drivers)[a];
                    Vehicle[] arr=this.vehicles;
                    if(arr[0]==null){
                        arr[0]=aVehicle;
            
                    }else{
                        for(int i=1;i<=(limitVehicle-1);i+=1){
                            if(arr[i]==null){
                                arr[i]=aVehicle;
                                break;
                            }
                        }
                    }
    
                }
            } 
        }
        this.numDrivers= c;
        this.numVehicles=c;
        //this.x=c;
         
    }

    public void unregisterVehicle(String plate){
        int c=0;
        int b=0;
        Vehicle[] vlist= new Vehicle[limitVehicle];
        for(int s=0;s<limitVehicle;s+=1){
            if((this.vehicles)[s]!= null){
                c+=1;
                if(((this.vehicles)[s]).plate==plate){
                    b+=1;
                    (this.vehicles)[s]=null;
                }
            } 
            vehicleHelper(vlist,(this.vehicles)[s]);
        }
        for(int i=0;i<limitVehicle;i+=1){
            this.vehicles[i]=vlist[i];
        }
        this.numVehicles=c-1;
    }

    public void reportStolen(String plate){
        int c=0;
        int b=0;
        for(int i=0;i<limitVehicle;i+=1){
            if((this.vehicles)[i]!=null){
                c+=1;
                if(((this.vehicles)[i]).plate== plate){
                    b+=1;
                    ((this.vehicles)[i]).reportedStolen=true;
    
                }
            } 
        }
        this.numVehicles=c;
    }

    public void changeOwner(String plate, String license ){
        for(int i=0;i<limitVehicle;i+=1){
            if((this.vehicles)[i]!=null){
                if(((this.vehicles)[i]).plate== plate){
                    for(int s=0;s<limitDrive;s+=1){
                        if((this.drivers)[s]!=null){
                            if(((this.drivers)[s]).license == license){
                                ((this.vehicles)[i]).owner=(this.drivers)[s];
                            }
                        } 
    
                    }
                }
            } 
        }
    }

    public  Infraction issueInfraction(String license,float amount,String description,Date date){
        int c=0;
        for(int i=0;i<limitDrive;i+=1){
            if((this.drivers)[i]!= null){
                if(((this.drivers)[i]).license == license){
                    c+=1;
                    Infraction newInfra = new Infraction(amount, description,date);
                    newInfra.driver=(this.drivers)[i];
                    Infraction[] arr=this.infractions;
                    //return newInfra;
                    if(arr[0]==null){
                        arr[0]=newInfra;
            
                    }else{
                        for(int s=1;s<=(limitInfrac-1);s+=1){
                            if(arr[s]==null){
                                arr[s]=newInfra;
                                break;
                                  
                            }
                        }
                    }
                    return newInfra;
                }
            }
        }
        this.numinfractions= c;
        Infraction emptyInfraction2 = new Infraction();
        return emptyInfraction2;
    }





    public boolean hasOutstandingInfractions(Driver d){
        for(int i=0;i<limitInfrac;i+=1){
            if((this.infractions)[i]!=null){
                if(((this.infractions)[i]).driver==d){
                    if(((this.infractions)[i]).outstanding==true){
                        return true;
                    }else{
                        return false;
                    }
                }
            }
            

        }
        return false;
    }




    public boolean shouldStopVehicle(String plate){
        for(int i=0;i<limitVehicle;i+=1){
            if((this.vehicles)[i]!=null){
                if(((this.vehicles)[i]).plate==plate){
                    if(((this.vehicles)[i]).reportedStolen==true || hasOutstandingInfractions(((this.vehicles)[i]).owner)== true){
                        return true;
                    }
                }

            }
        }
        return false;

    }


    public void showInfractionsFor(String license){
        int c=0;
        for(int i=0;i<limitInfrac;i+=1){
            if((this.infractions)[i]!= null){
                if((((this.infractions)[i]).driver).license == license){
                    System.out.println((this.infractions)[i].toString());
                    if(((this.infractions)[i]).outstanding== true){
                        c+=1;
                        
                    }
                }
            }
        }
        System.out.println("Total outstanding infraction= "+c);
    }

    public Driver[] cleanDrivers(){
        int c=0;
        Driver[] clean=new Driver[numDrivers];
        for(int i=0;i<limitDrive;i+=1){
            if((this.drivers)[i]!=null){
                for(int s=0;s<limitInfrac;s+=1){
                    if((this.infractions)[s]!=null){
                        if(((this.infractions)[s]).driver == (this.drivers)[i]){
                            (this.drivers)[i] = null;
                        }
                    }/*
                    if(((this.infractions)[s]).driver == (this.drivers)[i]){
                        (this.drivers)[i] = null;
                    }*/
                }
                if((this.drivers)[i]!=null){
                    c+=1;
                    clean[c]=(this.drivers)[i];
                }
            }
         

        }
         
         
        return clean;
        
    }
         
    

    public void showInfractionReport(){
        Driver x= new Driver();
        int c=0;
        //float a=0;
        for(int i=0;i<limitInfrac;i+=1){
            if((this.infractions)[i]!= null){
                x= (this.infractions)[i].driver;
                if(x.name==x.name){
                    c+=1;
                }/*
                if((this.infractions)[i].outstanding=true){
                    System.out.println(x+" : "+(c-1)+" infractions, total paid : $"+((this.infractions)[i].amount)); 
                }else{
                    System.out.println(x+" : "+(c-1)+" infractions, total paid : $0.0");
                }*/
                System.out.println(x+" : "+(c-1)+" infractions, total paid : $"+((this.infractions)[i].amount));

                

            }
            
             
        }
         
    }






    public static  PoliceDatabase example() { // Register all drivers and their vehicles
        PoliceDatabase pdb = new PoliceDatabase();
    
        pdb.registerDriver(new Driver("L1567-34323-84980", "Matt Adore",
                "1323 Kenaston St.", "Gloucester", "ON"));
        pdb.registerDriver(new Driver("L0453-65433-87655", "Bob B. Pins",
                "32 Rideau Rd.", "Greely", "ON"));
        pdb.registerDriver(new Driver("L2333-45645-54354", "Stan Dupp",
                "1355 Louis Lane", "Gloucester", "ON"));
        pdb.registerDriver(new Driver("L1234-35489-99837", "Ben Dover",
                "2348 Walkley Rd.", "Ottawa", "ON"));
        pdb.registerDriver(new Driver("L8192-87498-27387", "Patty O'Lantern",
                "2338 Carling Ave.", "Nepean", "ON"));
        pdb.registerDriver(new Driver("L2325-45789-35647", "Ilene Dover",
                "287 Bank St.", "Ottawa", "ON"));
        pdb.registerDriver(new Driver("L1213-92475-03984", "Patty O'Furniture",
                "200 St. Laurant Blvd.", "Ottawa", "ON"));
        pdb.registerDriver(new Driver("L1948-87265-34782", "Jen Tull",
                "1654 Stonehenge Cres.", "Ottawa", "ON"));
        pdb.registerDriver(new Driver("L0678-67825-83940", "Jim Class",
                "98 Oak Blvd.", "Ottawa", "ON"));
        pdb.registerDriver(new Driver("L0122-43643-73286", "Mark Mywords",
                "3 Third St.", "Ottawa", "ON"));
        pdb.registerDriver(new Driver("L6987-34532-43334", "Bob Upandown",
                "434 Gatineau Way", "Hull", "QC"));
        pdb.registerDriver(new Driver("L3345-32390-23789", "Carrie Meehome",
                "123 Thurston Drive", "Kanata", "ON"));
        pdb.registerDriver(new Driver("L3545-45396-88983", "Sam Pull",
                "22 Colonel By Drive", "Ottawa", "ON"));
        pdb.registerDriver(new Driver("L1144-26783-58390", "Neil Down",
                "17 Murray St.", "Nepean", "ON"));
        pdb.registerDriver(new Driver("L5487-16576-38426", "Pete Reedish",
                "3445 Bronson Ave.", "Ottawa", "ON"));
        pdb.registerVehicle(new Vehicle("Honda", "Civic", 2015, "yellow", "W3EW4T"),
                "L0453-65433-87655");
        pdb.registerVehicle(new Vehicle("Pontiac","Grand Prix",2007,"dark green","GO SENS"),
                "L0453-65433-87655");
        pdb.registerVehicle(new Vehicle("Mazda", "RX-8", 2004, "white", "OH YEAH"),
                "L2333-45645-54354");
        pdb.registerVehicle(new Vehicle("Nissan","Altima",2017,"bergundy", "Y6P9O7"),
                "L1234-35489-99837");
        pdb.registerVehicle(new Vehicle("Saturn", "Vue", 2002, "white", "9R6P2P"),
                "L2325-45789-35647");
        pdb.registerVehicle(new Vehicle("Honda", "Accord", 2018, "gray", "7U3H5E"),
                "L2325-45789-35647");
        pdb.registerVehicle(new Vehicle("Chrysler", "PT-Cruiser", 2006, "gold", "OLDIE"),
                "L2325-45789-35647");
        pdb.registerVehicle(new Vehicle("Nissan", "Cube", 2010, "white", "5Y6K8V"),
                "L1948-87265-34782");
        pdb.registerVehicle(new Vehicle("Porsche", "959", 1989, "silver", "CATCHME"),
                "L0678-67825-83940");
        pdb.registerVehicle(new Vehicle("Kia", "Soul", 2018, "red", "J8JG2Z"),
                "L0122-43643-73286");
        pdb.registerVehicle(new Vehicle("Porsche", "Cayenne", 2014, "black", "EXPNSV"),
                "L6987-34532-43334");
        pdb.registerVehicle(new Vehicle("Nissan", "Murano", 2010, "silver", "Q2WF6H"),
                "L3345-32390-23789");
        pdb.registerVehicle(new Vehicle("Honda", "Element", 2008, "black", "N7MB5C"),
                "L3545-45396-88983");
        pdb.registerVehicle(new Vehicle("Toyota", "RAV-4", 2010, "green", "R3W5Y7"),
                "L3545-45396-88983");
        pdb.registerVehicle(new Vehicle("Toyota", "Celica", 2006, "red", "FUNFUN"),
                "L5487-16576-38426");
    
        return pdb;
    }




    public  static void main(String[] args){

        //Driver mat =new Driver("l64544343435","tinzzy","6 Gra","osun","OS");
        //Driver mae =new Driver("U65","muby","6 lekki","lagos","OS");
        //registerDriver(mae);
         


    }
    
}
