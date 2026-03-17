/**
 * Book My Stay Application
 * Room Domain Initialization
 *
 * @author Student
 * @version 2.0
 */

abstract class Room{

    protected String type;
    protected int beds;
    protected double price;

    public Room(String type,int beds,double price){

        this.type=type;
        this.beds=beds;
        this.price=price;
    }

    public void display(){

        System.out.println("Room Type : "+type);
        System.out.println("Beds : "+beds);
        System.out.println("Price : "+price);
    }
}

class SingleRoom extends Room{

    public SingleRoom(){

        super("Single Room",1,2000);
    }
}

class DoubleRoom extends Room{

    public DoubleRoom(){

        super("Double Room",2,3500);
    }
}

class SuiteRoom extends Room{

    public SuiteRoom(){

        super("Suite Room",3,5000);
    }
}

public class RoomInitialization{

    public static void main(String[] args){

        System.out.println("Book My Stay App v2.0");
        System.out.println();

        SingleRoom s=new SingleRoom();
        DoubleRoom d=new DoubleRoom();
        SuiteRoom su=new SuiteRoom();

        int singleAvailable=5;
        int doubleAvailable=3;
        int suiteAvailable=2;

        s.display();
        System.out.println("Available : "+singleAvailable);

        System.out.println();

        d.display();
        System.out.println("Available : "+doubleAvailable);

        System.out.println();

        su.display();
        System.out.println("Available : "+suiteAvailable);

    }
}