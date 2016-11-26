import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

public class Test {

	public static void main(String[] args) {

		long unixTimestamp = Instant.now().getEpochSecond();
		
		System.out.println(unixTimestamp);

	}

}
