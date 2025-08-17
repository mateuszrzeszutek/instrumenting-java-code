package io.rzeszut.instrumentingjavacode.aspectj;

import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

public class AspectJCodeGen {

  @Timed
  public String getGreeting() {
    return "Hello World!";
  }

  public static void main(String[] args) {
    var app = new AspectJCodeGen();

    app = instrument(app);

    System.out.println(app.getGreeting());
  }

  static AspectJCodeGen instrument(AspectJCodeGen app) {
    var proxyFactory = new AspectJProxyFactory(app);
    proxyFactory.addAspect(new TimedAspect());
    return proxyFactory.getProxy();
  }
}
