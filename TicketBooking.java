package Railway_Ticket_Reservation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class TicketBooking {
	
	//63 berths(upper ,lower , middle)  + ( 18 RAC passengers)
	static int availableUppperBirth =18;
	static int availableLowerBirth =18;
	static int availableMiddleBirth =18;
	static int availableRAC_Ticket =18;
	static int availableWaitingList =100;
	
    static Queue<Integer>waitingList = new LinkedList<>();
    static Queue<Integer>racList = new LinkedList<>();
    static List <Integer>ticketBookedList = new ArrayList<>();
    
    static List<Integer> lowerBerthsPositions = new ArrayList<>(Arrays.asList(1));//normally 1,2,...21
    static List<Integer> middleBerthsPositions = new ArrayList<>(Arrays.asList(1));//normally 1,2,...21
    static List<Integer> upperBerthsPositions = new ArrayList<>(Arrays.asList(1));//normally 1,2,...21
    static List<Integer> racPositions = new ArrayList<>(Arrays.asList(1));//normally 1,2,...18
    static List<Integer> waitingListPositions = new ArrayList<>(Arrays.asList(1));//normally 1,2,...10
    
    static Map<Integer, Passenger> passengers = new HashMap<>();//map of passenger ids to passengers
    
    // Ticket Booking
    public void ticketBooking(Passenger p, int berth, String alloted){
    	
    	p.number = berth;
    	p.alloted = alloted;
    	// add passenger to the map
    	passengers.put(p.passengerID, p);
    	//add passenger id to the list of booked tickets
    	ticketBookedList.add(p.passengerID);
    	System.out.println("***_Successfully Ticket Booked_***");
    	
    }
       //adding to RAC
    public void addToRAC(Passenger p, int rac, String alloted){
    	
    	p.number = rac;
    	p.alloted = alloted;
    	// add passenger to the map
    	passengers.put(p.passengerID, p);
    	//add passenger id to the Queue of booked tickets
    	racList.add(p.passengerID);
    	//decrease available RAC tickets by 1
    	availableRAC_Ticket--;
    	racPositions.remove(0);
    	System.out.println("***_Successfully RAC Ticket Booked_***");
    	
    }
    // adding waiting List
    public void addToWaitingList(Passenger p, int waitinglist, String alloted){
    	
    	p.number= waitinglist;
    	p.alloted= alloted;
    	passengers.put(p.passengerID, p);
    	//add passenger id to the queue of WL tickets
    	waitingList.add(p.passengerID);
    	//decrease available WL tickets by 1 
    	availableWaitingList--;
    	//remove the position that was alloted to the passenger
    	waitingList.remove(0);
    	System.out.println("***_added to Waiting List Successfully_***");
    	
    }
    
    // Cancel ticket
    public void cancelTicket(int passengerID){
    	//remove passenger from map
    	Passenger p = passengers.get(passengerID);
    	passengers.remove(Integer.valueOf(passengerID));
    	//remove the booked ticket from the list
    	ticketBookedList.remove(Integer.valueOf(passengerID));
    	//take the booked position which is now free
    	int bookedPosition = p.number;
    	System.out.println("***_Ticket Successfully Cancel_***");
    	
    	//add the free position to the correspoding type of list (either L,M,U)
    	if(p.alloted.equals("L")){
    		availableLowerBirth++;
    		lowerBerthsPositions.add(bookedPosition);
    	} else
    		if(p.alloted.equals("U")){
    			availableUppperBirth++;
    			upperBerthsPositions.add(bookedPosition);
    		} else
    			if(p.alloted.equals("M")){
    				availableMiddleBirth++;
    				middleBerthsPositions.add(bookedPosition);
    			}
    	
    	 //check if any RAC is there
    	if(racList.size()>0){
    		//take passenger from RAC and increase the free space in RAC list and increase available RAC tickets
    		//increase available WL and decrease available RAC by 1
    		Passenger passengerFromWaitingList = passengers.get(waitingList.poll());
    		int positionWL = passengerFromWaitingList.number;
    		waitingListPositions.add(positionWL);
    		waitingList.remove(Integer.valueOf(passengerFromWaitingList.passengerID));
    		
    		passengerFromWaitingList.number = racPositions.get(0);
            passengerFromWaitingList.alloted = "RAC";
            racPositions.remove(0);
            racList.add(passengerFromWaitingList.passengerID);
            
            availableWaitingList++;
            availableRAC_Ticket--;
    	}
    }
  //print all available seats
    public void printAvailable(){
    	
        System.out.println("Available Lower Berths "  + availableLowerBirth);
        System.out.println("Available Middle Berths "  + availableMiddleBirth);
        System.out.println("Available Upper Berths "  + availableUppperBirth);
        System.out.println("Availabel RACs " + availableRAC_Ticket);
        System.out.println("Available Waiting List " + availableWaitingList);
        System.out.println("--------------------------");
    }
  //print all occupied passengers from all types including WL
    public void printPassenger(){
    	
    	if(passengers.size() == 0){
    		System.out.println("Passenger Details not Found");
    		return;
    	}
    	for(Passenger p: passengers.values()){
    		
    		System.out.println("PASSENGER ID " + p.passengerID );
            System.out.println(" Name " + p.name );
            System.out.println(" Age " + p.age );
            System.out.println(" Status " + p.number + p.alloted);
            System.out.println("--------------------------");
    	}
    }
}
