package tracer;
import la.*;
import tracer.Hittable;

import java.util.*;

public class HittableList{
	List<Hittable> list;
	public HittableList(){
		list = new ArrayList<Hittable>();
	} 
	public boolean hit(Ray r, double t_min, double t_max, HitRecord record){ 
		boolean hit_anything = false; 
		double closest_so_far = t_max; 
		for(int i= 0; i < list.size(); ++i){ 
			if(list.get(i).hit(r,t_min, closest_so_far, record)){
				hit_anything = true;
				closest_so_far = record.t();
			}
		}
		return hit_anything;
	} 

	public void add(Hittable hittable){
		this.list.add(hittable);	
	} 

	public Hittable get(int index){
		return list.get(index);	
	}


}
