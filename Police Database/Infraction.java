import java.util.Date;
public class Infraction {


    //import java.util.Date;
    public float amount;
    public String description;
    public Date dateIssued;
    public boolean outstanding;
    public Driver driver;

    public Infraction(float Amount, String Description, Date DateIssued){
        this.amount= Amount;
        this.description= Description;
        this.dateIssued= DateIssued;
        this.outstanding= true;

    }

    public Infraction(){
        this(0.0f,"",new Date());
    }
    
    public void pay(){
        this.outstanding= false;
    }

    public String toString(){
        if(this.outstanding==false){
            return ""+ String.format("%.2f",this.amount) + " infraction on " + String.format( "%tc",this.dateIssued) +" [PAID IN FULL]";
        }
        return ""+ String.format("%.2f",this.amount) + " infraction on " + String.format( "%tc",this.dateIssued) +" [OUTSTANDING]";
    }

    public static void main(String[] args){
        System.out.println("this is a break in");
        Date dte= new Date();
        Infraction test= new Infraction(200.6657676f,"today",dte);
        //test.pay();
        System.out.println(test.toString());
    }

     
    
}
