package io.rzeszut.instrumentingjavacode.manual;

import java.util.function.Supplier;

record TimedSupplier<T>(Supplier<T> delegate) implements Supplier<T> {

  @Override
  public T get() {
    var startNanos = System.nanoTime();
    try {
      return delegate.get();
    } finally {
      var endNanos = System.nanoTime();
      System.out.println("Time: " + (endNanos - startNanos) + "ns");
    }
  }
}
