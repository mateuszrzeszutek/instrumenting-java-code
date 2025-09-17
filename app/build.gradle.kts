plugins {
  application
//  id("instrumentation.compile-time")
}

val main = "io.rzeszut.example.App"

application {
  mainClass.set(main)
}

val javaagent by configurations.creating {
  isCanBeResolved = true
  isCanBeConsumed = false
}

dependencies {
  javaagent(project(":auto:javaagent", configuration = "shadow"))
}

tasks {
  jar {
    manifest {
      attributes("Main-Class" to main)
    }
  }

  val runWithJavaagent by registering(JavaExec::class) {
    dependsOn(javaagent)

    classpath = files(named("jar").get())
    jvmArgs("-javaagent:${javaagent.resolve().first()}")
    jvmArgs("-Dnet.bytebuddy.safe=true")
  }
}
