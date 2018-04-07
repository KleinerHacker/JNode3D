package org.pcsoft.framework.jnode3d.type;

import org.pcsoft.framework.jnode3d.internal.ogl.NativeGL;

public enum TextureStack {
    Texture0(NativeGL.GL_TEXTURE0),
    Texture1(NativeGL.GL_TEXTURE1),
    Texture2(NativeGL.GL_TEXTURE2),
    Texture3(NativeGL.GL_TEXTURE3),
    Texture4(NativeGL.GL_TEXTURE4),
    Texture5(NativeGL.GL_TEXTURE5),
    Texture6(NativeGL.GL_TEXTURE6),
    Texture7(NativeGL.GL_TEXTURE7),
    Texture8(NativeGL.GL_TEXTURE8),
    Texture9(NativeGL.GL_TEXTURE9),
    Texture10(NativeGL.GL_TEXTURE10),
    Texture11(NativeGL.GL_TEXTURE11),
    Texture12(NativeGL.GL_TEXTURE12),
    Texture13(NativeGL.GL_TEXTURE13),
    Texture14(NativeGL.GL_TEXTURE14),
    Texture15(NativeGL.GL_TEXTURE15),
    Texture16(NativeGL.GL_TEXTURE16),
    Texture17(NativeGL.GL_TEXTURE17),
    Texture18(NativeGL.GL_TEXTURE18),
    Texture19(NativeGL.GL_TEXTURE19),
    Texture20(NativeGL.GL_TEXTURE20),
    Texture21(NativeGL.GL_TEXTURE21),
    Texture22(NativeGL.GL_TEXTURE22),
    Texture23(NativeGL.GL_TEXTURE23),
    Texture24(NativeGL.GL_TEXTURE24),
    Texture25(NativeGL.GL_TEXTURE25),
    Texture26(NativeGL.GL_TEXTURE26),
    Texture27(NativeGL.GL_TEXTURE27),
    Texture28(NativeGL.GL_TEXTURE28),
    Texture29(NativeGL.GL_TEXTURE29),
    Texture30(NativeGL.GL_TEXTURE30),
    Texture31(NativeGL.GL_TEXTURE31),
    ;
    
    private final int value;

    TextureStack(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
