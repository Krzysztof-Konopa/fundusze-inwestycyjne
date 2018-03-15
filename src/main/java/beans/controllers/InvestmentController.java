package beans.controllers;

import beans.dtos.InvestmentDTO;
import beans.models.Investment;
import beans.services.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/investment")
@SuppressWarnings("unused")
public class InvestmentController {

    @Autowired
    private InvestmentService investmentService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Investment computeInvestment(@RequestBody InvestmentDTO investmentDTO) throws Exception {
        InvestmentService.validateInvestmentDTO(investmentDTO);
        return investmentService.compute(investmentDTO);
    }
}


