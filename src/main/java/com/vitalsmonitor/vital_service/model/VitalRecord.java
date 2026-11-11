package com.vitalsmonitor.vital_service.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class VitalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long patientId;
    private int heartRate;
    private int systolicBp;
    private int diastolicBp;
    private LocalDateTime recordedAt;
}
