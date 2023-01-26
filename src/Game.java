public class Game {
    private Room currentRoom;
    private Parser parser;
    private Player player;
    public Game(){
        parser = new Parser();
        player = new Player();
    }
    public static void main (String[] args){
        Game game = new Game();
        game.createRooms();
        game.play();
    }
    private void createRooms(){
        Room roseGarden = new Room("You now are in the rose garden, north of the library.","You are walking around this garden full of white roses. You see a sign that says 'don't touch the roses'. " +
         "You continue on your way. There are thousands of them. You're heading south and something catches your eye. You see one singular red rose. There is a sign next to the red one that says 'pick me'. You look around to see if anyone is around." +
                "You don't see anyone around. What do you do? ");
        Room libraryRoom = new Room("You made it to the library! You are east of the boogie room, and north of the button room.","long");
        Room boogieRoom = new Room("Time to dance! You're are in the boogie room! West of the library. Complete a task and you'll win the game!","You're' standing in a white room. You walk around and you find yourself standing at a panel of buttons. " +
                "There are mostly white buttons. But there is one red button. " +
                "You remember the red rose and think about there are only red and white buttons just like red and white roses. ");
        Room buttonRoom = new Room("You're in the button room! Be careful of which button you choose! You are north of the money room.","long!");
        Room moneyRoom = new Room("You are now in the money room, south of the button room.","long");

        roseGarden.setExit("south", libraryRoom);
        libraryRoom.setExit("push wall", boogieRoom);
        libraryRoom.setExit("south", buttonRoom);
        libraryRoom.setExit("north", roseGarden);
        boogieRoom.setExit("west", libraryRoom);
        buttonRoom.setExit("south", moneyRoom);
        buttonRoom.setExit("north", libraryRoom);
        moneyRoom.setExit("north", buttonRoom);


        Item obj =  new Item();
        Item obj2 = new Item();

        player.setItem("one", obj);
        roseGarden.setItem("two", obj2);

        currentRoom = roseGarden;
    }
    public void play(){
        printWelcome();
        boolean finished = false;
        while(!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thanks for playing!");
    }
    private boolean processCommand(Command command){
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();
        switch(commandWord){
            case UNKNOWN:
                System.out.println("I'm not sure what you mean");
            break;

            case HELP:
                printHelp();
            break;

            case GO:
                goRoom(command);
            break;

            case LOOK:
                look(command);
                break;

            case PICK:
                pick(command);
             break;

            case GRAB:
                grab(command);
            break;

            case DROP:
                drop(command);
            break;

            case QUIT:
            wantToQuit = true;
            break;
        }
        return wantToQuit;
    }
    private void printHelp(){
        System.out.println("You got lost.");
        System.out.println("You are in the rose garden");
        System.out.println("Your command words are:");
        parser.showCommands();
    }
    private void look(Command command){
        if (command.hasSecondWord()){
            System.out.println("You can't look at " + command.getSecondWord());
            return;
        }
        System.out.println(currentRoom.getLongDescription());
        System.out.println(player.getItemString());
    }
    private void pick(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("You picked a " + command.getSecondWord());
            return;
        }
        System.out.println(currentRoom.getLongDescription());
        System.out.println(player.getItemString());
    }
    private void goRoom(Command command) {
        if(!command.hasSecondWord()){
            System.out.println("Go where?");
            return;
        }
        String direction = command.getSecondWord();
        Room nextRoom = currentRoom.getExit(direction);
        if(nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getShortDescription());
        }
    }
    private void grab (Command command) {
        if(!command.hasSecondWord()){
            System.out.println("Grab what?");
            return;
        }
        String key = command.getSecondWord();
        Item grabItem = currentRoom.getItem(key);
        if(grabItem == null) {
            System.out.println("You can't grab " + command.getSecondWord());
        }
        else{
            player.setItem(key, grabItem);
        }

    }
    private void drop (Command command) {
        if(!command.hasSecondWord()){
            System.out.println("Drop what?");
            return;
        }
        String key = command.getSecondWord();
        Item dropItem = currentRoom.getItem(key);
        if(dropItem == null) {
            System.out.println("You can't drop " + command.getSecondWord());
        }
        else {
            currentRoom.setItem(key, dropItem);
        }
    }

    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("You can't quit " + command.getSecondWord());
            return false;
        }
        else {
            return true;
        }
    }
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to my text adventure game!");
        System.out.println("You will find yourself in a rose garden, desperate to find your way out.");
        System.out.println("Type \"help\" if you need assistance");
        System.out.println();
        System.out.println("We will print a long room description");
    }

}
