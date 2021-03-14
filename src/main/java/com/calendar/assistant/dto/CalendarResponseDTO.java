package com.calendar.assistant.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonPropertyOrder({ "Conflicts", "Meetings" })
public class CalendarResponseDTO implements Serializable
{

    @JsonProperty("Conflicts")
    private List<String> conflicts = null;

    @JsonProperty("Meetings")
    private List<MeetingDTO> meetingList = null;

    public List<String> getConflicts()
    {
        return conflicts;
    }

    public void setConflicts(List<String> conflicts)
    {
        this.conflicts = conflicts;
    }

    public List<MeetingDTO> getMeetingList()
    {
        return meetingList;
    }

    public void setMeetingList(List<MeetingDTO> meetingList)
    {
        this.meetingList = meetingList;
    }

    @Override
    public String toString()
    {
        return "CalendarResponseDTO [conflicts=" + conflicts + ", meetingList=" + meetingList + "]";
    }

}
