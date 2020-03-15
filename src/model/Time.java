package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;

public class Time implements Serializable{
	
	private Calendar c;
	private int hour;
	private int minutes;
	private int seconds;
	private int day;
	private String month;
	private int monthInt;
	private int year;
	
	public Time() {		
		c = new GregorianCalendar();
		this.hour = setHour();	
		this.minutes = setMinutes();
		this.seconds = setSeconds();
		this.day = setDay();
		this.month = setMonth();
		this.monthInt = setMonthInt();
		this.year = setYear();
	}
	private int setMonthInt() {
		return c.get(Calendar.MONTH);
	}
	private int setSeconds() {
		return c.get(Calendar.SECOND);
	}
	private int setMinutes() {
		return c.get(Calendar.MINUTE);
	}
	private int setHour() {
		return c.get(Calendar.HOUR);
	}
	private int setDay() {
		return c.get(Calendar.DAY_OF_MONTH);
	}
	private String setMonth() {
		String mesString = "";
		int mes = c.get(Calendar.MONTH);
		switch (mes) {
        case 0:  mesString = "Enero";
                 break;
        case 1:  mesString  = "Febrero";
                 break;
        case 2:  mesString = "Marzo";
                 break;
        case 3:  mesString = "Abril";
                 break;
        case 4:  mesString = "Mayo";
                 break;
        case 5:  mesString = "Junio";
                 break;
        case 6:  mesString = "Julio";
                 break;
        case 7:  mesString = "Agosto";
                 break;
        case 8:  mesString = "Septiembre";
                 break;
        case 9: mesString = "Octubre";
                 break;
        case 10: mesString = "Noviembre";
                 break;
        case 11: mesString = "Diciembre";
                 break;
        default: mesString = "Invalid month";
                 break;
        }
		return mesString;
	}
	private int setYear() {		
		return c.get(Calendar.YEAR);
	}
	
	public void refresh() {
		c = new GregorianCalendar();
		seconds = setSeconds();
		minutes = setMinutes();
		hour = setHour();
		day = setDay();
		month = setMonth();
		year = setYear();
	}
	
	public String getActualTime() {
		return hour + ":" + minutes + ":" + seconds;
	}
	
	public String getAll() {	
		return day + " : " +month+" : "+year+" : "+ hour + ":" + minutes + ":" + seconds;
	}
	
	public String date() {
		return day + ":" +monthInt+":"+year+":"+ hour + ":" + minutes + ":" + seconds;	
	}
	
	public String toCompare() {
		return year + ":" +monthInt+":"+day+":"+ hour + ":" + minutes + ":" + seconds;
	}

}
