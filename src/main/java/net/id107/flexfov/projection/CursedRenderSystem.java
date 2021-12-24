package net.id107.flexfov.projection;

import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL11;

import net.id107.flexfov.access.GlStateManagerAccess;

import com.mojang.blaze3d.systems.RenderSystem;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.util.math.MatrixStack;

public class CursedRenderSystem {
    public static final String APOLOGY = "im so sorry";
    MatrixStack matrixStack = new MatrixStack();
    private RenderSystem renderSystem;
    //private Object RenderSystem;

    public void pushMatrix() {

    }



    public void matrixMode(int mode) {
        ((GlStateManagerAccess)renderSystem).matrixMode(mode);
        /*
        RenderSystem.assertOnGameThread();
        //now from GlStateManager
        RenderSystem.assertOnRenderThreadOrInit();
        GL11.glMatrixMode(mode);
        */
    }

    public void loadIdentity() {
        matrixStack.loadIdentity();
        /*
        RenderSystem.assertOnGameThread();
        //now from GlStateManager
        RenderSystem.assertOnRenderThreadOrInit();
        GL30.glLoadIdentity();
        */
    }
    
    public void ortho(double l, double r, double b, double t, double n, double f) {
        System.out.println("FUCK YOU!!!!!");
        /*
        RenderSystem.assertOnGameThread();
        //now from GlStateManager
        RenderSystem.assertOnRenderThreadOrInit();
        GL30.glOrtho(l, r, b, t, n, f);*/
    }

    public void translatef(float x, float y, float z) {
        matrixStack.translate(x, y, z);
        /*
        RenderSystem.assertOnGameThread();
        //now from GlStateManager
        RenderSystem.assertOnRenderThreadOrInit();
        GL30.glTranslatef(x, y, z);
        */
    }

    @Environment(value=EnvType.CLIENT)
    static class CapabilityTracker {
        private final int cap;
        private boolean state;

        public CapabilityTracker(int cap) {
            this.cap = cap;
        }

        public void disable() {
            this.setState(false);
        }

        public void enable() {
            this.setState(true);
        }

        public void setState(boolean state) {
            RenderSystem.assertOnRenderThreadOrInit();
            if (state != this.state) {
                this.state = state;
                if (state) {
                    GL30.glEnable(this.cap);
                } else {
                    GL30.glDisable(this.cap);
                }
            }
        }
    }

    @Environment(value=EnvType.CLIENT)
    static class AlphaTestState {
        public final CapabilityTracker capState = new CapabilityTracker(3008);
        public int func = 519;
        public float ref = -1.0f;

        private AlphaTestState() {
        }
    }
    
    private static final AlphaTestState ALPHA_TEST = new AlphaTestState();
    
    public void alphaFunc(int func, float ref) {
        RenderSystem.assertOnGameThread();
        //now from GlStateManager
        RenderSystem.assertOnRenderThreadOrInit();
        if (func != ALPHA_TEST.func || ref != ALPHA_TEST.ref) {
            ALPHA_TEST.func = func;
            ALPHA_TEST.ref = ref;
            GL30.glAlphaFunc(func, ref);
        }
    }
    
    public void defaultAlphaFunc() {
        alphaFunc(516, 0.1f);
    }
}