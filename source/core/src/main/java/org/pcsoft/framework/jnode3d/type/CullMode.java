package org.pcsoft.framework.jnode3d.type;

public enum CullMode {
    None(0x00),
    Front(0x01),
    Back(0x02),
    Both(0x03),
    ;

    public static CullMode fromValue(int value) {
        for (final CullMode cullMode : values()) {
            if (cullMode.value == value)
                return cullMode;
        }

        return null;
    }

    private final int value;

    CullMode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
