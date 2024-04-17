package tracer.materials; 

import tracer.*; 
import la.*;

public class Lambertian extends Material{ 

	private Vec3 albedo; 

	public Lambertian(Vec3 color){
		this.albedo = color;	
	}  

	public boolean scatter(Ray ray, HitRecord hit_rec, Vec3 attenuation, Ray scattered){

		Vec3 scatter_direction = hit_rec.normal().add(Vec3.random_unit());

		if(scatter_direction.near_zero()){
			scatter_direction = hit_rec.normal();
		} 

		scattered.origin(hit_rec.p());
		scattered.direction(scatter_direction);   
		attenuation.set(albedo);  
		return true;
	}  

}
