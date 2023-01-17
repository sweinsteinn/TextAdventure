public class Game {
    private Room currentRoom;
    public Game(){
    }
    private void createRooms(){
        Room roseGarden = new Room("You now are in the rose garden, north of the money room.");
        Room libraryRoom = new Room("You made it to the library! You are east of the boogie room.");
        Room boogieRoom = new Room("Time to dance! your are in the boogie room! South of the money room, and north of the button room.");
        Room buttonRoom = new Room("You're in the button room! Be careful of which button you choose!");
        Room moneyRoom = new Room("You are now in the money room, south of the rose garden, north of the dance room, and west of In-n-Out");
    }
    public void play(){
        printWelcome();
        boolean finished = false;
        while(!finished);{
        }
        System.out.println("Thanks for playing!");
    }
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to my text adventure game!");
        System.out.println("You will find yourself in a rose garden, desperate to find your way out.");
        System.out.println("Type\"help\"if you need assistance");
        System.out.println();
        System.out.println("We will print a long roo description");
    }

}
