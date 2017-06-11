package com.colosseum.global;

/**
 *
 */
public class Player {
    private int id;
    private String name;
    private int win;
    private int loss;
    private String address;
    
    public Player() {
    	//Required to deserialize from server.
    }

    public Player(String name) {
        //Autogenerate an unique ID used to distinguish player with identical name on different server.
        this.name = name;
        win = 0;
        loss = 0;
    }
    
    public Player(String name, int win, int loss) {
    	this.name = name;
    	this.win = win;
    	this.loss = loss;
    }

    public int getPlayerID() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public int getWin() {
    	return win;
    }
    
    public int getLoss() {
    	return loss;
    }
    
    public String getAddress() {
    	return address;
    }
    
    public void setAddress(String address) {
    	this.address = address;
    }
    
    public void win() {
    	win++;
    }
    
    public void lose() {
    	loss++;
    }
}
