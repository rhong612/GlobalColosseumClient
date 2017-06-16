package com.globalcolosseum;

/**
 *
 */
public class Player {
	private int id;
    private String username;
    private String password;
    private String screenName;
    private int win;
    private int loss;
    private String address;

    public Player() {
    	//Required to deserialize from server.
    }
    
    public Player(String username) {
        //Autogenerate an unique ID used to distinguish player with identical name on different server.
        this.username = username;
        win = 0;
        loss = 0;
    }
    
    public Player(String username, int win, int loss) {
    	this.username = username;
    	this.win = win;
    	this.loss = loss;
    }

    public int getPlayerID() {
        return id;
    }

    public String getUsername() {
        return username;
    }
    
    public void setPassword(String password) {
    	this.password = password;
    }
    
    public String getScreenName() {
    	return screenName;
    }
    
    public void setScreenName(String screenName) {
    	this.screenName = screenName;
    }
    
    public int getWin() {
    	return win;
    }
    
    public int getLoss() {
    	return loss;
    }
    
    public void win() {
    	win++;
    }
    
    public void lose() {
    	loss++;
    }
}
