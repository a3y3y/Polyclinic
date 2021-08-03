package by.it_academy.polyclinic.model.patient_info;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.ArrayList;
import java.util.List;

public enum MedicalNoteType {
    DIAGNOSIS("Диагноз"),
    INDIVIDUAL_TREATMENT("Лечение"),
    MEDICAL_TEST("Анализ"),
    PRESCRIPTION("Рецепт"),
    SICK_LEAVE("Больничный"),
    VACCINATION("Прививка");

    public final String name;

    MedicalNoteType(String name){
        this.name = name;
    }

    public static List<String> getNames() {
        List<String> names = new ArrayList();
        for (MedicalNoteType noteType : values()) {
            names.add(noteType.name);
            }
        return names;
    }
    @JsonValue
    public String getName() {
        return name;
    }
}
