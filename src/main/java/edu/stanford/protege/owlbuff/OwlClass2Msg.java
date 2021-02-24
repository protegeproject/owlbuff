package edu.stanford.protege.owlbuff;

import edu.stanford.protege.owlbuf.Cls;
import org.semanticweb.owlapi.model.OWLClass;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-01-12
 */
public class OwlClass2Msg {

    @Nonnull
    private final Iri2Msg iri2Msg;

    @Inject
    public OwlClass2Msg(@Nonnull Iri2Msg iri2Msg) {
        this.iri2Msg = checkNotNull(iri2Msg);
    }

    @Nonnull
    public Cls toMsg(@Nonnull OWLClass cls) {
        return Cls.newBuilder()
                .setIri(iri2Msg.toMsg(cls.getIRI()))
                .build();
    }
}
