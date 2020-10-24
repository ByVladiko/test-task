package com.haulmont.testtask.repository.doctor;

import com.haulmont.testtask.domain.Doctor;
import org.junit.Test;

public class DoctorRepositoryImplTest {

    private final DoctorRepository doctorRepository = new DoctorRepositoryImpl();

    @Test
    public void Test() {
        Doctor doctorFirst = new Doctor("dsdsd", "dsadafd", "sfdsfsdf", "Traumatolog");
        Doctor doctorSecond = new Doctor("sfgdgfgdhf", "fsdgdgsdg", "dfgdghfdg", "Kardiolog");
        doctorRepository.save(doctorFirst);
        doctorRepository.save(doctorSecond);
        System.out.println(doctorRepository.getAll());
    }

}