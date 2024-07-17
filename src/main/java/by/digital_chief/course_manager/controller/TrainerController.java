package by.digital_chief.course_manager.controller;

import by.digital_chief.course_manager.core.dto.TrainerCreateDto;
import by.digital_chief.course_manager.core.dto.TrainerDto;
import by.digital_chief.course_manager.service.api.ITrainerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/trainers")
public class TrainerController {

    private static final Logger logger = LoggerFactory.getLogger(TrainerController.class);

    private final ITrainerService trainerService;

    public TrainerController(ITrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PostMapping
    public ResponseEntity<TrainerDto> createTrainer(@RequestBody TrainerCreateDto dto) {
        logger.info("Received request to create trainer: {}", dto.getName());

        TrainerDto createdTrainer = trainerService.createTrainer(dto);

        logger.info("Trainer created successfully with id: {}", createdTrainer.getId());
        return new ResponseEntity<>(createdTrainer, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TrainerDto>> getAllTrainers() {
        logger.info("Received request to get list of all trainers");

        List<TrainerDto> trainers = trainerService.getAllTrainers();

        logger.info("Fetched {} trainers.", trainers.size());
        return new ResponseEntity<>(trainers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainerDto> getTrainerById(@PathVariable int id) {
        logger.info("Received request to get trainer with id {}", id);

        TrainerDto trainer = trainerService.getTrainerById(id);

        logger.info("Fetched trainer with id {}", trainer.getId());
        return new ResponseEntity<>(trainer, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainerDto> updateTrainer(@PathVariable int id, @RequestBody TrainerCreateDto dto) {
        logger.info("Received request to update trainer with id {}", id);

        TrainerDto updatedTrainer = trainerService.updateTrainer(id, dto);

        logger.info("Updated trainer with id {}", updatedTrainer.getId());
        return new ResponseEntity<>(updatedTrainer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainer(@PathVariable int id, @RequestBody TrainerDto dto) {
        logger.info("Received request to delete trainer with id {}", id);

        trainerService.deleteTrainer(id,dto);

        logger.info("Deleted trainer with id {}", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
