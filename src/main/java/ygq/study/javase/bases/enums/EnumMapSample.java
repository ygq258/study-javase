package ygq.study.javase.bases.enums;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;

/**
 * 
 * 枚举的Map的使用
 * @author yang
 *
 */
public class EnumMapSample {

	
	public static void main(String[] args) {
		
		Map<Action, String> map = new EnumMap<Action, String>(Action.class);
		
		map.put(Action.TURN_LEFT, "向左");
		map.put(Action.TURN_RIGHT, "向右");
		map.put(Action.SHOOT, "射击");
		
		for (Action action : map.keySet()) {
			System.out.println(map.get(action));
		}
		
		EnumSet<Action> es = EnumSet.of(Action.TURN_LEFT,Action.SHOOT);
		for (Action action : Action.values()) {
			System.out.println(action.compareTo(Action.SHOOT));
		}
		
		System.out.println(es.size());
	}
	/**
	 * 定义枚举
	 * @author yang
	 */
	public static enum Action{
		TURN_LEFT, TURN_RIGHT, SHOOT;
	}
	
}


