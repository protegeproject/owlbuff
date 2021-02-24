package edu.stanford.protege.owlbuff;

import edu.stanford.protege.owlbuf.Annotation;
import org.semanticweb.owlapi.model.OWLAxiom;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-01-12
 */
public class OwlAxiomAnnotations2Msg {

    @Nonnull
    private final OwlAnnotation2Msg annotation2Msg;

    @Inject
    public OwlAxiomAnnotations2Msg(@Nonnull OwlAnnotation2Msg annotation2Msg) {
        this.annotation2Msg = checkNotNull(annotation2Msg);
    }

    public Iterable<Annotation> toMsg(@Nonnull OWLAxiom axiom) {
        return () -> axiom.getAnnotations().stream().map(annotation2Msg::toMsg).iterator();
    }

}
