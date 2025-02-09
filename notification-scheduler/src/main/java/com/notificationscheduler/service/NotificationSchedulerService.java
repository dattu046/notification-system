package com.notificationscheduler.service;

import com.notificationscheduler.dto.NotificationPreferenceDTO;
import com.notificationscheduler.models.NotificationPreference;
import com.notificationscheduler.repository.NotificationPreferenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NotificationSchedulerService {

    private NotificationPreferenceRepository notificationPreferenceRepository;

    public String createNotificationSchedule(NotificationPreferenceDTO notificationPreferenceDTO) {
        UUID preferenceId=UUID.randomUUID();

        NotificationPreference notificationPreference= NotificationPreference.builder().preferenceId(preferenceId.toString()).notificationType(notificationPreferenceDTO.getNotificationType()).
        userId(notificationPreferenceDTO.getUserId()).groupId(notificationPreferenceDTO.getNotificationSchedule().getGroupId()).recurrence(notificationPreferenceDTO.getNotificationSchedule().getRecurrence())
                        .triggerTime(notificationPreferenceDTO.getNotificationSchedule().getTriggerTime()).message(notificationPreferenceDTO.getNotificationSchedule().getMessage())
                        .status("OPEN").build();
        notificationPreferenceRepository.save(notificationPreference);


        return preferenceId.toString();

    }

    public List<NotificationPreference> getNotificationPreferences(String userId) {
        List<NotificationPreference> notificationPreferences= notificationPreferenceRepository.findByUserId(userId);
        return notificationPreferences;
    }
}
