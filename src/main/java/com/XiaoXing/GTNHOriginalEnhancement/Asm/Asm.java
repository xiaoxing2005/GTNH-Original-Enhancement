package com.XiaoXing.GTNHOriginalEnhancement.Asm;

import com.XiaoXing.GTNHOriginalEnhancement.Common.machine.Mixin.IndustrialFreezer_Mixin;
import gtPlusPlus.xmod.gregtech.common.tileentities.machines.multi.processing.GregtechMetaTileEntity_IndustrialVacuumFreezer;
import net.minecraft.launchwrapper.IClassTransformer;
import org.spongepowered.asm.lib.ClassReader;
import org.spongepowered.asm.lib.ClassVisitor;
import org.spongepowered.asm.lib.ClassWriter;
import org.spongepowered.asm.lib.MethodVisitor;
import org.spongepowered.asm.lib.Opcodes;
import org.spongepowered.asm.lib.commons.AdviceAdapter;
import org.spongepowered.asm.lib.util.TraceClassVisitor;

import java.io.PrintWriter;


public class Asm implements IClassTransformer {
    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (transformedName.equals("gtPlusPlus.xmod.gregtech.registration.gregtech.GregtechFactoryGradeReplacementMultis")){
            return transformeVacuumFreezer(basicClass);
        }
        return basicClass;
    }

    private byte[] transformeVacuumFreezer(byte[] basicClass){
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
        public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
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
            if (opcode == Opcodes.NEW && "gtPlusPlus/xmod/gregtech/common/tileentities/machines/multi/processing/GregtechMetaTileEntity_IndustrialVacuumFreezer".equals(type)) {
                super.visitTypeInsn(opcode, "com/XiaoXing/GTNHOriginalEnhancement/Common/machine/Mixin/IndustrialFreezer_Mixin");
            } else {
                super.visitTypeInsn(opcode, type);
            }
        }

        @Override
        public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
            if (opcode == Opcodes.INVOKESPECIAL && owner.equals("gtPlusPlus/xmod/gregtech/common/tileentities/machines/multi/processing/GregtechMetaTileEntity_IndustrialVacuumFreezer") && name.equals("<init>")) {
                super.visitMethodInsn(opcode, "com/XiaoXing/GTNHOriginalEnhancement/Common/machine/Mixin/IndustrialFreezer_Mixin", "<init>", descriptor, isInterface);
            }else if (opcode == Opcodes.INVOKEVIRTUAL && owner.equals("gtPlusPlus/xmod/gregtech/common/tileentities/machines/multi/processing/GregtechMetaTileEntity_IndustrialVacuumFreezer") && name.equals("getStackForm")){
                super.visitMethodInsn(opcode, "com/XiaoXing/GTNHOriginalEnhancement/Common/machine/Mixin/IndustrialFreezer_Mixin", "getStackForm", descriptor, isInterface);
            }
            else {
                super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
            }
        }
    }
}

