package la;
public class Vec3{ 
	private double x0;
	private double y0;
	private double z0; 
	public Vec3(double x, double y, double z){
		this.x0 = x;	
		this.y0 = y;	
		this.z0 = z;	
	}

	public double x(){
		return this.x0;	
	}

	public double y(){
		return this.y0;	
	}

	public double z(){
		return this.z0;	
	}

	public Vec3 plus(Vec3 v){
		return new Vec3(this.x0 + v.x(), this.y0 + v.y(), this.z0 + v.z());
	} 

	public Vec3 add(Vec3 v){
		return new Vec3(this.x0 + v.x(), this.y0 + v.y(), this.z0 + v.z());
	} 

	public Vec3 sub(Vec3 v){
		return new Vec3(this.x0 - v.x(), this.y0 - v.y(), this.z0 - v.z());
	}  

	public Vec3 scalar_mul(double k){
		return new Vec3(this.x0 * k, this.y0 * k, this.z0 * k);
	}  

	public Vec3 scalar_add(double k){
		return new Vec3(this.x0 + k, this.y0 + k, this.z0 + k);
	} 
	public Vec3 scalar_sub(double k){
		return new Vec3(this.x0 - k, this.y0 - k, this.z0 - k);
	} 

	public Vec3 scalar_div(double k){
		return new Vec3(this.x0 / k, this.y0 / k, this.z0 / k);
	}  





	public Vec3 mul(Vec3 v){
		return new Vec3(this.x0 * v.x(), this.y0 * v.y(), this.z0 * v.z());
	}   


	public Vec3 div(Vec3 v){
		return new Vec3(this.x0 / v.x(), this.y0 / v.y(), this.z0 / v.z());
	}   

	public double sqrlen(){
		return this.x0 * this.x0 + this.y0 * this.y0 + this.z0 * this.z0;
	} 

	public double dot(Vec3 v){
		return this.x0 * v.x() + this.y0 * v.y() + this.z0 * v.z();
	}  

	public Vec3 cross(Vec3 v){
		return new Vec3(
				this.y0 * v.z() - v.z() * this.y0, 
				this.x0 * v.z() - v.x() * this.z0, 
				this.x0 * v.y() - v.x() * this.y0
				);	
	}


	public double len(){
		return Math.sqrt(sqrlen());	
	}

	public Vec3 unit(){ 
		double len = len();
		return new Vec3(this.x0 / len, this.y0/len, this.z0/len);	
	} 

	public static double degrees_to_radians (double degrees){
		return degrees * Math.PI / 180.0;
	}

	public static Vec3 random(){
		return new Vec3(Math.random(), Math.random(), Math.random());	
	}

	public static Vec3 random_unit(){
		return new Vec3(Math.random(), Math.random(), Math.random()).unit();	
	} 

	public boolean near_zero(){
		double s = 1e-8;
		return (Math.abs(x0) < s) && (Math.abs(y0) < s) && (Math.abs(z0) < s); 
	} 
	public static Vec3 reflect(Vec3 v, Vec3 n){
		return v.sub(n.scalar_mul(v.dot(n) * 2.0));	
	} 

	public void set(Vec3 v){
		this.x0 = v.x();	
		this.y0 = v.y();	
		this.z0 = v.z();	
	}
	@Override
	public String toString(){
		return "["+x0 + "," + y0 + ","+ z0 + "]"; }
}
