package actions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import io.restassured.path.json.JsonPath;

public class APIUtility {
	public static JsonPath js;

	public static JsonPath getValueJsonPath(String rsp) {
		js = new JsonPath(rsp);
		return js;
	}

	public static String getDate(String date) throws ParseException {
		Date temp;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dateobj = new Date();
		Date dt = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		LocalDate currenttime = LocalDate.parse(formatter.format(dateobj));
		if (dt.compareTo(dateobj) > 0) {

			// temp = formatter.format(dateobj);
			temp = new SimpleDateFormat("yyyy-MM-dd").parse(currenttime.minusDays(1).toString());
		} else {
			temp = dt;
		}
		Calendar c1 = Calendar.getInstance();
		c1.setTime(temp);
		
		LocalDate locdt = LocalDate.parse(formatter.format(temp));
		LocalDate newlocdt = null;
		String weekDay = "Not a Weekday";
		if (c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			
			newlocdt = locdt.minusDays(1);
			weekDay = "The date "+date+" is a WeekEnd. Hence the previous WeekDay" +newlocdt.toString() +"  is taken";
		}

		else if (c1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			newlocdt = locdt.minusDays(2);
			weekDay = "The date "+date+" is a WeekEnd. Hence the previous WeekDay" +newlocdt.toString() +"  is taken";
		}
		
		else {
			newlocdt = locdt;
		}
		
		System.out.println(newlocdt);
		return newlocdt.toString()+":"+weekDay ;

	}

}
