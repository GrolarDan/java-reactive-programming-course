package org.masci.rp.sec11.assignment;

import org.masci.rp.courseutil.DmkUtil;

public class SlackMember {

  private SlackRoom slackRoom;
  private String name;

  public SlackMember(String name) {
    this.name = name;
  }

  public void joinRoom(SlackRoom room) {
    slackRoom = room;
    slackRoom.joinRoom(DmkUtil.subscriber(name));
  }

  public void sendMessage(String msg) {
    if (slackRoom == null) {
      System.out.println("You're not in any room");
    } else {
      slackRoom.sendMessage(new SlackMessage(name, msg));
    }
  }
}
