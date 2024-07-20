package com.XiaoXing.GTNHOriginalEnhancement.Asm;

import static com.XiaoXing.GTNHOriginalEnhancement.Config.Config.Enable_IndustrialFreezer_ASM;

import java.io.PrintWriter;

import net.minecraft.launchwrapper.IClassTransformer;

import org.spongepowered.asm.lib.ClassReader;
import org.spongepowered.asm.lib.ClassVisitor;
import org.spongepowered.asm.lib.ClassWriter;
import org.spongepowered.asm.lib.util.TraceClassVisitor;

import com.XiaoXing.GTNHOriginalEnhancement.Asm.VacuumFreezerASM.VacuumFreezerClassVisitor;

public class Asm implements IClassTransformer {

    public static final String INDUSTRIALVACUUMFREEZER = transformClassName(
        "gtPlusPlus.xmod.gregtech.common.tileentities.machines.multi.processing.GregtechMetaTileEntity_IndustrialVacuumFreezer");
    public static final String INDUSTRIALVACUUMFREEZER_ASM = transformClassName(
        "com.XiaoXing.GTNHOriginalEnhancement.Common.machine.ASM.IndustrialFreezer_ASM");

    public static String transformClassName(String name) {
        return name.replace(".", "/");
    }

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {

        switch (transformedName) {
            case "gtPlusPlus.xmod.gregtech.registration.gregtech.GregtechFactoryGradeReplacementMultis" -> {
                if (!Enable_IndustrialFreezer_ASM) {
                    return basicClass;
                }
                ClassReader classReader = new ClassReader(basicClass);
                ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
                ClassVisitor classVisitor = new VacuumFreezerClassVisitor(classWriter, "run1");
                return transformVacuumFreezer(classReader, classWriter, classVisitor);
            }
        }
        return basicClass;
    }

    private byte[] transformVacuumFreezer(ClassReader classReader, ClassWriter classWriter, ClassVisitor classVisitor) {
        classReader.accept(classVisitor, ClassReader.EXPAND_FRAMES);
        classReader.accept(new TraceClassVisitor(new PrintWriter(System.out)), 0);
        return classWriter.toByteArray();
    }

}
