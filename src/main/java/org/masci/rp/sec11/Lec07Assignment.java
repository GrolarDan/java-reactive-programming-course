package org.masci.rp.sec11;

import org.masci.rp.sec11.assignment.SlackMember;
import org.masci.rp.sec11.assignment.SlackRoom;

public class Lec07Assignment {

  public static void main(String[] args) {
    var room = new SlackRoom();
    var memberSam = new SlackMember("Sam");
    var memberJim = new SlackMember("Jim");
    var memberJohn = new SlackMember("John");

    memberSam.joinRoom(room);
    memberSam.sendMessage("Hello");

    memberJim.joinRoom(room);
    memberJim.sendMessage("Hi");

    memberSam.sendMessage("How do you do Jim?");
    memberJim.sendMessage("I'm fine");
    memberJim.sendMessage("What about you?");

    memberJohn.joinRoom(room);
    memberSam.sendMessage("I'm in France now");

    memberJohn.sendMessage("Oh, so many people");
  }
}
