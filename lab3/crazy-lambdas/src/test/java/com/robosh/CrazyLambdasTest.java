package com.robosh;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.IntUnaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class CrazyLambdasTest {

  @Test
  public void testHelloSupplier() {
    Supplier<String> helloSupplier = CrazyLambdas.helloSupplier();

    assertEquals("Hello", helloSupplier.get());
  }


  @Test
  public void testIsEmptyPredicate() {
    Predicate<String> isEmptyPredicate = CrazyLambdas.isEmptyPredicate();

    boolean nonEmptyStringResult = isEmptyPredicate.test("fasdfa");
    boolean emptyStringResult = isEmptyPredicate.test("");

    assertFalse(nonEmptyStringResult);
    assertTrue(emptyStringResult);
  }

  @Test
  public void testToDollarStringFunction() {
    Function<BigDecimal, String> toDollarStringFunction = CrazyLambdas.toDollarStringFunction();
    String tenDollarStr = toDollarStringFunction.apply(BigDecimal.TEN.setScale(2));

    assertEquals("$10.00", tenDollarStr);
  }

  @Test
  public void testLengthInRangePredicate() {
    Predicate<String> lengthInRangePredicate = CrazyLambdas.lengthInRangePredicate(4, 10);

    boolean twoLetterStringResult = lengthInRangePredicate.test("Hi");
    boolean fourLetterStringResult = lengthInRangePredicate.test("Hola");
    boolean fiveLetterStringResult = lengthInRangePredicate.test("Amigo");
    boolean eightLetterStringResult = lengthInRangePredicate.test("Lalaland");
    boolean thirteenLetterStringResult = lengthInRangePredicate.test("Lambda rocks!");

    assertFalse(twoLetterStringResult);
    assertTrue(fourLetterStringResult);
    assertTrue(fiveLetterStringResult);
    assertTrue(eightLetterStringResult);
    assertFalse(thirteenLetterStringResult);
  }

  @Test
  public void testRandomIntSupplier() {
    IntSupplier randomIntSupplier = CrazyLambdas.randomIntSupplier();

    int firstValue = randomIntSupplier.getAsInt();
    int secondValue = randomIntSupplier.getAsInt();

    assertNotEquals(firstValue, secondValue);
  }

  @Test
  public void testBoundedRandomIntSupplier() {
    IntUnaryOperator boundedRandomIntSupplier = CrazyLambdas.boundedRandomIntSupplier();

    int randomIntLessThan10 = boundedRandomIntSupplier.applyAsInt(10);
    int randomIntLessThan100 = boundedRandomIntSupplier.applyAsInt(100);
    int randomIntLessThan1000 = boundedRandomIntSupplier.applyAsInt(1000);
    int randomIntLessThan10000 = boundedRandomIntSupplier.applyAsInt(1000);

    assertTrue(randomIntLessThan10 < 10);
    assertTrue(randomIntLessThan100 < 100);
    assertTrue(randomIntLessThan1000 < 1000);
    assertTrue(randomIntLessThan10000 < 10000);
  }

  @Test
  public void testIntSquareOperation() {
    IntUnaryOperator squareOperation = CrazyLambdas.intSquareOperation();

    int squareOfFour = squareOperation.applyAsInt(4);
    int squareOfZero = squareOperation.applyAsInt(0);

    assertEquals(16, squareOfFour);
    assertEquals(0, squareOfZero);
  }

  @Test
  public void testLongSumOperation() {
    LongBinaryOperator sumOperation = CrazyLambdas.longSumOperation();

    long sumOfSevenAndEight = sumOperation.applyAsLong(7, 8);
    long sumOfTenAndZero = sumOperation.applyAsLong(10, 0);
    long sumOfFiveAndMinusTen = sumOperation.applyAsLong(5, -10);

    assertEquals(15, sumOfSevenAndEight);
    assertEquals(10, sumOfTenAndZero);
    assertEquals(-5, sumOfFiveAndMinusTen);
  }

  @Test
  public void testStringToIntConverter() {
    ToIntFunction<String> stringToIntConverter = CrazyLambdas.stringToIntConverter();

    int num = stringToIntConverter.applyAsInt("234");
    int negativeNum = stringToIntConverter.applyAsInt("-122");

    assertEquals(234, num);
    assertEquals(-122, negativeNum);
  }

  @Test
  public void testNMultiplyFunctionSupplier() {
    Supplier<IntUnaryOperator> fiveMultiplyFunctionSupplier = CrazyLambdas
        .nMultiplyFunctionSupplier(5);

    IntUnaryOperator multiplyByFiveOperation = fiveMultiplyFunctionSupplier.get();
    int result = multiplyByFiveOperation.applyAsInt(11); // 11 * 5 = 55

    assertEquals(55, result);
  }

  @Test
  public void testRunningThreadSupplier() throws InterruptedException {
    Queue<Integer> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
    Supplier<Thread> runningThreadSupplier = CrazyLambdas
        .runningThreadSupplier(() -> concurrentLinkedQueue.add(25));

    // supplier does not create and start a thread before you call get()
    assertEquals(0, concurrentLinkedQueue.size());

    Thread runningThread = runningThreadSupplier.get(); // new thread has been started
    runningThread.join();

    assertEquals(1, concurrentLinkedQueue.size());
    assertEquals(25, concurrentLinkedQueue.element().intValue());
  }

  @Test
  public void testNewThreadRunnableConsumer() throws InterruptedException {
    Consumer<Runnable> newThreadRunnableConsumer = CrazyLambdas.newThreadRunnableConsumer();

    Queue<Integer> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
    newThreadRunnableConsumer.accept(() -> concurrentLinkedQueue.add(50));

    Thread.sleep(500); // don't do that in real code

    assertEquals(1, concurrentLinkedQueue.size());
    assertEquals(50, concurrentLinkedQueue.element().intValue());
  }

  @Test
  public void testRunnableToThreadSupplierFunction() throws InterruptedException {
    Function<Runnable, Supplier<Thread>> runnableSupplierFunction = CrazyLambdas
        .runnableToThreadSupplierFunction();
    Queue<Integer> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();

    Supplier<Thread> threadSupplier = runnableSupplierFunction
        .apply(() -> concurrentLinkedQueue.add(200));

    assertEquals(0, concurrentLinkedQueue
        .size()); // supplier does not create and start a thread before you call get()

    Thread thread = threadSupplier.get();// new thread has been started
    thread.join();

    assertEquals(1, concurrentLinkedQueue.size());
    assertEquals(200, concurrentLinkedQueue.element().intValue());
  }

  @Test
  public void testFunctionToConditionalFunction() {
    BiFunction<IntUnaryOperator, IntPredicate, IntUnaryOperator> intFunctionToConditionalIntFunction
        = CrazyLambdas.functionToConditionalFunction();

    IntUnaryOperator abs = intFunctionToConditionalIntFunction.apply(a -> -a, a -> a < 0);

    assertEquals(5, abs.applyAsInt(-5));
    assertEquals(0, abs.applyAsInt(0));
    assertEquals(5, abs.applyAsInt(5));
  }

  @Test
  public void testTrickyWellDoneSupplier() {
    Supplier<Supplier<Supplier<String>>> wellDoneSupplier = CrazyLambdas.trickyWellDoneSupplier();

    String wellDoneStr = wellDoneSupplier.get().get().get();

    assertEquals("WELL DONE!", wellDoneStr);
  }
}