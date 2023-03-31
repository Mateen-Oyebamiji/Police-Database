public class Vehicle {

    public String make;
    public String model;
    public int year;
    public String color;
    public String plate;
    public Driver owner;
    public boolean reportedStolen ;

    public Vehicle(String Make,String Model,int Year,String Color,String Plate){
        this.make = Make;
        this.model = Model;
        this.year = Year;
        this.color = Color;
        this.plate = Plate;
        this.reportedStolen=false;
        
    }
    public Vehicle(){
        this("","",0,"","");
    }
    public String toString(){
        return "A "+ this.color + " " + this.year + " " + this.make + " " + this.model +" with plate " +this.plate;
    }

    public  static void main(String[] args){
        Vehicle ve = new Vehicle("lexus","570",2020,"black","X456H63");

        System.out.println(ve.toString());
    }
}
