package by.it_academy.polyclinic;

import by.it_academy.polyclinic.model.dto.TicketGenerationParams;
import by.it_academy.polyclinic.service.TicketService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class Test {

    public static void main(String[] args) {
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm");
        LocalDate startDate = LocalDate.parse("2021-08-10",formatDate);
        LocalDate endDate = LocalDate.parse("2021-08-15", formatDate);
        LocalTime startTime = LocalTime.parse("08:00",formatTime);
        LocalTime endTime = LocalTime.parse("17:00",formatTime);
        LocalTime breakStart = LocalTime.parse("13:00",formatTime);
        LocalTime breakEnd = LocalTime.parse("14:00",formatTime);
        String officeNumber = "300";
        int doctorId = 12;
        int durationOfAppointment = 20;
        TicketGenerationParams tgp = new TicketGenerationParams(startDate, endDate,startTime, endTime, breakStart, breakEnd,
                officeNumber, doctorId, durationOfAppointment);

        TicketService ticketService = new TicketService();
        ticketService.generateTickets(tgp);
    }

}
