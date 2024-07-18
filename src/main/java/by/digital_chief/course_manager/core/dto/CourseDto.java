package by.digital_chief.course_manager.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class CourseDto {

    private int id;

    private String title;

    private String description;

    @JsonProperty("trainer_id")
    private int trainerId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourseDto courseDto)) return false;
        return getId() == courseDto.getId() && getTrainerId() == courseDto.getTrainerId() && Objects.equals(getTitle(), courseDto.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getTrainerId());
    }
}
