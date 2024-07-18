package com.XiaoXing.GTNHOriginalEnhancement.Asm;

import java.io.PrintWriter;

import net.minecraft.launchwrapper.IClassTransformer;

import org.spongepowered.asm.lib.ClassReader;
import org.spongepowered.asm.lib.ClassVisitor;
import org.spongepowered.asm.lib.ClassWriter;
import org.spongepowered.asm.lib.MethodVisitor;
import org.spongepowered.asm.lib.Opcodes;
import org.spongepowered.asm.lib.commons.AdviceAdapter;
import org.spongepowered.asm.lib.util.TraceClassVisitor;

import com.XiaoXing.GTNHOriginalEnhancement.Common.machine.GT_MetaTileEntity_EM_EyeOfHarmonyInjector;

import gtPlusPlus.xmod.gregtech.common.tileentities.machines.multi.processing.GregtechMetaTileEntity_IndustrialVacuumFreezer;

public class Asm implements IClassTransformer {

    public static final String INDUSTRIALVACUUMFREEZER = transformClassName(
        GregtechMetaTileEntity_IndustrialVacuumFreezer.class.getName());
    public static final String INDUSTRIALVACUUMFREEZER_ASM = transformClassName(
        GT_MetaTileEntity_EM_EyeOfHarmonyInjector.class.getName());

    public static String transformClassName(String name) {
        return name.replace(".", "/");
    }

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (transformedName
            .equals("gtPlusPlus.xmod.gregtech.registration.gregtech.GregtechFactoryGradeReplacementMultis")) {
            return transformVacuumFreezer(basicClass);
        }
        return basicClass;
    }

    private byte[] transformVacuumFreezer(byte[] basicClass) {
        ClassReader classReader = new ClassReader(basicClass);
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        ClassVisitor classVisitor = new MyClassVisitor(classWriter);
        classReader.accept(classVisitor, ClassReader.EXPAND_FRAMES);
        classReader.accept(new TraceClassVisitor(new PrintWriter(System.out)), 0);
        return classWriter.toByteArray();
    }

    private static class MyClassVisitor extends ClassVisitor {

        public MyClassVisitor(ClassVisitor cv) {
            super(Opcodes.ASM5, cv);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String descriptor, String signature,
            String[] exceptions) {
            MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
            if ("run1".equals(name)) {
                return new MyMethodVisitor(mv, access, name, descriptor);
            }
            return mv;
        }
    }

    private static class MyMethodVisitor extends AdviceAdapter {

        protected MyMethodVisitor(MethodVisitor mv, int access, String name, String descriptor) {
            super(Opcodes.ASM5, mv, access, name, descriptor);
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
}
