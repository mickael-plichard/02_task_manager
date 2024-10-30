package com.example.task_manager.controller;

import com.example.task_manager.model.Tache;
import com.example.task_manager.service.TacheService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/taches")
public class TacheController {

    @Autowired
    private TacheService tacheService;

    @GetMapping("/ajouter")
    public String afficherFormulaire(Model model) {
        model.addAttribute("tache", new Tache());
        return "formulaire_tache";
    }

    @PostMapping("/ajouter")
    public String traiterFormulaire(@ModelAttribute("tache") Tache tache) {
        tacheService.save(tache);
        return "redirect:/taches/liste";
    }

    @GetMapping("/liste")
    public String getAllTaches(Model model) {
        List<Tache> taches = tacheService.findAllSortedByDateEcheance(); // Appeler la méthode triée
        model.addAttribute("taches", taches != null ? taches : new ArrayList<>());
        return "taches";
    }

    @PostMapping("/supprimer/{id}")
    public String supprimerTache(@PathVariable Long id) {
        tacheService.deleteById(id);
        return "redirect:/taches/liste"; // Redirige vers la liste après suppression
    }

    @GetMapping("/modifier/{id}")
    public String afficherFormulaireModification(@PathVariable Long id, Model model) {
        Tache tache = tacheService.findById(id).orElse(null);
        if (tache == null) {
            return "redirect:/taches/liste";
        }
        model.addAttribute("tache", tache);
        return "modifiertache"; // Renvoie vers le formulaire d'édition
    }

    @PostMapping("/modifier")
    public String traiterFormulaireModification(@ModelAttribute("tache") Tache tache) {
        tacheService.save(tache); // Enregistre les modifications de la tâche
        return "redirect:/taches/liste";
    }
}
