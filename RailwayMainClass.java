package Railway_Ticket_Reservation;
import java.util.Scanner;

public class RailwayMainClass {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		boolean loop = true;
		
		while(loop){
			System.out.println("Ticket Booking Press: 1");
			System.out.println("Ticket Cancellation Press: 2");
			System.out.println("Available Tickets Press: 3 ");
			System.out.println("Visit Booked Ticket Press: 4");
			System.out.println("Exist Press: 5");
			int choice = in.nextInt();
			switch(choice){
			
			case 1:  //Ticket Book
			   {
				System.out.println("Enter Passenger Details");
				System.out.print("Enter Name: ");
				String name= in.next();
				System.out.println("Enter Age: ");
				int age =in.nextInt();
				System.out.println("Set Berth Preference - (L/U/M): ");
				String berthPreference = in.next();
				
				//Create Passenger Object
				Passenger p = new Passenger(name, age, berthPreference);
				ticketBooking(p);
			    break;
			   }
			case 2: //Ticket Cancellation 
			   {
				System.out.print("Enter Passenger ID: ");
				int id= in.nextInt();
				cancellation(id);
				break;
			   }
			case 3: //Available Tickets print
			   {
				TicketBooking booking = new TicketBooking();
				booking.printAvailable();
				break;
			   }
			case 4: //Visit Booked Ticket
			   {
				   TicketBooking booking = new TicketBooking();
				   booking.printPassenger();
				   break;
			   }
			case 5: // Exist
				loop = false;
				break;
			}
		}
	}
	private static void ticketBooking(Passenger p) {
		
		TicketBooking booking = new TicketBooking();
		
		if(TicketBooking.availableWaitingList == 0){
			System.out.println("No Ticket Availabel");
			return;
		}
		if((p.birthPreference.equals("L") && TicketBooking.availableLowerBirth > 0) ||
				(p.birthPreference.equals("U") && TicketBooking.availableUppperBirth > 0)||
				(p.birthPreference.equals("M") && TicketBooking.availableMiddleBirth > 0)){
			
        	   System.out.println("Prefered Birth is Available");
        	   if(p.birthPreference.equals("L")){
        	 //call booking function in the TicketBooker class
        	   booking.ticketBooking(p, (TicketBooking.lowerBerthsPositions.get(0)), "L");
        	 //remove the booked position from available positions and also decrease available seats of that
               // particular type
        	   TicketBooking.lowerBerthsPositions.remove(0);
        	   TicketBooking.availableLowerBirth--;
        	   } else
        		   if(p.birthPreference.equals("U")){
        	        	 //call booking function in the TicketBooker class
        	        	   booking.ticketBooking(p, (TicketBooking.upperBerthsPositions.get(0)), "U");
        	        	 //remove the booked position from available positions and also decrease available seats of that
        	               // particular type
        	        	   TicketBooking.upperBerthsPositions.remove(0);
        	        	   TicketBooking.availableUppperBirth--;
        	        	   } else
        	        		   if(p.birthPreference.equals("M")){
        	        	        	 //call booking function in the TicketBooker class
        	        	        	   booking.ticketBooking(p, (TicketBooking.middleBerthsPositions.get(0)), "M");
        	        	        	 //remove the booked position from available positions and also decrease available seats of that
        	        	               // particular type
        	        	        	   TicketBooking.middleBerthsPositions.remove(0);
        	        	        	   TicketBooking.availableMiddleBirth--;
        	        	        	   } else
        	        	        		// if no berth available go to RAC
        	        	        		   if(TicketBooking.availableRAC_Ticket > 0){
        	        	        			   System.out.println("RAC Ticket Available");
        	        	        			   booking.addToRAC(p, (TicketBooking.racPositions.get(0)), "RAC");
        	        	        		   }else 
        	        	        			   // if no RAC available go to WaitingList
        	        	        			   if(TicketBooking.availableWaitingList > 0){
        	        	        				   System.out.println("Added to Waiting List");
        	        	        				   booking.addToWaitingList(p, (TicketBooking.waitingListPositions.get(0)), "WL");
        	        	        			   }
           } 
        	   
	}
	
	//Ticket Cancellation
	public static void cancellation(int id) {
		
		TicketBooking booking = new TicketBooking();
	    
		if(booking.passengers.containsKey(id)){
			booking.cancelTicket(id);
		} else{
			System.out.println("Passenger ID is not Valid");
		}
		
	}

}
