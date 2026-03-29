# Instrumenting Java Code

This talk is about the various ways and methods of instrumenting Java code.

We will:
- Explore several different methods, concentrating on code generation;
- Dive deeper into ByteBuddy;
- Create a simple javaagent;
- Instrument a Java application in build time.

All with actual running code examples.

## Tags

java, jvm, javaagent, instrumentation

## Building slides

```sh
make install-deps
make
```

## Installing necessary tools

Make sure mise is installed (e.g. `brew install mise`, `sudo dnf -y install mise`).

```sh
mise install
```
