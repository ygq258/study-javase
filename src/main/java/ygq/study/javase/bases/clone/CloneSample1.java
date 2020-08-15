package ygq.study.javase.bases.clone;

/**
 * 对象克隆,java内置提供了clone方法支持对象克隆，不能复制那么所引用的对象
 * @author yang
 */
public class CloneSample1 {

	public static void main(String[] args) throws Exception{
		
		Student student = new Student();
		student.setAge(20);
		student.setName("zhangsan");
		
		Student student2 = (Student)student.clone();
		
		System.out.println(student2.getAge());
		System.out.println(student2.getName());
		
		student2.setName("lisi");
		
		System.out.println("student.getName: " + student.getName());
		System.out.println("student2.getName: " + student2.getName());
	}
	
	public static class Student implements Cloneable{
		
		private int age;
		
		private String name;

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public Object clone() throws CloneNotSupportedException {
			Object obj = super.clone();
			return obj;
		}
	}
}