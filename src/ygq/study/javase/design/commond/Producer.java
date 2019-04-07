package ygq.study.javase.design.commond;

import java.util.ArrayList;
import java.util.List;

/**
 * @description 具体不同的命令/请求 是实现接口Command，下面有三个命令
 * @author yang
 */
public class Producer {

	public static List<Command> produceRequests(){
		List<Command> queue = new ArrayList<>();
		queue.add(new Engineer());
		queue.add(new Programmer());
		queue.add(new Politician());
		return queue;
	}
}

class Engineer implements Command{
	
	@Override
	public void execute() {
		//do Engineer's command
	}
}
class Programmer implements Command{
	
	@Override
	public void execute() {
		//do Programmer's command
	}
}
class Politician implements Command{
	
	@Override
	public void execute() {
		//do Politician's command
	}
}



