package com.XiaoXing.GTNHOriginalEnhancement.Asm.VacuumFreezerASM;

import org.spongepowered.asm.lib.ClassVisitor;
import org.spongepowered.asm.lib.MethodVisitor;
import org.spongepowered.asm.lib.Opcodes;

public class VacuumFreezerClassVisitor extends ClassVisitor {

    private final String name;

    public VacuumFreezerClassVisitor(ClassVisitor classVisitor, String name) {
        super(Opcodes.ASM5, classVisitor);
        this.name = name;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature,
        String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
        if (this.name.equals(name)) {
            return new VacuumFreezerMethodVisitor(mv, access, name, descriptor);
        }
        return mv;
    }
}
