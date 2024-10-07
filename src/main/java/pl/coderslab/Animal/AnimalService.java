package pl.coderslab.Animal;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Transactional
    public void saveAnimal(Animal animal) {
        animalRepository.save(animal);
    }

    public List<Animal> findAllAnimals() {
        return animalRepository.findAll();
    }

    public Animal findAnimalById(Long id) {
        return animalRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteAnimalById(Long id) {
        animalRepository.deleteById(id);
    }

    public List<Animal> findAnimalByAnimalName(String animalName) {
        return animalRepository.findByAnimalName(animalName);
    }

    public List<Animal> findAnimalByCategory(String category) {
        return animalRepository.findByCategory(category);
    }

    public List<Animal> findAnimalsByUsername(String username) {
        return animalRepository.findByUser_Username(username);
    }

    public List<Animal> findAnimalsByLocationName(String locationName) {
        return animalRepository.findByLocation_LocationName(locationName);
    }

    public List<Animal> findAnimalsByBiome(String biome) {
        return animalRepository.findByLocation_Biome(biome);
    }
}
