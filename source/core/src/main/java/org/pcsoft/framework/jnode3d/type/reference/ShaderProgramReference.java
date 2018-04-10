package org.pcsoft.framework.jnode3d.type.reference;

import java.util.Objects;

public final class ShaderProgramReference {
    private final int programId;

    public ShaderProgramReference(int programId) {
        this.programId = programId;
    }

    public int getProgramId() {
        return programId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShaderProgramReference that = (ShaderProgramReference) o;
        return programId == that.programId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(programId);
    }
}