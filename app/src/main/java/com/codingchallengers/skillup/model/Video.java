package com.codingchallengers.skillup.model;

public class Video {
    String videoId, videoLink, videoTitle, Module;
    Long ID, duration, important;

    public Video() {
    }

    @Override
    public String toString() {
        return "Video{" +
                "videoId='" + videoId + '\'' +
                ", videoLink='" + videoLink + '\'' +
                ", videoTitle='" + videoTitle + '\'' +
                ", ID=" + ID +
                ", duration=" + duration +
                ", important=" + important +
                ", module=" + Module +
                '}';
    }

    public Video(String videoId, String videoLink, String videoTitle, Long ID, Long duration, Long important,String Module) {
        this.videoId = videoId;
        this.videoLink = videoLink;
        this.videoTitle = videoTitle;
        this.ID = ID;
        this.duration = duration;
        this.important = important;
        this.Module=Module;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Long getImportant() {
        return important;
    }

    public void setImportant(Long important) {
        this.important = important;
    }

    public String getModule() {
        return Module;
    }

    public void setModule(String Module) {
        this.Module = Module;
    }
}
