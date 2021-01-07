package fr.sumwhere.questionnaire.controller;

import fr.sumwhere.questionnaire.dto.FormOptionsDTO;
import fr.sumwhere.questionnaire.model.FormOptions;
import fr.sumwhere.questionnaire.repo.FormOptionsRepo;
import fr.sumwhere.questionnaire.service.FormOptionsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("*")
@RestController()
@RequestMapping("/options/*")
public class FormOptionsController {
    @Autowired
    private FormOptionsRepo formOptionsRepo;

    @Autowired
    private FormOptionsService formOptionsService;

    @GetMapping("/{alias}")
    public Optional<FormOptionsDTO> findOptionsByAlias(@PathVariable(value = "alias") String alias){
        return formOptionsService.findFormOptionsByAlias(alias);
    }

    @PostMapping("/envoyer")
    public FormOptions saveFormOptions(@RequestBody FormOptions fOptions){
        return formOptionsRepo.save(fOptions);
    }


}
