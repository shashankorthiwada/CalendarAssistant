package com.calendar.assistant.service.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.calendar.assistant.dao.resource.EmployeeResource;
import com.calendar.assistant.dto.Calendar;
import com.calendar.assistant.dto.CalendarResponseDTO;
import com.calendar.assistant.dto.MeetingDTO;
import com.calendar.assistant.entity.Employee;
import com.calendar.assistant.util.ConflictValidatorUtil;

@Component
public class MeetingAssistantHelper
{
    @Autowired
    private EmployeeResource employeeResource;

    @Autowired
    private ConflictValidatorUtil conflictValidatorUtil;

    public LinkedList<MeetingDTO> sortMeetings(List<MeetingDTO> meetingList, Calendar calendar)
    {
        LinkedList<MeetingDTO> sortedMeetingList = new LinkedList<>();

        TreeMap<Integer, List<MeetingDTO>> rankSortMap = new TreeMap<>();

        // Sort the List based on Number of Invitees if Organizer is Same
        Comparator<MeetingDTO> meetingComparator = (meeting1, meeting2) -> {
            if (meeting1.getOrganizer().equals(meeting2.getOrganizer())
                    && meeting1.getInvitees() > meeting2.getInvitees())
            {
                return 1;
            }
            return 0;
        };
        Collections.sort(meetingList, meetingComparator);

        // Sort the list based on Rank
        meetingList.stream().forEach(meeting -> {
            List<MeetingDTO> mapMeetingList = new ArrayList<>();
            Employee organizer = employeeResource.findByEmployeeName(meeting.getOrganizer());
            if (rankSortMap.containsKey(organizer.getRank()))
            {
                List<MeetingDTO> existingList = rankSortMap.get(organizer.getRank());
                existingList.add(meeting);
                rankSortMap.put(organizer.getRank(), mapMeetingList);
            }
            else
            {
                mapMeetingList.add(meeting);
                rankSortMap.put(organizer.getRank(), mapMeetingList);
            }
        });

        // Add the meetings to SortedList based on the priorities
        rankSortMap.forEach((rank, list) -> list.stream().forEach(meeting -> {
            if (sortedMeetingList.isEmpty() && calendar.getOwner().equals(meeting.getOrganizer()))
            {
                sortedMeetingList.addFirst(meeting);
            }
            else
            {
                sortedMeetingList.add(meeting);
            }

        })

        );

        return sortedMeetingList;

    }

    public CalendarResponseDTO checkConflictsAndResolve(LinkedList<MeetingDTO> sortedMeetingList)
    {

        CalendarResponseDTO calendarResponse = conflictValidatorUtil.resolveConflicts(sortedMeetingList);

        return calendarResponse;
    }

}
