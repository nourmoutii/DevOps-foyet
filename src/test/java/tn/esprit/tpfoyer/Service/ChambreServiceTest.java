import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.service.IChambreService;

import java.util.List;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class ChambreServiceImplTest {

    @Autowired
    IChambreService chambreService;

    @Test
    @Order(1)
    public void testRetrieveAllChambres() {
        List<Chambre> chambres = chambreService.retrieveAllChambres();
        Assertions.assertNotNull(chambres);
    }

    @Test
    @Order(2)
    public void testAddChambre() {
        Chambre chambre = new Chambre(); // Set appropriate fields for Chambre here
        Chambre savedChambre = chambreService.addChambre(chambre);
        Assertions.assertNotNull(savedChambre.getId());
    }

    @Test
    @Order(3)
    public void testRetrieveChambre() {
        Chambre chambre = chambreService.retrieveChambre(1L);
        Assertions.assertNotNull(chambre);
    }

    @Test
    @Order(4)
    public void testModifyChambre() {
        Chambre chambre = chambreService.retrieveChambre(1L); // Replace with an actual ID
        chambre.setType(TypeChambre.SINGLE); // Modify a field
        Chambre updatedChambre = chambreService.modifyChambre(chambre);
        Assertions.assertEquals(TypeChambre.SINGLE, updatedChambre.getType());
    }

    @Test
    @Order(5)
    public void testRemoveChambre() {
        chambreService.removeChambre(1L); // Replace with an actual ID
        Chambre deletedChambre = chambreService.retrieveChambre(1L);
        Assertions.assertNull(deletedChambre);
    }

}
