package com.simon.asm.core.asm;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author Simon
 * @date 2020/8/24
 * @desc
 */
public class AsmAnnotationVisitor extends AnnotationVisitor implements Opcodes {

    public AsmAnnotationVisitor(AnnotationVisitor annotationVisitor) {
        super(ASM7, annotationVisitor);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String name, String descriptor) {
        System.out.println("visitAnnotation --- visitCode --- enter");
        System.out.println("name: " + name + " ,descriptor: " + descriptor);
        return av;
    }
}
