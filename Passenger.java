package Railway_Ticket_Reservation;

public class Passenger {

	static int ID=1; //static variable to give id for every new passenger
	
	String name;
	int age;
	String birthPreference; // U or L or M
	int passengerID;// id of passenger created automatically
	String alloted;//alloted type (L,U,M,RAC,WL)
	int number;//seat number
	
	public Passenger(String name, int age, String birthPreference){
		
		this.name=name;
		this.age=age;
		this.birthPreference=birthPreference;
		this.passengerID=ID++;
		alloted="";
		number =-1;
	}
}
