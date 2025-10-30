package co.edu.hotel.inventarioservice.services.generalinventory;

import co.edu.hotel.inventarioservice.repository.IGeneralInventoryRepository;

public class GeneralInventoryService {

    private final IGeneralInventoryRepository generalInventoryRepository;

    public GeneralInventoryService(IGeneralInventoryRepository generalInventoryRepository) {
        this.generalInventoryRepository = generalInventoryRepository;
    }
}
