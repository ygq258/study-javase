package ygq.study.javase.design.observer;

/**
 * 抽象主题角色
 * @author Yang
 */
public interface Watched {

	public void addWatcher(Watcher watcher);
	
	public void removeWatcher(Watcher watcher);
	
	public void notifyWatcher(String message);
}
