import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.repository.ChambreRepository;
import tn.esprit.tpfoyer.service.ChambreServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChambreServiceImplMockTest {

    @Mock
    ChambreRepository chambreRepository;

    @InjectMocks
    ChambreServiceImpl chambreService;

    Chambre chambre;
    List<Chambre> chambreList;

    @BeforeEach
    void setUp() {
        chambre = new Chambre();
        chambre.setIdChambre(1L);
        chambre.setNumeroChambre(101);
        chambre.setTypeC(TypeChambre.SIMPLE);  // Use an existing type here

        chambreList = Arrays.asList(
                new Chambre(2L, 102, TypeChambre.DOUBLE, null, null),
                new Chambre(3L, 103, TypeChambre.TRIPLE, null, null)
        );
    }

    @Test
    void testRetrieveAllChambres() {
        when(chambreRepository.findAll()).thenReturn(chambreList);

        List<Chambre> retrievedChambres = chambreService.retrieveAllChambres();

        assertNotNull(retrievedChambres);
        assertEquals(2, retrievedChambres.size());
        verify(chambreRepository, times(1)).findAll();
    }

    @Test
    void testAddChambre() {
        when(chambreRepository.save(chambre)).thenReturn(chambre);

        Chambre savedChambre = chambreService.addChambre(chambre);

        assertNotNull(savedChambre);
        assertEquals(1L, savedChambre.getIdChambre());
        verify(chambreRepository, times(1)).save(chambre);
    }

    @Test
    void testRetrieveChambre() {
        when(chambreRepository.findById(1L)).thenReturn(Optional.of(chambre));

        Chambre retrievedChambre = chambreService.retrieveChambre(1L);

        assertNotNull(retrievedChambre);
        assertEquals(1L, retrievedChambre.getIdChambre());
        verify(chambreRepository, times(1)).findById(1L);
    }

    @Test
    void testModifyChambre() {
        chambre.setTypeC(TypeChambre.TRIPLE);  // Modify using an existing type
        when(chambreRepository.save(chambre)).thenReturn(chambre);

        Chambre updatedChambre = chambreService.modifyChambre(chambre);

        assertEquals(TypeChambre.TRIPLE, updatedChambre.getTypeC());
        verify(chambreRepository, times(1)).save(chambre);
    }

    @Test
    void testRemoveChambre() {
        doNothing().when(chambreRepository).deleteById(1L);

        chambreService.removeChambre(1L);

        verify(chambreRepository, times(1)).deleteById(1L);
    }
}
