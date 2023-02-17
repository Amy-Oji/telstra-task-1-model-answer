package au.com.telstra.simcardactivator.component;

import au.com.telstra.simcardactivator.foundation.ActuationResult;
import au.com.telstra.simcardactivator.foundation.SimCard;
import au.com.telstra.simcardactivator.foundation.SimCardEntity;
import au.com.telstra.simcardactivator.repositories.SimCardRepository;
import au.com.telstra.simcardactivator.services.SimCardService;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SimCardActuationHandler implements SimCardService {
    private final RestTemplate restTemplate;
    private final String incentiveApiUrl;

    private final SimCardRepository simCardRepository;

    public SimCardActuationHandler(RestTemplateBuilder builder, SimCardRepository simCardRepository) {
        this.restTemplate = builder.build();
        this.incentiveApiUrl = "http://localhost:8444/actuate";
        this.simCardRepository = simCardRepository;
    }

    public ActuationResult actuate(SimCard simCard) {
        ActuationResult response =  restTemplate.postForObject(incentiveApiUrl, simCard, ActuationResult.class);
        assert response != null;

        SimCardEntity simCardEntity = new SimCardEntity();
        BeanUtils.copyProperties(simCard, simCardEntity);
        simCardEntity.setActive(response.getSuccess());
        simCardRepository.save(simCardEntity);

        return response;
    }

    public SimCard getCustomerById(long simCardId){
        SimCardEntity customer = simCardRepository.findById(simCardId)
                .orElseThrow(()-> new IllegalArgumentException("Customer with id number " + simCardId + " not found"));

        SimCard simCard = new SimCard();
        BeanUtils.copyProperties(customer, simCard);

        return simCard;
    }

}
