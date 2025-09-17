package io.rzeszut.instrumentingjavacode.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Ex3_CustomBytecode {

  @Test
  void generates_method_with_custom_bytecode() throws Exception {
    try (var unloadedClass = new ByteBuddy()
        .subclass(Object.class)
        .defineMethod("add", int.class, List.of(Visibility.PUBLIC))
        .withParameters(int.class)
        .intercept(new AddImpl())
        .make()) {

      var generatedClass = unloadedClass.load(getClass().getClassLoader()).getLoaded();
      var helloMethod = generatedClass.getDeclaredMethod("add", int.class);

      var instance = generatedClass.getConstructor().newInstance();
      assertEquals(42, helloMethod.invoke(instance, 32));
    }
  }

  static class AddImpl implements Implementation {

    @Override
    public ByteCodeAppender appender(Target implementationTarget) {
      return new AddByteCodeAppender();
    }

    @Override
    public InstrumentedType prepare(InstrumentedType instrumentedType) {
      return instrumentedType;
    }
  }

  static class AddByteCodeAppender implements ByteCodeAppender {

    @Override
    public Size apply(MethodVisitor mv, Implementation.Context implementationContext, MethodDescription instrumentedMethod) {
      mv.visitLdcInsn(10);
      mv.visitVarInsn(Opcodes.ILOAD, 1);
      mv.visitInsn(Opcodes.IADD);
      mv.visitInsn(Opcodes.IRETURN);
      return new Size(2 /* max stack size */, 2 /* local vars: this, i */);
    }
  }
}
