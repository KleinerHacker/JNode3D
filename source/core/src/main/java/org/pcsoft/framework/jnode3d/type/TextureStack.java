package org.pcsoft.framework.jnode3d.type;

import org.pcsoft.framework.jnode3d.internal.NGL;

public enum TextureStack {
    Texture0(NGL.GL_TEXTURE0),
    Texture1(NGL.GL_TEXTURE1),
    Texture2(NGL.GL_TEXTURE2),
    Texture3(NGL.GL_TEXTURE3),
    Texture4(NGL.GL_TEXTURE4),
    Texture5(NGL.GL_TEXTURE5),
    Texture6(NGL.GL_TEXTURE6),
    Texture7(NGL.GL_TEXTURE7),
    Texture8(NGL.GL_TEXTURE8),
    Texture9(NGL.GL_TEXTURE9),
    Texture10(NGL.GL_TEXTURE10),
    Texture11(NGL.GL_TEXTURE11),
    Texture12(NGL.GL_TEXTURE12),
    Texture13(NGL.GL_TEXTURE13),
    Texture14(NGL.GL_TEXTURE14),
    Texture15(NGL.GL_TEXTURE15),
    Texture16(NGL.GL_TEXTURE16),
    Texture17(NGL.GL_TEXTURE17),
    Texture18(NGL.GL_TEXTURE18),
    Texture19(NGL.GL_TEXTURE19),
    Texture20(NGL.GL_TEXTURE20),
    Texture21(NGL.GL_TEXTURE21),
    Texture22(NGL.GL_TEXTURE22),
    Texture23(NGL.GL_TEXTURE23),
    Texture24(NGL.GL_TEXTURE24),
    Texture25(NGL.GL_TEXTURE25),
    Texture26(NGL.GL_TEXTURE26),
    Texture27(NGL.GL_TEXTURE27),
    Texture28(NGL.GL_TEXTURE28),
    Texture29(NGL.GL_TEXTURE29),
    Texture30(NGL.GL_TEXTURE30),
    Texture31(NGL.GL_TEXTURE31),
    ;
    
    private final int value;

    TextureStack(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
