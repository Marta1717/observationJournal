package pl.coderslab.Observation;

import org.springframework.stereotype.Service;
import pl.coderslab.Animal.CATEGORY;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
public class ObservationService {

    private final ObservationRepository observationRepository;

    public ObservationService(ObservationRepository observationRepository) {
        this.observationRepository = observationRepository;
    }

    @Transactional
    public void saveObservation (Observation observation) {
        observationRepository.save(observation);
    }

    @Transactional
    public void editObservation (Observation observation) {
        observationRepository.save(observation);
    }

    public List<Observation> findAllObservations() {
        return observationRepository.findAll();
    }

    public Observation findObservationById(Long id) {
        return observationRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteObservationById(Long id) {
        observationRepository.deleteById(id);
    }

    public List<Observation> findObservationsByAnimalName(String animalName) {
        return observationRepository.findByAnimal_AnimalName(animalName);
    }

    public List<Observation> findObservationsByCategory(String category) {
        try {
            CATEGORY categoryEnum = CATEGORY.valueOf(category);
            return observationRepository.findByAnimal_Category(categoryEnum);
        } catch (IllegalArgumentException ex) {
            return Collections.emptyList();
        }
    }

    public List<Observation> findObservationsByUsername(String username) {
        return observationRepository.findByUser_Username(username);
    }

    public List<Observation> findObservationsByLocationName(String locationName) {
        return observationRepository.findByLocation_LocationName(locationName);
    }
}