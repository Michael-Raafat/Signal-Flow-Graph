package calculations.imp;

import java.util.ArrayList;
import java.util.List;

import calculations.elements.ILoop;
/**
 * 
 * @author Michael.
 *
 */
public class CombinationLoops { 
	/**
	 * list of individual loops.
	 */
	private List<ILoop> loops;
	/**
	 * gain of combination loop.
	 */
	private Double gain;
	/**
	 * no. of loops.
	 */
	private int level;
	
	public CombinationLoops(List<ILoop> loops,Double gain) {
		this.gain = gain;
		this.loops = loops;
		this.level = 2;
	}
	public CombinationLoops(List<ILoop> loops) {
		this.gain = 1.0;
		this.loops = loops;
		this.level = 2;
	}
	
	public CombinationLoops(Double gain) {
		this.gain = gain;
		loops = new ArrayList<ILoop>();
		this.level = 2;
	}
	
	public CombinationLoops() {
		loops = new ArrayList<ILoop>();
		gain = 1.0;
		this.level = 2;
	}
	
	public void addLoop(ILoop loop) {
		loops.add(loop);
	}
	
	public void setGain(Double gain) {
		this.gain = gain; 
	}
	
	public List<ILoop> getLoops() {
		return loops;
	}
	
	public Double getGain() {
		return gain;
	}
	
	public void setLevel(int num) {
		this.level = num;
	}
	
	public int getLevel(){
		return level;
	}

}
