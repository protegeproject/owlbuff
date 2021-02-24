package edu.stanford.protege.owlbuff;

import edu.stanford.protege.owlbuf.NamedIndividual;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-01-12
 */
public class OwlNamedIndividual2Msg {

    @Nonnull
    private final Iri2Msg iri2Msg;

    @Inject
    public OwlNamedIndividual2Msg(@Nonnull Iri2Msg iri2Msg) {
        this.iri2Msg = checkNotNull(iri2Msg);
    }

    @Nonnull
    NamedIndividual toMsg(@Nonnull OWLNamedIndividual namedIndividual) {
        return NamedIndividual.newBuilder()
                .setIri(iri2Msg.toMsg(namedIndividual.getIRI()))
                .build();
    }
}
