package com.simon.asm.core.asm;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import static org.objectweb.asm.Opcodes.ASM7;

/**
 * @author Simon
 * @date 2020/8/24
 * @desc
 */
public class AsmMethodVisitor extends MethodVisitor {

    private String className;
    private String methodName;

    public AsmMethodVisitor(MethodVisitor methodVisitor, String className, String methodName) {
        super(ASM7, methodVisitor);
        this.className = className;
        this.methodName = methodName;
        System.out.println("AsmMethodVisitor visit className-------" + className + ", methodName is " + methodName);
    }

    /**
     * 方法执行前插入
     */
    @Override
    public void visitCode() {
        super.visitCode();
        System.out.println("AsmMethodVisitor --- visitCode --- enter");
        mv.visitLdcInsn("STAG");
        mv.visitLdcInsn(className + "---->" + methodName);
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "android/util/Log", "i", "(Ljava/lang/String;Ljava/lang/String;)I", false);
        mv.visitInsn(Opcodes.POP);
    }
}
