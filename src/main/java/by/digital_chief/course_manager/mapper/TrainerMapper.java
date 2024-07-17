package by.digital_chief.course_manager.mapper;

import by.digital_chief.course_manager.core.dto.TrainerCreateDto;
import by.digital_chief.course_manager.core.dto.TrainerDto;
import by.digital_chief.course_manager.repository.entity.Trainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrainerMapper {

    private static final Logger logger = LoggerFactory.getLogger(TrainerMapper.class);

    public TrainerDto toDto(Trainer trainer) {
        logger.info("Converting {} trainer to DTO", trainer.getId());

        TrainerDto dto = new TrainerDto();
        dto.setId(trainer.getId());
        dto.setName(trainer.getName());

        logger.info("TrainerDto {} created", dto.getId());
        return dto;
    }

    public List<TrainerDto> toDtoList(List<Trainer> trainerList) {
        logger.info("Converting {} trainers to DTOs", trainerList.size());

        return trainerList.
                stream().map(this::toDto).
                collect(Collectors.toList());
    }

    public Trainer toEntity(TrainerCreateDto dto) {
        logger.info("Converting {} DTO to trainer", dto.getName());

        Trainer trainer = new Trainer();
        trainer.setName(dto.getName());

        logger.info("Trainer {} created", trainer.getName());
        return trainer;
    }

    public void updateEntity(Trainer trainer, TrainerCreateDto dto) {
        logger.info("Updating {} trainer", trainer.getId());

        trainer.setName(dto.getName());

        logger.info("Trainer {} updated", trainer.getId());
    }
}
