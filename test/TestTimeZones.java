
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;
import javafx.scene.control.Alert;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author m219663
 */
public class TestTimeZones {
    
    public static void main(String args[]){
        
//        LocalDateTime ldt = LocalDateTime.now();
//        System.out.println(ldt.toLocalTime());
//
//        ZonedDateTime ldtZoned = ldt.atZone(ZoneId.systemDefault());
//
//        ZonedDateTime utcZoned = ldtZoned.withZoneSameInstant(ZoneId.of("UTC"));
//
//        System.out.println(utcZoned.toLocalTime());
//        
//        LocalDateTime startDt = LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)),LocalTime.of(0, 0));
//        LocalDateTime endDt = LocalDateTime.of(LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)),LocalTime.of(23,59,59));
////        System.out.println("Start date: "+ startDt);
////        System.out.println("End date: "+ endDt);
//        
//        System.out.println(ldtZoned.getDayOfWeek().getValue());

        Alert altReminder = new Alert(Alert.AlertType.INFORMATION);
        altReminder.setTitle("Appointment Reminder");
        altReminder.setHeaderText("You have an appointment in 15 minutes");
        altReminder.setContentText("Coronation as King of Java!");
        altReminder.show();
    }
    
}
