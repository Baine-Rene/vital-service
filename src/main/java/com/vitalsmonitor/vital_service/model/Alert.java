package com.vitalsmonitor.vital_service.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long patientId;
    private String severity;
    private String alertMessage;
    private LocalDateTime triggeredAt;
}
