package com.calendar.assistant.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonPropertyOrder({ "Owner", "Meetings" })
public class Calendar implements Serializable
{

    @JsonProperty("Owner")
    private String owner;

    @JsonProperty("Meetings")
    private List<MeetingDTO> meetingList = null;

    public String getOwner()
    {
        return owner;
    }

    public void setOwner(String owner)
    {
        this.owner = owner;
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
        return "Calendar [owner=" + owner + ", meetingList=" + meetingList + "]";
    }

}
