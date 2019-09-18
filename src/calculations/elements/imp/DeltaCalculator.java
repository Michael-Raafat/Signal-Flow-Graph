package calculations.elements.imp;

import java.util.List;

import calculations.elements.IDeltaCalculator;
import calculations.elements.IForwardPath;
import calculations.elements.ILoop;
import calculations.imp.CombinationLoops;
/**
 * 
 * @author Michael
 *
 */
public class DeltaCalculator implements IDeltaCalculator {
	/**
	 * List of the combinations of loops.
	 */
	private List<CombinationLoops> combs;
	/**
	 * individual loops.
	 */
	private List<ILoop> loops;
	@Override
	public void setLoopList(List<ILoop> loops) {
		this.loops = loops;
	}
	@Override
	public void setCombinationLoopList(List<CombinationLoops> combs) {
		this.combs = combs;
	}
	@Override
	public double calculateDelta() {
		double gain = 1;	
		for(int i = 0; i < loops.size(); i++) {
			gain -= loops.get(i).getLoopGain();
		}
		for (int j = 0; j < combs.size(); j++) {
			if (Math.floorMod(combs.get(j).getLevel(),2) == 0) {
				gain += combs.get(j).getGain();
			} else {
				gain -= combs.get(j).getGain();
			}
		}
		return gain;
	}
	@Override
	public double calculateDelta(IForwardPath path) {
		double gain = 1;
		for(int i = 0; i < loops.size(); i++) {
			if (loops.get(i).isUntouched(path)) {
				gain -= loops.get(i).getLoopGain();
			}
		}
		for (int i = 0; i < combs.size(); i++) {
			boolean flag = false;
			for (int j = 0; j < combs.get(i).getLoops().size(); j++) {
				if (!combs.get(i).getLoops().get(j).isUntouched(path)) {
					flag = true;
				}
			}
			if (!flag) {
				if (Math.floorMod(combs.get(i).getLevel(),2) == 0) {
					gain += combs.get(i).getGain();
				} else {
					gain -= combs.get(i).getGain();
				}
			}
		}
		return gain;
	}

	

}
