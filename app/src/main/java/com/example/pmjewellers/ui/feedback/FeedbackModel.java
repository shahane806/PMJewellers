package com.example.pmjewellers.ui.feedback;

import android.net.Uri;

public class FeedbackModel {
    private String title;
    private Uri image;
    private String feedback;
    public FeedbackModel(String title, String feedback,Uri image){
        this.title = title;
        this.feedback = feedback;
        this.image = image;

    }
    public FeedbackModel(){}
    public String getFeedback() {
        return feedback;
    }

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
