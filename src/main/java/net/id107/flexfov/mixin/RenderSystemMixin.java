package net.id107.flexfov.mixin;

import com.mojang.blaze3d.platform.GlStateManager;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import com.mojang.blaze3d.systems.RenderSystem;
import net.id107.flexfov.access.GlStateManagerAccess;
import java.lang.Override;

@Mixin(GlStateManager.class) // ¯\_(ツ)_/¯
public abstract class RenderSystemMixin implements GlStateManagerAccess {
    @Override
    public void matrixMode(int mode) {
        RenderSystem.assertOnGameThread();
        //now from GlStateManager
        RenderSystem.assertOnRenderThreadOrInit();
        GL11.glMatrixMode(mode);
    }
}
