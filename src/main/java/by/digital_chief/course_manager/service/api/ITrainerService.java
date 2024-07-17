package by.digital_chief.course_manager.service.api;

import by.digital_chief.course_manager.core.dto.TrainerCreateDto;
import by.digital_chief.course_manager.core.dto.TrainerDto;

import java.util.List;

public interface ITrainerService {

    TrainerDto createTrainer(TrainerCreateDto dto);

    List<TrainerDto> getAllTrainers();

    TrainerDto getTrainerById(int id);

    TrainerDto updateTrainer(int id, TrainerCreateDto dto);

    void deleteTrainer(int id, TrainerDto dto);
}
