package edu.stanford.protege.owlbuff;

import edu.stanford.protege.owlbuf.AnnotationProperty;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-01-12
 */
public class OwlAnnotationProperty2Msg {

    private final Iri2Msg iri2Msg;

    @Inject
    public OwlAnnotationProperty2Msg(@Nonnull Iri2Msg iri2Msg) {
        this.iri2Msg = checkNotNull(iri2Msg);
    }

    @Nonnull
    public AnnotationProperty toMsg(OWLAnnotationProperty property) {
        return AnnotationProperty.newBuilder()
                .setIri(iri2Msg.toMsg(property.getIRI()))
                .build();
    }
}
