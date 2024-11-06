package tn.esprit.tpfoyer;

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
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class ChambreServiceImplMock {

    @Autowired
    IChambreService chambreService;

    @Test
    @Order(1)
    public void testRetrieveAllChambres() {
        List<Chambre> retrievedChambres = chambreService.retrieveAllChambres();
        Assertions.assertNotNull(retrievedChambres);
    }

    @Test
    @Order(2)
    public void testAddChambre() {
        Chambre chambre = new Chambre();
        chambre.setNumeroChambre(101);
        chambre.setTypeC(TypeChambre.SIMPLE);

        Chambre savedChambre = chambreService.addChambre(chambre);

        Assertions.assertNotNull(savedChambre);
        Assertions.assertEquals(chambre.getNumeroChambre(), savedChambre.getNumeroChambre());
        Assertions.assertEquals(chambre.getTypeC(), savedChambre.getTypeC());
    }

    @Test
    @Order(3)
    public void testRetrieveChambre() {
        Optional<Chambre> retrievedChambre = chambreService.retrieveChambre(1L);

        Assertions.assertTrue(retrievedChambre.isPresent());
        Assertions.assertEquals(1L, retrievedChambre.get().getIdChambre());
    }

    @Test
    @Order(4)
    public void testModifyChambre() {
        Chambre chambre = new Chambre();
        chambre.setIdChambre(1L);
        chambre.setNumeroChambre(101);
        chambre.setTypeC(TypeChambre.TRIPLE);

        Chambre updatedChambre = chambreService.modifyChambre(chambre);

        Assertions.assertNotNull(updatedChambre);
        Assertions.assertEquals(TypeChambre.TRIPLE, updatedChambre.getTypeC());
    }

    @Test
    @Order(5)
    public void testRemoveChambre() {
        chambreService.removeChambre(1L);

        Optional<Chambre> retrievedChambre = chambreService.retrieveChambre(1L);
        Assertions.assertTrue(retrievedChambre.isEmpty());
    }
}
