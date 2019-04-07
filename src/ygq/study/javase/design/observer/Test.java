package ygq.study.javase.design.observer;

public class Test {

	public static void main(String[] args) {
		
		
		Watched girl = new ConcreteWatched();
		
		Watcher watcher1 = new ConcreteWather();
		Watcher watcher2 = new ConcreteWather();
		Watcher watcher3 = new ConcreteWather();
		
		girl.addWatcher(watcher1);
		girl.addWatcher(watcher2);
		girl.addWatcher(watcher3);
		
		girl.notifyWatcher("开心，爽");
		
		girl.removeWatcher(watcher2);
		
		girl.notifyWatcher("不爽");
		
	}
}
