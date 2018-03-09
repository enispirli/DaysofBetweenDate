import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {

	static Date startDate;
	static Date finishDate;
	static int daysRangeList[] = new int[10];

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter start date eg:12.09.2017");
		startDate = stringToDate(scanner.next());

		System.out.println("Enter finish date eg:12.09.2017");
		finishDate = stringToDate(scanner.next());

		System.out.println("Enter start day eg:Çarşamba");
		int startDay = getDaysOrdinal(Days.getDaysByValue(scanner.next()));
		System.out.println(startDay);

		System.out.println("Enter finish date eg:Cuma");
		int finishDay = getDaysOrdinal(Days.getDaysByValue(scanner.next()));
		System.out.println(finishDay);

		int differenceDay = getDaysRange(startDay, finishDay);
		int firstWeekCount = differenceDay;

		int startDateDayOrder = getDayOfWeek(startDate);
		if (startDateDayOrder < startDay) {
			startDate = addDay(startDate, startDay - startDateDayOrder );
		} else if (startDateDayOrder > startDay) {
			firstWeekCount = differenceDay - (startDateDayOrder - startDay);
		}

		printDayRange(startDate, firstWeekCount, finishDate);

	}

	public static Date addDay(Date date, int dayCount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, dayCount);
		return calendar.getTime();
	}

	public static String formatDateToStr(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
		return simpleDateFormat.format(date);
	}

	public static int getDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	public static String getDaysStringBetweenToDate(Date startDate, Date finishDate) {
		StringBuilder sb = new StringBuilder();
		Date currentDate = startDate;
		Calendar calendar = Calendar.getInstance();
		while (currentDate.before(finishDate)) {
			calendar.setTime(currentDate);
			sb.append(Days.values()[calendar.get(Calendar.DAY_OF_WEEK) - 1].getValue()).append(" .");
			calendar.add(Calendar.DAY_OF_WEEK, 1);
			currentDate = calendar.getTime();
		}
		String result = sb.toString();
		result = result.replace(result.substring(result.length() - 1), "");
		return result;
	}

	public static void printDayRange(Date currentDate, int dayRangeCount, Date finishDate) {
		Date currentFinishDate = addDay(currentDate, dayRangeCount);
		if (!currentFinishDate.after(finishDate)) {
			String dateRangeStr = formatDateToStr(currentDate) + " - " + formatDateToStr(currentFinishDate);
			String dayList = getDaysStringBetweenToDate(currentDate, currentFinishDate);
			System.out.println(dateRangeStr + "  " + dayList);

		}
	}

	public static Date stringToDate(String string) {
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
		try {
			date = simpleDateFormat.parse(string);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("wrong formate");
			e.printStackTrace();
		}
		return date;

	}

	public static int getDaysOrdinal(Days day) {

		if (day == null) {
			System.out.println("Day is not formate");
			return 0;
		}
		return day.ordinal() + 1;

	}

	public static int getDaysRange(int startDay, int finishDay) {
		int count = 0;
		int day = startDay;
		do {

			if (day > 7) {
				day = 1;

			} else if (day == finishDay) {
				return ++count;

			}

			day++;
			count++;
		} while (true);

	}

	public static int[] getDaysRangeArray(int startDay, int finishDay) {
		int count = 0;
		int day = startDay;
		do {

			if (day > 7) {
				day = 1;

			} else if (day == finishDay) {
				daysRangeList[count] = day;
				return daysRangeList;

			}

			daysRangeList[count] = day;

			day++;
			count++;
		} while (true);
	}
}
