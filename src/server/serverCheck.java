/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author acer
 */
public class serverCheck {
    public String checkUsername(String username) {
    String[] parts = username.split("#");
    return parts[1];
}
}
