package by.digital_chief.course_manager.service.implementation;

import by.digital_chief.course_manager.core.dto.CourseDto;
import by.digital_chief.course_manager.core.dto.TrainerCreateDto;
import by.digital_chief.course_manager.core.dto.TrainerDto;
import by.digital_chief.course_manager.exception.BadRequestException;
import by.digital_chief.course_manager.exception.TrainerNotFoundException;
import by.digital_chief.course_manager.mapper.TrainerMapper;
import by.digital_chief.course_manager.repository.api.ITrainerRepository;
import by.digital_chief.course_manager.repository.entity.Trainer;
import by.digital_chief.course_manager.service.api.ICourseService;
import by.digital_chief.course_manager.service.api.ITrainerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TrainerService implements ITrainerService {

    private static final Logger logger = LoggerFactory.getLogger(TrainerService.class);

    private final ITrainerRepository trainerRepository;
    private final ICourseService courseService;
    private final TrainerMapper mapper;

    private static final String DATA_DISCREPANCY = "Trainer information discrepancy: provided DTO does not match the database entry";

    public TrainerService(ITrainerRepository trainerRepository, ICourseService courseService, TrainerMapper mapper) {
        this.trainerRepository = trainerRepository;
        this.courseService = courseService;
        this.mapper = mapper;
    }

    @Transactional
    @Override
    public TrainerDto createTrainer(TrainerCreateDto dto) {
        logger.info("Saving trainer with name: {}", dto.getName());

        Trainer trainer = trainerRepository.save(mapper.toEntity(dto));

        logger.info("Saved trainer with id: {}", dto.getName());
        return mapper.toDto(trainer);
    }

    @Transactional(readOnly = true)
    @Override
    public List<TrainerDto> getAllTrainers() {
        logger.info("Fetching trainers");

        List<Trainer> list = trainerRepository.findAll();
        if(list.isEmpty()) {

            logger.warn("No trainers found");
            throw new TrainerNotFoundException();
        }
        List<TrainerDto> resultList = mapper.toDtoList(list);

        logger.info("Fetched {} trainers", resultList.size());
        return resultList;
    }

    @Transactional(readOnly = true)
    @Override
    public TrainerDto getTrainerById(int id) {
        logger.info("Fetching trainer with id: {}", id);

        Trainer trainer = findById(id);
        TrainerDto dto = mapper.toDto(trainer);

        logger.info("Fetched trainer with id {}", dto.getId());

        return dto;
    }

    @Transactional
    @Override
    public TrainerDto updateTrainer(int id, TrainerCreateDto dto) {

        logger.info("Updating trainer with id: {}", id);

        Trainer trainer = findById(id);
        mapper.updateEntity(trainer, dto);
        TrainerDto newDto = mapper.toDto(trainer);

        logger.info("Updated trainer with id: {}", newDto.getId());

        return newDto;
    }

    @Transactional
    @Override
    public void deleteTrainer(int id, TrainerDto dto) {
        logger.info("Deleting trainer with id: {}", id);

        Trainer trainer = findById(id);
        TrainerDto dtoInDB = mapper.toDto(trainer);

        if(!dto.equals(dtoInDB)) {
            logger.warn(DATA_DISCREPANCY);
            throw new BadRequestException(DATA_DISCREPANCY);
        }

        trainerRepository.delete(trainer);

        for(CourseDto courseDto : courseService.getCoursesByTrainer(id)) {
            courseService.deleteCourse(courseDto.getId(), courseDto);
        }

        logger.info("Deleted trainer with id: {}", id);
    }

    private Trainer findById(int id) {
        return trainerRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Trainer with id {} not found", id);
                    return new TrainerNotFoundException("with id: " + id);
                });
    }
}
