package screen;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*; 
import la.*;
 
public class Screen extends Canvas implements Runnable{  
	private JFrame jframe; 
	private BufferedImage img;
	static Graphics2D gc;  
	private int width, height;

	@Override
	public void run(){
		jframe = new JFrame("Tracer");
		jframe.add(this); 
		jframe.setSize(width, height); 
		jframe.setVisible(true); 
	}

	public Screen(int width, int height){  
		this.width = width;
		this.height = height;
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	}	 

	public void paint(Graphics g){
		g.drawImage(img, 0, 0, this);
	}  

	public void msg(String msg,int x, int y){  
		gc = (Graphics2D) img.getGraphics();
		gc.drawString(msg, x, y);
	} 

	public void pixel(int x, int y, Color color){
		gc = (Graphics2D) img.getGraphics();  
		gc.setColor(color);
		gc.drawRect(x,y, 1, 1);	
	}

	public double clamp(double x, double min, double max){
		if(x < min)	return min;
		if(x > max) return max; 
		return x;
	}


	public void pixel_samples(int x, int y, Vec3 v, int samples_per_pixel){
		double r = v.x();
		double g = v.y();
		double b = v.z();

		double scale = 1.0 / samples_per_pixel;

		//Gamma 2
		r = Math.sqrt(scale * r);
		g = Math.sqrt(scale * g);
		b = Math.sqrt(scale * b); 

		gc = (Graphics2D) img.getGraphics();  

		gc.setColor(new Color((int)(clamp(r, 0.00, 0.999) * 256),(int) (clamp(g, 0.00, 0.999) * 256),(int)(clamp(b, 0.00, 0.999)*256)));
		gc.drawRect(x,y, 1, 1);	
	}


	public void clear(){
		repaint();
	}
}
