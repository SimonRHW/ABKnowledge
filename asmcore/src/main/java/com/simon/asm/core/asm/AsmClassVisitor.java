package com.simon.asm.core.asm;

import com.simon.asm.core.mapping.MappingDictionary;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author Simon
 * @date 2020/8/24
 * @desc
 */
public class AsmClassVisitor extends ClassVisitor implements Opcodes {


    private String className;
    private String superName;

    public AsmClassVisitor(ClassVisitor classVisitor) {
        super(ASM7, classVisitor);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        this.className = name;
        this.superName = superName;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        System.out.println("ClassVisitor visitMethod name-------" + name + ", superName is " + superName);
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);

        if (superName.equals(MappingDictionary.ANDROIDX_ACTIVITY)) {
            if (name.startsWith(MappingDictionary.ACTIVITY_ON_CREATE)) {
                //处理onCreate()方法
                return new AsmMethodVisitor(mv, className, name);
            }
        }
        return mv;
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
    }
}
