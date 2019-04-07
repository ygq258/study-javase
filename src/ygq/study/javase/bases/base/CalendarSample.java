package ygq.study.javase.bases.base;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Calendar类的使用技巧
 * @author ygq_gui_quan
 */
public class CalendarSample {
	
	public static String dateToString(Date time){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(time);
	}

	public static void main(String[] args) {
		
		Calendar instance = Calendar.getInstance();
		
		instance.set(Calendar.DATE, instance.getActualMinimum(Calendar.DATE));
		System.out.println("monthStart:"+ dateToString(instance.getTime()));
		
		instance.set(Calendar.DATE, instance.getActualMaximum(Calendar.DATE));
		System.out.println("monthEnd:"+ dateToString(instance.getTime()));
		
		instance = Calendar.getInstance();
		instance.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		System.out.println("weekStart:"+dateToString(instance.getTime()));
		
		instance.set(Calendar.DATE, instance.get(Calendar.DATE) + 6);
		System.out.println("weekEnd:"+dateToString(instance.getTime()));
	}
}