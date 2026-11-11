package com.vitalsmonitor.vital_service.service;

import com.vitalsmonitor.vital_service.model.Alert;
import com.vitalsmonitor.vital_service.model.VitalRecord;
import com.vitalsmonitor.vital_service.repository.AlertRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class VitalHandlingService {

    private final AlertRepository alertRepository;

    public VitalHandlingService(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    public Alert evaluateVitals(VitalRecord record) {
        if (record.getHeartRate() > 120 || record.getHeartRate() < 50) {
            Alert alert = new Alert();
            alert.setPatientId(record.getPatientId());
            alert.setSeverity("HIGH");
            alert.setAlertMessage("Critical Heart Rate Detected: " + record.getHeartRate() + " bpm.");
            alert.setTriggeredAt(LocalDateTime.now());
            
            return alertRepository.save(alert);
        }
        return null;
    }
}
