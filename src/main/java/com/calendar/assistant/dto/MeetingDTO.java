package com.calendar.assistant.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonPropertyOrder({ "StartTime", "EndTime", "Organizer", "Invitees" })
public class MeetingDTO implements Serializable
{

    @JsonProperty("StartTime")
    private String startTime;

    @JsonProperty("EndTime")
    private String endTime;

    @JsonProperty("Organizer")
    private String organizer;

    @JsonProperty("Invitees")
    private int invitees;

    public String getStartTime()
    {
        return startTime;
    }

    public void setStartTime(String startTime)
    {
        this.startTime = startTime;
    }

    public String getEndTime()
    {
        return endTime;
    }

    public void setEndTime(String endTime)
    {
        this.endTime = endTime;
    }

    public String getOrganizer()
    {
        return organizer;
    }

    public void setOrganizer(String organizer)
    {
        this.organizer = organizer;
    }

    public int getInvitees()
    {
        return invitees;
    }

    public void setInvitees(int invitees)
    {
        this.invitees = invitees;
    }

    @Override
    public String toString()
    {
        return "MeetingDTO [startTime=" + startTime + ", endTime=" + endTime + ", organizer=" + organizer
                + ", invitees=" + invitees + "]";
    }

}
