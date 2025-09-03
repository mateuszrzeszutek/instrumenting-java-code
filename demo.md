# Demo

## Manual

```shell
./gradlew -q :manual:run
```

## Proxy

```shell
./gradlew -q :auto:proxy:run
```

## AspectJ

```shell
./gradlew -q :auto:aspectj:run
```

## Uninstrumented app

```shell
./gradlew -q :app:run
```

## App instrumented with javaagent

```shell
./gradlew -q :app:runWithJavaagent
```

## App instrumented with compile-time instrumentation

Uncomment the `instrumentation.compile-time` plugin, and run

```shell
./gradlew -q :app:run
```
