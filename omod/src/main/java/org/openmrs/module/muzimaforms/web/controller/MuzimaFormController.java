package org.openmrs.module.muzimaforms.web.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.muzimaforms.MuzimaForm;
import org.openmrs.module.muzimaforms.api.MuzimaFormService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;


@Controller
@RequestMapping(value = "module/muzimaforms")
public class MuzimaFormController {

    //TODO: Use MuzimaFormResource to handle the save
    @RequestMapping(method = RequestMethod.POST, value = "form.form")
    @ResponseBody
    public void save(final @RequestBody MuzimaForm form) throws Exception {
        MuzimaFormService service = Context.getService(MuzimaFormService.class);
        service.save(form);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "retire/{formId}.form")
    @ResponseBody
    public void retire(final @PathVariable Integer formId) throws Exception {
        MuzimaFormService service = Context.getService(MuzimaFormService.class);
        MuzimaForm form = service.findById(formId);
        form.setRetired(true);
        form.setRetiredBy(Context.getAuthenticatedUser());
        form.setDateRetired(new Date());
        service.save(form);
    }

}
