package edu.stanford.protege.owlbuff;

import edu.stanford.protege.owlbuf.ObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectProperty;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-01-12
 */
public class OwlObjectProperty2Msg {

    @Nonnull
    private final Iri2Msg iri2Msg;

    @Inject
    public OwlObjectProperty2Msg(@Nonnull Iri2Msg iri2Msg) {
        this.iri2Msg = checkNotNull(iri2Msg);
    }

    @Nonnull
    public ObjectProperty toMsg(@Nonnull OWLObjectProperty property) {
        return ObjectProperty.newBuilder()
                .setIri(iri2Msg.toMsg(property.getIRI()))
                .build();
    }
}
