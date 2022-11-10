package com.myorg;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class MediaConcurrency {

    public MediaConcurrency(String channel, Integer  concurrency) {
        this.Channel = channel;
        this.Concurrency = concurrency;
    }
    @JsonProperty("Channel")
    private String Channel;
    public String getChannel() {
        return Channel;
    }
    public void setChannel(String channel) {
        Channel = channel;
    }
    @JsonProperty("Concurrency")
    private Integer Concurrency;
    public Integer getConcurrency() {
        return Concurrency;
    }
    public void setConcurrency(Integer concurrency) {
        Concurrency = concurrency;
    }

//     @Override
//     public String toString() {
//         return "{\"Channel\":"+ this.Channel + ", \"Concurrency\": "+ this.Concurrency + "}";
//     }
 }
