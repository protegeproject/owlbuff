package edu.stanford.protege.owlbuff;

import edu.stanford.protege.owlbuf.AnonymousIndividual;
import org.semanticweb.owlapi.model.OWLAnonymousIndividual;

import javax.annotation.Nonnull;
import javax.inject.Inject;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-01-12
 */
public class OwlAnonymousIndividual2Msg {

    @Inject
    public OwlAnonymousIndividual2Msg() {
    }

    @Nonnull
    public AnonymousIndividual toMsg(@Nonnull OWLAnonymousIndividual individual) {
        var nodeId = individual.asOWLAnonymousIndividual().getID().getID();
        return AnonymousIndividual.newBuilder().setId(nodeId).build();
    }
}
