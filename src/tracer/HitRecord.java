package tracer; 
import la.*; 

public class HitRecord{
	private Vec3 p;
	private Vec3 normal;
	private double t; 
	private boolean front_face; 
	//Material
	private Material material;
	public HitRecord(Vec3 p, Vec3 normal, double t){
		this.p = p;
		this.normal = normal;	 
		this.t = t; 
	}  

	public Vec3 p(){
		return p;	
	}

	public void p(Vec3 p){
		this.p = p;	
	}


	public Vec3 normal(){
		return this.normal;
	}

	public void normal(Vec3 normal){
		this.normal = normal;	
	} 

	public double t(){
		return this.t;	
	} 

	public void t(double t){
		this.t = t;	
	}  

	public Material material(){
		return this.material;	
	}

	public void material(Material material){
		this.material = material;	
	}


	public void set_face_normal(Ray r, Vec3 outward_normal){
		front_face = r.direction().dot(outward_normal) < 0;
		normal = front_face ? outward_normal : outward_normal.scalar_mul(-1);
	}
}
