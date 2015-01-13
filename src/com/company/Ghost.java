package com.company;

/**
 * Created by Sefa on 19-12-2014.
 */
public abstract class Ghost extends Icon {

    public Ghost() { }

    protected void setGhostPosition()
    {
        this.getBox().setGameElement(null);
        this.setBox(this.getNextBox());
        this.getNextBox().setGameElement(this);
    }

    public void moveRandom() { }

}
