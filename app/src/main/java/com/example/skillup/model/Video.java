package com.example.skillup.model;

public class Video {
    String videoId, videoLink, videoTitle, module;
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
                '}';
    }

    public Video(String videoId, String videoLink, String videoTitle, Long ID, Long duration, Long important,String module) {
        this.videoId = videoId;
        this.videoLink = videoLink;
        this.videoTitle = videoTitle;
        this.ID = ID;
        this.duration = duration;
        this.important = important;
        this.module=module;
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
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }
}
