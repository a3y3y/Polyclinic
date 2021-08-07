package by.it_academy.polyclinic.controller.rest;

import by.it_academy.polyclinic.model.dto.TicketGenerationParams;
import by.it_academy.polyclinic.model.patient_info.Ticket;
import by.it_academy.polyclinic.service.api.ITicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.TreeSet;

@RestController
@RequestMapping("/tickets")
public class TicketControllerRest {
    private final ITicketService ticketService;

    public TicketControllerRest(ITicketService ticketService) {
        this.ticketService = ticketService;
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
//
//    @PutMapping("/{id}")
//    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Ticket ticket) {
//        final boolean updated = ticketService.update(ticket, id);
//
//        return updated
//                ? new ResponseEntity<>(HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
//    }
//
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = ticketService.deleteTicketByUserId(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
