package fr.iagl.gamification.utils;
 
import org.junit.Assert;
import org.junit.Test;
 
public class ChannelEnumTest {
 
  @Test
  public void testGetFullChannelURL() {
    String expected = "/channel/notification/point";
    Assert.assertEquals(expected, ChannelEnum.NOTIFICATION_POINT.getFullChannelURL());
  }
  
  @Test
  public void testGetFullChannelURLForID() {
    String expected = "/channel/notification/point/USRtest";
    Assert.assertEquals(expected, ChannelEnum.NOTIFICATION_POINT.getFullChannelURLWithID("USRtest"));
  }
}