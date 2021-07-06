package main;

public enum ActionEnum {
    MOVE(0),
    REMOVE(1),
    ADD(2),
    INCREASEPOWER(3),
    NATUREPROTECTION(4);
    private final int value;


    private ActionEnum(int value){
        this.value=value;
    }

    public int getValue() {
        return value;
    }
}
