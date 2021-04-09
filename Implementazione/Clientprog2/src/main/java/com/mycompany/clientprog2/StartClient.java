/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.clientprog2;

import java.io.IOException;

/**
 *
 * @author march
 */
public class StartClient {
    //avvio programma
    public static void main(String args[]) throws IOException {
        new LoginGui().setVisible(true);
    }
}
