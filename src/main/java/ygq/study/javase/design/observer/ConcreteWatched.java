package ygq.study.javase.design.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体主题角色
 * @author Yang
 *
 */
public class ConcreteWatched implements Watched {

	private List<Watcher> list = new ArrayList<Watcher>();

	@Override
	public void addWatcher(Watcher watcher) {
		// TODO Auto-generated method stub
		list.add(watcher);
	}

	@Override
	public void removeWatcher(Watcher watcher) {
		// TODO Auto-generated method stub
		
		list.remove(watcher);
	}

	@Override
	public void notifyWatcher(String message) {
		// TODO Auto-generated method stub
		
		for (int i = 0; i < list.size(); i++) {
			list.get(i).update(message);
		}
	}
	
	
	
}
