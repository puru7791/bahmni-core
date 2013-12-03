package org.bahmni.module.bahmnicore.web.v1_0.controller;

import org.bahmni.module.bahmnicore.contract.encounter.data.ConceptData;
import org.bahmni.module.bahmnicore.contract.encounter.data.PersonObservationData;
import org.bahmni.module.bahmnicore.service.BahmniPersonObsService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.openmrs.Concept;
import org.openmrs.ConceptName;
import org.openmrs.Obs;
import org.openmrs.Person;
import org.openmrs.api.AdministrationService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class BahmniObsControllerTest {
    @Mock
    BahmniPersonObsService bahmniPersonObsService;
    @Mock
    AdministrationService administrationService   ;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    @Ignore
    public void shouldGetObsForPersonAndMap() {
        ArrayList<Obs> obs = new ArrayList<>();
        Person person = new Person(1);

        Concept concept = new Concept(1);
        concept.addName(new ConceptName("concept", Locale.ENGLISH));
        obs.add(new Obs(person, concept, new Date(), null));

        when(bahmniPersonObsService.getObsForPerson("foo")).thenReturn(obs);

        BahmniObsController controller = new BahmniObsController(bahmniPersonObsService);
        List<PersonObservationData> observationDataList = controller.get("foo");
        verify(bahmniPersonObsService).getObsForPerson("foo");

    }

    @Test
    @Ignore
    public void shouldGetNumericalConceptForPersonAndMap() {
        ArrayList<Concept> concepts = new ArrayList<>();

        Concept concept1 = new Concept(1);
        concept1.addName(new ConceptName("concept1", Locale.ENGLISH));

        Concept concept2 = new Concept(2);
        concept2.addName(new ConceptName("concept2", Locale.ENGLISH));

        concepts.add(concept1);
        concepts.add(concept2);

        when(bahmniPersonObsService.getNumericConceptsForPerson("foo")).thenReturn(concepts);

        BahmniObsController controller = new BahmniObsController(bahmniPersonObsService);
        List<ConceptData> observationDataList = controller.getConceptsfor("foo");
        verify(bahmniPersonObsService).getObsForPerson("foo");

    }
}