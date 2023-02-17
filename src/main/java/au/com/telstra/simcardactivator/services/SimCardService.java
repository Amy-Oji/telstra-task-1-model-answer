package au.com.telstra.simcardactivator.services;

import au.com.telstra.simcardactivator.foundation.ActuationResult;
import au.com.telstra.simcardactivator.foundation.SimCard;

public interface SimCardService {

    ActuationResult actuate(SimCard simCard);
    SimCard getCustomerById(long simCardId);
}
