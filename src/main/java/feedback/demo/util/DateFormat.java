package feedback.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {

    public static String formatDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return simpleDateFormat.format(date);

    }
}
