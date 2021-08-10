package com.it_academy.polyclinic.storage;

import com.it_academy.polyclinic.model.dto.TicketDto;
import com.it_academy.polyclinic.model.dto.UserDto;
import com.it_academy.polyclinic.model.patient_info.Ticket;
import com.it_academy.polyclinic.model.user_Info.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.TreeSet;


public interface ITicketRepository extends JpaRepository<Ticket, Long> {
    @Query("SELECT u.tickets FROM User u WHERE u.id=:id")
    TreeSet<Ticket> findAllByUser(@Param("id") int id);

    @Query(value = "DELETE * FROM ticket_user WHERE user_id=?1", nativeQuery = true)
    boolean truncateTicketUserTable(int UserId);

    @Query(value = "DELETE FROM tickets USING ticket_user WHERE tickets.id=ticket_user.ticket_id AND user_id=?1", nativeQuery = true)
    boolean deleteTicketsByUserId(int UserId);

    @Transactional
    @Modifying
    @Query("UPDATE Ticket t SET t.available=?1 WHERE t.id=?2")
    void setAvailability(boolean availability, long id);

    Ticket findById(long id);

    @Query("SELECT new com.it_academy.polyclinic.model.dto.TicketDto(t.id, t.number, t.date, t.officeNumber," +
            " t.time, t.available, p.lastName, p.firstName) " +
            "FROM Ticket t JOIN t.users u JOIN u.passport p " +
            "WHERE u.id=?1")
    TreeSet<TicketDto> findTicketsByUserId(int userId);

    @Query("SELECT new com.it_academy.polyclinic.model.dto.TicketDto(t.id, t.number, t.date, t.officeNumber," +
            " t.time, t.available, p.lastName, p.firstName) " +
            "FROM Ticket t JOIN t.users u JOIN u.roles r JOIN u.passport p " +
            "WHERE t.id=?1 and r='DOCTOR'")
    TicketDto findDoctorTicket(long id);


}
