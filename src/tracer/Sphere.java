package tracer; 
import la.Vec3;

public class Sphere extends Hittable{ 
	private Vec3 center; 
	private double radius; 
	private Material material;

	public Sphere(Vec3 center, double radius, Material material){
		this.center  = center ;
		this.radius = radius;	 
		this.material = material;
	} 

	@Override
	public boolean hit(Ray r, double t_min, double t_max, HitRecord record){ 


		Vec3 oc = r.origin().sub(center);
		double a = r.direction().sqrlen();
		double half_b = oc.dot(r.direction());
		double c = oc.sqrlen() - (radius*radius); 
		double discriminant = half_b*half_b - a * c;
		if (discriminant < 0){
			return false;
		}

		double sqrtd = Math.sqrt(discriminant);

		double root = (-half_b - sqrtd) /a; 
		if(root < t_min || t_max < root){
			root = (-half_b + sqrtd)/a;	 
			if(root < t_min || t_max < root){
				return false;
			}
		} 

		record.t(root);
		record.p(r.at(record.t())); 
		Vec3 outward_normal = record.p().sub(center).scalar_div(radius);
		record.set_face_normal(r,outward_normal);
		record.material(material);
		return true; 

	}

}
