public class Driver {
    public String license;
    public String name;
    public String street;
    public String city;
    public String province;
    
//  constructor
    public  Driver(String License,String Name, String Street, String City,String Province){
        this.license= License;
        this.name= Name;
        this.street= Street;
        this.city= City;
        this.province= Province;

    }
    public Driver(){
        this("","","","","");
    }
    
    public String toString(){
        return "#"+ this.license +" "+ this.name +" living at "+ this.street +", "+ this.city +", "+ this.province +"";
    }

    public  static void main(String[] args){
        System.out.println("Driver class");
        Driver mat =new Driver("l64544343435","tinzzy","6 Gra","osun","OS");
        Driver mae =new Driver("l64544343435","tinzzy","6 Gra","osun","OS");
        Driver moe =new Driver();
        Infraction x= new Infraction();
        if(moe.city==""){
            System.out.println("true");

        }
        
    }
}
