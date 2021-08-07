package by.it_academy.polyclinic.storage;

import by.it_academy.polyclinic.model.patient_info.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.TreeSet;


public interface ITicketRepository extends JpaRepository<Ticket, Long> {
    @Query("SELECT u.tickets FROM User u WHERE u.id=:id")
    TreeSet<Ticket> findAllByUser(@Param("id") int id);

    @Query(value = "DELETE * FROM ticket_user WHERE user_id=?1", nativeQuery = true)
    boolean truncateTicketUserTable(int UserId);

    @Query(value = "DELETE FROM tickets USING ticket_user WHERE tickets.id=ticket_user.ticket_id AND user_id=?1", nativeQuery = true)
    boolean deleteTicketsByUserId(int UserId);

}
