package fr.sumwhere.questionnaire.service;

import fr.sumwhere.questionnaire.dto.FormOptionsDTO;
import fr.sumwhere.questionnaire.model.FormOptions;
import fr.sumwhere.questionnaire.repo.FormOptionsRepo;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class FormOptionsServiceImpl implements FormOptionsService {

    private FormOptionsRepo formOptionsRepo;

    public FormOptionsServiceImpl() {
        super();
    }

    @Autowired
    public FormOptionsServiceImpl(FormOptionsRepo formOptionsRepo) {
        super();
        this.formOptionsRepo = formOptionsRepo;
    }

    @Override
    public FormOptions saveFormOptions(FormOptions formOptions) {
        FormOptions result = formOptionsRepo.save(formOptions);
        return result;
    }

    public Optional<FormOptionsDTO> findFormOptionsByAlias(String alias) {
        Optional<FormOptionsDTO> formOFound = formOptionsRepo.findByAlias(alias);
        return formOFound;
    }

    @Override
    public void deleteFormOptions(Long id) {
        try {
            formOptionsRepo.deleteById(id);
        }catch (EmptyResultDataAccessException e) {
            //Mettre logger
        }
    }

    @Override
    public Collection<FormOptions> getAllForms() {
        return IteratorUtils.toList(formOptionsRepo.findAll().iterator());
    }
}
