package Organisms.Animal;

import main.Action;
import main.ActionEnum;
import main.Position;
import main.World;

import java.util.ArrayList;

import java.util.List;
import java.util.Random;

public abstract class Organism {
    static int power;
    int initiative;
    Position position;
    int liveLength;
    int powerToReproduce;
    String sign;
    World world;
    Position lastposition;

    public Organism(int power, int initiative, Position position, int liveLength, int powerToReproduce, String sign, World world) {
        this.power = power;
        this.initiative = initiative;
        this.position = position;
        this.liveLength = liveLength;
        this.powerToReproduce = powerToReproduce;
        this.sign = sign;
        this.world = world;
        this.lastposition=this.position;
    }

    public Position getLastposition() {
        return lastposition;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position pozycja) {
        this.position = pozycja;
    }

    public int getLiveLength() {
        return liveLength;
    }

    public void setLiveLength(int liveLength) {
        this.liveLength = liveLength;
    }

    public int getPowerToReproduce() {
        return powerToReproduce;
    }

    public void setPowerToReproduce(int powerToReproduce) {
        this.powerToReproduce = powerToReproduce;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
    public abstract ArrayList move();
    public abstract ArrayList action();
    public abstract void initParams();
    public abstract Organism clone();

    @Override
    public String toString() {
        return String.format("Power: "+this.power+" Initiative: "+this.initiative+" Live Length: "+this.liveLength+" Position: "+this.position.toString());
    }

    public void roundComplete(Organism organism){
        organism.setPower(organism.getPower()+1);
        organism.setLiveLength(organism.getLiveLength()-1);
    }

    public Position getCalculatedPosition(){
        Random ra=new Random();
        List<Position> freePositionsList = getWorld().getAllFreePosition(this.getPosition());
        int randomElement;
        if (freePositionsList.size() > 1) {
            randomElement = ra.nextInt(freePositionsList.size()-1);
        }
        else if (freePositionsList.size() == 1){
            randomElement = 0;
        }
        else {
            return null;
        }
        ArrayList<Action> result =new ArrayList<Action>();
        result.add(new Action(ActionEnum.INCREASEPOWER,freePositionsList.get(randomElement),this));
        System.out.println(result);
        return freePositionsList.get(randomElement);
    }

    public boolean canMove(){
        return !this.getWorld().getAllFreePosition(this.getPosition()).isEmpty();
    }


}
