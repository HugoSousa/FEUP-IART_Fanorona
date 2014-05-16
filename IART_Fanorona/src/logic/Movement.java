package logic;

import java.util.Collection;

/**
 * Created by Francisco on 16/05/2014.
 */
public class Movement {
    int returnValue;
    Node moveNode;
    Movement m;
    public Movement() {
        returnValue = 0;
        moveNode = null;
    }
    public  Movement(int value, Node moveNode ){
        this.returnValue = value;
        this.moveNode = moveNode.clone();
    }

    public  Movement(int value, Node moveNode , Movement m ){
        this.returnValue = value;
        this.moveNode = moveNode.clone();
        this.m = m;
    }

    public Play getPlay() {
        return (Play)m;
    }

}
