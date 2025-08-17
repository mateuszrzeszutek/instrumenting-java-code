package io.rzeszut.instrumentingjavacode.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class TimedAspect {

  @Around("@annotation(io.rzeszut.instrumentingjavacode.aspectj.Timed)")
  public Object instrumentMethod(ProceedingJoinPoint pjp) throws Throwable {
    var startNanos = System.nanoTime();
    try {
      return pjp.proceed();
    } finally {
      var endNanos = System.nanoTime();
      System.out.println("Time: " + (endNanos - startNanos) + "ns");
    }
  }
}
