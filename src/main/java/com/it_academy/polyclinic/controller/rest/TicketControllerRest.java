package com.it_academy.polyclinic.controller.rest;

import com.it_academy.polyclinic.model.dto.TicketDto;
import com.it_academy.polyclinic.model.dto.TicketGenerationParams;
import com.it_academy.polyclinic.model.patient_info.Ticket;
import com.it_academy.polyclinic.model.user_Info.User;
import com.it_academy.polyclinic.service.api.ITicketService;
import com.it_academy.polyclinic.service.api.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.TreeSet;

@RestController
@RequestMapping("/tickets")
public class TicketControllerRest {
    private final ITicketService ticketService;
    private final IUserService userService;

    public TicketControllerRest(ITicketService ticketService, IUserService userService) {
        this.ticketService = ticketService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreeSet<Ticket>> readAllByUser(@PathVariable(name = "id") int id) {
        final TreeSet<Ticket> tickets = ticketService.getAllByUser(id);

        return tickets != null
                ? new ResponseEntity<>(tickets, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<TicketGenerationParams> add(TicketGenerationParams ticketGenerationParams) {
        ticketService.generateTickets(ticketGenerationParams);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Ticket ticket) {
        ticket.setAvailable(false);
        return ticketService.addToUser(ticket, ticket.getUserId())
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = ticketService.deleteTicketByUserId(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("/patient/{id}")
    public ResponseEntity<?> deleteFromPatient(@PathVariable(name = "id") long id) {
        ticketService.deleteFromUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("get_my_tickets/{id}")
    public ResponseEntity<TreeSet<TicketDto>> readAllByDoctorIdWithName(@PathVariable(name = "id") int id) {
        final TreeSet<TicketDto> tickets = ticketService.getTicketsByUserId(id);

        return tickets != null
                ? new ResponseEntity<>(tickets, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
