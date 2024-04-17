package tracer;
import la.*; 

public abstract class Hittable{ 
	public abstract boolean hit(Ray r, double t_min, double t_max, HitRecord record);
}

