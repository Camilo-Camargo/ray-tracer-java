package ppm;
import java.io.*;
import tracer.*;
import la.*;

public class PPM{ 
	private FileOutputStream fis;
   private PrintWriter pw;
   private BufferedWriter bw;
   private String error; 
	private String path;

	public PPM(String filename, int width, int height){
		try{
			pw = new PrintWriter(filename);
			bw = new BufferedWriter(pw);
			path  = filename;
			msg("P3\n" + width + " " + height + "\n255\n");
		}catch(IOException e){
			pw = new PrintWriter(System.out);
			bw = new BufferedWriter(pw); 
		} 
	} 
	//[TODO:] Mathematical utility
	public double clamp(double x, double min, double max){
		if(x < min)	return min;
		if(x > max) return max; 
		return x;
	}

	public void color_sample(Vec3 v, int samples_per_pixel){ 
		double r = v.x();
		double g = v.y();
		double b = v.z();

		double scale = 1.0 / samples_per_pixel;
		r *= scale;
		g *= scale;
		b *= scale; 

		msg((int)(clamp(r, 0.00, 0.999) * 256) + " " + (int) (clamp(g, 0.00, 0.999) * 255.99) + " " + (int)(clamp(b, 0.00, 0.999)*255.99) + "\n");	
	}



	public void color(Vec3 v){ 
		msg((int)(clamp(v.x(), 0.00, 0.999) * 256) + " " + (int) (clamp(v.y(), 0.00, 0.999) * 255.99) + " " + (int)(clamp(v.z(), 0.00, 0.999)*255.99) + "\n");	
	}


	private void msg(String msg){
		try{
			bw.write(msg);
			bw.flush();
		}catch(IOException e){
			msg("CATCH: " + e);
		}	
	}
} 
