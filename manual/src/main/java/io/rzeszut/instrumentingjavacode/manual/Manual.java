package io.rzeszut.instrumentingjavacode.manual;

import java.util.function.Supplier;

public class Manual {

  public Supplier<String> getGreetingSupplier() {
    return () -> "Hello World!";
  }

  public static void main(String[] args) {
    var greetingSupplier = new Manual().getGreetingSupplier();

    greetingSupplier = new TimedSupplier<>(greetingSupplier);

    System.out.println(greetingSupplier.get());
  }
}
