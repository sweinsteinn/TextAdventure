public class Game {
    private Room currentRoom;
    private Parser parser;
    private Player player;
    private Room roseGarden;
    private Room boogieRoom;
    boolean wantToQuit;
    // private String red = "red";
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
         roseGarden = new Room("You now are in the rose garden, north of the library.","You are walking around this garden full of white roses. You see a sign that says 'don't touch the roses'. " +
         "You continue on your way. There are thousands of them. You're heading south and something catches your eye. You see one singular red rose. There is a sign next to the red one that says 'pick me'. You look around to see if anyone is around." +
         "You don't see anyone around. What do you do? ");
        Room libraryRoom = new Room("You made it to the library! You are east of the boogie room, and north of the button room.","You walk into the library and you see tons of books. Looking around you see book cases miles high." +
                " Nothing looks out of the ordinary until you see one wall of all white books. Everything else has color besides this one wall. Do you continue to the next room? Or do you go to the wall of white books?");
        boogieRoom = new Room("Time to dance! You're are in the boogie room! West of the library. Complete the task and you'll win the game!","long");
        Room buttonRoom = new Room("You're in the button room! Be careful of which button you choose! You are north of the money room.","You're' standing in a white room. You walk around and you find yourself standing at a panel of buttons. " +
                "There are mostly white buttons. But there is one red button. You remember the red rose and think it's a bit odd there are only red and white buttons just like red and white roses. Which button do you push?");
        Room moneyRoom = new Room("You are now in the money room, south of the button room.","You walk into a dark room and all of the sudden the lights flash on. Money starts falling from the ceiling and flying everywhere. In the back corner of the room you see a money tree." +
                "The tree is just sitting there. Does this room do anything to help you win? I guess we'll find out.");

        roseGarden.setExit("south", libraryRoom);
        libraryRoom.setExit("push wall", boogieRoom);
        libraryRoom.setExit("south", buttonRoom);
        libraryRoom.setExit("north", roseGarden);
        boogieRoom.setExit("west", libraryRoom);
        buttonRoom.setExit("south", moneyRoom);
        buttonRoom.setExit("north", libraryRoom);
        buttonRoom.setExit("red", boogieRoom);
        moneyRoom.setExit("north", buttonRoom);


        Item red =  new Item();
        Item white = new Item();
        Item money = new Item();

        player.setItem("one", red);
        roseGarden.setItem("two", white);

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
         wantToQuit = false;

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

            case PUSH:
                push(command);
                break;

            case PICK:
                pick(command);
             break;

            case GRAB:
                grab(command);
            break;

            case DANCE:
                dance(command);
            break;

            case QUIT:
            wantToQuit = true;
            break;
        }
        return wantToQuit;
    }
    private void printHelp(){
        System.out.println("You got lost.");
       // System.out.println("You are in " + getCurrentRoom() );
        System.out.println("Your command words are:");
        parser.showCommands();
    }
    private void push(Command command) {
        if (command.getSecondWord().equals("red")) {
            System.out.println("You chose the right button! You will be transported to the boogie room! You are so close to completing the task at hand!");
            currentRoom = boogieRoom;
            return;
        } else if (command.getSecondWord().equals("white")){
            System.out.println("Oh No! You chose wrong. You lost!");
        wantToQuit = true;
        return;
    } else if (command.getSecondWord().equals("wall")); {
            System.out.println("Yay! You found your way into the boogie room. You are so close to completing the task at hand!");
            currentRoom = boogieRoom;
            return;
        }
    }

    private void dance(Command command){
        if (command.equals("dance")){
           System.out.println("Yay! You finished the task. You win!");
           wantToQuit = true;
        }
        else {
            //System.out.println("That's not the task at hand.");
            System.out.println("Yay! You finished the task. You win!");
            wantToQuit = true;
        }
    }

        private void pick (Command command) {
            if (command.getSecondWord().equals("rose")) {
                System.out.println("You made the right choice by picking the " + command.getSecondWord() + "! You earn 25 bonus points!");
                return;
            }
            else if (command.getSecondWord().equals("red")) {
                System.out.println("You made the right choice by picking the red " + command.getSecondWord() + "! You earn 25 bonus points!");
            } else if (command.getSecondWord().equals("white")) {
                System.out.println("You picked wrong, you lose");
                wantToQuit = true;
            }
            else {
                System.out.println("You can't pick " + command.getSecondWord());
            }
            String key = command.getSecondWord();
            Item grabItem = currentRoom.getItem(key);
        }
        private void goRoom (Command command){
            if (!command.hasSecondWord()) {
                System.out.println("Go where?");
                return;
            }
            String direction = command.getSecondWord();
            Room nextRoom = currentRoom.getExit(direction);
            if (nextRoom == null) {
                System.out.println("There is no door!");
            } else {
                currentRoom = nextRoom;
                System.out.println(currentRoom.getShortDescription());
                System.out.println(currentRoom.getLongDescription());
            }
        }
        private void grab (Command command){
            if (!command.hasSecondWord()) {
                System.out.println("Grab what?");
                return;
            }
            String key = command.getSecondWord();
            Item grabItem = currentRoom.getItem(key);
            if (grabItem == null) {
                System.out.println("You can't grab " + command.getSecondWord());
            }
            if(command.getSecondWord().equals("money")){
                System.out.println("Yay! You grabbed the money. you get 25 bonus points.");
            }
            else {
                player.setItem(key, grabItem);
            }

        }

        private boolean quit (Command command){
            if (command.hasSecondWord()) {
                System.out.println("You can't quit " + command.getSecondWord());
                return false;
            } else {
                return true;
            }
        }
        private void printWelcome () {
            System.out.println();
            System.out.println("Welcome to my text adventure game!");
            System.out.println("You will find yourself in a rose garden, desperate to find your way out.");
            System.out.println("Type \"help\" if you need assistance");
            System.out.println();
            System.out.println(roseGarden.getShortDescription());
            System.out.println(roseGarden.getLongDescription());
        }
    }