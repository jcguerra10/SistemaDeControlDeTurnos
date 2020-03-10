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
	private int year;
	
	public Time() {		
		c = new GregorianCalendar();
		this.hour = setHour();	
		this.minutes = setMinutes();
		this.seconds = setSeconds();
		this.day = setDay();
		this.month = setMonth();
		this.year = setYear();
		System.out.println(getAll());
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
        case 1:  mesString = "Enero";
                 break;
        case 2:  mesString  = "Febrero";
                 break;
        case 3:  mesString = "Marzo";
                 break;
        case 4:  mesString = "Abril";
                 break;
        case 5:  mesString = "Mayo";
                 break;
        case 6:  mesString = "Junio";
                 break;
        case 7:  mesString = "Julio";
                 break;
        case 8:  mesString = "Agosto";
                 break;
        case 9:  mesString = "Septiembre";
                 break;
        case 10: mesString = "Octubre";
                 break;
        case 11: mesString = "Noviembre";
                 break;
        case 12: mesString = "Diciembre";
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
		setSeconds();
		setMinutes();
		setHour();
		setDay();
		setMonth();
		setYear();
	}
	
	public String getActualTime() {
		refresh();
		return hour + ":" + minutes + ":" + seconds;
	}
	
	public String getAll() {
		refresh();
		return day + " : " +month+" : "+year+" : "+ hour + ":" + minutes + ":" + seconds;
	}

}
