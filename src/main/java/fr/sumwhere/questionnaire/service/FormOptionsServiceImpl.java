package fr.sumwhere.questionnaire.service;

import fr.sumwhere.questionnaire.model.FormOptions;
import fr.sumwhere.questionnaire.repo.FormOptionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
