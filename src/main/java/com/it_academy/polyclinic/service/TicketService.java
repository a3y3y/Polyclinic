package com.it_academy.polyclinic.service;

import com.it_academy.polyclinic.model.dto.TicketDto;
import com.it_academy.polyclinic.model.dto.TicketGenerationParams;
import com.it_academy.polyclinic.model.patient_info.Ticket;
import com.it_academy.polyclinic.model.user_Info.Role;
import com.it_academy.polyclinic.model.user_Info.User;
import com.it_academy.polyclinic.service.api.ITicketService;
import com.it_academy.polyclinic.storage.ITicketRepository;
import com.it_academy.polyclinic.storage.IUserRepository;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public boolean setAvailability(boolean availability, int id) {
        ticketRepository.setAvailability(availability, id);
        return true;
    }

    @Transactional
    @Override
    public boolean addToUser(Ticket ticket, int userId) {
        User user = userRepository.getById(userId);
        user.setId(userId);
        Set<Ticket> tickets = ticketRepository.findAllByUser(userId);
        tickets.add(ticket);
        user.setTickets(tickets);
        userRepository.save(user);
        return true;
    }

    @Override
    public Ticket getById(long id) {
        return ticketRepository.findById(id);
    }

    @Transactional
    @Override
    public void deleteFromUser(long id) {
        Ticket ticket = ticketRepository.getById(id);
        ticket.setAvailable(true);
        Set<Role> roles = new HashSet<>();
        roles.add(Role.PATIENT);
        User user = userRepository.findByTicketId(roles, id);
        TreeSet<Ticket> tickets = ticketRepository.findAllByUser(user.getId());
        TreeSet<Ticket> ticketsToSave = new TreeSet<>();
        for (Ticket n : tickets) {
            if(n.getId() != id){
                ticketsToSave.add(n);
            }
        }
        user.setTickets(ticketsToSave);
        userRepository.save(user);
    }

    @Override
    public TreeSet<TicketDto> getTicketsByUserId(int id) {
        TreeSet<TicketDto> doctorTicketList = new TreeSet<>();
        TreeSet<TicketDto> patientTicketList = ticketRepository.findTicketsByUserId(id);
        for (TicketDto ticketDto : patientTicketList) {
            TicketDto ticketDtoNew = ticketRepository.findDoctorTicket(ticketDto.getId());
            doctorTicketList.add(ticketDtoNew);
        }
        return doctorTicketList;
    }


}
