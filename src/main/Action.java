package main;

import Organisms.Animal.Animal;
import Organisms.Animal.Organism;

public class Action {
    ActionEnum action;
    Position position;
    Organism organism;

    public Action(ActionEnum action, Position position, Organism organism) {
        this.action = action;
        this.position = position;
        this.organism = organism;
    }

    public ActionEnum getAction() {
        return action;
    }

    public Position getPosition() {
        return position;
    }


    public Organism getOrganism() {
        return organism;
    }

    @Override
    public String toString() {
        String classname=this.organism.getClass().getSimpleName();
        switch (action){
            case MOVE:
                return String.format(classname+" move from "+organism.getLastposition() +" to "+this.organism.getPosition());
            case REMOVE:
                return String.format(classname+" remove from "+this.organism.getPosition())+" ,because is so old";
            case ADD:
                return String.format(classname+" added at "+this.position );
            case INCREASEPOWER:
                return String.format(classname+" born in "+this.position+" as a result of reproduction");
            case NATUREPROTECTION:
                return String.format(classname+" remove a resolt nature protection from "+this.position);
            default:
                return "";

        }
    }


}
