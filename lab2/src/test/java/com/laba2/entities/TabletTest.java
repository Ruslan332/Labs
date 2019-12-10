package com.laba2.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.laba2.annotations.TestAnnotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TabletTest {

  private Tablet tablet;

  @BeforeAll
  private void init() {
    tablet = getTablet();
  }

  @Test
  public void shouldReturnClassName() {
    assertEquals("Tablet", tablet.getClass().getSimpleName());
  }

  @Test
  public void shouldReturnSuperclassName() {
    assertEquals("Computer", tablet.getClass().getSuperclass().getSimpleName());
  }

  @Test
  public void shouldReturnClassAnnotations() {
    // it show only annotation which has RetentionPolicy.Runtime
    System.out.println(Arrays.toString(tablet.getClass().getAnnotations()));
  }

  @Test
  public void shouldReturnFieldsAndTheirAnnotations() {
    for (Field field : tablet.getClass().getDeclaredFields()) {
      System.out.println("Name: " + field.getName() +
          "\nType: " + field.getAnnotatedType().getType() +
          "\nAnnotation: " + Arrays.toString(field.getAnnotations()) + "\n");
    }
  }

  @Test
  @SneakyThrows
  public void shouldChangeOsToIos() {
    for (Method method : tablet.getClass().getMethods()) {
      if (method.isAnnotationPresent(TestAnnotation.class)) {
        method.invoke(tablet);
      }
    }
    assertEquals(OS.IOS, tablet.getOs());

  }


  private Tablet getTablet() {
    return Tablet.builder()
        .name("Ipad")
        .memory(64)
        .cores(8)
        .diagonal(15.6f)
        .frequency(2)
        .ram(4)
        .batteryCapacity(4000)
        .os(OS.IOS)
        .build();
  }

}