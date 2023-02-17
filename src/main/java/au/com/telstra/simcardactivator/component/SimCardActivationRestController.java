package au.com.telstra.simcardactivator.component;

import au.com.telstra.simcardactivator.foundation.SimCard;
import org.springframework.web.bind.annotation.*;

@RestController
public class SimCardActivationRestController {

    private final SimCardActuationHandler simCardActuationHandler;

    public SimCardActivationRestController(SimCardActuationHandler simCardActuationHandler) {
        this.simCardActuationHandler = simCardActuationHandler;
    }

    @PostMapping(value = "/activate")
    public void handleActivationRequest(@RequestBody SimCard simCard) {
        var actuationResult = simCardActuationHandler.actuate(simCard);
        System.out.println(actuationResult.getSuccess());
    }

    @GetMapping(value = "/get-customer/{id}")
    public SimCard getCustomer(@PathVariable("id") long simCardId) {
        return simCardActuationHandler.getCustomerById(simCardId);
    }

}
