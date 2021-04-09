package com.mycompany.clientprog2;


public final class UserAccount_Base extends UserAccount {
    
    public UserAccount_Base(){
        setToken();
    }
    
    @Override
    public void setToken() {
        this.token = null;
    }
}
