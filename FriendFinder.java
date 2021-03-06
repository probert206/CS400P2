import java.io.*;

/**
 * @author Lucas
 *
 */
public class FriendFinder implements Serializable {

   class CurrentUser extends Person implements Serializable {
    
    /**
     * Checks if the user is in this program. If they are their profile is loaded
     * If they are not a new profile is creates
     * @param name
     */
    public CurrentUser(String name) {  
        this (masterList.contains(name) ? masterList.lookup(name) 
            : new Person(name));
        masterList.insert(new Person(name));
     
    }
    /**
     * Instantiates a current user with the fields of the person param
     * @param p
     */
    public CurrentUser(Person p){
       super(p.getFullName(), p.getResidency(), p.getBio(), p.getFriendsList());  
     }
     /**
      * Checks if this current user is freinds with the person P
     * @param p
     * @return
     */
    private boolean checkForMutualFriend(Person p) {
       return this.hasFriend(p) && p.hasFriend(this);
     }
     
     /**
     *Inserts a friend and sets mutual friend status
     */
    @Override
     public boolean insertFriend(Person p) {
      //gets the person to from the master list
       if(masterList.contains(p.getFullName())) {
         p = masterList.lookup(p.getFullName());
         return super.insertFriend(p);
       }
       //adds the new person to master list
       masterList.insert(p);
       
       return super.insertFriend(p);
     }
     
     /**
     *Prints the current use to string
     */
    @Override
     public String toString() {
       String str = "";
       str += "[ Full Name: " + (this.getFullName() == null ? " " : this.getFullName()) +"\n";
       str += "Residency: " + (this.getResidency() == null ? " " : this.getResidency()) + "\n";
       str += "Bio: " + (this.getBio() == null ? " " : this.getBio()) + "\n";
       str += "Friends List: " + (this.getFriendsList() == null ? "[]" : this.getFriendsList().toString()) + " ]\n";
       return str;
     }
    
    /**
     * writes the possibly saved data to the list.
     */
    public void saveCurrentUser() {
      Person p = masterList.lookup(this.getFullName());
      p.updateChanges(this);
      
      
  
    }
     
    
  }
   
  
  private FriendFinderRBTree masterList;
  private CurrentUser curUser;
  
  /**
   * Loads from a saved file
   * @param savedData
   */
  @SuppressWarnings("unchecked")
  public FriendFinder(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException{
    FileInputStream fileStream = new FileInputStream(fileName);
    ObjectInputStream os = null;
    try {
       os = new ObjectInputStream(fileStream);
    } catch(EOFException e) {
      masterList = new FriendFinderRBTree();
      return;
    }
   
    Object o = os.readObject();
    FriendFinderRBTree tree = (FriendFinderRBTree) o;
 
    if (o == null) {  
      masterList= new FriendFinderRBTree();
      os.close();
      return; 
    }
    
    masterList = tree ;
    os.close();
    
  }
  
  /**
   * Makes a new friend finder
   */
  public FriendFinder() {
    masterList = new FriendFinderRBTree();
  }
  /**
   * Updates the current user, by getting them from the master list or by
   * making a new one.
   * @param name Name of there new user
   */
  public void updateCurrentUser(String name) {
    
    if(this.masterList.contains(name)){
      curUser = new CurrentUser(this.masterList.lookup(name));
      return;
    }
     
     curUser = new CurrentUser(new Person(name));
     masterList.insert(new Person(name));  
  }
  
  
  /**
   * Exits and saves the master list to a file specified by file name
   * @param fileName the file name to save to 
   * @throws FileNotFoundException if the file is not found
   * @throws IOException if there is an issue with writing the object
   */
  public void exitAndSave(String fileName) throws FileNotFoundException, IOException{
    FileOutputStream fileStream = new FileOutputStream(fileName);
    ObjectOutputStream os = new ObjectOutputStream(fileStream);
    os.writeObject(masterList);
    os.close();   
  }
  public static void main(String[] args) {
    FriendFinder app = null;
    
    try {
      app = new FriendFinder("FriendFinder.ser");
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
//    
//    app.updateCurrentUser("Lucas");
//    app.curUser.insertFriend(new Person("2"));
    System.out.println(app.masterList);
//    System.out.println(app.curUser);
//    try {
//    app.exitAndSave("FriendFinder.ser");
//    } catch (Exception e) {
//      e.printStackTrace();
//    }

    
    
    
  }
  
 
  
}
