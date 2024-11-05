import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.repository.ChambreRepository;
import tn.esprit.tpfoyer.service.ChambreServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
        chambre.setTypeC(TypeChambre.SIMPLE);

        chambreList = Arrays.asList(
                new Chambre(2L, 102, TypeChambre.DOUBLE, null, null),
                new Chambre(3L, 103, TypeChambre.TRIPLE, null, null)
        );
    }


    @Test
    void testRetrieveAllChambres() {
        when(chambreRepository.findAll()).thenReturn(chambreList);

        List<Chambre> retrievedChambres = chambreService.retrieveAllChambres();

        Mockito.verify(chambreRepository, times(1)).findAll();
        Mockito.verifyNoMoreInteractions(chambreRepository);
    }

    @Test
    void testAddChambre() {
        when(chambreRepository.save(chambre)).thenReturn(chambre);

        Chambre savedChambre = chambreService.addChambre(chambre);

        Mockito.verify(chambreRepository, times(1)).save(chambre);
        Mockito.verifyNoMoreInteractions(chambreRepository);
    }

    @Test
    void testRetrieveChambre() {
        when(chambreRepository.findById(1L)).thenReturn(Optional.of(chambre));

        Chambre retrievedChambre = chambreService.retrieveChambre(1L);

        Mockito.verify(chambreRepository, times(1)).findById(1L);
        
        Mockito.verifyNoMoreInteractions(chambreRepository);
    }

    @Test
    void testModifyChambre() {
        chambre.setTypeC(TypeChambre.TRIPLE);

        when(chambreRepository.save(chambre)).thenReturn(chambre);

        Chambre updatedChambre = chambreService.modifyChambre(chambre);

        Mockito.verify(chambreRepository, times(1)).save(chambre);
        Mockito.verifyNoMoreInteractions(chambreRepository);
    }

    @Test
    void testRemoveChambre() {
        doNothing().when(chambreRepository).deleteById(1L);

        chambreService.removeChambre(1L);

        Mockito.verify(chambreRepository, times(1)).deleteById(1L);
        Mockito.verifyNoMoreInteractions(chambreRepository); // Ensure no other interactions occurred
    }
}
