public enum CommandWord {
    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?"), PUSH("push"), GRAB("grab"), DROP("grab"), PICK("pick"), DANCE("dance");
    private String commandString;
    CommandWord(String commandString){
        this.commandString = commandString;
    }
    public String toString(){
        return commandString;
    }
}
