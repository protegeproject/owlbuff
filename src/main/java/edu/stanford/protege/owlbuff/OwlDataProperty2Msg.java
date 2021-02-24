package edu.stanford.protege.owlbuff;

import edu.stanford.protege.owlbuf.DataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-01-12
 */
public class OwlDataProperty2Msg {

    @Nonnull
    private final Iri2Msg iri2Msg;

    @Inject
    public OwlDataProperty2Msg(@Nonnull Iri2Msg iri2Msg) {
        this.iri2Msg = checkNotNull(iri2Msg);
    }

    @Nonnull
    public DataProperty toMsg(@Nonnull OWLDataPropertyExpression dataProperty) {
        return DataProperty.newBuilder()
                .setIri(iri2Msg.toMsg(dataProperty.asOWLDataProperty().getIRI()))
                .build();
    }

}
