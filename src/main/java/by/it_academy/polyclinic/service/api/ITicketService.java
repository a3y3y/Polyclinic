package by.it_academy.polyclinic.service.api;

import by.it_academy.polyclinic.model.dto.TicketGenerationParams;
import by.it_academy.polyclinic.model.patient_info.Ticket;

import java.util.Set;
import java.util.TreeSet;

public interface ITicketService {
    Set<Ticket> generateTickets(TicketGenerationParams tgp);
    TreeSet<Ticket> getAllByUser(int id);
    boolean deleteTicketByUserId(int id);
}
