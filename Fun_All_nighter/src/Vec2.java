public class Vec2 {

    // Public variables because these should be accessible
    // like a struct in c++.
    public float x, y, magnitude, direction;

    // Only constructor because haven't implemented
    // other types of Vector instantiations.
    public Vec2(float x, float y) {
        this.x = x;
        this.y = y;
        if(this.x==0 && this.y==0)
            this.magnitude = 0;
        else
            this.magnitude = (float)Math.pow((x*x)+(y*y), 0.5);
        this.direction = (float)Math.atan2(y, x);
    }

    // Returns the normal Vector of 'this' Vector.
    public Vec2 getSafeNormal() {
        if(this.magnitude==0)
            return new Vec2(0.f, 0.f);
        float mx = this.x / this.magnitude;
        float my = this.y / this.magnitude;
        return new Vec2(mx, my);
    }

    // Returns the resultant Vector between 'this' Vector
    // and 'vec'.
    public Vec2 add(Vec2 vec) {
        return new Vec2(this.x + vec.x, this.y + vec.y);
    }

    // Returns the dot product between 'this' Vector calls
    // and parameter 'vec'.
    public Vec2 dot(Vec2 vec) {
        return new Vec2(this.x * vec.x, this.y * vec.y);
    }

    // Multiplies Vector by a Scalar.
    public Vec2 scale(float value) {
        return new Vec2(this.x * value, this.y * value);
    }

    public Vec2 clamp(float min, float max) {

        // Returned Vector will be instantiated with these variables.
        float mx=0.f, my=0.f;

        // Clamps x component.
        if (this.x >= max)
            mx = max;
        else if (this.x <= min)
            mx = min;
        else
            mx = this.x;

        // Clamps y component.
        if (this.y >= max)
            my = max;
        else if (this.y <= min)
            my = min;
        else
            my = this.y;

        // Returns vector.
        return new Vec2(mx, my);
    }
}
