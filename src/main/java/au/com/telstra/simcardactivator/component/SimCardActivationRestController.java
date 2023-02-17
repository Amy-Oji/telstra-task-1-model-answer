package au.com.telstra.simcardactivator.component;

import au.com.telstra.simcardactivator.foundation.SimCard;
import au.com.telstra.simcardactivator.services.SimCardService;
import org.springframework.web.bind.annotation.*;

@RestController
public class SimCardActivationRestController {

    private final SimCardService simCardService;

    public SimCardActivationRestController(SimCardActuationHandler simCardService) {
        this.simCardService = simCardService;
    }

    @PostMapping(value = "/activate")
    public void handleActivationRequest(@RequestBody SimCard simCard) {
        var actuationResult = simCardService.actuate(simCard);
        System.out.println(actuationResult.getSuccess());
    }

    @GetMapping(value = "/get-customer/{id}")
    public SimCard getCustomer(@PathVariable("id") long simCardId) {
        return simCardService.getCustomerById(simCardId);
    }

}
