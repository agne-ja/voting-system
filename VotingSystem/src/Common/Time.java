package Common;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Time {
	private GregorianCalendar currentDate = new GregorianCalendar(2019, 0, 1);

	public GregorianCalendar getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(GregorianCalendar currentDate) {
		this.currentDate = currentDate;
	}
	
	@Override
	public String toString() {
		String str = currentDate.get(Calendar.YEAR) + " – "
				+ (currentDate.get(Calendar.MONTH) + 1) + " – "
				+ currentDate.get(Calendar.DATE);
		return str;
	}
	
	public void moveDay() {
		currentDate.add(Calendar.DATE, 1);
	}
	
	public void moveWeek() {
		currentDate.add(Calendar.DATE, 7);
	}
	
	public void moveMonth() {
		currentDate.add(Calendar.MONTH, 1);
	}
}
