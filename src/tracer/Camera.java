package tracer;
import la.*;

public class Camera{ 
	private int width;
	private int height; 

	private Vec3 lookfrom;
	private Vec3 lookat; 
	private double vup;
	private double vfov;

	private double aspect_ratio;  
	private final int sample_per_pixel = 100;
	private double viewport_height;
	private double focal_length; 
	private double viewport_width; 

	private Vec3 origin;
	private Vec3 horizontal;
	private Vec3 vertical;
	private Vec3 lower_left_corner;
	private Vec3 dir;
	public Camera(int width,Vec3 lookfrom, Vec3 lookat, double vup, double vfov, double aspect_ratio){  
		this.width =  width;
		this.lookfrom = lookfrom;
		this.lookat = lookat;
		this.vup = vup;
		this.vfov = vfov; 


		Vec3 w = lookfrom.sub(lookat).unit();

		this.aspect_ratio = aspect_ratio;
		double theta = Vec3.degrees_to_radians(vfov); 
		double h = Math.tan(theta/2); 
		viewport_height = 2.0 * h;
		viewport_width = aspect_ratio * viewport_height; 
		focal_length = 1.0;

		height = (int) (width / aspect_ratio); 

		origin = new Vec3(0,0,0);
		horizontal = new Vec3(viewport_width, 0, 0);
		vertical = new Vec3(0, viewport_height, 0); 
		lower_left_corner = origin.sub(horizontal.scalar_div(2)).sub(vertical.scalar_div(2)).sub(new Vec3(0,0,focal_length)); 
		System.out.println(lower_left_corner);

		//Ray
	}  

	public Ray ray(double u, double v){
		dir = new Vec3(0,0,0);
		dir = dir.add(lower_left_corner).add(horizontal.scalar_mul(u)).add(vertical.scalar_mul(v)).sub(origin);
		return new Ray(origin, dir);
	}

	public int width(){
		return width;	
	}

	public int height(){
		return height;	
	}
	public int samples_per_pixel(){
		return sample_per_pixel;	
	}
}
