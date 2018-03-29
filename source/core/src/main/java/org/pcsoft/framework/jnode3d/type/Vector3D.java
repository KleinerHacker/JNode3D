package org.pcsoft.framework.jnode3d.type;

import java.util.Objects;

public final class Vector3D {
    public static final Vector3D ZERO = new Vector3D(0f, 0f, 0f);
    public static final Vector3D X = new Vector3D(1f, 0f, 0f);
    public static final Vector3D Y = new Vector3D(0f, 1f, 0f);
    public static final Vector3D Z = new Vector3D(0f, 0f, 1f);

    private final float x, y, z;

    public Vector3D(Vector3D vector3D) {
        this(vector3D.x, vector3D.y, vector3D.z);
    }

    public Vector3D(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public Vector3D add(Vector3D vector) {
        return this.add(vector.x, vector.y, vector.z);
    }

    public Vector3D add(float x, float y, float z) {
        return new Vector3D(this.x + x, this.y + y, this.z + z);
    }

    public Vector3D add(float values) {
        return new Vector3D(this.x + values, this.y + values, this.z + values);
    }

    public Vector3D sub(Vector3D a_vec) {
        return this.sub(a_vec.x, a_vec.y, a_vec.z);
    }

    public Vector3D sub(float x, float y, float z) {
        return new Vector3D(this.x - x, this.y - y, this.z - z);
    }

    public Vector3D sub(float value) {
        return new Vector3D(this.x - value, this.y - value, this.z - value);
    }

    public Vector3D scl(float scalar) {
        return new Vector3D(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    public Vector3D scl(Vector3D other) {
        return new Vector3D(this.x * other.x, this.y * other.y, this.z * other.z);
    }

    public Vector3D scl(float vx, float vy, float vz) {
        return new Vector3D(this.x * vx, this.y * vy, this.z * vz);
    }

    public Vector3D mulAdd(Vector3D vec, float scalar) {
        return new Vector3D(this.x + vec.x * scalar, this.y + vec.y * scalar, this.z + vec.z * scalar);
    }

    public Vector3D mulAdd(Vector3D vec, Vector3D mulVec) {
        return new Vector3D(this.x + vec.x * mulVec.x, this.y + vec.y * mulVec.y, this.z + vec.z * mulVec.z);
    }

    public static float len(float x, float y, float z) {
        return (float)Math.sqrt((double)(x * x + y * y + z * z));
    }

    public float len() {
        return (float)Math.sqrt((double)(this.x * this.x + this.y * this.y + this.z * this.z));
    }

    public static float len2(float x, float y, float z) {
        return x * x + y * y + z * z;
    }

    public float len2() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }

    public boolean idt(Vector3D vector) {
        return this.x == vector.x && this.y == vector.y && this.z == vector.z;
    }

    public static float dst(float x1, float y1, float z1, float x2, float y2, float z2) {
        float a = x2 - x1;
        float b = y2 - y1;
        float c = z2 - z1;
        return (float)Math.sqrt((double)(a * a + b * b + c * c));
    }

    public float dst(Vector3D vector) {
        float a = vector.x - this.x;
        float b = vector.y - this.y;
        float c = vector.z - this.z;
        return (float)Math.sqrt((double)(a * a + b * b + c * c));
    }

    public float dst(float x, float y, float z) {
        float a = x - this.x;
        float b = y - this.y;
        float c = z - this.z;
        return (float)Math.sqrt((double)(a * a + b * b + c * c));
    }

    public static float dst2(float x1, float y1, float z1, float x2, float y2, float z2) {
        float a = x2 - x1;
        float b = y2 - y1;
        float c = z2 - z1;
        return a * a + b * b + c * c;
    }

    public float dst2(Vector3D point) {
        float a = point.x - this.x;
        float b = point.y - this.y;
        float c = point.z - this.z;
        return a * a + b * b + c * c;
    }

    public float dst2(float x, float y, float z) {
        float a = x - this.x;
        float b = y - this.y;
        float c = z - this.z;
        return a * a + b * b + c * c;
    }

    public Vector3D nor() {
        float len2 = this.len2();
        return len2 != 0.0F && len2 != 1.0F ? this.scl(1.0F / (float)Math.sqrt((double)len2)) : this;
    }

    public static float dot(float x1, float y1, float z1, float x2, float y2, float z2) {
        return x1 * x2 + y1 * y2 + z1 * z2;
    }

    public float dot(Vector3D vector) {
        return this.x * vector.x + this.y * vector.y + this.z * vector.z;
    }

    public float dot(float x, float y, float z) {
        return this.x * x + this.y * y + this.z * z;
    }

    public Vector3D crs(Vector3D vector) {
        return new Vector3D(this.y * vector.z - this.z * vector.y, this.z * vector.x - this.x * vector.z, this.x * vector.y - this.y * vector.x);
    }

    public Vector3D crs(float x, float y, float z) {
        return new Vector3D(this.y * z - this.z * y, this.z * x - this.x * z, this.x * y - this.y * x);
    }

    public boolean isUnit() {
        return this.isUnit(1.0E-9F);
    }

    public boolean isUnit(float margin) {
        return Math.abs(this.len2() - 1.0F) < margin;
    }

    public boolean isZero() {
        return this.x == 0.0F && this.y == 0.0F && this.z == 0.0F;
    }

    public boolean isZero(float margin) {
        return this.len2() < margin;
    }

    public boolean isOnLine(Vector3D other, float epsilon) {
        return len2(this.y * other.z - this.z * other.y, this.z * other.x - this.x * other.z, this.x * other.y - this.y * other.x) <= epsilon;
    }

    public boolean isOnLine(Vector3D other) {
        return len2(this.y * other.z - this.z * other.y, this.z * other.x - this.x * other.z, this.x * other.y - this.y * other.x) <= 1.0E-6F;
    }

    public boolean isCollinear(Vector3D other, float epsilon) {
        return this.isOnLine(other, epsilon) && this.hasSameDirection(other);
    }

    public boolean isCollinear(Vector3D other) {
        return this.isOnLine(other) && this.hasSameDirection(other);
    }

    public boolean isCollinearOpposite(Vector3D other, float epsilon) {
        return this.isOnLine(other, epsilon) && this.hasOppositeDirection(other);
    }

    public boolean isCollinearOpposite(Vector3D other) {
        return this.isOnLine(other) && this.hasOppositeDirection(other);
    }

    public boolean hasSameDirection(Vector3D vector) {
        return this.dot(vector) > 0.0F;
    }

    public boolean hasOppositeDirection(Vector3D vector) {
        return this.dot(vector) < 0.0F;
    }

    public Vector3D lerp(Vector3D target, float alpha) {
        return new Vector3D(this.x + alpha * (target.x - this.x), this.y + alpha * (target.y - this.y), this.z + alpha * (target.z - this.z));
    }

    public Vector3D slerp(Vector3D target, float alpha) {
        float dot = this.dot(target);
        if ((double)dot <= 0.9995D && (double)dot >= -0.9995D) {
            float theta0 = (float)Math.acos((double)dot);
            float theta = theta0 * alpha;
            float st = (float)Math.sin((double)theta);
            float tx = target.x - this.x * dot;
            float ty = target.y - this.y * dot;
            float tz = target.z - this.z * dot;
            float l2 = tx * tx + ty * ty + tz * tz;
            float dl = st * (l2 < 1.0E-4F ? 1.0F : 1.0F / (float)Math.sqrt((double)l2));
            return this.scl((float)Math.cos((double)theta)).add(tx * dl, ty * dl, tz * dl).nor();
        } else {
            return this.lerp(target, alpha);
        }
    }

    public Vector3D limit(float limit) {
        return this.limit2(limit * limit);
    }

    public Vector3D limit2(float limit2) {
        float len2 = this.len2();
        if (len2 > limit2) {
            this.scl((float)Math.sqrt((double)(limit2 / len2)));
        }

        return this;
    }

    public Vector3D setLength(float len) {
        return this.setLength2(len * len);
    }

    public Vector3D setLength2(float len2) {
        float oldLen2 = this.len2();
        return oldLen2 != 0.0F && oldLen2 != len2 ? this.scl((float)Math.sqrt((double)(len2 / oldLen2))) : this;
    }

    public Vector3D clamp(float min, float max) {
        float len2 = this.len2();
        if (len2 == 0.0F) {
            return this;
        } else {
            float max2 = max * max;
            if (len2 > max2) {
                return this.scl((float)Math.sqrt((double)(max2 / len2)));
            } else {
                float min2 = min * min;
                return len2 < min2 ? this.scl((float)Math.sqrt((double)(min2 / len2))) : this;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector3D vector3D = (Vector3D) o;
        return Float.compare(vector3D.x, x) == 0 &&
                Float.compare(vector3D.y, y) == 0 &&
                Float.compare(vector3D.z, z) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public String toString() {
        return "Vector3D{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
