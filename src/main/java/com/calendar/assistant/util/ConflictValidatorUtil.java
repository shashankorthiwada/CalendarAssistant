package com.calendar.assistant.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.springframework.stereotype.Component;

import com.calendar.assistant.dto.CalendarResponseDTO;
import com.calendar.assistant.dto.MeetingDTO;

@Component
public class ConflictValidatorUtil
{
    // Structure to represent an interval
    static class Meeting
    {
        int start, end;
    }

    // A utility function to create a new node
    static Meeting newNode(int l, int h)
    {
        Meeting temp = new Meeting();
        temp.start = l;
        temp.end = h;
        return temp;
    }

    static class MNode
    {
        Meeting meeting;

        int max;

        MNode left, right;
    }

    static MNode createNode(Meeting meeting)
    {
        MNode temp = new MNode();
        temp.meeting = meeting;
        temp.max = meeting.end;
        temp.right = null;
        temp.left = null;
        return temp;

    }

    public CalendarResponseDTO resolveConflicts(LinkedList<MeetingDTO> sortedMeetingList)
    {
        CalendarResponseDTO calendarResponseDTO = new CalendarResponseDTO();
        Map<Integer, String> map = new HashMap<>();
        Meeting[] meetingArr = new Meeting[sortedMeetingList.size()];

        for (int i = 0; i < sortedMeetingList.size(); i++)
        {
            String[] startTimeSplit = sortedMeetingList.get(i).getStartTime().split(":");
            int startHours = Integer.parseInt(startTimeSplit[0]);
            int startMinutes = Integer.parseInt(startTimeSplit[1]);

            int totalStartMinutes = startHours * 60 + startMinutes;

            String[] endTimeSplit = sortedMeetingList.get(i).getEndTime().split(":");
            int endHours = Integer.parseInt(endTimeSplit[0]);
            int endMinutes = Integer.parseInt(endTimeSplit[1]);

            int totalEndMinutes = endHours * 60 + endMinutes;
            map.put(totalStartMinutes + totalEndMinutes,
                    sortedMeetingList.get(i).getStartTime() + " " + sortedMeetingList.get(i).getEndTime());
            meetingArr[i] = newNode(totalStartMinutes, totalEndMinutes);

        }

        checkForConflicts(meetingArr, meetingArr.length, calendarResponseDTO, map);

        return calendarResponseDTO;
    }

    private void checkForConflicts(Meeting[] meetingArr, int length, CalendarResponseDTO calendarResponseDTO,
            Map<Integer, String> map)
    {

        MNode root = null;
        root = insert(root, meetingArr[0]);

        for (int i = 1; i < length; i++)
        {
            Meeting conflictMeeting = overLapSearch(root, meetingArr[i]);

            if (conflictMeeting != null)
            {
                StringBuilder firstConflict = new StringBuilder(map.get(meetingArr[i].start + meetingArr[i].end));
                StringBuilder secondConflict = new StringBuilder(map.get(conflictMeeting.start + conflictMeeting.end));
                firstConflict.append(" and ");
                firstConflict.append(secondConflict);
                // firstConflict.append(conflictMeeting.end + " ");

                if (calendarResponseDTO.getConflicts() == null)
                {
                    List<String> conflictList = new ArrayList<>();
                    conflictList.add(firstConflict.toString());
                    calendarResponseDTO.setConflicts(conflictList);

                    // System.out.println("There is a conflict in Meetings: " + meetingArr[i].start + " " +
                    // meetingArr[i].end
                    // + " Conflicts with " + conflictMeeting.start + " " + conflictMeeting.end);
                }
                else
                {
                    List<String> conflictList = calendarResponseDTO.getConflicts();
                    conflictList.add(firstConflict.toString());
                    calendarResponseDTO.setConflicts(conflictList);
                }
                Meeting newMeeting = newNode(meetingArr[i].start, meetingArr[i].end);
                root = insert(root, newMeeting);
            }
            else
            {
                root = insert(root, meetingArr[i]);
            }

        }
        calendarResponseDTO.setMeetingList(displayMeetings(root, map));
        // calendarResponseDTO.setMeetingList(meetingList);

    }

    private List<MeetingDTO> displayMeetings(MNode root, Map<Integer, String> map)
    {
        List<MeetingDTO> displayMeetingList = new ArrayList<>();
        Queue<MNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty())
        {
            MeetingDTO meetingDTO = new MeetingDTO();
            MNode tempNode = queue.poll();
            // System.out.print(tempNode.data + " ");

            String meetingString = map.get(tempNode.meeting.start + tempNode.meeting.end);
            String[] meetingStringArr = meetingString.split(" ");
            meetingDTO.setStartTime(meetingStringArr[0]);
            meetingDTO.setEndTime(meetingStringArr[1]);
            displayMeetingList.add(meetingDTO);
            if (tempNode.left != null)
            {
                queue.add(tempNode.left);
            }
            if (tempNode.right != null)
            {
                queue.add(tempNode.right);
            }

        }

        return displayMeetingList;

    }

    private Meeting overLapSearch(MNode root, Meeting meeting)
    {
        if (root == null)
            return null;

        if (isOverLap(root.meeting, meeting))
            return root.meeting;

        if (root.left != null && root.max >= meeting.start)

            return overLapSearch(root.left, meeting);

        return overLapSearch(root.right, meeting);

    }

    private boolean isOverLap(Meeting meeting, Meeting meeting2)
    {
        return meeting.start < meeting2.end && meeting2.start < meeting.end ? true : false;
    }

    private MNode insert(MNode root, Meeting meeting)
    {
        if (root == null)
            return createNode(meeting);

        int start = root.meeting.start;

        if (meeting.start < start)
            root.left = insert(root.left, meeting);

        else
            root.right = insert(root.right, meeting);

        if (root.max < meeting.end)
            root.max = meeting.end;

        return root;
    }

}
