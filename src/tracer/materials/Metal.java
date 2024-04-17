package tracer.materials;

import tracer.*;
import la.*;


public class Metal extends Material { 
	private Vec3 albedo;
	public Metal(Vec3 color){
		this.albedo = color;	
	} 

	 public boolean scatter(Ray ray, HitRecord hit_rec, Vec3 attenuation, Ray scattered){
		 Vec3 reflected = Vec3.reflect(ray.direction().unit(), hit_rec.normal());  
 			scattered.origin(hit_rec.p());
			scattered.direction(reflected);
			attenuation.set(albedo);
			return (scattered.direction().dot(hit_rec.normal()) > 0);
	 }

}
