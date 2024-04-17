package tracer;
import la.*;
import tracer.*;

public class Ray{
	private Vec3 orig;
	private Vec3 dir; 

	public Ray(Vec3 orig, Vec3 dir){
		this.orig = orig;
		this.dir = dir;	
	} 

	public void set(Ray ray){
		this.orig = ray.origin();
		this.dir = ray.direction();	
	}

	public Vec3 origin(){
		return orig;	
	}  

	public void origin(Vec3 origin){
		this.orig = origin;	
	}
	
	public Vec3 at(double t){
		return  orig.add(dir.scalar_mul(t));	
	}

	public Vec3 direction(){
		return dir;	
	}

	public void direction(Vec3 direction){ 
		this.dir = direction;
	}


}
