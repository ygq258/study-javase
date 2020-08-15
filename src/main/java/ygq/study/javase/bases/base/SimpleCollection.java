package ygq.study.javase.bases.base;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@SuppressWarnings("rawtypes")
public class SimpleCollection<T extends List> {

	private T[] list;

	private int index = 0;

	@SuppressWarnings("unchecked")
	public SimpleCollection() {

		list = (T[]) new Object[10];
	}

	public void add(T t) {
		list[index++] = t;
	}

	public T get(int i) {

		return (T) list[i];
	}

	public int length() {

		return index;
	}

	public static void main(String[] args) {

		SimpleCollection<ArrayList> l = new SimpleCollection<ArrayList>();

		System.out.println(l.get(0));

		Set<People> set = new HashSet<People>();
		set.add(new People("yang", 12, "fz"));
		set.add(new People("yang", 12, "fz"));
		set.add(new People("yang", 13, "fz"));
		System.out.println(set.size());

		Map<String, Object> map = new HashMap<String, Object>();

		Set<Entry<String, Object>> set2 = map.entrySet();

		for (Iterator<Entry<String, Object>> iterator = set2.iterator(); iterator
				.hasNext();) {

			System.out.println(iterator.next().getKey());
			System.out.println(iterator.next().getValue());
		}

	}

}

class People {

	private String name;

	private int age;

	private String address;

	public People() {
		super();
	}

	public People(String name, int age, String address) {
		super();
		this.name = name;
		this.age = age;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		People other = (People) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
