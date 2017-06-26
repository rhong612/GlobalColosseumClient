package com.globalcolosseum;

/**
 *
 */
public class Profile {
	private int id;
    private String username;
    private String password;
    private int win;
    private int loss;
    
    public Profile() {
    	//Required to deserialize from server.
    }
    
    public Profile(String username) {
        this.username = username;
        win = 0;
        loss = 0;
    }
    
    public int getPlayerID() {
        return id;
    }

    public String getUsername() {
        return username;
    }
    
    public void changePassword(String newPassword) {
    	password = newPassword;
    }
    
    public int getWin() {
    	return win;
    }
    
    public int getLoss() {
    	return loss;
    }
}
