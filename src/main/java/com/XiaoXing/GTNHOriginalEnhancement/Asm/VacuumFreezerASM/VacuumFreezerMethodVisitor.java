package com.XiaoXing.GTNHOriginalEnhancement.Asm.VacuumFreezerASM;

import static com.XiaoXing.GTNHOriginalEnhancement.Asm.Asm.INDUSTRIALVACUUMFREEZER;
import static com.XiaoXing.GTNHOriginalEnhancement.Asm.Asm.INDUSTRIALVACUUMFREEZER_ASM;

import org.spongepowered.asm.lib.MethodVisitor;
import org.spongepowered.asm.lib.Opcodes;
import org.spongepowered.asm.lib.commons.AdviceAdapter;

public class VacuumFreezerMethodVisitor extends AdviceAdapter {

    public VacuumFreezerMethodVisitor(MethodVisitor methodVisitor, int access, String name, String descriptor) {
        super(Opcodes.ASM5, methodVisitor, access, name, descriptor);
    }

    @Override
    public void visitTypeInsn(int opcode, String type) {
        if (opcode == Opcodes.NEW && INDUSTRIALVACUUMFREEZER.equals(type)) {
            super.visitTypeInsn(opcode, INDUSTRIALVACUUMFREEZER_ASM);
        } else {
            super.visitTypeInsn(opcode, type);
        }
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
        if (opcode == Opcodes.INVOKESPECIAL && owner.equals(INDUSTRIALVACUUMFREEZER) && name.equals("<init>")) {
            super.visitMethodInsn(opcode, INDUSTRIALVACUUMFREEZER_ASM, "<init>", descriptor, isInterface);
        } else if (opcode == Opcodes.INVOKEVIRTUAL && owner.equals(INDUSTRIALVACUUMFREEZER)
            && name.equals("getStackForm")) {
                super.visitMethodInsn(opcode, INDUSTRIALVACUUMFREEZER_ASM, "getStackForm", descriptor, isInterface);
            } else {
                super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
            }
    }
}
