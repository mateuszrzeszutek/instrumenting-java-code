rootProject.name = "instrumenting-java-code"

include(
  "app",
  "manual",
  "auto:proxy",
  "auto:aspectj",
  "auto:bytebuddy",
  "auto:javaagent",
  "auto:compile-time"
)