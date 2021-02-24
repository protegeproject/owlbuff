package edu.stanford.protege.owlbuff;

import edu.stanford.protege.owlbuf.AnnotationAssertion;
import org.semanticweb.owlapi.model.OWLAnnotationAssertionAxiom;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-01-12
 */
public class OwlAnnotationAssertionAxiom2Msg {

    @Nonnull
    private final OwlAnnotationSubject2Msg annotationSubject2Msg;

    @Nonnull
    private final OwlAnnotationProperty2Msg annotationProperty2Msg;

    @Nonnull
    private final OwlAnnotationValue2Msg annotationValue2Msg;

    @Nonnull
    private final OwlAxiomAnnotations2Msg axiomAnnotations2Msg;

    @Inject
    public OwlAnnotationAssertionAxiom2Msg(@Nonnull OwlAnnotationSubject2Msg annotationSubject2Msg,
                                           @Nonnull OwlAnnotationProperty2Msg annotationProperty2Msg,
                                           @Nonnull OwlAnnotationValue2Msg annotationValue2Msg,
                                           @Nonnull OwlAxiomAnnotations2Msg axiomAnnotations2Msg) {
        this.annotationSubject2Msg = checkNotNull(annotationSubject2Msg);
        this.annotationProperty2Msg = checkNotNull(annotationProperty2Msg);
        this.annotationValue2Msg = checkNotNull(annotationValue2Msg);
        this.axiomAnnotations2Msg = checkNotNull(axiomAnnotations2Msg);
    }

    public AnnotationAssertion toMsg(@Nonnull OWLAnnotationAssertionAxiom axiom) {
        var subject = annotationSubject2Msg.toMsg(axiom.getSubject());
        var property = annotationProperty2Msg.toMsg(axiom.getProperty());
        var value = annotationValue2Msg.toMsg(axiom.getValue());
        var annotations = axiomAnnotations2Msg.toMsg(axiom);
        return AnnotationAssertion.newBuilder()
                                  .setAnnotationProperty(property)
                                  .setAnnotationSubject(subject)
                                  .setAnnotationValue(value)
                                  .addAllAnnotations(annotations)
                                  .build();

    }
}
