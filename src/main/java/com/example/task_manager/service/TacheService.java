package com.example.task_manager.service;

import com.example.task_manager.model.Tache;
import com.example.task_manager.repository.TacheRepository;
import java.util.Comparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TacheService {

    @Autowired
    private TacheRepository tacheRepository;

    public void save(Tache tache) {
        tacheRepository.save(tache);
    }

    public List<Tache> findAllSortedByDateEcheance() {
        List<Tache> taches = tacheRepository.findAll();
        taches.sort(Comparator.comparing(Tache::getDateEcheance)); // Trier par date d'échéance
        return taches;
    }

    public Optional<Tache> findById(Long id) {
        return tacheRepository.findById(id);
    }

    public void deleteById(Long id) {
        tacheRepository.deleteById(id);
    }
}
