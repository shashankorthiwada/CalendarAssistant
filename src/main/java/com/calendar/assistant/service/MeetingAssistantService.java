package com.calendar.assistant.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calendar.assistant.dto.Calendar;
import com.calendar.assistant.dto.CalendarResponseDTO;
import com.calendar.assistant.dto.MeetingDTO;
import com.calendar.assistant.service.helper.MeetingAssistantHelper;

@Service
public class MeetingAssistantService
{
    @Autowired
    private MeetingAssistantHelper meetingAssistantHelper;

    public CalendarResponseDTO resolveMeetingConflicts(Calendar calendar)
    {
        List<MeetingDTO> meetingList = calendar.getMeetingList();

        CalendarResponseDTO response = new CalendarResponseDTO();

        // response.setConflicts(null);
        response.setMeetingList(meetingList);

        if (!meetingList.isEmpty() && meetingList.size() > 1)
        {
            LinkedList<MeetingDTO> sortedMeetingList = meetingAssistantHelper.sortMeetings(meetingList, calendar);

            response = meetingAssistantHelper.checkConflictsAndResolve(sortedMeetingList);
        }

        return response;

    }

}
