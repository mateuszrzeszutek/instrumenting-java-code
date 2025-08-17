package io.rzeszut.instrumentingjavacode.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;

record TimedStatementHandler(PreparedStatement delegate) implements InvocationHandler {

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    if (method.getName().startsWith("execute")) {
      var startNanos = System.nanoTime();
      try {
        return method.invoke(delegate, args);
      } finally {
        var endNanos = System.nanoTime();
        System.out.println("Time: " + (endNanos - startNanos) + "ns");
      }
    }
    return method.invoke(delegate, args);
  }
}
