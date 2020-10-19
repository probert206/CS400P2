// --== CS400 File Header Information ==--
// Name: Pieran Robert
// Email: probert@wisc.edu 
// Team: NE
// TA: Daniel Finer
// Lecturer: Gary Dahl
// Notes to Grader:
import java.util.Scanner;

public class FriendFinderFrontEnd {

	public static void main(String [] args) {
		Scanner reader = new Scanner(System.in);
		String name = "";
		boolean run = true;
		System.out.println("Welcome to Friend Finder\nPlease Enter your name: ");
		name = reader.nextLine();
		FriendFinder tree = new FriendFinder();
		tree.updateCurrentUser(name);

		
		while(run) {
	
		System.out.println("Thanks " + tree.curUser.getFullName() + ", please input a command"); //prints instructions for users
		System.out.println("Enter p to view the entire FriendFinder tree \nEnter f to modify your personal information \n"
		        + "Enter c to change or add a new friend \nEnter u to change a friend's personal information"
		        +  "\nEnter x to check for a mutual friend \nEnter q to end program");
		 switch(reader.next()) {
		 case "p": System.out.println(tree.masterList.tree);
		 break;
		 case "f": System.out.println("What information would you like to change:");
		 System.out.print("Enter n to change name \nEnter r to change residency" +
		 "\nEnter b to change bio");
		 switch(reader.next()) {
		 case "n": System.out.println("Enter new Full Name: ");
			 tree.curUser.setFullName(reader.next());
			 break;
		 case "r":	System.out.println("Enter new Residency: ");
			 tree.curUser.setResidency(reader.next());
			 break;
		 case "b": System.out.println("Enter new Bio ");
			 tree.curUser.setBio(reader.next());
		 break;
		 }
		 break;
		 case "c": System.out.println("Add a new friend: \nWhat is their name?");
		 tree.masterList.tree.insert(new Person(reader.next()));
		 break;
		 case "u": System.out.println("What friend's information would you like to change?");
		 String p = tree.masterList.lookup(reader.next()).getFullName();
		 System.out.println("What information would you like to change:");
		 System.out.print("Enter n to change name \nEnter r to change residency" +
		 "\nEnter b to change bio");
		 switch(reader.next()) {
		 case "n": System.out.println("Enter new Full Name: ");
			 tree.masterList.lookup(p).setFullName(reader.next());
			 break;
		 case "r": System.out.println("Enter new Residency: ");
			 tree.masterList.lookup(p).setResidency(reader.next());
			 break;
		 case "b": System.out.println("Enter new Bio ");
			 tree.masterList.lookup(p).setBio(reader.next());
			 break;

		 }
		 break;
		 case "x": System.out.println("For what friend would you like to check mutuality?");
		 String friend = tree.masterList.lookup(reader.next()).getFullName();
		 if(tree.curUser.checkForMutualFriend(tree.masterList.lookup(friend)))
			 System.out.println(friend + " and you are mutual friends!");
		 else {System.out.println("Sadly, " + friend + " and you are not mutual friends.");}
		 break;
		 case "q": run = false;
		 break;
		 }
		
		}
	
	}
	
}
