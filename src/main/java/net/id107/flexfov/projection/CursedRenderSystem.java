package net.id107.flexfov.projection;

import org.lwjgl.opengl.GL30;

import com.mojang.blaze3d.systems.RenderSystem;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public class CursedRenderSystem {
    public static final String APOLOGY = "im so sorry";
    
    public static void matrixMode(int mode) {
        RenderSystem.assertOnGameThread();
        //now from GlStateManager
        RenderSystem.assertOnRenderThreadOrInit();
        GL30.glMatrixMode(mode);
    }
    
    public static void loadIdentity() {
        RenderSystem.assertOnGameThread();
        //now from GlStateManager
        RenderSystem.assertOnRenderThreadOrInit();
        GL30.glLoadIdentity();
    }
    
    public static void ortho(double l, double r, double b, double t, double n, double f) {
        RenderSystem.assertOnGameThread();
        //now from GlStateManager
        RenderSystem.assertOnRenderThreadOrInit();
        GL30.glOrtho(l, r, b, t, n, f);
    }
    
    public static void translatef(float x, float y, float z) {
        RenderSystem.assertOnGameThread();
        //now from GlStateManager
        RenderSystem.assertOnRenderThreadOrInit();
        GL30.glTranslatef(x, y, z);
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
    
    public static void alphaFunc(int func, float ref) {
        RenderSystem.assertOnGameThread();
        //now from GlStateManager
        RenderSystem.assertOnRenderThreadOrInit();
        if (func != ALPHA_TEST.func || ref != ALPHA_TEST.ref) {
            ALPHA_TEST.func = func;
            ALPHA_TEST.ref = ref;
            GL30.glAlphaFunc(func, ref);
        }
    }
    
    public static void defaultAlphaFunc() {
        alphaFunc(516, 0.1f);
    }
}