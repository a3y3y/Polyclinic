package by.it_academy.polyclinic.model.doctor_info;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class Schedule {
    private Date startDate;
    private Date endDate;
    private Time startTime;
    private Time endTime;
    private Time breakStart;
    private Time breakEnd;
    private List<Date> weekend;

}
