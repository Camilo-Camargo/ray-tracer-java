package tracer;

import ppm.*;
import la.*;
import screen.*;
import tracer.HitRecord;
import tracer.Hittable;
import tracer.materials.*;

import java.awt.*;
import java.io.*;

public class Tracer {
	private PPM ppm;
	private int width;
	private int height;
	private Screen dpy;

	public Tracer() {
	}

	public Vec3 random_in_hemisphere(Vec3 normal) {
		Vec3 in_unit_sphere = Vec3.random_unit();
		if (in_unit_sphere.dot(normal) > 0.0) {
			return in_unit_sphere;
		}
		return in_unit_sphere.scalar_mul(-1);
	}

	public Vec3 ray_color(Ray r, HittableList world, int depth) {
		if (depth <= 0) {
			return new Vec3(0, 0, 0);
		}

		HitRecord hit_record = new HitRecord(new Vec3(0, 0, 0), new Vec3(0, 0, 0), 0);

		if (world.hit(r, 0.001, Double.MAX_VALUE, hit_record)) {
			// Material Implementation
			Ray scattered = new Ray(new Vec3(0, 0, 0), new Vec3(0, 0, 0));
			Vec3 attenuation = new Vec3(0, 0, 0);
			if (hit_record.material().scatter(r, hit_record, attenuation, scattered)) {
				return attenuation.mul(ray_color(scattered, world, depth - 1));
			}

			return new Vec3(1, 1, 1);
			// Vec3 target = random_in_hemisphere(hit_record.normal()).add(hit_record.p());
			// return ray_color(new Ray(hit_record.p(), target.sub(hit_record.p())), world,
			// depth-1).scalar_mul(0.6);
		}

		Vec3 unit_direction = r.direction().unit();
		double t = 0.5 * (unit_direction.y() + 1.0);
		return new Vec3(1, 1, 1).scalar_mul(1.0 - t).add(new Vec3(0.5, 0.7, 1.0).scalar_mul(t));
	}

	public void init() {
		// Camera
		Camera camera = new Camera(1920, new Vec3(0,0,0),new Vec3(0,0,0), 200, 120, (16.0/9.0));
		width = camera.width();
		height = camera.height();
		int max_depth = 50;

		double R = Math.cos(Math.PI / 4);

		ppm = new PPM("img.ppm", width, height);
		dpy = new Screen(width, height);

		// World
		HittableList world = new HittableList();
		Lambertian material_ground = new Lambertian(new Vec3(0.8, 0.8, 0.0));
		Lambertian material_center = new Lambertian(new Vec3(0.7, 0.3, 0.3));
		Metal material_left = new Metal(new Vec3(0.8, 0.8, 0.8));
		Metal material_right = new Metal(new Vec3(0.8, 0.6, 0.2));

		//world.add(new Sphere(new Vec3(-R - 0.4, 0, -1), R, material_left));
		//world.add(new Sphere(new Vec3(R + 0.4, 0, -1), R, material_right));

		world.add(new Sphere(new Vec3(0,-100.5,-1), 100, material_ground));
		world.add(new Sphere(new Vec3(0,0,-1), 0.5 , material_center));
		world.add(new Sphere(new Vec3(-1,0,-1), 0.5, material_left));
		world.add(new Sphere(new Vec3(1,0,-1), 0.5, material_right));
		Vec3 color = new Vec3(0, 0, 0);
		Ray r;

		for (int y = height - 1, y1 = 0; y >= 0; --y, y1++) {
			System.out.print("Tracing ... Scaneline: " + y + "\r");
			for (int x = 0; x < width; ++x) {
				color = new Vec3(0, 0, 0);
				for (int s = 0; s < camera.samples_per_pixel(); s++) {
					double u = (double) ((x + Math.random()) / (width - 1));
					double v = (double) ((y + Math.random()) / (height - 1));
					r = camera.ray(u, v);
					color = color.add(ray_color(r, world, max_depth));
				}
				dpy.pixel_samples(x, y1, color, camera.samples_per_pixel());
				ppm.color_sample(color, camera.samples_per_pixel());
			}
		}
	}

	public int width() {
		return this.width;
	}

	public int height() {
		return this.height;
	}

	public Screen screen() {
		return dpy;
	}

	public static void main(String[] args) {
		Tracer tracer = new Tracer();
		tracer.init();
		EventQueue.invokeLater(tracer.screen());
	}
}
