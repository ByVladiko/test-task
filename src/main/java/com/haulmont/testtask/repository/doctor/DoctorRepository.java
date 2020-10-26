package com.haulmont.testtask.repository.doctor;

import com.haulmont.testtask.domain.Doctor;
import com.haulmont.testtask.repository.CrudRepository;

import java.util.Map;

public interface DoctorRepository extends CrudRepository<Doctor> {

    Map<Doctor, Long> getStatistic();

}
