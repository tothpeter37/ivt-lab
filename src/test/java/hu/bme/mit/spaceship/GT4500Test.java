package hu.bme.mit.spaceship;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore primary;
  private TorpedoStore secondary;

  @Before
  public void init(){

    primary = mock(TorpedoStore.class);
    secondary = mock(TorpedoStore.class);

    this.ship = new GT4500(primary, secondary);
  }

  @Test
  public void fireTorpedos_Single_Success(){
    // Arrange
    when(primary.fire(1)).thenReturn(true);
    when(secondary.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedos(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedos_All_Success(){
    // Arrange
    when(primary.fire(1)).thenReturn(true);
    when(secondary.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedos(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedos_All_Primary_Success(){
    // Arrange
    when(primary.fire(1)).thenReturn(true);
    when(secondary.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedos(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedos_All_Secondary_Success(){
    // Arrange
    when(primary.fire(1)).thenReturn(false);
    when(secondary.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedos(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedos_All_Fail(){
    // Arrange
    when(primary.fire(1)).thenReturn(false);
    when(secondary.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedos(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
  }

  @Test
  public void fireTorpedos_Single_First_Fail(){
    // Arrange
    when(primary.fire(1)).thenReturn(false);
    when(secondary.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedos(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
  }

  @Test
  public void fireTorpedos_Single_Second_Fail_Success(){
    // Arrange
    when(primary.fire(1)).thenReturn(true);
    when(secondary.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedos(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedos_Single_Fail(){
    // Arrange
    when(primary.fire(1)).thenReturn(false);
    when(secondary.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedos(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
  }

  @Test
  public void fireTorpedos_Mixed(){
    // Arrange
    when(primary.fire(1)).thenReturn(false);
    when(secondary.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedos(FiringMode.ALL);

    // Assert
    assertEquals(true, result);


    // Arrange
    when(primary.fire(1)).thenReturn(false);
    when(secondary.fire(1)).thenReturn(true);

    // Act
    result = ship.fireTorpedos(FiringMode.ALL);

    // Assert
    assertEquals(true, result);



    // Arrange
    when(primary.fire(1)).thenReturn(true);
    when(secondary.fire(1)).thenReturn(true);

    // Act
    result = ship.fireTorpedos(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);



    // Arrange
    when(primary.fire(1)).thenReturn(true);
    when(secondary.fire(1)).thenReturn(true);

    // Act
    result = ship.fireTorpedos(FiringMode.ALL);

    // Assert
    assertEquals(true, result);


    // Arrange
    when(primary.fire(1)).thenReturn(false);
    when(secondary.fire(1)).thenReturn(false);

    // Act
    result = ship.fireTorpedos(FiringMode.ALL);

    // Assert
    assertEquals(false, result);

    verify(primary, times(5)).fire(1);
    verify(secondary, times(4)).fire(1);
  }

  @Test
  public void fireTorpedos_Single_Multiple_Times(){
    // Arrange
    when(primary.fire(1)).thenReturn(true);
    when(secondary.fire(1)).thenReturn(false);
    when(secondary.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedos(FiringMode.SINGLE);


    // Assert
    assertEquals(true, result);


    // Act
    result = ship.fireTorpedos(FiringMode.SINGLE);


    // Assert
    assertEquals(true, result);

  }

  @Test
  public void fireTorpedos_Single_Multiple_Times_Second(){
    // Arrange
    when(primary.fire(1)).thenReturn(true);
    when(secondary.fire(1)).thenReturn(true);
//    when(secondary.isEmpty()).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedos(FiringMode.SINGLE);


    // Assert
    assertEquals(true, result);


    // Act
    result = ship.fireTorpedos(FiringMode.SINGLE);


    // Assert
    assertEquals(true, result);


    // Arrange
    when(primary.isEmpty()).thenReturn(true);

    // Act
    result = ship.fireTorpedos(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);

  }

}
