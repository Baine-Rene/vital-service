import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class VitalHandlingServiceTest {

    @Mock
    private AlertRepository alertRepository; // We mock dependencies

    @InjectMocks
    private VitalHandlingService vitalService; // The service we are testing

    public VitalHandlingServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenHeartRateIsCriticallyHigh_shouldTriggerHighSeverityAlert() {
        // 1. Arrange: Create a mock VitalRecord with a dangerous heart rate (130 bpm)
        VitalRecord dangerousRecord = new VitalRecord();
        dangerousRecord.setPatientId(101L);
        dangerousRecord.setHeartRate(130); 
        dangerousRecord.setSystolicBp(120);
        dangerousRecord.setDiastolicBp(80);

        // 2. Act: Pass the record to our service layer logic
        Alert resultAlert = vitalService.evaluateVitals(dangerousRecord);

        // 3. Assert: Verify the logic correctly identified the danger
        assertNotNull(resultAlert, "An alert should be generated for high heart rate.");
        assertEquals("HIGH", resultAlert.getSeverity());
        assertTrue(resultAlert.getAlertMessage().contains("Critical Heart Rate"));
        
        // Verify that the code actually tried to save this alert to the database
        verify(alertRepository, times(1)).save(any(Alert.class));
    }
}
