package tracer; 
import la.*;

public abstract class Material{ 
	protected HitRecord hit_record;
	protected abstract boolean scatter(Ray ray, HitRecord hit_rec, Vec3 attenuation, Ray Scattered);  
} 
