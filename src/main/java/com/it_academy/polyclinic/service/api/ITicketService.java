package com.it_academy.polyclinic.service.api;

import com.it_academy.polyclinic.model.dto.TicketDto;
import com.it_academy.polyclinic.model.dto.TicketGenerationParams;
import com.it_academy.polyclinic.model.patient_info.Ticket;

import java.util.Set;
import java.util.TreeSet;

public interface ITicketService {
    Set<Ticket> generateTickets(TicketGenerationParams tgp);
    TreeSet<Ticket> getAllByUser(int id);
    boolean deleteTicketByUserId(int id);
    boolean setAvailability(boolean availability, int id);
    boolean addToUser(Ticket ticket, int userId);
    Ticket getById(long id);
    void deleteFromUser(long id);
    TreeSet<TicketDto> getTicketsByUserId(int id);
}
