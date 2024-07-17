package by.digital_chief.course_manager.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CourseCreateDto {

    @JsonProperty(required = true)
    private String title;

    private String description;

    @JsonProperty(value = "trainer_id", required = true)
    private int trainerId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }
}
