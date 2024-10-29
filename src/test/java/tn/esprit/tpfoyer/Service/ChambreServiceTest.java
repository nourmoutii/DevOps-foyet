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
        Assertions.assertFalse(chambres.isEmpty(), "Chambre list should not be empty.");
    }

    @Test
    @Order(2)
    public void testAddChambre() {
        Chambre chambre = new Chambre();
        chambre.setNumeroChambre(101);
        chambre.setTypeC(TypeChambre.SINGLE);

        Chambre savedChambre = chambreService.addChambre(chambre);
        Assertions.assertNotNull(savedChambre.getIdChambre(), "Saved chambre should have an ID.");
        Assertions.assertEquals(101, savedChambre.getNumeroChambre(), "Chambre number should match.");
        Assertions.assertEquals(TypeChambre.SINGLE, savedChambre.getTypeC(), "Chambre type should match.");
    }

    @Test
    @Order(3)
    public void testRetrieveChambre() {
        Chambre chambre = chambreService.retrieveChambre(1L);
        Assertions.assertNotNull(chambre, "Chambre should not be null.");
        Assertions.assertEquals(1L, chambre.getIdChambre(), "Chambre ID should match.");
    }

    @Test
    @Order(4)
    public void testModifyChambre() {
        Chambre chambre = chambreService.retrieveChambre(1L);
        chambre.setTypeC(TypeChambre.DOUBLE); 
        Chambre updatedChambre = chambreService.modifyChambre(chambre);

        Assertions.assertEquals(TypeChambre.DOUBLE, updatedChambre.getTypeC(), "Chambre type should be updated.");
    }

    @Test
    @Order(5)
    public void testRemoveChambre() {
        chambreService.removeChambre(1L);
        Chambre deletedChambre = chambreService.retrieveChambre(1L);

        Assertions.assertNull(deletedChambre, "Chambre should be null after deletion.");
    }

}
