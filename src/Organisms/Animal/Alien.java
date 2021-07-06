package Organisms.Animal;

import main.Position;
import main.World;

import java.util.ArrayList;

public class Alien extends Animal{
    public Alien(Position position, World world){
        super(1,1,position,2,10,"A",world);
    }

    @Override
    public ArrayList move() {
        return null;
    }

    @Override
    public ArrayList action() {
        return null;
    }

    @Override
    public void initParams() {
        this.setPower(1);
        this.setInitiative(1);
        this.setLiveLength(2);
        this.setPowerToReproduce(10);
        this.setSign("A");
    }

    @Override
    public Organism clone() {
        return null;
    }



}
