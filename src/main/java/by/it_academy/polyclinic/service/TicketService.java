package by.it_academy.polyclinic.service;

import by.it_academy.polyclinic.model.dto.TicketGenerationParams;
import by.it_academy.polyclinic.model.patient_info.Ticket;
import by.it_academy.polyclinic.model.user_Info.User;
import by.it_academy.polyclinic.service.api.ITicketService;
import by.it_academy.polyclinic.storage.ITicketRepository;
import by.it_academy.polyclinic.storage.IUserRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class TicketService implements ITicketService {

    private ITicketRepository ticketRepository;

    private IUserRepository userRepository;

    public TicketService(ITicketRepository ticketRepository, IUserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    public TicketService() {
    }

    @Override
    public Set<Ticket> generateTickets(TicketGenerationParams tgp) {
        int hourDifference = (tgp.getEndTime().getHour() - tgp.getStartTime().getHour()) -
                (tgp.getBreakEnd().getHour() - tgp.getBreakStart().getHour());
        int minuteDifference = (tgp.getEndTime().getMinute() - tgp.getStartTime().getMinute()) -
                (tgp.getBreakEnd().getMinute() - tgp.getBreakStart().getMinute());
        int numberOfTickets = (hourDifference * 60 + minuteDifference) / tgp.getDurationOfAppointment();
        int numberOfDays = tgp.getEndDate().getDayOfMonth() - tgp.getStartDate().getDayOfMonth();
        User doctor = userRepository.findById(tgp.getDoctorId());
        Set<Ticket> tickets = new HashSet<>();
        int afterBreakTime = 0;
        for (int i = 0; i < numberOfDays; i++) {
            for (int y = 0; y < numberOfTickets; y++) {
                Ticket ticket = new Ticket();
                LocalDate ticketDate = tgp.getStartDate().plusDays(i);
                ticket.setDate(ticketDate);
                LocalTime ticketTime = tgp.getStartTime();
                int minutes = tgp.getDurationOfAppointment() * y;
                ticketTime = ticketTime.plusMinutes(minutes);
                if (ticketTime.equals(tgp.getBreakStart()) || ticketTime.isAfter(tgp.getBreakStart())) {
                    ticketTime = tgp.getBreakEnd().plusMinutes(tgp.getDurationOfAppointment() * afterBreakTime);
                    ticket.setTime(ticketTime);
                    afterBreakTime++;
                } else {
                    ticket.setTime(ticketTime);
                }
                ticket.setNumber(y+1);
                ticket.setOfficeNumber(tgp.getOfficeNumber());
                ticket.setAvailable(true);
                tickets.add(ticket);
            }
        }

        doctor.setTickets(tickets);
        userRepository.save(doctor);
        return tickets;
    }

    @Override
    public TreeSet<Ticket> getAllByUser(int id) {
        return ticketRepository.findAllByUser(id);
    }

    @Override
    public boolean deleteTicketByUserId(int id) {
        Set<Ticket> tickets = ticketRepository.findAllByUser(id);
        User user = userRepository.findById(id);
        user.setTickets(null);
        userRepository.save(user);
        ticketRepository.deleteAll(tickets);
        return true;

    }
}
