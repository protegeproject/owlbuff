package edu.stanford.protege.owlbuff;

import edu.stanford.protege.owlbuf.Datatype;
import org.semanticweb.owlapi.model.OWLDatatype;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-01-12
 */
public class OwlDatatype2Msg {

    @Nonnull
    private final Iri2Msg iri2Msg;

    @Inject
    public OwlDatatype2Msg(@Nonnull Iri2Msg iri2Msg) {
        this.iri2Msg = checkNotNull(iri2Msg);
    }

    @Nonnull
    public Datatype toMsg(@Nonnull OWLDatatype datatype) {
        return Datatype.newBuilder()
                .setIri(iri2Msg.toMsg(datatype.getIRI()))
                .build();
    }
}
