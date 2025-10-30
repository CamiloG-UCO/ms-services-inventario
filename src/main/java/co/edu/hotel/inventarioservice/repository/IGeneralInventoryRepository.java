package co.edu.hotel.inventarioservice.repository;

import co.edu.hotel.inventarioservice.domain.GeneralInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGeneralInventoryRepository extends JpaRepository<GeneralInventory, Integer> {

}
